package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.University;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class UniversityExtendedSpecification {

    public static Specification<University> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<University> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<University> hasCountryId(Long countryId) {
        return (root, query, cb) -> cb.equal(root.get("country").get("id"), countryId);
    }

    public static Specification<University> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name.trim() + "%");
    }


   /* public static Specification<VolunteerExtended> inCategories(List<Long> ids) {
        return (root, query, cb) -> {
            Expression<VolunteerExtended> exp = root.get("categoryId");
            return exp.in(ids);
        };
    }*/

}