package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.Subject;
import ge.gov.tsu.studentmanagement.entity.view.StudentSubjectExtended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentSubjectExtendedRepository extends PagingAndSortingRepository<StudentSubjectExtended, Long>, JpaSpecificationExecutor<StudentSubjectExtended> {

    /*String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:studentId IS NULL OR e.studentId = :studentId) AND " +
            "(:subjectReleaseId IS NULL OR e.subjectReleaseId = :subjectReleaseId) AND " +
            "(:subjectId IS NULL OR e.subjectId = :subjectId) ";

    @Query(searchQuery)
    StudentSubjectExtended search(
            @Param("id") Long id,
            @Param("studentId") Long studentId,
            @Param("subjectReleaseId") Long subjectReleaseId,
            @Param("subjectId") Long subjectId);

    @Query(searchQuery)
    Page<StudentSubjectExtended> search(
            @Param("id") Long id,
            @Param("studentId") Long studentId,
            @Param("subjectReleaseId") Long subjectReleaseId,
            @Param("subjectId") Long subjectId,
            Pageable pageable);*/

}
