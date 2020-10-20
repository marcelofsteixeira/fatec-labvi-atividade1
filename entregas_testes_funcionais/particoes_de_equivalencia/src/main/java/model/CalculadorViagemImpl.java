package model;

import com.google.gson.Gson;

public class CalculadorViagemImpl implements CalculadorViagem {

    @Override
    public float calcularLitrosRestantesParaViagem(String carroJson, float distancia) throws Exception {

        Carro carro = new Gson().fromJson(carroJson, Carro.class);

        if (distancia <=0) {
            throw new Exception("Distancia não pode ter valor igual a zero ou negativo!");
        }

        float resultado = (distancia/carro.getGastoMedio()) - carro.getQtdAbastacida();

        if (resultado < 0) {
            return 0f;
        }

        return resultado;
    }

}
