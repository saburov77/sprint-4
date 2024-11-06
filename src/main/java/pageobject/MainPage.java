package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage {
    //Драйвер
    private WebDriver driver;
    //Локатор для картитнки самоката
    private final By scooterImg = By.xpath(".//img[@src='/assets/blueprint.png']");
    //Локатор для кнопки куки
    private final By cookButton = By.id("rcc-confirm-button");
    //Локатор для кнопки "Заказать" сверху страницы
    private final By topOrderButton = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button");
    //Локатор для кнопки "Заказать" в середине страницы
    private final By middleOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Паттерн локатора вопросов
    private static final String FAQ_QUESTION_PATTERN = ".//div[contains(@id, 'accordion__heading') and text() = '%s']";
    //Паттерн локатора ответов
    private static final String FAQ_RESPONSE_PATTERN = ".//div[contains(@id, 'accordion__panel-%s')]";
    //Константы вопросов/ответов
    public static final String HOW_MUCH_QUESTION = "Сколько это стоит? И как оплатить?";
    public static final String HOW_MUCH_RESPONSE = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String SEVERAL_SCOOTER_QUESTION = "Хочу сразу несколько самокатов! Так можно?";
    public static final String SEVERAL_SCOOTER_RESPONSE = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String RENT_TIME_QUESTION = "Как рассчитывается время аренды?";
    public static final String RENT_TIME_RESPONSE = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String ORDER_TODAY_QUESTION = "Можно ли заказать самокат прямо на сегодня?";
    public static final String ORDER_TODAY_RESPONSE = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String CHANGING_ORDER_QUESTION = "Можно ли продлить заказ или вернуть самокат раньше?";
    public static final String CHANGING_ORDER_RESPONSE = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String CHARGING_SCOOTER_QUESTION = "Вы привозите зарядку вместе с самокатом?";
    public static final String CHARGING_SCOOTER_RESPONSE = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String CANCEL_ORDER_QUESTION = "Можно ли отменить заказ?";
    public static final String CANCEL_ORDER_RESPONSE = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String COUNTRY_SIDE_QUESTION = "Я жизу за МКАДом, привезёте?";
    public static final String COUNTRY_SIDE_RESPONSE = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";


    //Конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //Ждём загрузки главной страницы по загрузке картинки с самокатом, так как дольше всего грузится
    public void waitPageLoad()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(scooterImg));
    }
    //Подтвердить согласие на использование куки
    public void clickConfirmButton() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(cookButton).click();
    }
    //Кликнуть на соответствующий вопрос
    public void clickOnQuestion(String questionText) {
        String questionLocator = String.format(FAQ_QUESTION_PATTERN, questionText);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(questionLocator))));
        driver.findElement(By.xpath(questionLocator)).click();
    }
    //Дождаться загрузки ответа
    public void waitFindResponse (String indexNumber) {
        String responseLocator = String.format(FAQ_RESPONSE_PATTERN, indexNumber);
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(responseLocator))));}
    //Прочитать текст ответа
    public String getResponseText (String indexNumber) {
        String responseLocator = String.format(FAQ_RESPONSE_PATTERN, indexNumber);
        return driver.findElement(By.xpath(responseLocator)).getText();
    }

    //Кликнуть по кнопке Заказать в середине страницы
    public void clickOnMiddleOrderButton() {
        driver.findElement(middleOrderButton).click();
    }
    //Кликнуть по кнопке Заказать сверху страницы
    public void clickOnTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

}
