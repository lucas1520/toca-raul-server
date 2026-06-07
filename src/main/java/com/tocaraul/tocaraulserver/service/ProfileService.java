package com.tocaraul.tocaraulserver.service;

import com.tocaraul.tocaraulserver.dto.MeResponseDto;
import com.tocaraul.tocaraulserver.entity.Artist;
import com.tocaraul.tocaraulserver.entity.User;
import com.tocaraul.tocaraulserver.entity.Venue;
import com.tocaraul.tocaraulserver.enums.UserTypeEnum;
import com.tocaraul.tocaraulserver.repository.ArtistRepository;
import com.tocaraul.tocaraulserver.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ArtistRepository artistRepository;
    private final VenueRepository venueRepository;

    public MeResponseDto buildMeResponse(User user) {

        Artist artist = null;
        Venue venue = null;

        if (UserTypeEnum.ARTIST.name().equals(user.getType())) {
            artist = artistRepository
                    .findByUser(user)
                    .orElse(null);
        }

        if (UserTypeEnum.VENUE.name().equals(user.getType())) {
            venue = venueRepository
                    .findByUser(user)
                    .orElse(null);
        }

        return new MeResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getType(),
                artist,
                venue
        );
    }
}