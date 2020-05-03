package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.Student;
import ge.gov.tsu.studentmanagement.entity.view.StudentSemesterExtended;
import ge.gov.tsu.studentmanagement.specification.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.*;
import java.util.List;

public class StudentSpecification {

    public static Specification<Student> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<Student> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Student> hasUniversityId(Long universityId) {
        return (root, query, cb) -> cb.equal(root.get("university").get("id"), universityId);
    }

    public static Specification<Student> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public static Specification<Student> hasUserId(Long userId) {
        return (root, query, cb) -> cb.equal(root.get("userId"), userId);
    }

    public static Specification<Student> hasStatuses(List<Integer> statuses) {
        return (root, query, cb) -> {
            Path<Object> status = root.get("status");
            return status.in(statuses);
        };
    }

    public static Specification<Student> hasSearchString(String searchString) {
        return (root, query, cb) ->
                cb.or(
                cb.like(root.get("firstName"), "%" + searchString + "%"),
                cb.like(root.get("georgianFirstName"), "%" + searchString + "%"),
                cb.like(root.get("georgianLastName"), "%" + searchString + "%"),
                cb.like(root.get("citizenship"), "%" + searchString + "%"),
                cb.like(root.get("countryOfResidence"), "%" + searchString + "%"),
                cb.like(root.get("passportNumber"), "%" + searchString + "%"),
                cb.like(root.get("address"), "%" + searchString + "%"),
                cb.like(root.get("city"), "%" + searchString + "%"),
                cb.like(root.get("country"), "%" + searchString + "%"),
                cb.like(root.get("email"), "%" + searchString + "%"),
                cb.like(root.get("phone"), "%" + searchString + "%"),
                cb.like(root.get("email"), "%" + searchString + "%"),
                cb.like(cb.lower(root.get("university").get("name")), "%" + searchString + "%"),
                cb.like(root.get("lastName"), "%" + searchString + "%"));

    }


    public static Specification<Student> hasSemesterIds(List<Long> semesterIds) {
        return (root, query, cb) -> {
            Join join = root.join("semesters");
            query.distinct(true);
            return join.get("id").in(semesterIds);
        };
    }

}