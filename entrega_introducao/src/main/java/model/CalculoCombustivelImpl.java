package model;

public class CalculoCombustivelImpl implements CalculoCombustivel{

    @Override
    public float calcularGastoMedio(float quantCombustivel, float kmRodados){
        return (kmRodados/quantCombustivel);
    }

    @Override
    public float calcularLitrosParaViagem(float gastoMedio, float kmTotais, float qtdAtual) {
        return (kmTotais/gastoMedio) - qtdAtual;
    }

}
