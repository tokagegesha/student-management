package ge.gov.tsu.studentmanagement.repository.security;

import ge.gov.tsu.studentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from #{#entityName} e " +
            "where e.email = :email")
    User getUser(@Param("email") String email);

    @Query("from #{#entityName} e " +
            "where e.id = :id")
    User getUser(@Param("id") Long id);

}
