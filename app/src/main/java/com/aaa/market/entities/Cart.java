package com.aaa.market.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aaa.market.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class Cart extends MyDBHelper {

    int accountId;

    public Cart(Context context, int accountId) {
        super(context);
        this.accountId = accountId;
    }

    private static CartItem deserializer(Cursor cursor) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cursor.getInt((int) cursor.getColumnIndex("id")));
        cartItem.setAccountId(cursor.getInt((int) cursor.getColumnIndex("account_id")));
        cartItem.setBookId(cursor.getInt((int) cursor.getColumnIndex("book_id")));
        cartItem.setBook(BookService.deserializer(cursor, 4));
        cartItem.setQuantity(cursor.getInt((int) cursor.getColumnIndex("quantity")));
        return cartItem;
    }

    /**
     * 获取购物车列表
     */
    public List<CartItem> getCart() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cart,book WHERE cart.account_id=? AND cart.book_id=book.id", new String[]{String.valueOf(accountId)});
        List<CartItem> result = new ArrayList<>();
        if (cursor.getCount() != 0)
            while (cursor.moveToNext())
                result.add(deserializer(cursor));
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 获取购物车数量
     */
    public int getCartCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("cart", null, "account_id=?", new String[]{String.valueOf(accountId)}, null, null, null);
        int result = cursor.getCount();
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 添加到购物车
     */
    public void addToCart(int bookId, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("cart", null, "account_id=? and book_id=?", new String[]{String.valueOf(accountId), String.valueOf(bookId)}, null, null,null);
        if (cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put("account_id", accountId);
            values.put("book_id", bookId);
            values.put("quantity", quantity);
            db.insertOrThrow("cart", null, values);
        } else {
            cursor.moveToNext();
            int quantityInCart = cursor.getInt((int) cursor.getColumnIndex("quantity"));
            ContentValues values = new ContentValues();
            values.put("quantity", quantityInCart + quantity);
            db.update("cart", values, "account_id=? and book_id=?", new String[]{String.valueOf(accountId), String.valueOf(bookId)});
        }
        cursor.close();
        db.close();
    }

    /**
     * 从购物车中删除
     */
    public void removeFromCart(int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cart", "account_id=? and book_id=?", new String[]{String.valueOf(accountId), String.valueOf(bookId)});
        db.close();
    }

    /**
     * 清空购物车
     */
    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cart", "account_id=?", new String[]{String.valueOf(accountId)});
        db.close();
    }

    /**
     * 获取购物车总价
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem cartItem : getCart())
            totalPrice += cartItem.getBook().getPrice() * cartItem.getQuantity();
        return totalPrice;
    }
}
