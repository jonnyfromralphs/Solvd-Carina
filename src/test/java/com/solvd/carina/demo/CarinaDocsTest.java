package com.solvd.carina.demo;

import com.solvd.carina.demo.gui.pages.desktop.CarinaDocsHomePage;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CarinaDocsTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testZebrunnerLogo() {
        CarinaDocsHomePage carinaDocsHomePage = new CarinaDocsHomePage(getDriver());
        carinaDocsHomePage.open();
        Assert.assertTrue(carinaDocsHomePage.isPageOpened(), "Home page is not opened");

        ExtendedWebElement zebrunnerImage = carinaDocsHomePage.getZebrunnerImage();
        Point point = zebrunnerImage.getLocation();

        String expectedImgUrl = "http://zebrunner.github.io/carina/assets/logo.svg";
        String actualImgUrl = zebrunnerImage.getAttribute("src");

        Assert.assertEquals(actualImgUrl, expectedImgUrl, "The image url should be: http://zebrunner.github.io/carina/assets/logo.svg");
        Assert.assertTrue(point.getX() < 300, "The zebrunner logo should be on the left side of the header");

        zebrunnerImage.click();
        String expextedNewUrl = "http://zebrunner.github.io/carina/";
        String actualNewUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualNewUrl, expextedNewUrl, "Clicking on the logo should return back to the home documentation page");
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testCarinaHeader() {
        CarinaDocsHomePage carinaDocsHomePage = new CarinaDocsHomePage(getDriver());
        carinaDocsHomePage.open();
        Assert.assertTrue(carinaDocsHomePage.isPageOpened(), "Home page is not opened");

        ExtendedWebElement carinaHeader = carinaDocsHomePage.getCarinaHeader();
        String actualCarinaHeaderText = carinaHeader.getText();
        String expectedCarinaHeaderText = "Carina";

        Assert.assertEquals(actualCarinaHeaderText, expectedCarinaHeaderText, "The header text should be Carina");
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testSearchBar() {
        CarinaDocsHomePage carinaDocsHomePage = new CarinaDocsHomePage(getDriver());
        carinaDocsHomePage.open();
        Assert.assertTrue(carinaDocsHomePage.isPageOpened(), "Home page is not opened");

        ExtendedWebElement searchBar = carinaDocsHomePage.getSearchBar();
        Assert.assertTrue(searchBar.isElementPresent(), "The search bar should be present on the web page");

        ExtendedWebElement label = searchBar.findExtendedWebElement(By.tagName("label"));
        ExtendedWebElement input = searchBar.findExtendedWebElement(By.tagName("input"));
        String actualPlaceholder = input.getAttribute("placeholder");
        String expectedPlaceholder = "Search";

        Assert.assertTrue(label.isElementPresent(), "Search bar should have the search icon");
        Assert.assertTrue(input.isElementPresent(), "Search bar should have an input form");
        Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "The search bar should have a placeholder of 'Search' to indicate to the user it is a search bar");
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testGithubUrl() {
        CarinaDocsHomePage carinaDocsHomePage = new CarinaDocsHomePage(getDriver());
        carinaDocsHomePage.open();
        Assert.assertTrue(carinaDocsHomePage.isPageOpened(), "Home page is not opened");

        ExtendedWebElement githubLink = carinaDocsHomePage.getGithubLink();
        Assert.assertTrue(githubLink.isElementPresent(), "The carina documentation should have a link to the github repository");

        githubLink.click();
        String expextedNewUrl = "https://github.com/zebrunner/carina/";
        String actualNewUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualNewUrl, expextedNewUrl, "Clicking on the carina github link should redirect to the github page");
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testStickyHeader() {
        CarinaDocsHomePage carinaDocsHomePage = new CarinaDocsHomePage(getDriver());
        carinaDocsHomePage.open();
        Assert.assertTrue(carinaDocsHomePage.isPageOpened(), "Home page is not opened");

        ExtendedWebElement header = carinaDocsHomePage.getHeader();
        ExtendedWebElement footer = carinaDocsHomePage.getFooter();
        footer.scrollTo();

        Assert.assertTrue(header.isVisible(), "The header should be visible no matter what part of the page you are on as it is sticky");
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testNavBar() {
        CarinaDocsHomePage carinaDocsHomePage = new CarinaDocsHomePage(getDriver());
        carinaDocsHomePage.open();
        Assert.assertTrue(carinaDocsHomePage.isPageOpened(), "Home page is not opened");

        ExtendedWebElement navBar = carinaDocsHomePage.getNavBar();
        Assert.assertTrue(navBar.isElementPresent());

        ExtendedWebElement navBarTitle = navBar.findExtendedWebElement(By.xpath("//label[contains(@class, 'md-nav__title')]"));
        String actualNavBarTitle = navBarTitle.getText();
        String expectedNavBarTitle = "Carina";

        Assert.assertEquals(actualNavBarTitle, expectedNavBarTitle, "The navigation bar should have Carina at the top of it");

        ExtendedWebElement navBarList = carinaDocsHomePage.findExtendedWebElement(By.className("md-nav__title"));
        Assert.assertTrue(navBarList.isElementPresent(), "Navigation bar should have a list of items");
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testNavBarLinks() {
        CarinaDocsHomePage carinaDocsHomePage = new CarinaDocsHomePage(getDriver());
        carinaDocsHomePage.open();
        Assert.assertTrue(carinaDocsHomePage.isPageOpened(), "Home page is not opened");

        List<ExtendedWebElement> listOfNavItems = carinaDocsHomePage.findExtendedWebElements(By.className("md-nav__item"));
        for (ExtendedWebElement item : listOfNavItems) {

            String className = item.getAttribute("class");

            if (className.contains("md-nav__item--nested")) {
                item.click();
            }
            else {
                ExtendedWebElement itemLink = item.findExtendedWebElement(By.tagName("a"));
                String expectedUrl = itemLink.getAttribute("href");

                itemLink.click();
                String actualUrl = getDriver().getCurrentUrl();
                Assert.assertEquals(actualUrl, expectedUrl, "Clicking on each link in the navigation bar should redirect you to the proper url");
            }

        }
    }
}
