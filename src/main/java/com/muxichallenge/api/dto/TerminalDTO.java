package com.muxichallenge.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TerminalDTO {

    private static final long serialVersionUID = 1L;
    private Long id;

    @NotNull
    private Integer logic;

    @NotBlank
    private String serial;
    private String model;
    private Integer sam;
    private String ptid;
    private Integer plat;

    @NotBlank
    private String version;
    private Integer mxr;
    private Integer mxf;
    private String verfm;

}
