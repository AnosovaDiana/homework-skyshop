package org.skypro.skyshop.article;

import org.skypro.skyshop.article.Searchable;
import org.skypro.skyshop.product.BestResultNotFound;

import java.util.ArrayList;
import java.util.List;


public class SearchEngine {
    private final List<Searchable> searchables = new ArrayList<>();

    public SearchEngine() {

    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public List<Searchable> search(String request) {
        List<Searchable> results = new ArrayList<>();

        for (Searchable current : searchables) {
            if (current.getSearchTerm().contains(request)) {
                results.add(current);
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

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new IllegalArgumentException("Поисковая строка не может быть null, пустой или состоять только из пробелов!");
        }
        if (searchables.isEmpty()) {
            throw new BestResultNotFound(search);
        }

        Searchable bestMatch = null;
        int maxRepetitions = 0;
        for (Searchable current : searchables) {
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

