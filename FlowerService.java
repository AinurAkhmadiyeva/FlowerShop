package FinalProject.FlowerShop.service;

import FinalProject.FlowerShop.dto.FlowerDto;
import FinalProject.FlowerShop.mapper.FlowerMapper;
import FinalProject.FlowerShop.model.Flower;
import FinalProject.FlowerShop.repository.FlowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerService {
    private final FlowerRepository flowerRepository;
    private final FlowerMapper flowerMapper;

    public FlowerService(FlowerRepository flowerRepository, FlowerMapper flowerMapper) {
        this.flowerRepository = flowerRepository;
        this.flowerMapper = flowerMapper;
    }

    public List<FlowerDto> findAll() {
        return flowerRepository.findAll().stream()
                .map(flowerMapper::toDto)
                .collect(Collectors.toList());
    }

    public FlowerDto findById(Long id) {
        Flower flower = flowerRepository.findById(id).orElseThrow(() -> new RuntimeException("Flower not found"));
        return flowerMapper.toDto(flower);
    }

    public FlowerDto addFlower(FlowerDto flowerDto) {
        Flower flower = flowerMapper.toEntity(flowerDto);
        return flowerMapper.toDto(flowerRepository.save(flower));
    }

    public FlowerDto updateFlower(Long id, FlowerDto flowerDto) {
        Flower flower = flowerRepository.findById(id).orElseThrow(() -> new RuntimeException("Flower not found"));
        flower.setName(flowerDto.getName());
        flower.setPrice(flowerDto.getPrice());
        flower.setQuantity(flowerDto.getQuantity());
        return flowerMapper.toDto(flowerRepository.save(flower));
    }

    public void deleteFlower(Long id) {
        flowerRepository.deleteById(id);
    }
}
