package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.SubjectReleased;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class SubjectReleasedSpecification {

    public static Specification<SubjectReleased> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<SubjectReleased> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<SubjectReleased> hasSemesterId(Long semesterId) {
        return (root, query, cb) -> cb.equal(root.get("semester").get("id"), semesterId);
    }

}