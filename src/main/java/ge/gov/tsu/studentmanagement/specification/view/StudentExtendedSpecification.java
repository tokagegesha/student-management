package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.view.StudentExtended;
import ge.gov.tsu.studentmanagement.entity.view.StudentSemesterExtended;
import ge.gov.tsu.studentmanagement.specification.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class StudentExtendedSpecification {

    public static Specification<StudentExtended> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<StudentExtended> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<StudentExtended> hasUniversityId(Long universityId) {
        return (root, query, cb) -> cb.equal(root.get("universityId"), universityId);
    }

    public static Specification<StudentExtended> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public static Specification<StudentExtended> hasUserId(Long userId) {
        return (root, query, cb) -> cb.equal(root.get("userId"), userId);
    }

    public static Specification<StudentExtended> hasStatuses(List<Integer> statuses) {
        return (root, query, cb) -> {
            Path<Object> status = root.get("status");
            return status.in(statuses);
        };
    }

    public static Specification<StudentExtended> hasSearchString(String searchString) {
        return (root, query, cb) -> cb.like(root.get("searchString"), "%" + searchString + "%");
    }

    public static Specification<StudentExtended> hasSemesterIds(List<Long> semesterIds) {
        return (Root<StudentExtended> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Join<StudentExtended, StudentSemesterExtended> join = root.join("semesters", JoinType.LEFT);
            query.groupBy(
                    root.get("id"),
                    root.get("registrationDate"),
                    root.get("universityId"),
                    root.get("firstName"),
                    root.get("lastName"),
                    root.get("gender"),
                    root.get("citizenship"),
                    root.get("countryOfResidence"),
                    root.get("birthDate"),
                    root.get("passportNumber"),
                    root.get("address"),
                    root.get("city"),
                    root.get("country"),
                    root.get("email"),
                    root.get("phone"),
                    root.get("contactPersonName"),
                    root.get("contactPersonAddress"),
                    root.get("contactPersonPhone"),
                    root.get("contactPersonRelationship"),
                    root.get("status"),
                    root.get("letterOfNominationPath"),
                    root.get("cvPath"),
                    root.get("diplomaPath"),
                    root.get("learningAgreementPath"),
                    root.get("universityRecordPath"),
                    root.get("picturePath"),
                    root.get("passportPath"),
                    root.get("proofOfEnglishSkillPath"),
                    root.get("healthInsurancePath"),
                    root.get("universityName"),
                    root.get("georgianFirstName"),
                    root.get("georgianLastName"),
                    root.get("uniGeorgianName"),
                    root.get("uniCountryGeorgianName"),
                    root.get("userId"));
            return join.get("semesterId").in(semesterIds);
        };
    }

}