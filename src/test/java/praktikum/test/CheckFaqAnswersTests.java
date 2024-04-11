package praktikum.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.setup.BrowserSetUp;
import praktikum.SamokatPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

@RunWith(Parameterized.class)
public class CheckFaqAnswersTests {

    private static final Random RANDOM = new Random();

    private final WebDriver driver;
    private final int questionIndex;

    public CheckFaqAnswersTests(String browserType, int questionIndex) {
        this.questionIndex = questionIndex;
        BrowserSetUp browserSetUp = new BrowserSetUp(browserType);
        this.driver = browserSetUp.getDriver();
    }

    @Parameterized.Parameters(name = "{index}: Browser - {0}, Question Index - {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chrome", getRandomQuestionIndex()},
                {"firefox", getRandomQuestionIndex()}
        });
    }

    private static int getRandomQuestionIndex() {
        return RANDOM.nextInt(8);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void checkFaqAnswers() {
        System.out.println("Test run with parameters: browserType=" + driver.getClass().getSimpleName() + ", questionIndex=" + questionIndex);
        SamokatPage samokatPage = new SamokatPage(driver);
        samokatPage.clickOnCookie();
        samokatPage.clickOnQuestion(questionIndex);
        Assert.assertTrue("Answer not displayed", samokatPage.getAnswer(questionIndex).isDisplayed());
    }
}