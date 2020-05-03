package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.Subject;
import ge.gov.tsu.studentmanagement.specification.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class SubjectSpecification {

    public static Specification<Subject> hasRecord() {
        return (root, query, cb) -> cb.isNotNull(root.get("id"));
    }

    public static Specification<Subject> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Subject> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%"+name+"%");
    }

    public static Specification<Subject> notInSubjects(List<Long> subjects) {
        return (root, query, cb) -> {
            Path<Subject> status = root.get("id");
            return status.in(subjects).not();
        };
    }


}