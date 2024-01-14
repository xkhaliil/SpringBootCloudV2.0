import { AuthService } from './../services/auth.service';
import { User } from './../model/User.model';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private auth:AuthService) { }

  
  User=new User();
  passConf!:string;


  ngOnInit(): void {
  }

  register(){
    if(this.User.password!=this.passConf){
      Swal.fire({
        title: 'Password not match',
      });
      return;
    }
    console.log(this.User)
    
    
    this.auth.Register(this.User).subscribe(
      data=>{
        console.log(data);
        Swal.fire({
          title: 'Please confirm your email',
          timer: 2000
        });
        
        
       
      },
      err=>{
        console.log(err);
      }
    )
    
  }

}
