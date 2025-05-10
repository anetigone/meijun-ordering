### 整体项目结构
```plaintext
root-project/
├── pom.xml  # 根项目的 Maven 配置文件
├── module-api/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── api/
│   │   │   │               ├── dto/  # 数据传输对象
│   │   │   │               │   └── UserDto.java # 样例（下同）
│   │   │   │               └── service/  # 服务接口
│   │   │   │                   └── UserService.java
│   │   │   └── resources/
│   │   │       └── application.yml  # 模块的配置文件
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── example/
│   │                   └── api/
│   │                       └── service/
│   │                           └── UserServiceTest.java
│   └── pom.xml  # module-api 的 Maven 配置文件
├── module-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── service/
│   │   │   │               ├── impl/  # 服务实现类
│   │   │   │               │   └── UserServiceImpl.java
│   │   │   │               └── mapper/  # 数据访问层
│   │   │   │                   └── UserMapper.java
│   │   │   └── resources/
│   │   │       └── application.yml  # 模块的配置文件
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── example/
│   │                   └── service/
│   │                       └── impl/
│   │                           └── UserServiceImplTest.java
│   └── pom.xml  # module-service 的 Maven 配置文件
├── module-web/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── web/
│   │   │   │               ├── controller/  # 控制器层
│   │   │   │               │   └── UserController.java
│   │   │   │               ├── config/  # 配置类
│   │   │   │               │     └── WebConfig.java
│   │   │   │               └── WebApplication.java #启动器 
│   │   │   └── resources/
│   │   │       ├── static/  # 静态资源
│   │   │       │   └── css/
│   │   │       │       └── style.css
│   │   │       ├── templates/  # 模板文件
│   │   │       │   └── index.html
│   │   │       └── application.yml  # 模块的配置文件
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── example/
│   │                   └── web/
│   │                       └── controller/
│   │                           └── UserControllerTest.java
│   └── pom.xml  # module-web 的 Maven 配置文件
└── module-core/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── example/
    │   │   │           └── core/
    │   │   │               ├── entity/  # 实体类
    │   │   │               │   └── User.java
    │   │   │               └── common/  # 工具类
    │   │   │                   └── DateUtils.java
    │   │   └── resources/
    │   │       └── application.yml  # 模块的配置文件
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── example/
    │                   └── core/
    │                       └── common/
    │                           └── DateUtilsTest.java
    └── pom.xml  # module-core 的 Maven 配置文件
```

### 各模块说明
1. **`module-api`**：
    - 定义服务接口与数据传输对象（DTO）。
    - 其他模块能够依赖此模块调用服务接口。
2. **`module-service`**：
    - 实现 `module-api` 里定义的服务接口。
    - 包含数据访问层（Repository），用于与数据库交互。
3. **`module-web`**：
    - 处理 HTTP 请求，包含控制器层（Controller）。
    - 存放静态资源（如 CSS、JavaScript）和模板文件（如 Thymeleaf 模板）。
4. **`module-core`**：
    - 包含实体类（Entity）和工具类（Util）。
    - 提供项目的核心功能与基础组件。

### 模块间的依赖关系
- `module-service` 依赖 `module-api` 和 `module-core`。
- `module-web` 依赖 `module-api` 和 `module-service`。
