package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.University;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class UniversitySpecification {

    public static Specification<University> hasRecord() {
        return (root, query, cb) ->  cb.isNotNull(root.get("id"));
    }

    public static Specification<University> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<University> hasCountryId(Long countryId) {
        return (root, query, cb) -> cb.equal(root.get("countryId"), countryId);
    }

    public static Specification<University> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.trim().toLowerCase() + "%");
    }


}