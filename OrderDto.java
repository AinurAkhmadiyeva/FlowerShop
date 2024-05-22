package FinalProject.FlowerShop.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Long flowerId;
    private int quantity;
    private String status;
}