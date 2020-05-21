package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long>,JpaSpecificationExecutor<Subject>{

    String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:name IS NULL OR lower(e.name) LIKE CONCAT('%',:name,'%')) ";

    @Query(searchQuery)
    Subject search(
            @Param("id") Long id,
            @Param("name") String name);

    @Query(searchQuery)
    Page<Subject> search(
            @Param("id") Long id,
            @Param("name") String name,
            Pageable pageable
    );

    @Query("select e from Subject e where e.id=:id ")
    Subject getSubject(@Param("id") Long id);

    @Query("select e from Subject e where " +
            "(:name IS NULL OR lower(e.name) LIKE CONCAT('%',:name,'%')) and " +
            " e.id not in :subjects")
    Page<Subject> getSubjectExpectSomeSubject(@Param("subjects") List<Long> subjects, @Param("name") String name, Pageable pageable);


    @Query(value = " SELECT " +
            " e.*" +
            " FROM SUBJECTS e " +
            " LEFT JOIN SUBJECTS_RELEASED sb ON sb.SUBJECT_ID = e.ID AND sb.SEMESTER_ID =:semesterId and sb.ROW_ID is null " +
            " WHERE e.ROW_ID IS NULL AND sb.ID IS NULL "+
            "  ORDER BY e.ID  DESC /*#pageable*/  "
            , countQuery = "SELECT COUNT(*)" +
            " FROM SUBJECTS e " +
            " LEFT JOIN SUBJECTS_RELEASED sb ON sb.SUBJECT_ID = e.ID AND sb.SEMESTER_ID =:semesterId and sb.ROW_ID is null " +
            " WHERE e.ROW_ID IS NULL AND sb.ID IS NULL"
            , nativeQuery = true)
    Page<Subject> getSubjectExpectReleasedData(@Param("semesterId") Long semesterId, Pageable pageable);





    @Query("select s from Subject s left join s.subjectReleaseds sr on sr.semester.id=:semesterId where sr.subject.id is null")
    List<Subject> getAllUnreleasedSubjects(@Param("semesterId") Long semesterId);

}
