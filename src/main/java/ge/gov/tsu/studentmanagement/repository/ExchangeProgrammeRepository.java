package ge.gov.tsu.studentmanagement.repository;

import ge.gov.tsu.studentmanagement.entity.ExchangeProgramme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExchangeProgrammeRepository extends JpaRepository<ExchangeProgramme, Long>, JpaSpecificationExecutor<ExchangeProgramme> {

    @Query("select e from ExchangeProgramme  e where e.id=:id ")
    ExchangeProgramme getById(@Param("id") Long id);

}
