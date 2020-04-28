package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.StudentSemester;
import ge.gov.tsu.studentmanagement.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentSemestersRepository extends JpaRepository<StudentSemester, Long> {

/*

    @Query("select e FROM Candidate e where" +
            "(e.id = :id) " +
            AND_E_ACTIVE_ROWS)
    Candidate getCandidateById(@Param("id") Long id);
*/


    @Query("select e FROM StudentSemester e where e.studentId = :studentId")
    List<StudentSemester> getByStudentId(@Param("studentId") Long studentId);

}
