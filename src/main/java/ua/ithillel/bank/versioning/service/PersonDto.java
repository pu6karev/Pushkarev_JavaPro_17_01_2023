package ua.ithillel.bank.versioning.service;

import lombok.Builder;

@Builder
public record PersonDto(String id, String name, String email) {

}
