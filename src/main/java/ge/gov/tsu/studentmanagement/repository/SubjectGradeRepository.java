package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.SubjectGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectGradeRepository extends JpaRepository<SubjectGrade, Long> {
    @Query("from #{#entityName} e where e.subjectReleaseId = :subjectReleaseId")
    List<SubjectGrade> getSubjectGrades(@Param("subjectReleaseId") Long subjectReleaseId);
}
