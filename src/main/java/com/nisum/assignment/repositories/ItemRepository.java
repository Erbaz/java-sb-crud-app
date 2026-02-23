package com.nisum.assignment.repositories;

import com.nisum.assignment.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
    @Query("""
    SELECT i FROM ItemEntity i
    WHERE (:name IS NULL OR i.name LIKE CONCAT('%', :name, '%'))
      AND (:minPrice IS NULL OR i.price >= :minPrice)
      AND (:maxPrice IS NULL OR i.price <= :maxPrice)
    """)
    List<ItemEntity> findByOptionalFilters(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}
