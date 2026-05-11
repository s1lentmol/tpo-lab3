package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public static final String BASE_URL = "https://www.yaplakal.com/";

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public HomePage open() {
        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                driver.get(BASE_URL);
            } catch (TimeoutException ignored) {
            }

            if (isOpened()) {
                pauseBetweenSteps();
                return this;
            }
        }

        throw new TimeoutException("Home page did not load.");
    }

    public boolean isOpened() {
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//form[@name='search_form']//input[@name='keywords' and @type='text']")
                ),
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='top-logo']//a[@title='ЯПлакалъ']")
                ),
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[normalize-space()='Активные темы' and contains(@href,'getactive')]")
                )
            ));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public SectionPage openPicturesSection() {
        clickWithScroll("//a[normalize-space()='Картинки' and contains(@href,'pics.yaplakal.com')]");
        wait.until(ExpectedConditions.urlContains("pics.yaplakal.com"));
        return new SectionPage(driver, wait);
    }

    public SectionPage openVideoSection() {
        clickAnyVisibleWithScroll("//a[contains(normalize-space(),'Видео') and contains(@href,'/forum28/')]");
        wait.until(ExpectedConditions.urlContains("/forum28/"));
        return new SectionPage(driver, wait);
    }

    public SectionPage openFotogabaSection() {
        clickWithScroll("//a[normalize-space()='Фотожаба' and contains(@href,'fotozhaba.yaplakal.com')]");
        wait.until(ExpectedConditions.urlContains("fotozhaba.yaplakal.com"));
        return new SectionPage(driver, wait);
    }

    public SectionPage openActiveTopics() {
        clickWithScroll("//a[normalize-space()='Активные темы' and contains(@href,'getactive')]");
        wait.until(ExpectedConditions.urlContains("getactive"));
        return new SectionPage(driver, wait);
    }

    public String getFirstPictureTopicTitle() {
        return waitForVisible(
            "(//h2[contains(@class,'mainpage')]/child::a[contains(@href,'/forum2/topic') and contains(@class,'subtitle')])[1]"
        ).getText().trim();
    }

    public TopicPage openFirstPictureTopic() {
        clickWithScroll("(//h2[contains(@class,'mainpage')]/child::a[contains(@href,'/forum2/topic') and contains(@class,'subtitle')])[1]");
        return new TopicPage(driver, wait);
    }

    public TopicPage openTopicViaComments() {
        clickWithScroll(
            "(//b[contains(@class,'icon-comments')]/child::a[contains(@href,'/forum2/topic') or contains(@href,'/forum27/topic') or contains(@href,'/forum28/topic')])[1]"
        );
        return new TopicPage(driver, wait);
    }

    public TopicPage openTopicViaReadMore() {
        clickWithScroll(
            "(//a[normalize-space()='Читать дальше...' and (contains(@href,'/forum2/topic') or contains(@href,'/forum27/topic'))])[1]"
        );
        return new TopicPage(driver, wait);
    }

    public SearchResultPage search(String query) {
        WebElement searchField = waitForVisible("//form[@name='search_form']//input[@type='text' and @name='keywords']");
        searchField.click();
        searchField.clear();
        searchField.sendKeys(query);
        pauseBetweenSteps();

        clickWithScroll("//form[@name='search_form']//a[contains(@class,'search-btn') and normalize-space()='Найти']");

        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(BASE_URL)));
        } catch (TimeoutException exception) {
            ((JavascriptExecutor) driver).executeScript("document.forms['search_form'].submit();");
        }

        return new SearchResultPage(driver, wait);
    }
}
