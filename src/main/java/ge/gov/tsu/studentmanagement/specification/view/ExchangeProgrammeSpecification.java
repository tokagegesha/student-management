package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.ExchangeProgramme;
import ge.gov.tsu.studentmanagement.entity.statics.Country;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class ExchangeProgrammeSpecification {

    public static Specification<ExchangeProgramme> hasRecord() {
        return (root, query, cb) -> cb.isNotNull(root.get("id"));
    }

    public static Specification<ExchangeProgramme> nameLike(String searchQuery) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + searchQuery + "%");
    }


}