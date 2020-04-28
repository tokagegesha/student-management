package ge.gov.tsu.studentmanagement.repository.security;

import ge.gov.tsu.studentmanagement.entity.security.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolePermissionsRepository extends JpaRepository<RolePermission, Long> {
    @Query("FROM #{#entityName} e where e.role.id = :roleId")
    List<RolePermission> getRolePermissions(@Param("roleId") Long roleId);
}
