package uz.pdp.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.AuthUser;
import uz.pdp.AuthUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser authUser = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + authUser.getRole());
        authorities.add(role);
        List<SimpleGrantedAuthority> pers = authUser.getPermissions().stream().map(SimpleGrantedAuthority::new).toList();
        authorities.addAll(pers);

//        return new User(authUser.getUsername(), authUser.getPassword(), authorities);
        return new CustomUserDetails(authUser.getUsername(), authUser.getPassword(), authorities, authUser.getId(), false, "uz");

        // {ROLE_USER}
        // {ROLE_USER, show:profile, show:.. ..... }
        // {ROLE_ADMIN}


    }
}
