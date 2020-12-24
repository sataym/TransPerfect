package com.wahoofitness.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wahoofitness.qa.base.TestBase;
import com.wahoofitness.qa.pages.HomePage;
import com.wahoofitness.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	HomePage homePage;
	TestUtil testUtil;

	public HomePageTest() {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		testUtil = new TestUtil();
		homePage = new HomePage();
	}
	
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() throws InterruptedException{
		
		//Accept cookies
		Thread.sleep(5000);
		homePage.acceptCookies();
		Thread.sleep(2000);
		
		//open all product 
		homePage.openProductCategory();
		
		Thread.sleep(6000);
		
		int min = 1;
		int max = homePage.productInStockCount();
		System.out.println(max);
		
		int int_randomItem = (int)(Math.random()*(max - min + 1) + min);
		
		//slect first random product
		homePage.clickAnyInStockItemUsingIndex(int_randomItem);
		Thread.sleep(5000);
		//Select first color if available
		homePage.selectFirstColorForItem();
		Thread.sleep(5000);
		//add first product to cart
		homePage.clickAddToCartBtn();
		
        homePage.openProductCategory();
		
		Thread.sleep(6000);
		
		int int_randomItem2 = (int)(Math.random()*(max - min + 1) + min);
		
		//select secomd product
		homePage.clickAnyInStockItemUsingIndex(int_randomItem2);
		Thread.sleep(5000);
		//select color for second product if available
		homePage.selectFirstColorForItem();
		Thread.sleep(5000);
		//Add second product to cart
		homePage.clickAddToCartBtn();
		Thread.sleep(6000);
		
		homePage.clickCartIcon();
		
		Thread.sleep(5000);
		//remove first product from cart
		homePage.removeFirstItemOfYourCart();
		Thread.sleep(6000);
		
		homePage.clickitemRemovalAcceptBtn();
		Thread.sleep(6000);
		
        homePage.clickCartIcon();
		
		Thread.sleep(5000);
		//Edit cart 
		homePage.clickViewAndEditCartLink();
		Thread.sleep(6000);
		//edit product quantity
		homePage.enterItemQuantity("2");
		Thread.sleep(3000);
		//click update cart
		homePage.clickUpdateCartBtn();
		
        Thread.sleep(3000);
		//procced to checkout
		homePage.clickProceedToCheckoutBtn();
		
		Thread.sleep(3000);
		
		homePage.waitTillCheckoutPageLoaded();
		Thread.sleep(3000);
		//error validation for null value in shippment details
		homePage.clickPlaceOrderBtn();
		Thread.sleep(2000);
		Assert.assertEquals("This is a required field.", homePage.getEmptyEmailErrorMsg());
		Thread.sleep(2000);
		//select express shiping
		homePage.clickExpressShippingRadioBtn();
		Thread.sleep(2000);
		
		//enter shipping details
		homePage.enterEmailAddressInShippingAddress("someone@whocares.com");
		homePage.enterFirstNameInShippingAddress("Test");
		homePage.enterLastNameInShippingAddress("Tester");
		homePage.enterStreetInShippingAddress("Comandante Izarduy 67");
		homePage.enterCityInShippingAddress("Barcelona");
		homePage.enterPostcodeInShippingAddress("08940");
		homePage.enterTelephoneInShippingAddress("5555555555");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Secure card payment input frame']")));
		//enter payment details
		homePage.enterCardNumberInPaymentMethod("4111111111111111");
		homePage.enterCardExpireInPaymentMethod("08/24");
		homePage.enterCardCVCInPaymentMethod("111");
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		homePage.clickPlaceOrderBtn();
		Thread.sleep(4000);
		//payment decline validation
		Assert.assertEquals("Your card was declined. Your request was in live mode, but used a known test card.", homePage.paymentDeclinedErrorMsg());
		
		
		
		
	}
	
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	

}
