package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.view.SubjectReleaseExtended;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class SubjectReleaseExtendedSpecification {

    public static Specification<SubjectReleaseExtended> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<SubjectReleaseExtended> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<SubjectReleaseExtended> hasSemesterId(Long semesterId) {
        return (root, query, cb) -> cb.equal(root.get("semesterId"), semesterId);
    }

}