package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.Programme;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class ProgrammeSpecification {

    public static Specification<Programme> hasRecord() {
        return (root, query, cb) -> cb.isNotNull(root.get("id"));
    }

    public static Specification<Programme> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Programme> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%"+name+"%");
    }

    public static Specification<Programme> hasDegree(Long degree) {
        return (root, query, cb) -> cb.equal(root.get("degree"), degree);
    }


}