package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.view.ProgrammeSubjectExtended;
import ge.gov.tsu.studentmanagement.entity.view.StudentSubjectExtended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgrammeSubjectExtendedRepository extends JpaRepository<ProgrammeSubjectExtended, String>, JpaSpecificationExecutor<ProgrammeSubjectExtended> {

}
