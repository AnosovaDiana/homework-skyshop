package org.skypro.skyshop.product;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String searchRequest) {
        super("Не удалось найти подходящий результат для поискового запроса: \"" + searchRequest + "\"");
    }
}

