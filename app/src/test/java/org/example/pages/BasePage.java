package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private static final long DEFAULT_STEP_DELAY_MS = Long.getLong("stepDelayMs", 2000L);

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    protected WebElement waitForVisible(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected WebElement waitForAnyVisible(String xpath) {
        return wait.until(driver -> {
            for (WebElement element : driver.findElements(By.xpath(xpath))) {
                if (element.isDisplayed()) {
                    return element;
                }
            }
            return null;
        });
    }

    protected void clickWithScroll(String xpath) {
        WebElement element = waitForVisible(xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            element.click();
        } catch (WebDriverException exception) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        pauseBetweenSteps();
    }

    protected void clickAnyVisibleWithScroll(String xpath) {
        WebElement element = waitForAnyVisible(xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            element.click();
        } catch (WebDriverException exception) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        pauseBetweenSteps();
    }

    protected boolean isVisible(String xpath) {
        try {
            return waitForVisible(xpath).isDisplayed();
        } catch (TimeoutException exception) {
            return false;
        }
    }

    protected void pauseBetweenSteps() {
        pause(DEFAULT_STEP_DELAY_MS);
    }

    protected void pause(long milliseconds) {
        if (milliseconds <= 0) {
            return;
        }

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Test execution was interrupted.", exception);
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
