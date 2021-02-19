package com.allofus.admin.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Company implements Model {

    @Id
    @SequenceGenerator(name = "seq_generator_company", sequenceName = "seq_adm_empresa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator_company")
    @Column(name = "id_empresa")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_assinatura")
    private Signature signature;

    @Column(name = "tx_nome_fantasia")
    private String companyName;

    @Column(name = "tx_razao_social", nullable = false)
    private String companyNickname;

    @Column(name = "tx_inscricao")
    private String cnpjIE;

    @Column(name = "tx_endereco")
    private String address;

    @Column(name = "tx_telefone_contato")
    private String telephone;

    @Column(name = "tx_email_contato")
    private String email;

    @Column(name = "in_ativo", nullable = false)
    private Boolean isEnable;

    @Column(name = "dt_criacao")
    private LocalDateTime createdAt;

    @Column(name = "dt_alteracao")
    private LocalDateTime updateAt;

    @Column(name = "id_usuario_ator", nullable = false)
    private Long userCreatedUpdate;

    @PrePersist
    public void generateCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void generateUpdateAt() {
        this.updateAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }


}
