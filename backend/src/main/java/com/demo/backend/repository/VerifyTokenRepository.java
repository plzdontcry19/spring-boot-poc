package com.demo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.backend.entity.VerifyToken;

@Repository
public interface VerifyTokenRepository extends JpaRepository<VerifyToken, Long> {

    VerifyToken findByToken(String token);

}
