package com.kodilla.testing2.google;

import com.kodilla.testing2.google.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleTestingApp {

    public static final String SEARCHFIELD = "lst-ib";

    public static void main(String[] args) {
        WebDriver webDriver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        webDriver.get("https://www.google.com");

        WebElement searchfield = webDriver.findElement(By.id(SEARCHFIELD));
        searchfield.sendKeys("Kodilla");
        searchfield.submit();
    }
}
