package com.khalil.sma;

import com.khalil.sma.entities.Genre;
import com.khalil.sma.entities.Game;
import com.khalil.sma.repos.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SMATests {
    @Autowired
    private GameRepository gameRepository;


    @Test
    void contextLoads() {
    }
    @Test
    public void testCreateGame(){
        Game game = new Game("battlefield");
        gameRepository.save(game);
    }
    @Test

    public void testFindByNomGame()
    {
        List<Game> prods = gameRepository.findByNomGame("battlefield");
        for (Game p : prods)
        {
            System.out.println(p);
        }
    }
    @Test
    public void testFindByNomProduitContains ()
    {
        List<Game> prods=gameRepository.findByNomGameContains("a");
        for (Game p : prods)
        {
            System.out.println(p);
        } }

    @Test
    public void testfindByGenre()
    {
        Genre l = new Genre();
        l.setIdLeg(1L);
        List<Game> prods=gameRepository.findByGenre(l);
        for (Game p : prods)
        {
            System.out.println(p);
        } }
    @Test
    public void findByCategorieIdCat()
    {
        List<Game> prods=gameRepository.findByGenreIdLeg(1L);
        for (Game p : prods)
        {
            System.out.println(p);
        }
    }
    @Test
    public void testfindByOrderByNomGameAsc()
    {
        List<Game> prods=gameRepository.findByOrderByNomGameAsc();
        for (Game p : prods)
        {
            System.out.println(p);
        }
    }



}
