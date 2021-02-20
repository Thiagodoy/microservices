package com.allofus.admin.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adm_subproduto")
@Data
public class SubProduct implements Model {

    @Id
    @SequenceGenerator(name = "seq_generator_subproduct", sequenceName = "seq_adm_subproduto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_generator_subproduct")
    @Column(name = "id_subproduto")
    private Long id;

    @OneToOne
    private Signature signature;

    @ManyToOne
    private Product product;

    @Column(name = "tx_descricao")
    private String description;

    @Column(name = "in_ativo")
    private Boolean isEnable;

    @Column(name = "dt_criacao")
    private LocalDateTime createdAt;

    @Column(name = "dt_alteracao")
    private LocalDateTime updateAt;

    @Column(name = "id_usuario_ator")
    private Long user;

    @PrePersist
    public void generateCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void generateUpdateAt(){
        this.updateAt = LocalDateTime.now();
    }


    @Override
    public Long getId() {
        return id;
    }
}
