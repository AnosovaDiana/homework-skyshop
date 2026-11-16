package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        ProductBasket myBasket = new ProductBasket();
        Product milk = new SimpleProduct("Молоко", 80);
        Product bread = new SimpleProduct("Хлеб", 45);
        myBasket.addProduct(milk);
        myBasket.addProduct(bread);

        int totalSum = myBasket.getTotalPrice();
        System.out.println("Общая стоимость: " + totalSum + " руб.");
        myBasket.printProductList();
        boolean hasBread = myBasket.hasProduct("Хлеб");
        System.out.println("Есть ли хлеб? " + hasBread);
        boolean hasJuice = myBasket.hasProduct("Сок");
        System.out.println("Есть ли Сок? " + hasJuice);
        myBasket.clearBasket();
        myBasket.printProductList();
        Product coffee = new DiscountedProduct("Кофе", 70, 10);
        Product orange = new FixPriceProduct("Апельсин");
        myBasket.addProduct(milk);
        myBasket.addProduct(bread);
        myBasket.addProduct(coffee);
        myBasket.addProduct(orange);
        myBasket.printProductList();

        SearchEngine searchEngine = new SearchEngine();
        searchEngine.add(milk);
        searchEngine.add(bread);
        searchEngine.add(coffee);
        searchEngine.add(orange);
        Article cofeeArticle = new Article(
                "Кофе",
                "Кофе очень полезен"
        );
        Article orangeArticle = new Article(
                "Апельсин",
                "Цитрусы содержат витамины"
        );
        searchEngine.add(cofeeArticle);
        searchEngine.add(orangeArticle);
        Map<String, Searchable> searchResults = searchEngine.search("Кофе");
        for (Searchable result : searchResults.values()) {
       System.out.println(result.getStringRepresentation());
   }
        Map<String, Searchable> searchResults1 = searchEngine.search("Апельсин");
        for (Searchable result : searchResults1.values()) {
            if (result != null) {
                System.out.println(result.getStringRepresentation());
            }
        }
        try {
            new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new SimpleProduct("Сок", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new DiscountedProduct("Чай", 200, 120);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Searchable bestMatch = searchEngine.findBestMatch("Кофе");
            System.out.println("Лучший результат: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            Searchable bestMatch1 = searchEngine.findBestMatch("НесуществующийЗапрос");
            System.out.println("Лучший результат: " + bestMatch1.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        List<Product> removed = myBasket.removeProductByName("Молоко");
        System.out.println("Удалённые продукты: " + removed);
        myBasket.printProductList();
        List<Product> notRemoved = myBasket.removeProductByName("Сыр");
        if (notRemoved.isEmpty()) {
            System.out.println("Список пуст");
        }
        myBasket.printProductList();
    }


}

