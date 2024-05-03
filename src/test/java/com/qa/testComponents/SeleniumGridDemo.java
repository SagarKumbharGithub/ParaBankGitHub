package com.qa.testComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridDemo {
	private static WebDriver driver;

    public static void main(String[] args) throws IOException, InterruptedException {
        // Start Selenium Grid Hub programmatically
        startSeleniumGridHub();
        
        // Start Selenium Grid Node programmatically
        startSeleniumGridNode();

        // Open Google URL
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        driver.get("https://www.google.com");
        System.out.println("Page title is: " + driver.getTitle());

        // Close the browser
     //   driver.quit();
    }

    public static void startSeleniumGridHub() throws IOException, InterruptedException {
        String[] command = {"cmd", "/c", "start/min", "cmd", "/k", "java -jar selenium-server-4.20.0.jar hub"};
        Runtime.getRuntime().exec(command);
        Thread.sleep(5000); // Wait for the hub to start
    }

    public static void startSeleniumGridNode() throws IOException, InterruptedException {
        String[] command = {"cmd", "/c", "start/min", "cmd", "/k", "java -jar selenium-server-4.20.0.jar node --port " + PortProber.findFreePort()};
        Runtime.getRuntime().exec(command);
        Thread.sleep(5000); // Wait for the node to start
    }
}