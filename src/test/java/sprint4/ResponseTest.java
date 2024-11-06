package sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.MainPage;

import static pageobject.MainPage.*;


@RunWith(Parameterized.class)
public class ResponseTest {
    private WebDriver driver;
    private String questionText;
    private String responseText;
    private String indexNumber;

        public ResponseTest(String questionText, String responseText, String indexNumber) {
            // Текст вопроса
            this.questionText = questionText;
            // Образец ответа
            this.responseText = responseText;
            // индекс с номером вопроса/ответа
            this.indexNumber = indexNumber;
        }

    @Parameterized.Parameters
    public static Object[][] getQuestions() {
        return new Object[][] {
                { HOW_MUCH_QUESTION, HOW_MUCH_RESPONSE, "0"},
                { SEVERAL_SCOOTER_QUESTION, SEVERAL_SCOOTER_RESPONSE, "1"},
                { RENT_TIME_QUESTION, RENT_TIME_RESPONSE, "2"},
                { ORDER_TODAY_QUESTION, ORDER_TODAY_RESPONSE, "3"},
                { CHANGING_ORDER_QUESTION, CHANGING_ORDER_RESPONSE, "4"},
                { CHARGING_SCOOTER_QUESTION, CHARGING_SCOOTER_RESPONSE, "5"},
                { CANCEL_ORDER_QUESTION, CANCEL_ORDER_RESPONSE, "6"},
                { COUNTRY_SIDE_QUESTION, COUNTRY_SIDE_RESPONSE, "7"},
        };
    }
    @Before
    public void startActivity() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox","--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void checkQuestionsTest() {
            //Создание объекта класса главной страницы
            MainPage objMainPage = new MainPage(driver);
            //Ожидание загрузки страницы
            objMainPage.waitPageLoad();
            //Подтвердить согласие на использование куки
            objMainPage.clickConfirmButton();
            //Кликнуть на вопрос
            objMainPage.clickOnQuestion(questionText);
            //Дождаться загрузки ответа
            objMainPage.waitFindResponse(indexNumber);
            //Сравнить ответ с образцом
            Assert.assertEquals("Текст не соответствует образцу",responseText,
                objMainPage.getResponseText(indexNumber));
    }

   @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}


