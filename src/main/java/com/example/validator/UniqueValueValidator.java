package com.example.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> clazz;
    @PersistenceContext
    private EntityManager manager;
    @Override
    public void initialize(UniqueValue params) {
       domainAttribute = params.fieldName();
       clazz = params.domainClass();
    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + clazz.getName() +
                " where " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <=1, "Foi encontrado mais de um " + clazz +
                "com o atributo " + domainAttribute + " = "  + value + "cadastrado");
        return list.isEmpty();
    }
}
