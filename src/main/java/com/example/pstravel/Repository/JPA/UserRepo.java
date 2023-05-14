package com.example.pstravel.Repository.JPA;

import com.example.pstravel.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

   Optional<User> findByUserIdx(Long id);
}
