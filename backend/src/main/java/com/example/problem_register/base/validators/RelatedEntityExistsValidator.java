package com.example.problem_register.base.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class RelatedEntityExistsValidator implements ConstraintValidator<RelatedEntityExists, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;

    @Override
    public void initialize(RelatedEntityExists constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value == null)
            return true;
        // Use EntityManager to check if the entity exists
        Object entity = entityManager.find(entityClass, value);

  

        return entity != null;
    }
}
