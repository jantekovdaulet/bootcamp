package com.jantekovdaulet.bootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends BaseEntity implements GrantedAuthority {

    @Column(name = "role")
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
