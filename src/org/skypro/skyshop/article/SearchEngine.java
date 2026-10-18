package org.skypro.skyshop.article;

import org.skypro.skyshop.article.Searchable;


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
}

