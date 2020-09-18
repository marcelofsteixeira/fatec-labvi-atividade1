package model;

import org.junit.Assert;
import org.junit.Test;

public class CalculoCombustivelTest {

    @Test
    public void testGastoMedio_Caso5_40(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivel();
        Assert.assertEquals(8.00f, calculoCombustivel.calcularGastoMedio(5f, 40f), 0f);
    }

    @Test
    public void testGastoMedio_Caso1_1(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivel();
        Assert.assertEquals(1.00f, calculoCombustivel.calcularGastoMedio(1f, 1f), 0f);
    }
}
