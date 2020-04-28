package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.view.StudentExtended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentExtendedRepository extends JpaRepository<StudentExtended, Long>, JpaSpecificationExecutor<StudentExtended> {


  /*  String searchQuery = "FROM #{#entityName} e where " +

            "(:searchString IS NULL OR concat(e.firstName, e.lastName, e.firstName, e.email) like :searchString) AND " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:userId IS NULL OR e.userId = :userId) and " +
            "(:universityId IS NULL OR e.universityId = :universityId) and " +
            "(:email IS NULL OR e.email = :email) and" +
            "(e.status in :statuses)";

    @Query(searchQuery)
    StudentExtended search(
            @Param("id") Long id,
            @Param("universityId") Long universityId,
            @Param("userId") Long userId,
            @Param("email") String email,
            @Param("statuses") List<Integer> statuses,
            @Param("searchString") String searchString
    );

    @Query(searchQuery)
    Page<StudentExtended> search(
            @Param("id") Long id,
            @Param("universityId") Long universityId,
            @Param("userId") Long userId,
            @Param("email") String email,
            @Param("statuses") List<Integer> statuses,
            @Param("searchString") String searchString,
            Pageable pageable
    );*/


    @Query("select e from STUDENT_EXTENDED e where e.userId=:userId")
    StudentExtended getStudentByUserId(
            @Param("userId") Long userId
    );


/*
    @Query("FROM #{#entityName} e where" +
            "(e.id = :id) and " +
            "(e.status = :status) " +
            AND_E_ACTION_DATE)
    Candidate getCandidateByIdAndStatus(
            @Param("id") Long id,
            @Param("status") Integer status);
*/



   /* @Query( "FROM #{#entityName} e where e.abbreviation = :abbreviation OR e.name = :name" + AND_E_ACTION_DATE)
    University findDuplicates(@Param("abbreviation") String abbreviation, @Param("name") String name);*/

}
