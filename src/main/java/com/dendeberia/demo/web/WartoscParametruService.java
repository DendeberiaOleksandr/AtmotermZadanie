package com.dendeberia.demo.web;

import com.dendeberia.demo.model.WartoscParametru;
import com.dendeberia.demo.repository.WartoscParametruRepository;
import org.springframework.stereotype.Service;

@Service
public class WartoscParametruService {
    private final WartoscParametruRepository repository;
    private final WartoscParametruValidator validator;

    public WartoscParametruService(WartoscParametruRepository repository,
                                   WartoscParametruValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public boolean save(WartoscParametru wartoscParametru){
        if (validator.canSave(wartoscParametru)){
            this.repository.save(wartoscParametru);
            return true;
        }
        return false;
    }
}
