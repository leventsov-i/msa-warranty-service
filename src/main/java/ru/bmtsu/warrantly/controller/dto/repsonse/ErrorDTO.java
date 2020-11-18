package ru.bmtsu.warrantly.controller.dto.repsonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private String message;
}
