package ru.bmtsu.warrantly.controller.dto.repsonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.bmtsu.warrantly.utils.WarrantyDecision;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderWarrantyDTO {
    private String warrantyDate;
    private WarrantyDecision decision;
}
