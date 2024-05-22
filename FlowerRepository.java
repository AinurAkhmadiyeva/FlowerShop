package FinalProject.FlowerShop.repository;

import FinalProject.FlowerShop.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Long> {
    List<Flower> findByNameContainingIgnoreCase(String name);
    List<Flower> findByPriceBetween(double minPrice, double maxPrice);
}
