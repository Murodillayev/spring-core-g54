package uz.pdp.config.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String userId;
    private Boolean superAdmin;
    private String lang;


    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String userId, Boolean superAdmin, String lang) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.userId = userId;
        this.superAdmin = superAdmin;
        this.lang = lang;
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


    // spring core 1,1,1
    // spring mvc 1.1.1
    // spring aop 1.2.2
    // tomcat 9.0.0
    // securer 2.2.2

    // boot 3.3.3 [core:1.1.1, sec: 1.1.1, aop: 2.2.2]
    // core 4.4.4
    // aop
    // securty

}
