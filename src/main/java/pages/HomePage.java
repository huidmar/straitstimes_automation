package pages;

import org.openqa.selenium.By;

public class HomePage {

    public static By loginLink =
            By.xpath("//li[@class='nav-login']//a[contains(@href,'javascript:;')]");

    public static By loginUserName =
            By.xpath("//li[@class='nav-login']//a[contains(text(),'taltest2')]");
  
    public static By MainArticleHeading =
            By.xpath("//h2[contains(text(),'Top Stories')]");

    public static By MainArticleImage =
            By.xpath("//a[@href='/singapore/saf-to-lower-training-tempo-to-better-focus-on-safety-in-wake-of-actor-aloysius-pangs']");

    public static By MainArticleSummary =
            By.xpath("//a[@class='block-link']");
}
