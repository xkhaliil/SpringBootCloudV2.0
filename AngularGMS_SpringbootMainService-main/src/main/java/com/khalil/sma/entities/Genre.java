package com.khalil.sma.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;


@Entity


public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLeg;
    private String nomGenre;
    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    private List<Game> games;
    public Genre() {
        super();
    }
    public Genre(String nomGenre) {
        super();
        this.nomGenre = nomGenre;
    }
       public Long getIdLeg() {
            return idLeg;
        }
    public void setIdLeg(Long idLeg) {
        this.idLeg = idLeg;
    }

    public String getNomGenre() {
        return nomGenre;
    }
    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }
    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }
    @Override
    public String toString() {
        return "Genre [idCat=" + idLeg + ", nomGenre=" + nomGenre + "]";
    }

}
