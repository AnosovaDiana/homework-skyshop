package org.skypro.skyshop.article;

public final class Article implements Searchable {
    private final String title;
    private final String text;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    public String getName() {
        return title;
    }

    public String getSearchTerm() {
        return toString();
    }

    public String getContentType() {
        return "ARTICLE";
    }

}



