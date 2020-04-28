package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.view.StudentSubjectRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentSubjectRecordRepository extends JpaRepository<StudentSubjectRecord, Long>, JpaSpecificationExecutor<StudentSubjectRecord> {
  /*  @Query("FROM #{#entityName} e WHERE (:semesterId is null or e.semesterId = :semesterId) AND (:studentId is NULL or e.studentId = :studentId)  AND (:subjectReleaseId is NULL or e.subjectReleaseId = :subjectReleaseId)")
    List<StudentSubjectRecord> search(@Param("studentId") Long studentId,
                                      @Param("semesterId") Long semesterId,
                                      @Param("subjectReleaseId") Long subjectReleaseId);*/


    @Query("FROM StudentSubjectRecord e WHERE  e.semesterId = :semesterId AND  e.studentId = :studentId")
    List<StudentSubjectRecord> getByStudentIdAndSemesterId(@Param("studentId") Long studentId,
                                      @Param("semesterId") Long semesterId);
}
