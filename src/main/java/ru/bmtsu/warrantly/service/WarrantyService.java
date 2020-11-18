package ru.bmtsu.warrantly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bmtsu.warrantly.controller.dto.repsonse.OrderWarrantyDTO;
import ru.bmtsu.warrantly.controller.dto.repsonse.WarrantyInfoDTO;
import ru.bmtsu.warrantly.controller.dto.request.ItemWarrantyDTO;
import ru.bmtsu.warrantly.entity.Warranty;
import ru.bmtsu.warrantly.exception.NotFoundWarrantyException;
import ru.bmtsu.warrantly.exception.WarrantyAlreadyExistException;
import ru.bmtsu.warrantly.repository.WarrantyRepository;
import ru.bmtsu.warrantly.utils.WarrantyDecision;
import ru.bmtsu.warrantly.utils.WarrantyStatus;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarrantyService {
    private final WarrantyRepository warrantyRepository;

    @Autowired
    public WarrantyService(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }

    public void createWarranty(UUID item) {
        checkExistWarranty(item);

        warrantyRepository.save(
                Warranty.builder()
                .itemUid(item)
                .status(WarrantyStatus.ON_WARRANTY)
                .warrantyDate(new Timestamp(System.currentTimeMillis()))
                .build()
        );
    }

    public WarrantyInfoDTO getWarrantyInfoDTO(UUID item) {
        Warranty warranty = getWarranty(item);
        return WarrantyInfoDTO.from(warranty);
    }

    public void deleteWarranty(UUID item) {
        Warranty warranty = getWarranty(item);
        warranty.setStatus(WarrantyStatus.REMOVED_FROM_WARRANTY);
        warrantyRepository.save(warranty);
    }

    public OrderWarrantyDTO warranty(UUID item, ItemWarrantyDTO itemWarranty) {
        Warranty warranty = getWarranty(item);

        return OrderWarrantyDTO.builder()
                .decision(defineWarrantyDecision(warranty.getStatus(), itemWarranty.getAvailableCount()))
                .warrantyDate(warranty.getWarrantyDate().toString())
                .build();
    }

    private void checkExistWarranty(UUID item) {
        Optional<Warranty> warranty = warrantyRepository.findByItemUid(item);
        if (warranty.isPresent()) { throw new WarrantyAlreadyExistException(); }
    }

    private Warranty getWarranty(UUID item) {
        return warrantyRepository
                .findByItemUid(item)
                .orElseThrow(NotFoundWarrantyException::new);
    }

    private WarrantyDecision defineWarrantyDecision(WarrantyStatus warrantyStatus, Integer availableCount) {
        WarrantyDecision decision;

        if (warrantyStatus != WarrantyStatus.ON_WARRANTY) {
            decision = WarrantyDecision.REFUSED;
        } else {
            if (availableCount > 0) {
                decision = WarrantyDecision.RETURN;
            } else {
                decision = WarrantyDecision.FIXING;
            }
        }

        return decision;
    }
}
