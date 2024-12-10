package com.fca.calidad.funcionales;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

public class CrudTest {
    private WebDriver driver;
    private String baseUrl;
    private JavascriptExecutor js;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        baseUrl = "https://mern-crud-mpfr.onrender.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void CrudTestAgregar() {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
        driver.findElement(By.name("name")).sendKeys("Ana");
        driver.findElement(By.name("email")).sendKeys("belenmena06@gmail.com");
        driver.findElement(By.name("age")).sendKeys("22");
        driver.findElement(By.xpath("//div[contains(text(),'Gender')]/following-sibling::div")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Female')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Added successfully!')]")));
        assertEquals("Added successfully!", confirmationMessage.getText());
    }

    @Test
    public void CrudTestEditar() {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//table/tbody/tr/td[5]/button[contains(text(),'Edit')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
        driver.findElement(By.name("name")).sendKeys("Ana Mena");
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("belenmena@gmail.com");
        driver.findElement(By.name("age")).clear();
        driver.findElement(By.name("age")).sendKeys("23");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        WebElement updatedName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[1]/td[1]")));
        assertEquals("Ana Mena", updatedName.getText());
    }

    @Test
    public void CrudTestEliminar() {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//table/tbody/tr/td[5]/button[contains(text(),'Delete')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmationPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Are you sure you want to delete')]")));
        assertNotNull(confirmationPopup);

        driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
    }

}+
@After
public void tearDown() throws Exception {
  driver.quit();
  String verificationErrorString = verificationErrorString.toString();
  if (!"".equals(verificationErrorString)) {
    fail(verificationErrorString);
  }
}

private boolean isElementPresent(By by) {
  try {
    driver.findElement(by);
    return true;
  } catch (NoSuchElementException e) {
    return false;
  }
}

private boolean isAlertPresent() {
  try {
    driver.switchTo().alert();
    return true;
  } catch (NoAlertPresentException e) {
    return false;
  }
}

private String closeAlertAndGetItsText() {
  try {
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    if (acceptNextAlert) {
      alert.accept();
    } else {
      alert.dismiss();
    }
    return alertText;
  } finally {
    acceptNextAlert = true;
  }
}
}
   
   
