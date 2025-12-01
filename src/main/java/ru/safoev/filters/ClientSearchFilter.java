package ru.safoev.filters;

public record ClientSearchFilter(
        Long client_id,
        String client_email,
        Integer pageSize,
        Integer pageNumber
) {}
