package com.Project;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_Project {
	WebDriver driver;
	long endTime;
	long startTime;
	long totalTime;
	WebDriverWait wait;

	@BeforeSuite
	public void projectBeggining() {
		startTime = System.currentTimeMillis();
		System.out.println("Project Beggining");
	}

	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.Chrome.driver", "D:\\Web-drivers\\chromedriver.exe");
		ChromeOptions opti = new ChromeOptions();
		opti.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(opti);
		driver.navigate().to("https://www.google.com/");
		driver.manage().window().maximize();
		System.out.println("Browser Opened");
	}

	@BeforeClass
	public void openJquery() {
		WebElement searchBox = driver.findElement(By.id("APjFqb"));
		searchBox.sendKeys("https://jqueryui.com/");
		searchBox.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//h3[ text()='jQuery UI']")).click();
		System.out.println("Jqurey link Opened");
	}

	@Test
	public void drag_drop() {
		driver.findElement(By.linkText("Droppable")).click();
		WebElement frame = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		driver.switchTo().frame(frame);
		WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement drop = driver.findElement(By.xpath("//div[@id='droppable']"));
		Actions act = new Actions(driver);
		act.dragAndDrop(drag, drop).perform();
		System.out.println("Drag And Drop Completed");
		driver.switchTo().defaultContent();
	}

	@Test(priority = 1)
	public void checkBox_Radio() {
		driver.findElement(By.linkText("Checkboxradio")).click();
		WebElement frame2 = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		driver.switchTo().frame(frame2);
		WebElement london = driver.findElement(By.xpath("/html/body/div/fieldset[1]/label[3]"));
		london.click();
		WebElement room = driver.findElement(By.xpath("/html/body/div/fieldset[2]/label[4]/span[2]"));
		room.click();
		WebElement bed = driver.findElement(By.xpath("/html/body/div/fieldset[3]/label[4]/span[2]"));
		bed.click();
		WebElement room2 = driver.findElement(By.xpath("/html/body/div/fieldset[2]/label[3]/span[1]"));
		room2.click();
		room.click();
		System.out.println("Bed is selected " + "bed.isSelected()");
		driver.switchTo().defaultContent();
		System.out.println("CheckBox Completed ");
	}

	@Test(priority = 2)
	public void textBox() {
		WebElement text = driver.findElement(By.xpath("//*[@id=\"main\"]/form/label/span[2]/input"));
		text.click();
		text.sendKeys("Autocomplete" + Keys.ENTER);
		System.out.println("TextBox Completed");
	}

	@Test(priority = 3)
	public void autoSuggesion() {
		driver.findElement(By.xpath("//a[text()='Autocomplete']")).click();
		WebElement frame = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		driver.switchTo().frame(frame);
		WebElement auto = driver.findElement(By.xpath("//input[@id=\"tags\"]"));
		auto.sendKeys("a");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li"));
		int count = list.size();
		System.out.println("No of list in AutoCompleted:-" + count);
		list.get(count - 3).click();
		driver.switchTo().defaultContent();
		System.out.println("AutoSuggesion Completed");
	}

	@Test(priority = 4)
	public void mouseAction() throws InterruptedException {
		driver.navigate().refresh();
		Actions mouse = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//a[text()='Position']"));
		mouse.scrollToElement(element).perform();
		Thread.sleep(1000);
		element.click();
		System.out.println("Mouse Completed");
	}

	@Test(priority = 5)
	public void multi_Select() {
		driver.navigate().refresh();

		driver.navigate().to("https://chercher.tech/practice/practice-dropdowns-selenium-webdriver");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement multi = driver.findElement(By.xpath("//select[@id=\"second\"]"));
		Select select = new Select(multi);
		select.selectByIndex(0);
		select.selectByIndex(1);
		select.selectByIndex(2);
		select.deselectAll();
		select.selectByValue("burger");
		select.selectByVisibleText("Bonda");
		System.out.println("Multi Select Completed");

		/*
		 * //With same Jqurey .link
		 * driver.findElement(By.xpath("//a[text()='Selectable']")).click(); WebElement
		 * frame = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		 * driver.switchTo().frame(frame); List<WebElement> multiselect =
		 * driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		 * System.out.println(multiselect.size()); Actions actions = new
		 * Actions(driver);
		 * 
		 * actions.keyDown(Keys.CONTROL)
		 * .click(multiselect.get(2)).click(multiselect.get(3)).click(multiselect.get(4)
		 * ).perform(); driver.switchTo().defaultContent();
		 * 
		 * //we have two types actions.clickAndHold(multiselect.get(1));
		 * actions.clickAndHold(multiselect.get(2)); actions.perform();
		 * driver.switchTo().defaultContent();
		 */

	}

	@Test(priority = 6)
	public void dropdown() {
		driver.navigate().refresh();
		WebElement option = driver.findElement(By.xpath("//select[@id=\"first\"]"));
		option.click();
		Select ddown = new Select(option);
		ddown.selectByIndex(2);
		WebElement option2 = driver.findElement(By.xpath("//select[@id=\"animals\"]"));
		option2.click();
		Select ddown2 = new Select(option2);
		ddown2.selectByVisibleText("Avatar");
		System.out.println("Drop Down Completed");
	}

	@Test(priority = 7)
	public void keyboardActions() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().to("https://extendsclass.com/text-compare.html");
		WebElement sourceArea = driver.findElement(By.xpath("//*[@id=\"dropZone\"]/div[2]"));
		Actions actions = new Actions(driver);
		actions.keyDown(sourceArea, Keys.CONTROL).sendKeys("a").sendKeys("x").perform();
		WebElement dropArea = driver.findElement(By.xpath("//*[@id=\"dropZone2\"]/div[2]/div/div[6]"));
		actions.keyDown(dropArea, Keys.CONTROL).sendKeys("v").perform();
		actions.keyDown(dropArea, Keys.CONTROL).sendKeys("a").sendKeys("x").perform();
		System.out.println("KeyBoard Actions Completed");
	}

	@AfterClass
	public void findAllLinks() {
		driver.navigate().to("https://jqueryui.com/");
		List<WebElement> list = driver.findElements(By.tagName("a"));
		int count = list.size();
		System.out.println("Number of Link tag is:-" + count);
		for (int i = 1; i <= count - 1; i++) {
			System.out.println("name of link is:-" + i + list.get(i).getText());
			System.out.println("Find All Link Completed");
		}
	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

	@AfterSuite
	public void projectEnd() {
		System.out.println("Project Completed");
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Total time Taken for testing Project is:-" + totalTime);
	}
}
