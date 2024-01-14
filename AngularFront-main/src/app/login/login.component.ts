import { AuthService } from './../services/auth.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../model/User.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent implements OnInit {
  user =new User();
  erreur!: number;
  listeUsers: User[] = [];

  constructor(private router:Router,
    private authService:AuthService) { }

  ngOnInit(): void {
    this.authService.ListOfusers().subscribe({
      next: (data) => {
        this.listeUsers = data;
        console.log(this.listeUsers);
      },
      error: (err) => {
        console.log(err);
      }
    });
    }
      

  err:number = 0;
  onLoggedin() {
    // Check if the user exists in listeUsers
    if (this.listeUsers.some((e) => e.username === this.user.username)) {
      // User found, show a success message
      Swal.fire({
        title: 'Welcome!',
        showConfirmButton: false,
        timer: 1500,
      });
    } else {
      // User not found, set err flag and show an error message
      this.err = 1;
      Swal.fire({
        title: '',
        text: 'User is not registered',
      });
      return;
    }
  
    console.log(this.user.enabled);
  
    // Attempt to log in the user
    this.authService.login(this.user).subscribe({
      next: (data) => {
        // Extract JWT token from the response headers
        const jwToken = data.headers.get('Authorization')!;
  
        // Save the JWT token
        this.authService.saveToken(jwToken);
  
        // Redirect to the home page
        this.router.navigate(['/']);
      },
      error: (err: any) => {
        // Show an error message and allow the user to set "enabled" to true
        Swal.fire({
          showConfirmButton: false,
          showCancelButton: true,
          title: 'Account is not activated',
          preConfirm: (inputValue) => {
            if (inputValue === '') {
              Swal.showValidationMessage('Please enter a value');
            }
            return inputValue;
          },
          allowOutsideClick: () => !Swal.isLoading(),
        }).then((result) => {
          if (result.isConfirmed) {
            // Get the input value
            const inputValue = result.value;
            console.log(inputValue);
  
            // Activate the user with the entered value
            this.authService.activateUser(this.user.username, inputValue).subscribe({
              next: (data) => {
                this.err = 1;
                console.log(data);
  
                // Update the UI with a success message for account activation
               
              },
              error: (err: any) => {
                console.log(err);
  
                // Update the UI with an error message if something goes wrong during activation
                Swal.fire({
                  icon: 'error',
                 
                  text: 'error',
                });
              }
            });
          }
        });
      },
    });
  
    console.log(this.user.enabled);
  }
  
}