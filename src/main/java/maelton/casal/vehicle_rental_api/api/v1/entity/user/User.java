package maelton.casal.vehicle_rental_api.api.v1.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_user")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Setter
    String name;

    @Setter
    @Column(unique = true)
    String email;

    @Setter
    String password;

    @Setter
    UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if(this.role == UserRole.SUPER_ADMIN) {
//            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
//                           new SimpleGrantedAuthority("ROLE_USER"));
//        } else if(this.role == UserRole.ADMIN){
//            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        } else {
//            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
//        }
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    protected User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
