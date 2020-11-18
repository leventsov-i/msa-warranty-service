package ru.bmtsu.warrantly.controller.dto.repsonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorDTO {
    private String message;
}
