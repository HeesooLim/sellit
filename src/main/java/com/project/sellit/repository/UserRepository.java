package com.project.sellit.repository;

import com.project.sellit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Modifying
    @Query("update Users u set u.email = ?2, u.firstName = ?3, u.lastName = ?4, u.password = ?5 where u.username = ?1")
    void updateUser(String username, String email, String firstName, String lastName, String password);
}
