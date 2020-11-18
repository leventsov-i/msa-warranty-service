package ru.bmtsu.warrantly.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ItemWarrantyDTO {
    private String reason;
    private Integer availableCount;
}
