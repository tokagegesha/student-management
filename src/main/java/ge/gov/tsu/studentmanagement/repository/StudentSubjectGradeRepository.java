package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.StudentSubject;
import ge.gov.tsu.studentmanagement.entity.StudentSubjectGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentSubjectGradeRepository extends JpaRepository<StudentSubjectGrade, Long> {

    @Query( "select e FROM StudentSubjectGrade e where e.studentId = :studentId and e.subjectGradeId=:gradeId ")
    List<StudentSubjectGrade> getByStudentIdAndGradeId(@Param("studentId") Long studentId,
                                                               @Param("gradeId") Long gradeId);

}
