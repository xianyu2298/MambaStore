package com.aaa.market.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aaa.market.R;
import com.aaa.market.adapters.MyBookRecyclerViewAdapter;
import com.aaa.market.entities.Book;
import com.aaa.market.entities.BookService;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private Button btnAddBook, btnEditBook, btnDeleteBook;
    private RecyclerView recyclerView;
    private MyBookRecyclerViewAdapter adapter;
    private BookService bookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bookService = new BookService(this);

        btnAddBook = findViewById(R.id.btnAddBook);
        btnEditBook = findViewById(R.id.btnEditBook);
        btnDeleteBook = findViewById(R.id.btnDeleteBook);
        recyclerView = findViewById(R.id.recyclerViewBooks);

        btnAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        btnEditBook.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, EditBookActivity.class);
            startActivity(intent);
        });

        btnDeleteBook.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, DeleteBookActivity.class);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshBookList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshBookList(); // 回来时刷新列表
    }

    private void refreshBookList() {
        List<Book> bookList = bookService.getList();
        ArrayList<Object> mixedList = new ArrayList<>();

        // 把书按分类插入分组标题（如果要分组）
        for (Book.Category category : Book.Category.values()) {
            mixedList.add(category); // 加分组标题
            for (Book b : bookList) {
                if (b.getCategory() == category) {
                    mixedList.add(b); // 加书
                }
            }
        }

        adapter = new MyBookRecyclerViewAdapter(mixedList);
        adapter.setOnItemClickListener(view -> {
            int id = (int) view.getTag();
            // 跳转到修改页面，并传递id
            Intent intent = new Intent(AdminActivity.this, EditBookActivity.class);
            intent.putExtra("book_id", id);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
