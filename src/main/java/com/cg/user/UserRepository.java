package com.cg.user;

import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    List<User> findByUsername(String username);


    List<User> findAllUser();

    List<User> findUserByUsername(String username);


    Boolean existsByUsername(String username);


}
