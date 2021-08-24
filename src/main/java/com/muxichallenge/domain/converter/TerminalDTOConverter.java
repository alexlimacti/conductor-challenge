package com.muxichallenge.domain.converter;

import com.muxichallenge.api.dto.TerminalDTO;
import com.muxichallenge.domain.model.Terminal;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TerminalDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TerminalDTO getTerminalDto(Terminal terminal){
        TerminalDTO terminalDTO = modelMapper.map(terminal, TerminalDTO.class);
        return terminalDTO;
    }

    public Terminal getTerminal(TerminalDTO terminalDTO){
        Terminal terminal = modelMapper.map(terminalDTO, Terminal.class);
        return terminal;
    }

    public List<TerminalDTO> getTerminalDTOs(List<Terminal> terminals){
        List<TerminalDTO> list = modelMapper.map(terminals, new TypeToken<List<TerminalDTO>>() {}.getType());
        return list;
    }
    
}
