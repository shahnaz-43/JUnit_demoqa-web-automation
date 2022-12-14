import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class JunitTutorial {

    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void getTitle() {
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();
        Assert.assertEquals(title, "ToolsQA");
        //Assert.assertTrue(title.contains("ToolsqA"));
    }

    @Test
    public void writeText() {
        driver.get("https://demoqa.com/text-box");
        // driver.findElement(By.id("userName")).sendKeys("kanan");
        //driver.findElement(By.cssSelector("[type=text]")).sendKeys("kanan");
        // driver.findElement(By.cssSelector("[id=userName]")).sendKeys("kanan");
        // driver.findElement(By.className("form-control")).sendKeys("kanan");

        WebElement txtFirstName = driver.findElement(By.id("userName"));
        txtFirstName.sendKeys("Kanan");

        // WebElement txtEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
        //  txtEmail.sendKeys("kirei.kanan@gmail.com");

        WebElement txtEmail2 = driver.findElement(By.cssSelector("#userEmail"));
        txtEmail2.sendKeys("abc@gmail.com");

        // List<WebElement> button = driver.findElements(By.tagName("submit"));
        // button.get(1).click();

        driver.findElements(By.tagName("button")).get(1).click();
    }

    @Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        
        driver.findElement(By.id("confirmButton")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().dismiss();

        // String text = driver.findElement(By.className("text-success")).getText();
        //Assert.assertTrue(text.contains("Cancel"));

        driver.findElement(By.id("promtButton")).click();
        driver.switchTo().alert().sendKeys("kanan");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        String text = driver.findElement(By.id("promptResult")).getText();
        Assert.assertTrue(text.contains("kanan"));
        Thread.sleep(2000);
    }

    @Test
    public void selectDate() throws InterruptedException {
        driver.get("https://demoqa.com/date-picker");
        Actions actions = new Actions(driver);
        WebElement txtDate = driver.findElement(By.id("datePickerMonthYearInput"));
        /*actions.moveToElement(txtDate).
                doubleClick().click().
                keyDown(Keys.BACK_SPACE).
                keyUp(Keys.BACK_SPACE).perform();*/
        txtDate.sendKeys(Keys.CONTROL + "a");
        txtDate.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("07/27/2022");
        txtDate.sendKeys(Keys.ENTER);

    }

    @Test
    public void keyboardEvent() throws InterruptedException {
        driver.get("https://www.google.com/");
        WebElement searchElement = driver.findElement(By.name("q"));
        Actions action = new Actions(driver);
        action.moveToElement(searchElement);
        action.keyDown(Keys.SHIFT);
        action.sendKeys("Selenium Webdriver")
                .keyUp(Keys.SHIFT)
                .doubleClick().click()
                .contextClick()
                .perform();

        Thread.sleep(5000);
    }

    @Test
    public void selectDropdown() {
        driver.get("https://demoqa.com/select-menu");
        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByValue("3");
        Select cars = new Select(driver.findElement(By.id("cars")));
        if (cars.isMultiple()) {
            cars.selectByValue("volvo");
            cars.selectByValue("saab");
        }
    }

    @Test
    public void modalDialog() throws InterruptedException {
        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showSmallModal")).click();
        Thread.sleep(2000);
        String text = driver.findElement(By.className("modal-body")).getText();
        System.out.println(text);
        driver.findElement(By.id("closeSmallModal")).click();
    }

    @Test
    public void mouseHover() {
        driver.get("https://green.edu.bd/");
        Actions actions = new Actions(driver);
        List<WebElement> list = driver.findElements(By.xpath("//a[contains(text(),\"About Us\")]"));
        actions.moveToElement(list.get(2)).perform();

    }

    @Test
    public void actionClick() {
        driver.get("https://demoqa.com/buttons");

        /*Actions actions=new Actions(driver);
        actions.doubleClick(driver.findElement(By.id("doubleClickBtn"))).perform();
        actions.contextClick(driver.findElement(By.id("rightClickBtn"))).perform();*/

        List<WebElement> buttons = driver.findElements(By.cssSelector("[type=button]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(buttons.get(1)).perform();
        actions.contextClick(buttons.get(2)).perform();

    }

    @Test
    public void takeScreenShot() throws IOException {
        driver.get("https://demoqa.com");
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
        File DestFile = new File(fileWithPath);
        //FileUtils.copyFile(screenshotFile, DestFile);
    }


    @Test
    public void uploadFile() {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("uploadFile")).sendKeys("C:\\Users\\samawat\\Desktop\\Java N211-1\\elsa.jpg");

    }


    @Test
    public void downloadFile() {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("downloadButton")).click();
    }

    @Test
    public void handleTab() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        Thread.sleep(3000);
        ArrayList<String> wd = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(wd.get(1));
        System.out.println("New tab title" + driver.getTitle());
        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(text, "This is a sample page");
        driver.close();
        driver.switchTo().window(wd.get(0));
    }
    @Test
    public void handleWindow() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        //Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("windowButton")));
        driver.findElement(By.id(("windowButton"))).click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                String text = driver.findElement(By.id("sampleHeading")).getText();
                Assert.assertTrue(text.contains("This is a sample page"));
            }
        }
        driver.close();
    }

    @Test
    public void dataScrap(){
        driver.get("https://demoqa.com/webtables");
        WebElement table = driver.findElement(By.className("rt-tbody"));
        List<WebElement> allRows = table.findElements(By.className("rt-tr"));
        int i=0;
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.className("rt-td"));
            for (WebElement cell : cells) {
                i++;
                System.out.println("num["+i+"] "+ cell.getText());

            }
        }
    }
    @Test
    public void handleIframe(){
        driver.get("https://demoqa.com/frames");
        driver.switchTo().frame("frame2");
        String text= driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertTrue(text.contains("This is a sample page"));
        driver.switchTo().defaultContent();
    }

    @After
    public void closeDriver() {
        //driver.close();
    }
}
