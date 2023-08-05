package com.solvd.carina.demo;

import com.solvd.carina.demo.gui.components.CarinaHeader;
import com.solvd.carina.demo.gui.components.CarinaNavBar;
import com.solvd.carina.demo.gui.components.CarinaSearchBar;
import com.solvd.carina.demo.gui.pages.desktop.CarinaDocsHomePage;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CarinaDocsTest implements IAbstractTest {

    private CarinaDocsHomePage carinaDocsHomePage;
    @BeforeMethod
    public void openPage() {
        carinaDocsHomePage = new CarinaDocsHomePage(getDriver());
        carinaDocsHomePage.open();
        Assert.assertTrue(carinaDocsHomePage.isPageOpened(), "Home page is not opened");
    }

    @Test
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testZebrunnerLogo() {
        CarinaHeader carinaHeader = carinaDocsHomePage.getCarinaHeader();
        Point point = carinaHeader.getZebrunnerImageLocation();

        Assert.assertEquals(carinaHeader.getZebrunnerImageUrl(), "http://zebrunner.github.io/carina/assets/logo.svg", "The image url should be: http://zebrunner.github.io/carina/assets/logo.svg");
        Assert.assertTrue(point.getX() < 300, "The zebrunner logo should be on the left side of the header");

        carinaHeader.clickOnZebrunnerImage();
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://zebrunner.github.io/carina/", "Clicking on the logo should return back to the home documentation page");
    }

    @Test
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testCarinaHeader() {
        CarinaHeader carinaHeader = carinaDocsHomePage.getCarinaHeader();
        Assert.assertEquals(carinaHeader.getHeaderText(), "Carina", "The header text should be Carina");
    }

    @Test
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testSearchBar() {
        CarinaSearchBar carinaSearchBar = carinaDocsHomePage.getCarinaHeader().getCarinaSearchBar();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(carinaSearchBar.isLabelPresent(), "Search bar should have the search icon");
        softAssert.assertTrue(carinaSearchBar.isInputPresent(), "Search bar should have an input form");
        softAssert.assertEquals(carinaSearchBar.getInputPlaceholder(), "Search", "The search bar should have a placeholder of 'Search' to indicate to the user it is a search bar");
        softAssert.assertAll();
    }

    @Test
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testGithubUrl() {
        CarinaHeader carinaHeader = carinaDocsHomePage.getCarinaHeader();
        carinaHeader.clickOnGitHubLink();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://github.com/zebrunner/carina/", "Clicking on the carina github link should redirect to the github page");
    }

    @Test
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testStickyHeader() {
        Assert.assertTrue(carinaDocsHomePage.isHeaderSticky(), "The header should be visible no matter what part of the page you are on as it is sticky");
    }

    @Test
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testNavBar() {
        CarinaNavBar carinaNavBar = carinaDocsHomePage.getCarinaNavBar();

        Assert.assertEquals(carinaNavBar.getNavBarLabelText(), "Carina", "The navigation bar should have Carina at the top of it");
        Assert.assertTrue(carinaNavBar.isNavBarPresent(), "The navigation bar with a list of items should be present on the web page");
    }

    @Test
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testNavBarLinks() {
        CarinaNavBar carinaNavBar = carinaDocsHomePage.getCarinaNavBar();

        List<ExtendedWebElement> nonNestedNavItems = carinaNavBar.getAllNonNestedNavItems();
        Assert.assertTrue(carinaNavBar.isItemActiveAndRedirected(nonNestedNavItems), "Clicking on each non nested navigation item should redirect to the corresponding page and highlight the item");

        List<ExtendedWebElement> nestedNavItems = carinaNavBar.getNestedNavItems();
        Assert.assertTrue(carinaNavBar.clickOnNestedNavItemsAndVerify(nestedNavItems), "Clicking on each nested navigation item should introduce new options. Each option should behave similarly to a non nested navigation item when visible and clicked");
    }
}
