package com.isi2.examenspring.Services;

import com.isi2.examenspring.Models.User;
import com.isi2.examenspring.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.isi2.examenspring.Models.Role;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServices {
    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        User userToUpdate = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + userId));
        userToUpdate.setName(updatedUser.getName());
        userToUpdate.setPrenom(updatedUser.getPrenom());
        userToUpdate.setEmail(updatedUser.getEmail());
        return userRepository.save(userToUpdate);
    }

    public void deleteTask(Long userId) {
        User userToDelete = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + userId));

        userRepository.delete(userToDelete);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
