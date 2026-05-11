package org.example;

import org.example.pages.HomePage;
import org.example.pages.SectionPage;
import org.example.pages.TopicPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NavigationPageObjectTest extends BaseTest {

    @Test
    @DisplayName("PO UC-1: Переход из главной в раздел Картинки (Chrome)")
    public void testPicturesSectionNavigationChrome() {
        setUp("chrome");
        runPicturesSectionNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-1: Переход из главной в раздел Картинки (Firefox)")
    public void testPicturesSectionNavigationFirefox() {
        setUp("firefox");
        runPicturesSectionNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-2: Открытие темы из блока Картинки на главной (Chrome)")
    public void testOpenPictureTopicFromHomeChrome() {
        setUp("chrome");
        runOpenPictureTopicScenario();
    }

    @Test
    @DisplayName("PO UC-2: Открытие темы из блока Картинки на главной (Firefox)")
    public void testOpenPictureTopicFromHomeFirefox() {
        setUp("firefox");
        runOpenPictureTopicScenario();
    }

    @Test
    @DisplayName("PO UC-4: Переход в раздел Видео (Chrome)")
    public void testVideoSectionNavigationChrome() {
        setUp("chrome");
        runVideoSectionNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-4: Переход в раздел Видео (Firefox)")
    public void testVideoSectionNavigationFirefox() {
        setUp("firefox");
        runVideoSectionNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-5: Переход в тему по ссылке Комментарии (Chrome)")
    public void testOpenTopicViaCommentsChrome() {
        setUp("chrome");
        runOpenTopicViaCommentsScenario();
    }

    @Test
    @DisplayName("PO UC-5: Переход в тему по ссылке Комментарии (Firefox)")
    public void testOpenTopicViaCommentsFirefox() {
        setUp("firefox");
        runOpenTopicViaCommentsScenario();
    }

    @Test
    @DisplayName("PO UC-6: Переход в раздел Фотожаба (Chrome)")
    public void testFotogabaSectionNavigationChrome() {
        setUp("chrome");
        runFotogabaSectionNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-6: Переход в раздел Фотожаба (Firefox)")
    public void testFotogabaSectionNavigationFirefox() {
        setUp("firefox");
        runFotogabaSectionNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-7: Открытие темы по ссылке Читать дальше (Chrome)")
    public void testReadMoreNavigationChrome() {
        setUp("chrome");
        runReadMoreNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-7: Открытие темы по ссылке Читать дальше (Firefox)")
    public void testReadMoreNavigationFirefox() {
        setUp("firefox");
        runReadMoreNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-8: Возврат на главную через логотип (Chrome)")
    public void testReturnHomeViaLogoChrome() {
        setUp("chrome");
        runReturnHomeViaLogoScenario();
    }

    @Test
    @DisplayName("PO UC-8: Возврат на главную через логотип (Firefox)")
    public void testReturnHomeViaLogoFirefox() {
        setUp("firefox");
        runReturnHomeViaLogoScenario();
    }

    @Test
    @DisplayName("PO UC-9: Переход в Активные темы (Chrome)")
    public void testActiveTopicsNavigationChrome() {
        setUp("chrome");
        runActiveTopicsNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-9: Переход в Активные темы (Firefox)")
    public void testActiveTopicsNavigationFirefox() {
        setUp("firefox");
        runActiveTopicsNavigationScenario();
    }

    @Test
    @DisplayName("PO UC-10: Возврат из темы в раздел через breadcrumb (Chrome)")
    public void testBreadcrumbBackToSectionChrome() {
        setUp("chrome");
        runBreadcrumbBackToSectionScenario();
    }

    @Test
    @DisplayName("PO UC-10: Возврат из темы в раздел через breadcrumb (Firefox)")
    public void testBreadcrumbBackToSectionFirefox() {
        setUp("firefox");
        runBreadcrumbBackToSectionScenario();
    }

    private HomePage homePage() {
        return new HomePage(driver, wait).open();
    }

    private void runPicturesSectionNavigationScenario() {
        SectionPage picturesPage = homePage().openPicturesSection();

        Assertions.assertTrue(picturesPage.isOpened(), "После перехода в раздел 'Картинки' страница не отобразилась.");
        Assertions.assertTrue(picturesPage.urlContains("pics.yaplakal.com"), "После перехода открылась не витрина картинок.");
        Assertions.assertTrue(picturesPage.hasVisibleContent(), "В разделе 'Картинки' не найден контент.");
    }

    private void runOpenPictureTopicScenario() {
        HomePage homePage = homePage();
        String expectedTitle = homePage.getFirstPictureTopicTitle();
        TopicPage topicPage = homePage.openFirstPictureTopic();

        Assertions.assertTrue(topicPage.isOpened(), "После открытия темы страница темы не загрузилась.");
        Assertions.assertEquals(expectedTitle, topicPage.getTopicTitle(), "Заголовок темы после перехода изменился.");
        Assertions.assertTrue(topicPage.isCommentsBlockVisible(), "На странице темы не найден блок комментариев.");
    }

    private void runVideoSectionNavigationScenario() {
        SectionPage videoPage = homePage().openVideoSection();

        Assertions.assertTrue(videoPage.isOpened(), "После перехода в раздел 'Видео' страница не отобразилась.");
        Assertions.assertTrue(videoPage.urlContains("/forum28/"), "После перехода открылся не раздел 'Видео'.");
        Assertions.assertTrue(videoPage.hasVisibleContent(), "В разделе 'Видео' не найден контент.");
    }

    private void runOpenTopicViaCommentsScenario() {
        TopicPage topicPage = homePage().openTopicViaComments();

        Assertions.assertTrue(topicPage.isOpened(), "После перехода по ссылке 'Комментарии' страница темы не загрузилась.");
        Assertions.assertTrue(topicPage.isCommentsBlockVisible(), "После перехода по ссылке 'Комментарии' не найден блок комментариев.");
        Assertions.assertTrue(topicPage.getCurrentUrl().contains("/topic"), "Ссылка 'Комментарии' не открыла страницу темы.");
    }

    private void runFotogabaSectionNavigationScenario() {
        SectionPage fotozhabaPage = homePage().openFotogabaSection();

        Assertions.assertTrue(fotozhabaPage.isOpened(), "После перехода в раздел 'Фотожаба' страница не отобразилась.");
        Assertions.assertTrue(fotozhabaPage.urlContains("fotozhaba.yaplakal.com"), "После перехода открылась не витрина 'Фотожаба'.");
        Assertions.assertTrue(fotozhabaPage.hasVisibleContent(), "В разделе 'Фотожаба' не найден контент.");
    }

    private void runReadMoreNavigationScenario() {
        TopicPage topicPage = homePage().openTopicViaReadMore();

        Assertions.assertTrue(topicPage.isOpened(), "После перехода по 'Читать дальше...' страница темы не загрузилась.");
        Assertions.assertTrue(topicPage.getCurrentUrl().contains("/topic"), "Переход по ссылке 'Читать дальше...' открыл не страницу темы.");
    }

    private void runReturnHomeViaLogoScenario() {
        TopicPage topicPage = homePage().openFirstPictureTopic();
        HomePage returnedHomePage = topicPage.returnHomeViaLogo();

        Assertions.assertTrue(returnedHomePage.isOpened(), "После клика по логотипу главная страница не отобразилась.");
        Assertions.assertTrue(
            returnedHomePage.getCurrentUrl().equals(HomePage.BASE_URL)
                || returnedHomePage.getCurrentUrl().equals("https://www.yaplakal.com"),
            "После клика по логотипу пользователь не вернулся на главную страницу."
        );
    }

    private void runActiveTopicsNavigationScenario() {
        SectionPage activeTopicsPage = homePage().openActiveTopics();

        Assertions.assertTrue(activeTopicsPage.isOpened(), "На странице активных тем не отобразился контент.");
        Assertions.assertTrue(activeTopicsPage.urlContains("getactive"), "После перехода не открылась страница активных тем.");
        Assertions.assertTrue(activeTopicsPage.hasVisibleContent(), "На странице активных тем не найден контент.");
    }

    private void runBreadcrumbBackToSectionScenario() {
        TopicPage topicPage = homePage().openFirstPictureTopic();
        SectionPage sectionPage = topicPage.goToPicturesSectionViaBreadcrumb();

        Assertions.assertTrue(sectionPage.isOpened(), "После перехода по breadcrumb раздел 'Картинки' не открылся.");
        Assertions.assertTrue(sectionPage.urlContains("/forum2/"), "Breadcrumb не вернул пользователя в раздел 'Картинки'.");
        Assertions.assertTrue(sectionPage.hasVisiblePictureTopics(), "После возврата по breadcrumb список тем раздела не найден.");
    }
}
