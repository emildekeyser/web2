package ui.view;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ValidHtmlTest
{
	private WebDriver driver;

	@Before
	public void setUp()
	{
		System.setProperty(
				"webdriver.chrome.driver",
				"/usr/local/bin/chromedriver");
		driver = new ChromeDriver();
	}

	@After
	public void clean()
	{
		driver.quit();
	}

	@Test // Voer deze test uit als je je project opgeladen hebt
	public void isValidHtml()
	{
		checkHTML("http://java.cyclone2.khleuven.be:38034/1TX10_DeKeyserEmil/");
	}
	
	@Test // Voer deze test uit als je je project opgeladen hebt
	public void isValidHtmlServlet()
	{
		checkHTML("http://java.cyclone2.khleuven.be:38034/1TX10_DeKeyserEmil/servlet");
	}
	
	@Test // Voer deze test uit als je je project opgeladen hebt
	public void isValidHtmlVoegToe()
	{
		checkHTML("http://java.cyclone2.khleuven.be:38034/1TX10_DeKeyserEmil/voeg_toe.jsp");
	}
	
	public void checkHTML(String url)
	{
		driver.get("https://validator.w3.org/#validate_by_uri+with_options");
		WebElement invulveld = driver.findElement(By.id("uri"));
		// verander naamVanJeEigenSite naar de juiste naam
		invulveld.sendKeys(url);

		Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
		dropdown.selectByValue("HTML5");

		WebElement button = driver
				.findElement(By.cssSelector(".submit_button"));
		button.click();

		WebElement pass = driver.findElement(By.cssSelector("p.success"));
		assertEquals(
				"Document checking completed. No errors or warnings to show.",
				pass.getText());
	}
}
