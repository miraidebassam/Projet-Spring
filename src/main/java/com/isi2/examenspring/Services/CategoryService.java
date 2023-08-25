package com.isi2.examenspring.Services;

import com.isi2.examenspring.Models.Category;
import com.isi2.examenspring.Models.Task;
import com.isi2.examenspring.Repositorys.CategoryRepository;
import com.isi2.examenspring.Repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, Category updatedCategorie) {
        Category categoryToUpdate = categoryRepository.findById(Math.toIntExact(categoryId))
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + categoryId));
        categoryToUpdate.setCategory_name(updatedCategorie.getCategory_name());
        return categoryRepository.save(categoryToUpdate);
    }

    public void deleteCategory(Long categoryId) {
        Category categoryToDelete = categoryRepository.findById(Math.toIntExact(categoryId))
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + categoryId));

        categoryRepository.delete(categoryToDelete);
    }

    public void addTaskToCategory(Long categoryId, Task task) {
        Optional<Category> optionalCategory = categoryRepository.findById(Math.toIntExact(categoryId));

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            task.setCategory(category);
            taskRepository.save(task);
        } else {
            // Gérer le cas où la catégorie n'est pas trouvée
        }
    }

    public Optional<Category> findById(Long newCategoryId) {
        return categoryRepository.findById(Math.toIntExact(newCategoryId));
    }
}
