package com.allofus.admin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ComponentEvent {

    @Id
    @SequenceGenerator(name = "seq_generator_event", sequenceName = "seq_adm_evento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="seq_generator_event" )
    @Column(name = "id_tipo_evento")
    private Long id;

    @ManyToOne
    private Event event;

    @Column(name = "tx_sigla")
    private String sigla;

    @Column(name = "tx_descricao")
    private String description;

    @Column(name = "in_ativo")
    private Boolean isEnable;

    @Column(name = "dt_criacao")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "dt_alteracao")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @CreatedBy
    @Column(name = "criado_por")
    private String createdByUser;

    @LastModifiedBy
    @Column(name = "atualizado_por")
    private String userCreatedUpdate;



}
