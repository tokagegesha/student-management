package ge.gov.tsu.studentmanagement.repository.security;

import ge.gov.tsu.studentmanagement.entity.University;
import ge.gov.tsu.studentmanagement.entity.statics.Country;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long>, JpaSpecificationExecutor<Country> {

   /* String searchQuery = "FROM Country e where " +
            "(:id IS NULL OR e.id = :id) ";

    @Query(searchQuery)
    Country search(
            @Param("id") Long id);

    @Query(searchQuery)
    Page<Country> search(
            @Param("id") Long id,
            Pageable pageable
    );*/



}
