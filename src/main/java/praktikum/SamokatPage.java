package praktikum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;


public class SamokatPage {
     WebDriver driver;

    public SamokatPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnCookie() {
        System.out.println("Clicking on cookie button...");
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }
    public void clickOnQuestion(int number) {
        System.out.println("Clicking on question number: " + number);
        try {
            WebElement questionElement = driver.findElement(By.id("accordion__heading-" + number));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", questionElement);
            Thread.sleep(1000); // Add a small delay to ensure the element is fully scrolled into view
            questionElement.click();
            System.out.println("Successfully clicked on question number: " + number);
        } catch (Exception e) {
            System.out.println("Failed to click on question number: " + number);

        }
    }

    public WebElement getAnswer(int number) {
        System.out.println("Waiting for answer number: " + number);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + number)));
            System.out.println("Answer number " + number + " is visible.");
            return answerElement;
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for answer number " + number + " to be visible.");
            return null;
        }
    }

    public void clickOnHeaderButtonOrder() {

        driver.findElement(By.className("Button_Button__ra12g")).click();
    }
    public void clickOnSecondButtonOrder() {
        WebElement button = driver.findElement(By.className("Home_FinishButton__1_cWm"));
        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", button);
        // Click the element
        button.click();
    }

    public void typeName(String name) {
        driver.findElement(By.xpath("//*[@placeholder='* Имя']")).sendKeys(name);
    }

    public void typeSurname(String surname) {
        driver.findElement(By.xpath("//*[@placeholder='* Фамилия']")).sendKeys(surname);
    }

    public void typeAdress(String adresse) {
        driver.findElement(By.xpath("//*[@placeholder='* Адрес: куда привезти заказ']")).sendKeys(adresse);
    }

    public void clickOnMetro() {
        driver.findElement(By.xpath("//*[@placeholder='* Станция метро']")).click();
    }

    public void clickOnStreet() {
        driver.findElement(By.xpath("//div[text()='Черкизовская']")).click();
    }

    public void typePhoneNumber(String phoneNumber) {
        driver.findElement(By.xpath("//*[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys(phoneNumber);
    }

    public void clickOnTheButtonNext() {
        driver.findElement(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM")).click();
    }

    public void clickOnDeliveryDateAndSelectionOfOrderDate() {
        driver.findElement(By.xpath("//*[@placeholder='* Когда привезти самокат']")).click();
        driver.findElement(By.cssSelector(".react-datepicker__day--001")).click();
    }
    public void clickOnPeriodOfRental() {
        driver.findElement(By.xpath("//div[text()='* Срок аренды']")).click();
    }

    public void selectionOfTheRentalPeriod() {
        driver.findElement(By.xpath("//div[text()='сутки']")).click();
    }

    public void selectionDropdownMenu() {
        driver.findElement(By.id("black")).click();
    }

    public void typeComment(String comment) {
        driver.findElement(By.xpath("//*[@placeholder='Комментарий для курьера']")).sendKeys(comment);
    }

    public void clickOnButtonOrderTwo() {
        driver.findElement(By.xpath("(//*[text()='Заказать'])[2]")).click();
    }

    public void clickOnYes() {
        driver.findElement(By.xpath("(//*[@class='Order_Modal__YZ-d3']//button)[2]")).click();
    }
    public void waitForOrderNumberAndLog() {
        // Wait for the order modal to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement orderModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));

        // Extract and log the order number
        String orderText = orderModal.findElement(By.className("Order_Text__2broi")).getText();
        String[] orderTextParts = orderText.split(":");
        String orderNumber = orderTextParts[1].trim(); // Extract the order number
        System.out.println("Order Number: " + orderNumber);
    }
    // Custom assertion method
    public void assertLookStatusButtonIsDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement lookStatusButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Посмотреть статус']")));
            Assert.assertTrue("'Look status' button is not displayed", lookStatusButton.isDisplayed());
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Кнопка 'Посмотреть статус' не отобразилась по истечении периода ожидания.");
            Assert.fail("Failed to find 'Look status' button within the specified timeout."); // Fail the test explicitly
        }
    }

}
