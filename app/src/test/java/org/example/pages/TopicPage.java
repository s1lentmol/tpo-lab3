package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

public class TopicPage extends BasePage {
    public TopicPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isOpened() {
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[@id='main-title']/a[contains(@class,'subtitle')]")
                ),
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='navstrip']")
                ),
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'caption-all-comments') and contains(normalize-space(),'Все комментарии')]")
                )
            ));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public String getTopicTitle() {
        return waitForVisible("//h1[@id='main-title']/a[contains(@class,'subtitle')]").getText().trim();
    }

    public boolean isCommentsBlockVisible() {
        return isVisible("//div[contains(@class,'caption-all-comments') and contains(normalize-space(),'Все комментарии')]");
    }

    public HomePage returnHomeViaLogo() {
        clickWithScroll("//div[@id='top-logo']//a[@title='ЯПлакалъ']");
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlToBe(HomePage.BASE_URL),
            ExpectedConditions.urlToBe("https://www.yaplakal.com")
        ));
        return new HomePage(driver, wait);
    }

    public SectionPage goToPicturesSectionViaBreadcrumb() {
        clickWithScroll("//div[@id='navstrip']/descendant::a[normalize-space()='Картинки' and contains(@href,'/forum2/')]");
        wait.until(ExpectedConditions.urlContains("/forum2/"));
        return new SectionPage(driver, wait);
    }
}
