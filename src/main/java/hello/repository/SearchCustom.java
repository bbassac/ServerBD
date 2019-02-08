package hello.repository;

import hello.bean.SearchBD;

public interface SearchCustom {
    SearchBD getBookFromIsbn(String id) throws BookNotFoundException;

    SearchBD getBookFromEanIsbn(String id) throws BookNotFoundException;
}
