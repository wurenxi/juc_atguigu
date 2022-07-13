package com.bilibili.juc.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/9 18:39
 */
public class AtomicStampedDemo {
    public static void main(String[] args) {
        Book book = new Book(1, "java");

        AtomicStampedReference<Book> stampedReference =
                new AtomicStampedReference<>(book, 1);

        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());

        Book mysqlBook = new Book(2, "mysql");

        boolean b;
        b = stampedReference.compareAndSet(book, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        b = stampedReference.compareAndSet(mysqlBook, book, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
    }
}

class Book {
    private int id;
    private String bookName;

    public Book(int id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
