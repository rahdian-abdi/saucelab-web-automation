package starter.Pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;


public class SauceDemoInventoryPage extends PageObject {
    WebDriver driver = getDriver();

    private final By cartNotification = By.cssSelector(".shopping_cart_badge");
    private final By getHeaderText = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    private final By activeSort = By.className("active_option");
    private final By cartBadgeNumber = By.className("shopping_cart_badge");
    private final By numberOfProductList = By.className("inventory_item_name");

    public String verifiedInventoryPage(){
        return driver.getCurrentUrl();
    }
    public String verifiedInventoryPageHeader() {
        return driver.findElement(getHeaderText).getText();
    }
    public boolean verifiedAddToCart(){
        return driver.findElement(cartNotification).isDisplayed();
    }
    public int initiateProductListSize() {
        List<String> getList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(numberOfProductList);
        for (WebElement list : elementList){
            getList.add(list.getText());
        }
        return getList.size();
    }
    public List<String> initiateProductList() {
        List<String> getList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(numberOfProductList);
        for (WebElement list : elementList){
            getList.add(list.getText());
        }
        return getList;
    }
    public List<Float> initiatePriceList() {
        List<Float> getPriceList = new ArrayList<>();
        List<WebElement> priceElement = driver.findElements(By.className("inventory_item_price"));
        for (WebElement element : priceElement) {
            getPriceList.add(Float.parseFloat(element.getText().substring(1)));
        }
        return getPriceList;
    }
    public int verifiedNumberItemHasAddedToCart() {
        // can't use btn btn_secondary btn_small btn_inventory because class names not permitted
        // Thus using full xpath to get list that "Remove" button is exist on inventory page
        List<String> addedItem = new ArrayList<>();
        for (int i=1 ; i<initiateProductListSize() ; i++){
            try {
                if ($(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div["+i+"]/div[2]/div[2]/button"))
                        .getAttribute("innerHTML")
                        .equals("Remove")){
                    addedItem.add($(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div["+i+"]/div[2]/div[2]/button"))
                            .getText());
                }
            } catch (Exception e){
                return addedItem.size();
            }
        }
        return addedItem.size();
    }

    public int verifiedNumberBadge() {
        return Integer.parseInt(driver.findElement(cartBadgeNumber).getText());
    }
    public String verifiedActiveSort() {
        return driver.findElement(activeSort).getAttribute("innerHTML");
    }
    public String[] verifiedAscendingSortingItem() {
        String[] arraySorted = new String[initiateProductListSize()];
        for (int i=0 ; i<initiateProductListSize();i++){
            arraySorted[i] = initiateProductList().get(i);
        }
        Arrays.sort(arraySorted);
        return arraySorted;
    }
    public String[] verifiedDescendingSortingItem() {
        String[] arraySorted = new String[initiateProductListSize()];
        for (int i=0 ; i<initiateProductListSize();i++){
            arraySorted[i] = initiateProductList().get(i);
        }
        Arrays.sort(arraySorted, Collections.reverseOrder());
        return arraySorted;
    }
    public Float[] verifiedAscendingPriceSortingItem() {
        Float[] arraySorted = new Float[initiateProductListSize()];
        for (int i=0 ; i<initiateProductListSize(); i++){
            arraySorted[i] = initiatePriceList().get(i);
        }
        Arrays.sort(arraySorted);
        return arraySorted;
    }

    public Float[] verifiedDescendingPriceSortingItem() {
        Float[] arraySorted = new Float[initiateProductListSize()];
        for (int i=0 ; i<initiateProductListSize(); i++){
            arraySorted[i] = initiatePriceList().get(i);
        }
        Arrays.sort(arraySorted, Collections.reverseOrder());
        return arraySorted;
    }

    public Map<Float, String> initiateMapItemAndPrice() {
        Map<Float, String> mapThing = new HashMap<>();
        for (int i=0 ; i<initiateProductListSize() ; i++){
            mapThing.put(initiatePriceList().get(i), initiateProductList().get(i));
        }
        return mapThing;
    }

    public String[] mapSortedAscProduct() {
        List<Float> productFromPrice = new ArrayList<Float>(initiateMapItemAndPrice().keySet());
        List<String> getProductFromPrice = new ArrayList<>();
        String[] product = new String[initiateProductListSize()];
        Collections.sort(productFromPrice);
        for (Float getProduct : productFromPrice){
            getProductFromPrice.add(initiateMapItemAndPrice().get(getProduct));
        }
        for (int i=0 ; i<initiateMapItemAndPrice().size();i++){
            product[i] = getProductFromPrice.get(i);
        }
        return product;
    }

}