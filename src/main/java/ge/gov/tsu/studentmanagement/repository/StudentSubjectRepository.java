package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {


   @Query( "select e FROM StudentSubject e where e.studentId = :studentId and e.subjectReleaseId = :subjectReleaseId")
   StudentSubject findByStudentIdAndSubjectId(@Param("studentId") Long studentId,
                                              @Param("subjectReleaseId") Long subjectReleaseId);


   @Query( "select e FROM StudentSubject e where e.studentId = :studentId " )
   List<StudentSubject> findByStudentId(@Param("studentId") Long studentId);
}
