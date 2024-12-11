package pageobject.selenide;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Epic("Сatalog functionality")
@Feature("Duck sorting")
public class CatalogTest extends TestBase {


    @Severity(SeverityLevel.BLOCKER)
    @Description("Method compares the list of products after clicking the button")
    @Test(description = "Sort by name and then check results")
    public void sortByNameFullComparison() {
        CatalogPage.clickLinkRubberDuckNavigationPanel();
        List<String> listBefore = CatalogPage.getListOfTitles();
        Collections.sort(listBefore);
        CatalogPage.clickSortByNameButton();
        List<String> listAfterClickSortByNameButton = CatalogPage.getListOfTitles();
        Assert.assertTrue(CatalogPage.CompareLists(listBefore, listAfterClickSortByNameButton));
    }
}
