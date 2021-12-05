package com.dendeberia.demo.web;

import com.dendeberia.demo.model.WartoscLiczbowaParametru;
import com.dendeberia.demo.model.WartoscOpisowaParametru;
import com.dendeberia.demo.model.WartoscParametru;
import com.dendeberia.demo.repository.WartoscParametruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/wartosciParametru")
@Validated
public class WartoscParametruController {

    private final WartoscParametruRepository wartoscParametruRepository;
    private final WartoscParametruService wartoscParametruService;

    @Autowired
    public WartoscParametruController(WartoscParametruRepository wartoscParametruRepository,
                                      WartoscParametruService wartoscParametruService) {
        this.wartoscParametruRepository = wartoscParametruRepository;
        this.wartoscParametruService = wartoscParametruService;
    }

    @GetMapping
    public List<WartoscParametru> getAll(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-dd-MM") Date dataOd,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-dd-MM") Date dataDo,
            @RequestParam(required = false) Long parametrId){
        if(type != null){
            return this.wartoscParametruRepository.findAllByDtype(type);
        } else if (dataOd != null && dataDo != null){
            return this.wartoscParametruRepository.findAllByDataOdAnAndDataDo(dataOd, dataDo);
        } else if (parametrId != null){
            return this.wartoscParametruRepository.findAllByParametrId(parametrId);
        }
        return this.wartoscParametruRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody WartoscParametru wartoscParametru){
        boolean saved = this.wartoscParametruService.save(wartoscParametru);
        if (saved){
            return ResponseEntity.ok("Successfully saved!");
        }
        return ResponseEntity.badRequest().body("Cannot save wartoscParametru cause of time collision");
    }

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody WartoscParametru wartoscParametruRequest,
                                    @PathVariable Long id){
        Optional<WartoscParametru> wartoscParametruOptional =
                this.wartoscParametruRepository.findById(id);
        if (wartoscParametruOptional.isPresent()){

            WartoscParametru wartoscParametruDb = wartoscParametruOptional.get();

            if (wartoscParametruRequest.getDataOd() != null){
                wartoscParametruDb.setDataOd(wartoscParametruRequest.getDataOd());
            }

            if (wartoscParametruRequest.getDataDo() != null){
                wartoscParametruDb.setDataDo(wartoscParametruRequest.getDataDo());
            }

            if (wartoscParametruRequest.getId() != null){
                wartoscParametruDb.setId(wartoscParametruRequest.getId());
            }

            if (wartoscParametruRequest instanceof WartoscLiczbowaParametru &&
                wartoscParametruDb instanceof WartoscLiczbowaParametru){

                if (((WartoscLiczbowaParametru) wartoscParametruRequest).getJednostka() != null){
                    ((WartoscLiczbowaParametru) wartoscParametruDb)
                            .setJednostka(((WartoscLiczbowaParametru) wartoscParametruRequest).getJednostka());
                }

                if (((WartoscLiczbowaParametru) wartoscParametruRequest).getWartosc() != null){
                    ((WartoscLiczbowaParametru) wartoscParametruDb)
                            .setWartosc(((WartoscLiczbowaParametru) wartoscParametruRequest).getWartosc());
                }

            } else if (wartoscParametruRequest instanceof WartoscOpisowaParametru &&
                        wartoscParametruDb instanceof WartoscOpisowaParametru){
                if (((WartoscOpisowaParametru) wartoscParametruRequest).getWartosc() != null){
                    ((WartoscOpisowaParametru)wartoscParametruDb)
                            .setWartosc(((WartoscOpisowaParametru) wartoscParametruRequest).getWartosc());
                }
            }
            boolean saved = this.wartoscParametruService.save(wartoscParametruDb);

            if (saved){
                return ResponseEntity.ok("Successfully updated");
            }
        }
        return ResponseEntity.badRequest().body("Cannot update");
    }

    @DeleteMapping
    public void deleteAll(){
        this.wartoscParametruRepository.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAll(@PathVariable Long id){
        this.wartoscParametruRepository.deleteById(id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
