package com.isi2.examenspring.Controllers;

import com.isi2.examenspring.Models.Category;
import com.isi2.examenspring.Models.Task;
import com.isi2.examenspring.Models.User;
import com.isi2.examenspring.Repositorys.UserRepository;
import com.isi2.examenspring.Services.CategoryService;
import com.isi2.examenspring.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DashboardControllerView {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public String showDashboard(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName(); // Obtenez le nom d'utilisateur
            System.out.println("J'ai trouve " + username);
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                List<Task> userTasks = taskService.getTasksByUser(user);
                List<Category> categories = categoryService.getAllCategory();

                // Organiser les tâches par catégorie
                Map<Category, List<Task>> tasksByCategory = new HashMap<>();
                for (Category category : categories) {
                    List<Task> tasksForCategory = userTasks.stream()
                            .filter(task -> task.getCategory().equals(category))
                            .collect(Collectors.toList());
                    tasksByCategory.put(category, tasksForCategory);
                }

                model.addAttribute("tasksByCategory", tasksByCategory);
                model.addAttribute("categories", categories);
                model.addAttribute("user", user);
            }

        }
        return "Dashboard";

    }

    @GetMapping("/loginUser")
    public String showLoginPage() {
        System.out.println("hello team");
        return "login";
    }

    @GetMapping("/addTask")
    public String addTask() {
        System.out.println("hello team");
        return "AddTask";
    }


    @GetMapping("/addCategori")
    public String addCategorie() {
        System.out.println("hello team");
        return "AddCategorie";
    }

    @PostMapping("/loginUser")
    public String showDashboard(@RequestParam String email,@RequestParam String password) {
        System.out.println("hello team dev"+email+password);
        return "Dashboard";
    }

    @PostMapping("/add-category")
    public String addCategory(@RequestParam String categoryName) {
        Category newCategory = new Category();
        newCategory.setCategory_name(categoryName);
        categoryService.saveCategory(newCategory);

        return "AddCategorie"; // Redirige vers le tableau de bord après l'ajout
    }

}
