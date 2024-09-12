package com.mindtrack.backend.prescription.interfaces.rest.resources;

public record PillResource(
        String name,
        String description,
        int quantity
) {
}
