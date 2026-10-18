package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.article.Searchable;
import org.skypro.skyshop.article.SearchEngine;


public class Main {
    public static void main(String[] args) {
        Product milk = new SimpleProduct("Молоко", 80);
        Product laptop = new DiscountedProduct("Ноутбук", 80000, 10);
        Product book = new DiscountedProduct("Книга", 1500, 15);
        Product mug = new FixPriceProduct("Фирменная кружка");
        ProductBasket basket = new ProductBasket();
        System.out.println("Добавляем товары в корзину");
        basket.addProduct(milk);
        basket.addProduct(laptop);
        basket.addProduct(book);
        basket.addProduct(mug);
        System.out.println("В корзину добавлено 4 товара");
        System.out.println("\nПечать содержимого корзины");
        basket.printBasket();
        System.out.println("\nПроверка поиска товаров");
        boolean hasMilk = basket.hasProduct("Молоко");
        System.out.println("Есть ли 'Молоко' в корзине? Ответ: " + hasMilk);
        boolean hasCoffee = basket.hasProduct("Кофе");
        System.out.println("Есть ли 'Кофе' в корзине? Ответ: " + hasCoffee);
        System.out.println("\nОчистка корзины");
        basket.clearBasket();
        System.out.println("Корзина очищена.\n");

        SearchEngine searchEngine = new SearchEngine(10);
        Product phone = new Product("Смартфон Apple iPhone 16") {
            @Override public double getPrice() { return 90000; }
            @Override public boolean isSpecial() { return false; }
        };
        Product casePhone = new Product("Чехол для iPhone силиконовый") {
            @Override public double getPrice() { return 1500; }
            @Override public boolean isSpecial() { return true; }
        };
        Product tea = new Product("Чай черный") {
            @Override public double getPrice() { return 300; }
            @Override public boolean isSpecial() { return false; }
        };

        searchEngine.add(phone);
        searchEngine.add(casePhone);
        searchEngine.add(tea);

        Article article = new Article(
                "Тенденция iPhone",
                "По данным аналитики за 2026 год, на фоне общего падения рынка iPhone заняли около 30% российского рынка смартфонов в денежном выражении"
        );
        Article appleArticle = new Article(
                "Презентация Apple",
                "Компания Apple показала новые смартфоны и аксессуары к ним"
        );
        Article teaArticle = new Article(
                "Чай и его польза",
                "Черный и зеленый чай содержат много антиоксидантов"
        );

        searchEngine.add(article);
        searchEngine.add(appleArticle);
        searchEngine.add(teaArticle);

        System.out.println("ПОИСК ПО ЗАПРОСУ: 'iPhone'");
        Searchable[] results = searchEngine.search("iPhone");

        for (Searchable result : results) {
            if (result != null) {
                System.out.println(result.getStringRepresentation());
            }
        }

        System.out.println("\nПОИСК ПО ЗАПРОСУ: 'Чай'");
        Searchable[] teaResults = searchEngine.search("Чай");
        for (Searchable result : teaResults) {
            if (result != null) {
                System.out.println(result.getStringRepresentation());
            }
        }


    }

    }
