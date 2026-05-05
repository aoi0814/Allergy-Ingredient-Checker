package com.example.service;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {
	
    
    // アレルギーリストとキーワードに基づいてURLを生成
    public static String generateUrl(List<String> allergies, String keyword) {
        String baseUrl = "https://www.miraizaidan.or.jp/recipe/?pg=1";
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        // アレルギーの処理
        for (String allergy : allergies) {
            if (allergy.equals("卵")) {
                urlBuilder.append("&comingredients[]=1");
            } else if (allergy.equals("乳")) {
                urlBuilder.append("&comingredients[]=2");
            }else if(allergy.equals("小麦")) {
            	urlBuilder.append("&comingredients[]=3");
            }else if(allergy.equals("そば")) {
            	urlBuilder.append("&comingredients[]=4");
            }else if(allergy.equals("落花生")) {
            	urlBuilder.append("&comingredients[]=5");
            }else if(allergy.equals("えび")) {
            	urlBuilder.append("&comingredients[]=6");
            }else if(allergy.equals("かに")) {
            	urlBuilder.append("&comingredients[]=7");
            }else if(allergy.equals("アーモンド")) {
            	urlBuilder.append("&comingredients[]=8");
            }else if(allergy.equals("あわび")) {
            	urlBuilder.append("&comingredients[]=9");
            }else if(allergy.equals("いか")) {
            	urlBuilder.append("&comingredients[]=10");
            }else if(allergy.equals("いくら")) {
            	urlBuilder.append("&comingredients[]=11");
            }else if(allergy.equals("オレンジ")) {
            	urlBuilder.append("&comingredients[]=12");
            }else if(allergy.equals("カシューナッツ")) {
            	urlBuilder.append("&comingredients[]=13");
            }else if(allergy.equals("キウイフルーツ")) {
            	urlBuilder.append("&comingredients[]=14");
            }else if(allergy.equals("牛肉")) {
            	urlBuilder.append("&comingredients[]=15");
            }else if(allergy.equals("くるみ")) {
            	urlBuilder.append("&comingredients[]=16");
            }else if(allergy.equals("ごま")) {
            	urlBuilder.append("&comingredients[]=17");
            }else if(allergy.equals("さけ")) {
            	urlBuilder.append("&comingredients[]=18");
            }else if(allergy.equals("さば")) {
            	urlBuilder.append("&comingredients[]=19");
            }else if(allergy.equals("大豆")) {
            	urlBuilder.append("&comingredients[]=20");
            }else if(allergy.equals("鶏肉")) {
            	urlBuilder.append("&comingredients[]=21");
            }else if(allergy.equals("バナナ")) {
            	urlBuilder.append("&comingredients[]=22");
            }else if(allergy.equals("豚肉")) {
            	urlBuilder.append("&comingredients[]=23");
            }else if(allergy.equals("マカデミアナッツ")) {
            	urlBuilder.append("&comingredients[]=24");
            }else if(allergy.equals("桃")) {
            	urlBuilder.append("&comingredients[]=25");
            }else if(allergy.equals("やまいも")) {
            	urlBuilder.append("&comingredients[]=26");
            }else if(allergy.equals("りんご")) {
            	urlBuilder.append("&comingredients[]=27");
            }else if(allergy.equals("ゼラチン")) {
            	urlBuilder.append("&comingredients[]=28");
            }
        }


        // キーワードの処理
        if (keyword != null && !keyword.isEmpty()) {
            urlBuilder.append("&keyword=").append(keyword);
        }
        System.out.println(urlBuilder.toString());


        return urlBuilder.toString();
    }

    // 生成したURLをブラウザで開く
    public static void openUrlInBrowser(String url) {
        try {
            // URLをブラウザで開く
            URI uri = new URI(url);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Desktop is not supported on this system.");
            }
        } catch (IOException | java.net.URISyntaxException e) {
            System.out.println("URLを開けませんでした: " + e.getMessage());
        }
    }


    
        public static String ExtractUrl(String url) {
            // ChromeDriverのパスを指定（必要に応じて）
            // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

            // ヘッドレスモードを有効にする
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");

            WebDriver driver = null;
            try {
                // WebDriverのインスタンスを作成
                driver = new ChromeDriver(options);
                driver.get(url);

                // 要素が読み込まれるまで待機（新しい書き方）
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement searchMainDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.search_main")));

                WebElement searchList = searchMainDiv.findElement(By.cssSelector("ul.search_main_list"));
                WebElement firstLink = searchList.findElement(By.tagName("a"));
                String topLinkUrl = firstLink.getAttribute("href");

                System.out.println("一番上のリンクのURL: " + topLinkUrl);
                return topLinkUrl;

            } catch (org.openqa.selenium.TimeoutException e) {
                System.out.println("指定した要素が見つかりませんでした: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (driver != null) {
                    driver.quit(); // ドライバを閉じる
                }
            }
            return url;
        }
    

}
