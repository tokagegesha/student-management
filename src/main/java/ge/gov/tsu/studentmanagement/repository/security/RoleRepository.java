package ge.gov.tsu.studentmanagement.repository.security;

import ge.gov.tsu.studentmanagement.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select e from Role e where e.id=:id")
    Role getById(@Param("id") Long id);

}
