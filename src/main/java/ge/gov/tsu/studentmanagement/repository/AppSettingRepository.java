package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppSettingRepository extends JpaRepository<AppSetting, Long>, JpaSpecificationExecutor<AppSetting> {

    @Query("FROM AppSetting e where  e.keyword = :keyword")
    List<AppSetting> findByKeyword(@Param("keyword") String keyword);
}
