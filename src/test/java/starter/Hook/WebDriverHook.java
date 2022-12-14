package starter.Hook;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import starter.BaseTest;

public class WebDriverHook extends BaseTest {

    @Before(order = 0, value = "@login")
    public void beforeScenario(){
        home.open();
        login.inputUsername("standard_user");
        login.inputPassword("secret_sauce");
        login.clickLogin();
    }

    @Before(order = 1, value = "@add-item")
    public void addInitiateItem(){
        addToCart.clickAddToCartPreCondition();
    }

    @After(value = "@remove-item")
    public void afterScenario(){
        addToCart.clickRemovePreCondition();
    }
}
