package com.allofus.admin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "adm_usuario")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails,Model {

    @Id
    @SequenceGenerator(name = "seq_generator_user",sequenceName = "seq_adm_usuario")
    @GeneratedValue(generator = "seq_generator_user",strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "tx_usuario")
    private String name;

    @Column(name = "tx_email")
    private String email;

    @Column(name = "tx_senha")
    private String password;

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

    @Column(name = "nr_erros_senha")
    private Long attempts;

    @OneToOne
    @JoinColumn(name = "id_assinatura")
    private Signature signature;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserProfile>profiles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }


//    @PrePersist
//    public void generateCreatedAt(){
//        this.createdAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void generateUpdateAt(){
//        this.updateAt = LocalDateTime.now();
//    }

    public Long getId(){
        return id;
    }
}
