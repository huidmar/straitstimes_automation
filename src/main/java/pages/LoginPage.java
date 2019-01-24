package pages;

import org.openqa.selenium.By;

public class LoginPage {

    public static By loginIdField =
            By.xpath("//input[@id='j_username']");

    public static By loginPasswordField =
            By.xpath("//input[@id='j_password']");

    public static By loginButton =
            By.xpath("//button[@class='btn']");
}
