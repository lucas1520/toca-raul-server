package com.tocaraul.tocaraulserver.repository;

import com.tocaraul.tocaraulserver.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
