import com.github.javafaker.Faker;
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
import screens.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BaseTests {
    public static String accessKey = "jJzzxaR3GN2JCoLqEoQT";
    public static String userName = "yanakudryk3";
    public AndroidDriver<AndroidElement> driver;
    public Faker usFaker;

    protected WelcomeScreen welcomeScreen;
    protected SignUpScreen signUpScreen;
    protected LoginScreen loginScreen;
    protected MainScreen mainScreen;
    protected CreateBoardScreen createBoardScreen;
    protected EditProfileScreen editProfileScreen;
    protected SavedScreen savedScreen;
    protected SettingsScreen settingsScreen;




    @BeforeClass
    public void setUp() throws MalformedURLException {
        driver = getEmulatorAndroidDriver();
        //driver = getBSAndroidDriver();
        welcomeScreen = new WelcomeScreen(driver);
        signUpScreen = new SignUpScreen(driver);
        loginScreen = new LoginScreen(driver);
        mainScreen = new MainScreen(driver);
        createBoardScreen = new CreateBoardScreen(driver);
        editProfileScreen = new EditProfileScreen(driver);
        savedScreen = new SavedScreen(driver);
        settingsScreen = new SettingsScreen(driver);

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        usFaker = new Faker(new Locale("en-US"));
    }

    private AndroidDriver<AndroidElement> getBSAndroidDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");
        caps.setCapability("project", "My First Project");
        caps.setCapability("build", "My First Build");
        caps.setCapability("name", "Bstack-[Java] Sample Test");
        caps.setCapability("app", "bs://f654530288a029f94948961ab06cf5ed0877b397");

       return new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
    }

    private AndroidDriver<AndroidElement> getEmulatorAndroidDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("avd", "Pixel_3_API_29");
        caps.setCapability("deviceName","Pixel 3 API 29");
        caps.setCapability("platformVersion","10.0");
        caps.setCapability("platformName", "Android");
        caps.setCapability("app","C:\\Users\\akade\\IdeaProjects\\pinterest-android-automation\\apk\\com.pinterest_7.43.0_7438140.apk");
        caps.setCapability("appPackage", "com.pinterest");
        caps.setCapability("appActivity", "com.pinterest.activity.PinterestActivity");
        return new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
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
