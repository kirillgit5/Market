package com.kramar.Market.rest.controller;

import com.kramar.Market.dto.Cake;
import com.kramar.Market.dto.Cakes;
import com.kramar.Market.exception.CakeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@Validated
//@RequestMapping("/v1/cakes")
public class CakeController {
    private Cakes cakesList = new Cakes();

    public  CakeController() {
        Cakes cakes = new Cakes();
        Cake cake1 = new Cake();
        Cake cake2 = new Cake();

        cake1.setName("Napoleon");
        cake1.setPrice(new BigDecimal(124));
        cake1.setCalories(new BigDecimal(141));
        cake1.setWeight(new BigDecimal(414));
        cake1.setImage("tort.jpg");
        cake1.setId(1L);

        cake2.setName("Med");
        cake2.setPrice(new BigDecimal(313));
        cake2.setCalories(new BigDecimal(133));
        cake2.setWeight(new BigDecimal(546));
        cake2.setId(2L);

        cake2.setImage("tort.jpg");

        ArrayList<Cake> arrCake = new ArrayList<Cake>();
        arrCake.add(cake1);
        arrCake.add(cake2);

        cakesList.setCakesList(arrCake);
    }

    @GetMapping(value = "cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes() {
        return  cakesList;
    }

    @GetMapping(value = "cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cake cake(@PathVariable Long id) {
        return cakesList.getCakesList().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new CakeNotFoundException("Cake not found"));
    }

    @PostMapping(path = "createCake", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> createCake(@RequestBody Cake newCake) {
        if (newCake.isValid()) {
            cakesList.getCakesList().add(newCake);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
