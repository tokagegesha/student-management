package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.SemesterUniversity;
import ge.gov.tsu.studentmanagement.specification.Specification;

public class SemesterUniversitySpecification {

    public static Specification<SemesterUniversity> hasRecord() {
        return (root, query, cb) -> cb.isNotNull(root.get("id"));
    }

    public static Specification<SemesterUniversity> hasUniversityId(Long universityId) {
        return (root, query, cb) -> cb.equal(root.get("university").get("id"), universityId);
    }

    public static Specification<SemesterUniversity> visible(Boolean visible) {
        return (root, query, cb) -> cb.equal(root.get("visible"), visible);
    }

    public static Specification<SemesterUniversity> semesterVisible(Boolean semesterVisibility) {
        return (root, query, cb) -> cb.equal(root.get("semester").get("visible"), semesterVisibility);
    }

    public static Specification<SemesterUniversity> hasSemesterId(Long semesterId) {
        return (root, query, cb) -> cb.equal(root.get("semester").get("id"), semesterId);
    }

}