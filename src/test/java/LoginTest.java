import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    ChromeDriver driver;
    LoginPage loginPage;
    @BeforeMethod
    public void Setup() {
        driver = openBrowser();
        loginPage = new LoginPage(driver);
    }
    @Test
    public void LoginWithCorrectData() {
        loginPage.Login("standard_user","secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void LoginWithWrongPass(){
        loginPage.Login("standard_user","qwerty");
        Assert.assertEquals(loginPage.getTextMessage(),"Epic sadface: Username and password do not match any user in this service");
    }
    @Test
    public void LoginwithWrongUserAndPass(){
        loginPage.Login("ytrewq","qwerty");
        Assert.assertEquals(loginPage.getTextMessage(),"Epic sadface: Username and password do not match any user in this service");
    }
    @Test
    public void LoginWithWrongUser(){
        loginPage.Login("user","secret_sauce");
        Assert.assertEquals(loginPage.getTextMessage(),"Epic sadface: Username and password do not match any user in this service");

    }
    @Test
    public void LoginWithNoData(){
        loginPage.Login("","");
        Assert.assertEquals(loginPage.getTextMessage(),"Epic sadface: Username is required");
    }
    @AfterMethod
    public void After(){
        driver.quit();
    }
}
