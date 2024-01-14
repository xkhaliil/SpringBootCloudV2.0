package com.khalil.sma.service;

import com.khalil.sma.entities.Genre;
import com.khalil.sma.entities.Game;
import org.springframework.data.domain.Page;

import java.util.List;
public interface GameService {


    Game saveGame(Game e);
    Game updateGame(Game e);
    void deleteGame(Game e);
    void deleteGameById(Long id);
    Game getGame(Long id);
    List<Game> getAllGames();
    Page<Game> getAllGamesParPage(int page, int size);
    List<Game> findByNomGame(String nom);
    List<Game> findByNomGameContains(String nom);
    List<Game> findByGenreIdLeg (Long id);
    List<Game> findByOrderByNomGameAsc();
    List<Game>findByGenre(Genre l);



    int  countGames();
}
