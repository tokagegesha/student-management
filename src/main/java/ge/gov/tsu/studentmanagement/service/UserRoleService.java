package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.User;
import ge.gov.tsu.studentmanagement.entity.security.UserRoles;
import ge.gov.tsu.studentmanagement.repository.security.RoleRepository;
import ge.gov.tsu.studentmanagement.repository.security.UserRepository;
import ge.gov.tsu.studentmanagement.repository.security.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserRoleService {

    private UserRolesRepository userRolesRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserRoleService(
            UserRolesRepository userRolesRepository,
            UserRepository userRepository, RoleRepository roleRepository) {
        this.userRolesRepository = userRolesRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserRoles addRoleToUser(Long userId, Long roleId) {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(userId);
        userRoles.setRole(roleRepository.getById(roleId));
        return userRolesRepository.save(userRoles);
    }
}
