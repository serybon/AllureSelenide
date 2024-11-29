package pageobject.selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Epic("Сatalog functionality")
@Feature("Duck sorting")
public class CatalogTest extends TestBase {

    @Test
    @Description("Method compares the list of products after clicking the button")
    public void sortByNameFullComparison() {
        CatalogPage.clickLinkRubberDuckNavigationPanel();
        List<String> listBefore = CatalogPage.getListOfTitles();
        Collections.sort(listBefore);
        CatalogPage.clickSortByNameButton();
        List<String> listAfterClickSortByNameButton = CatalogPage.getListOfTitles();
        Assert.assertTrue(CatalogPage.CompareLists(listBefore, listAfterClickSortByNameButton));
    }
}
