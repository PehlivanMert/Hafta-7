package business;

import core.Helper;
import dao.BookDao;
import entity.Book;
import entity.Model;

import java.util.ArrayList;

public class ReservationManager {

    private final BookDao bookDao;

    public ReservationManager() {
        this.bookDao = new BookDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Book> books) {
        ArrayList<Object[]> bookObjList = new ArrayList<>();
        for (Book obj : books) {
            Object[] rowObject = new Object[size];

            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getCar_id();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getIdno();
            rowObject[i++] = obj.getMpno();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getStrt_date();
            rowObject[i++] = obj.getFnsh_date();
            rowObject[i++] = obj.getPrc();
            rowObject[i++] = obj.getNote();
            rowObject[i++] = obj.getbCase();

            bookObjList.add(rowObject);
        }
        return bookObjList;
    }

    public ArrayList<Book> findAll() {
        return this.bookDao.findAll();
    }


    public Book getById(int id) {
        return this.bookDao.getById(id);
    }

    public boolean update(Book book) {
        if (this.getById(book.getId()) == null) {
            Helper.showMsg("notfound");
        }
        return this.bookDao.update(book);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.bookDao.delete(id);
    }

}
