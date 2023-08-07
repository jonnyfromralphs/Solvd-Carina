package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaSearchBar extends AbstractUIObject {
    @FindBy(tagName = "input")
    private ExtendedWebElement input;

    @FindBy(tagName = "label")
    private ExtendedWebElement label;

    public CarinaSearchBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getInput() {
        return input;
    }

    public String getInputPlaceholder() {
        return input.getAttribute("placeholder");
    }

    public boolean isInputPresent() {
        return input.isElementPresent();
    }

    public boolean isLabelPresent() {
        return label.isElementPresent();
    }
}
