package utils;

import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.imagecomparison.*;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ActionWithElements {
    AndroidDriver driver;
    Logger logger;
    WebDriverWait wait;

    public ActionWithElements(AndroidDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 30);
        logger = Logger.getLogger(getClass());
    }

    public void inputText(WebElement element, String text){
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            element.clear();
            element.sendKeys(text);
            logger.info("Successful inputting " + text);
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during inputting " + text);
        }

    }
    public void tapButton(WebElement element){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            String getText = element.getText();
            element.click();
            logger.info("Successful clicking on " + getText);
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during clicking");
        }


    }
    public boolean isDisplayedElement(WebElement element){
        boolean result = false;
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            String getText = element.getText();
            result = element.isDisplayed();
            logger.info("Successful displaying checking displaying element " + getText);
            return result;
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during checking displaying element");
            return result;
        }
    }
    public void selectElementFromDropDown(WebElement element, String value){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            Select dropDownValue = new Select(element);
            dropDownValue.selectByVisibleText(value);
            logger.info("Successful selecting from drop down value " + value);
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during selecting from drop down");
        }

    }

    public void setCheckBox(WebElement element, Boolean state){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            String getText = element.getText();
            if(state){
                if(!element.isSelected()){
                    element.click();
                }
            }
            if(!state){
                if(element.isSelected()){
                    element.click();
                }
            }
            logger.info("Successful setting check-box " + getText + " to state " + state);
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during checking check-box.");
        }
    }

    public String getText(WebElement element){
        String result = null;
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            result = element.getText();
            logger.info("Successful getting text " + result);
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during getting text.");
        }
        return result;
    }

    public boolean isElementSelected(WebElement element){
        boolean result = false;
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            String getText = element.getText();
            if (element.isSelected()){
                result = true;
            }
            logger.info("Successful checking if element " + getText + "is selected");
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during checking if element is selected");
        }
        return result;
    }

    public boolean isElementEnabled(WebElement element){
        boolean result = false;
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            if (element.isEnabled()){
                result = true;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during checking if element is enabled");
        }
        return result;
    }
    public void selectRadioButton(List<WebElement> elements, String text){
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            List<WebElement> radioButtons = elements;
            int numberOfRadioButtons = radioButtons.size();
            for (int i = 0; i < numberOfRadioButtons; i++) {
                if (radioButtons.get(i).getAttribute("value").equals(text)) {
                    if (!radioButtons.get(i).isSelected()) {
                        radioButtons.get(i).click();
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during selection Radio Button");
        }

    }

    public boolean isElementDisplayed(WebElement element){
        boolean result = false;
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            result = element.isDisplayed();
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error("Something went wrong during checking element displaying.");
        }
        return result;
    }

    public void longPressAndSelectOption(WebElement firstElement, WebElement secondElement){

        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(firstElement));

            TouchAction action = new TouchAction((PerformsTouchActions) driver);

            PointOption firstPointOption = new PointOption();
            firstPointOption.withCoordinates(
                    firstElement.getLocation().getX() + firstElement.getSize().width / 2,
                    firstElement.getLocation().getY() + firstElement.getSize().height / 2);
            action.
                    longPress(LongPressOptions.longPressOptions().
                            withPosition(firstPointOption).
                            withDuration(Duration.ofSeconds(3))).
                    perform();

            PointOption secondPointOption = new PointOption().withCoordinates(secondElement.getLocation().getX(), secondElement.getLocation().getY());

            action.
                    moveTo(secondPointOption).
                    waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).
                    release().
                    perform();

            logger.info("Long press was performed successfully.");
        }
        catch (Exception ex){
            logger.error("Something went wrong during long press.");
        }
    }

    public void swipe(String direction) {
        Dimension size = driver.manage().window().getSize();

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;
        PointOption startPoint = new PointOption();
        PointOption movePoint = new PointOption();

        switch (direction) {
            case "RIGHT":
                startY = (int) (size.height / 2);
                startX = (int) (size.width * 0.90);
                endX = (int) (size.width * 0.05);
                startPoint.withCoordinates(startX, startY);
                movePoint.withCoordinates(endX, startY);
                new TouchAction((PerformsTouchActions) driver)
                        .press(startPoint)
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(movePoint)
                        .release()
                        .perform();
                break;

            case "LEFT":
                startY = (int) (size.height / 2);
                startX = (int) (size.width * 0.05);
                endX = (int) (size.width * 0.90);
                startPoint.withCoordinates(startX, startY);
                movePoint.withCoordinates(endX, startY);
                new TouchAction((PerformsTouchActions) driver)
                        .press(startPoint)
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(movePoint)
                        .release()
                        .perform();

                break;

            case "UP":
                endY = (int) (size.height * 0.70);
                startY = (int) (size.height * 0.30);
                startX = (size.width / 2);

                startPoint.withCoordinates(startX, startY);
                movePoint.withCoordinates(startX, endY);

                new TouchAction((PerformsTouchActions) driver)
                        .press(startPoint)
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(movePoint)
                        .release()
                        .perform();
                break;

            case "DOWN":
                startY = (int) (size.height * 0.80);
                endY = (int) (size.height * 0.10);
                startX = (size.width / 2);
                startPoint.withCoordinates(startX, startY);
                movePoint.withCoordinates(startX, endY);
                new TouchAction((PerformsTouchActions) driver)
                        .press(startPoint)
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(movePoint)
                        .release()
                        .perform();

                break;
        }
    }

    public void waitList(List<WebElement> elements){
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public OccurrenceMatchingResult compareImages(File screenshot, File originalImage) throws IOException {
        /*FeaturesMatchingResult result = driver.
                matchImagesFeatures(screenshot, originalImage, new FeaturesMatchingOptions()
                        .withDetectorName(FeatureDetector.ORB)
                        .withGoodMatchesFactor(40)
                        .withMatchFunc(MatchingFunction.BRUTE_FORCE_HAMMING)
                        .withEnabledVisualization());

         */

        OccurrenceMatchingResult result = driver
                .findImageOccurrence(screenshot, originalImage, new OccurrenceMatchingOptions()
                        .withEnabledVisualization());
        return result;
    }

    public void makeScreenshot(String name) throws InterruptedException, IOException {

        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        Files.move(screenshot, new File("out/screenshots/" + name + ".png"));
    }
}