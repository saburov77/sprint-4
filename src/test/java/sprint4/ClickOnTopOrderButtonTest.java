package sprint4;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageobject.MainPage;
import pageobject.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;

public class ClickOnTopOrderButtonTest {
    private static WebDriver driver;

    @Before
    public void startActivity() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru");
    }
        @Test
        public void checkTopOrderButtonTest () {
            //Создание объекта класса главной страницы
            MainPage objMainPage = new MainPage(driver);
            //Создание объекта класса страницы заказа
            OrderPage objOrderPage = new OrderPage(driver);
            //Ожидание загрузки главной страницы
            objMainPage.waitPageLoad();
            //Подтвердить согласие на использование куки
            objMainPage.clickConfirmButton();
            //Кликнуть по кнопке "Заказать" сверху страницы
            objMainPage.clickOnTopOrderButton();
            //Проверить, открыта ли форма "Для кого самокат" для этого проверить соответствующий текст в заголовке формы
            MatcherAssert.assertThat("Заголовок формы не соответствует ожидаемому", objOrderPage.getHeaderForm(), containsString("Для кого самокат"));


        }
    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
    }
