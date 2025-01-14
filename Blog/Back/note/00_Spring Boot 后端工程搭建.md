# 一、搭建Spring Boot 多模块工程(Spring Initializer)

## 1.1、IDEA搭建工程骨架

![](images/00_00.png)

0. 通过设置修改初始化链接为阿里云：http://start.aliyun.com(否则无法使用Java8)
1. 选择 Spring Boot 项目初始化容器
2. 配置父项目名称
3. 选择项目存放位置
4. 选择Maven构建项目
5. 填写组织名称，通常为公司域名倒写
6. 选择JDK
7. 选择Java版本
8. Next -> create
9. 删除无用文件，最终目录如下：
   ![](images/00_01.png)

10. 整理pom.xml

    - Spring Boot 版本切换成2.6.3

    - 项目信息

    - 多模块项目父工程打包模式必须指定为pom

    - 子模块管理

    - 版本号统一管理

    - 统一依赖管理

    - 统一插件管理

    - 配置阿里云的Maven镜像，提升下载速度

    - 最终代码如下：

      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
          <modelVersion>4.0.0</modelVersion>
      
          <parent>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-parent</artifactId>
              <version>2.6.3</version>
              <relativePath />
          </parent>
      
          <groupId>com.cm</groupId>
          <artifactId>weblog-springboot</artifactId>
          <version>${revision}</version>
          <name>weblog-springboot</name>
          <description>前后端分离博客项目</description>
      
          <!-- 多模块项目父工程打包模式必须指定为pom -->
          <packaging>pom</packaging>
      
          <!-- 子模块管理-->
          <modules></modules>
      
          <!-- 版本号统一管理 -->
          <properties>
              <!-- 项目版本号 -->
              <version>0.0.1-SNAPSHOT</version>
              <java.version>1.8</java.version>
              <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      
              <!-- Maven 相关-->
              <maven.complier.source>${java.version}</maven.complier.source>
              <maven.complier.target>${java.version}</maven.complier.target>
          </properties>
      
          <!-- 统一依赖管理 -->
          <dependencyManagement>
      
          </dependencyManagement>
      
          <build>
              <!-- 统一插件管理 -->
              <pluginManagement>
      
              </pluginManagement>
          </build>
      
          <!-- 阿里云镜像 -->
          <repositories>
              <repository>
                  <id>aliyunmaven</id>
                  <name>aliyun</name>
                  <url>https://maven.aliyun.com/repository/public</url>
              </repository>
          </repositories>
      </project>
      ```

      

## 1.2、Web模块：入口模块

0. IDEA新建weblog-web子模块
1. 勾选lombok和Spring Web
2. 删除多余文件
3. 父项目pom.xml中添加该子模块及spring-boot-maven-plugin插件
4. 整理web模块的pom.xml，仅保留spring-boot-starter-web、lombok、spring-boot-starter-test依赖以及spring-boot-maven-plugin插件

## 1.3、Admin模块：后台管理功能模块

0. IDEA新建weblog-module-admin子模块
1. 勾选lombok
2. 删除多余文件
   ![最终目录](images/00_02.png)
3. 父项目配置子模块
4. 整理pom.xml：lombok依赖和spring-boot-starter-test依赖

## 1.4、Common模块：通用功能模块

0. IDEA 新建weblog-module-common模块

1. 勾选lombok

2. 删除多余文件

3. 依赖：lombok、guava、spring-boot-starter-test

4. 父项目配置子模块、统一版本管理、配置依赖

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
   
       <parent>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-parent</artifactId>
           <version>2.6.3</version>
           <relativePath />
       </parent>
   
       <groupId>com.cm</groupId>
       <artifactId>weblog-springboot</artifactId>
       <version>${revision}</version>
       <name>weblog-springboot</name>
       <description>前后端分离博客项目</description>
   
       <!-- 多模块项目父工程打包模式必须指定为pom -->
       <packaging>pom</packaging>
   
       <!-- 子模块管理-->
       <modules>
           <!-- 入口模块 -->
           <module>weblog-web</module>
           <!-- Admin 功能模块 -->
           <module>weblog-module-admin</module>
           <!-- Common 通用功能模块-->
           <module>weblog-module-common</module>
       </modules>
   
       <!-- 版本号统一管理 -->
       <properties>
           <!-- 项目版本号 -->
           <version>0.0.1-SNAPSHOT</version>
           <java.version>1.8</java.version>
           <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
   
           <!-- Maven 相关-->
           <maven.complier.source>${java.version}</maven.complier.source>
           <maven.complier.target>${java.version}</maven.complier.target>
   
           <!-- 依赖包版本 -->
           <lombok.version>1.18.28</lombok.version>
           <guava.version>31.1-jre</guava.version>
           <commons-lang3.version>3.12.0</commons-lang3.version>
       </properties>
   
       <!-- 统一依赖管理 -->
       <dependencyManagement>
           <dependencies>
               <dependency>
                   <groupId>com.cm</groupId>
                   <artifactId>weblog-module-admin</artifactId>
                   <version>${revision}</version>
               </dependency>
   
               <dependency>
                   <groupId>com.cm</groupId>
                   <artifactId>weblog-module-common</artifactId>
                   <version>${revision}T</version>
               </dependency>
   
               <!-- 常用工具库-->
               <dependency>
                   <groupId>com.google.guava</groupId>
                   <artifactId>guava</artifactId>
                   <version>${guava.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>org.apache.commons</groupId>
                   <artifactId>commons-lang3</artifactId>
                   <version>${commons-lang.version}</version>
               </dependency>
           </dependencies>
       </dependencyManagement>
   
       <build>
           <!-- 统一插件管理 -->
           <pluginManagement>
               <plugins>
                   <plugin>
                       <groupId>org.springframework.boot</groupId>
                       <artifactId>spring-boot-maven-plugin</artifactId>
                       <configuration>
                           <excludes>
                               <exclude>
                                   <groupId>org.projectlombok</groupId>
                                   <artifactId>lombok</artifactId>
                               </exclude>
                           </excludes>
                       </configuration>
                   </plugin>
               </plugins>
           </pluginManagement>
       </build>
   
       <!-- 阿里云镜像 -->
       <repositories>
           <repository>
               <id>aliyunmaven</id>
               <name>aliyun</name>
               <url>https://maven.aliyun.com/repository/public</url>
           </repository>
       </repositories>
   </project>
   ```

5. 子模块之间的依赖关系：weblog-web依赖于weblog-module-common和weblog-module-admin、weblog-module-admin依赖于weblog-module-common

## 1.5、测试

> 在测试前要检查好IDEA中maven的配置

对weblog-springboot执行maven的clean、package命令(将跳过单元测试勾选上)

打包成功后会在weblog-web的target目录下生成weblog-web-0.0.1-SNAPSHOT.jar

**测试启动**

在weblog-web下找到启动类WeblogWebApplication运行main方法

**启动成功**

![](images/00_03.png)

