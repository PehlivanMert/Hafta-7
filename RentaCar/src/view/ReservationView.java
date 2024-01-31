package view;

import business.BookManager;
import business.ReservationManager;
import core.ComboItem;
import core.Helper;
import entity.Book;
import entity.Car;
import entity.Model;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationView extends Layout {

    private JPanel contanier;
    private JLabel lbl_reservation;
    private JTextField fld_name;
    private JTextField fld_idno;
    private JLabel lbl_car_inf;
    private JTextField fld_mpno;
    private JTextField fld_post;
    private JTextField fld_strt;
    private JTextField fld_fnsh;
    private JTextField fld_prc;
    private JTextArea txta_rez_note;
    private JLabel rez_prc;
    private JLabel rez_fnsh;
    private JLabel rez_strt;
    private JLabel rez_posta;
    private JLabel rez_mpno;
    private JLabel rez_idno;
    private JLabel rez_name;
    private JButton btn_rez_save;
    private JLabel rez_note;

    private Car car;
    private Book book;
    private BookManager bookManager;
    private ReservationManager reservationManager;

    public ReservationView(Book selectedBook, Car selectedCar, String strt_date, String fnsh_date) {
        this.book = selectedBook;
        this.car = selectedCar;
        this.reservationManager = new ReservationManager();

        this.add(contanier);
        this.guiInitiliaze(300, 600);

        lbl_car_inf.setText("Araç : " + this.car.getPlate() +
                " / " + this.car.getModel().getBrand().getName() +
                " / " + this.car.getModel().getName());


        if (this.book.getId() != 0) {

            this.fld_name.setText(this.book.getName());
            this.fld_idno.setText(this.book.getIdno());
            this.fld_mpno.setText(this.book.getMpno());
            this.fld_post.setText(this.book.getMail());
            this.fld_strt.setText(String.valueOf(this.book.getStrt_date()));
            this.fld_fnsh.setText(String.valueOf(this.book.getFnsh_date()));
            this.fld_prc.setText(String.valueOf(this.book.getPrc()));
            this.txta_rez_note.setText(this.book.getNote());


        }

        btn_rez_save.addActionListener(e -> {
            JTextField[] checkFieldList = {
                    this.fld_name,
                    this.fld_idno,
                    this.fld_post,
                    this.fld_mpno,
                    this.fld_prc,
                    this.fld_strt,
                    this.fld_fnsh
            };

            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                boolean result ;

                this.book.setName(fld_name.getText());
                this.book.setIdno(fld_idno.getText());
                this.book.setMpno(fld_mpno.getText());
                this.book.setMail(fld_post.getText());
                this.book.setStrt_date(LocalDate.parse(fld_strt.getText()));
                this.book.setFnsh_date(LocalDate.parse(fld_fnsh.getText()));
                this.book.setPrc(Integer.parseInt(fld_prc.getText()));
                this.book.setNote(txta_rez_note.getText());

                if (this.book.getId() != 0) {
                    result = this.reservationManager.update(this.book);

                } else {
                    result = this.bookManager.save(this.book);
                }

                if (result) {
                    Helper.showMsg("done");
                    this.dispose();
                } else {
                    Helper.showMsg("error");
                }


            }

        });
    }
}

