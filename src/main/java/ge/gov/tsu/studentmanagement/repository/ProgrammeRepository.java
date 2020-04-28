package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgrammeRepository extends JpaRepository<Programme, Long>, JpaSpecificationExecutor<Programme> {

    /*String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:degree IS NULL OR e.degree = :degree) and " +
            "(:name IS NULL OR e.name like concat('%',:name,'%'))" +
            AND_E_ACTIVE_ROWS;

    @Query(searchQuery)
    Programme search(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("degree") Long degree);

    @Query(searchQuery)
    Page<Programme> search(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("degree") Long degree,
            Pageable pageable
    );*/

    @Query("select e from Programme e where e.id=:id ")
    Programme getProgramme(@Param("id") Long id);
}
