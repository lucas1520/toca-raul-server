package com.tocaraul.tocaraulserver.entity;

import com.tocaraul.tocaraulserver.dto.RegisterArtistDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "artists")
public class Artist {

    public static Artist from(RegisterArtistDto registerArtistDto, User user) {
        Artist artist = new Artist();

        artist.setAbout(registerArtistDto.about());
        artist.setGenres(registerArtistDto.genres());
        artist.setName(registerArtistDto.name());
        artist.setUser(user);
        artist.setPhotoUrl(registerArtistDto.photoUrl());
        artist.setSamplesUrl(registerArtistDto.samplesUrl());
        artist.setStartPrice(registerArtistDto.startPrice());

        return artist;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String about;

    @Getter
    @Setter
    private String photoUrl;

    @Getter
    @Setter
    private String genres;

    @Getter
    @Setter
    private String samplesUrl;

    @Getter
    @Setter
    @Column(scale = 2)
    private BigDecimal startPrice;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

}
