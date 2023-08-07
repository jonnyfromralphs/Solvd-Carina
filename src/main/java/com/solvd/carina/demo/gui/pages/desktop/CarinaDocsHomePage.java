package com.solvd.carina.demo.gui.pages.desktop;


import com.solvd.carina.demo.gui.components.CarinaHeader;
import com.solvd.carina.demo.gui.components.CarinaNavBar;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CarinaDocsHomePage extends AbstractPage {

    @FindBy(tagName = "header")
    private CarinaHeader carinaHeader;

    @FindBy(tagName = "footer")
    private ExtendedWebElement footer;

    @FindBy(xpath = "//nav[contains(@class, 'md-nav md-nav--primary')]")
    private CarinaNavBar carinaNavBar;

    public CarinaDocsHomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(footer);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public CarinaHeader getCarinaHeader() {
        return carinaHeader;
    }

    public boolean isHeaderSticky() {
        footer.scrollTo();
        return carinaHeader.getHeader().isVisible();
    }

    public CarinaNavBar getCarinaNavBar() {
        return carinaNavBar;
    }

}
