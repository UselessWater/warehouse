# Warehouse 仓库管理系统

## 📖 项目概述
Warehouse是一个基于Spring Boot开发的仓库管理系统，集成了DeepSeek AI API实现智能对话功能。系统提供同步和流式两种接口，用于人工智能聊天交互。

## ✨ 主要功能
本项目是一个基于 Spring Boot 的仓库管理系统，旨在通过技术手段提升企业仓储管理的效率与准确性。系统主要功能包括：
- 用户权限管理（角色分配、权限控制）
- 商品信息管理（商品类型、品牌、单位等）
- 库存管理（入库、出库记录）
- 采购清单管理
- 数据校验与异常处理
- 验证码生成与验证
- JWT 身份认证机制
- DeepSeek AI API集成（基础测试）
- 同步和流式API响应（基础测试）
- MySQL数据库支持
- JWT认证
- Redis缓存
- RESTful API架构
- Swagger/OpenAPI文档

## 🛠️ 技术栈
- Java 17
- Spring Boot 3.4.4
- Spring WebFlux (响应式编程)
- MyBatis
- MySQL
- Redis
- JWT认证
- Lombok
- FastJSON
- Pinyin4j (汉字转拼音工具)
- Kaptcha (验证码工具)

## 📚 项目结构
```
warehouse/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── uselesswater/
│   │   │           └── warehouse/
│   │   │               ├── beans/        # 实体类
│   │   │               ├── config/       # 配置类
│   │   │               ├── controller/   # 控制器
│   │   │               ├── deepseek/     # DeepSeek集成
│   │   │               │   ├── beans/    # DeepSeek相关实体类
│   │   │               │   ├── config/   # DeepSeek配置
│   │   │               │   ├── controller/ # DeepSeek控制器
│   │   │               │   ├── service/  # DeepSeek服务
│   │   │               │   └── properties/ # DeepSeek属性
│   │   │               ├── exception/    # 异常处理
│   │   │               ├── filter/       # 过滤器
│   │   │               ├── mapper/       # MyBatis映射
│   │   │               ├── service/      # 服务层
│   │   │               ├── utils/        # 工具类
│   │   │               └── WarehouseApplication.java # 应用入口
│   │   ├── resources/
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
# 构建项目
mvn clean install

# 运行应用
mvn spring-boot:run
```
或
```bash
java -jar target/warehouse-0.0.1-SNAPSHOT.jar
```

## ⚙️ 配置说明
项目需要在application.properties中配置DeepSeek API凭证：

```properties
# DeepSeek API配置
deepseek.api.url=https://api.deepseek.com/v1/chat/completions
deepseek.api.key=你的API密钥

# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/warehouse?useSSL=false&serverTimezone=UTC
spring.datasource.username=数据库用户名
spring.datasource.password=数据库密码

# Redis配置
spring.redis.host=localhost
spring.redis.port=6379
```

## 📝 API使用示例

### 聊天API (同步)
```http
POST /api/chat
Content-Type: application/json
Authorization: Bearer 你的JWT令牌

{
  "model": "deepseek-chat",
  "messages": [
    {
      "role": "system",
      "content": "你是一个有帮助的助手。"
    },
    {
      "role": "user",
      "content": "你好，最近怎么样？"
    }
  ],
  "temperature": 0.7
}
```

### 聊天API (流式)
```http
POST /api/chat/stream
Content-Type: application/json
Authorization: Bearer 你的JWT令牌

{
  "model": "deepseek-chat",
  "messages": [
    {
      "role": "system",
      "content": "你是一个有帮助的助手。"
    },
    {
      "role": "user",
      "content": "你好，最近怎么样？"
    }
  ],
  "stream": true,
  "temperature": 0.7
}
```

## 🧩 扩展开发

### 添加新的DeepSeek API功能
要扩展DeepSeek集成，请按以下步骤操作：

1. 在`deepseek.beans`包中添加新的请求/响应模型
2. 在`DeepSeekService.java`中实现服务方法
3. 在DeepSeek控制器中创建端点

### 构建和测试
```bash
# 运行测试
mvn test

# 构建包
mvn package
```

## 📄 许可证
本项目使用[MIT许可证](LICENSE)。

## 🤝 贡献
欢迎贡献！请随时提交Pull Request。

## 📞 联系方式
如有任何疑问，请发送邮件至[your-email@example.com](mailto:your-email@example.com)。

## 🙏 致谢
感谢所有为此项目做出贡献的开发者和使用者。
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/UselessWater/warehouse)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

> A warehouse management system for optimizing inventory, procurement, and outbound operations
