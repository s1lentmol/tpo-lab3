package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationTest extends BaseTest {

    @Test
    @DisplayName("UC-1: Переход из главной в раздел Картинки (Chrome)")
    public void testPicturesSectionNavigationChrome() {
        setUp("chrome");
        runPicturesSectionNavigationScenario();
    }

    @Test
    @DisplayName("UC-1: Переход из главной в раздел Картинки (Firefox)")
    public void testPicturesSectionNavigationFirefox() {
        setUp("firefox");
        runPicturesSectionNavigationScenario();
    }

    @Test
    @DisplayName("UC-2: Открытие темы из блока Картинки на главной (Chrome)")
    public void testOpenPictureTopicFromHomeChrome() {
        setUp("chrome");
        runOpenPictureTopicScenario();
    }

    @Test
    @DisplayName("UC-2: Открытие темы из блока Картинки на главной (Firefox)")
    public void testOpenPictureTopicFromHomeFirefox() {
        setUp("firefox");
        runOpenPictureTopicScenario();
    }

    @Test
    @DisplayName("UC-4: Переход в раздел Видео (Chrome)")
    public void testVideoSectionNavigationChrome() {
        setUp("chrome");
        runVideoSectionNavigationScenario();
    }

    @Test
    @DisplayName("UC-4: Переход в раздел Видео (Firefox)")
    public void testVideoSectionNavigationFirefox() {
        setUp("firefox");
        runVideoSectionNavigationScenario();
    }

    @Test
    @DisplayName("UC-5: Переход в тему по ссылке Комментарии (Chrome)")
    public void testOpenTopicViaCommentsChrome() {
        setUp("chrome");
        runOpenTopicViaCommentsScenario();
    }

    @Test
    @DisplayName("UC-5: Переход в тему по ссылке Комментарии (Firefox)")
    public void testOpenTopicViaCommentsFirefox() {
        setUp("firefox");
        runOpenTopicViaCommentsScenario();
    }

    @Test
    @DisplayName("UC-6: Переход в раздел Фотожаба (Chrome)")
    public void testFotogabaSectionNavigationChrome() {
        setUp("chrome");
        runFotogabaSectionNavigationScenario();
    }

    @Test
    @DisplayName("UC-6: Переход в раздел Фотожаба (Firefox)")
    public void testFotogabaSectionNavigationFirefox() {
        setUp("firefox");
        runFotogabaSectionNavigationScenario();
    }

    @Test
    @DisplayName("UC-7: Открытие темы по ссылке Читать дальше (Chrome)")
    public void testReadMoreNavigationChrome() {
        setUp("chrome");
        runReadMoreNavigationScenario();
    }

    @Test
    @DisplayName("UC-7: Открытие темы по ссылке Читать дальше (Firefox)")
    public void testReadMoreNavigationFirefox() {
        setUp("firefox");
        runReadMoreNavigationScenario();
    }

    @Test
    @DisplayName("UC-8: Возврат на главную через логотип (Chrome)")
    public void testReturnHomeViaLogoChrome() {
        setUp("chrome");
        runReturnHomeViaLogoScenario();
    }

    @Test
    @DisplayName("UC-8: Возврат на главную через логотип (Firefox)")
    public void testReturnHomeViaLogoFirefox() {
        setUp("firefox");
        runReturnHomeViaLogoScenario();
    }

    @Test
    @DisplayName("UC-9: Переход в Активные темы (Chrome)")
    public void testActiveTopicsNavigationChrome() {
        setUp("chrome");
        runActiveTopicsNavigationScenario();
    }

    @Test
    @DisplayName("UC-9: Переход в Активные темы (Firefox)")
    public void testActiveTopicsNavigationFirefox() {
        setUp("firefox");
        runActiveTopicsNavigationScenario();
    }

    @Test
    @DisplayName("UC-10: Возврат из темы в раздел через breadcrumb (Chrome)")
    public void testBreadcrumbBackToSectionChrome() {
        setUp("chrome");
        runBreadcrumbBackToSectionScenario();
    }

    @Test
    @DisplayName("UC-10: Возврат из темы в раздел через breadcrumb (Firefox)")
    public void testBreadcrumbBackToSectionFirefox() {
        setUp("firefox");
        runBreadcrumbBackToSectionScenario();
    }

    private void runPicturesSectionNavigationScenario() {
        openHomePage();

        clickWithScroll("//a[normalize-space()='Картинки' and contains(@href,'pics.yaplakal.com')]");

        wait.until(ExpectedConditions.urlContains("pics.yaplakal.com"));
        WebElement pageBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//body")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(pageBody.isDisplayed(), "После перехода в раздел 'Картинки' страница не отобразилась.");
        Assertions.assertTrue(driver.getCurrentUrl().contains("pics.yaplakal.com"), "После перехода открылась не витрина картинок.");
    }

    private void runOpenPictureTopicScenario() {
        openHomePage();

        WebElement topicLink = waitForVisible("(//h2[contains(@class,'mainpage')]/a[contains(@href,'/forum2/topic') and contains(@class,'subtitle')])[1]");
        pauseBetweenSteps();
        String topicTitle = topicLink.getText().trim();
        clickWithScroll("(//h2[contains(@class,'mainpage')]/a[contains(@href,'/forum2/topic') and contains(@class,'subtitle')])[1]");

        WebElement topicHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[@id='main-title']/a[contains(@class,'subtitle')]")
        ));
        WebElement commentsCaption = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'caption-all-comments') and contains(normalize-space(),'Все комментарии')]")
        ));
        pauseBetweenSteps();

        Assertions.assertEquals(topicTitle, topicHeader.getText().trim(), "Заголовок темы после перехода изменился.");
        Assertions.assertTrue(commentsCaption.isDisplayed(), "На странице темы не найден блок комментариев.");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/forum2/topic"), "Открылся URL не темы раздела 'Картинки'.");
    }

    private void runVideoSectionNavigationScenario() {
        openHomePage();

        clickAnyVisibleWithScroll("//a[contains(normalize-space(),'Видео') and contains(@href,'/forum28/')]");

        wait.until(ExpectedConditions.urlContains("/forum28/"));
        WebElement pageBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//body")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(pageBody.isDisplayed(), "После перехода в раздел 'Видео' страница не отобразилась.");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/forum28/"), "После перехода открылся не раздел 'Видео'.");
    }

    private void runOpenTopicViaCommentsScenario() {
        openHomePage();

        WebElement commentsLink = waitForVisible(
            "(//b[contains(@class,'icon-comments')]/a[contains(@href,'/forum2/topic') or contains(@href,'/forum27/topic') or contains(@href,'/forum28/topic')])[1]"
        );
        String targetUrl = commentsLink.getAttribute("href");
        pauseBetweenSteps();
        clickWithScroll(
            "(//b[contains(@class,'icon-comments')]/a[contains(@href,'/forum2/topic') or contains(@href,'/forum27/topic') or contains(@href,'/forum28/topic')])[1]"
        );

        wait.until(ExpectedConditions.urlToBe(targetUrl));
        WebElement pageBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//body")
        ));
        WebElement commentsCaption = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'caption-all-comments') and contains(normalize-space(),'Все комментарии')]")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(pageBody.isDisplayed(), "После перехода по ссылке 'Комментарии' страница темы не отобразилась.");
        Assertions.assertTrue(commentsCaption.isDisplayed(), "После перехода по ссылке 'Комментарии' не найден блок комментариев.");
        Assertions.assertEquals(targetUrl, driver.getCurrentUrl(), "Ссылка 'Комментарии' открыла не ту тему.");
    }

    private void runFotogabaSectionNavigationScenario() {
        openHomePage();

        clickWithScroll("//a[normalize-space()='Фотожаба' and contains(@href,'fotozhaba.yaplakal.com')]");

        wait.until(ExpectedConditions.urlContains("fotozhaba.yaplakal.com"));
        WebElement pageBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//body")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(pageBody.isDisplayed(), "После перехода в раздел 'Фотожаба' страница не отобразилась.");
        Assertions.assertTrue(driver.getCurrentUrl().contains("fotozhaba.yaplakal.com"), "После перехода открылась не витрина 'Фотожаба'.");
    }

    private void runReadMoreNavigationScenario() {
        openHomePage();

        WebElement readMoreLink = waitForVisible(
            "(//a[normalize-space()='Читать дальше...' and (contains(@href,'/forum2/topic') or contains(@href,'/forum27/topic'))])[1]"
        );
        String targetUrl = readMoreLink.getAttribute("href");
        pauseBetweenSteps();
        clickWithScroll("(//a[normalize-space()='Читать дальше...' and (contains(@href,'/forum2/topic') or contains(@href,'/forum27/topic'))])[1]");

        WebElement topicHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[@id='main-title']/a[contains(@class,'subtitle')]")
        ));
        WebElement navStrip = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@id='navstrip']")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(topicHeader.isDisplayed(), "После перехода по 'Читать дальше...' не открылся заголовок темы.");
        Assertions.assertTrue(navStrip.isDisplayed(), "После перехода по 'Читать дальше...' не загрузилась страница темы.");
        Assertions.assertEquals(targetUrl, driver.getCurrentUrl(), "Переход по ссылке 'Читать дальше...' открыл не ту тему.");
    }

    private void runReturnHomeViaLogoScenario() {
        openHomePage();

        clickWithScroll("(//h2[contains(@class,'mainpage')]/a[contains(@href,'/forum2/topic') and contains(@class,'subtitle')])[1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[@id='main-title']/a[contains(@class,'subtitle')]")
        ));
        pauseBetweenSteps();

        clickWithScroll("//div[@id='top-logo']//a[@title='ЯПлакалъ']");

        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlToBe(BASE_URL),
            ExpectedConditions.urlToBe("https://www.yaplakal.com")
        ));
        WebElement pageBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//body")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(pageBody.isDisplayed(), "После клика по логотипу главная страница не отобразилась.");
        Assertions.assertTrue(driver.getCurrentUrl().equals(BASE_URL) || driver.getCurrentUrl().equals("https://www.yaplakal.com"),
            "После клика по логотипу пользователь не вернулся на главную страницу.");
    }

    private void runActiveTopicsNavigationScenario() {
        openHomePage();

        clickWithScroll("//a[normalize-space()='Активные темы' and contains(@href,'getactive')]");

        wait.until(ExpectedConditions.urlContains("getactive"));
        WebElement pageBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//body")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(pageBody.isDisplayed(), "На странице активных тем не отобразился контент.");
        Assertions.assertTrue(driver.getCurrentUrl().contains("getactive"), "После перехода не открылась страница активных тем.");
    }

    private void runBreadcrumbBackToSectionScenario() {
        openHomePage();

        clickWithScroll("(//h2[contains(@class,'mainpage')]/a[contains(@href,'/forum2/topic') and contains(@class,'subtitle')])[1]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[@id='main-title']/a[contains(@class,'subtitle')]")
        ));
        pauseBetweenSteps();
        clickWithScroll("//div[@id='navstrip']//a[normalize-space()='Картинки' and contains(@href,'/forum2/')]");

        WebElement sectionBreadcrumb = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@id='navstrip']//a[normalize-space()='Картинки']")
        ));
        WebElement firstTopic = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("(//a[contains(@href,'/forum2/topic') and contains(@class,'subtitle')])[1]")
        ));
        pauseBetweenSteps();

        Assertions.assertTrue(sectionBreadcrumb.isDisplayed(), "После перехода по breadcrumb не открылся раздел 'Картинки'.");
        Assertions.assertTrue(firstTopic.isDisplayed(), "После возврата по breadcrumb список тем раздела не найден.");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/forum2/"), "Breadcrumb не вернул пользователя в раздел 'Картинки'.");
    }
}
