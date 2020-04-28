package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.view.ProgrammeReleasedExtended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgrammeUniversityRepository extends JpaRepository<ProgrammeReleasedExtended, Long> {

/*String searchQuery = "FROM #{#entityName} e where " +
        "(:id IS NULL OR e.id = :id) AND " +
        "(:universityId IS NULL OR e.universityId = :universityId) AND " +
        "(:programId IS NULL OR e.programId = :programId) AND " +
        "(e.startDate > :actionDate and e.regStartDate < :actionDate)";

    @Query(searchQuery)
    ProgrammeReleasedExtended search(
            @Param("id") Long id,
            @Param("universityId") Long universityId,
            @Param("programId") Long programId);

    @Query(searchQuery)
    Page<ProgrammeReleasedExtended> search(
            @Param("id") Long id,
            @Param("universityId") Long universityId,
            @Param("programId") Long programId,
            Pageable pageable
    );*/

   /* @Query( "FROM #{#entityName} e where e.abbreviation = :abbreviation OR e.name = :name" + AND_E_ACTION_DATE)
    University findDuplicates(@Param("abbreviation") String abbreviation, @Param("name") String name);*/

}
