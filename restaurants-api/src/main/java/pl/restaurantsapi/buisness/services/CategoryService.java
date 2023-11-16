package pl.restaurantsapi.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.CategoryDTO;
import pl.restaurantsapi.buisness.dto.RestaurantDTO;
import pl.restaurantsapi.buisness.dto.mappers.CategoryMapper;
import pl.restaurantsapi.buisness.dto.mappers.RestaurantMapper;
import pl.restaurantsapi.infrastructure.database.entities.CategoryEntity;
import pl.restaurantsapi.infrastructure.database.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Transactional
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.map(category))
                .toList();
    }

    @Transactional
    public CategoryDTO getCategoryById(Integer categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return categoryMapper.map(category.get());
        }else throw new EntityNotFoundException("Category with categoryId: [%s], not found".formatted(categoryId));
    }

    @Transactional
    public Integer getCategoryIdByName(String categoryName) {

        return categoryRepository.findAll().stream()
                .filter(category -> category.getCategoryName().equals(categoryName))
                .map(CategoryEntity::getCategoryId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Cannot find category with this name: %s".formatted(categoryName)));
    }

    @Transactional
    public void addNewCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryName(categoryDTO.getCategoryName())
                .build();

        categoryRepository.save(categoryEntity);
    }

}
