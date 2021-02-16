package com.allofus.gateway.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adm_assinatura")
@Data
public class Signature {

    @Id
    @SequenceGenerator(name = "seg_generator_signature", sequenceName = "seq_adm_assinatura")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seg_generator_signature")
    @Column(name = "id_assinatura")
    private Long id;

    @Column(name = "tx_nome_cliente")
    private String nameCustomer;

    @Column(name = "txt_nome_contato")
    private String nameContact;

    @Column(name = "txt_email_contato")
    private String emailContact;

    @Column(name = "tx_telefone_contato")
    private String tellphoneContact;

    @Column(name = "tx_tipo_pessoa")
    private String kindOfPerson;

    @Column(name = "tx_inscricao")
    private String cpfCnpj;

    @Column(name = "tx_numero_banco")
    private String numberBank;

    @Column(name = "tx_numero_agencia")
    private String numberAgency;

    @Column(name = "tx_numero_conta")
    private String numberAcount;

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


}
