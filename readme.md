# MambaStore

> 安卓大作业——书本商店
>
> 附带作业报告

## 项目简介

由于期末要交交Android作业，自由选题，所以开发了基于 Android 平台开发的小型图书商城应用。应用支持普通用户浏览图书、购物、下单和查看订单，同时支持管理员用户登录后台管理系统，进行图书的添加、修改和删除。本项目可以直接部署运行用来交作业，如果本项目应付了你的Android作业的话，不妨给我一个Star⭐吧！

## 软件展示

![](app/screenshot/1.gif)

## 软件功能

### 普通用户

| 用户类型     | 可以做的事情 | 具体说明                                                    |
| ------------ | ------------ | ----------------------------------------------------------- |
| **普通用户** | 注册账号     | 在登录界面点击切换注册，输入用户名密码完成注册。            |
|              | 登录账号     | 使用注册的用户名密码登录，持久化保存。                      |
|              | 浏览图书     | 进入首页(MainActivity)，查看所有图书列表。                  |
|              | 查看书本详情 | 点击某本书，可以看到详细信息（封面、作者、价格、描述）。    |
|              | 加入购物车   | 在书本详情页点击“加入购物车”。                              |
|              | 查看购物车   | 查看购物车页面(CartActivity)，管理已添加的图书。            |
|              | 修改购物车   | 修改数量或者删除某本图书。                                  |
|              | 提交订单     | 将购物车里的商品生成一个订单。                              |
|              | 查看订单     | 查看自己下过的订单列表(OrderActivity)，包括每次购买的内容。 |
|              | 退出登录     | 可以清除登录状态，返回登录界面重新登录或换账号。            |

------

### 管理员用户（管理员账户已静态设置为admin密码admin123）：

| 用户类型       | 可以做的事情 | 具体说明                                                   |
| -------------- | ------------ | ---------------------------------------------------------- |
| **管理员用户** | 登录管理账号 | 使用账号`admin`和密码`admin123`登录。                      |
|                | 查看所有图书 | 进入管理员首页(AdminActivity)，可以浏览所有上架图书。      |
|                | 添加新书     | 点击“添加书籍”按钮，填写新书资料并保存到数据库。           |
|                | 修改已有书   | 点击“修改书籍”按钮，选择已有书，修改信息后保存。           |
|                | 删除书籍     | 点击“删除书籍”按钮，根据 ISBN 删除对应图书。               |
|                | 管理图书数据 | 维护商城里所有图书的基本信息（名字、价格、描述、图片等）。 |

所有的数据，包括用户、图书、购物车和历史订单信息，全部存储在 SQLite 数据库中。

## 目录结构

```
├── app
│   ├── build.gradle
│   ├── libs
│   ├── proguard-rules.pro
│   └── src
│       ├── main
│       │   ├── AndroidManifest.xml
│       │   ├── java
│       │   │   └── com
│       │   │       └── lsx
│       │   │           └── finalhomework
│       │   │               ├── MyAuth.java                         // 用户管理类
│       │   │               ├── MyDBHelper.java                     // 数据库访问类
│       │   │               ├── NWImageView.java                    // 支持网络图片的ImageView
│       │   │               ├── adapters
│       │   │               │   ├── MyBookRecyclerViewAdapter.java  // 图书列表Adapter
│       │   │               │   ├── MyCartRecyclerViewAdapter.java  // 购物车Adapter
│       │   │               │   └── MyOrderRecyclerViewAdapter.java // 订单列表Adapter
│       │   │               ├── controllers
|		    |  	|				        |	  ├── AddBookActivity.java            // 添加图书
|		    |	  |				        |	  ├── AdminActivity.java              // 管理员界面
│       │   │               │   ├── BookDetailFragment.java         // 图书详情
│       │   │               │   ├── BookFragment.java               // 图书列表
│       │   │               │   ├── CartFragment.java               // 购物车
|		    |	  |				        |	  ├── DeleteBookActivity.java         // 删除图书
│       │   │               │   ├── EditBookActivity.java           // 编辑图书信息
│       │   │               │   ├── LoginActivity.java              // 注册登录页面
│       │   │               │   ├── MainActivity.java               // 主页
│       │   │               │   ├── OrderDetailFragment.java        // 订单详情
│       │   │               │   └── OrderFragment.java              // 订单列表
│       │   │               └── entities                            // 实体类
│       │   │                   ├── Book.java                       // 图书
│       │   │                   ├── BookService.java                // 图书操作
│       │   │                   ├── Cart.java                       // 购物车
│       │   │                   ├── CartItem.java                   // 购物车项
│       │   │                   ├── Order.java                      // 订单
│       │   │                   ├── OrderDetail.java                // 订单详情
│       │   │                   └── OrderService.java               // 订单操作
│       │   └── res                                                 // 资源文件
│       │       ├── color
│       │       ├── drawable
│       │       ├── drawable-v24
│       │       ├── entity
│       │       ├── layout                                          // 布局文件
│       │       │   ├── activity_add_book.xml                       // 管理员添加图书页面
│       │       │   ├── activity_admin.xml                          // 管理员页面
│       │       │   ├── activity_delete_book.xml                    // 管理员删除图书页面
│       │       │   ├── activity_edit_book.xml                      // 管理员编辑图书页面
│       │       │   ├── activity_login.xml                          // 注册登录页面
│       │       │   ├── activity_main.xml                           // 主页面
│       │       │   ├── book_fragment_item.xml                      // 图书列表项
│       │       │   ├── book_fragment_item_list.xml                 // 图书列表页面
│       │       │   ├── book_fragment_item_wide.xml                 // 图书列表项（宽）
│       │       │   ├── cart_fragment_item.xml                      // 购物车列表项
│       │       │   ├── cart_fragment_item_list.xml                 // 购物车列表页面
│       │       │   ├── fragment_book_detail.xml                    // 图书详情页面
│       │       │   ├── fragment_order_detail.xml                   // 订单详情页面
│       │       │   ├── list_header.xml                             // 分组标题
│       │       │   ├── order_fragment_item.xml                     // 订单列表项
│       │       │   ├── order_fragment_item_list.xml                // 订单列表页面
│       │       │   └── pager_item.xml                              // 分组标签
│       │       ├── menu
│       │           └── bottom_nav_menu.xml                         // 底部导航菜单项
│       │       ├── mipmap-anydpi-v26
│       │       ├── mipmap-hdpi
│       │       ├── mipmap-mdpi
│       │       ├── mipmap-xhdpi
│       │       ├── mipmap-xxhdpi
│       │       ├── mipmap-xxxhdpi
│       │       ├── navigation
│       │       │   └── mobile_navigation.xml                       // 导航关系
│       │       ├── values
│       │       └── values-night
├── build.gradle
├── gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── LICENSE
└── settings.gradle


```

