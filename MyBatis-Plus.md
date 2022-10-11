# MyBatis-Plus

## 1、简介

**MyBatis-Plus（简称 MP）是一个 MyBatis的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。**

> 愿景
>
> 我们的愿景是成为 MyBatis 最好的搭档，就像  魂斗罗 中的 1P、2P，基友搭配，效率翻倍。

### 1.1、MyBatisPlus介绍

| ![image-20221010193434237](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210101934370.png) |
| :----------------------------------------------------------: |

### 1.2、特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

### 1.3、支持数据库

> 任何能使用 `MyBatis` 进行 CRUD, 并且支持标准 SQL 的数据库，具体支持情况如下，如果不在下列表查看分页部分教程 PR 您的支持。

- MySQL，Oracle，DB2，H2，HSQL，SQLite，PostgreSQL，SQLServer，Phoenix，Gauss ，ClickHouse，Sybase，OceanBase，Firebird，Cubrid，Goldilocks，csiidb
- 达梦数据库，虚谷数据库，人大金仓数据库，南大通用(华库)数据库，南大通用数据库，神通数据库，瀚高数据库

### 1.4、框架结构

| ![image-20221010200439790](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210102004882.png) |
| :----------------------------------------------------------: |



### 1.5、代码及文档发布地址

官方地址：https://baomidou.com/

代码发布地址：

- Github：https://github.com/baomidou/mybatis-plus
- Gitee：https://gitee.com/baomidou/mybatis-plus

文档发布地址：http://baomidou.com/pages/24112f



## 2、入门案例

### 2.1、开发环境

IDE：idea 2022.2

JDK：jdk8+

构建工具：maven 3.8.4

MySQL版本：MySQL 8.0.27

Spring Boot：2.6.3

MyBatis-Plus：3.5.1

### 2.2、创建数据库及表

- 创建表

	```mysql
	CREATE DATABASE `mybatis_plus` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
	use `mybatis_plus`;
	CREATE TABLE `user` (
		`id` bigint(20) NOT NULL COMMENT '主键ID',
		`name` varchar(30) DEFAULT NULL COMMENT '姓名',
		`age` int(11) DEFAULT NULL COMMENT '年龄',
		`email` varchar(50) DEFAULT NULL COMMENT '邮箱',
		PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	```

- 添加数据

	```mysql
	INSERT INTO user (id, name, age, email) VALUES
	(1, 'Jone', 18, 'test1@baomidou.com'),
	(2, 'Jack', 20, 'test2@baomidou.com'),
	(3, 'Tom', 28, 'test3@baomidou.com'),
	(4, 'Sandy', 21, 'test4@baomidou.com'),
	(5, 'Billie', 24, 'test5@baomidou.com');
	```



### 2.3、创建Spring Boot工程

#### 2.3.1、创建项目

#### 2.3.2、引入依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.5.1</version>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
    </dependency>
</dependencies>
```

#### 2.3.3、idea中安装Lombok插件

省略

### 2.4、编写代码

#### 2.4.1、配置application.yml文件

```yaml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: jc951753
```

> 注意：
>
> 1. 驱动类driver-class-name 
>
> 	- spring boot 2.0（内置jdbc5驱动），驱动类使用：driver-class-name: com.mysql.jdbc.Driver
> 	- spring boot 2.1及以上（内置jdbc8驱动），驱动类使用： driver-class-name: com.mysql.cj.jdbc.Driver
>
> 	否则运行测试用例的时候会有 WARN 信息
>
> 2. 连接地址url 
>
> 	- MySQL5.7版本的url： jdbc:mysql://localhost:3306/mybatis_plus?characterEncoding=utf-8&useSSL=false 
> 	- MySQL8.0版本的url： jdbc:mysql://localhost:3306/mybatis_plus? serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false 
>
> 	否则运行测试用例报告如下错误： 
>
> 	java.sql.SQLException: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more

#### 2.4.2、启动类

```java
@SpringBootApplication
public class MybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusApplication.class, args);
    }

}
```

#### 2.4.3、添加实体

```java
package com.jingchao.mybatisplus.pojo;

import lombok.*;

@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;
}

```

#### 2.4.4、添加mapper

```java
public interface UserMapper extends BaseMapper<User> {
}
```

> BaseMapper是MyBatis-Plus提供的模板mapper，其中包含了基本的CRUD方法，泛型为操作的实体类型

#### 2.4.5、测试

```java
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        userMapper.selectList(null).forEach(System.out::println);
    }
}
```

> **注意:**
>
> IDEA在 userMapper 处报错，因为找不到注入的对象，因为类是动态创建的，但是程序可以正确 的执行。 为了避免报错，可以**在mapper接口上添加 @Repository 注解**

#### 2.4.6、添加日志

```yaml
#配置MyBatis日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```



## 3、基本CRUD

### 3.1、BaseMapper

MyBatis-Plus中的基本CRUD在内置的BaseMapper中都已得到了实现，我们可以直接使用，接口如下：

