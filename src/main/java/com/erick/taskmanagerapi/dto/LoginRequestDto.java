package com.erick.taskmanagerapi.dto;

public record LoginRequestDto(
    String email,
    String password
) {}