package ru.bmtsu.warrantly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmtsu.warrantly.controller.dto.repsonse.ErrorDTO;
import ru.bmtsu.warrantly.controller.dto.repsonse.OrderWarrantyDTO;
import ru.bmtsu.warrantly.controller.dto.repsonse.WarrantyInfoDTO;
import ru.bmtsu.warrantly.controller.dto.request.ItemWarrantyDTO;
import ru.bmtsu.warrantly.exception.NotFoundWarrantyException;
import ru.bmtsu.warrantly.service.WarrantyService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warranty")
public class WarrantyController {
    private final WarrantyService warrantyService;

    @Autowired
    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }

    @GetMapping("/{item}")
    public ResponseEntity<WarrantyInfoDTO> get(@PathVariable UUID item) {
        WarrantyInfoDTO warrantyInfoDTO = warrantyService.getWarrantyInfoDTO(item);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(warrantyInfoDTO);
    }

    @PostMapping("/{item}")
    public ResponseEntity start(@PathVariable UUID item) {
        warrantyService.createWarranty(item);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/{item}")
    public ResponseEntity delete(@PathVariable UUID item) {
        warrantyService.deleteWarranty(item);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping("/{item}/warranty")
    public ResponseEntity<OrderWarrantyDTO> warranty(@PathVariable UUID item, @RequestBody ItemWarrantyDTO itemWarranty) {
        OrderWarrantyDTO orderWarrantyDTO = warrantyService.warranty(item, itemWarranty);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderWarrantyDTO);
    }

    @ExceptionHandler(NotFoundWarrantyException.class)
    public ResponseEntity<ErrorDTO> notFoundWarrantyExceptionHandler(NotFoundWarrantyException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO(e.getMessage()));
    }

}
