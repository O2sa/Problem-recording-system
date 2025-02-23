package com.example.problem_register.base.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null)
        return true;
        try {
      

            String queryString = String.format(
                    "SELECT COUNT(e) FROM %s e WHERE e.%s = :value",
                    entityClass.getSimpleName(),
                    fieldName);

            Query query = entityManager.createQuery(queryString);
            query.setParameter("value", value);

            Long count = (Long) query.getSingleResult();

            return count == 0; // Valid if no duplicates exist

        } catch (Exception e) {
            throw new RuntimeException("Error during unique validation", e);
        }
    }
}
