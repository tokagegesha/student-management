package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.SettingValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SettingValueRepository extends JpaRepository<SettingValue, Long> {


    @Query("FROM #{#entityName} e where e.settingId = :settingId AND e.clientId IS NULL AND e.userId IS NULL" )
    SettingValue getAppSettingValue(
            @Param("settingId") Long applicationId);


    @Query("FROM #{#entityName} e where e.settingId = :settingId AND e.clientId = :clientId AND e.userId IS NULL" )
    SettingValue getClientSettingValue(
            @Param("settingId") Long applicationId,
            @Param("clientId") Long clientId);


    @Query("FROM #{#entityName} e where e.settingId = :settingId AND e.clientId = :clientId AND e.userId = :userId" )
    SettingValue getClientUserSettingValue(
            @Param("settingId") Long applicationId,
            @Param("clientId") Long clientId,
            @Param("userId") Long userId);
}