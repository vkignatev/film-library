package ru.sber.spring.java13springmy.libraryfilmproject.constants;

import java.util.List;

public interface SecurityConstants {

    List<String> RESOURCES_WHITE_LIST = List.of("/resources/**",
            "/js/**",
            "/css/**",
            "/",
            // -- Swagger UI v3 (OpenAPI)
            "/swagger-ui/**",
            "/v3/api-docs/**");

    List<String> FILMS_WHITE_LIST = List.of("/films");
    List<String> FILMS_PERMISSION_LIST = List.of("/films/add",
            "/films/update",
            "/films/delete",
            "/directors/add",
            "/directors/update",
            "/directors/delete");

    List<String> USERS_WHITE_LIST = List.of("/login",
            "/users/registration",
            "/users/remember-password",
            "/users/change-password");

    List<String> USERS_REST_WHITE_LIST = List.of("/users/auth");
}

