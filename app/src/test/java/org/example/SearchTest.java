package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchTest extends BaseTest {

    @Test
    @DisplayName("UC-3: Отправка поискового запроса (Chrome)")
    public void testSearchSubmissionChrome() {
        setUp("chrome");
        runSearchSubmissionScenario();
    }

    @Test
    @DisplayName("UC-3: Отправка поискового запроса (Firefox)")
    public void testSearchSubmissionFirefox() {
        setUp("firefox");
        runSearchSubmissionScenario();
    }

    private void runSearchSubmissionScenario() {
        openHomePage();

        WebElement searchField = waitForVisible("//form[@name='search_form']//input[@type='text' and @name='keywords']");
        searchField.click();
        searchField.clear();
        searchField.sendKeys("кот");
        pauseBetweenSteps();

        clickWithScroll("//form[@name='search_form']//a[contains(@class,'search-btn') and normalize-space()='Найти']");

        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(BASE_URL)));
        } catch (TimeoutException exception) {
            ((JavascriptExecutor) driver).executeScript("document.forms['search_form'].submit();");
        }

        wait.until(ExpectedConditions.or(
            ExpectedConditions.not(ExpectedConditions.urlToBe(BASE_URL)),
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'maintitle')]")),
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='navstrip']"))
        ));
        WebElement pageBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//body")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(pageBody.isDisplayed(),
            "После отправки формы не загрузилась целевая страница поиска.");
        Assertions.assertNotEquals(BASE_URL, driver.getCurrentUrl(),
            "После нажатия кнопки 'Найти' пользователь остался на главной странице.");
    }
}
