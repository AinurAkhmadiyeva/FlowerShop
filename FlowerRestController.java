package FinalProject.FlowerShop.controller;

import FinalProject.FlowerShop.dto.FlowerDto;
import FinalProject.FlowerShop.service.FlowerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class FlowerRestController {
    private final FlowerService flowerService;

    public FlowerRestController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping
    public List<FlowerDto> getAllFlowers() {
        return flowerService.findAll();
    }

    @GetMapping("/{id}")
    public FlowerDto getFlowerById(@PathVariable Long id) {
        return flowerService.findById(id);
    }

    @PostMapping
    public FlowerDto createFlower(@RequestBody FlowerDto flowerDto) {
        return flowerService.addFlower(flowerDto);
    }

    @PutMapping("/{id}")
    public FlowerDto updateFlower(@PathVariable Long id, @RequestBody FlowerDto flowerDto) {
        return flowerService.updateFlower(id, flowerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFlower(@PathVariable Long id) {
        flowerService.deleteFlower(id);
    }

}
