package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {


    /*String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:universityId IS NULL OR e.universityId = :universityId) and " +
            "(:email IS NULL OR e.email = :email)" +
            //"(:statuses is null or e.status in :statuses)" +
            AND_E_ACTION_DATE;

    @Query(searchQuery)
    Candidate search(
            @Param("id") Long id,
            @Param("universityId") Long universityId,
            @Param("email") String email,
            //@Param("statuses") List<Integer> status);

    @Query(searchQuery)
    Page<Candidate> search(
            @Param("id") Long id,
            @Param("universityId") Long universityId,
            @Param("email") String email,
            //@Param("statuses") List<Integer> status,
            ,
            Pageable pageable
    );

*/
    @Query("select e FROM Student e where" +
            "(e.id = :id) and " +
            "(:status is null or e.status = :status) ")
    Student getCandidateByIdAndStatus(
            @Param("id") Long id,
            @Param("status") Integer status);


    @Query("select e FROM Student e where" +
            "(e.id = :id) ")
    Student getCandidateById(@Param("id") Long id);


    @Query("select e FROM Student e where" +
            "(e.userId = :userId) ")
    Student getStudentByUserId(@Param("userId") Long userId);



   /* @Query( "FROM #{#entityName} e where e.abbreviation = :abbreviation OR e.name = :name" + AND_E_ACTION_DATE)
    University findDuplicates(@Param("abbreviation") String abbreviation, @Param("name") String name);*/

}
