package de.tritux.db.Auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    RECRUTEUR_READ("recruteur:read"),
    RECRUTEUR_UPDATE("recruteur:update"),
    RECRUTEUR_CREATE("recruteur:create"),
    RECRUTEUR_DELETE("recruteur:delete"),
    CANDIDAT_READ("candidat:read"),

    ;

    @Getter
    private final String permission;
}
