package com.cg.user;

import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User getByUsername(String username);

    Optional<User> findByUsername(String username);


//    List<User> findAllUser();
//
//    List<User> findUserByUsername(String username);


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


}
