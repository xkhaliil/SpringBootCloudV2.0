package com.khalil.sma.controllers;


import com.khalil.sma.entities.Game;
import com.khalil.sma.entities.Genre;
import com.khalil.sma.service.GameService;
import com.khalil.sma.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GameRestController {

    @Autowired
    GameService gameService;
    @Autowired
    GenreService genreService;

    @RequestMapping(path = "all",method= RequestMethod.GET)
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }
    @RequestMapping(value="/getbyid/{id}" ,method = RequestMethod.GET )
    public Game getGame(@PathVariable("id") long id){
        return gameService.getGame(id);
    }
    @RequestMapping(path = "/addgame",method = RequestMethod.POST)
    public Game saveGame(@RequestBody Game e){
        return gameService.saveGame(e);
    }
    @RequestMapping(path = "/updategame",method = RequestMethod.PUT)
    public Game updateGame(@RequestBody Game e)
    {
        return gameService.updateGame(e);

    }
    @RequestMapping(value="/deletegame/{id}",method = RequestMethod.DELETE)
    public void  deleteGame(@PathVariable("id") long id)
    {
         gameService.deleteGameById(id);

    }
    @RequestMapping(value="/eqlg/{idLeg}",method = RequestMethod.GET)
    public List<Game> getProduitsByCatId(@PathVariable("idLeg") Long idLeg) {
        return gameService.findByGenreIdLeg(idLeg);
    }
    @RequestMapping(value="/leg",method = RequestMethod.GET)
    public List<Genre>getAllgenres(){
        return genreService.getAllGenres();
    }
    @RequestMapping(value="/addleg",method = RequestMethod.POST)
    public Genre saveGenre(@RequestBody Genre l){
        return genreService.saveGenre(l);
    }
    @RequestMapping(value="game/{nom}" ,method = RequestMethod.GET )
    public List<Game> getGame(@PathVariable("nom") String nom){
        return gameService.findByNomGameContains(nom);
    }


}
