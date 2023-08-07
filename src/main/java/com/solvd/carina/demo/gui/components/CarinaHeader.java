package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaHeader extends AbstractUIObject {

    @FindBy(xpath = "//div[contains(@class, 'md-header-nav__title')]//span")
    private ExtendedWebElement header;

    @FindBy(xpath = "//a[contains(@class, 'md-header-nav__button md-logo')]//img")
    private ExtendedWebElement zebrunnerImage;

    @FindBy(className = "md-search__form")
    private CarinaSearchBar carinaSearchBar;

    @FindBy(xpath = "//a[contains(@class, 'md-source')]")
    private ExtendedWebElement githubLink;

    public CarinaHeader(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getZebrunnerImageUrl() {
        return zebrunnerImage.getAttribute("src");
    }

    public Point getZebrunnerImageLocation() {
        return zebrunnerImage.getLocation();
    }

    public void clickOnZebrunnerImage() {
        zebrunnerImage.click();
    }

    public ExtendedWebElement getHeader() {
        return header;
    }

    public String getHeaderText() {
        return header.getText();
    }

    public CarinaSearchBar getCarinaSearchBar() {
        return carinaSearchBar;
    }

    public void clickOnGitHubLink() {
        githubLink.clickIfPresent();
    }
}
