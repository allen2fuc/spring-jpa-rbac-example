# 权限系统案例
使用最新的SpringBoot搭建权限系统案例，包括用户管理、角色管理、菜单管理等模块。项目采用前后端分离的架构，后端使用Spring Boot + Spring Security + Spring Data Jpa + H2 + Hutool等技术实现。
## 1. 项目介绍
### 1.1 项目背景
学习权限系统的设计与实现，以及如何使用Spring Security进行权限控制。
### 1.2 项目简介
本项目是一个权限系统的案例，主要包括用户管理、角色管理、菜单管理等模块。项目采用前后端分离的架构，后端使用Spring Boot + Spring Security + Spring Data Jpa + H2 + Hutool等技术实现。
### 1.3 项目演示
#### 1.3.1 演示地址
登陆地址: `curl -v -H "Content-Type: application/json" -X POST "localhost:8080/login" -d '{"username":"user1","password":"123456"}'`
#### 1.3.2 演示账号
| 用户名 | 密码 | 权限 |
| :---: | :---: | :---: |
| user1 | 123456 | user |
| user2 | 123456 | user |
| admin | 123456 | admin |
## 2. 项目结构
```
├── src/main/java
│   └── com
│       └── example
│           └── example 实体、仓库、启动器、响应体
│               ├── auth
│               │   ├── auditing 审计Entity
│               ├── controller 控制器
│               ├── helper 工具集合
├── src/main/resources
```
## 3. 技术选型
### 3.1 后端技术
| 技术 | 说明 | 官网 |
| :---: | :---: | :---: |
| Spring Boot | 容器+MVC框架 | https://spring.io/projects/spring-boot |
| Spring Security | 认证和授权框架 | https://spring.io/projects/spring-security |
| Spring Data Jpa | ORM框架 | https://spring.io/projects/spring-data-jpa |
| H2 | 内存数据库 | http://www.h2database.com/html/main.html |
| Lombok | 简化对象封装工具 |
| Hutool | Java工具包 | https://hutool.cn/ |

## 表设计
### 审计Entity
| 字段 | 类型  | 说明 |
| :---: |:---:| :---: |
| id | int | 主键 |
| created_by | varchar(50) | 创建人 |
| created_date | datetime | 创建时间 |
| last_modified_by | varchar(50) | 最后修改人 |
| last_modified_date | datetime | 最后修改时间 |
| deleted | tinyint | 是否删除 0未删除 1已删除 |

### 用户表
| 字段 | 类型  | 说明 |
| :---: |:---:| :---: |
| username | varchar(50) | 用户名 |
| password | varchar(50) | 密码 |

### 角色表
| 字段 | 类型  | 说明 |
| :---: |:---:| :---: |
| name | varchar(50) | 角色名 |

### 用户角色表
| 字段 | 类型  | 说明 |
| :---: |:---:| :---: |
| user_id | int | 用户id |
| role_id | int | 角色id |

### 菜单表
|  字段  | 类型  |      说明      |
|:----:|:---:|:------------:|
| name | varchar(50) |     菜单名      |
| code | varchar(50) |     菜单编码     |
| url  | varchar(50) |     菜单地址     |
| type | int | 菜单类型 1菜单 2按钮 |
| sort | int |     菜单排序     |
| pid | int |    父菜单id     |

### 角色菜单表
| 字段 | 类型  | 说明 |
| :---: |:---:| :---: |
| role_id | int | 角色id |
| menu_id | int | 菜单id |


## 4. 环境搭建
### 4.1 开发工具
| 工具 | 说明 | 官网 |
| :---: | :---: | :---: |
| IDEA | 开发IDE | https://www.jetbrains.com/idea/download |
| Navicat | 数据库连接工具 | http://www.formysql.com/xiazai.html |
| Postman | API接口调试工具 | https://www.postman.com/ |
| Typora | Markdown编辑器 | https://typora.io/ |
| ProcessOn | 流程图绘制工具 | https://www.processon.com/ |
| X-shell | Linux远程连接工具 | http://www.netsarang.com/download/software.html |
| SecureCRT | Linux远程连接工具 | https://www.vandyke.com/download/securecrt/download.html |

### 4.2 开发环境
| 工具 | 版本号 |                                          下载                                           |
| :---: |:---:|:-------------------------------------------------------------------------------------:|
| JDK | 17  | https://www.oracle.com/technetwork/java/javase/downloads/jdk17-downloads-2133151.html |
| H2 | 1.4.200 | http://www.h2database.com/html/main.html |

### 4.3 搭建步骤
#### 4.3.1 克隆源码到本地
```
git clone git@github.com:allen2fuc/spring-jpa-rbac-example.git
```
#### 4.3.2 创建数据库
```
使用的是H2内存数据库，不需要创建数据库
```

#### 4.3.3 修改配置文件
```
使用的是H2内存数据库，不需要修改配置文件
```

#### 4.3.4 运行项目
```
java -jar build/libs/spring-jpa-rbac-example-0.0.1-SNAPSHOT.jar
```

#### 4.3.5 访问项目
```
http://localhost:8080/user/list
```

## 5. 项目总结
### 5.1 项目亮点
- 使用最新的SpringBoot搭建权限系统案例，包括用户管理、角色管理、菜单管理等模块。
- 项目采用前后端分离的架构，后端使用Spring Boot + Spring Security + Spring Data Jpa + H2 + Hutool等技术实现。
- 使用Spring Security进行权限控制，实现了基于角色的权限控制。
- 使用H2内存数据库，不需要安装数据库，下载源码即可运行。
- 使用Hutool工具包，简化了代码的编写。
- 使用Lombok简化了实体类的编写。
- 使用Spring Data Jpa简化了数据访问层的编写。
- 使用Spring Security实现了基于角色的权限控制。