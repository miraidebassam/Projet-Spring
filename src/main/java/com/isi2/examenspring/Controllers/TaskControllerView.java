package com.isi2.examenspring.Controllers;

import com.isi2.examenspring.Models.Task;
import com.isi2.examenspring.Models.User;
import com.isi2.examenspring.Repositorys.UserRepository;
import com.isi2.examenspring.Services.CategoryService;
import com.isi2.examenspring.Services.RoleService;
import com.isi2.examenspring.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // This means that this class is a Controller
@RequestMapping(path="/taches") // This means URL's start with /demo (after Application path)
public class TaskControllerView {

    private final TaskService taskService;
    private final CategoryService categorieService;

    private  final RoleService roleService;

    private final UserRepository userRepository;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Autowired
    public TaskControllerView(TaskService taskService, CategoryService categorieService, RoleService roleService, UserRepository userRepository) {
        this.taskService = taskService;
        this.categorieService = categorieService;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<Task> tasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("addTask")
    public Task login(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PutMapping("updateTasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @GetMapping("updateTasks")
    public String vueUpdateTask() {
        return "ModifTask";
    }

    @PostMapping("/deleteTaskById")
    public String deleteTask(@RequestParam Long taskId) {
        System.out.println("Helo task a delete "+taskId);
        taskService.deleteTask(taskId);

        return "redirect:/dashboard"; // Redirige vers le tableau de bord après l'ajout
    }

    @PostMapping("/update-category")
    public String updateTaskCategory(@RequestParam Long taskId, @RequestParam Long newCategoryId) {
        System.out.println("Helo "+newCategoryId);
        Task updatedTask = taskService.updateTaskCategory(taskId, newCategoryId);
        return "redirect:/dashboard"; // Redirige vers le tableau de bord après l'ajout
    }

    @PostMapping("/add-task")
    public String addCategory(@RequestParam String nomTache, String description, Authentication authentication) {
        // Récupérer l'utilisateur authentifié
        if (authentication != null) {
            String username = authentication.getName(); // Obtenez le nom d'utilisateur
            System.out.println("J'ai trouve "+ username);
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                System.out.println("J'ai ton user tu peux ajoute "+user.getUsername() + " "+user.getPassword());
                Task newTask = new Task();
                newTask.setName(nomTache);
                newTask.setDescription(description);

                newTask.setUser(user); // Associer l'utilisateur à la tâche

                taskService.saveTask(newTask);

                // Maintenant vous avez l'utilisateur correspondant au nom d'utilisateur actuel
            } else {
                // Gérer le cas où l'utilisateur n'a pas été trouvé
            }

            // Vous pouvez maintenant rechercher votre propre classe User en utilisant le nom d'utilisateur.
        }


        return "redirect:/Dashboard"; // Redirige vers le tableau de bord après l'ajout
    }


}
