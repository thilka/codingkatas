package de.hilka.potter;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;


public class PriceCalculatorTest {

    private PriceCalculator m_priceCalculator = new PriceCalculator();
    
    @Test
    public void nothingToPayIfNothingIsBought() throws Exception {
        assertEquals(0.0d, m_priceCalculator.calculatePrice(0, 0, 0, 0, 0), 0.0);
    }

}
