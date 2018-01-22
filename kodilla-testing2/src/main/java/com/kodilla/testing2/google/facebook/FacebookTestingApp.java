package com.kodilla.testing2.google.facebook;
/*
Exercise 23.3
Twoim zadaniem będzie napisanie programu, który na stronie https://www.facebook.com/ przygotuje dane do założenia konta.
Konkretnie poprzez wypełnienie dnia, miesiąca i roku urodzenia.


W celu realizacji zadania:

W katalogu src/main/java stwórz pakiet com.kodilla.testing2.facebook, a w nim klasę FacebookTestingApp wraz z metodą
main(String[] args)
W metodzie main(String[] args) napisz kod, który otworzy w przeglądarce Chrome stronę https://www.facebook.com/,
 a następnie wypełni pola dzień, miesiąc i rok w sekcji danych związanych z datą urodzenia użytkownika
Do realizacji powyższego użyj konwencji XPath-Relative oraz obiektów klasy Select

Ustawiam dzisiejsza date :-)
 */

import com.kodilla.testing2.google.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FacebookTestingApp {
    public static final String XPATH_SELECT_DAY = "//div[@class=\"_5k_5\"]/span/span/select[1]";
    public static final String XPATH_SELECT_MONTH = "//div[@class=\"_5k_5\"]/span/span/select[2]";
    public static final String XPATH_SELECT_YEAR = "//div[@class=\"_5k_5\"]/span/span/select[3]";
    public static final String XPATH_WAIT_FOR = "//select[1]";


    public static void main(String[] args) {
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get("https://www.facebook.com/");

        while (!driver.findElement(By.xpath(XPATH_WAIT_FOR)).isDisplayed());

        WebElement selectDayCombo = driver.findElement(By.xpath(XPATH_SELECT_DAY));
        Select selectDay = new Select(selectDayCombo);
        selectDay.selectByIndex(22);

        WebElement selectMonthCombo = driver.findElement(By.xpath(XPATH_SELECT_MONTH));
        Select selectMonth = new Select(selectMonthCombo);
        selectMonth.selectByIndex(01);

        WebElement selectYearCombo = driver.findElement(By.xpath(XPATH_SELECT_YEAR));
        Select selectYear = new Select(selectYearCombo);
        selectYear.selectByIndex(1);

    }
}
