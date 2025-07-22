package base;

import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class BaseTest {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void initializeDriver() {
        initializeDriver("chrome"); // Browser por defecto
    }

    public static void initializeDriver(String browserName) {
        WebDriver driver = DriverManager.getDriver(browserName);
        setDriver(driver);
    }

    public static void quitDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    protected WebDriver driver() {
        return getDriver();
    }
}
