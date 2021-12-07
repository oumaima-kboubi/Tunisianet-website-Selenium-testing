import io.github.bonigarcia.wdm.WebDriverManager;
import models.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TunisianetTesting {
    public static void main(String[] args) throws InterruptedException {
        //instance du driver
        WebDriver driver;

        //instance du l'exécuteur de Js
        JavascriptExecutor js;


        //Utilisation d'un navigateur Opera
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver();

        //initialisation de l'exécuteur Js
        js = (JavascriptExecutor) driver;

        //! NB: Cette initialisation (time,TimeUnit) est "deprecated" et remplacée par (Duration)
        //! driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Configuration de l'attente implicite
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(90));

        //TODO:Naviguer vers le site de Tunisianet
        driver.get("https://www.tunisianet.com.tn");

        //TODO:Maximiser la fenêtre
        driver.manage().window().maximize();

        //TODO:Cliquer sur l'icon de User
        Thread.sleep(1000);

        //Pour trouver l'icon on peut partir du div d'id ="_desktop_user_info"
        //puis decendre dans son arborescence de deux div, le svg suivant est celui de l'icon de user
        WebElement userLoginDropdown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        userLoginDropdown.click();

        //TODO:Cliquer sur le bouton "connexion"
        Thread.sleep(1000);
        WebElement connectionButton = driver.findElement(By.cssSelector(".user-down > li > a > span"));
        connectionButton.click();

        //TODO:Cliquer sur "Pas de compte ? Créez-en un"
        Thread.sleep(1000);
        WebElement createAccountButton = driver.findElement(By.className("no-account"));
        createAccountButton.click();

        //TODO:Choisir l'option "Mme"
        Thread.sleep(1000);
        List<WebElement> genderOptions = driver.findElements(By.className("custom-radio"));
        genderOptions.get(1).click();

        //TODO:Remplir le formulaire par les données du compte
        String format = "dd/MM/yyyy";
        SimpleDateFormat dateFormater = new SimpleDateFormat(format);
        //* Changer les données pour chaque test
        //? ou bien on peut utiliser des méthodes qui génèrent des données random
        Account userAccount = new Account("oumaima", "kboubi", "ouma@kboubi.com", "123abcABC", new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1) * 365 * 22)); //22ans

        Thread.sleep(1000);
        List<WebElement> createAccountForm = driver.findElements(By.cssSelector("input.form-control"));
        Thread.sleep(1000);
        createAccountForm.get(1).sendKeys(userAccount.firstName);
        Thread.sleep(1000);
        createAccountForm.get(2).sendKeys(userAccount.name);
        Thread.sleep(1000);
        createAccountForm.get(3).sendKeys(userAccount.email);
        Thread.sleep(1000);
        createAccountForm.get(4).sendKeys(userAccount.password);
        Thread.sleep(1000);
        createAccountForm.get(5).sendKeys(dateFormater.format(userAccount.birthday));

        //TODO:Basculer vers le bas
        js.executeScript("window.scrollBy(0,250)", "");

        //TODO:Cliquer sur "Enregister"
        Thread.sleep(1000);
        WebElement submitButton = driver.findElement(By.className("form-control-submit"));
        submitButton.click();

        //TODO:Se déconnecter du site
        Thread.sleep(1000);
        userLoginDropdown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        userLoginDropdown.click();
        Thread.sleep(2000);
        WebElement logoutButton = driver.findElement(By.className("logout"));
        logoutButton.click();

        //TODO:Se connecter de nouveau au compte récemment créé
        //Cliquer sur l'icon user dans la navbar
        Thread.sleep(1000);
        userLoginDropdown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        userLoginDropdown.click();

        //Cliquer sur l'option connexion
        Thread.sleep(1000);
        connectionButton = driver.findElement(By.cssSelector(".user-down > li > a > span"));
        connectionButton.click();

        //Remplir le champs de l'email
        Thread.sleep(1000);
        WebElement emailField = driver.findElement(By.cssSelector(".form-group > div > input"));
        emailField.sendKeys(userAccount.email);

        //Remplir le champs de mots de passe
        Thread.sleep(1000);
        WebElement passwordField = driver.findElement(By.cssSelector(".form-group > div > div > input"));
        passwordField.sendKeys(userAccount.password);

        //Cliquer sur le bouton "Connexion"
        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.id("submit-login"));
        loginButton.click();

        //TODO:Chercher le laptop "PC portable MacBook M1 13.3"
        //Saisir la marque du laptop dans le champs de recherche
        Thread.sleep(2000);
        WebElement searchBarField = driver.findElement(By.className("search_query"));
        searchBarField.sendKeys("PC portable MacBook M1 13.3");

        //Cliquer sur le bouton de recherche
        Thread.sleep(2000);
        WebElement searchButton = driver.findElement(By.cssSelector("#sp-btn-search > button"));
        searchButton.click();

        //Cliquer sur le premier produits
        Thread.sleep(2000);
        List<WebElement> productsList = driver.findElements(By.className("product-title"));
        productsList.get(0).click();

        //TODO:Ajouter le produit au panier
        Thread.sleep(2000);
        WebElement addToCart = driver.findElement(By.className("add-to-cart"));
        addToCart.click();

        //TODO:Finaliser le process d'achat du produit
        Thread.sleep(2000);
        WebElement buyButton = driver.findElement(new By.ByLinkText("Commander"));
        buyButton.click();
        Thread.sleep(4000);

        //la méthode quit ferme la fenêtre ouverte actuelle sur laquelle
        // le pilote se concentre et termine la session WebDriver avec élégance
        driver.quit();


    }
}
