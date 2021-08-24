package com.muxichallenge.domain.service;

import com.muxichallenge.api.dto.TerminalDTO;
import com.muxichallenge.domain.converter.TerminalDTOConverter;
import com.muxichallenge.domain.exception.LogicNotFoundException;
import com.muxichallenge.domain.model.Terminal;
import com.muxichallenge.infrastructure.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerminalService {

    public static final String MSG_LOGIC_NOT_FOUND = "There is no terminal with logic %d";

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private TerminalDTOConverter terminalDTOConverter;

    public TerminalDTO findByLogic(Integer logic) {
        return terminalDTOConverter.getTerminalDto(terminalRepository.findByLogic(logic).orElseThrow(() -> new LogicNotFoundException((String.format(MSG_LOGIC_NOT_FOUND, logic)))));
    }

    public List<TerminalDTO> findAll() {
        return terminalDTOConverter.getTerminalDTOs(terminalRepository.findAll());
    }

    public TerminalDTO save(TerminalDTO terminalDTO) {
        Terminal terminal = terminalDTOConverter.getTerminal(terminalDTO);
        return terminalDTOConverter.getTerminalDto(terminalRepository.save(terminal));
    }

}
