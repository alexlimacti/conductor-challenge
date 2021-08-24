package com.muxichallenge.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_terminal")
public class Terminal implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer logic;

    @NotBlank
    @Column(nullable = false)
    private String serial;

    @NotBlank
    @Column(nullable = false)
    private String model;

    private Integer sam;

    private String ptid;

    private Integer plat;
    @NotBlank
    @Column(nullable = false)
    private String version;

    private Integer mxr;

    private Integer mxf;

    private String verfm;
}
