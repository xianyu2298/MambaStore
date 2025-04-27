package com.aaa.market.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aaa.market.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class BookService extends MyDBHelper {

    private static final List<Book> defaultData = new ArrayList<Book>() {{
        add(new Book(1, Book.Category.COMPUTER, "第一行代码", "https://th.bing.com/th/id/OIP.1jWya0jG-zC_wXKo2UOpBAHaJ4?rs=1&pid=ImgDetMain", "郭霖", "9787115524836", "本书被Android开发者广为推荐。全书系统全面、循序渐进地介绍了Android软件开发的必备知识、经验和技巧。\n" +
                "\n" +
                "第3版基于Android 10.0对第2版进行了全面更新，不仅将所有知识点都在Android 10.0系统上进行了重新适配，同时加入Kotlin语言的全面讲解，使用Kotlin对全书代码进行重写，而且还介绍了最新系统特性以及Jetpack架构组件的使用，使本书更加实用。\n" +
                "\n" +
                "本书内容通俗易懂，由浅入深，既是Android初学者的入门必备，也是Android开发者的进阶首选。", 65.9));
        add(new Book(2, Book.Category.HISTORY, "上下五千年", "https://pic1.zhimg.com/v2-0fe16cf17e397058e39848218586e9d6_r.jpg?source=1def8aca", "林汉达、曹余章", "9787532412587", "让“上下五千年”名闻遐迩的是国内第一款少儿通俗历史读物《上下五千年》，它是由著名的语言学家、历史教育家林汉达教授与现代作家曹余章先后编著，林汉达教授编写了写作提纲和部分篇目，曹余章则写了剩下的篇目，其中第一版分5册于1979年问世，东汉以前的部分为林汉达与曹余章合著，其余的部分是曹余章在林汉达手稿基础上整理而成，1991年该书分精华版和平装版发行了第二版。受其影响和启发，有了相关读物“中华上下五千年”，继而又有了“世界五千年”少儿文学读物问世。", 64.00));
        add(new Book(3, Book.Category.NOVEL, "红楼梦", "https://th.bing.com/th/id/OIP.2ESKSMIWuny9NFVwkKER9gHaKX?rs=1&pid=ImgDetMain", "[清] 曹雪芹", "9787020002207", "《红楼梦》塑造了众多的人物形象，他们各自具有自己独特而鲜明的个性特征，成为不朽的艺术典型，在中国文学史和世界文学史上永远放射着奇光异彩。《红楼梦》是一部具有高度思想性和高度艺术性的伟大作品，从《红楼梦》反映的思想倾向来看，作者具有初步的民主主义思想，他对现实社会包括宫廷及官场的黑暗，封建贵族阶级及其家庭的腐朽，封建的科举制度、婚姻制度、奴婢制度、等级制度，以及与此相适应的社会统治思想即孔孟之道和程朱理学、社会道德观念等等，都进行了深刻的批判并且提出了朦胧的带有初步民主主义性质的理想和主张。这些理想和主张正是当时正在滋长的资本主义经济萌芽因素的曲折反映。", 99.0));
        add(new Book(4, Book.Category.SCIENCE, "人类简史", "https://img3.doubanio.com/view/subject/l/public/s27814883.jpg", "[以色列] 尤瓦尔·赫拉利", "9787508647357", "《人类简史：从动物到上帝》是以色列新锐历史学家的一部重磅作品。从十万年前有生命迹象开始到21世纪资本、科技交织的人类发展史。十万年前，地球上至少有六个人种，为何今天却只剩下了我们自己？我们曾经只是非洲角落一个毫不起眼的族群，对地球上生态的影响力和萤火虫、猩猩或者水母相差无几。为何我们能登上生物链的顶端，最终成为地球的主宰", 83.20));
        add(new Book(5, Book.Category.OTHER, "十万个为什么", "https://img3.doubanio.com/view/subject/l/public/s27002462.jpg", "韩启德 / 李大潜 / 沈文庆 / 吴启迪", "9787532493289", "经过数百位编委、作者和编辑历时3年的辛勤努力，《十万个为什么》第六版以全新的问题、全新的体系、全新的内容、全新的样式问世。110多位中国科学院和中国工程院的院士担任编委，20多位中国一流科学家担纲分卷主编和副主编，具体负责组织相关分卷编纂工作，40多位院士亲自撰稿。同时还有来自世界各地、各个学科的700多位优秀科学家和科普作家参与了《十万个为什么》第六版的编写。全书包括600万字，7000余幅图片，共18分卷16开本的全彩色图文本。", 980.0));
    }};// 静态的默认数据

    public BookService(Context context) {
        super(context);
    }

    private static Book deserializer(Cursor cursor) {
        Book b = new Book();
        b.setId(cursor.getInt((int) cursor.getColumnIndex("id")));
        b.setName(cursor.getString((int) cursor.getColumnIndex("name")));
        b.setCategory(Book.Category.values()[cursor.getInt((int) cursor.getColumnIndex("category"))]);
        b.setImgUrl(cursor.getString((int) cursor.getColumnIndex("img_url")));
        b.setAuthor(cursor.getString((int) cursor.getColumnIndex("author")));
        b.setISBN(cursor.getString((int) cursor.getColumnIndex("isbn")));
        b.setDescription(cursor.getString((int) cursor.getColumnIndex("description")));
        b.setPrice(cursor.getDouble((int) cursor.getColumnIndex("price")));
        return b;
    }

    /**
     * @param cursor     cursor
     * @param idLocIndex 联合查询时Book的ID的位置
     * @return 反序列化后的Book
     */
    public static Book deserializer(Cursor cursor, int idLocIndex) {
        Book b = new Book();
        b.setId(cursor.getInt(idLocIndex));
        b.setName(cursor.getString((int) cursor.getColumnIndex("name")));
        b.setCategory(Book.Category.values()[cursor.getInt((int) cursor.getColumnIndex("category"))]);
        b.setImgUrl(cursor.getString((int) cursor.getColumnIndex("img_url")));
        b.setAuthor(cursor.getString((int) cursor.getColumnIndex("author")));
        b.setISBN(cursor.getString((int) cursor.getColumnIndex("isbn")));
        b.setDescription(cursor.getString((int) cursor.getColumnIndex("description")));
        b.setPrice(cursor.getDouble((int) cursor.getColumnIndex("price")));
        return b;
    }

    public List<Book> getList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        List<Book> result = new ArrayList<>();
        if (cursor.getCount() != 0)
            while (cursor.moveToNext())
                result.add(deserializer(cursor));
        cursor.close();
        db.close();
        return result;
    }

    public Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("book", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToNext();
        Book b = deserializer(cursor);
        cursor.close();
        db.close();
        return b;
    }

    public void initData() {
        SQLiteDatabase db = this.getWritableDatabase();
        initData(db);
        db.close();
    }

    public static void initData(SQLiteDatabase db) {
        for (Book b : defaultData) {
            ContentValues values = new ContentValues();
            values.put("name", b.getName());
            values.put("category", b.getCategory().ordinal());
            values.put("img_url", b.getImgUrl());
            values.put("author", b.getAuthor());
            values.put("isbn", b.getISBN());
            values.put("description", b.getDescription());
            values.put("price", b.getPrice());
            db.insert("book", null, values);
        }
    }

    // 添加一本书
    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("category", book.getCategory().ordinal());
        values.put("img_url", book.getImgUrl());
        values.put("author", book.getAuthor());
        values.put("isbn", book.getISBN());
        values.put("description", book.getDescription());
        values.put("price", book.getPrice());
        db.insert("book", null, values);
        db.close();
    }

    // 修改一本书（根据id）
    public void updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("category", book.getCategory().ordinal());
        values.put("img_url", book.getImgUrl());
        values.put("author", book.getAuthor());
        values.put("isbn", book.getISBN());
        values.put("description", book.getDescription());
        values.put("price", book.getPrice());
        db.update("book", values, "id=?", new String[]{String.valueOf(book.getId())});
        db.close();
    }

    // 根据ISBN删除书
    public void deleteBookByISBN(String isbn) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("book", "isbn=?", new String[]{isbn});
        db.close();
    }


}
