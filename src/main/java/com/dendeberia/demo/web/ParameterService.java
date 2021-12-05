package com.dendeberia.demo.web;

import com.dendeberia.demo.model.Parametr;
import com.dendeberia.demo.model.WartoscParametru;
import com.dendeberia.demo.repository.ParametrRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {
    private final ParametrRepository repository;
    private final WartoscParametruValidator validator;

    public ParameterService(ParametrRepository repository,
                            WartoscParametruValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public boolean save(Parametr parametr){
        if (parametr != null){
            List<WartoscParametru> wartoscParametruList = parametr.getWartoscParametruList();

            if (wartoscParametruList != null){
                for (WartoscParametru wp: wartoscParametruList){
                    if (wp != null){
                        boolean canSave = validator.canSave(wp);

                        if (!canSave){
                            return false;
                        }
                    }
                }
            }
            this.repository.save(parametr);
            return true;
        }
        return false;
    }
}
