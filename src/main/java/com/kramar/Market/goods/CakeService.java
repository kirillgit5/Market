package com.kramar.Market.goods;

import com.kramar.Market.rest.dto.CakeFullInfo;
import com.kramar.Market.rest.dto.Cakes;

public interface CakeService {
    public Cakes getCakes();
    CakeFullInfo getCakeFullInfo(Long id);
}
