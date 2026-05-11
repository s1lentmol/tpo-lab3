package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePage {
    public SearchResultPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isOpened() {
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.not(ExpectedConditions.urlToBe(HomePage.BASE_URL)),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'maintitle')]")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='navstrip']"))
            ));
            return isVisible("//body");
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public boolean isNotHomePage() {
        return !HomePage.BASE_URL.equals(getCurrentUrl());
    }
}
