import com.google.common.io.Files;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import screens.CreateServerScreen;
import screens.RegisterScreen;
import screens.WelcomeScreen;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTests {
    public static String accessKey = "jJzzxaR3GN2JCoLqEoQT";
    public static String userName = "yanakudryk3";
    public AndroidDriver<AndroidElement> driver;
    protected WelcomeScreen welcomeScreen;
    protected RegisterScreen registerScreen;
    protected CreateServerScreen createServerScreen;


    @BeforeClass
    public void setUp() throws MalformedURLException {
        driver = getAndroidDriver();
        welcomeScreen = new WelcomeScreen(driver);
        registerScreen = new RegisterScreen(driver);
        createServerScreen = new CreateServerScreen(driver);
    }

    private AndroidDriver<AndroidElement> getAndroidDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");
        caps.setCapability("project", "My First Project");
        caps.setCapability("build", "My First Build");
        caps.setCapability("name", "Bstack-[Java] Sample Test");
        caps.setCapability("app", "bs://9b764648509aec59489db7f3d18bd1fad2299123");

       return new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
    }


    @AfterMethod
    public void recordFailure(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File(
                        "out/screenshots/" +
                                result.getName() +
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern(" dd-MM-yyyy HH_mm_ss")) +
                                ".png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
