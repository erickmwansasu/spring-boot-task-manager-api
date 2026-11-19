package com.erick.taskmanagerapi.dto;

public record RegisterRequestDto(
    String email,
    String password
) {}