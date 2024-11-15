package com.gideon.Writing_Corrector_API.model.repository;

import com.gideon.Writing_Corrector_API.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findById(String id);
}
