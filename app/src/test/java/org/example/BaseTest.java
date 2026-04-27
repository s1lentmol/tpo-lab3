package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected static final String BASE_URL = "https://www.yaplakal.com/";
    private static final long DEFAULT_STEP_DELAY_MS = Long.getLong("stepDelayMs", 2000L);
    private static final long DEFAULT_CLOSE_DELAY_MS = Long.getLong("closeDelayMs", 2000L);
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected void setUp(String browser) {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            if (headless) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            if (headless) {
                options.addArguments("-headless");
            }
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
            driver = new FirefoxDriver(options);
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void openHomePage() {
        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                driver.get(BASE_URL);
            } catch (TimeoutException ignored) {
            }

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
                pauseBetweenSteps();
                return;
            } catch (TimeoutException exception) {
                if (attempt == 1) {
                    throw exception;
                }
            }
        }

        pauseBetweenSteps();
    }

    protected WebElement waitForVisible(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected WebElement waitForClickable(String xpath) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
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

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            pause(DEFAULT_CLOSE_DELAY_MS);
            driver.quit();
        }
    }
}
