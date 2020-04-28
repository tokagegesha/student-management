package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.view.ProgrammeSubjectExtended;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class ProgrammeSubjectExtendedSpecification {

    public static Specification<ProgrammeSubjectExtended> hasRecord() {
        return (root, query, cb) -> cb.isNotNull(root.get("id"));
    }

    public static Specification<ProgrammeSubjectExtended> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<ProgrammeSubjectExtended> hasSubjectId(Long subjectId) {
        return (root, query, cb) -> cb.equal(root.get("subjectId"), subjectId);
    }

    public static Specification<ProgrammeSubjectExtended> hasSemesterId(Long semesterId) {
        return (root, query, cb) -> cb.equal(root.get("semesterId"), semesterId);
    }

    public static Specification<ProgrammeSubjectExtended> hasSemesterYear(Integer semesterYear) {
        return (root, query, cb) -> cb.equal(root.get("semesterYear"), semesterYear);
    }

    public static Specification<ProgrammeSubjectExtended> hasProgrammeId(Long programmeId) {
        return (root, query, cb) -> cb.equal(root.get("programmeId"), programmeId);
    }


}