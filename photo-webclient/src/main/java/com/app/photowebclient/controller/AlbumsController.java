package com.app.photowebclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class AlbumsController {

    @GetMapping("/albums")
    public String getAlbums (Model model){
        model.addAttribute("albums", "albums");
        return "albums";
    }

}
