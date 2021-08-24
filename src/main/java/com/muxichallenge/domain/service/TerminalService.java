package com.muxichallenge.domain.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muxichallenge.api.dto.TerminalDTO;
import com.muxichallenge.domain.converter.TerminalDTOConverter;
import com.muxichallenge.domain.exception.LogicExistingException;
import com.muxichallenge.domain.exception.LogicNotFoundException;
import com.muxichallenge.domain.model.Terminal;
import com.muxichallenge.infrastructure.repository.TerminalRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TerminalService {

    public static final String MSG_LOGIC_NOT_FOUND = "There is no terminal with logic %d";
    public static final String MSG_LOGIC_EXISTING = "Terminal with logic %d already exists";

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
        if (terminalRepository.findByLogic(terminalDTO.getLogic()).isPresent())
            throw new LogicExistingException((String.format(MSG_LOGIC_EXISTING, terminalDTO.getLogic())));
        Terminal terminal = terminalDTOConverter.getTerminal(terminalDTO);
        return terminalDTOConverter.getTerminalDto(terminalRepository.save(terminal));
    }

    public TerminalDTO update(Integer logic, TerminalDTO terminalDTO) {
        Terminal currentTerminal = terminalRepository.findByLogic(logic).orElseThrow(() -> new LogicNotFoundException((String.format(MSG_LOGIC_NOT_FOUND, logic))));
        Optional<Terminal> terminalValidation = terminalRepository.findByLogic(terminalDTO.getLogic());

        if (terminalValidation.isPresent() && terminalValidation.get().getId() != currentTerminal.getId())
            throw new LogicExistingException((String.format(MSG_LOGIC_EXISTING, terminalDTO.getLogic())));

        BeanUtils.copyProperties(terminalDTOConverter.getTerminal(terminalDTO), currentTerminal, "id");
        return terminalDTOConverter.getTerminalDto(terminalRepository.save(currentTerminal));
    }

    public TerminalDTO partialUpdate(Integer logic, Map<String, Object> fields, HttpServletRequest request) {
        Terminal currentTerminal = terminalRepository.findByLogic(logic).orElseThrow(() -> new LogicNotFoundException((String.format(MSG_LOGIC_NOT_FOUND, logic))));
        merge(fields, currentTerminal, request);
        return update(logic, terminalDTOConverter.getTerminalDto(currentTerminal));
    }

    private void merge(Map<String, Object> dadosOrigem, Terminal destinationTerminal, HttpServletRequest request) {
        ServletServerHttpRequest servletHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            Terminal terminalOrigin = objectMapper.convertValue(dadosOrigem, Terminal.class);
            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Terminal.class, nomePropriedade);
                field.setAccessible(true);
                Object newValue = ReflectionUtils.getField(field, terminalOrigin);
                ReflectionUtils.setField(field, destinationTerminal, newValue);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletHttpRequest);
        }
    }

}
