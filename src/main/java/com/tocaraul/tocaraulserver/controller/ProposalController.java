package com.tocaraul.tocaraulserver.controller;

import com.tocaraul.tocaraulserver.dto.NewProposalDto;
import com.tocaraul.tocaraulserver.entity.Proposal;
import com.tocaraul.tocaraulserver.entity.User;
import com.tocaraul.tocaraulserver.service.ProposalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newProposal(@RequestBody NewProposalDto newProposalDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Proposal proposal = proposalService.createProposal(newProposalDto, user);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(Map.of("id", proposal.getId()));
    }
}
