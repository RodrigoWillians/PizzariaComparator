package com.univesp.pi.pizzariacomparator.Model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_roles")
public class Roles implements GrantedAuthority{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    
    @Override
    public String getAuthority() {
        return this.roleName;
    }

    
    public Roles(String role) {
    }

}
