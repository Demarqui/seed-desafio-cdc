package com.example.validator;

import com.example.model.Autor;
import com.example.repository.AutorRepository;
import com.example.request.NovoAutorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {
    @Autowired
    private AutorRepository autorRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        NovoAutorRequest request = (NovoAutorRequest) target;
        Optional<Autor> possuiAutor = autorRepository.findByEmail(request.getEmail());
        if(possuiAutor.isPresent()){
            errors.rejectValue("email", null, "Já existe um(a) outro(a) autor(a) com o mesmo email "
                    + request.getEmail());
        }
    }
}