## 数据库结构表

### 1. `account` 表 —— 用户账号表

| 字段名     | 类型                              | 说明                       |
| ---------- | --------------------------------- | -------------------------- |
| `id`       | INTEGER PRIMARY KEY AUTOINCREMENT | 账号ID，自增主键           |
| `username` | VARCHAR(50)                       | 用户名                     |
| `password` | CHAR(32)                          | 密码（加密存储，MD5 32位） |

> **作用**：保存所有注册过的用户账号和密码。

------

### 2. `book` 表 —— 书本信息表

| 字段名        | 类型                              | 说明                           |
| ------------- | --------------------------------- | ------------------------------ |
| `id`          | INTEGER PRIMARY KEY AUTOINCREMENT | 书籍ID，自增主键               |
| `category`    | TINYINT                           | 书籍类别（数字，关联类别枚举） |
| `name`        | VARCHAR(25)                       | 书名                           |
| `img_url`     | VARCHAR(100)                      | 封面图片URL                    |
| `author`      | VARCHAR(15)                       | 作者名                         |
| `isbn`        | CHAR(13)                          | 国际标准书号（ISBN）           |
| `description` | TEXT                              | 书籍描述/介绍                  |
| `price`       | DOUBLE                            | 价格                           |

> **作用**：保存商城上所有上架的书本信息。

------

### 3. `cart` 表 —— 购物车表

| 字段名       | 类型                              | 说明                      |
| ------------ | --------------------------------- | ------------------------- |
| `id`         | INTEGER PRIMARY KEY AUTOINCREMENT | 购物车条目ID              |
| `account_id` | INTEGER                           | 外键，关联 `account` 的ID |
| `book_id`    | INTEGER                           | 外键，关联 `book` 的ID    |
| `quantity`   | INTEGER                           | 购买数量                  |

**外键约束**：

- `account_id` 外键 ➔ `account(id)`
- `book_id` 外键 ➔ `book(id)`
- 如果账户或书籍被删除，对应购物车项也跟着删除（`ON DELETE CASCADE`）

> **作用**：记录用户添加到购物车的图书和数量。

------

### 4. `book_order` 表 —— 订单表（订单主表）

| 字段名       | 类型                              | 说明                                   |
| ------------ | --------------------------------- | -------------------------------------- |
| `id`         | INTEGER PRIMARY KEY AUTOINCREMENT | 订单ID                                 |
| `account_id` | INTEGER                           | 外键，关联 `account` 的ID              |
| `order_time` | CHAR(19)                          | 下单时间（格式 "yyyy-MM-dd HH:mm:ss"） |

**外键约束**：

- `account_id` 外键 ➔ `account(id)`
- 用户删了，订单也跟着删（`ON DELETE CASCADE`）

> **作用**：记录用户每一次下单的总体信息（谁下的单、什么时间下的单）。

------

### 5. `order_detail` 表 —— 订单详情表

| 字段名        | 类型                              | 说明                                         |
| ------------- | --------------------------------- | -------------------------------------------- |
| `id`          | INTEGER PRIMARY KEY AUTOINCREMENT | 订单详情ID                                   |
| `order_id`    | INTEGER                           | 外键，关联 `book_order` 的ID                 |
| `book_id`     | INTEGER                           | 外键，关联 `book` 的ID                       |
| `order_price` | DOUBLE                            | 下单时该图书的单价（即使后来涨价了也不会变） |
| `quantity`    | INTEGER                           | 购买数量                                     |

**外键约束**：

- `order_id` 外键 ➔ `book_order(id)`（父订单被删，子详情也删）
- `book_id` 外键 ➔ `book(id)`
  - 删除书籍时，将 `book_id` 设置为空（`ON DELETE SET NULL`）

> **作用**：详细记录每一笔订单里面具体有哪些书、买了多少本、下单时的价格。
