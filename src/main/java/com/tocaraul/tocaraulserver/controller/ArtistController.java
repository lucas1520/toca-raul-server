package com.tocaraul.tocaraulserver.controller;

import com.tocaraul.tocaraulserver.model.Artist;
import com.tocaraul.tocaraulserver.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;

    public static final int MAX_ARTISTS_TO_LIST = 50;

    ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/get")
    public List<Artist> listArtists(@RequestParam("quantity") int quantity) {
        if (quantity < 1 || quantity >  MAX_ARTISTS_TO_LIST) {
            return Collections.emptyList();
        }

        return this.artistService.listArtists(quantity);
    }

    @PostMapping("/register")
    public ResponseEntity<Artist> registerArtist(@RequestBody Artist artist) {
        if (artist  == null) {
            return ResponseEntity.badRequest().build();
        }

        Artist registeredArtist = this.artistService.save(artist);

        return new ResponseEntity<>(registeredArtist, HttpStatus.CREATED);
    }
}
