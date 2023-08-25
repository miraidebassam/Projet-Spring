package com.isi2.examenspring.Repositorys;

import com.isi2.examenspring.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameOrEmail(String username, String password);

    Optional<User> findByUsername(String username);
}
