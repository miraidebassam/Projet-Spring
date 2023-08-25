package com.isi2.examenspring.Controllers;

import com.isi2.examenspring.Models.User;
import com.isi2.examenspring.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {
    private final UserServices userService;

    @Autowired
    public UserController(UserServices userService) {

        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("add")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteTask(id);
    }

}
