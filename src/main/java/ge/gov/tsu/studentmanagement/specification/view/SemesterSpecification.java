package ge.gov.tsu.studentmanagement.specification.view;

import ge.gov.tsu.studentmanagement.entity.Programme;
import ge.gov.tsu.studentmanagement.entity.Semester;
import ge.gov.tsu.studentmanagement.specification.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class SemesterSpecification {

    public static Specification<Semester> hasRecord() {
        return (root, query, cb) -> cb.greaterThan(root.get("id"), 0);
    }

    public static Specification<Semester> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Semester> visible(Boolean visible) {
        return (root, query, cb) -> cb.equal(root.get("visible"), visible);
    }

    // TODO: 04-Dec-17 გასატესტია მუშაობს თუ არა
    public static Specification<Semester> hasCurrentDate(Date currentDae) {
        return (Root<Semester> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate s = cb.lessThan(root.get("beginDate"), currentDae);
            Predicate s1 = cb.greaterThanOrEqualTo(root.get("endDate"), currentDae);
            return cb.and(s, s1);
        };

    }

}