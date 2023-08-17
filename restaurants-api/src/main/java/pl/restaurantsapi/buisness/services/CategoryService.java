package pl.restaurantsapi.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.CategoryDTO;
import pl.restaurantsapi.buisness.dto.mappers.CategoryMapper;
import pl.restaurantsapi.infrastructure.database.entities.CategoryEntity;
import pl.restaurantsapi.infrastructure.database.repositories.CategoryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Transactional
    public CategoryDTO getCategoryById(Integer categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return categoryMapper.map(category.get());
        }else throw new EntityNotFoundException("Category with categoryId: [%s], not found".formatted(categoryId));
    }

    @Transactional
    public void addNewCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryName(categoryDTO.getCategoryName())
                .build();
        categoryRepository.save(categoryEntity);
    }
}