package org.aryan.smart_campus_platform.specification;

import org.aryan.smart_campus_platform.entity.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> collegeContains(String college) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("college"),
                        "%" + college + "%"
                )
        );
    }

    public static Specification<Student> graduationYearGreaterThan(int graduationYear) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("graduationYear"), graduationYear)
        );
    }
}
