package com.allofus.admin.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "adm_produto")
@Data
public class Product implements Model {


    @Id
    @SequenceGenerator(name = "seq_generator_product", sequenceName = "seq_adm_produto")
    @GeneratedValue(generator = "seq_generator_product",strategy = GenerationType.SEQUENCE)
    @Column(name = "id_produto")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_assinatura")
    private Signature signature;

    @Column(name = "tx_nome")
    private String name;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SubProduct> subProducts;

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
