package com.demo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.backend.entity.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByEmail(String email);

}
