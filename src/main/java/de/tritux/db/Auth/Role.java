package de.tritux.db.Auth;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.var;

@RequiredArgsConstructor
public enum Role {
  
  USER(Collections.emptySet()),
  
  ADMIN(
          Set.of(
                  Permission.ADMIN_READ,
                  Permission.ADMIN_UPDATE,
                  Permission.ADMIN_DELETE,
                  Permission.ADMIN_CREATE
          )
  ),
  
  RECRUTEUR(
          Set.of(
                  Permission.RECRUTEUR_READ,
                  Permission.RECRUTEUR_UPDATE,
                  Permission.RECRUTEUR_DELETE,
                  Permission.RECRUTEUR_CREATE
          )
  ),
  
  CANDIDAT(
    		Set.of(
                    Permission.CANDIDAT_READ
    		)
  )
  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
