package com.tocaraul.tocaraulserver.service;

import com.tocaraul.tocaraulserver.dto.NewProposalDto;
import com.tocaraul.tocaraulserver.dto.ProposalScheduleItemDto;
import com.tocaraul.tocaraulserver.entity.Artist;
import com.tocaraul.tocaraulserver.entity.Event;
import com.tocaraul.tocaraulserver.entity.Proposal;
import com.tocaraul.tocaraulserver.entity.User;
import com.tocaraul.tocaraulserver.entity.Venue;
import com.tocaraul.tocaraulserver.enums.ProposalInitiator;
import com.tocaraul.tocaraulserver.enums.UserTypeEnum;
import com.tocaraul.tocaraulserver.repository.ArtistRepository;
import com.tocaraul.tocaraulserver.repository.EventRepository;
import com.tocaraul.tocaraulserver.repository.ProposalRepository;
import com.tocaraul.tocaraulserver.repository.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class ProposalService {

    private final ArtistRepository artistRepository;
    private final EventRepository eventRepository;
    private final ProposalRepository proposalRepository;
    private final VenueRepository venueRepository;

    public ProposalService(
        ArtistRepository artistRepository,
        EventRepository eventRepository,
        ProposalRepository proposalRepository,
        VenueRepository venueRepository
    ) {
        this.artistRepository = artistRepository;
        this.eventRepository = eventRepository;
        this.proposalRepository = proposalRepository;
        this.venueRepository = venueRepository;
    }

    @Transactional
    public Proposal createProposal(NewProposalDto newProposalDto, User user) {
        if (!UserTypeEnum.VENUE.equals(user.getType())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas contratantes podem enviar propostas");
        }

        Artist artist = artistRepository.findById(newProposalDto.artistId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artista nao encontrado"));

        Venue venue = venueRepository.findByUser(user)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Perfil de contratante nao encontrado"));

        Event event = new Event();
        event.setVenue(venue);
        event.setDate(newProposalDto.eventDate());
        event.setLocal(newProposalDto.local());
        event.setGenre(newProposalDto.genre());
        event.setOfferedPrice(newProposalDto.offeredPrice());
        event.setDescription(newProposalDto.description());

        Event savedEvent = eventRepository.save(event);

        Proposal proposal = new Proposal();
        proposal.setArtist(artist);
        proposal.setEvent(savedEvent);
        proposal.setOfferedPrice(newProposalDto.offeredPrice());
        proposal.setInitiatedBy(ProposalInitiator.VENUE);

        return proposalRepository.save(proposal);
    }

    public List<ProposalScheduleItemDto> getVenueSchedule(User user, YearMonth month) {
        if (!UserTypeEnum.VENUE.equals(user.getType())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas contratantes podem consultar a agenda");
        }

        Venue venue = venueRepository.findByUser(user)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Perfil de contratante nao encontrado"));

        LocalDateTime start = month.atDay(1).atStartOfDay();
        LocalDateTime end = month.plusMonths(1).atDay(1).atStartOfDay();

        return proposalRepository.findScheduleByVenueAndDateRange(venue, start, end)
            .stream()
            .map(this::toScheduleItem)
            .toList();
    }

    private ProposalScheduleItemDto toScheduleItem(Proposal proposal) {
        Artist artist = proposal.getArtist();
        Event event = proposal.getEvent();

        return new ProposalScheduleItemDto(
            proposal.getId(),
            artist.getId(),
            artist.getName(),
            artist.getPhotoUrl(),
            artist.getGenres(),
            event.getId(),
            event.getDate(),
            event.getLocal(),
            event.getGenre(),
            event.getDescription(),
            proposal.getOfferedPrice(),
            proposal.getStatus(),
            proposal.getPaymentStatus()
        );
    }
}
