package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.article.Searchable;
import org.skypro.skyshop.article.SearchEngine;
import org.skypro.skyshop.product.BestResultNotFound;

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
            @Override
            public double getPrice() {
                return 90000;
            }

            @Override
            public boolean isSpecial() {
                return false;
            }
        };
        Product casePhone = new Product("Чехол для iPhone силиконовый") {
            @Override
            public double getPrice() {
                return 1500;
            }

            @Override
            public boolean isSpecial() {
                return true;
            }
        };
        Product tea = new Product("Чай черный") {
            @Override
            public double getPrice() {
                return 300;
            }

            @Override
            public boolean isSpecial() {
                return false;
            }
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
        System.out.println("----------------------------------------");
        try {
            System.out.println("Попытка создать продукт с пустым именем");
            Product Milk = new SimpleProduct("     ",0);
        } catch (IllegalArgumentException e) {
            System.out.println("Реакция системы: " + e.getMessage());
        }

        try {
            System.out.println("Попытка создать SimpleProduct с ценой 0");
            SimpleProduct freeBread = new SimpleProduct("Хлеб", 0);
        } catch (IllegalArgumentException e) {
            // Обрабатываем исключение: выводим сообщение об ошибке
            System.out.println("Реакция системы: " + e.getMessage());
        }

        try {
            System.out.println("Попытка создать DiscountedProduct со скидкой 150%");
            DiscountedProduct superDiscount = new DiscountedProduct("Смартфон", 25000, 150);
        } catch (IllegalArgumentException e) {
            // Обрабатываем исключение: выводим сообщение об ошибке
            System.out.println("Реакция системы: " + e.getMessage());
        }

        try {
            System.out.println("Попытка создать продукт с правильными данными");
            DiscountedProduct goodProduct = new DiscountedProduct("Книга", 500, 10);
            System.out.println("Продукт '" + goodProduct.getName() + "' успешно создан.");
        } catch (IllegalArgumentException e) {
            System.out.println("Реакция системы: " + e.getMessage());
        }

        SearchEngine engine = new SearchEngine(5);
        engine.add(new Article("Кофе", "Кофе несет как пользу, так и вред"));
        engine.add(new Article("Рецепт пирога", "Как приготовить вкусный яблочный пирог в духовке."));
        engine.add(new Article("Космические корабли", "Технологии будущего: космолёты, гиперпрыжки и новые галактики."));
        try {
            System.out.println("Ищем самый подходящий элемент по запросу 'Кофе'");

            Searchable result = engine.findMostSuitable("Кофе");

            System.out.println("Объект успешно найден!");
            System.out.println("Содержимое найденного объекта: " + result.getSearchTerm());

        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        try {
            System.out.println("Ищем самый подходящий элемент по запросу 'Сок'");
            Searchable result = engine.findMostSuitable("Сок");
            System.out.println("Объект найден: " + result.getSearchTerm());

        } catch (BestResultNotFound e) {
            System.out.println("Сработало исключение:");
            System.out.println("Сообщение из исключения: " + e.getMessage());
        }

    }
}

