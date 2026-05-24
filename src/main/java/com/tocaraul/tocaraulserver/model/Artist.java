package com.tocaraul.tocaraulserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "artists")
public class Artist {

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
    private String startPrice;

}
