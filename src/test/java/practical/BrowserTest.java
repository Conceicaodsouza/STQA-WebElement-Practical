package practical;

import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class BrowserTest {

    @Test
    public void browserTest() {

        WebDriver driver = null;

        try {
            // Configure Chromium for headless execution
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            // Specify the Chromium browser installed in GitHub Codespaces
            options.setBinary("/usr/bin/chromium");

            // Start the browser
            driver = new ChromeDriver(options);

            // Open the local HTML file
            String filePath = System.getProperty("user.dir")
                    + "/src/test/resources/web-elements.html";

            driver.get("file://" + filePath);

            // Verify page title
            assertEquals(
                    "Web Element Interaction Practical",
                    driver.getTitle()
            );

            // Locate the text box
            WebElement nameInput = driver.findElement(By.id("name"));

            // Enter text into the text box
            nameInput.sendKeys("Conceicao");

            // Verify the entered text
            assertEquals(
                    "Conceicao",
                    nameInput.getAttribute("value")
            );

            // Locate the country dropdown
            WebElement countryDropdown =
                    driver.findElement(By.id("country"));

            Select countrySelect =
                    new Select(countryDropdown);

            // Select India from the dropdown
            countrySelect.selectByVisibleText("India");

            // Verify the selected option
            assertEquals(
                    "India",
                    countrySelect.getFirstSelectedOption().getText()
            );

        } finally {

            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}