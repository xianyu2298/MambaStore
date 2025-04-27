package com.aaa.market.controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aaa.market.R;
import com.aaa.market.entities.Book;
import com.aaa.market.entities.BookService;

public class EditBookActivity extends AppCompatActivity {

    private EditText etId, etName, etAuthor, etPrice, etCategory, etISBN, etImgUrl, etDescription;
    private Button btnUpdate;
    private BookService bookService;
    private int bookId; // 要编辑的书本ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        bookService = new BookService(this);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etAuthor = findViewById(R.id.etAuthor);
        etPrice = findViewById(R.id.etPrice);
        etCategory = findViewById(R.id.etCategory);
        etISBN = findViewById(R.id.etISBN);
        etImgUrl = findViewById(R.id.etImgUrl);
        etDescription = findViewById(R.id.etDescription);
        btnUpdate = findViewById(R.id.btnUpdate);

        // 取出上一个页面传来的ID
        bookId = getIntent().getIntExtra("book_id", -1);

        if (bookId != -1) {
            loadBookData(bookId); // 根据id加载书的信息
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }

    private void loadBookData(int id) {
        Book book = bookService.getBook(id);
        if (book != null) {
            etId.setText(String.valueOf(book.getId()));
            etId.setEnabled(false); // 禁止修改ID
            etName.setText(book.getName());
            etAuthor.setText(book.getAuthor());
            etCategory.setText(String.valueOf(book.getCategory().ordinal()));
            etISBN.setText(book.getISBN());
            etImgUrl.setText(book.getImgUrl());
            etDescription.setText(book.getDescription());
            etPrice.setText(String.valueOf(book.getPrice()));
        }
    }

    private void saveChanges() {
        String name = etName.getText().toString();
        String author = etAuthor.getText().toString();
        String imgUrl = etImgUrl.getText().toString();
        String isbn = etISBN.getText().toString();
        String description = etDescription.getText().toString();
        int category = Integer.parseInt(etCategory.getText().toString());
        double price = Double.parseDouble(etPrice.getText().toString());

        Book book = new Book();
        book.setId(bookId); // 用原ID
        book.setName(name);
        book.setAuthor(author);
        book.setImgUrl(imgUrl);
        book.setISBN(isbn);
        book.setDescription(description);
        book.setCategory(Book.Category.values()[category]);
        book.setPrice(price);

        bookService.updateBook(book);
        Toast.makeText(EditBookActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
        finish();
    }
}
