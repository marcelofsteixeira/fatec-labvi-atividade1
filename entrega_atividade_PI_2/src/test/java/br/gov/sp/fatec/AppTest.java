package br.gov.sp.fatec;

import com.google.common.annotations.VisibleForTesting;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


public class AppTest 
{
    private AppiumDriver appiumDriver;

    @Before
    public void initiateDriver() throws MalformedURLException, InterruptedException {
        this.appiumDriver = generateAppiumDriver();
        Thread.sleep(8000);
    }

    @After
    public void closeApp() {
        this.appiumDriver.closeApp();
    }

    @Test
    public void loginCorrectUserPasswordTest () throws MalformedURLException, InterruptedException {
        verifyCurrentScreenLogin();
        doLogin("50", "123");
        verifyCurrentScreenHomeDriver();
    }
    @Test
    public void loginIncorrectUserPasswordTest () throws MalformedURLException, InterruptedException {
        verifyCurrentScreenLogin();
        doLogin("11122233344", "senha");
        verifyCurrentScreenLogin();
    }


    @Test
    public void loginEmptyCPFTest() throws MalformedURLException, InterruptedException {
        verifyCurrentScreenLogin();
        doLogin("", "123");
        verifyCurrentScreenLogin();
    }

    @Test
    public void loginEmptyPasswordTest  () throws MalformedURLException, InterruptedException {
        verifyCurrentScreenLogin();
        doLogin("50", "123");
        verifyCurrentScreenLogin();
    }

