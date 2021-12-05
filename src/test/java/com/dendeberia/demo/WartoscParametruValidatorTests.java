package com.dendeberia.demo;

import com.dendeberia.demo.model.WartoscLiczbowaParametru;
import com.dendeberia.demo.model.WartoscOpisowaParametru;
import com.dendeberia.demo.model.WartoscParametru;
import com.dendeberia.demo.repository.WartoscParametruRepository;
import com.dendeberia.demo.web.WartoscParametruValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class WartoscParametruValidatorTests {

    private WartoscParametruRepository repository;
    private SimpleDateFormat dateFormat;
    private WartoscParametruValidator validator;

    @BeforeEach
    public void init() throws ParseException {
        this.dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        this.repository = Mockito.mock(WartoscParametruRepository.class);
        Mockito.when(repository.findAll()).thenReturn(generateMockData());
        validator = new WartoscParametruValidator(this.repository);
    }

    private List<WartoscParametru> generateMockData() throws ParseException {
        WartoscLiczbowaParametru wartosc1 = new WartoscLiczbowaParametru();
        wartosc1.setDataOd(this.dateFormat.parse("2021-20-10"));
        wartosc1.setDataDo(this.dateFormat.parse("2021-25-10"));

        WartoscOpisowaParametru wartosc2 = new WartoscOpisowaParametru();
        wartosc2.setDataOd(this.dateFormat.parse("2021-01-10"));
        wartosc2.setDataDo(this.dateFormat.parse("2021-05-10"));

        return Arrays.asList(wartosc1, wartosc2);
    }

    @Test
    public void timeShouldNotCollide() throws ParseException {
        WartoscLiczbowaParametru wartosc = new WartoscLiczbowaParametru();
        wartosc.setDataOd(this.dateFormat.parse("2021-10-10"));
        wartosc.setDataDo(this.dateFormat.parse("2021-15-10"));
        Assertions.assertTrue(validator.canSave(wartosc));
    }

    @Test
    public void timeShouldCollide() throws ParseException {
        WartoscLiczbowaParametru wartosc = new WartoscLiczbowaParametru();
        wartosc.setDataOd(this.dateFormat.parse("2021-19-10"));
        wartosc.setDataDo(this.dateFormat.parse("2021-21-10"));
        Assertions.assertFalse(validator.canSave(wartosc));
    }

    @Test
    public void timeShouldCollide2() throws ParseException {
        WartoscLiczbowaParametru wartosc = new WartoscLiczbowaParametru();
        wartosc.setDataOd(this.dateFormat.parse("2021-21-10"));
        wartosc.setDataDo(this.dateFormat.parse("2021-24-10"));
        Assertions.assertFalse(validator.canSave(wartosc));
    }

    @Test
    public void timeShouldCollide3() throws ParseException {
        WartoscLiczbowaParametru wartosc = new WartoscLiczbowaParametru();
        wartosc.setDataOd(this.dateFormat.parse("2021-22-10"));
        wartosc.setDataDo(this.dateFormat.parse("2021-26-10"));
        Assertions.assertFalse(validator.canSave(wartosc));
    }

    @Test
    public void timeShouldCollide4() throws ParseException {
        WartoscLiczbowaParametru wartosc = new WartoscLiczbowaParametru();
        wartosc.setDataOd(this.dateFormat.parse("2021-28-09"));
        wartosc.setDataDo(this.dateFormat.parse("2021-03-10"));
        Assertions.assertFalse(validator.canSave(wartosc));
    }

    @Test
    public void timeShouldCollide5() throws ParseException {
        WartoscLiczbowaParametru wartosc = new WartoscLiczbowaParametru();
        wartosc.setDataOd(this.dateFormat.parse("2021-02-10"));
        wartosc.setDataDo(this.dateFormat.parse("2021-04-10"));
        Assertions.assertFalse(validator.canSave(wartosc));
    }

    @Test
    public void timeShouldCollide6() throws ParseException {
        WartoscLiczbowaParametru wartosc = new WartoscLiczbowaParametru();
        wartosc.setDataOd(this.dateFormat.parse("2021-04-10"));
        wartosc.setDataDo(this.dateFormat.parse("2021-08-10"));
        Assertions.assertFalse(validator.canSave(wartosc));
    }
}
