package consumo_combustivel;

import com.google.common.collect.Lists;
import model.CalculoCombustivel;
import model.CalculoCombustivelImpl;
import model.Carro;

import java.util.List;

public class App {
    public String getGreeting() {
        return "Aplicacao 'Calculo Cobustivel'";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        //Durante a inicializacao da aplicacao, cria objetos do tipo "Carro", calcula seu gasto medio de combustivel
        // e imprime os resultados
        CalculoCombustivel calculoCombustivel = new CalculoCombustivelImpl();
        Carro carro1 = new Carro("Uno");
        Carro carro2 = new Carro("Corsa");
        carro1.setGastoMedio(calculoCombustivel.calcularGastoMedio( 10f, 90f));
        carro2.setGastoMedio(calculoCombustivel.calcularGastoMedio( 20f, 140f));
        List<Carro> carros = Lists.newArrayList(carro1, carro2);
        StringBuilder stringPrintCarro = null;
        for (Carro carro: carros){
            stringPrintCarro = new StringBuilder();
            stringPrintCarro.append("Carro ").append(carro.getModelo()).append(" possui gasto medio de: ")
                    .append(carro.getGastoMedio()).append(" km/l");
            System.out.println(stringPrintCarro.toString());
        }
    }
}
