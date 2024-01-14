package com.khalil.sma.controllers;


import com.khalil.sma.entities.Game;
import com.khalil.sma.entities.Genre;
import com.khalil.sma.service.GameService;
import com.khalil.sma.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    GenreService genreService;
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap)
    {
        List<Genre> genres = genreService.getAllGenres();
        modelMap.addAttribute("genres", genres);


        modelMap.addAttribute("game", new Game());
        modelMap.addAttribute("mode", "new");

        return "formGame";
    }


    @RequestMapping("/saveGame")
    public String saveGame(@Valid Game game,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,

                             @RequestParam (name="size", defaultValue = "2") int size) {

        if (bindingResult.hasErrors()) return "formGame";
        gameService.saveGame(game);

        // Calculate the total number of pages based on the number of teams and the page size.
        int totalGamePages = (int) Math.ceil((double) gameService.countGames() /size);
        int lastPage = totalGamePages - 1;

        redirectAttributes.addAttribute("page", lastPage);
        return "redirect:/ListeGame";
    }


    @RequestMapping("/ListeGame")
    public String ListeGame(ModelMap modelMap,
                              @RequestParam (name="page",defaultValue = "0") int page,
                              @RequestParam (name="size", defaultValue = "2") int size)
    {
        List<Genre> genres = genreService.getAllGenres();
        modelMap.addAttribute("genres", genres);
        Page <Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "ListeGame";


    }
    @RequestMapping("/supprimerGame")
    public String supprimerGame(@RequestParam("id") Long id,
                                  ModelMap modelMap,
                                  @RequestParam (name="page",defaultValue = "0") int page,
                                  @RequestParam (name="size", defaultValue = "2") int size)
    {
        gameService.deleteGameById(id);
        Page <Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "ListeGame";
    }
    @RequestMapping("/modifierGame")
    public String modifierGame(@RequestParam("id") Long id,
                                 @RequestParam (name="page",defaultValue = "0") int page,


                                 ModelMap modelMap)
    {
        Game e = gameService.getGame(id);
        List<Genre> genres = genreService.getAllGenres();
        modelMap.addAttribute("genres", genres);
        modelMap.addAttribute("game", e);
        modelMap.addAttribute("genres", genres);
        modelMap.addAttribute("selectedGenre", e.getGenre());
        modelMap.addAttribute("currentPage", page);


        modelMap.addAttribute("mode", "edit");

        return "formGame";
    }
    @RequestMapping("/updateGame")
    public String updateGame(@ModelAttribute("game") Game game,
                               @RequestParam("date") String date,
                               @RequestParam("idGame") Long id,
                               @RequestParam("page") int page,
                               @RequestParam("size") int size,
                               ModelMap modelMap) throws
            ParseException
    {
//conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        game.setDateFound(dateCreation);
        gameService.updateGame(game);
        List <Game> games = gameService.getAllGames();
        modelMap.addAttribute("games", games);
        modelMap.addAttribute("pages", page);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "ListeGame";
    }


}