package com.aaa.market;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyAuth extends MyDBHelper {

    private static int userId;

    public MyAuth(Context context) {
        super(context);
    }

    public enum AuthResult {
        SUCCESS,
        INVALID_USERNAME_OR_PWD,
        USER_EXISTED,
        TOKEN_TOO_LONG,
        UNKNOWN_ERROR
    }

    public static int getUserId() {
        return userId;
    }

    private static void setUserId(int userId) {
        MyAuth.userId = userId;
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 注册结果
     */
    public AuthResult addUser(String username, String password) {
        if (username.length() > 50)
            return AuthResult.TOKEN_TOO_LONG;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("account", null, "username=?", new String[]{username}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return AuthResult.USER_EXISTED;
        }
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", md5(password));
        int id = (int) db.insert("account", null, values);
        db.close();
        if (id > 0) {
            userId = id;
            return AuthResult.SUCCESS;
        } else
            return AuthResult.UNKNOWN_ERROR;
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    public AuthResult authUser(String username, String password) {
        if (username.length() > 50)
            return AuthResult.TOKEN_TOO_LONG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("account", null, "username=?", new String[]{username}, null, null, null);
        String result = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            result = cursor.getString((int) cursor.getColumnIndex("password"));
            userId = cursor.getInt((int) cursor.getColumnIndex("id"));
        }
        cursor.close();
        db.close();
        if (result != null && result.equals(md5(password)))
            return AuthResult.SUCCESS;
        else {
            userId = 0;
            return AuthResult.INVALID_USERNAME_OR_PWD;
        }
    }

    static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}