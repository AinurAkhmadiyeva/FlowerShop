package FinalProject.FlowerShop.mapper;

import FinalProject.FlowerShop.dto.FlowerDto;
import FinalProject.FlowerShop.model.Flower;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlowerMapper {
    FlowerDto toDto(Flower flower);
    Flower toEntity(FlowerDto flowerDto);
}

