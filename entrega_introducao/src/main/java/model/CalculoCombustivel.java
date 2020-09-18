package model;

public interface CalculoCombustivel {

    public float calcularGastoMedio (float quantCombustivel, float kmRodados);

    public float calcularLitrosParaViagem(float gastoMedio, float kmTotais, float qtdAtual);

}
