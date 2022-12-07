package com.tutorialsninja.testsuite;

import com.tutorialsninja.pages.DesktopsPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LaptopsAndNotebooksPage;
import com.tutorialsninja.testbase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends BaseTest {

    HomePage homePage = new HomePage();
    DesktopsPage desktopsPage = new DesktopsPage();

    LaptopsAndNotebooksPage laptopsAndNotebooksPage = new LaptopsAndNotebooksPage();


    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        // 1.1 Mouse hover on Desktops Tab.and click
        homePage.clickOnDesktops();
        //1.2 Click on “Show All Desktops”
        homePage.clickOnShowAllDesktopMenu();
        //1.3 Select Sort By position "Name: Z to A"
        desktopsPage.sortByZtoA();
        //1.4 Verify the Product will arrange in Descending order
        Assert.assertEquals(desktopsPage.beforeSortInOrderList(), desktopsPage.afterSortInOrderList(), "Product not sorted into Z to A order");
        System.out.println(desktopsPage.beforeSortInOrderList());
        System.out.println(desktopsPage.afterSortInOrderList());
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        desktopsPage.currencyMenu();
        //2.1 Mouse hover on Desktops Tab. and click
        homePage.clickOnDesktops();

        //2.2 Click on “Show All Desktops”
        homePage.clickOnShowAllDesktopMenu();

        //2.3 Select Sort By position "Name: A to Z"
        desktopsPage.sortByAtoZ();

        //2.4 Select product “HP LP3065”
        desktopsPage.clickOnHpLaptop();

        //2.5 Verify the Text "HP LP3065"
        verifyText(By.xpath("//h1[contains(text(),'HP LP3065')]"),"HP LP3065", "Wrong Product Displayed");

        //2.6 Select Delivery Date "2022-11-30"
        desktopsPage.deliveryDatePicker();

        //2.7.Enter Qty "1” using Select class.
        desktopsPage.quantityAdd();

        //2.8 Click on “Add to Cart” button
        Thread.sleep(1000);
        desktopsPage.addToCart();

        //2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Thread.sleep(1000);
        Assert.assertEquals(laptopsAndNotebooksPage.getsuccessMessageAfterClickingAddToCartButton(), "Success: You have added HP LP3065 to your shopping cart!\n" + "×");

        //2.10 Click on link “shopping cart” display into success message
        desktopsPage.clickOnShoppingCart();

        //2.11 Verify the text "Shopping Cart"
        Thread.sleep(1000);
        verifyText(By.xpath("//a[contains(text(),'Shopping Cart')]"), "Shopping Cart", "Page not displayed" );

        //2.12 Verify the Product name "HP LP3065"
        Thread.sleep(800);
        verifyText(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"), "HP LP3065", "Wrong Product Displayed");

        //2.13 Verify the Delivery Date "2022-11-30"
        Thread.sleep(800);
        verifyText(By.xpath("//small[contains(text(),'Delivery Date: 2022-11-30')]"), "Delivery Date: 2022-11-30", "Wrong Delivery Date");

        //2.14 Verify the Model "Product21"
        Thread.sleep(800);
        verifyText(By.xpath("//td[contains(text(),'Product 21')]"), "Product 21", "Wrong Product Displayed");

        //2.15 Verify the Todat "£74.73"
        Thread.sleep(800);
        verifyText(By.xpath("//tbody/tr[1]/td[6]"), "£74.73", "Wrong Price");

    }

}
