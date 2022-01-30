package by.scooter.entity.enumerator;

import lombok.Getter;

@Getter
public enum RoleValue {

    ROLE_USER("USER"),
    ROLE_CLIENT("CLIENT"),
    ROLE_ADMIN("ADMIN");

    private final String title;

    RoleValue(String title) {
        this.title = title;
    }
}
