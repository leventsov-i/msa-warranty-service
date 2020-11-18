package ru.bmtsu.warrantly.entity;

import lombok.*;
import ru.bmtsu.warrantly.utils.WarrantyStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "warranty")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warranty {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", length = 1024)
    private String comment;

    @Getter
    @Column(name = "item_uid", nullable = false, unique = true)
    private UUID itemUid;

    @Getter
    @Setter
    @Column(name = "status", nullable = false)
    private WarrantyStatus status;

    @Getter
    @Column(name = "warranty_date", nullable = false)
    private Timestamp warrantyDate;
}
