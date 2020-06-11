package entities;

import java.util.List;

public record User(
        String email,
        String password,
        String fullName,
        Integer age,
        String gender,
        String country) { }
