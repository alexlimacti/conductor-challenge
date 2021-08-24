package com.muxichallenge.infrastructure.repository;

import com.muxichallenge.domain.model.Terminal;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {

    Optional<Terminal> findByLogic(Integer logic);

}
