import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Excel_Utility;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class steps extends baseTest
{
    String path = "src/test/java/resources/UyeBilgi.xlsx";

    List<List<String>> list = Excel_Utility.getData(path, "Sayfa1", 2);
    @Test

    public void search () throws Exception
    {
        //Url aldık ve gerekli değeri arattık
        assertName(driver.getCurrentUrl(),"https://www.network.com.tr/");
        sendElement(By.id("search"),"ceket");
        clickEnter(By.id("search"),Keys.ENTER);

        //aşağıya doğru scroll eder.
        scroll();

        clickElement(By.xpath("//*[@class='button -secondary -sm relative']"));
        Thread.sleep(2000);
        assertName(driver.getCurrentUrl(), "https://www.network.com.tr/search?searchKey=ceket&page=2");

        //ilk ürüne doğru hover yaptık.
        hoverElement(By.xpath("//*[@class='product__discountPercent -dualSmall -end']"));
        Thread.sleep(2000);
        hoverElement(By.xpath("//*[text()='Beden Ekle']"));
        Thread.sleep(2000);
        clickElement(By.xpath("//*[@class='product__size -productCart radio-box'][3]"));

        Thread.sleep(2000);

        //ürün fiyat ve beden bilgisini çektik.
        String urunFiyati=findElement(By.xpath("//*[@class='header__basketModal -priceValue']")).getText();
        String urunBedeni=findElement(By.xpath("//*[@class='header__basketProductVariation--val")).getText();




        //ürün sepet ve normal kısım için beden ve fiyat karşılaştırması
        assertControl(By.className("cartItem__price -sale"),urunFiyati);
        assertControl(By.className("cartItem__attrValue"),urunFiyati);

        String eskiFiyat=findElement(By.xpath("//*[@class='header__basketProductVariation--val")).getText();

        if(eskiFiyat!=urunFiyati)
        {
           logger.info("ürünün indirimli fiyatı düşük");
        }
        else
        {
            logger.info("fiyatlar eşittir!");
        }


        Thread.sleep(2000);
        clickElement(By.xpath("//*[@class='continueButton n-button large block text-center -primary']"));
        Thread.sleep(2000);

        //Excelden giriş için gerekli bilgileri çektik ve kullandık
        sendElement(By.xpath("//*[@id='n-input-email']"),list.get(0).get(0));
        sendElement(By.xpath("//*[@id='n-input-password']"),list.get(0).get(1));

        displayElement(By.xpath("//*[@class='n-button large block text-center -primary ']"));
        clickElement(By.xpath("//*[@class='headerCheckout__logo__img']"));

        Thread.sleep(2000);
        clickElement(By.xpath("//*[@class='header__icon -shoppingBag']"));

        Thread.sleep(2000);
        //sepetten ürün çıkarıldı.
        clickElement(By.xpath("//*[@class='header__basketProductRemove']"));
        Thread.sleep(2000);

        //sepetten ürün çıkartılma onaylandı.
        clickElement(By.xpath("//*[@class='btn -black o-removeCartModal__button']"));
        clickElement(By.xpath("//*[@class='header__icon -shoppingBag']"));
        Thread.sleep(2000);

        //sepet boşmu kontorlü
        assertControl(By.className("header__emptyBasketText"),"Sepetiniz Henüz Boş");


    }


    void randomSelect(){

        List<WebElement> allProducts= driver.findElements(By.xpath("//*[@class='product__size -productCart radio-box'][3]"));
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();

    }

}

