package model;

public class Carro {

    private String modelo;
    private float capacidadeTanque;
    private float qtdAbastacida;
    private float gastoMedio;

    public Carro(String modelo) {
        this.modelo = modelo;
    }

    public float getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public void setCapacidadeTanque(float capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }

    public float getQtdAbastacida() {
        return qtdAbastacida;
    }

    public void setQtdAbastacida(float qtdAbastacida) {
        this.qtdAbastacida = qtdAbastacida;
    }

    public float getGastoMedio() {
        return gastoMedio;
    }

    public void setGastoMedio(float gastoMedio) {
        this.gastoMedio = gastoMedio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
