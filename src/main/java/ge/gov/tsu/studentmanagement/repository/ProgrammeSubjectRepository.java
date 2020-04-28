package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.ProgrammeSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgrammeSubjectRepository extends PagingAndSortingRepository<ProgrammeSubject, Long>, JpaSpecificationExecutor<ProgrammeSubject> {

    String searchQuery = "FROM #{#entityName} e where " +
            "(:id IS NULL OR e.id = :id) AND " +
            "(:subjectId IS NULL OR e.subject.id = :subjectId ) and" +
            "(:semesterId IS NULL OR e.semester.id = :semesterId ) and" +
            "(:programmeId IS NULL OR e.programme.id = :programmeId ) " ;


    @Query(searchQuery)
    ProgrammeSubject search(
            @Param("id") Long id,
            @Param("semesterId") Long semesterId,
            @Param("subjectId") Long subjectId,
            @Param("programmeId") Long programmeId
    );

    @Query(searchQuery)
    Page<ProgrammeSubject> search(
            @Param("id") Long id,
            @Param("semesterId") Long semesterId,
            @Param("subjectId") Long subjectId,
            @Param("programmeId") Long programmeId,
            Pageable pageable
    );

    @Query("select e from ProgrammeSubject  e where " +
            " e.programme.id=:programmeId and " +
            " e.semester.id=:semesterId and " +
            " e.subject.id=:subjectId ")
    ProgrammeSubject getByProgrammeIdAndSemesterIdAndSubjectId(
            @Param("semesterId") Long semesterId,
            @Param("subjectId") Long subjectId,
            @Param("programmeId") Long programmeId
    );



    @Query("select e from ProgrammeSubject e where e.programme.id=:programmeId")
    List<ProgrammeSubject> getByProgrammeId(@Param("programmeId") Long programmeId);

}
