package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.view.StudentSubjectRecord;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class StudentSubjectRecordSpecification {

    public static Specification<StudentSubjectRecord> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }


    public static Specification<StudentSubjectRecord> hasStudentid(Long studentId) {
        return (root, query, cb) -> cb.equal(root.get("studentId"), studentId);
    }

    public static Specification<StudentSubjectRecord> hasSubjectReleaseId(Long subjectReleaseId) {
        return (root, query, cb) -> cb.equal(root.get("subjectReleaseId"), subjectReleaseId);
    }

    public static Specification<StudentSubjectRecord> hasSemesterId(Long semesterId) {
        return (root, query, cb) -> cb.equal(root.get("semesterId"), semesterId);
    }


}