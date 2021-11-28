package com.kramar.Market.rest.advice;

import com.kramar.Market.goods.CakeService;
import com.kramar.Market.rest.controller.CakeController;
import com.kramar.Market.rest.dto.CakeFullInfo;
import com.kramar.Market.rest.dto.Cakes;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;

import static org.mockito.ArgumentMatchers.any;

public class CakeControllerTest {

//    @Test
//    void testCakes() {
//        Cakes cakes = new Cakes();
//        CakeService cakeService = Mockito.mock(CakeService.class);
//        Mockito.doReturn(cakes).when(cakeService).getCakes();
//
//        CakeController cakeController = new CakeController(cakeService);
//
//        Cakes cakesTest = cakeController.cakes();
//        AssertionErrors.assertEquals("", cakesTest, cakes);
//
//        Mockito.verify(cakeService, Mockito.times(1)).getCakes();
//    }
//
//    @Test
//    void testByIdCakes() {
//        CakeFullInfo cakesFullInfo = new CakeFullInfo();
//        CakeService cakeService = Mockito.mock(CakeService.class);
//        Mockito.doReturn(cakesFullInfo).when(cakeService).getCakeFullInfo(any());
//
//        CakeController cakeController = new CakeController(cakeService);
//
//        CakeFullInfo cakesTest = cakeController.cake(1L);
//        AssertionErrors.assertEquals("", cakesTest, cakesFullInfo);
//        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
//        Mockito.verify(cakeService, Mockito.times(1)).getCakeFullInfo(argumentCaptor.capture());
//        AssertionErrors.assertEquals("", argumentCaptor.getValue(), 1L);
//    }
}
