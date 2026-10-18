package org.skypro.skyshop.article; // Должно быть строго так

public interface Searchable { // Обязательно PUBLIC
    String getSearchTerm();

    String getContentType();

    String getName();

    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }
}