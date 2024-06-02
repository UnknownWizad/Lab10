import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class App10 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFacebookLogin() throws Exception {

        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        String user = "selvagjhybrjtu6hjuanesh12p@gmail.com";
        String pass = "Selva@123";

        usernameField.sendKeys(user);
        passwordField.sendKeys(pass);

        String location = "src/Excel1/user.xlsx";
        XSSFWorkbook wbook = new XSSFWorkbook(location);
        XSSFSheet sheet = wbook.getSheetAt(0);
        boolean flag = false;
        for (int i = 2; i <= 6; i++) {
            XSSFRow row1 = sheet.getRow(i);
            for (int j = 0; j < 1; j++) {
                XSSFCell cell1 = row1.getCell(j);
                XSSFCell cell2 = row1.getCell(j + 1);
                String value1 = cell1.getStringCellValue();
                String value2 = cell2.getStringCellValue();
                if (value1.equals(user) && value2.equals(pass)) {
                    flag = true;
                    break;
                }
            }
        }
        wbook.close();
        System.out.print(flag);
        if (flag) {
            loginButton.click();
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl,
                    "https://www.facebook.com/checkpoint/1501092823525282/?next=https%3A%2F%2Fwww.facebook.com%2F%3Fsk%3Dwelcome",
                    "Login");
        } else {
            loginButton.click();
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl,
                    "https://www.facebook.com/checkpoint/1501092823525282/?next=https%3A%2F%2Fwww.facebook.com%2F%3Fsk%3Dwelcome",
                    "Login");
        }

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}