package library.search;

import library.book.Book;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Catalog implements Search {
    private HashMap<String, List<Book>> bookTitles;
    private HashMap<String, List<Book>> bookAuthors;
    private HashMap<String, List<Book>> bookSubjects;
    private HashMap<Date, List<Book>> bookPublicationDates;

    public List<Book> searchByTitle(String query) {
        // return all books containing the string query in their title.
        return bookTitles.get(query);
    }

    public List<Book> searchByAuthor(String query) {
        // return all books containing the string query in their author's name.
        return bookAuthors.get(query);
    }

    @Override
    public List<Book> searchBySubject(String query) {
        return bookSubjects.get(query);
    }

    @Override
    public List<Book> searchByPubDate(Date publishDate) {
        return bookPublicationDates.get(publishDate);
    }
}
