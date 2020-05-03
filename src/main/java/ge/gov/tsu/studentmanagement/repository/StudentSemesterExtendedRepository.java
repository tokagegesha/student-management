package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.view.StudentSemesterExtended;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentSemesterExtendedRepository extends PagingAndSortingRepository<StudentSemesterExtended, Long> {

    @Query("select e FROM STUDENT_SEMESTER_EXTENDED e where e.studentId = :studentId and e.semesterId=:semesterId")
    StudentSemesterExtended getStudentByIdAndSemesterId(@Param("studentId") Long studentId, @Param("semesterId") Long semesterId);

}
