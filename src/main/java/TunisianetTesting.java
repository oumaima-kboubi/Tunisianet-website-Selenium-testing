import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;

import java.time.Duration;


public class TunisianetTesting {
    //instance du driver
    WebDriver driver;


    @Before
    public void initDriver() {
        //Utilisation d'un navigateur Opera
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver();
        //NB: Cette initialisation (time,TimeUnit) est "deprecated" et remplacée par (Duration)
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Configuration de l'attente implicite
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(90));
    }

    @Test
    public void buyMacCaseTesting() throws InterruptedException {

        //TODO:Naviguer vers le site de Tunisianet
        driver.get("https://www.tunisianet.com.tn");

        //TODO:Cliquer sur l'icon de User
        Thread.sleep(1000);
        //Pour trouver l'icon on peut partir du div d'id ="_desktop_user_info"
        //puis decendre dans son arborescence de deux div, le svg suivant est celui de l'icon de user
        WebElement userLoginDropdown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        userLoginDropdown.click();

        //TODO:Cliquer sur le bouton "connexion"
        Thread.sleep(1000);
        WebElement boutonConnexion = driver.findElement(By.cssSelector(".user-down > li > a > span" ));
        boutonConnexion.click();

        //TODO:Cliquer sur "Pas de compte ? Créez-en un"
        Thread.sleep(1000);
        WebElement boutonCreerCompte = driver.findElement(By.className("no-account"));
        //Verifier le bouton
        Assert.assertEquals("Pas de compte ? Créez-en un",boutonCreerCompte.findElement(By.cssSelector("*")).getText());
        boutonCreerCompte.click();
    }

}
