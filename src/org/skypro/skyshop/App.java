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
import java.util.Set;

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
        Set<Searchable> searchResults = searchEngine.search("Кофе");
        for (Searchable result : searchResults) {
            System.out.println(result.getStringRepresentation());
        }
        Set<Searchable> searchResults1 = searchEngine.search("Апельсин");
        for (Searchable result : searchResults1) {
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

        Product coffee1 = new SimpleProduct("Кофе", 100);
        Product coffee2 = new SimpleProduct("Кофе в зёрнах", 350);
        Product coffeeDuplicate = new SimpleProduct("Кофе", 200);

        Article coffeeArticle1 = new Article("Кофе-брейк", "Статья про перерыв на кофе");
        Article coffeeArticle2 = new Article("Кофе-латте", "Рецепт приготовления кофе");

        searchEngine.add(coffee1);
        searchEngine.add(coffee2);
        searchEngine.add(coffeeDuplicate);
        searchEngine.add(coffeeArticle1);
        searchEngine.add(coffeeArticle2);
        System.out.println("Сценарий: Поиск по слову 'Кофе'");
        Set<Searchable> coffeeResults = searchEngine.search("Кофе");
        for (Searchable item : coffeeResults) {
            System.out.println(item.getStringRepresentation());
        }
        System.out.println("Сценарий: Поиск по слову 'Рецепт'");
        Set<Searchable> recipeResults = searchEngine.search("Рецепт");
        for (Searchable item : recipeResults) {
            System.out.println(item.getStringRepresentation());
        }
        System.out.println("Сценарий: Поиск несуществующего слова");
        Set<Searchable> emptyResults = searchEngine.search("Шоколад");
        System.out.println("Найдено элементов: " + emptyResults.size());
    }
}

