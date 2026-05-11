package org.example.pages;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

public class SectionPage extends BasePage {
    public SectionPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isOpened() {
        return isVisible("//body");
    }

    public boolean urlContains(String fragment) {
        return getCurrentUrl().contains(fragment);
    }

    public boolean hasVisibleContent() {
        return isVisible("//body");
    }

    public boolean hasVisiblePictureTopics() {
        return isVisible("(//a[contains(@href,'/forum2/topic') and contains(@class,'subtitle')])[1]");
    }
}
