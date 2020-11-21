# FATEC - LABVI - Atividade PI 1

Alunos: 
Jessica Rosado \
Jean Piero \
Marcelo Teixeira \
Rodrigo Prado 

## Video de demonstração
https://www.youtube.com/watch?v=sHAFOzvwOVg

## Arquivos da entrega:
Plano de testes e arquivo .apk do aplicativo na pasta src/main/resources.

## Instruções para execução
-Executar uma instância do servidor da aplicação 'Appium' sem qualquer configuração adicional, instruções em: http://appium.io/docs/en/about-appium/getting-started/?lang=pt .\
-Executar uma instância de emulador do sistema android através do Android Studio, utilizando uma versão de imagem do Android 9.0 ou superior.\
-Configurar no arquivo src/test/java/br/sp/gov/sp/fatec/AppTest, método 'generateAppiumDriver' (linha 102) o caminho completo do arquivo .apk do aplicativo testado (arquivo src/main/resources/Src_vaidevanSprint2.apk). Ex: "C:\\Workspace\\fatec-labvi-atividade1\\entrega_atividade_PI_1\\src\\main\\resources\\Src_vaidevanSprint2.apk".\
-Executar o comando 'mvn test' no diretorio do projeto, que executará os testes do projeto pelo gerenciador Maven.





