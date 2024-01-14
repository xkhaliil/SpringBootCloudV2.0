package com.khalil.sma.service;

import com.khalil.sma.entities.Genre;
import com.khalil.sma.entities.Game;
import com.khalil.sma.repos.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class GameServieImpl implements GameService{

    @Autowired
    GameRepository gameRepository;
    @Override
    public Game saveGame(Game e) {
        return gameRepository.save(e);
    }

    @Override
    public Game updateGame(Game e) {
        return gameRepository.save(e);
    }

    @Override
    public void deleteGame(Game e) {
        gameRepository.delete(e);

    }

    @Override
    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);

    }

    @Override
    public Game getGame(Long id) {
        return gameRepository.findById(id).get();
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Page<Game> getAllGamesParPage(int page, int size) {
        return gameRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Game> findByNomGame(String nom) {
        return gameRepository.findByNomGame(nom);
    }

    @Override
    public List<Game> findByNomGameContains(String nom) {
        return gameRepository.findByNomGameContains(nom);
    }

    @Override
    public List<Game> findByGenreIdLeg(Long id) {
        return gameRepository.findByGenreIdLeg(id);
    }

    @Override
    public List<Game> findByOrderByNomGameAsc() {
        return gameRepository.findByOrderByNomGameAsc();
    }

    @Override
    public List<Game> findByGenre(Genre l) {
        return gameRepository.findByGenre(l);
    }

    @Override
    public int countGames() {
        return gameRepository.countGames();
    }
}
