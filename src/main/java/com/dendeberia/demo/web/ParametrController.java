package com.dendeberia.demo.web;

import com.dendeberia.demo.model.Parametr;
import com.dendeberia.demo.repository.ParametrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/parameters")
@Validated
public class ParametrController {
    private final ParametrRepository parametrRepository;
    private final ParameterService parameterService;

    @Autowired
    public ParametrController(ParametrRepository parametrRepository,
                              ParameterService parameterService) {
        this.parametrRepository = parametrRepository;
        this.parameterService = parameterService;
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody Parametr parametr,
                                    @PathVariable Long id){
        Optional<Parametr> parametrOptional =
                this.parametrRepository.findById(id);
        if (parametrOptional.isEmpty()){
            return ResponseEntity.badRequest().body("No parameter found by id: " + id);
        }
        Parametr parametrFromDb = parametrOptional.get();

        if (parametr.getName() != null){
            parametrFromDb.setName(parametr.getName());
        }

        if (parametr.getDescription() != null){
            parametrFromDb.setDescription(parametr.getDescription());
        }

        if (parametr.getWartoscParametruList() != null){
            parametrFromDb.setWartoscParametruList(parametr.getWartoscParametruList());
        }

        boolean saved = this.parameterService.save(parametrFromDb);

        if (saved){
            return ResponseEntity.ok("Successfully saved");
        }

        return ResponseEntity.badRequest().body("Cannot update parametr");
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Parametr parametr){
        boolean saved = this.parameterService.save(parametr);
        if (saved){
            return ResponseEntity.ok("Successfully saved");
        }
        return ResponseEntity.badRequest().body("Cannot save parametr");
    }

    @GetMapping
    public List<Parametr> getAll(){
        return this.parametrRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Parametr> getById(@PathVariable Long id){
        Optional<Parametr> parametr = this.parametrRepository.findById(id);
        if (parametr.isPresent()){
            return ResponseEntity.ok(parametr.get());
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.parametrRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        this.parametrRepository.deleteAll();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
