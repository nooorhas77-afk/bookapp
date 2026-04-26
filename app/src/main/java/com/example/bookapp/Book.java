package com.example.bookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class Book {
    int id;
    String title;
    String author;
    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public void addBook(Context context) {
        MySQLiteHelper dbHelper = new MySQLiteHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", this.title);
        values.put("author", this.author);

        db.insert("books", null, values);
        db.close();
    }
    public void deleteBook(Context context) {
        MySQLiteHelper dbHelper = new MySQLiteHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete("books", "title=?", new String[]{this.title});

        db.close();
    }
    public static String getAllBooks(Context context) {
        MySQLiteHelper dbHelper = new MySQLiteHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM books", null);

        StringBuilder data = new StringBuilder();

        if (cursor.moveToFirst()) {
            do {
                data.append("Title: ")
                        .append(cursor.getString(1))
                        .append(" | Author: ")
                        .append(cursor.getString(2))
                        .append("\n");
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return data.toString();
    }
}