package FinalProject.FlowerShop.mapper;

import FinalProject.FlowerShop.dto.OrderDto;
import FinalProject.FlowerShop.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
