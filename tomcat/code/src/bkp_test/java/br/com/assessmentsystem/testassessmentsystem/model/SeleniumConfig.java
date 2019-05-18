package br.com.assessmentsystem.testassessmentsystem.model;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumConfig {
 
    private WebDriver driver;
    
    static {
        System.setProperty("webdriver.gecko.driver", findFile("geckodriver"));
    }
 
    public SeleniumConfig() {
        Capabilities capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    static private String findFile(String filename) {
       String paths[] = {"", "bin/", "target/classes", "seleniumdriver/"};
       for (String path : paths) {
          if (new File(path + filename).exists())
              return path + filename;
       }
       return "";
    }
    
    public WebDriver getDriver() {
    	return driver;
    }
 
}