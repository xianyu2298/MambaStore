package com.aaa.market.controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aaa.market.R;
import com.aaa.market.entities.BookService;

public class DeleteBookActivity extends AppCompatActivity {

    private EditText etISBN;
    private Button btnDelete;
    private BookService bookService; // 注意这里！！！

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        bookService = new BookService(this); // 注意这里！！！

        etISBN = findViewById(R.id.etISBN);
        btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isbn = etISBN.getText().toString().trim();

                if (isbn.isEmpty()) {
                    Toast.makeText(DeleteBookActivity.this, "请输入ISBN", Toast.LENGTH_SHORT).show();
                    return;
                }

                bookService.deleteBookByISBN(isbn); // 这里就不会红了！
                Toast.makeText(DeleteBookActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
