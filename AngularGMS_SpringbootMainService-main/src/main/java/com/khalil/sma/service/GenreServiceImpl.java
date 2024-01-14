package com.khalil.sma.service;

import com.khalil.sma.entities.Genre;
import com.khalil.sma.repos.GenreRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    private GenreRepos genreRepos;
    @Override
    public List<Genre> getAllGenres() {
        return genreRepos.findAll();
    }

    @Override
    public Genre saveGenre(Genre l) {
        return genreRepos.save(l);
    }
}
