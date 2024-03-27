package praktikum.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.SamokatPage;
import praktikum.setup.BrowserSetUp;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MakeAnOrderTests {

    private WebDriver driver;

    private final String browserType;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String comment;

    public MakeAnOrderTests(String browserType, String name, String surname, String address,
                            String phoneNumber, String comment) {
        this.browserType = browserType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "{index}: Browser - {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"firefox", "Петр", "Фамильичко","Санкт-Петербург" ,"79056666666","HelloSamokat1" },
                {"chrome", "Вася", "Фамилиянов","Москва" ,"79056666665","Коммент" }
        });
    }

    @Before
    public void setup () {
        BrowserSetUp browserSetUp = new BrowserSetUp(browserType);
        driver = browserSetUp.getDriver();
        System.out.println("Browser opened successfully: " + browserType);
        System.out.println("Navigated to URL: " + driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }

    @Test
    public void orderWithHeaderButton() {
        System.out.println("Executing test with parameters:");
        System.out.println("Browser Type: " + browserType);
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Comment: " + comment);

        SamokatPage samokatPage = new SamokatPage(driver);
        // Click on the cookie button if it appears
        System.out.println("Clicking on the cookie button if it appears");
        samokatPage.clickOnCookie();
        // Click on the order button
        System.out.println("Clicking on the order button");
        samokatPage.clickOnHeaderButtonOrder();
        // Enter common fields
        enterCommonFields(samokatPage);
        // Order process
        orderProcess(samokatPage);
    }

    @Test
    public void orderWithSecondButton() {
        System.out.println("Executing test with parameters (using second button):");
        System.out.println("Browser Type: " + browserType);
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Comment: " + comment);

        SamokatPage samokatPage = new SamokatPage(driver);
        // Click on the cookie button if it appears
        System.out.println("Clicking on the cookie button if it appears");
        samokatPage.clickOnCookie();
        // Click on the order button
        System.out.println("Clicking on the order button");
        samokatPage.clickOnSecondButtonOrder();
        // Enter common fields
        enterCommonFields(samokatPage);
        // Order process
        orderProcess(samokatPage);
    }

    private void enterCommonFields(SamokatPage samokatPage) {
        // Enter name
        System.out.println("Entering name: " + name);
        samokatPage.typeName(name);
        // Enter surname
        System.out.println("Entering surname: " + surname);
        samokatPage.typeSurname(surname);
        // Enter address
        System.out.println("Entering address: " + address);
        samokatPage.typeAdress(address);
        // Click on the metro field
        System.out.println("Clicking on the metro field");
        samokatPage.clickOnMetro();
        // Click on the street
        System.out.println("Clicking on the street");
        samokatPage.clickOnStreet();
        // Enter phone number
        System.out.println("Entering phone number: " + phoneNumber);
        samokatPage.typePhoneNumber(phoneNumber);
        // Click on the next button
        System.out.println("Clicking on the next button");
        samokatPage.clickOnTheButtonNext();
    }

    private void orderProcess(SamokatPage samokatPage) {
        // Click on delivery date and select order date
        System.out.println("Clicking on delivery date and selecting order date");
        samokatPage.clickOnDeliveryDateAndSelectionOfOrderDate();
        // Click on rental period
        System.out.println("Clicking on rental period");
        samokatPage.clickOnPeriodOfRental();
        // Select rental period
        System.out.println("Selecting rental period");
        samokatPage.selectionOfTheRentalPeriod();
        // Select from dropdown menu
        System.out.println("Selecting from dropdown menu");
        samokatPage.selectionDropdownMenu();
        // Add comment
        System.out.println("Adding comment: " + comment);
        samokatPage.typeComment(comment);
        // Click order button
        System.out.println("Clicking on order button");
        samokatPage.clickOnButtonOrderTwo();
        // Confirm order
        System.out.println("Confirming order");
        samokatPage.clickOnYes();
        // Assert if the "Look status" button is displayed
        System.out.println("Asserting if the 'Look status' button is displayed");
        samokatPage.assertLookStatusButtonIsDisplayed();
        System.out.println("'Look status' button is displayed");
        samokatPage.waitForOrderNumberAndLog();
    }

}

