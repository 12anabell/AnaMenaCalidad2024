package com.fca.calidad.funcionales;

import static org.junit.Assert.*;
 
import org.junit.Test;
 
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;
 
		public class EditarTest {
			  private WebDriver driver;
			  private String baseUrl;
			  private boolean acceptNextAlert = true;
			  private StringBuffer verificationErrors = new StringBuffer();
			  JavascriptExecutor js;
			  @Before
			  public void setUp() throws Exception {
				  WebDriverManager.chromedriver().setup();
			    driver = new ChromeDriver();
			    baseUrl = "https://www.google.com/";
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			    js = (JavascriptExecutor) driver;
			  }
 
			  @Test
			  public void testEditar() throws Exception {
			      driver.get(baseUrl + "chrome://newtab/");
			      Thread.sleep(1000); // Pausa de 1 segundo
 
			      driver.get(baseUrl + "chrome://newtab/");
			      Thread.sleep(1000);
 
			      driver.get("https://mern-crud-mpfr.onrender.com/");
			      Thread.sleep(1000);
 
			      driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
			      Thread.sleep(1000);
 
			      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
			      Thread.sleep(1000);
 
			      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Female'])[1]/following::div[1]")).click();
			      Thread.sleep(1000);
 
			      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
			      Thread.sleep(1000);
 
			      // Verifica el texto esperado en la página
			      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Successfully updated![\\s\\S]*$"));
			  }
 
			  @After
			  public void tearDown() throws Exception {
			    driver.quit();
			    String verificationErrorString = verificationErrors.toString();
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
