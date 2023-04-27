import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest{
    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod
    public void Setup() {
        driver = openBrowser();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

    }
    @Test
    public void Add3ItemsInCart(){
        loginPage.Login("standard_user","secret_sauce");
        inventoryPage.SortItemByValue();
        inventoryPage.AddLabsOnesie();
        inventoryPage.AddBikeLight();
        inventoryPage.AddBoltTShirt();
        Assert.assertEquals(inventoryPage.Cart(),"3");
        inventoryPage.ClickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/cart.html");
    }
    @Test
    public void AddAndRemoveProducts(){
        loginPage.Login("standard_user","secret_sauce");
        inventoryPage.AddBikeLight();
        inventoryPage.AddBoltTShirt();
        inventoryPage.ClickOnCart();
        inventoryPage.RemoveBikeLight();
        inventoryPage.RemoveBoltTShirt();
        inventoryPage.ClickOnContinueShopping();
        Assert.assertEquals(inventoryPage.visibleBikeLight.isDisplayed(),true);
        Assert.assertEquals(inventoryPage.visibleBoltTShirt.isDisplayed(),true);

    }
    @AfterMethod
    public void After(){
        driver.quit();
    }

}

