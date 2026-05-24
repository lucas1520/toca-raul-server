package com.tocaraul.tocaraulserver.service;

import com.tocaraul.tocaraulserver.model.Artist;
import com.tocaraul.tocaraulserver.repository.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> listArtists(int size) {
        Pageable pageable = PageRequest.of(0, size);
        Page<Artist> page = this.artistRepository.findAll(pageable);

        return page.getContent();
    }

    public Artist save(Artist artist){
        return this.artistRepository.save(artist);
    }
}
