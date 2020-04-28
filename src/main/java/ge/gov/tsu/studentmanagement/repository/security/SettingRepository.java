package ge.gov.tsu.studentmanagement.repository.security;

import ge.gov.tsu.studentmanagement.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SettingRepository extends JpaRepository<Setting, Long> {

    @Query("FROM Setting e where e.name = :name")
    Setting search(@Param("name") String name);
}