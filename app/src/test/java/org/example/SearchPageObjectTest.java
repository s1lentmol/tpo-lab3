package org.example;

import org.example.pages.HomePage;
import org.example.pages.SearchResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchPageObjectTest extends BaseTest {

    @Test
    @DisplayName("PO UC-3: Отправка поискового запроса (Chrome)")
    public void testSearchSubmissionChrome() {
        setUp("chrome");
        runSearchSubmissionScenario();
    }

    @Test
    @DisplayName("PO UC-3: Отправка поискового запроса (Firefox)")
    public void testSearchSubmissionFirefox() {
        setUp("firefox");
        runSearchSubmissionScenario();
    }

    private void runSearchSubmissionScenario() {
        SearchResultPage searchResultPage = new HomePage(driver, wait)
            .open()
            .search("кот");

        Assertions.assertTrue(searchResultPage.isOpened(), "После отправки формы не загрузилась целевая страница поиска.");
        Assertions.assertTrue(searchResultPage.isNotHomePage(), "После нажатия кнопки 'Найти' пользователь остался на главной странице.");
    }
}
