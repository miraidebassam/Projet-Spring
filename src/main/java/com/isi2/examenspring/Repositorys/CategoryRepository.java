package com.isi2.examenspring.Repositorys;

import com.isi2.examenspring.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //@Query("SELECT DISTINCT c FROM Category c JOIN FETCH c.tasks")
    //List<Category> findAllWithTasks();
}
