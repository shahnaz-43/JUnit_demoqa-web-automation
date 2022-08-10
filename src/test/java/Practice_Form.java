import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Practice_Form {
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
    public void formFillup() throws InterruptedException, IOException {
        driver.get("https://demoqa.com/automation-practice-form");

        //First Name
        WebElement txtFirstName = driver.findElement(By.id("firstName"));
        txtFirstName.sendKeys("Shahnaz");

        //Last Name
        WebElement txtLastName = driver.findElement(By.id("lastName"));
        txtLastName.sendKeys("Begum");

        //Email
        WebElement txtEmail = driver.findElement(By.id("userEmail"));
        txtEmail.sendKeys("naz@gmail.com");

        //Gender
        WebElement rButton = driver.findElement(By.xpath("//label[normalize-space()=\"Female\"]"));
        rButton.click();

        //Mobile no:
        WebElement txtMobile = driver.findElement(By.id("userNumber"));
        txtMobile.sendKeys("1234567892");

        //Date of Birth
        Actions actions = new Actions(driver);
        WebElement txtDate = driver.findElement(By.id("dateOfBirthInput"));
        txtDate.sendKeys(Keys.CONTROL + "a");
        //txtDate.sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.id("dateOfBirthInput")).sendKeys("11 jun 1978");
        txtDate.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        //Subject
        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        subjects.sendKeys("English");
        //products.sendKeys("SQA");
        subjects.sendKeys(Keys.ARROW_DOWN);
        subjects.sendKeys(Keys.ENTER);
        subjects.sendKeys("Chemistry");
        subjects.sendKeys(Keys.ARROW_DOWN);
        subjects.sendKeys(Keys.ENTER);
        subjects.sendKeys("Biology");
        subjects.sendKeys(Keys.ARROW_DOWN);
        subjects.sendKeys(Keys.ENTER);
        subjects.sendKeys(Keys.TAB);
        Thread.sleep(2000);

        // -- Hobbies --
        driver.findElement(By.xpath("//label[normalize-space()=\"Sports\"]")).click();
        driver.findElement(By.xpath("//label[normalize-space()=\"Reading\"]")).click();
        Thread.sleep(2000);

        // -- Upload Picture --
        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\samawat\\Desktop\\Java N211-1\\elsa.jpg");

        // -- Current Address --
        WebElement txtAddress = driver.findElement(By.id("currentAddress"));
        txtAddress.sendKeys("1555 main st");

        // --State --
        WebElement state = driver.findElement(By.xpath("//input[@id='react-select-3-input']"));
        state.sendKeys("Rajasthan");
        //subjects.sendKeys("Biology");
        state.sendKeys(Keys.ARROW_DOWN);
        state.sendKeys(Keys.ENTER);

        // -- City --
        WebElement city = driver.findElement(By.xpath("//input[@id='react-select-4-input']"));
        city.sendKeys("Jaiselmer");
        city.sendKeys(Keys.ARROW_DOWN);
        city.sendKeys(Keys.ENTER);

        // -- Submit Form --
        driver.findElement(By.id("submit")).click();
        Thread.sleep(2000);


        // --Write json file --
        JSONObject jsonObject = new JSONObject();

        String nTitle = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")).getText();
        String wName = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[2]")).getText();

        String eTitle = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[1]")).getText();
        String eMail = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[2]")).getText();

        String tGender = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[3]/td[1]")).getText();
        String gender = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[3]/td[2]")).getText();

        String tMob = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[1]")).getText();
        String mobile = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[2]")).getText();

        jsonObject.put(nTitle, wName);
        jsonObject.put(eTitle, eMail);
        jsonObject.put(tGender, gender);
        jsonObject.put(tMob, mobile);

        JSONArray infoList = new JSONArray();
        infoList.add(jsonObject);
        try {
            FileWriter file = new FileWriter("C:/Users/samawat/IdeaProjects/SeleniumPractice/src/test/resources/students.json");
            file.write(infoList.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -- close modal --
    //  driver.findElement(By.id("closeLargeModal")).click();
    // Thread.sleep(2000);

    @After
    public void closeDriver() {
        driver.close();
    }
}


