package electronicCommerce.persistence.h2.repositories;

import electronicCommerce.persistence.h2.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query("SELECT p FROM ProductEntity p WHERE :dateParam >= p.startDate AND :dateParam <= p.endDate AND p.productId = :productIdParam AND p.brandEntity.id = :brandIdParam")
    List<ProductEntity> findByDateBetweenAndProductIdAndBrandId(
            @Param("dateParam") LocalDateTime date,
            @Param("productIdParam") int productId,
            @Param("brandIdParam") int brandId);
}
