package com.allofus.gateway.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "adm_perfil")
public class Profile {

    @Id
    @SequenceGenerator(name = "seq_generator_profile",sequenceName = "seq_adm_perfil")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator_profile")
    @Column(name = "id_perfil")
    private Long id;

    @Column(name = "tx_perfil")
    private String description;

    @Column(name = "in_interno")
    private Boolean isIntern;

    @Column(name = "in_ativo")
    private Boolean isEnable;


}
