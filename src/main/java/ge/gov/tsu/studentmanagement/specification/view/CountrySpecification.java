package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.statics.Country;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class CountrySpecification {

    public static Specification<Country> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<Country> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }


}