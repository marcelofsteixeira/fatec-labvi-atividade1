package model;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CalculoCombustivelTest {

    @Test
    public void testGastoMedio_Caso5_40(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivelImpl();
        Assert.assertEquals(8.00f, calculoCombustivel.calcularGastoMedio(5f, 40f), 0f);
    }

    @Test
    public void testGastoMedio_Caso1_1(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivelImpl();
        Assert.assertEquals(1.00f, calculoCombustivel.calcularGastoMedio(1f, 1f), 0f);
    }

    @Test
    public void testGastoMedio_CasoLista(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivelImpl();
        float resultado1 = calculoCombustivel.calcularGastoMedio(6f, 30f);
        float resultado2 = calculoCombustivel.calcularGastoMedio(25f, 100f);
        List<Float> resultados = Lists.newArrayList(resultado1, resultado2);
        Assert.assertEquals(5.00f, resultados.get(0), 0f);
        Assert.assertEquals(4.00f, resultados.get(1), 0f);
    }


}