```java
package com.baomidou.mybatisplus.core.mapper;

/**
 * Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 * <p>这个 Mapper 支持 id 泛型</p>
 *
 * @author hubin
 * @since 2016-01-23
 */
public interface BaseMapper<T> extends Mapper<T> {

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    int insert(T entity);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(Serializable id);

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体对象
     * @since 3.4.4
     */
    int deleteById(T entity);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int delete(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 删除（根据ID或实体 批量删除）
     *
     * @param idList 主键ID列表或实体列表(不能为 null 以及 empty)
     */
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<?> idList);

    /**
     * 根据 ID 修改
     *
     * @param entity 实体对象
     */
    int updateById(@Param(Constants.ENTITY) T entity);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T selectById(Serializable id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，查询一条记录
     * <p>查询一条记录，例如 qw.last("limit 1") 限制取一条记录, 注意：多条数据会报异常</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        List<T> ts = this.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(ts)) {
            if (ts.size() != 1) {
                throw ExceptionUtils.mpe("One record is expected, but the query result is multiple records");
            }
            return ts.get(0);
        }
        return null;
    }

    /**
     * 根据 Wrapper 条件，判断是否存在记录
     *
     * @param queryWrapper 实体对象封装操作类
     * @return
     */
    default boolean exists(Wrapper<T> queryWrapper) {
        Long count = this.selectCount(queryWrapper);
        return null != count && count > 0;
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     * <p>注意： 只返回第一个字段的值</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    <P extends IPage<T>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作类
     */
    <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
```

### 3.2、插入

```java
@Test
public void testInsert(){
    User user = new User();
    user.setName("张三");
    user.setAge(27);
    user.setEmail("zhangsan@gmail.com");
    int result = userMapper.insert(user);
    System.out.println("result = " + result);
    System.out.println("user.getId() = " + user.getId());
}
```

> 最终生成的id为 1579632326512488450
>
> 因为MyBatis-Plus在实现插入数据时，会默认基于雪花算法的策略生成id



### 3.3、删除

#### 3.3.1、通过id删除记录

```java
@Test
public void testDelete(){
    int result = userMapper.deleteById(1579632326512488450L);
    System.out.println("result = " + result);
}
```



#### 3.3.2、通过id批量删除记录

```java
@Test
public void testDelete(){
    List<Long> list = Arrays.asList(1L, 2L, 3L);
    int result = userMapper.deleteBatchIds(list);
    System.out.println("result = " + result);
}
```



#### 3.3.3、通过map条件删除记录

```java
@Test
public void testDelete(){
    HashMap<String, Object> map = new HashMap<>();
    map.put("name","张三");
    map.put("age",23);
    int result = userMapper.deleteByMap(map);
    System.out.println("result = " + result);
}
```



### 3.4、修改

```java
@Test
public void testUpdate(){
    User user = new User();
    user.setId(4L);
    user.setName("李四");
    user.setEmail("lisi@qq.com");
    int result = userMapper.updateById(user);
    System.out.println("result = " + result);
}
```



### 3.5、查询

#### 3.5.1、根据Id查询用户信息

```java
@Test
public void testSelect(){
    User user = userMapper.selectById(1L);
    System.out.println(user);

}
```



#### 3.5.2、根据多个id查询多个用户信息

```java
@Test
public void testSelect(){
    List<Long> list = Arrays.asList(1L, 2L, 3L);
    List<User> users = userMapper.selectBatchIds(list);
    users.forEach(System.out::println);
}
```



#### 3.5.3、通过map条件查询用户信息

```java
@Test
public void testSelect(){
    Map<String, Object> map = new HashMap<>();
    map.put("name","Tom");
    map.put("age",28);
    List<User> users = userMapper.selectByMap(map);
    users.forEach(System.out::println);
}
```



#### 3.5.4、查询所有数据

```java
@Test
public void testSelect(){
    List<User> users = userMapper.selectList(null);
    users.forEach(System.out::println);
}
```

>通过观察BaseMapper中的方法，大多方法中都有Wrapper类型的形参，此为条件构造器，可针对于SQL语句设置不同的条件，若没有条件，则可以为该形参赋值null，即查询（删除/修改）所有数据

### 3.6、自定义功能

>  resource目录下新建mapper/UserMapper.xml文件

- UserMapper接口

	```java
	/**
	 * 根据 id 查询用户信息为 map 集合
	 * @param id
	 * @return
	 */
	Map<String, Object> selectMapById(Long id);
	```

- UserMapper映射文件

	```xml
	<!-- Map<String, Object> selectMapById(Long id); -->
	<select id="selectMapById" resultType="map">
	    select id,name,age,email from user where id = #{id}
	</select>
	```

- 测试

	```java
	@Test
	public void testSelect(){
	    Map<String, Object> map = userMapper.selectMapById(1L);
	    System.out.println(map);
	}
	```

	

### 3.6、通用Service

















