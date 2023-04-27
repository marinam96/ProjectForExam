import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOutTest extends BaseTest {
    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CheckOutPage checkOutPage;

    @BeforeMethod
    public void Setup() {
        driver = openBrowser();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkOutPage = new CheckOutPage(driver);
    }
    @Test
    public void ItemTotalPrice(){
        loginPage.Login("standard_user","secret_sauce");
        inventoryPage.SortItemByValue();
        inventoryPage.AddLabsOnesie();
        inventoryPage.AddBikeLight();
        inventoryPage.AddBoltTShirt();
        inventoryPage.ClickOnCart();
        inventoryPage.ClickOnCheckOut();
        checkOutPage.EnterFirstName("Marina");
        checkOutPage.EnterLastName("Matic");
        checkOutPage.EnterZipCode("11300");
        checkOutPage.ClickOnContinueButton();
        checkOutPage.ItemPrice();
        Assert.assertEquals(checkOutPage.ItemPrice(), "Item total: $33.97");
    }
    @Test
    public void TotalPriceTest(){
        loginPage.Login("standard_user","secret_sauce");
        inventoryPage.SortItemByValue();
        inventoryPage.AddLabsOnesie();
        inventoryPage.AddBikeLight();
        inventoryPage.AddBoltTShirt();
        inventoryPage.ClickOnCart();
        inventoryPage.ClickOnCheckOut();
        checkOutPage.EnterFirstName("Marina");
        checkOutPage.EnterLastName("Matic");
        checkOutPage.EnterZipCode("11300");
        checkOutPage.ClickOnContinueButton();
        checkOutPage.TotalPrice();
        Assert.assertEquals(checkOutPage.TotalPrice(),"Total: $36.69");

    }
    @Test
    public void OrderCompleted(){
        loginPage.Login("standard_user","secret_sauce");
        inventoryPage.SortItemByValue();
        inventoryPage.AddLabsOnesie();
        inventoryPage.AddBikeLight();
        inventoryPage.AddBoltTShirt();
        inventoryPage.ClickOnCart();
        inventoryPage.ClickOnCheckOut();
        checkOutPage.EnterFirstName("Petar");
        checkOutPage.EnterLastName("Radosavljevic");
        checkOutPage.EnterZipCode("11030");
        checkOutPage.ClickOnContinueButton();
        checkOutPage.ClickOnFinishButton();
        checkOutPage.OrderCompleted();
        Assert.assertEquals(checkOutPage.OrderCompleted(),"Thank you for your order!");
    }
    @AfterMethod
    public void After(){
        driver.quit();
    }
}


