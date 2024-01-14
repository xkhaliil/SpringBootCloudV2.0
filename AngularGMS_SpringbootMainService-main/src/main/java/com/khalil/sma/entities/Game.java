package com.khalil.sma.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGame;
    @NotNull

    private String nomGame;




    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent

    private Date dateFound;
    @ManyToOne
    private Genre genre;
    @OneToOne
    private Image image;

    public Game() {
        super();
    }
    public Game(String nomGame) {
        super();
        this.nomGame = nomGame;

    }
    public Long getIdGame() {
        return idGame;
    }
    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }
    public String getNomGame() {
        return nomGame;
    }
    public void setNomGame(String nomGame) {
        this.nomGame = nomGame;
    }

    public void setDateFound(Date dateFound) {
        this.dateFound = dateFound;
    }
    public Date getDateFound() {
        return dateFound;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    @Override
    public String toString() {
        return "Game [idGame=" + idGame + ", nomGame=" + nomGame + ", =" + "]";
    }




}
