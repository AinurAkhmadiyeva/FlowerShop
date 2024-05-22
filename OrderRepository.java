package FinalProject.FlowerShop.repository;

import FinalProject.FlowerShop.model.Order;
import FinalProject.FlowerShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByStatus(String status);
}
