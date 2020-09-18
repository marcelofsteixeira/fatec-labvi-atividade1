package model;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CalculoCombustivelTest {

    //Teste de calculo de gasto medio para o gaso de gasto de 5L com 40Km rodados (esperado: 8Km/L)
    @Test
    public void testGastoMedio_Caso5_40(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivelImpl();
        Assert.assertEquals(8.00f, calculoCombustivel.calcularGastoMedio(5f, 40f), 0f);
    }

    //Teste de calculo de gasto medio para o gaso de gasto de 1L com 0Km rodados (esperado: 0Km/L)
    @Test
    public void testGastoMedio_Caso1_0(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivelImpl();
        Assert.assertEquals(0.00f, calculoCombustivel.calcularGastoMedio(1f, 0f), 0f);
    }

    //Teste de calculo de gasto medio para uma lista de gastos (criada com a biblioteca Google Guava), com os casos de:
    //- gasto de 6L com 30Km rodados (esperado: 5Km/L)
    //- gasto de 25L com 100Km rodados (esperado: 4Km/L)
    @Test
    public void testGastoMedio_CasoLista(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivelImpl();
        float resultado1 = calculoCombustivel.calcularGastoMedio(6f, 30f);
        float resultado2 = calculoCombustivel.calcularGastoMedio(25f, 100f);
        List<Float> resultados = Lists.newArrayList(resultado1, resultado2);
        Assert.assertEquals(5.00f, resultados.get(0), 0f);
        Assert.assertEquals(4.00f, resultados.get(1), 0f);
    }

    //Teste de calculo de litros que faltam para uma viagem de 300km com 55L abastecidos, com consumo medio de 5Km/L (esperado: 5L)
    @Test
    public void testCalculoLitrosParaViagem_Caso300_55(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivelImpl();
        float gastoMedio = calculoCombustivel.calcularGastoMedio(6f, 30f);
        Assert.assertEquals(5.00f, calculoCombustivel.calcularLitrosParaViagem(gastoMedio, 300f,55), 0f);
    }

    //Teste de calculo de litros que faltam para uma viagem de 400km com 0L abastecidos, com consumo medio de 5Km/L (esperado: 80L)
    @Test
    public void testCalculoLitrosParaViagem_Caso400_0(){
        CalculoCombustivel calculoCombustivel= new CalculoCombustivelImpl();
        float gastoMedio = calculoCombustivel.calcularGastoMedio(6f, 30f);
        Assert.assertEquals(80.00f, calculoCombustivel.calcularLitrosParaViagem(gastoMedio, 400f,0), 0f);
    };


}
