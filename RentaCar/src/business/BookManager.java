package business;

import core.Helper;
import dao.BookDao;
import entity.Book;


import java.util.ArrayList;

public class BookManager {
    private final BookDao bookDao;

    public BookManager() {
        this.bookDao = new BookDao();
    }

    public ArrayList<Book> findAll() {
        return this.bookDao.findAll();
    }

    public boolean save(Book book) {
        if (book.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.bookDao.save(book);
    }

}
