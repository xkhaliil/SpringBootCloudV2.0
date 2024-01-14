package com.khalil.sma.repos;

import com.khalil.sma.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepos extends JpaRepository<Genre,Long> {

}
