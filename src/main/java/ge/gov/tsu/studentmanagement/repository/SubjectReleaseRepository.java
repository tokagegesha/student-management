package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.Programme;
import ge.gov.tsu.studentmanagement.entity.SubjectReleased;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectReleaseRepository extends PagingAndSortingRepository<SubjectReleased, Long>, JpaSpecificationExecutor<SubjectReleased> {


    String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:semesterId IS NULL OR e.semester.id = :semesterId) ";

    @Query(searchQuery)
    SubjectReleased search(
            @Param("id") Long id,
            @Param("semesterId") Long semesterId);

    @Query(searchQuery)
    Page<SubjectReleased> search(
            @Param("id") Long id,
            @Param("semesterId") Long semesterId,
            Pageable pageable
    );

    @Query("select e from SubjectReleased  e where e.semester.id=:semesterId and e.id not in :subjectReleaseId")
    Page<SubjectReleased> getStudentSubjectExpectAlreadyChosenSubjects(
            @Param("subjectReleaseId") List<Long> subjectReleaseId,
            @Param("semesterId") Long semesterId,
            Pageable pageable);


    @Query("select e from SubjectReleased e where e.id=:id ")
    SubjectReleased getSubjectRelease(@Param("id") Long id);

    @Modifying
    @Query("delete from SubjectReleased e where e.semester.id=:semesterId ")
    void deleteAllSubjectRelease(@Param("semesterId") Long semesterId);

}
