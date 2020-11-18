package ru.bmtsu.warrantly.controller.dto.repsonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.bmtsu.warrantly.entity.Warranty;
import ru.bmtsu.warrantly.utils.WarrantyStatus;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class WarrantyInfoDTO {
    private String itemUid;
    private Timestamp warrantyDate;
    private WarrantyStatus status;

    public static WarrantyInfoDTO from(Warranty warranty) {
        return WarrantyInfoDTO.builder()
                .itemUid(warranty.getItemUid().toString())
                .status(warranty.getStatus())
                .warrantyDate(warranty.getWarrantyDate())
                .build();
    }
}
