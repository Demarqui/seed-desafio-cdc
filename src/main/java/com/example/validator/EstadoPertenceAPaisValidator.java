package com.example.validator;

import com.example.model.Estado;
import com.example.model.Pais;
import com.example.request.NovaCompraParcialRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraParcialRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) return;
        NovaCompraParcialRequest request = (NovaCompraParcialRequest) target;

        Pais pais = entityManager.find(Pais.class, request.getIdPais());
        Estado estado = entityManager.find(Estado.class, request.getIdEstado());

        if(!estado.pertenceAPais(pais)){
            errors.rejectValue("idEstado", null, "este estado não é do país " +
                    "selecionado");
        }
    }
}
