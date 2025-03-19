# 一、搭建 Spring Boot 多模块工程（通过 Spring Initializr）

## 什么是多模块项目？

多模块项目是项目构建中的概念。拿 Maven 来说，多模块项目（Multi-Module Project）是其一个重要特性，**它允许我们在一个项目中管理多个子模块。**

在一个 Maven 多模块项目中，每个模块都是一个独立的项目，拥有自己的 POM 文件（Project Object Model，项目对象模型）。这些模块可以互相依赖，也可以被其他项目依赖。但是，所有的模块都会被统一管理，它们共享同一套构建系统和依赖管理。

Maven 多模块项目的结构大概是下面这样的：

```perl
my-app/  (父项目)
  |- pom.xml
  |- my-module1/  (子模块1)
  |    |- pom.xml
  |- my-module2/  (子模块2)
       |- pom.xml
  | ... (实际企业级项目中，会分非常多的模块)     
```

在这个例子中，`my-app` 是父项目，`my-module1` 和 `my-module2` 是它的子模块。每个模块都有自己的 `pom.xml` 文件。

## 为什么需要多模块项目？

主要有以下几个原因：

- **代码组织**：在大型项目中，我们经常需要把代码分成多个模块，以便更好地组织代码。每个模块可以聚焦于一个特定的功能或领域，这样可以提高代码的可读性和可维护性。
- **依赖管理**：Maven 多模块项目可以帮助我们更好地管理项目的依赖。在父项目的 POM 文件中，我们可以定义所有模块共享的依赖，这样可以避免重复的依赖定义，也方便我们管理和升级依赖。
- **构建和部署**：Maven 多模块项目的另一个优点是它可以统一管理项目的构建和部署。我们只需要在父项目中执行 Maven 命令，就可以对所有模块进行构建和部署。这大大简化了开发者的工作。

## IDEA 搭建 Spring Boot 多模块工程骨架

### 开始动手

首先选择一个位置，新建一个名为 `weblog` 的工程目录，用于统一存放后端项目和前端项目，方便后续管理：

