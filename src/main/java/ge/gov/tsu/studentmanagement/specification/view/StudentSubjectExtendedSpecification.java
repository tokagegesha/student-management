package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.view.StudentExtended;
import ge.gov.tsu.studentmanagement.entity.view.StudentSubjectExtended;
import ge.gov.tsu.studentmanagement.specification.Specification;

import javax.persistence.criteria.Path;
import java.util.List;

public class StudentSubjectExtendedSpecification {

    public static Specification<StudentSubjectExtended> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<StudentSubjectExtended> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<StudentSubjectExtended> hasStudentid(Long studentId) {
        return (root, query, cb) -> cb.equal(root.get("studentId"), studentId);
    }

    public static Specification<StudentSubjectExtended> hasSubjectReleaseId(Long subjectReleaseId) {
        return (root, query, cb) -> cb.equal(root.get("subjectReleaseId"), subjectReleaseId);
    }

    public static Specification<StudentSubjectExtended> hasSemesterId(Long semesterId) {
        return (root, query, cb) -> cb.equal(root.get("semesterId"), semesterId);
    }

    public static Specification<StudentSubjectExtended> hasSubjectId(Long subjectId) {
        return (root, query, cb) -> cb.equal(root.get("subjectId"), subjectId);
    }


}