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