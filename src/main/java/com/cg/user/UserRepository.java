package com.cg.user;

import com.cg.model.User;
<<<<<<< HEAD
import com.cg.user.dto.UserDTO;
=======

import com.cg.user.dto.UserResult;
>>>>>>> thi-dev
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.cg.user.UserDTO (" +
            "u.id, " +
            "u.fullName, " +
            "u.username, " +
            "u.email, " +
            "u.phone, " +
            "u.role," +
            "u.deleted"+
            ") " +
            "FROM User u " +
            "WHERE u.role.id <> 1"
    )
    List<UserResult> findAllUserDTO();
    @Query("SELECT NEW com.cg.model.dto.user.UserDTO (" +
                "u.id, " +
                "u.username" +
            ") " +
            "FROM User u " +
            "WHERE u.username = ?1"
    )
    Optional<UserResult> findUserDTOByUsername(String username);


    Boolean existsByUsername(String username);


}
