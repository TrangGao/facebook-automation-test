package pagetest;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@AllArgsConstructor
@Data
public class LoginPageTest {

    public void loginFacebook(String email, String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        // cau lenh load 1 page
        webDriver.get("https://www.facebook.com/");
        //
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id='email']"));
        emailElement.sendKeys(email);
        WebElement passElement = webDriver.findElement(By.xpath("//*[@id='pass']"));
        passElement.sendKeys(password);
        WebElement loginButton = webDriver.findElement(By.xpath("//*[@name='login']"));
        loginButton.click();
        Thread.sleep(10000);

        if (this.findElement(webDriver, "//*[@name='login']") == null) {
            System.out.println(email + "-" + password + "Login Thanh Cong ");
        } else {
            WebElement errorElement = this.findElement(webDriver, "//*[@id='error_box']");
            String error = "";
            if (errorElement != null) error = errorElement.getText();
            System.out.println(email + "-" + password + ":" + error);
        }
        webDriver.close();
        webDriver.quit();
    }

    public WebElement findElement(WebDriver webdriver, String xpathExpresion) {
        try {
            return webdriver.findElement(By.xpath(xpathExpresion));
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

}
