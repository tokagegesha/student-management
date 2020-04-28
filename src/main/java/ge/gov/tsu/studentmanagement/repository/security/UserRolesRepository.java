package ge.gov.tsu.studentmanagement.repository.security;

import ge.gov.tsu.studentmanagement.entity.User;
import ge.gov.tsu.studentmanagement.entity.security.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {


    @Query("SELECT e.role.name FROM #{#entityName} e where (e.userId = :userId) ")
    List<String> getUserRolesNames(@Param("userId") Long userId);

}