![img](https://img.quanxiaoha.com/quanxiaoha/169105275897127)

### 创建父项目

打开 IDEA, 依次点击菜单 *File -> New -> Project*, 准备新建**父项目** :

![img](https://img.quanxiaoha.com/quanxiaoha/169105299374199)

> **注意**：部分小伙伴反馈说，IDEA 通过 Spring Initializr 来创建 Spring Boot 项目， 突然不支持勾选 Java 8 了 ， 可以如下图所示，点击小齿轮，将初始化链接换成阿里云的 `http://start.aliyun.com`, 就可以正常选择 Java 8 了：![img](https://img.quanxiaoha.com/quanxiaoha/170514859046276)

- ①：选择 Spring Boot 项目初始化器；
- ②：填写父项目名称;
- ③：选择新建项目所在的位置；
- ④：选择通过 Maven 来构建项目；
- ⑤：填写 Group 组织名称，通常为公司域名倒写，如 `com.quanxiaoha`；
- ⑥：选择前面小节中已经安装好的 JDK 1.8 版本；
- ⑦：选择 Java 版本，和 JDK 版本保持一致，选择 8；

点击 Next ，进入下一步，配置 Spring Boot：

![img](https://img.quanxiaoha.com/quanxiaoha/169105331805677)

- ①：选择 Spring Boot 版本，这里暂选择一个 `2.7` 版本，后面我们再手动修改为 `2.6.3` 版本；

> 注意，*本项目使用的 2.6.3 版本，请务必和我保持一个版本*，高版本可能有相关 API 的变化，导致相关代码不适用，以及其他相关环境问题。

- ②：点击创建项目；

因为这是个父项目，专门负责统一管理子模块、依赖版本等，创建完成后，在左边导航栏中，先删除下图中标注的无用文件：

![img](https://img.quanxiaoha.com/quanxiaoha/169105367136250)

删除成功后，目录结构如下：

![img](https://img.quanxiaoha.com/quanxiaoha/169105385573361)

接下来，开始整理一下父项目的 `pom.xml` 文件，整理后内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <!-- 将 Spring Boot 的版本号切换成 2.6 版本 -->
        <version>2.6.3</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.quanxiaoha</groupId>
    <artifactId>weblog-springboot</artifactId>
    <version>${revision}</version>
    <name>weblog-springboot</name>
    <!-- 项目描述 -->
    <description>前后端分离博客 Weblog By 犬小哈</description>

    <!-- 多模块项目父工程打包模式必须指定为 pom -->
    <packaging>pom</packaging>

    <!-- 子模块管理 -->
    <modules>
    </modules>


    <!-- 版本号统一管理 -->
    <properties>
        <!-- 项目版本号 -->
        <revision>0.0.1-SNAPSHOT</revision>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        
        <!-- Maven 相关 -->
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
        
    </properties>

    <!-- 统一依赖管理 -->
    <dependencyManagement>

    </dependencyManagement>

    <build>
        <!-- 统一插件管理 -->
        <pluginManagement>

        </pluginManagement>
    </build>
    
    <!-- 使用阿里云的 Maven 仓库源，提升包下载速度 -->
    <repositories>
        <repository>
            <id>aliyunmaven</id>
            <name>aliyun</name>
            <url>https://maven.aliyun.com/repository/public</url>
        </repository>
    </repositories>
</project>
```

### 创建 web 访问模块（打包也在这个模块中进行）

接下来，我们开始创建父项目下面的子模块。在父项目上**右键**，添加模块 `Module`:

![img](https://img.quanxiaoha.com/quanxiaoha/169105542889388)

还是和上面创建父项目差不多的步骤，命名一个 `weblog-web` 模块，此模块是项目的入口，Maven 打包的打包插件放在这里，同时，和博客前台页面展示相关的功能也统一放在此模块下：

![img](https://img.quanxiaoha.com/quanxiaoha/169105561968358)

勾选上 `Lombok` 和 `Spring Web` 依赖，点击 `create` 创建模块：

![img](https://img.quanxiaoha.com/quanxiaoha/169105572621429)

创建完成后，删除掉无用的一些目录和文件，删完后，大致如下：

![img](https://img.quanxiaoha.com/quanxiaoha/169105662157068)

删除完成后，在父项目的 `pom.xml` 中添加该子模块，以及添加 `spring-boot-maven-plugin` ：

```xml
<!-- 子模块管理 -->
<modules>
    <!-- 入口模块 -->
    <module>weblog-web</module>
</modules>

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
```

添加完成后，开始编辑 `weblog-web` 模块中的 `pom.xml`，只保留了一些需要的配置，最终配置内容如下:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <!-- 指定父项目为 weblog-springboot -->
    <parent>
        <groupId>com.quanxiaoha</groupId>
        <artifactId>weblog-springboot</artifactId>
        <version>${revision}</version>
    </parent>

    <groupId>com.quanxiaoha</groupId>
    <artifactId>weblog-web</artifactId>
    <name>weblog-web</name>
    <description>weblog-web (入口项目，负责博客前台展示相关功能，打包也放在这个模块负责)</description>

    <dependencies>
        <!-- Web 依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- 免写冗余的 Java 样板式代码 -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- 单元测试 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### 你可能会遇到的问题

#### 依赖爆红解决方案

若 `pom.xml` 文件中的依赖出现**爆红**等情况，通过点击右侧栏 *Reload* 图标，重新加载 Maven 项目来解决：

![img](https://img.quanxiaoha.com/quanxiaoha/169105643995431)

#### 控制台出现警告解决方案

在执行 Maven 命令时，若使用 IDEA 默认的 Maven 配置，可能会导致后面打包控制台出现如下警告：

![img](https://img.quanxiaoha.com/quanxiaoha/169105725140439)

可在点击 *File -> Settings* 找到 Maven 选项，默认是使用 IDEA 自带的 Maven 版本, 这里将 *Maven home path* 设置为前面小节中，我们手动安装好的 Maven 路径，再次执行 Maven 命令，即警告信息消失了：

![img](https://img.quanxiaoha.com/quanxiaoha/169105758106560)

#### 执行 mvn clean package 命令报错

执行 `mvn clean package` 等相关命令时, 控制台提示如下图所示的错误，这个问题星球内好多人反馈都有犯：

![img](https://img.quanxiaoha.com/quanxiaoha/169431387104796)

问题原因是，在子模块中的 `<parent></parent>` 节点下添加了 `<relativePath/>` ，注意，它只需要在父级项目中添加一次即可，子模块中是无需添加这个的，一定要去掉：

![img](https://img.quanxiaoha.com/quanxiaoha/169431394475155)

### 创建 Admin 管理后台功能模块

再次新建一个负责 Admin 管理后台功能的子模块，命名为 `weblog-module-admin` ，此模块用于统一放置和 Admin 管理后台相关的功能:

![img](https://img.quanxiaoha.com/quanxiaoha/169107147955563)

依赖仅需勾选 `Lombok` 即可，后面需要什么再添加：

![img](https://img.quanxiaoha.com/quanxiaoha/169105897985541)

创建成功后，在父项目的 `pom.xml` 文件中添加该子模块：

```xml
 <!-- 子模块管理 -->
    <modules>
        <!-- 入口模块 -->
        <module>weblog-web</module>
        <!-- 管理后台 -->
        <module>weblog-module-admin</module>
    </modules>
```

然后，和前面一样，删除掉哪些无用的文件夹、文件，删除完成后，如下图所示：

![img](https://img.quanxiaoha.com/quanxiaoha/169139368655873)

另外，还需要将 `resource` 目录下的配置文件，和 `Application` 启动类删除掉。配置文件统一放在 `weblog-web` 入口模块中来管理：

![img](https://img.quanxiaoha.com/quanxiaoha/169107164959721)

最后，再整理一下此模块的 `pom.xml` , 内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!-- 指定父项目为 weblog-springboot -->
	<parent>
		<groupId>com.quanxiaoha</groupId>
		<artifactId>weblog-springboot</artifactId>
		<version>${revision}</version>
	</parent>

	<groupId>com.quanxiaoha</groupId>
	<artifactId>weblog-module-admin</artifactId>
	<name>weblog-module-admin</name>
	<description>weblog-admin (负责管理后台相关功能)</description>

	<dependencies>
		<!-- 免写冗余的 Java 样板式代码 -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<!-- 单元测试 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

</project>
```

### 创建 common 通用功能子模块

依葫芦画瓢，按照上面的步骤，再次创建 `weblog-module-common` 子模块，此模块专门用于存放一些通用的功能，如接口的日志切面、全局异常管理等等。

![img](https://img.quanxiaoha.com/quanxiaoha/169107204469343)

创建成功后，在父项目的 `pom.xml` 文件中添加该子模块：

```xml
    <!-- 子模块管理 -->
    <modules>
        <!-- 入口模块 -->
        <module>weblog-web</module>
        <!-- 管理后台 -->
        <module>weblog-module-admin</module>
        <!-- 通用模块 -->
        <module>weblog-module-common</module>
    </modules>
```

和 `weblog-mudule-admin` 子模块一样，再删除掉无用的文件，最终其 `pom.xml` 内容如下:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.quanxiaoha</groupId>
		<artifactId>weblog-springboot</artifactId>
		<version>${revision}</version>
	</parent>

	<groupId>com.quanxiaoha</groupId>
	<artifactId>weblog-module-common</artifactId>
	<name>weblog-module-common</name>
	<description>weblog-module-common (此模块用于存放一些通用的功能)</description>

	<dependencies>
		<!-- 免写冗余的 Java 样板式代码 -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		
		<!-- 常用工具库 -->
        <dependency>
              <groupId>com.google.guava</groupId>
              <artifactId>guava</artifactId>
        </dependency>

		<!-- 单元测试 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

</project>
```

### 父项目统一版本管理

在父项目的 `pom.xml` 中，将这几个子模块统一声明一下，另外添加相关常用的工具包，如 `commons-lang3` 、`guava` ：

```xml
<!-- 版本号统一管理 -->
    <properties>
        ...

        <!-- 依赖包版本 -->
        <lombok.version>1.18.28</lombok.version>
        <guava.version>31.1-jre</guava.version>
        <commons-lang3.version>3.12.0</commons-lang3.version>
    </properties>
    
<!-- 统一依赖管理 -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.quanxiaoha</groupId>
                <artifactId>weblog-module-admin</artifactId>
                <version>${revision}</version>
            </dependency>

            <dependency>
                <groupId>com.quanxiaoha</groupId>
                <artifactId>weblog-module-common</artifactId>
                <version>${revision}</version>
            </dependency>

            <!-- 常用工具库 -->
            <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>${guava.version}</version>
            </dependency>

            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
                <version>${commons-lang3.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>    
```

### 子模块之间的依赖关系

这几个子模块之间，互相还存在依赖关系，我们也需要引入一下。如入口模块 `weblog-web` 依赖于 `weblog-module-admin` 和 `weblog-module-common`，在其 `pom.xml` 添加如下：

```xml
<dependencies>
        <dependency>
            <groupId>com.quanxiaoha</groupId>
            <artifactId>weblog-module-common</artifactId>
        </dependency>

        <dependency>
            <groupId>com.quanxiaoha</groupId>
            <artifactId>weblog-module-admin</artifactId>
        </dependency>

        ...
    </dependencies>
```

以及 `weblog-module-admin` 依赖于 `weblog-module-common`，在其 `pom.xml` 添加如下：

```xml
<dependencies>
		<dependency>
			<groupId>com.quanxiaoha</groupId>
			<artifactId>weblog-module-common</artifactId>
		</dependency>

		...
	</dependencies>
```

### 测试看看，有没有报错

在 IDEA 中, 对 `weblog-springboot` 父项目执行 Maven 的 `clean package` 打包命令，看看是否能够正常给项目打包：

> ⚠️ 注意：企业开发中，一般都比较追求敏捷开发，可能不会写太多的单元测试。本实战项目同样为了追求开发进度，不会编写单元测试，所以多模块的单元测试并未配置完成，这里，需要如下图所示，将*跳过单元测试勾选上*，**否则，你打包的时候会报错**。
>
> ![img](https://img.quanxiaoha.com/quanxiaoha/169424850908858)

![img](https://img.quanxiaoha.com/quanxiaoha/169110814675585)

- ①：执行 `mvn clean` 命令；
- ②：执行 `mvn package` 打包命令；

如果没有问题，控制台会提示 *BUILD SUCCESS* 表示构建成功：

![img](https://img.quanxiaoha.com/quanxiaoha/169110824402533)

打包成功后的 `Jar` 包，可以在项目目录的 `weblog-web` 入口模块中的 `/target` 目录下找到：

![img](https://img.quanxiaoha.com/quanxiaoha/169424913046620)

## 启动该 Spring Boot 项目

进入 `weblog-web` 入口项目，找到 `WeblogWebApplication` 启动类，**点击启动图标**，运行此项目：

![img](https://img.quanxiaoha.com/quanxiaoha/169139566366586)

![img](https://img.quanxiaoha.com/quanxiaoha/169139571908352)

若控制台能够正确打印如下日志，则表示 Spring Boot 启动成功：

![img](https://img.quanxiaoha.com/quanxiaoha/169139580640766)

至此，搭建 Spring Boot 多模块工程就成功啦，是不是还挺简单的。

# 二、Spring Boot 项目多环境配置

## 什么是多环境配置？

在日常的开发工作中，我们的应用程序往往需要在不同的环境中运行，例如：开发环境、测试环境和生产环境。每个环境中的配置参数可能都会有所不同，例如数据库连接信息、文件服务器的 IP 地址等。Spring Boot 提供了非常方便的方式来管理这些不同环境的配置。

## Spring Profile 介绍

Spring Profile 是 Spring 框架用于处理不同环境配置的解决方案。Profile 可以帮助我们在不改变应用代码的情况下，根据当前环境动态地激活或者切换不同的配置。

Spring Boot 为每个 Profile 提供了一个独立的 `application.properties`（或 `application.yml`）配置文件。默认情况下，Spring Boot 使用的是 `application.properties` 文件。当你激活一个特定的 Profile 时，Spring Boot 会查找名为 `application-{profile}.properties` 的文件，并把其中的属性加载到 Spring Environment 中。

## 如何创建和使用 Profile

假设我们有三个环境：开发（`dev`）、测试（`test`）、生产（`prod`）。那么我们可以创建以下几个配置文件：

- `application.properties`: 存放所有环境通用的配置。
- `application-dev.properties`: 存放开发环境的特殊配置。
- `application-test.properties`: 存放测试环境的特殊配置。
- `application-prod.properties`: 存放生产环境的特殊配置。

## 配置文件内容示例

在 `application.properties` 中，我们可能会**配置一些公共的属性**：

```ini
# 应用名称
app.name=Weblog
```

在 `application-dev.properties` 中，我们可以**配置一些一些开发环境特定的配置**，如本地调试要使用的数据库连接等：

```bash
# 数据库连接信息
spring.datasource.url=jdbc:mysql://localhost:3306/dev_db
```

在 `application-test.properties` 和 `application-prod.properties` 中也可以类似地配置测试和生产环境的特定属性。

## 如何激活 Profile

在 Spring Boot 中，有多种方式可以激活 Profile：

- 1、 **在 Spring Boot 的 application.properties 或 application.yml 中设置 spring.profiles.active 属性**。例如，你可以添加以下配置来激活 "dev" 环境：

  ```ini
  # 企业级项目开发中，一般项目默认会激活 dev 环境
  spring.profiles.active=dev
  ```

- 2、 **在命令行中设置 spring.profiles.active 系统属性**。例如，你可以使用以下命令来启动你的应用，并激活 "prod" 环境：

  ```bash
  # 企业级项目开发中，针对生产环境，一般通过启动命令再指定激活 prod 环境
  java -jar $APP_NAME --spring.profiles.active=prod
  ```

- 3、 **在操作系统的环境变量中设置 SPRING_PROFILES_ACTIVE**（这种方式比较少用到）。

## 开始动手

### 新建多环境配置文件

针对 `weblog` 项目来说，由于它的定位是个人博客，不涉及团队开发，我们只需要配置两个环境就 OK 了：

- `dev` : 本地开发环境，兼顾测试，测试完成后直接上线；
- `prod` : 生产环境；

打开 `weblog-web` 入口模块，将 `/resources` 目录下的 `/static` 和 `/templates` 都删除掉，它们用于服务端渲染的项目，因为我们是前后端分离，所以不需要：

![img](https://img.quanxiaoha.com/quanxiaoha/169139949860827)

然后，将 `application.properties` 的后缀改为 `.yml` 格式，另外，再新建 `application-dev.yml` 开发环境与 `application-prod.yml` 生产环境:

![img](https://img.quanxiaoha.com/quanxiaoha/169139969084183)

### 科普：什么是 `.yml` 格式？它和 `.properties` 有什么区别？

`.yml`（或`.yaml`）和`.properties`都是用来存储配置信息的文件格式，分别代表了 YAML（YAML Ain't Markup Language）和 Properties 两种格式。

**YAML（.yml 或 .yaml）**

YAML 是一种人类可读的数据序列化标准，通常用于配置文件和在网络上交换数据，如 API 交互等。YAML 以数据为中心，采用缩进式层次结构，从而使得配置文件更加清晰，易于阅读和书写。此外，YAML 支持复杂的数据结构，如列表和键值对，因此，YAML 的使用适用于需要表示复杂数据结构的场合。

一个简单的 YAML 文件示例：

```yaml
person:
  name: John Doe
  age: 30
  hobbies:
    - Reading
    - Sports
```

**Properties（.properties）**

Properties 格式是 Java 平台原生的一种配置文件格式。它的格式简单，每一行都是一个键值对，键和值之间用等号（=）或冒号（:）分隔。Properties 文件不支持复杂的数据结构，只支持字符串类型的键值对，因此，它的使用适用于简单的配置场景。

一个简单的 Properties 文件示例：

```ini
person.name=John Doe
person.age=30
```

**YAML vs Properties**

- 可读性：YAML 的可读性更强，它采用缩进表示层次关系，而 Properties 文件则通过点分隔符表示层次关系。
- 数据结构：YAML 支持复杂的数据结构，包括列表和映射，而 Properties 文件只支持字符串类型的键值对。
- 兼容性：Properties 是 Java 平台的标准配置格式，几乎所有的 Java 程序都可以直接读取。而 YAML 需要相应的解析库才能解析。

在 Spring Boot 应用中，你可以根据自己的实际需求，选择使用 YAML 还是 Properties 格式的配置文件。如果你的配置比较简单，Properties 格式可能会更好些。如果你的配置比较复杂，或者你希望配置文件更具可读性，那么 YAML 格式可能是更好的选择。`weblog` 项目选择使用 `.yml` 格式。

### 激活默认环境

编辑 `applicaiton.yml`, 添加通用配置：

```yaml
spring:
  profiles:
    # 默认激活 dev 环境
    active: dev
```

由于目前还是项目搭建阶段，暂时就配置以上内容，后面需要啥，如数据库连接信息、Elasticsearch 连接信息等，用到时再添加。

## 验证是否生效

配置完成后，再次启动项目，观察控制台日志，若看到 `The following profiles are active: dev` , 则表示激活 `dev` 环境成功啦：

![img](https://img.quanxiaoha.com/quanxiaoha/169140066097812)

# 三、Spring Boot 整合 Logback 日志

在构建任何应用程序时，良好的日志管理都是必不可少的。**日志可以帮助我们监控、调试和跟踪代码的运行情况。** 在本小节中，小哈将介绍如何在 Spring Boot 中整合 Logback 这个流行的日志管理框架。

## 什么是 Logback？

Logback 是日志框架 SLF4J 的一个实现，它被设计用来替代 `log4j`。Logback 提供了更高的性能，更丰富的日志功能和更好的配置选项。

## 为什么要用它？

在 Spring Boot 中，Logback 是默认的日志实现，至于官方为何用它作为默认日志组件，有以下几个原因：

1. **性能**：Logback 在性能上超越了许多其他的日志实现，尤其是在高并发环境下。
2. **灵活性**：Logback 提供了高度灵活的日志配置方式，支持从 XML、Groovy 以及编程式的方式进行配置。
3. **功能丰富**：除了基本的日志功能，Logback 还提供了如日志归档、日志级别动态修改、事件监听等高级功能。
4. **与 SLF4J 集成**：SLF4J 是一个日志门面（facade），使得应用程序可以在运行时更换日志实现。Logback 作为 SLF4J 的一个原生实现，可以无缝地与其集成。
5. **与 Spring Boot 的自动配置集成**：Spring Boot 提供了对 Logback 的自动配置，这意味着开发者无需手动配置 Logback，只需提供一个简单的配置文件即可。

## 引入依赖

由于 Spring Boot 默认使用 Logback，所以当你在 `pom.xml` 中加入 `spring-boot-starter-web` 依赖时，它会自动包含 Logback 相关依赖，无需额外添加：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## 自定义 Logback 配置

为了满足特定的日志需求，我们通常会自定义 Logback 配置。这里需要注意，配置文件我们统一放置在 `weblog-web` 模块中，方便统一管理。然后在 `src/main/resources` 目录下，创建一个名为 `logback-weblog.xml` 的文件。

![img](https://img.quanxiaoha.com/quanxiaoha/169162974344630)

内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration >
    <jmxConfigurator/>
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />

    <!-- 应用名称 -->
    <property scope="context" name="appName" value="weblog" />
    <!-- 自定义日志输出路径，以及日志名称前缀 -->
    <property name="LOG_FILE" value="/app/weblog/logs/${appName}.%d{yyyy-MM-dd}"/>
    <property name="FILE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n"/>
    <!--<property name="CONSOLE_LOG_PATTERN" value="${FILE_LOG_PATTERN}"/>-->

    <!-- 按照每天生成日志文件 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- 日志文件输出的文件名 -->
        <FileNamePattern>${LOG_FILE}-%i.log</FileNamePattern>
        <!-- 日志文件保留天数 -->
        <MaxHistory>30</MaxHistory>
        <!-- 日志文件最大的大小 -->
        <TimeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
        <maxFileSize>10MB</maxFileSize>
        </TimeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
        <!-- 格式化输出：%d 表示日期，%thread 表示线程名，%-5level：级别从左显示 5 个字符宽度 %errorMessage：日志消息，%n 是换行符-->
        <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- dev 环境（仅输出到控制台） -->
    <springProfile name="dev">
        <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
        <root level="info">
            <appender-ref ref="CONSOLE" />
        </root>
    </springProfile>

    <!-- prod 环境（仅输出到文件中） -->
    <springProfile name="prod">
        <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
        <root level="INFO">
            <appender-ref ref="FILE" />
        </root>
    </springProfile>
</configuration>
```

因为打印日志到文件只需要在生产环境开启就行了，所以，使日志生效的配置放到 `application-prod.yml` 文件中就行了：

```yaml
#=================================================================
# log 日志
#=================================================================
logging:
  config: classpath:logback-weblog.xml
```

## 打印日志

为了测试一下日志是否能够正常打印，我们在单元测试包下的 `WeblogWebApplicationTests` 类中：

![img](https://img.quanxiaoha.com/quanxiaoha/169149364280917)

新建一个 `testLog()` 测试方法，同时添加 Lombok 的 `@Slf4j` 日志注解，它可以帮助我们自动的生成日志实例，示例代码如下：

```typescript
package com.example.weblog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testLog() {
        log.info("这是一行 Info 级别日志");
        log.warn("这是一行 Warn 级别日志");
        log.error("这是一行 Error 级别日志");

        // 占位符
        String author = "犬小哈";
        log.info("这是一行带有占位符日志，作者：{}", author);
    }

}
```

### 控制台日志

运行该测试方法，若控制台日志输出如下，表示日志组件运行正常运行，在后续的功能开发中，我们会频繁地使用到它：

![img](https://img.quanxiaoha.com/quanxiaoha/169149388355488)

### 日志输出到文件中

接下来，我们再验证一下生产环境是否能够使文件输出到文件中。先将 `application.yml` 的环境改为 `prod` ，一旦激活 `prod`, 则日志将被输出到文件中:

```yaml
spring:
  profiles:
    # 默认激活 dev 环境
    active: prod
```

因为小哈用的 Windows 系统做的演示，还需要修改 `logback-weblog.xml` , 将日志输出路径自定义为 Windows 路径：

![img](https://img.quanxiaoha.com/quanxiaoha/169149833826499)

> 💡 测试完成后，记得将 `profile` 重新改回 `dev` 环境，以及路径改回 Linux 格式路径。

重新运行该单元测试，查看该路径下是否正确输出日志文件：

![img](https://img.quanxiaoha.com/quanxiaoha/169149856299780)

可以看到日志文件输出正常。至此，Spring Boot 整合 Logback 日志就算搞定啦~

# 四、Spring Boot 自定义注解，实现 API 请求日志切面

## 前言

在后端业务中，对每次请求的入参、被请求类、方法，以及出参、执行耗时等信息进行日志打印，是很有必要的，有了这些信息，当某个接口出现问题时，可以帮助我们快速完成问题的追踪。那么，Spring Boot 中要如何实现呢? 本小节中，小哈就带着大家通过自定义注解和切面编程（AOP）的方法，轻松实现此功能。

## 什么是自定义注解 (Custom Annotations)？

Java 注解是从 Java 5 开始引入的，它为我们提供了一种元编程的方法，允许我们在不改变代码逻辑的情况下为代码添加元数据。这些元数据可以在编译时或运行时通过反射被访问。

自定义注解就是用户定义的，用于为代码提供元数据的注解。例如，本小节中自定义的 `@ApiOperationLog` 注解，它用来表示一个方法在执行时需要被记录日志。

## 什么是 AOP (面向切面编程)？

AOP（Aspect-Oriented Programming，面向切面编程）是一个编程范式，它提供了一种能力，让开发者能够模块化跨多个对象的横切关注点（例如日志、事务管理、安全等）。

主要概念包括：

- **切点 (Pointcuts)**: 定义在哪里应用切面（即在哪里插入横切关注点的代码）。
- **通知 (Advices)**: 定义在特定切点上要执行的代码。常见的通知类型有：前置通知、后置通知、环绕通知等。
- **切面 (Aspects)**: 切面将切点和通知结合起来，定义了在何处和何时应用特定的逻辑。

例如，使用AOP，我们可以为所有使用 `@ApiOperationLog` 注解的方法自动添加日志逻辑，而不需要在每个方法中手动添加。

## 开始动手

### 添加依赖

在父项目 `weblog-springboot` 中的 `pom.xml` 文件中，添加 `jackson` 工具，它用于将出入参转为 `json` 字符串：

```xml
<!-- 版本号统一管理 -->
<properties>
    ...省略
    <jackson.version>2.15.2</jackson.version>
</properties>

<!-- 统一依赖管理 -->
    <dependencyManagement>
        <dependencies>
            ...省略

            <!-- Jackson -->
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>${jackson.version}</version>
            </dependency>

        </dependencies>
    </dependencyManagement>
```

因为日志切面属于前台、后台管理接口通用的功能，所以和该功能相关代码可以统一放置于 `weblog-module-common` 模块中。

打开 `weblog-module-common` 模块中的 `pom.xml` , 引用具体依赖：

```xml
<dependencies>
		...省略

		<!-- AOP 切面 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>

		<!-- Jackson -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
		</dependency>
	</dependencies>
```

### 自定义注解

在 `weblog-module-common` 通用模块下，新建一个名为 `aspect` 的包，用于放置切面相关的功能类：

![img](https://img.quanxiaoha.com/quanxiaoha/172968609014221)

接着，在其中创建一个名为 `ApiOperationLog` 的自定义注解：

```java
package com.quanxiaoha.weblog.common.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";

}
```

元注解说明：

- `@Retention(RetentionPolicy.RUNTIME)`： 这个元注解用于指定注解的保留策略，即注解在何时生效。`RetentionPolicy.RUNTIME` 表示该注解将在运行时保留，这意味着它可以通过反射在运行时被访问和解析。
- `@Target({ElementType.METHOD})`： 这个元注解用于指定注解的目标元素，即可以在哪些地方使用这个注解。`ElementType.METHOD` 表示该注解只能用于方法上。这意味着您只能在方法上使用这个特定的注解。
- `@Documented`： 这个元注解用于指定被注解的元素是否会出现在生成的Java文档中。如果一个注解使用了 `@Documented`，那么在生成文档时，被注解的元素及其注解信息会被包含在文档中。这可以帮助文档生成工具（如 JavaDoc）在生成文档时展示关于注解的信息。

### 日志切面

#### aspectj 注解说明

在配置 AOP 切面之前，我们需要了解下 `aspectj` 相关注解的作用：

- **@Aspect**：声明该类为一个切面类；
- **@Pointcut**：定义一个切点，后面跟随一个表达式，表达式可以定义为切某个注解，也可以切某个 package 下的方法；

切点定义好后，就是围绕这个切点做文章了：

- **@Before**: 在切点之前，织入相关代码；
- **@After**: 在切点之后，织入相关代码;
- **@AfterReturning**: 在切点返回内容后，织入相关代码，一般用于对返回值做些加工处理的场景；
- **@AfterThrowing**: 用来处理当织入的代码抛出异常后的逻辑处理;
- **@Around**: 环绕，可以在切入点前后织入代码，并且可以自由的控制何时执行切点；

#### 创建 JSON 工具类

在定义日志切面之前，我们先来创建一个 JSON 工具类，这在日志切面中打印出入参为 JSON 字符串会用到。在 `weblog-module-common` 通用模块下，创建一个 `utils` 包，用于统一放置工具类相关，然后，新建一个名为 `JsonUtil` 的工具类， 代码如下：

```java
package com.quanxiaoha.weblog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-08-14 16:27
 * @description: JSON 工具类
 **/
@Slf4j
public class JsonUtil {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static String toJsonString(Object obj) {
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }
}
```

上面的代码中，我们使用了 Spring Boot 内置的 JSON 工具`Jackson` ， 同时，创建了一个静态的 `ObjectMapper` 类，并写个一个 `toJsonString` 方法，用于将传入的对象打印成 JSON 字符串。

#### 定义日志切面类

工具类搞定后，在 `aspect` 包下，新建切面类 `ApiOperationLogAspect` , 代码如下，附有详细注释：

```java
package com.quanxiaoha.weblog.common.aspect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanxiaoha.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    /** 以自定义 @ApiOperationLog 注解为切点，凡是添加 @ApiOperationLog 的方法，都会执行环绕中的代码 */
    @Pointcut("@annotation(com.quanxiaoha.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {}

    /**
     * 环绕
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 请求开始时间
            long startTime = System.currentTimeMillis();

            // MDC
            MDC.put("traceId", UUID.randomUUID().toString());

            // 获取被请求的类和方法
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();

            // 请求入参
            Object[] args = joinPoint.getArgs();
            // 入参转 JSON 字符串
            String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));

            // 功能描述信息
            String description = getApiOperationLogDescription(joinPoint);

            // 打印请求相关参数
            log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                    description, argsJsonStr, className, methodName);

            // 执行切点方法
            Object result = joinPoint.proceed();

            // 执行耗时
            long executionTime = System.currentTimeMillis() - startTime;

            // 打印出参等相关信息
            log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                    description, executionTime, JsonUtil.toJsonString(result));

            return result;
        } finally {
            MDC.clear();
        }
    }

    /**
     * 获取注解的描述信息
     * @param joinPoint
     * @return
     */
    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        // 1. 从 ProceedingJoinPoint 获取 MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 2. 使用 MethodSignature 获取当前被注解的 Method
        Method method = signature.getMethod();

        // 3. 从 Method 中提取 LogExecution 注解
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);

        // 4. 从 LogExecution 注解中获取 description 属性
        return apiOperationLog.description();
    }

    /**
     * 转 JSON 字符串
     * @return
     */
    private Function<Object, String> toJsonStr() {
        return arg -> JsonUtil.toJsonString(arg);
    }

}
```

功能核心代码完成后，结构如下：

![img](https://img.quanxiaoha.com/quanxiaoha/169201085926810)

### 添加包扫描

在启动类 `WeblogWebApplication` 中，手动添加包扫描 `@ComponentScan` , 指定扫描 `com.quanxiaoha.weblog` 包下面的所有类:

```kotlin
package com.quanxiaoha.weblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.quanxiaoha.weblog.*"}) // 多模块项目中，必需手动指定扫描 com.quanxiaoha.weblog 包下面的所有类
public class WeblogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeblogWebApplication.class, args);
    }

}
```

## 新增测试接口

下面我们新建一个测试接口，并指定出入参，看看日志切面是否能够正常运行。在 `weblog-module-web` 模块中，新建 `controller` 包用于统一存放接口定义，另外，再定义一个 `model` 模型包，用于放置 pojo 对象：

![img](https://img.quanxiaoha.com/quanxiaoha/169165583455313)

新增一个 `User` 对象类，代码如下：

```kotlin
package com.quanxiaoha.weblog.web.model;

import lombok.Data;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-08-10 10:35
 * @description: TODO
 **/
@Data
public class User {
	// 用户名
    private String username;
    // 性别
    private Integer sex;
}
```

新增 `TestController` 类，定义一个 POST 格式，路径为 `/test` 接口，代码如下：

```kotlin
package com.quanxiaoha.weblog.web.controller;

import com.quanxiaoha.weblog.web.model.User;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-08-10 10:34
 * @description: TODO
 **/
@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public User test(@RequestBody User user) {
        // 返参
        return user;
    }

}
```

至此，测试接口就完成了。

## 测试一下好不好使

接下来，我们重启 `weblog` 项目，并使用 Postman 工具以 json 的方式请求 `/test` 接口：

![img](https://img.quanxiaoha.com/quanxiaoha/169165648162349)

![img](https://img.quanxiaoha.com/quanxiaoha/169165662431964)

请求参数如下：

```json
{
    "username": "admin",
    "sex": 1
}
```

查看控制台日志：

![img](https://img.quanxiaoha.com/quanxiaoha/169165676396675)

可以看到正确打印了两条日志：

- 请求开始日志：请求接口的描述信息，入参的 Json 数据，被请求类、方法；
- 请求结束日志：请求接口出参 Json 数据，以及执行耗时；