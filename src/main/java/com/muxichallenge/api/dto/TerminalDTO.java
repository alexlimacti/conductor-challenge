package com.muxichallenge.api.dto;

import lombok.Data;

@Data
public class TerminalDTO {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer logic;
    private String serial;
    private String model;
    private Integer sam;
    private String ptid;
    private Integer plat;
    private String version;
    private Integer mxr;
    private Integer mxf;
    private String verfm;

}
