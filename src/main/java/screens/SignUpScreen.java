package screens;

import entities.User;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindByAllSet;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SignUpScreen extends BaseScreen{
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Create a password']")
    private AndroidElement password;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Next, Tap next to continue to the next step']")
    private AndroidElement nextButton;
    @AndroidFindBy(xpath = "//*[@text='Full name']")
    private AndroidElement fullName;
    @AndroidFindBy(xpath = "//*[@text='Age']")
    private AndroidElement age;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Female button']")
    private AndroidElement femaleButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Male button']")
    private AndroidElement maleButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Specify another button']")
    private AndroidElement anotherGenderButton;
    @AndroidFindBy(id = "com.pinterest:id/current_country")
    private AndroidElement selectCountry;
    @AndroidFindBy(xpath = "//*[@resource-id='com.pinterest:id/country_name']")
    private List<AndroidElement> countries;
    @AndroidFindBy(id = "com.pinterest:id/country_next_button")
    private AndroidElement countryNextButton;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'of')]/android.view.ViewGroup/android.widget.TextView")
    private List<WebElement> themes;
    @AndroidFindBy(xpath = "//*[@text='Next']")
    private AndroidElement next;

    public SignUpScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void signUp(User user){
        action.inputText(password, user.password());
        action.tapButton(nextButton);
        action.inputText(fullName, user.fullName());
        action.tapButton(nextButton);
        action.inputText(age, user.age().toString());
        action.tapButton(nextButton);
        selectGender(user.gender());
        action.tapButton(selectCountry);
        selectCountry(user.country());
        action.tapButton(countryNextButton);
        selectThemes(5);
        action.tapButton(next);

    }

    private void selectGender(String gender){
        switch (gender){
            case "Female":
                action.tapButton(femaleButton);
                break;
            case "Male":
                action.tapButton(maleButton);
                break;
            case "Another":
                action.tapButton(anotherGenderButton);
                break;
            default:
                logger.error("Incorrect gender inputted.");
        }
    }
    private void selectCountry(String country){
        for (int i = 0; i < countries.size(); i++) {
            String countryName = countries.get(i).getAttribute("text");
            if(countryName.contains(country)){
                action.tapButton(countries.get(i));
                return;
            }
        }
        action.swipe("DOWN");
        selectCountry(country);
    }

    private void selectThemes(int number) {
        action.waitList(themes);
        for (int i = 0; i < number; i++) {
            action.tapButton(themes.get(i));
        }
    }

}
