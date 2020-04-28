package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.SemesterUniversity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SemesterUniversityRepository extends JpaRepository<SemesterUniversity, Long>, JpaSpecificationExecutor<SemesterUniversity> {

    /*String searchQuery = "FROM #{#entityName} e where " +
            "(:universityId IS NULL OR e.universityId = :universityId ) and" +
            "(:visible IS NULL OR e.visible = :visible ) and  " +
            "(:semesterVisibility IS NULL OR e.semesterVisibility = :semesterVisibility )";

    @Query(searchQuery)
    List<SemesterUniversityExtended> search(
            @Param("universityId") Long universityId,
            @Param("visible") Boolean visible,
            @Param("semesterVisibility") Boolean semesterVisibility
    );*/

    @Query("select e from SemesterUniversity e where e.semester.id=:semesterId and e.university.id=:universityId")
    SemesterUniversity findBySemesterIdAndUniversityId(
            @Param("universityId") Long universityId,
            @Param("semesterId") Long semesterId
    );








//    String searchQuery = "FROM #{#entityName} e where " +
//            "(:universityId IS NULL OR e.universityId = :universityId ) and" +
//            "(:visible IS NULL OR e.visible = :visible ) and  " +
//            "(:semesterVisibility IS NULL OR e.semesterVisibility = :semesterVisibility )";
//
//    @Query(searchQuery)
//    List<SemesterUniversityExtended> search(
//            @Param("universityId") Long universityId,
//            @Param("visible") Boolean visible,
//            @Param("semesterVisibility") Boolean semesterVisibility
//    );
//
//    @Query("select e from SEMESTER_UNIVERSITIES_EXTENDED e where e.semesterId=:semesterId and e.universityId=:universityId")
//    SemesterUniversityExtended findBySemesterIdAndUniversityId(
//            @Param("universityId") Long universityId,
//            @Param("semesterId") Long semesterId
//    );



}
