package com.example.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class ExistsByIdValidator implements ConstraintValidator<ExistsById, Object> {

    private String fieldName;
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void initialize(ExistsById params) {
        fieldName = params.fieldName();
        domainClass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        Query query = entityManager.createQuery("select 1 from " + domainClass.getName()
                + " where " + fieldName + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <=1, "Não existe nenhum(a) " + domainClass +
                "com o atributo " + fieldName + " = "  + value + "cadastrado");
        return !list.isEmpty();
    }
}
