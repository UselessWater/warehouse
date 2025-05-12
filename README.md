# Warehouse 仓库管理系统

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/UselessWater/warehouse)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

> A warehouse management system for optimizing inventory, procurement, and outbound operations

## 📌 项目简介

本项目是一个基于 Spring Boot 的仓库管理系统，旨在通过技术手段提升企业仓储管理的效率与准确性。系统主要功能包括：
- 用户权限管理（角色分配、权限控制）
- 商品信息管理（商品类型、品牌、单位等）
- 库存管理（入库、出库记录）
- 采购清单管理
- 数据校验与异常处理
- 验证码生成与验证
- JWT 身份认证机制

## 🧱 技术架构

系统采用经典的 **Spring Boot + MyBatis + MySQL** 架构方案，结合 Redis 实现缓存优化，使用 JWT 进行无状态身份验证。

### ✅ 后端技术栈
- **框架**: Spring Boot 3.4.4
- **ORM**: MyBatis-Spring-Boot-Starter 3.0.4
- **数据校验**: Hibernate Validator 6.2.0.Final
- **JSON 解析**: FastJSON 2.0.57
- **JWT**: Java-JWT 3.18.3
- **验证码生成**: Kaptcha 2.3.2
- **拼音转换**: Pinyin4j 2.5.1
- **数据库**: MySQL 8.x
- **缓存**: Redis
- **其他**: Swagger/OpenAPI 接口文档、Lombok 箉辑工具

## 📦 目录结构

```bash
.
├── src/
│   ├── main/
│   │   ├── java/com/uselesswater/warehouse/
│   │   │   ├── beans/         # 实体类与 DTO 定义
│   │   │   ├── config/        # 系统配置类（Redis、Swagger、JWT 等）
│   │   │   ├── controller/    # API 控制器层
│   │   │   ├── mapper/        # MyBatis Mapper 接口
│   │   │   ├── service/       # 业务逻辑层（接口与实现）
│   │   │   ├── utils/         # 工具类（JWT 工具、加密工具等）
│   │   │   └── WarehouseApplication.java  # 启动类
│   │   └── resources/
│   │       ├── mapper/        # MyBatis XML 映射文件
│   │       ├── static/        # 静态资源（如图片）
│   │       └── application.properties  # 主配置文件
└── pom.xml                  # Maven 项目配置文件
```

## 🚀 快速开始

### 🔧 开发环境要求
- JDK 17+
- Maven 3.x
- MySQL 8.x
- Redis

### 📥 克隆项目
```bash
git clone https://github.com/UselessWater/warehouse.git
cd warehouse
```

### 🛠️ 构建并运行
```bash
mvn clean package
java -jar target/warehouse-0.0.1-SNAPSHOT.jar
```

### 📬 接口测试
- 使用 Postman 或访问 `/swagger-ui.html` 查看 API 文档进行接口测试。

## ⚠️ 注意事项
- 请确保在 `application.properties` 中正确配置数据库和 Redis 地址。
- 不要将敏感信息（如数据库密码、API 密钥等）提交到 Git 仓库。

## 🤝 贡献代码
欢迎贡献代码！如果你发现了 bug 或有改进建议，请提交 Issue 或 Pull Request。

## 📜 许可协议
该项目采用 [MIT License](LICENSE) 开源协议。