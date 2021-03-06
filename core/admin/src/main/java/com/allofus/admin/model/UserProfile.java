package com.allofus.admin.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "id_rel_perfil_usuario")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @SequenceGenerator(name = "seq_generator_user_profile", sequenceName = "seq_adm_usuario_perfil")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator_user_profile")
    @Column(name = "id_rel_perfil_usuario")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_perfil")
    private Profile profile;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @Column(name = "in_ativo")
    private Boolean isEnable;

    @Column(name = "dt_criacao")
    private LocalDateTime createdAt;

    @Column(name = "dt_alteracao")
    private LocalDateTime updateAt;

    @Column(name = "id_usuario_ator")
    private Long userCreatedUpdate;

    @PrePersist
    public void generateCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void generateUpdateAt(){
        this.updateAt = LocalDateTime.now();
    }

}
