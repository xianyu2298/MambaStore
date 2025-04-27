# MambaStore

> 安卓大作业——书本商店

## 项目简介

由于期末要交交Android作业，自由选题，所以开发了基于 Android 平台开发的小型图书商城应用。应用支持普通用户浏览图书、购物、下单和查看订单，同时支持管理员用户登录后台管理系统，进行图书的添加、修改和删除。本项目可以直接部署运行用来交作业，如果本项目应付了你的Android作业的话，不妨给我一个Star⭐吧！

## 软件展示

![](E:\market\app\screenshot\1.gif)

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
main/
├── AndroidManifest.xml                           // 应用程序清单文件（声明Activity、权限、启动页等）
├── ic_launcher-playstore.png                     // Play商店上传使用的应用图标（512x512）
│
├── java/
│   └── com/
│       └── mamba/
│           ├── activity/
│           │   ├── AdminActivity.java            // 管理员界面（添加/删除/修改书籍）
│           │   ├── CartActivity.java             // 购物车页面
│           │   ├── DetailActivity.java           // 商品（书本）详情页
│           │   ├── LoginActivity.java            // 登录界面
│           │   ├── MainActivity.java             // 应用主界面（首页，展示所有图书）
│           │   ├── OrderActivity.java            // 订单列表页面
│           │   ├── RegisterActivity.java         // 注册新用户界面
│           │   └── SplashActivity.java           // 启动页界面（LOGO展示页）
│           │
│           ├── adapter/
│           │   ├── BookAdapter.java               // 图书列表RecyclerView适配器
│           │   ├── CartAdapter.java               // 购物车列表RecyclerView适配器
│           │   └── OrderAdapter.java              // 订单列表RecyclerView适配器
│           │
│           ├── database/
│           │   ├── DatabaseHelper.java            // SQLite数据库帮助类（创建表、升级等）
│           │   └── DatabaseManager.java           // 封装数据库增删改查操作
│           │
│           ├── model/
│           │   ├── Book.java                      // 图书实体类（title, author, price等）
│           │   ├── CartItem.java                  // 购物车条目实体类
│           │   └── Order.java                     // 订单实体类
│           │
│           └── utils/
│               ├── Constants.java                 // 全局常量类（比如表名、字段名）
│               └── PreferenceManager.java         // SharedPreferences封装（保存用户信息等）
│
├── res/
│   ├── drawable/                                  // 图片资源文件夹（图标、背景）
│   ├── layout/
│   │   ├── activity_admin.xml                     // 管理员界面布局
│   │   ├── activity_cart.xml                      // 购物车界面布局
│   │   ├── activity_detail.xml                    // 商品详情界面布局
│   │   ├── activity_login.xml                     // 登录界面布局
│   │   ├── activity_main.xml                      // 主页面布局（图书列表）
│   │   ├── activity_order.xml                     // 订单界面布局
│   │   ├── activity_register.xml                  // 注册界面布局
│   │   ├── activity_splash.xml                    // 启动页布局
│   │   ├── item_book.xml                          // 单本图书列表项布局
│   │   ├── item_cart.xml                          // 单条购物车商品项布局
│   │   └── item_order.xml                         // 单条订单项布局
│   │
│   ├── mipmap-hdpi/                               // 应用图标（不同分辨率）
│   ├── mipmap-mdpi/
│   ├── mipmap-xhdpi/
│   ├── mipmap-xxhdpi/
│   ├── mipmap-xxxhdpi/
│   │
│   ├── values/
│   │   ├── colors.xml                             // 颜色资源
│   │   ├── strings.xml                            // 字符串资源（比如APP名字、按钮文字）
│   │   ├── styles.xml                             // 应用样式（比如主题Theme）
│   │
│   └── values-night/
│       ├── colors.xml                             // 夜间模式颜色配置
│

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