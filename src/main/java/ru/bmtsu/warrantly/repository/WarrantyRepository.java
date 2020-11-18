package ru.bmtsu.warrantly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bmtsu.warrantly.entity.Warranty;

import java.util.Optional;
import java.util.UUID;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    Optional<Warranty> findByItemUid(UUID itemUid);
}
