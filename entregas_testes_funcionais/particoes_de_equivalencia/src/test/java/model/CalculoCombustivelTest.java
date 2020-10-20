package model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Lista de testes criada com partes nas partições de equivalencia (e testes de valores limite) do método
// calcularLitrosRestantesParaViagem (distancia, carro) da classe CalculadorViagem:
//
// - Partição de valores invalidos = {distancia = 0, distancia < 0}
//
// - Partição de valores validos com resultado igual a 0 = {distancia > 0 e carro com quantidade de combustivel
// abastecida igual a necessaria para a viagem,
// distancia > 0 e carro com quantidade de combustivel abastecida maior que a necessaria para a viagem,}
//
// - Partição de valores validos com resultado maior a 0 = {distancia > 0 e carro com quantidade de combustivel
// abastecida menor do que a necessaria para a viagem}
public class CalculoCombustivelTest {

    @Test
    public void calculoCombustivelResultadoInvalidoCasoDistanciaZeroTest(){

        CalculadorViagem calculadorViagem =  new CalculadorViagemImpl();

        String carroJson =
                "{\"modelo\":\"teste\", \"capacidadeTanque\": 10.0, \"qtdAbastacida\": 10.0, \"gastoMedio\": 1.0 }";

        float distanciaViagem = 0f;

        Assertions.assertThrows(Exception.class, () -> {
            calculadorViagem.calcularLitrosRestantesParaViagem(carroJson, distanciaViagem);
        });
    }

    @Test
    public void calculoCombustivelResultadoInvalidoCasoDistanciaNegativaTest(){

        CalculadorViagem calculadorViagem =  new CalculadorViagemImpl();

        String carroJson =
                "{\"modelo\":\"teste\", \"capacidadeTanque\": 10.0, \"qtdAbastacida\": 10.0, \"gastoMedio\": 1.0 }";

        float distanciaViagem = -1f;

        Assertions.assertThrows(Exception.class, () -> {
            calculadorViagem.calcularLitrosRestantesParaViagem(carroJson, distanciaViagem);
        });
    }

    @Test
    public void calculoCombustivelResultazo0CasoCombustivelSuficiente() throws Exception {

        CalculadorViagem calculadorViagem =  new CalculadorViagemImpl();

        String carroJson =
                "{\"modelo\":\"teste\", \"capacidadeTanque\": 10.0, \"qtdAbastacida\": 10.0, \"gastoMedio\": 1.0 }";

        float distanciaViagem = 10f;

        float resultado = calculadorViagem.calcularLitrosRestantesParaViagem(carroJson, distanciaViagem);

        Assertions.assertEquals(0f, resultado);
    }

    @Test
    public void calculoCombustivelResultazo0CasoCombustivelMaisQueNecessario() throws Exception {

        CalculadorViagem calculadorViagem =  new CalculadorViagemImpl();

        String carroJson =
                "{\"modelo\":\"teste\", \"capacidadeTanque\": 10.0, \"qtdAbastacida\": 20.0, \"gastoMedio\": 1.0 }";

        float distanciaViagem = 10f;

        float resultado = calculadorViagem.calcularLitrosRestantesParaViagem(carroJson, distanciaViagem);

        Assertions.assertEquals(0f, resultado);
    }

    @Test
    public void calculoCombustivelResultazo0CasoCombustivelMaisQueNecessarioCasoLimite() throws Exception {

        CalculadorViagem calculadorViagem =  new CalculadorViagemImpl();

        String carroJson =
                "{\"modelo\":\"teste\", \"capacidadeTanque\": 10.0, \"qtdAbastacida\": 10.01, \"gastoMedio\": 1.0 }";

        float distanciaViagem = 10f;

        float resultado = calculadorViagem.calcularLitrosRestantesParaViagem(carroJson, distanciaViagem);

        Assertions.assertEquals(0f, resultado);
    }

    @Test
    public void calculoCombustivelResultazoMaiorQue0CasoCombustivelMenosQueNecessario() throws Exception {

        CalculadorViagem calculadorViagem =  new CalculadorViagemImpl();

        String carroJson =
                "{\"modelo\":\"teste\", \"capacidadeTanque\": 10.0, \"qtdAbastacida\": 5.0, \"gastoMedio\": 1.0 }";

        float distanciaViagem = 10f;

        float resultado = calculadorViagem.calcularLitrosRestantesParaViagem(carroJson, distanciaViagem);

        Assertions.assertEquals(5f, resultado);
    }

    @Test
    public void calculoCombustivelResultazoMaiorQue0CasoCombustivelMenosQueNecessarioCasoLimite() throws Exception {

        CalculadorViagem calculadorViagem =  new CalculadorViagemImpl();

        String carroJson =
                "{\"modelo\":\"teste\", \"capacidadeTanque\": 10.0, \"qtdAbastacida\": 9.999, \"gastoMedio\": 1.0 }";

        float distanciaViagem = 10f;

        float resultado = calculadorViagem.calcularLitrosRestantesParaViagem(carroJson, distanciaViagem);

        Assertions.assertEquals (0.001f, resultado, 0.00001f);
    }


}
