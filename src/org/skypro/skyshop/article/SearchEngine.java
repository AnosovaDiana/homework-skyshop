package org.skypro.skyshop.article;

import org.skypro.skyshop.article.Searchable;
import org.skypro.skyshop.product.BestResultNotFound;


public class SearchEngine {
    private final Searchable[] searchables;
    private int size = 0;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
    }

    public void add(Searchable searchable) {
        if (size < searchables.length) {
            searchables[size] = searchable;
            size++;
        } else {
            System.out.println("Поисковый движок переполнен! Не удалось добавить элемент.");
        }
    }

    public Searchable[] search(String request) {
        Searchable[] results = new Searchable[5];
        int count = 0;
        for (int i = 0; i < size; i++) {
            Searchable current = searchables[i];


            if (current.getSearchTerm().contains(request)) {
                results[count] = current;
                count++;

                if (count == 5) {
                    break;
                }
            }
        }
        return results;
    }

    private int countOccurrences(String text, String target) {
        if (text == null || target == null || target.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = text.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }

        return count;
    }

    public Searchable findMostSuitable(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new IllegalArgumentException("Поисковая строка не может быть null, пустой или состоять только из пробелов!");
        }
        if (size == 0) {
            throw new BestResultNotFound(search);
        }

        Searchable bestMatch = null;
        int maxRepetitions = 0;
        for (int i = 0; i < size; i++) {
            Searchable current = searchables[i];

            if (current == null) {
                continue;
            }
            String text = current.getSearchTerm();
            int repetitions = countOccurrences(text, search);
            if (repetitions > maxRepetitions) {
                maxRepetitions = repetitions;
                bestMatch = current;
            }
        }
        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }
        return bestMatch;
    }
}

