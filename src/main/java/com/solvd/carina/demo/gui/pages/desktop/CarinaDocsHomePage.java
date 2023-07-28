package com.solvd.carina.demo.gui.pages.desktop;


import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CarinaDocsHomePage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'md-header-nav__title')]//span")
    private ExtendedWebElement carinaHeader;
    @FindBy(xpath = "//a[contains(@class, 'md-header-nav__button md-logo')]//img")
    private ExtendedWebElement zebrunnerImage;
    @FindBy(className = "md-search__form")
    private ExtendedWebElement searchBar;
    @FindBy(xpath = "//a[contains(@class, 'md-source')]")
    private ExtendedWebElement githubLink;
    @FindBy(tagName = "header")
    private ExtendedWebElement header;
    @FindBy(tagName = "footer")
    private ExtendedWebElement footer;
    @FindBy(xpath = "//nav[contains(@class, 'md-nav--primary')]")
    private ExtendedWebElement navBar;

    public CarinaDocsHomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(zebrunnerImage);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public ExtendedWebElement getZebrunnerImage() {
        return zebrunnerImage;
    }

    public ExtendedWebElement getCarinaHeader() {
        return carinaHeader;
    }

    public ExtendedWebElement getSearchBar() {
        return searchBar;
    }

    public ExtendedWebElement getGithubLink() {
        return githubLink;
    }

    public ExtendedWebElement getHeader() {
        return header;
    }

    public ExtendedWebElement getFooter() {
        return footer;
    }

    public ExtendedWebElement getNavBar() {
        return navBar;
    }
}
