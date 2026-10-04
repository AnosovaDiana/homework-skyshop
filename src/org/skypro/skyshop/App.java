package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main(String[] args) {
        Product milk = new Product("Молоко", 80);
        Product coffee = new Product("Кофе", 55);
        Product cheese = new Product("Сыр", 245);
        Product orange = new Product("Апельсин", 150);
        Product cook = new Product("Печенье", 35);
        Product fish = new Product("Рыба", 125);

        ProductBasket basket = new ProductBasket();

        System.out.println("Добавление продуктов:");
        basket.addProduct(milk);
        basket.addProduct(coffee);
        basket.addProduct(cheese);
        basket.addProduct(orange);
        basket.addProduct(cook);
        System.out.println("В корзину добавлено 5 товаров\n");

        System.out.println("Попытка добавить 6-й товар в корзину");
        basket.addProduct(fish);
        System.out.println();

        System.out.println("Вывод содержимого заполненной корзины");
        basket.printBasket();
        System.out.println();

        System.out.println("Получение итоговой суммы корзины");
        int total = basket.getTotalPrice();
        System.out.println("Итого: " + total + "\n");

        System.out.println("Поиск товара в корзине");
        boolean hasCoffee = basket.hasProduct("Кофе");
        System.out.println("Есть ли 'Кофе' в корзине? Ответ: " + hasCoffee + "\n");

        System.out.println("Поиск несуществующего товара");
        boolean hasFish = basket.hasProduct("Рыба");
        System.out.println("Есть ли 'Рыба' в корзине? Ответ: " + hasFish + "\n");

        System.out.println("Очистка корзины");
        basket.clearBasket();
        System.out.println("Корзина очищена.\n");

        System.out.println("Печать содержимого пустой корзины");
        basket.printBasket();
        System.out.println();

        System.out.println("Получение стоимости пустой корзины");
        int emptyTotal = basket.getTotalPrice();
        System.out.println("Стоимость пустой корзины: " + emptyTotal + "\n");

        System.out.println("Поиск товара в пустой корзине");
        boolean hasMilkInEmpty = basket.hasProduct("Молоко");
        System.out.println("Есть ли 'Молоко' в пустой корзине? Ответ: " + hasMilkInEmpty);
    }
}