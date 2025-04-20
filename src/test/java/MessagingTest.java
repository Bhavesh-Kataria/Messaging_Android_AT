import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class MessagingTest {
    public static AndroidDriver driver;
    public static DesiredCapabilities dcs;
    public static Properties prop;

    @BeforeClass
    public void setUp(){
        dcs = new DesiredCapabilities();
        dcs.setCapability("udid","731fc626");
        dcs.setCapability("deviceName", "Android Device");
        dcs.setCapability("platformName", "Android");
        dcs.setCapability("platformVersion", "12.0");
        dcs.setCapability("automationName", "UiAutomator2");
        dcs.setCapability("noReset","true");
        dcs.setCapability(  "autoGrantPermissions","true");
        dcs.setCapability("appPackage","com.simplemobiletools.smsmessenger");
        dcs.setCapability("appActivity","com.simplemobiletools.smsmessenger.activities.MainActivity");
        dcs.setCapability("adbExecTimeout", 30000); // Increase timeout to 30 seconds (default is 20s)
        dcs.setCapability("newCommandTimeout", 600);  // Increase timeout for commands
        dcs.setCapability("uiautomator2ServerInstallTimeout", 120000);
        dcs.setCapability("uiautomator2ServerLaunchTimeout", 120000);

        try{
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),dcs);
            FileInputStream fis = new FileInputStream("/Users/topb/Desktop/Automation Testing/App Testing/MessagingAT/src/config/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (MalformedURLException mue) {
            mue.getStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sendMessage(){
        int x = 967;
        int y = 2282;
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(x, y)).perform();
        driver.findElement(By.id("com.simplemobiletools.smsmessenger:id/new_conversation_address")).sendKeys(prop.getProperty("number"));
        driver.findElement(By.id("com.simplemobiletools.smsmessenger:id/contacts_letter_fastscroller")).click();
//        driver.findElement(By.id("com.simplemobiletools.smsmessenger:id/thread_type_message")).sendKeys("Hello User , this is Bhavesh here");
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText")).sendKeys("Hello User , this is Bhavesh here");
        driver.findElement(By.id("com.simplemobiletools.smsmessenger:id/thread_send_message")).click();
    }

}
