package com.khalil.sma.repos;

import com.khalil.sma.entities.Game;
import com.khalil.sma.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



import java.util.List;



@RepositoryRestResource(path = "rest")
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByNomGame(String nom);
    List<Game>findByNomGameContains(String nom);
    @Query("select p from Game p where p.genre = ?1")
    List<Game> findByGenre (Genre l);
    List<Game> findByGenreIdLeg(Long id);

    List<Game> findByOrderByNomGameAsc();
    @Query("select count(p) from Game p")
    int  countGames();



}
