package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.Programme;
import ge.gov.tsu.studentmanagement.entity.view.ProgrammeSubjectExtended;
import ge.gov.tsu.studentmanagement.entity.view.SubjectReleaseExtended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SubjectReleaseExtendedRepository extends PagingAndSortingRepository<SubjectReleaseExtended, Long>,JpaSpecificationExecutor<SubjectReleaseExtended> {

    String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:semesterId IS NULL OR e.semesterId = :semesterId) ";

    @Query(searchQuery)
    SubjectReleaseExtended search(
            @Param("id") Long id,
            @Param("semesterId") Long semesterId);

    @Query(searchQuery)
    Page<SubjectReleaseExtended> search(
            @Param("id") Long id,
            @Param("semesterId") Long semesterId,
            Pageable pageable
    );

    @Query("select e from SUBJECT_RELEASE_EXTENDED  e where e.semesterId=:semesterId and e.id not in :subjectReleaseId")
    Page<SubjectReleaseExtended> getStudentSubjectExpectAlreadyChosenSubjects(
            @Param("subjectReleaseId") List<Long> subjectReleaseId,
            @Param("semesterId") Long semesterId,
            Pageable pageable);


}
