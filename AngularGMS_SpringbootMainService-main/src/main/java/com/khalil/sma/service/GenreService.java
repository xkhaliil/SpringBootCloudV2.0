package com.khalil.sma.service;

import com.khalil.sma.entities.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAllGenres();
    Genre saveGenre(Genre l);
}
