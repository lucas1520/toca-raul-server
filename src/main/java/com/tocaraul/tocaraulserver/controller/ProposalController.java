package com.tocaraul.tocaraulserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @PostMapping("/new")
    public ResponseEntity<?> newProposal() {
        return ResponseEntity.ok("ok");
    }
}
