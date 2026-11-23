package org.skypro.skyshop.search;

import java.util.*;

public class SearchEngine {
    private final Set<Searchable> searchables = new HashSet<>();


    public SearchEngine() {
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Set<Searchable> search(String searchTerm) {
        Set<Searchable> results = new TreeSet<>(new SearchableComparator());
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
