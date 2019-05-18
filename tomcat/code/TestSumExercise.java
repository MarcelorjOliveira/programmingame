/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author marcelooliveira
 */
public class TestSumExercise {

    // Declarando um objeto do tipo WebDriver, utilizado pelo Selenium WebDriver.
    private static WebDriver driver;
 
    // Método que inicia tudo que for necessário para o teste
    // Cria uma instância do navegador e abre a página inicial da DevMedia.
    @BeforeClass
    public static void setUpTest(){
        driver = new FirefoxDriver();
        driver.get("http://www.devmedia.com.br");
    }
 
    // Método que finaliza o teste, fechando a instância do WebDriver.    
    @AfterClass
    public static void tearDownTest(){
        driver.quit();
    }
 
    // Testa título "DevMedia - Cursos, Tutoriais e Vídeos para Desenvolvedores".
    @Test
    public void testaTituloDaPagina(){
        assertEquals("DevMedia - Cursos, Tutoriais e Vídeos para Desenvolvedores", driver.getTitle());
    }
 
    // Método que testa o login no site DevMedia.
    @Test
    public void testaLoginDevMedia() {
 
    // Instancia um novo objeto do tipo "WebElement", e passa como parâmetro
    // um elemento da tela cujo valor do atributo "name" seja igual a "usuario".
    WebElement element = driver.findElement(By.name("usuario"));
     
    // Insere dados no elemento "usuario".
    element.sendKeys("user@devmedia.com.br");
 
    // Atribui ao objeto “element” o elemento de atributo "name" igual a "senha".
    element = driver.findElement(By.name("senha"));
 
    // Insere dados no elemento "senha".
    element.sendKeys("123456");
 
    // Clica no botão "OK" e submete os dados para concluir o login.
        driver.findElement(By.id("imglogar")).click();
    }
}   
