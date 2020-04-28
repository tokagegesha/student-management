package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.ProgrammeSubject;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class ProgrammeSubjectSpecification {

    public static Specification<ProgrammeSubject> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<ProgrammeSubject> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<ProgrammeSubject> hasSubjectId(Long subjectId) {
        return (root, query, cb) -> cb.equal(root.get("subject").get("id"), subjectId);
    }

    public static Specification<ProgrammeSubject> hasSemesterId(Long semesterId) {
        return (root, query, cb) -> cb.equal(root.get("semester").get("id"), semesterId);
    }

    public static Specification<ProgrammeSubject> hasProgrammeId(Long programmeId) {
        return (root, query, cb) -> cb.equal(root.get("programme").get("id"), programmeId);
    }


}