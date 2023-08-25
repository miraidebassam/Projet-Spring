package com.isi2.examenspring.Services;

import com.isi2.examenspring.Models.Category;
import com.isi2.examenspring.Models.Task;
import com.isi2.examenspring.Models.User;
import com.isi2.examenspring.Repositorys.CategoryRepository;
import com.isi2.examenspring.Repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        Long categoryId = null;
        Category categoryN = categoryRepository.findById(Math.toIntExact(1)).orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        task.setCategory(categoryN);

        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        Task taskToUpdate = taskRepository.findById(Math.toIntExact(taskId))
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
        taskToUpdate.setName(updatedTask.getName());
        taskToUpdate.setDescription(updatedTask.getDescription());
        taskToUpdate.setCategory(updatedTask.getCategory());
        return taskRepository.save(taskToUpdate);
    }

    public Task updateTaskCategory(Long taskId, Long newCategoryId) {
        Task taskToUpdate = taskRepository.findById(Math.toIntExact(taskId))
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        Category newCategory = categoryRepository.findById(Math.toIntExact(newCategoryId))
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + newCategoryId));

        taskToUpdate.setCategory(newCategory);
        return taskRepository.save(taskToUpdate);
    }



    public void deleteTask(Long taskId) {
        Task taskToDelete = taskRepository.findById(Math.toIntExact(taskId))
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        taskRepository.delete(taskToDelete);
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(Math.toIntExact(taskId)).orElse(null);
    }

    public List<Task> getTasksByUser(User user) {

        return taskRepository.findByUser(user);
    }
}
