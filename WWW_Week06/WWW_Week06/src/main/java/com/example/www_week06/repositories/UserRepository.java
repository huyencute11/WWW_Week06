package com.example.www_week06.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.www_week06.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u WHERE u.email = :email AND u.passwordHash = :passwordHash")
    User Login(@Param("email") String email, @Param("passwordHash") String password);

    @Query("SELECT u from User u WHERE u.token = :token AND u.experiedDate >= :currentDay")
    User ParseToken(@Param("token") String token, @Param("currentDay") LocalDate localDateTime);

    @Query("SELECT u from User u " +
            "LEFT JOIN u.posts p  WHERE u.id = :id")
    User GetUserDetails(@Param("id") long id);

    // @Query("INSERT INTO User (first_name,middle_name,last_name,mobile,email,registered_at) u WHERE u.email = :email AND u.passwordHash = :passwordHash")
    // User Register(@Param("email") String email, @Param("passwordHash") String password);
}


