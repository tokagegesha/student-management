package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Long>,JpaSpecificationExecutor<Semester> {

    /*String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id ) and" +
            "(:currentDate IS NULL OR  :currentDate BETWEEN e.beginDate and e.endDate ) and " +
            "(:visible IS NULL OR e.visible = :visible ) " + AND_E_ACTIVE_ROWS;

    @Query(searchQuery)
    Page<Semester> search(
            @Param("id") Long id,
            @Param("visible") Boolean visible,
            @Param("currentDate") Date currentDate,
            Pageable pageable
    );*/

    @Query("select e from Semester  e where e.id=:id")
    Semester getById( @Param("id") Long id);


    @Query("select e from Semester  e where e.id in (:ids) ")
    List<Semester> getByIds(@Param("ids")List<Long> semesterIds);
}

