package com.project.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
//Securitynin anlayacagi User class

    private Long id;
    private String username;
    private String name;
    private Boolean isChiefDoctor;

    @JsonIgnore
    private String password;
    private String ssn;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String name, Boolean isChiefDoctor, String password, String ssn, String role ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.isChiefDoctor = isChiefDoctor;
        this.password = password;
        this.ssn = ssn;

        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        this.authorities=grantedAuthorities;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }

    public boolean equals(Object o){
        if (this==o){   //bu metodu cagiran nesne ile Parametrede gelen "o" isimli nesneyi karsilastirir. Ama Java bu nesnelerin bellekteki referanslarini karsilastirir.
            return true;
        }

        //parametre ile gelen nesnenin userDetailsImpl turunden mi kontrolu
        if (o==null || getClass() != o.getClass()){

            return false;
        }

        //UserDetailsImpl sinifindan oldugu dogrulanan nesnenin id ozelliginin mevcut nesnenin id ozelligi ile esit olup olmadigini kontrol eder

        UserDetailsImpl user=(UserDetailsImpl)o;
        return Objects.equals(id,user.getId()); //id ile kiyaslama
    }
}
