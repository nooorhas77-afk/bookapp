package com.example.bookapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText title, author;
    Button insert, view, delete;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        delete = findViewById(R.id.btnDelete);
        result = findViewById(R.id.result);


        insert.setOnClickListener(v -> {
            Book book = new Book(
                    title.getText().toString(),
                    author.getText().toString()
            );

            book.addBook(this);

            result.setText("Book Added!");
            title.setText("");
            author.setText("");
        });

        view.setOnClickListener(v -> {
            String data = Book.getAllBooks(this);
            result.setText(data);
        });

        delete.setOnClickListener(v -> {
            Book book = new Book(
                    title.getText().toString(),
                    author.getText().toString()
            );

            book.deleteBook(this);

            result.setText("Book Deleted!");
            title.setText("");
        });
    }
}