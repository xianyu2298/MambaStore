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

public class AddBookActivity extends AppCompatActivity {

    private EditText etName, etAuthor, etPrice, etCategory, etISBN, etImgUrl, etDescription;
    private Button btnSave;
    private BookService bookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        bookService = new BookService(this); // 初始化！

        etName = findViewById(R.id.etName);
        etAuthor = findViewById(R.id.etAuthor);
        etPrice = findViewById(R.id.etPrice);
        etCategory = findViewById(R.id.etCategory);
        etISBN = findViewById(R.id.etISBN);
        etImgUrl = findViewById(R.id.etImgUrl);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String author = etAuthor.getText().toString();
                String imgUrl = etImgUrl.getText().toString();
                String isbn = etISBN.getText().toString();
                String description = etDescription.getText().toString();
                int category = Integer.parseInt(etCategory.getText().toString()); // 要输入数字（比如0）
                double price = Double.parseDouble(etPrice.getText().toString());

                Book newBook = new Book();
                newBook.setName(name);
                newBook.setAuthor(author);
                newBook.setImgUrl(imgUrl);
                newBook.setISBN(isbn);
                newBook.setDescription(description);
                newBook.setCategory(Book.Category.values()[category]);
                newBook.setPrice(price);

                bookService.addBook(newBook);
                Toast.makeText(AddBookActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
