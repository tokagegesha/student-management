package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.config.TsuGrantedAuthority;
import ge.gov.tsu.studentmanagement.entity.User;
import ge.gov.tsu.studentmanagement.entity.security.AccessType;
import ge.gov.tsu.studentmanagement.entity.security.RolePermission;
import ge.gov.tsu.studentmanagement.entity.security.UserRoles;
import ge.gov.tsu.studentmanagement.exception.TsuRuntimeException;
import ge.gov.tsu.studentmanagement.repository.security.RolePermissionsRepository;
import ge.gov.tsu.studentmanagement.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RolePermissionsRepository rolePermissionsRepository;

    /*public MyUserDetailsService(UserRepository userRepository, UserRolesExtendedRepository userRolesExtendedRepository){
        this.userRepository=userRepository;
    }*/


    @Override
    public User loadUserByUsername(String mail) throws UsernameNotFoundException, TsuRuntimeException {
        try {
            System.out.println("findingUser " + mail);
            User user = userRepository.getUser(mail);
            if (user.getStatus()==null || user.getStatus().equals(User.Type.ACTIVE.getValue())) {
                throw new TsuRuntimeException();
            }
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            user.setAuthorities(getAuthorities(user.getId()));
            user.getUserRoles().getRole();
            return user;
        }
        catch (TsuRuntimeException e) {
            e.printStackTrace();
            throw new TsuRuntimeException("User Not Verified");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new TsuRuntimeException("User not found");
        }
    }

    @Cacheable("USER_AUTHORITIES")
    public Set<GrantedAuthority> getAuthorities(Long userId) {
        Date actionDate = new Date();
        char[] accessChars;
        Set<GrantedAuthority> authorities = new HashSet<>();
        UserRoles userRoles = userRepository.getUser(userId).getUserRoles();
            for (RolePermission rolePermission : rolePermissionsRepository.getRolePermissions(userRoles.getRoleId())) {
                accessChars = Integer.toBinaryString(rolePermission.getAccess()).toCharArray();
                for (int i = 0; i < accessChars.length; i++) {
                    if (accessChars[accessChars.length - 1 - i] == '1') {
                        authorities.add(new TsuGrantedAuthority(rolePermission.getPermission().getName(), AccessType.getByValue(i)));
                        //  System.out.print(rolePermission.getPermission().getName() + "_" + AccessType.getByValue(i).name() + ", ");
                    }
                }
            }

        return authorities;
    }
}