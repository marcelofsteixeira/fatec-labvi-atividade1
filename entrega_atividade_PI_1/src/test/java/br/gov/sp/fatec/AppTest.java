package br.gov.sp.fatec;

import static org.junit.Assert.assertEquals;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class AppTest 
{
    @Test
    public void loginCorrectUserPasswordTest () throws MalformedURLException, InterruptedException {
        AppiumDriver appiumDriver = this.generateAppiumDriver();
        Thread.sleep(10000);

        List<MobileElement> elementosInputTexto = appiumDriver.findElementsByClassName("android.widget.EditText");
        MobileElement inputCPF = elementosInputTexto.get(0);
        MobileElement inputSenha = elementosInputTexto.get(1);
        inputCPF.setValue("50");
        inputSenha.setValue("123");

        List<MobileElement> elementosBotoes = appiumDriver.findElementsByClassName("android.widget.Button");
        MobileElement botaoLogin = elementosBotoes.get(1);
        botaoLogin.click();
        Thread.sleep(2000);

        List<MobileElement> elementosTextos = appiumDriver.findElementsByClassName("android.widget.TextView");
        assertEquals("Lista de Passageiros", elementosTextos.get(0).getText());
        appiumDriver.closeApp();
    }
    @Test
    public void loginIncorrectUserPasswordTest () throws MalformedURLException, InterruptedException {
        AppiumDriver appiumDriver = this.generateAppiumDriver();
        Thread.sleep(10000);

        List<MobileElement> elementosInputTexto = appiumDriver.findElementsByClassName("android.widget.EditText");
        MobileElement inputCPF = elementosInputTexto.get(0);
        MobileElement inputSenha = elementosInputTexto.get(1);
        inputCPF.setValue("11122233344");
        inputSenha.setValue("senha");

        List<MobileElement> elementosBotoes = appiumDriver.findElementsByClassName("android.widget.Button");
        MobileElement botaoLogin = elementosBotoes.get(1);
        botaoLogin.click();
        Thread.sleep(2000);

        List<MobileElement> elementosTextos = appiumDriver.findElementsByClassName("android.widget.TextView");
        assertEquals("CPF", elementosTextos.get(0).getText());
        appiumDriver.closeApp();
    }


    @Test
    public void loginEmptyCPFTest() throws MalformedURLException, InterruptedException {
        AppiumDriver appiumDriver = this.generateAppiumDriver();
        Thread.sleep(10000);

        List<MobileElement> elementosInputTexto = appiumDriver.findElementsByClassName("android.widget.EditText");
        MobileElement inputSenha = elementosInputTexto.get(1);
        inputSenha.setValue("123");

        List<MobileElement> elementosBotoes = appiumDriver.findElementsByClassName("android.widget.Button");
        MobileElement botaoLogin = elementosBotoes.get(1);
        botaoLogin.click();
        Thread.sleep(2000);

        List<MobileElement> elementosTextos = appiumDriver.findElementsByClassName("android.widget.TextView");
        assertEquals("CPF", elementosTextos.get(0).getText());
        appiumDriver.closeApp();
    }

    @Test
    public void loginEmptyPasswordTest  () throws MalformedURLException, InterruptedException {
        AppiumDriver appiumDriver = this.generateAppiumDriver();
        Thread.sleep(10000);

        List<MobileElement> elementosInputTexto = appiumDriver.findElementsByClassName("android.widget.EditText");
        MobileElement inputCPF = elementosInputTexto.get(0);
        inputCPF.setValue("50");

        List<MobileElement> elementosBotoes = appiumDriver.findElementsByClassName("android.widget.Button");
        MobileElement botaoLogin = elementosBotoes.get(1);
        botaoLogin.click();
        Thread.sleep(2000);

        List<MobileElement> elementosTextos = appiumDriver.findElementsByClassName("android.widget.TextView");
        assertEquals("CPF", elementosTextos.get(0).getText());
        appiumDriver.closeApp();
    }


    private AppiumDriver generateAppiumDriver() throws MalformedURLException {
        File apk = new File("Path completo do arquivo .apk");
        DesiredCapabilities configuracoes = new DesiredCapabilities();
        configuracoes.setCapability(MobileCapabilityType.APP, apk.getAbsolutePath());
        configuracoes.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        configuracoes.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        URL urlAppium = new URL("http://127.0.0.1:4723/wd/hub");

        return new AppiumDriver(urlAppium, configuracoes);
    }

}
