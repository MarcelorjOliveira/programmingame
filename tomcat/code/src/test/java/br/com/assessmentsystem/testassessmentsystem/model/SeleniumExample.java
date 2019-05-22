package br.com.assessmentsystem.testassessmentsystem.model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumExample {
    private SeleniumConfig config;

    private String url = "http://www.programmingame.com";

//private String url = "http://localhost/";
    public SeleniumExample() {
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }
    
    public void closeWindow() {
        this.config.getDriver().close();
    }
     
    public String getTitle() {
        return this.config.getDriver().getTitle();
    }
    
    public void getAboutBaeldungPage() {
        closeOverlay();
        clickAboutLink();
        clickAboutUsLink();
    }
     
    private void closeOverlay() {
        List<WebElement> webElementList = this.config.getDriver()
          .findElements(By.tagName("a"));
        if (webElementList != null) {
           webElementList.stream()
             .filter(webElement -> "Close".equalsIgnoreCase(webElement.getAttribute("title")))
             .filter(WebElement::isDisplayed)
             .findAny()
             .ifPresent(WebElement::click);
        }
    }
     
    private void clickAboutLink() {
        this.config.getDriver().findElement(By.partialLinkText("About")).click();
    }
     
    private void clickAboutUsLink() {
        Actions builder = new Actions(config.getDriver());
        WebElement element = this.config.getDriver()
          .findElement(By.partialLinkText("About Baeldung."));
        builder.moveToElement(element)
          .build()
          .perform();
    }
    
    public boolean isAuthorInformationAvailable() {
        return this.config.getDriver()
          .findElement(By.cssSelector("article > .row > div"))
          .isDisplayed();
    }
}