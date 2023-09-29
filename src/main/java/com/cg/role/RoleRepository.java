package com.cg.role;

import com.cg.model.ERole;
import com.cg.model.Role;
import com.cg.role.dto.RoleResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);

    @Query("SELECT " +
            "r.id, " +
            "r.code" +
            "FROM Role r"
    )
    List<RoleResult> findAllRoleDTO();
}
