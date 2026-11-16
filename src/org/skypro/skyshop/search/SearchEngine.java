package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> searchables = new ArrayList<>();



    public SearchEngine() {
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public List<Searchable> search(String searchTerm) {
        List<Searchable> results = new ArrayList<>();
        for (Searchable item : searchables) {
            if (item != null && item.getSearchTerm().contains(searchTerm)) {
                results.add(item);
            }
        }
        return results;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable bestResult = null;
        int maxCount = 0;

        for (Searchable item : searchables) {
            if (item == null) {
                continue;
            }
            String str = item.getSearchTerm();
            int count = 0;
            int index = 0;
            int substringIndex = str.indexOf(search, index);
            while (substringIndex != -1) {
                count++;
                index = substringIndex + search.length();
                substringIndex = str.indexOf(search, index);
            }
            if (count > maxCount) {
                maxCount = count;
                bestResult = item;
            }
        }
        if (bestResult == null) {
            throw new BestResultNotFound(search);
        }
        return bestResult;
    }
}
