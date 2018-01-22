package com.kodilla.testing2.google.ebay;

/*
Exercise 23.2
Twoim zadaniem jest napisanie mini-aplikacji otwierającej przeglądarkę na stronie https://www.ebay.com/ oraz wpisanie
w pole wyszukiwania przedmiotów słowa "Laptop" oraz zatwierdzenie wyszukiwania - jak pokazano na poniższym zdjęciu:


W celu realizacji zadania:

Stwórz w katalogu src/main/java pakiet com.kodilla.testing2.ebay
W pakiecie tym stwórz klasę EBayTestingApp z metodą main(String[] args)
W metodzie main napisz kod, który tworzy Selenium WebDriver (wykorzystaj istniejącą klasę WebDriverConfig do
jego utworzenia), znajduje pole do wyszukiwania przedmiotów, wstawia do niego napis "Laptop" i zatwierdza wyszukiwanie
 */

import com.kodilla.testing2.google.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EbayTestingApp {
    public static final String SEARCHFIELD = "gh-ac";

    public static void main(String[] args){
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get("https://www.ebay.com");

        WebElement searchfield = driver.findElement(By.id(SEARCHFIELD));
        searchfield.sendKeys("Laptop");
        searchfield.submit();
    }
}
