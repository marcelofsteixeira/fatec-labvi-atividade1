package model;

import com.google.gson.Gson;

public class CalculadorViagemImpl implements CalculadorViagem {

    @Override
    public float calcularLitrosRestantesParaViagem(String carroJson, float distancia) throws Exception {
        if (distancia <=0) {
            throw new Exception("Distancia não pode ter valor igual a zero ou negativo!");
        }

        float resultado = (distancia/new Gson().fromJson(carroJson, Carro.class).getGastoMedio()) -
                new Gson().fromJson(carroJson, Carro.class).getQtdAbastacida();

        if (resultado < 0) {
            return 0f;
        }

        return resultado;
    }

}
