package ru.bmtsu.warrantly.controller.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ItemWarrantyDTO {
    private String reason;
    private Integer availableCount;
}
