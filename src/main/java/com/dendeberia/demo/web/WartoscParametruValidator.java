package com.dendeberia.demo.web;

import com.dendeberia.demo.model.WartoscParametru;
import com.dendeberia.demo.repository.WartoscParametruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class WartoscParametruValidator {

    private WartoscParametruRepository repository;

    @Autowired
    public WartoscParametruValidator(WartoscParametruRepository repository) {
        this.repository = repository;
    }

    public boolean canSave(WartoscParametru wartoscParametru){
        List<WartoscParametru> wartosciParametru =
                this.repository.findAll();
        Date dataOd = wartoscParametru.getDataOd();
        Date dataDo = wartoscParametru.getDataDo();

        for (WartoscParametru wp: wartosciParametru){
            //Checking for time collision
            if(
                    (dataDo.after(wp.getDataOd()) && dataOd.before(wp.getDataOd())) ||
                            (dataOd.before(wp.getDataDo()) && dataDo.after(wp.getDataDo())) ||
                            (dataOd.after(wp.getDataOd()) && dataDo.before(wp.getDataDo())) ||
                            (dataOd.before(wp.getDataOd()) && dataDo.after(wp.getDataDo()))
            ){
                return false;
            }
        }
        return true;
    }
}
