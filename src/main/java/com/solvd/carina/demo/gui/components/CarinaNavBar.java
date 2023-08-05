package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CarinaNavBar extends AbstractUIObject {
    @FindBy(tagName = "label")
    private ExtendedWebElement navBarLabel;
    @FindBy(tagName = "ul")
    private ExtendedWebElement navBarList;
    @FindBy(tagName = "//li[contains(@class, 'md-nav__item')]")
    private List<ExtendedWebElement> navigationItems;
    public CarinaNavBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getNavBarLabelText() {
        return navBarLabel.getText();
    }

    public boolean isNavBarPresent() {
        return navBarList.isPresent() && getAllNavigationItems().size() > 0;
    }

    public List<ExtendedWebElement> getAllNavigationItems() {
        return navBarList.findExtendedWebElements(By.xpath("//li[contains(@class, 'md-nav__item')]"));
    }

    public List<ExtendedWebElement> getAllNonNestedNavItems() {
        List<ExtendedWebElement> allItems = getAllNavigationItems();
        List<ExtendedWebElement> nonNestedItems = new ArrayList<>();
        for (ExtendedWebElement item : allItems) {
            if (item.getText().length() > 0 && !item.getAttribute("class").contains("md-nav__item--nested")) {
                nonNestedItems.add(item);
            }
        }
        return nonNestedItems;
    }

    public List<ExtendedWebElement> getNestedNavItems() {
        List<ExtendedWebElement> allItems = getAllNavigationItems();
        List<ExtendedWebElement> nestedItems = new ArrayList<>();
        for (ExtendedWebElement item : allItems) {
            if (item.getAttribute("class").contains("md-nav__item--nested")) {
                nestedItems.add(item);
            }
        }
        return nestedItems;
    }

    public boolean clickOnNestedNavItemsAndVerify(List<ExtendedWebElement> nestedNavItems) {
        for (ExtendedWebElement navItem : nestedNavItems) {
            navItem.click();
        }
        List<ExtendedWebElement> hiddenNavItems = nestedNavItems.get(0).findExtendedWebElements(By.xpath("//nav//ul//li//nav//ul//li"));
        return isItemActiveAndRedirected(hiddenNavItems);
    }

    public boolean isItemActiveAndRedirected(List<ExtendedWebElement> navItems) {
        String baseUrl = "http://zebrunner.github.io/carina/";

        for (ExtendedWebElement navItem : navItems) {
            if (!navItem.getAttribute("class").contains("md-nav__item--active")) {
                String aHrefLink = navItem.getElement().findElement(By.tagName("a")).getAttribute("href");
                navItem.click();

                if (!getDriver().getCurrentUrl().equals(aHrefLink)) {
                    return false;
                }

                getDriver().navigate().back();
            }
        }
        return true;
    }
}
