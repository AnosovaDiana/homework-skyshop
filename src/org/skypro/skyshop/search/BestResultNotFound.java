package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String search) {
        super("Для поискового запроса «" + search + "» не нашлось подходящего объекта");
    }
}