    @Test
    public void createUserDriverCorrectTest () throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312344", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("12312312344", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
    }

    @Test
    public void createUserPassengerCorrectTest () throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "123123125", "12999999888", "Avenida Andrômeda",
                "55", "Jardim Satélite", "senhaforte", "senhaforte", false);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("123123125", "senhaforte");
        assertTrue(verifyCurrentScreenHomePassenger());
    }

    @Test
    public void createUserDriverAlreadyExistsTest() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312111", "12999999111", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312111", "12999999111", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenRegister());
    }

    @Test
    public void createUserPassagerAlreadyExistsTest () throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312666", "1299999666", "Avenida Andrômeda",
                "55", "Jardim Satélite", "senhaforte", "senhaforte", false);
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312666", "1299999666", "Avenida Andrômeda",
                "55", "Jardim Satélite", "senhaforte", "senhaforte", false);
        assertTrue(verifyCurrentScreenRegister());
    }

    @Test
    public void createUserDriverPasswordDontMatchTest() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312300", "12999999444", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senha", true);
        assertTrue(verifyCurrentScreenRegister());
    }

    @Test
    public void createUserPassagerPasswordDontMatchTest () throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312333", "1299999777", "Avenida Andrômeda",
                "55", "Jardim Satélite", "senhaforte", "senha", false);
        assertTrue(verifyCurrentScreenRegister());
    }

    @Test
    public void createUserDriverEmptyIncorrectTest () throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister(null, null, null, null, null, null, null, null, true);
        assertTrue(verifyCurrentScreenRegister());
    }



    @Test
    public void createUserPassengerEmptyIncorrectTest () throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister(null, null, null, null, null, null, null, null, false);
        assertTrue(verifyCurrentScreenRegister());
    }

    @Test
    public void listPassengerTest() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312349", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("12312312349", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
    }

    @Test
    public void showDepartureAddress1Test() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312350", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("12312312350", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
        clickFirstPassengerInList();
        assertTrue(verifyCurrentScreenPassengerDetail());
        clickDetail(0);
        assertTrue(verifyCurrentScreenAddressDetail());
    }

    @Test
    public void showDepartureAddress2Test() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312351", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("12312312351", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
        clickFirstPassengerInList();
        assertTrue(verifyCurrentScreenPassengerDetail());
        clickDetail(1);
        assertTrue(verifyCurrentScreenAddressDetail());
    }

    @Test
    public void showEndereçoDesembarque1() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312352", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("12312312352", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
        clickFirstPassengerInList();
        assertTrue(verifyCurrentScreenPassengerDetail());
        clickDetail(2);
        assertTrue(verifyCurrentScreenAddressDetail());
    }

    @Test
    public void showEndereçoDesembarque2() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312353", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("12312312353", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
        clickFirstPassengerInList();
        assertTrue(verifyCurrentScreenPassengerDetail());
        clickDetail(3);
        assertTrue(verifyCurrentScreenAddressDetail());
    }

    @Test
    public void horaPrevistaSetTest() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312354", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("123123123534", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
        clickFirstPassengerInList();
        assertTrue(verifyCurrentScreenPassengerDetail());
        clickDetail(0);
        assertTrue(verifyCurrentScreenAddressDetail());
        clickHoraPrevista();
        fillTime("2", "25");
        assertTrue(verifyCurrentScreenAddressDetail());
        assertTrue(verifyTextoTime("2:25:00 AM"));
    }

    @Test
    public void horaPrevistaDeleteTest() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312355", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("123123123535", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
        clickFirstPassengerInList();
        assertTrue(verifyCurrentScreenPassengerDetail());
        clickDetail(0);
        assertTrue(verifyCurrentScreenAddressDetail());
        clickHoraPrevista();
        fillTime("2", "30");
        assertTrue(verifyCurrentScreenAddressDetail());
        assertTrue(verifyTextoTime("2:30:00 AM"));
        clickExcluirHora();
        assertFalse(verifyTextoTime("2:30:00 AM"));
    }

    @Test
    public void alteraDadosEnderecoTest() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312356", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("12312312356", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
        clickFirstPassengerInList();
        assertTrue(verifyCurrentScreenPassengerDetail());
        clickDetail(0);
        assertTrue(verifyCurrentScreenAddressDetail());
        clickAlterarEndereco();
        fillEndereco("Av. Pres. Juscelino kubitschek", "1005", "Vila Industrial");
        assertTrue(verifyTextoEnderecos("Av. Pres. Juscelino kubitschek", "1005", "Vila Industrial"));
    }

    @Test
    public void alteraDadosEmptyEnderecoFailTest() throws InterruptedException {
        assertTrue(verifyCurrentScreenLogin());
        clickRegister();
        assertTrue(verifyCurrentScreenRegister());
        fillRegister("Maria", "12312312357", "12999999999", "Avenida Marechal Castelo Branco",
                "55", "Jardim Bela Vista", "senhaforte", "senhaforte", true);
        assertTrue(verifyCurrentScreenLogin());
        doLogin("12312312357", "senhaforte");
        assertTrue(verifyCurrentScreenHomeDriver());
        clickList();
        assertTrue(verifyCurrentScreenPassengerList());
        clickFirstPassengerInList();
        assertTrue(verifyCurrentScreenPassengerDetail());
        clickDetail(0);
        assertTrue(verifyCurrentScreenAddressDetail());
        clickAlterarEndereco();
        fillEndereco("", "", "");
        assertTrue(verifyTextoEnderecosNotNull());
    }

    private void doLogin(String cpf, String senha) throws InterruptedException {
        List<MobileElement> inputsLogin = appiumDriver.findElementsByClassName("android.widget.EditText");
        MobileElement inputCPF = inputsLogin.get(0);
        MobileElement inputSenha = inputsLogin.get(1);
        inputCPF.setValue(cpf);
        inputSenha.setValue(senha);
        clickLogin();

        Thread.sleep(2000);
    }

    private boolean verifyCurrentScreenLogin() {
        List elementosTextos = appiumDriver.findElementsByClassName("android.widget.TextView");
        List elementosTextoCpf = appiumDriver.findElementsByXPath("//android.widget.TextView[@text='CPF']");
        List elementosTextoSenha = appiumDriver.findElementsByXPath("//android.widget.TextView[@text='SENHA']");

        return elementosTextos.size() == 3 && elementosTextoCpf.size() == 1 && elementosTextoSenha.size() == 1;
    }

    private boolean verifyCurrentScreenRegister() {
        List elementosTextos = appiumDriver.findElementsByClassName("android.widget.TextView");
        List elementosBotaoSalvar = appiumDriver.findElementsByXPath("//android.widget.Button[@text='salvar']");
        List elementosBotaoVoltar = appiumDriver.findElementsByXPath("//android.widget.Button[@text='voltar']");

        return elementosTextos.size() == 7 && elementosBotaoSalvar.size() == 1 && elementosBotaoVoltar.size() == 1;
    }

    private boolean verifyCurrentScreenHomePassenger() {
        List elementosBotaoMinhasInfos = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Minhas informações']");

        return elementosBotaoMinhasInfos.size() == 1;
    }

    private boolean verifyCurrentScreenHomeDriver() {
        List elementosTextoOpcoes = appiumDriver.findElementsByXPath("//android.widget.TextView[@text='Opções do Motorista']");
        List elementosBotaoListar = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Listar Passageiros']");
        List elementosBotaoCalculo = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Calculo de Rota']");

        return elementosTextoOpcoes.size() == 1 && elementosBotaoListar.size() == 1 && elementosBotaoCalculo.size() == 1;
    }

    private boolean verifyCurrentScreenPassengerList() {
        List elementosTextoOpcoes = appiumDriver.findElementsByXPath("//android.widget.TextView[@text='Opções do Motorista']");
        List elementosBotaoCalculo = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Calculo de Rota']");
        List elementosLista = appiumDriver.findElementsByClassName("android.widget.ListView");

        return elementosTextoOpcoes.size() == 1 && elementosBotaoCalculo.size() == 1 && elementosLista.size() == 1;
    }

    private boolean verifyCurrentScreenPassengerDetail() {
        List elementosTextoInfos = appiumDriver.findElementsByXPath("//android.widget.TextView[@text='INFORMAÇÕES GERAIS DO PASSAGEIRO']");
        return elementosTextoInfos.size() == 1;
    }

    private boolean verifyCurrentScreenAddressDetail() {
        List elementosEndereço = appiumDriver.findElementsByXPath("//android.widget.TextView[@text='Endereço:']");
        List elementosNumero = appiumDriver.findElementsByXPath("//android.widget.TextView[@text='Número:']");
        List elementosBotaoHora = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Hora Prevista']");
        return elementosEndereço.size() >= 1 && elementosNumero.size() >= 1 && elementosBotaoHora.size() >= 1;
    }

    private boolean verifyTextoTime(String time) {
        List elementosTempo = appiumDriver.findElementsByXPath("//android.widget.TextView[@text='"+ time +"']");
        return elementosTempo.size() == 1;
    }

    private boolean verifyTextoEnderecos(String endereco, String numero, String bairro) {
        List elementosEndereco= appiumDriver.findElementsByXPath("//android.widget.EditText[@text='"+ endereco +"']");
        List elementosNumero= appiumDriver.findElementsByXPath("//android.widget.EditText[@text='"+ numero +"']");
        List elementosBairro= appiumDriver.findElementsByXPath("//android.widget.EditText[@text='"+ bairro +"']");

        return elementosEndereco.size() == 1 && elementosNumero.size() == 1 && elementosBairro.size() == 1;
    }

    private boolean verifyTextoEnderecosNotNull() {
        List<MobileElement> camposEndereco = appiumDriver.findElementsByClassName("android.widget.EditText");
        MobileElement campoEndereco = camposEndereco.get(0);
        MobileElement campoNumero = camposEndereco.get(1);
        MobileElement campoBairro = camposEndereco.get(2);

        return !campoEndereco.getText().equals("") && !campoNumero.getText().equals("") && !campoBairro.getText().equals("");
    }

    private void clickLogin() throws InterruptedException {
        List<MobileElement> elementosBotaoEntrar = appiumDriver.findElementsByXPath("//android.widget.Button[@text='ENTRAR']");
        elementosBotaoEntrar.get(0).click();
        Thread.sleep(2000);
    }

    private void clickRegister() throws InterruptedException {
        List<MobileElement> elementosBotaoCadastrar = appiumDriver.findElementsByXPath("//android.widget.Button[@text='CADASTRAR']");
        elementosBotaoCadastrar.get(0).click();
        Thread.sleep(2000);
    }

    private void clickFirstPassengerInList() throws InterruptedException {
        List<MobileElement> elementosTextos = appiumDriver.findElementsByClassName("android.widget.TextView");
        elementosTextos.get(2).click();
        Thread.sleep(2000);
    }

    private void clickList() throws InterruptedException {
        List<MobileElement> botoesListar = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Listar Passageiros']");
        MobileElement botaoLista = botoesListar.get(0);
        botaoLista.click();
        Thread.sleep(2000);
    }

    private void clickDetail(int index) throws InterruptedException {
        List<MobileElement> botoesDetalhes = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Detalhes']");
        MobileElement botaoDetalhes = botoesDetalhes.get(index);
        botaoDetalhes.click();
        Thread.sleep(2000);
    }

    private void clickHoraPrevista() throws InterruptedException {
        List<MobileElement> botoesHorasPrevistas = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Hora Prevista']");
        MobileElement botaoHoraPrevista = botoesHorasPrevistas.get(0);
        botaoHoraPrevista.click();
        Thread.sleep(2000);
    }

    private void clickExcluirHora() throws InterruptedException {
        List<MobileElement> botoesExcluirHora = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Excluir Hora Prevista']");
        MobileElement botaoExcluirHora = botoesExcluirHora.get(0);
        botaoExcluirHora.click();
        Thread.sleep(2000);
    }

    private void clickAlterarEndereco() throws InterruptedException {
        List<MobileElement> botoesAlterar = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Alterar']");
        MobileElement botaoAlterar = botoesAlterar.get(0);
        botaoAlterar.click();
        Thread.sleep(2000);
    }

    private void fillRegister(String nome, String cpf, String tel, String endereco, String numero, String bairro, String senha,
                             String confirmaSenha, Boolean isDriver) throws InterruptedException {

        List<MobileElement> inputsEditText = appiumDriver.findElementsByClassName("android.widget.EditText");
        MobileElement inputNome = inputsEditText.get(0);
        inputNome.setValue(nome);

        MobileElement inputCPF = inputsEditText.get(1);
        inputCPF.setValue(cpf);

        MobileElement inputTel = inputsEditText.get(2);
        inputTel.setValue(tel);

        MobileElement inputEndereco = inputsEditText.get(3);
        inputEndereco.setValue(endereco);

        MobileElement inputNumero = inputsEditText.get(4);
        inputNumero.setValue(numero);

        MobileElement inputBairro = inputsEditText.get(5);
        inputBairro.setValue(bairro);

        MobileElement inputSenha = inputsEditText.get(6);
        inputSenha.setValue(senha);

        MobileElement inputConfirmarSenha = inputsEditText.get(7);
        inputConfirmarSenha.setValue(confirmaSenha);

        if (isDriver) {
            List<MobileElement> switchTipoUser = appiumDriver.findElementsByClassName("android.widget.Switch");
            MobileElement switchUserType = switchTipoUser.get(0);
            switchUserType.click();
        }

        List<MobileElement> botoesCadastro = appiumDriver.findElementsByXPath("//android.widget.Button[@text='salvar']");
        MobileElement botaoSalvar = botoesCadastro.get(0);
        botaoSalvar.click();

        Thread.sleep(2000);
    }

    private void fillTime(String hour, String minute) throws InterruptedException {
        List<MobileElement> inputsHour = appiumDriver.findElementsByXPath("//android.widget.EditText[@resource-id='android:id/input_hour']");
        inputsHour.get(0).setValue(hour);
        List<MobileElement> inputsMinute = appiumDriver.findElementsByXPath("//android.widget.EditText[@resource-id='android:id/input_minute']");
        inputsMinute.get(0).setValue(minute);
        List<MobileElement> botaoOk = appiumDriver.findElementsByXPath("//android.widget.Button[@text='OK']");
        botaoOk.get(0).click();

        Thread.sleep(2000);
    }

    private void fillEndereco(String endereco, String numero, String bairro) throws InterruptedException {
        List<MobileElement> camposEndereco = appiumDriver.findElementsByClassName("android.widget.EditText");
        camposEndereco.get(0).setValue(endereco);
        camposEndereco.get(1).setValue(numero);
        camposEndereco.get(2).setValue(bairro);

        List<MobileElement> botaoSalvar = appiumDriver.findElementsByXPath("//android.widget.Button[@text='Salvar']");
        botaoSalvar.get(0).click();

        Thread.sleep(2000);
    }

    private static AppiumDriver generateAppiumDriver() throws MalformedURLException {
        File apk = new File("Caminho completo do arquivo .apk");
        DesiredCapabilities configuracoes = DesiredCapabilities.android();
        configuracoes.setCapability(MobileCapabilityType.APP, apk.getAbsolutePath());
        configuracoes.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        configuracoes.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        configuracoes.setCapability("autoGrantPermissions", true);
        configuracoes.setCapability("noReset", false);

        URL urlAppium = new URL("http://127.0.0.1:4723/wd/hub");

        return new AppiumDriver(urlAppium, configuracoes);
    }

}
