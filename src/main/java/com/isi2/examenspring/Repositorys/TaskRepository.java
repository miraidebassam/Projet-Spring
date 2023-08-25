package com.isi2.examenspring.Repositorys;

import com.isi2.examenspring.Models.Task;
import com.isi2.examenspring.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUser(User user);
}
