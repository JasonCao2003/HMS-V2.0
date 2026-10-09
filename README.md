# HMS · 酒店管理系统

> **H**otel **M**anagement **S**ystem —— 一套面向酒店场景的一体化管理系统，覆盖客房预订、餐饮点单、订单支付、投诉反馈、资讯发布与后台统计，并集成 **人脸识别登录** 与 **订单量预测** 能力。

<p align="left">
  <img src="./HMS/src/main/resources/static/img/front/logo.png" width="180" alt="HMS logo" />
</p>

---

## 目录

- [功能特性](#功能特性)
- [系统组成与架构](#系统组成与架构)
- [技术栈](#技术栈)
- [目录结构](#目录结构)
- [快速开始](#快速开始)
- [核心模块](#核心模块)
- [安全提示](#安全提示)
- [说明](#说明)

---

## 功能特性

| 模块 | 说明 |
| --- | --- |
| 🔐 多方式登录 | 用户 / 员工 / 管理员账号密码登录 + 图形验证码、邮箱验证码找回密码、Gitee 第三方登录、**人脸识别登录** |
| 🛏️ 客房管理 | 客房信息维护、房型分类、在线查房与预订、房间订单管理 |
| 🍽️ 餐饮管理 | 菜品维护与分类、在线点餐、购物车、餐饮订单管理 |
| 💰 订单与支付 | 客房 / 餐饮订单、订单历史、取消订单、**支付宝沙箱支付** |
| 📊 后台统计 | 订单量、营业额等数据的统计与图表展示 |
| 📰 资讯与互动 | 文章 / 话题（Topic）发布、投诉与建议反馈、我的投诉 / 话题 |
| 👤 账号与信息 | 用户信息维护、头像上传（相机拍照）、员工与管理员账号管理 |

---

## 系统组成与架构

仓库由三个相互配合的部分组成：

```text
┌──────────────────────────┐        HTTP         ┌──────────────────────────┐
│  HMS (Spring Boot 2.6.5)  │ ──────────────────▶ │  支付宝沙箱 / 邮箱服务   │
│  Thymeleaf 服务端渲染     │                     └──────────────────────────┘
│  MySQL + Redis            │
└───────────┬──────────────┘
            │ TCP Socket (:12345)
            ▼
┌──────────────────────────┐
│ FaceIdentify (Python)     │   InsightFace 人脸识别，返回用户名用于登录
└──────────────────────────┘

┌──────────────────────────┐
│ DataForcast (PyTorch)     │   读取 hms.orders，训练模型预测订单量
└──────────────────────────┘
```

1. **HMS**：主系统，Spring Boot + Thymeleaf 服务端渲染的酒店业务平台，提供全部后台与前台页面。
2. **FaceIdentify**：独立的人脸识别服务，基于 InsightFace，以 TCP Socket（默认端口 `12345`）对外提供识别；Java 端 `LoginFaceController` 通过该 Socket 发送 Base64 图片并接收匹配到的用户名完成登录。
3. **DataForcast**：PyTorch 订单量预测实验，从 MySQL `hms.orders` 拉取数据并按“月 / 星期”统计订单量，训练模型输出预测结果，用于经营分析。

---

## 技术栈

**HMS（Java 后端）**

- Spring Boot 2.6.5 · JDK 17
- Thymeleaf 3.0.15（服务端模板渲染）
- MyBatis-Plus 3.5.2 · Druid 1.1.23 · PageHelper
- MySQL 8.0 · Redis
- Apache Commons Email · javax.mail
- 支付宝 SDK（`alipay-sdk-java`，沙箱环境）
- Gitee OAuth 第三方登录 · OkHttp / HttpClient
- OpenCV（`org.openpnp:opencv`）· FastJSON · Lombok · H2（runtime）

**FaceIdentify（Python）**

- Python · InsightFace · OpenCV · NumPy · scikit-learn
- 基于 TCP Socket 的图片接收与识别服务

**DataForcast（Python）**

- Python · PyTorch · pandas · PyMySQL
- 自定义 `Dataset` / `nn.Module` 模型，产物 `model.pth`

**前端**

- Thymeleaf 页面 + 静态资源（Bootstrap、LayUI、jQuery、Chart.js、owl.carousel 等）

---

## 目录结构

```text
HMS-V2.0/
├── HMS/                     # 主系统（Spring Boot + Thymeleaf）
│   ├── pom.xml
│   ├── mvnw / mvnw.cmd
│   ├── photo/               # 用户上传图片目录
│   └── src/
│       ├── main/java/com/xupt/hms/
│       │   ├── controller/  # 25 个控制器（登录/用户/房间/餐饮/订单/支付/资讯等）
│       │   ├── service/     # 业务服务层
│       │   ├── mapper/      # MyBatis-Plus Mapper
│       │   ├── pojo/        # 实体类
│       │   └── utils/       # 支付宝、邮件、验证码、拦截器等工具
│       ├── main/resources/
│       │   ├── application.yml
│       │   ├── templates/   # admin / user / Utils 页面模板
│       │   ├── static/      # css / js / img 等静态资源
│       │   └── others/sql/  # 数据库脚本（hms.sql 等）
│       └── test/            # 测试
├── FaceIdentify/            # 人脸识别服务（Python）
│   ├── faceidentify.ipynb   # InsightFace + Socket 服务
│   ├── face_db/             # 人脸底库（按人分目录）
│   └── received_image.jpg
└── DataForcast/             # 订单量预测（PyTorch）
    ├── pytorch-95.ipynb     # 模型训练与预测
    ├── pytorch-95 - 2024.ipynb
    ├── model.pth            # 训练得到的模型
    └── *.csv                # 数据导出
```

---

## 快速开始

### 环境要求

| 组件 | 版本建议 |
| --- | --- |
| JDK | 17 |
| Maven | 3.8+（或直接使用 `./mvnw`） |
| MySQL | 8.0 |
| Redis | 5.0+ |
| Python | 3.8+（人脸识别 / 预测） |

### 1. 初始化数据库

```bash
# 创建数据库
mysql -uroot -p -e "CREATE DATABASE hms DEFAULT CHARSET utf8mb4;"

# 导入建表与初始数据
mysql -uroot -p hms < HMS/src/main/resources/others/sql/hms.sql
```

### 2. 启动主系统 HMS

```bash
cd HMS

# 按环境修改 src/main/resources/application.yml：
#   - spring.datasource.druid.*   MySQL 连接
#   - spring.redis.*              Redis 连接
#   - photo.file.dir              上传图片保存目录（Windows 示例为绝对路径）
#   - alipay.*                    支付宝沙箱配置
#   - mail.*                      邮件服务

./mvnw spring-boot:run      # Windows: mvnw.cmd spring-boot:run
# 默认地址 http://localhost:8080
```

### 3. 启动人脸识别服务 FaceIdentify（可选，用于人脸登录）

```bash
cd FaceIdentify
pip install insightface opencv-python numpy scikit-learn

# 运行 notebook；服务会监听 TCP 12345 端口
jupyter notebook faceidentify.ipynb
```

> 人脸底库放在 `FaceIdentify/face_db/`，每人一个子目录存放照片。
> 若使用 GPU，请确认已安装对应 CUDA 版 onnxruntime（notebook 中默认使用 `CUDAExecutionProvider`）。

### 4. 运行订单预测 DataForcast（可选）

```bash
cd DataForcast
pip install torch pandas pymysql

# 先按需修改 notebook 中的 MySQL 连接信息，再运行
jupyter notebook pytorch-95.ipynb
```

---

## 核心模块

### 登录与鉴权

`LoginController` 提供用户 / 员工 / 管理员登录、注册、图形验证码、邮箱验证码等；`LoginFaceController` 通过 Socket 与人脸识别服务交互实现人脸登录；`LoginGiteeController` 处理 Gitee 第三方登录回调。

### 业务管理

- 客房 / 餐饮：`RoomsController`、`FoodsController`、`TabxController`（菜品分类）
- 订单：`OrdersController`、`RoomordersController`、`BuyController`、`Cart`、`PayController`
- 资讯与互动：`ArticleController`、`TopicController`、`ComplainsController`
- 账号：`UsersController`、`EmployController`、`AdminController`、`UserInfoController`

### 后台与前台页面

- `templates/admin/`：管理员后台（账户、财务、商品、资讯等）
- `templates/user/`：用户前台（首页、客房、餐饮、订单、投诉等）
- `templates/Utils/`：相机、人脸识别、邮箱、支付等工具页

---

## 安全提示

- ⚠️ **敏感配置**：`HMS/src/main/resources/application.yml` 中包含 MySQL 口令、QQ 邮箱授权码以及支付宝沙箱私钥 / 公钥，**请勿使用真实生产凭据提交**，建议改用环境变量或配置模板。
- 仓库中存在 `.ipynb_checkpoints/`、`photo/`、`received_image.jpg` 等运行 / 上传产物，已在 `.gitignore` 中忽略；如确需清理历史，可使用 `git rm -r --cached <path>`（见提交说明）。
- 内网地址（如人脸服务 `localhost:12345`）请根据实际部署环境调整。

---

## 说明

- 本项目为课程 / 创新实践项目，仅用于学习交流。
- 人脸底库中的人像数据仅用于本地演示，请遵守相关隐私法规，勿用于其他用途。
