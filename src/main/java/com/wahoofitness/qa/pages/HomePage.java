package com.wahoofitness.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wahoofitness.qa.base.TestBase;

public class HomePage extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(xpath="//button[text()='Accept']")
	WebElement acceptCookiesBtn;
	
	@FindBy(xpath="//a[@href='/devices']/span")
	WebElement shopCategory;
	
	@FindBy(xpath="(//a[text()='All Products'])[1]")
	WebElement allProductType;
	
	@FindBy(xpath="//li[@class='item']/div[2]//form/button[@title='Add to Cart']")
	List<WebElement> productCount;
	
	
	@FindBy(xpath="//div[@class='add-to-cart']//button")
	WebElement addToCartBtn;
	
	

	
	@FindBy(xpath="//div[@class='header-links count']/a[2]")
	WebElement cartIcon;
	
	@FindBy(xpath="(//li[@class='item product product-item'])[1]/div/div/div[3]//a")
	WebElement removeLinkFirstItemOfYourCart;
	
	@FindBy(xpath="//footer[@class='modal-footer']/button[2]")
	WebElement itemRemovalAcceptBtn;
	
	@FindBy(xpath="//span[text()='View and Edit Cart']")
	WebElement viewAndEditCartLink;
	
	@FindBy(xpath="//div[@class='control qty']/input")
	WebElement quantitytextField;
	
	@FindBy(xpath="//button[@title='Update Cart']/span")
	WebElement updateCartBtn;
	
	@FindBy(xpath="//button[@title='Proceed to Checkout']/span")
	WebElement proceedToCheckoutBtn;
	
	@FindBy(xpath="//h1[text()='Checkout']")
	WebElement checkoutOutTitle;
	
	@FindBy(xpath="//button[@title=' Place Order']")
	WebElement placeOrderBtn;
	
	
	@FindBy(xpath="//div[@id='customer-email-error']")
	WebElement emptyEmailFieldError;
	
	
	@FindBy(xpath="//td[contains(text(),'Express Shipping')]/preceding-sibling::td[2]/input")
	WebElement expressShippingRadioBtn;
	
	@FindBy(xpath="//div[@class='control _with-tooltip']/input[@id='customer-email']")
	WebElement emailAddressField;
	
	@FindBy(xpath="//div[@name='shippingAddress.firstname']//input[@name='firstname']")
	WebElement firstNameField;
	
	@FindBy(xpath="//div[@name='shippingAddress.lastname']//input[@name='lastname']")
	WebElement lastNameField;
	
	@FindBy(xpath="//div[@name='shippingAddress.street.0']//input[@name='street[0]']")
	WebElement streetField;
	
	@FindBy(xpath="//div[@name='shippingAddress.city']//input[@name='city']")
	WebElement cityField;
	
	@FindBy(xpath="//div[@name='shippingAddress.postcode']//input[@name='postcode']")
	WebElement postcodeField;
	
	@FindBy(xpath="//div[@name='shippingAddress.telephone']//input[@name='telephone']")
	WebElement telephoneField;
	
	@FindBy(xpath="//input[@name='cardnumber']")
	WebElement cardNumberField;
	
	@FindBy(xpath="//input[@name='exp-date']")
	WebElement cardExpireDateField;
	
	@FindBy(xpath="//input[@name='cvc']")
	WebElement cardCVCField;
	
	
			
	@FindBy(xpath="(//div[@class='message message-error error'])[1]/div")
	WebElement paymentDeclinedMsg;		
	
	//Initializing the Page Objects:
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	
	public void acceptCookies() throws InterruptedException{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptCookiesBtn);
	}
	
	
	public void openProductCategory() throws InterruptedException{
		
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", shopCategory);
		Thread.sleep(5000);
	
	}
	
	public int productInStockCount(){
		int size = productCount.size();
	    return size;
	}
	
	public void clickAnyInStockItemUsingIndex(int itemnumber) throws InterruptedException{
		WebElement element1 = driver.findElement(By.xpath("(//button[@title='Add to Cart'])["+itemnumber+"]/ancestor::li/div[1]/a"));
		
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
	}
	
	public void clickAddToCartBtn() {
		addToCartBtn.click();
	}
	
	
	
	public void selectFirstColorForItem() {
		
		if(driver.findElements(By.xpath("//span[text()='Color']/../following-sibling::div/select")).size()!=0){
			Select dropdown = new Select(driver.findElement(By.xpath("//span[text()='Color']/../following-sibling::div/select")));
			dropdown.selectByIndex(1);
		}
		
	}
	
	public void removeFirstItemOfYourCart() {
		removeLinkFirstItemOfYourCart.click();
	}
	
	public void clickCartIcon() {
		if(cartIcon.getAttribute("class").contains("active")==false){
		cartIcon.click();
		}
	}
	
	
	public void clickitemRemovalAcceptBtn() {
			itemRemovalAcceptBtn.click();
	}
	
	public void clickViewAndEditCartLink() {
		//viewAndEditCartLink.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewAndEditCartLink);
}
	
	
	public void enterItemQuantity(String str) {
		quantitytextField.clear();
		quantitytextField.sendKeys(str);
}
	
	
	public void clickUpdateCartBtn() {
		updateCartBtn.click();
}
	
	public void clickProceedToCheckoutBtn() {
		proceedToCheckoutBtn.click();
}
	
	
	
	
	
	
	public void waitTillCheckoutPageLoaded() {
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.visibilityOf(checkoutOutTitle));
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
		
}
	
	public void clickPlaceOrderBtn() {
       WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderBtn);
		//placeOrderBtn.click();
}
	
	public void clickExpressShippingRadioBtn() {
		expressShippingRadioBtn.click();
}
	
	
	public String getEmptyEmailErrorMsg() {
		return emptyEmailFieldError.getText();
}
	
	public void enterEmailAddressInShippingAddress(String str) {
		emailAddressField.sendKeys(str);
}
	
	public void enterFirstNameInShippingAddress(String str) {
		firstNameField.sendKeys(str);
}
	
	public void enterLastNameInShippingAddress(String str) {
		lastNameField.sendKeys(str);
}
	
	
	public void enterStreetInShippingAddress(String str) {
		streetField.sendKeys(str);
}
	
	public void enterCityInShippingAddress(String str) {
		cityField.sendKeys(str);
}
	
	public void enterPostcodeInShippingAddress(String str) {
		postcodeField.sendKeys(str);
}
	
	
	public void enterTelephoneInShippingAddress(String str) {
		telephoneField.sendKeys(str);
}
	
	
	public void enterCardNumberInPaymentMethod(String str) {
		cardNumberField.sendKeys(str);
}
	
	
	public void enterCardExpireInPaymentMethod(String str) {
		cardExpireDateField.sendKeys(str);
}
	
	public void enterCardCVCInPaymentMethod(String str) {
		cardCVCField.sendKeys(str);
}
	
	public String paymentDeclinedErrorMsg(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOf(paymentDeclinedMsg));
		return paymentDeclinedMsg.getText();
}
	

	
	
	
	

	

}
