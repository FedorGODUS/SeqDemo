package Andersen.SeqDemo.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    private String role;


    public Role (String role){
        this.role=role;
    }
    private Set<SimpleGrantedAuthority> permissions;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        permissions.clear();
        switch (this.role){
            case "admin": permissions.add(new SimpleGrantedAuthority("write"));
            case "rm": permissions.add(new SimpleGrantedAuthority("read"));
            case "hrm": permissions.add(new SimpleGrantedAuthority("write"));
            case "arm": permissions.add(new SimpleGrantedAuthority("read"));
        }
        return permissions;

    }
}
