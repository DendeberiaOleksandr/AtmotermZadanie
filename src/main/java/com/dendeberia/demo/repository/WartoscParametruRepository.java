package com.dendeberia.demo.repository;

import com.dendeberia.demo.model.WartoscParametru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WartoscParametruRepository extends JpaRepository<WartoscParametru, Long> {

    @Query(value = "SELECT * FROM WARTOSC_PARAMETRU WHERE DTYPE=?1", nativeQuery = true)
    List<WartoscParametru> findAllByDtype(String dtype);

    @Query(value = "SELECT * FROM WARTOSC_PARAMETRU AS wp WHERE wp.DATA_OD > ?1 AND wp.DATA_DO < ?2", nativeQuery = true)
    List<WartoscParametru> findAllByDataOdAnAndDataDo(Date dataOd, Date dataDo);

    @Query(value = "SELECT * FROM WARTOSC_PARAMETRU WHERE PARAMETR_ID=?1", nativeQuery = true)
    List<WartoscParametru> findAllByParametrId(Long id);

}
