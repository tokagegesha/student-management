package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.SemesterUniversity;
import ge.gov.tsu.studentmanagement.entity.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UniversityRepository extends JpaRepository<University, Long>,JpaSpecificationExecutor<University> {

   /* String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:countryId IS NULL OR e.countryId = :countryId) AND " +
            "(:address IS NULL OR e.address = :address) AND " +
            "(:info IS NULL OR e.info = :info) and " +
            "(:name IS NULL OR lower(e.name) LIKE CONCAT('%',:name,'%')) " +
            AND_E_ACTIVE_ROWS;

    @Query(searchQuery)
    University search(
            @Param("id") Long id,
            @Param("countryId") Long countryId,
            @Param("address") String address,
            @Param("info") String info,
            @Param("name") String name);

    @Query(searchQuery)
    Page<University> search(
            @Param("id") Long id,
            @Param("countryId") Long countryId,
            @Param("address") String address,
            @Param("info") String info,
            @Param("name") String name,
            Pageable pageable
    );*/


    @Query("select e from University e where e.id=:id ")
    University getUniversity(@Param("id") Long id);


    @Query(value = "select e from University  e where e.name=:name")
    University getByName(@Param("name")String name);

    @Query("select s from University s where s.id not in (select su.university.id from SemesterUniversity su where su.semester.id=:semesterId )")
    Page<University> getUnactivatedUniversitiesForSemester(@Param("semesterId")Long semesterId,Pageable pageable);



}
