package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.Programme;
import ge.gov.tsu.studentmanagement.entity.SubjectReleased;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectReleaseRepository extends JpaRepository<SubjectReleased, Long> {
    @Query("select e from SubjectReleased e where e.id=:id ")
    SubjectReleased getSubjectRelease(@Param("id") Long id);

}
