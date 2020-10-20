package consumo_combustivel;

import com.google.gson.Gson;
import model.CalculadorViagem;
import model.CalculadorViagemImpl;
import model.Carro;

import java.util.HashMap;
import java.util.Map;

public class App {
    public String getGreeting() {
        return "Aplicacao 'Calculo Cobustivel'";
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new App().getGreeting());

        //Durante a inicializacao da aplicacao, cria objetos do tipo "Carro", e imprime quanto de combustivel falta para
        //sua viagem correspondente
        String carroJson1 = "{\"modelo\":\"Exemplo1\",\"capacidadeTanque\": 20.0,\"qtdAbastacida\": 10.0,\"gastoMedio\": 2.0 }";
        String carroJson2 = "{\"modelo\":\"Exemplo2\",\"capacidadeTanque\": 20.0,\"qtdAbastacida\": 1.0,\"gastoMedio\": 3.0 }";

        Map<String, Float> viagens = new HashMap<>();
        viagens.put(carroJson1, 50.0f);
        viagens.put(carroJson2, 30.0f);

        CalculadorViagem calculadorViagem = new CalculadorViagemImpl();

        for (String carroViagem: viagens.keySet()){
            StringBuilder stringPrintCarro = new StringBuilder();
            Carro carro = new Gson().fromJson(carroViagem, Carro.class);
            stringPrintCarro.append("Para o carro: ")
                    .append(carro.getModelo())
                    .append(" faltam ")
                    .append(calculadorViagem.calcularLitrosRestantesParaViagem(carroViagem, viagens.get(carroViagem)))
                    .append("L de combustivel para a sua viagem") ;
            System.out.println(stringPrintCarro.toString());
        }
    }
}
