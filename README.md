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

> 说明:
>
> - 通用 Service CRUD 封装[IService](https://gitee.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-extension/src/main/java/com/baomidou/mybatisplus/extension/service/IService.java)接口，进一步封装 CRUD 采用 `get 查询单行` `remove 删除` `list 查询集合` `page 分页` 前缀命名方式区分 `Mapper` 层避免混淆，
> - 泛型 `T` 为任意实体对象
> - 建议如果存在自定义通用 Service 方法的可能，请创建自己的 `IBaseService` 继承 `Mybatis-Plus` 提供的基类
> - 对象 `Wrapper` 为 [条件构造器](https://baomidou.com/01.指南/02.核心功能/wrapper.html)



#### 3.6.1、IService

MyBatis-Plus中有一个接口 IService和其实现类 ServiceImpl，封装了常见的业务层逻辑 

详情查看源码IService和ServiceImpl

#### 3.6.2、创建Service接口和实现类

```java
/**
 *  UserService继承IService模板提供的基础功能
 */
public interface UserService extends IService<User> {
}
```

```java
/**
 * ServiceImpl实现了IService，提供了IService中基础功能的实现
 * 若ServiceImpl无法满足业务需求，则可以使用自定的UserService定义方法，并在实现类中实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```

#### 3.6.3、测试查询记录数

```java
@Autowired
private UserService userService;

@Test
public void test(){
    long count = userService.count();
    System.out.println(count);
}
```

#### 3.6.4、测试批量插入

```java
@Test
public void testInsertMore(){
    ArrayList<User> list = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
        User user = new User();
        user.setName("jingchao"+i);
        user.setAge(18+i);
        user.setEmail("jingchao"+i+"@gmail.com");
        list.add(user);
    }
    boolean b = userService.saveBatch(list);
    System.out.println(b);
}
```



## 4、常用注解

### 4.1、@TableName

> 经过以上的测试，在使用MyBatis-Plus实现基本的CRUD时，我们并没有指定要操作的表，只是在 Mapper接口继承BaseMapper时，设置了泛型User，而操作的表为user表 由此得出结论，MyBatis-Plus在确定操作的表时，由BaseMapper的泛型决定，即实体类型决 定，且默认操作的表名和实体类型的类名一致

#### 4.1.1、问题

**若实体类类型的类名和要操作的表的表名不一致，会出现什么问题？**

> 我们将表user更名为t_user，测试查询功能 程序抛出异常，Table 'mybatis_plus.user' doesn't exist，因为现在的表名为t_user，而默认操作 的表名和实体类型的类名一致，即user表

| ![image-20221011112207637](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210111122713.png) |
| :----------------------------------------------------------: |



#### 4.1.2、通过@TableName解决问题

> 在实体类类型上添加@TableName("t_user")，标识实体类对应的表，即可成功执行SQL语句

| ![image-20221011112509572](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210111233145.png) |
| :----------------------------------------------------------: |

#### 4.1.3、通过全局配置解决问题

> 在开发的过程中，我们经常遇到以上的问题，即实体类所对应的表都有固定的前缀，例如 t_ 或 tbl_ 此时，可以使用MyBatis-Plus提供的全局配置，为实体类所对应的表名设置默认的前缀，那么就 不需要在每个实体类上通过@TableName标识实体类对应的表

```yaml
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      # 配置表的默认前缀
      table-prefix: t_
```



### 4.2、@TableId

> 经过以上的测试，MyBatis-Plus在实现CRUD时，会默认将id作为主键列，并在插入数据时，默认基于**雪花算法**的策略生成id

#### 4.2.1、问题

**若实体类和表中表示主键的不是id，而是其他字段，例如uid，MyBatis-Plus会自动识别uid为主键列吗？**

> 我们实体类中的属性id改为uid，将表中的字段id也改为uid，测试添加功能
>
> 程序抛出异常，Field 'uid' doesn't have a default value，说明MyBatis-Plus没有将uid作为主键赋值

| ![image-20221011123744421](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210111237503.png) |
| :----------------------------------------------------------: |



#### 4.2.2、通过@TableId解决问题

> 在实体类中uid属性上通过@TableId将其标识为主键，即可成功执行SQL语句

| ![image-20221011123845194](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210111238256.png) |
| :----------------------------------------------------------: |

##### ① @TableId的value属性

> 若实体类中主键对应的属性为id，而表中表示主键的字段为uid，此时若只在属性id上添加注解 @TableId，则抛出异常Unknown column 'id' in 'field list'，
>
> | ![image-20221011124417354](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210111244416.png) |
> | :----------------------------------------------------------: |
>
> 即MyBatis-Plus仍然会将id作为表的 主键操作，而表中表示主键的是字段 uid 此时需要通过@TableId注解的value属性，指定表中的主键字段，@TableId("uid")或 @TableId(value="uid")

| ![image-20221011124605209](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210111246268.png) |
| :----------------------------------------------------------: |

##### ② @TableId的type属性

> type 属性用来自定义主键策略

|            值             | 描述                                                         |
| :-----------------------: | ------------------------------------------------------------ |
| IdType.ASSIGN_ID（默 认） | 基于雪花算法的策略生成数据id，与数据库id是否设置自增无关     |
|        IdType.AUTO        | 使用数据库的自增策略，注意，该类型请确保数据库设置了id自增， 否则无效 |

**配置全局的主键策略**

```yaml
mybatis-plus:
  configuration:
    # 配置 MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      # 配置表的默认前缀
      table-prefix: t_
      # 设置统一的主键生成策略
      id-type: auto
```



#### 4.2.3、雪花算法

- **背景**

	需要选择合适的方案去应对数据规模的增长，以应对逐渐增长的访问压力和数据量。

	数据库的扩展方式主要包括：业务分库、主从复制，数据库分表。

- **数据库分表**

	将不同业务数据分散存储到不同的数据库服务器，能够支撑百万甚至千万用户规模的业务，但如果业务 继续发展，同一业务的单表数据也会达到单台数据库服务器的处理瓶颈。例如，淘宝的几亿用户数据， 如果全部存放在一台数据库服务器的一张表中，肯定是无法满足性能要求的，此时就需要对单表数据进行拆分。

	单表数据拆分有两种方式：垂直分表和水平分表。示意图如下：

	| ![image-20221011125932605](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210111259677.png) |
	| :----------------------------------------------------------: |

	

- **垂直分表**

	垂直分表适合将表中某些不常用且占了大量空间的列拆分出去。

	例如，前面示意图中的 nickname 和 description 字段，假设我们是一个婚恋网站，用户在筛选其他用 户的时候，主要是用 age 和 sex 两个字段进行查询，而 nickname 和 description 两个字段主要用于展 示，一般不会在业务查询中用到。description 本身又比较长，因此我们可以将这两个字段独立到另外 一张表中，这样在查询 age 和 sex 时，就能带来一定的性能提升。

- **水平分表**

	水平分表适合表行数特别大的表，有的公司要求单表行数超过 5000 万就必须进行分表，这个数字可以 作为参考，但并不是绝对标准，关键还是要看表的访问性能。对于一些比较复杂的表，可能超过 1000 万就要分表了；而对于一些简单的表，即使存储数据超过 1 亿行，也可以不分表。

	但不管怎样，当看到表的数据量达到千万级别时，作为架构师就要警觉起来，因为这很可能是架构的性能瓶颈或者隐患。

	水平分表相比垂直分表，会引入更多的复杂性，例如要求全局唯一的数据id该如何处理

	- **主键自增**

		1. 以最常见的用户 ID 为例，可以按照 1000000 的范围大小进行分段，1 ~ 999999 放到表 1中， 1000000 ~ 1999999 放到表2中，以此类推。
		2. 复杂点：分段大小的选取。分段太小会导致切分后子表数量过多，增加维护复杂度；分段太大可能会导致单表依然存在性能问题，一般建议分段大小在 100 万至 2000 万之间，具体需要根据业务选取合适的分段大小。
		3. 优点：可以随着数据的增加平滑地扩充新的表。例如，现在的用户是 100 万，如果增加到 1000 万， 只需要增加新的表就可以了，原有的数据不需要动。
		4. 缺点：分布不均匀。假如按照 1000 万来进行分表，有可能某个分段实际存储的数据量只有 1 条，而 另外一个分段实际存储的数据量有 1000 万条。

	- **取模**

		1. 同样以用户 ID 为例，假如我们一开始就规划了 10 个数据库表，可以简单地用 user_id % 10 的值来 表示数据所属的数据库表编号，ID 为 985 的用户放到编号为 5 的子表中，ID 为 10086 的用户放到编号 为 6 的子表中。
		2. 复杂点：初始表数量的确定。表数量太多维护比较麻烦，表数量太少又可能导致单表性能存在问题。
		3. 优点：表分布比较均匀。
		4. 缺点：扩充新的表很麻烦，所有数据都要重分布。

	- **雪花算法**

		> 雪花算法是由Twitter公布的分布式主键生成算法，它能够保证不同表的主键的不重复性，以及相同表的 主键的有序性。

		1. 核心思想

			长度共64bit（一个long型）。

			首先是一个符号位，1bit标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负 数是1，所以id一般是正数，最高位是0。

			- 41bit时间截(毫秒级)，存储的是时间截的差值（当前时间截 - 开始时间截)，结果约等于69.73年。 

			- 10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID，可以部署在1024个节点）。 

			- 12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID）。

				| ![image-20221011131539539](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210111315606.png) |
				| :----------------------------------------------------------: |

				

		2. 优点

			整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞，并且效率较高。



### 4.3、@TableField

经过以上的测试，我们可以发现，MyBatis-Plus在执行SQL语句时，要保证实体类中的属性名和 表中的字段名一致 

如果实体类中的属性名和字段名不一致的情况，会出现什么问题呢？

#### 4.3.1、情况一

> 若实体类中的属性使用的是驼峰命名风格，而表中的字段使用的是下划线命名风格 
>
> 例如实体类属性userName，表中字段user_name 
>
> 此时MyBatis-Plus会自动将下划线命名风格转化为驼峰命名风格 相当于在MyBatis中配置

#### 4.3.2、情况二

> 若实体类中的属性和表中的字段不满足情况1 
>
> 例如实体类属性name，表中字段username 
>
> 此时需要在实体类属性上使用@TableField("username")设置属性所对应的字段名

| ![image-20221012100641761](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210121327738.png) |
| :----------------------------------------------------------: |

### 4.4、@TableLogic

#### 4.4.1、逻辑删除

- 物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除的数据 
- 逻辑删除：假删除，将对应数据中代表是否被删除字段的状态修改为“被删除状态”，之后在数据库 中仍旧能看到此条数据记录 
- 使用场景：可以进行数据恢复

#### 4.4.2、实现逻辑删除

1. step1：数据库中创建逻辑删除状态列，设置默认值为0

	| ![image-20221012101311538](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210121327105.png) |
	| :----------------------------------------------------------: |

	

2. step2：实体类中添加逻辑删除属性

	| ![image-20221012101458798](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210121329194.png) |
	| :----------------------------------------------------------: |

	

3. step3：测试

	> 测试删除功能，真正执行的是修改 
	>
	> UPDATE t_user SET is_deleted=1 WHERE id=? AND is_deleted=0 
	>
	> 测试查询功能，被逻辑删除的数据默认不会被查询 
	>
	> SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0



## 5、条件构造器和常用接口

### 5.1、wrapper介绍

| ![image-20221012102824403](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210121328525.png) |
| :----------------------------------------------------------: |

- Wrapper：条件构造抽象类，最顶端父类
	- AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件
		- QueryWrapper ： 查询条件封装 
		- UpdateWrapper ： Update 条件封装 
		- AbstractLambdaWrapper ： 使用Lambda 语法
			- LambdaQueryWrapper ：用于Lambda语法使用的查询Wrapper
			- LambdaUpdateWrapper ： Lambda 更新封装Wrapper

### 5.2、QueryWrapper

#### 5.2.1、组装查询条件

```java
@Test
public void test01(){
    // 查询用户名包含 a , 年龄 20 ~ 30 , 邮箱不为null
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("user_name", "a")
        .between("age", 20, 30)
        .isNotNull("email");
    List<User> list = userMapper.selectList(queryWrapper);
    list.forEach(System.out::println);
}
```

#### 5.2.2、组装排序条件

```java
@Test
public void  test02(){
    // 查询用户信息，按照年龄降序排列，id升序排列
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc("age")
        .orderByAsc("uid");
    List<User> list = userMapper.selectList(queryWrapper);
    list.forEach(System.out::println);
}
```

#### 5.2.3、组装删除条件

```java
@Test
public void test03(){
    // 删除邮箱信息为 null 的记录
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.isNull("email");
    int result = userMapper.delete(queryWrapper);
    System.out.println(result);
}
```

#### 5.2.4、条件的优先级（实现修改）

```java
@Test
public void test04(){
    // 将 (年龄大于 20 并且用户名中包含 a) 或邮箱为 null的用户信息修改
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.gt("age", 20)
        .like("user_name","a")
        .or()
        .isNull("email");
    User user = new User();
    user.setName("小名");
    user.setEmail("jignchao@qq.com");
    int result = userMapper.update(user, queryWrapper);
    System.out.println(result);
}
```

```java
@Test
public void test05(){
    // 将用户名中包含 a 并且（年龄大于20或邮箱为null）的用户信息修改
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    // lambda表达式先执行
    queryWrapper.like("user_name", "a")
        .and(i -> i.gt("age", 20)
             .or()
             .isNull("email")
            );
    User user = new User();
    user.setAge(18);
    user.setEmail("jingchao.jc@qq.com");
    int result = userMapper.update(user, queryWrapper);
    System.out.println(result);
}
```

#### 5.2.5、组装select子句

```java
@Test
public void test06(){
    // 查询用户的 user_name 和 age 字段
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("user_name", "age");
    List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
    maps.forEach(System.out::println);
}
```

#### 5.2.6、实现子查询

```java
@Test
public void test07(){
    // 查询 id 小于10的用户信息
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.inSql("uid", "select uid from t_user where uid < 10");
    List<User> list = userMapper.selectList(queryWrapper);
    list.forEach(System.out::println);
}
```

### 5.3、UpdateWrapper

```java
@Test
public void test08(){
    // 将用户名中包含 a 并且（年龄大于20或邮箱为null）的用户信息修改
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    updateWrapper.like("user_name", "a")
        .and(i -> i.gt("age", 20)
             .or()
             .isNull("email")
            );
    updateWrapper.set("user_name", "小红").set("email", "jc@qq.com");
    int result = userMapper.update(null, updateWrapper);
    System.out.println(result);
}
```



### 5.4、condition

> 在真正开发的过程中，组装条件是常见的功能，而这些条件数据来源于用户输入，是可选的，因 此我们在组装这些条件时，必须先判断用户是否选择了这些条件，若选择则需要组装该条件，若 没有选择则一定不能组装，以免影响SQL执行的结果

**思路一**

```java
@Test
public void test09(){
    // 定义查询条件
    String username = "a";
    Integer ageBegin = 18;
    Integer ageEnd = 30;
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotBlank(username)){
        queryWrapper.like("user_name", username);
    }
    if (ageBegin != null){
        queryWrapper.ge("age", ageBegin);
    }
    if (ageEnd != null){
        queryWrapper.le("age", ageEnd);
    }
    List<User> list = userMapper.selectList(queryWrapper);
    list.forEach(System.out::println);
}
```

**思路二**

上面的实现方案没有问题，但是代码比较复杂，我们可以使用带condition参数的重载方法构建查询条件，简化代码的编写

```java
@Test
public void test10(){
    String username = "";
    Integer ageBegin = 18;
    Integer ageEnd = 30;
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like(StringUtils.isNotBlank(username), "user_name", "a")
        .ge(ageBegin != null, "age", ageBegin)
        .le(ageEnd != null, "age", ageEnd);
    List<User> list = userMapper.selectList(queryWrapper);
    list.forEach(System.out::println);
}
```



### 5.5、LambdaQueryWrapper

```java
@Test
public void test11(){
    String username = "a";
    Integer ageBegin = null;
    Integer ageEnd = 30;
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
        .ge(ageBegin != null, User::getAge, ageBegin)
        .le(ageEnd != null, User::getAge, ageEnd);
    List<User> list = userMapper.selectList(queryWrapper);
    list.forEach(System.out::println);
}
```

### 5.6、LambdaUpdateWrapper

```java
@Test
public void test12(){
    // 将用户名中包含 a 并且（年龄大于20或邮箱为null）的用户信息修改
    LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper.like(User::getName, "a")
        .and(i -> i.gt(User::getAge, 20)
             .or()
             .isNull(User::getEmail)
            );
    updateWrapper.set(User::getName, "小红").set(User::getEmail, "jc@qq.com");
    int result = userMapper.update(null, updateWrapper);
    System.out.println(result);
}
```



## 6、插件

### 6.1、分页插件

> MyBatis Plus自带分页插件，只要简单的配置即可实现分页功能

#### 6.1.1、添加配置类

```java
@Configuration
@MapperScan("com.jingchao.mybatisplus.mapper")
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

#### 6.1.2、测试

```java
@Test
public void testPage(){
    Page<User> page = new Page<>(2,3);
    userMapper.selectPage(page, null);
    //获取分页数据
    List<User> list = page.getRecords();
    list.forEach(System.out::println);
    System.out.println("当前页："+page.getCurrent());
    System.out.println("每页显示的条数："+page.getSize());
    System.out.println("总记录数："+page.getTotal());
    System.out.println("总页数："+page.getPages());
    System.out.println("是否有上一页："+page.hasPrevious());
    System.out.println("是否有下一页："+page.hasNext());
}
```



### 6.2、xml自定义分页

#### 6.2.1、UserMapper中定义接口方法

```java
/**
 * 通过年龄查询用户信息并分页
 * @param page MyBatis-Plus 提供的分页参数，必须位于第一个参数的位置
 * @param age
 * @return
 */
Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
```

#### 6.2.2、UserMapper.xml中编写SQL语句

```xml
<select id="selectPageVo" resultType="User">
    select uid,user_name,age,email from t_user where age > #{age}
</select>
```

#### 6.2.3、测试

```java
@Test
public void testPageVo(){
    Page<User> page = new Page<>(1,3);
    userMapper.selectPageVo(page, 20);
    List<User> list = page.getRecords();
    list.forEach(System.out::println);
}
```



### 6.3、乐观锁

#### 6.3.1、场景

一件商品，成本价是80元，售价是100元。老板先是通知小李，说你去把商品价格增加50元。小李正在玩游戏，耽搁了一个小时。正好一个小时后，老板觉得商品价格增加到150元，价格太高，可能会影响销量。又通知小王，你把商品价格降低30元。 

此时，小李和小王同时操作商品后台系统。小李操作的时候，系统先取出商品价格100元；小王也在操作，取出的商品价格也是100元。小李将价格加了50元，并将100+50=150元存入了数据 库；小王将商品减了30元，并将100-30=70元存入了数据库。是的，如果没有锁，小李的操作就完全被小王的覆盖了。 

现在商品价格是70元，比成本价低10元。几分钟后，这个商品很快出售了1千多件商品，老板亏1 万多。

#### 6.3.2、乐观锁与悲观锁

上面的故事，如果是乐观锁，小王保存价格前，会检查下价格是否被人修改过了。如果被修改过 了，则重新取出的被修改后的价格，150元，这样他会将120元存入数据库。 

如果是悲观锁，小李取出数据后，小王只能等小李操作完之后，才能对价格进行操作，也会保证 最终的价格是120元。

#### 6.3.3、模拟修改冲突

**数据库中添加商品表**

```mysql
CREATE TABLE t_product
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	NAME VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称',
	price INT(11) DEFAULT 0 COMMENT '价格',
	VERSION INT(11) DEFAULT 0 COMMENT '乐观锁版本号',
	PRIMARY KEY (id)
);
```

**添加数据**

```mysql
INSERT INTO t_product (id, NAME, price) VALUES (1, '外星人笔记本', 100);
```

**添加实体**

```java
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer version;
}
```

**添加mapper**

```java
public interface ProductMapper extends BaseMapper<Product> {
}
```

**测试**

```java
@Test
public void testProduct(){
    // 小李查询商品价格
    Product productLi = productMapper.selectById(1L);
    System.out.println("productLi = " + productLi);

    // 小王查询商品价格
    Product productWang = productMapper.selectById(1L);
    System.out.println("productWang = " + productWang);

    // 小李价格加 50，存入数据库
    productLi.setPrice(productLi.getPrice() + 50);
    productMapper.updateById(productLi);

    // 小王价格减 30，存入数据库
    productWang.setPrice(productWang.getPrice() - 30);
    productMapper.updateById(productWang);

    // 最后结果
    Product product = productMapper.selectById(1L);
    System.out.println("product = " + product);
}
```

#### 6.3.4、乐观锁实现流程

数据库中添加version字段 

取出记录时，获取当前version

```mysql
SELECT id,`name`,price,`version` FROM product WHERE id=1
```

更新时，version + 1，如果where语句中的version版本不对，则更新失败

```mysql
UPDATE product SET price=price+50, `version`=`version` + 1 WHERE id=1 AND
`version`=1
```

#### 6.3.5、MyBatis-Plus实现乐观锁

**修改实体类**

```java
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}
```

**添加乐观锁插件配置**

```java
@Configuration
@MapperScan("com.jingchao.mybatisplus.mapper")
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 添加乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
```

**测试修改冲突**

> 小李查询商品信息： 
>
> SELECT id,name,price,version FROM t_product WHERE id=? 
>
> 小王查询商品信息： 
>
> SELECT id,name,price,version FROM t_product WHERE id=? 
>
> 小李修改商品价格，自动将version+1 
>
> UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=? 
>
> Parameters: 外星人笔记本(String), 150(Integer), 1(Integer), 1(Long), 0(Integer) 
>
> 小王修改商品价格，此时version已更新，条件不成立，修改失败 
>
> UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=? 
>
> Parameters: 外星人笔记本(String), 70(Integer), 1(Integer), 1(Long), 0(Integer) 
>
> 最终，小王修改失败，查询价格：150 
>
> SELECT id,name,price,version FROM t_product WHERE id=?

**优化流程**

```java
@Test
public void testProduct(){
    // 小李查询商品价格
    Product productLi = productMapper.selectById(1L);
    System.out.println("productLi = " + productLi);

    // 小王查询商品价格
    Product productWang = productMapper.selectById(1L);
    System.out.println("productWang = " + productWang);

    // 小李价格加 50，存入数据库
    productLi.setPrice(productLi.getPrice() + 50);
    productMapper.updateById(productLi);

    // 小王价格减 30，存入数据库
    productWang.setPrice(productWang.getPrice() - 30);
    int result = productMapper.updateById(productWang);
    if (result == 0){
        // 操作失败，重试
        Product productNew = productMapper.selectById(1L);
        productNew.setPrice(productNew.getPrice() - 30);
        productMapper.updateById(productNew);
    }

    // 最后结果
    Product product = productMapper.selectById(1L);
    System.out.println("product = " + product);
}
```



## 7、通用枚举

表中的有些字段值是固定的，例如性别（男或女），此时我们可以使用MyBatis-Plus的通用枚举 来实现

### 7.1、数据库添加sex字段

| ![image-20221013120311885](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210131203052.png) |
| :----------------------------------------------------------: |

### 7.2、创建通用枚举类类型

```java
@Getter
public enum SexEnums {
    MALE(1,"男"),
    FEMALE(2,"女");

    @EnumValue
    private Integer sex;

    private String sexName;

    SexEnums(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
```

### 7.3、配置扫描通用枚举

```yaml
mybatis-plus:
  configuration:
    # 配置 MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      # 配置表的默认前缀
      table-prefix: t_
      # 设置统一的主键生成策略
      id-type: auto
  type-aliases-package: com.jingchao.mybatisplus.pojo
  # 扫描通用枚举的包
  type-enums-package: com.jingchao.mybatisplus.enums
```

### 7.4、测试

```java
@Test
public void test(){
    User user = new User();
    user.setName("admin");
    user.setAge(25);
    //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
    user.setSex(SexEnums.MALE);
    int result = userMapper.insert(user);
    System.out.println(result);
}
```



## 8、代码生成器

### 8.1、引入依赖

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.5.2</version>
</dependency>
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.31</version>
</dependency>
```

### 8.2、快速生成

```java
public class FastAutoGeneratorTest {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus?characterEncoding=utf-8&userSSL=false", "root", "jc951753")
                .globalConfig(builder -> {
                    builder.author("JingChao") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E://mybatis_plus"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.jingchao") // 设置父包名
                            .moduleName("mybatisplus") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://mybatis_plus"));// 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
```

[官方文档](https://baomidou.com/pages/779a6e/#%E5%AE%89%E8%A3%85)

## 9、多数据源

适用于多种场景：纯粹多库、 读写分离、 一主多从、 混合模式等 

目前我们就来模拟一个纯粹多库的一个场景，其他场景类似 

场景说明： 我们创建两个库，分别为：mybatis_plus（以前的库不动）与mybatis_plus_1（新建），将 mybatis_plus库的product表移动到mybatis_plus_1库，这样每个库一张表，通过一个测试用例 分别获取用户数据与商品数据，如果获取到说明多库模拟成功

### 9.1、创建数据库及表

创建数据库mybatis_plus_1和表product

```mysql
CREATE DATABASE `mybatis_plus_1` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
use `mybatis_plus_1`;
CREATE TABLE product
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称',
	price INT(11) DEFAULT 0 COMMENT '价格',
	version INT(11) DEFAULT 0 COMMENT '乐观锁版本号',
	PRIMARY KEY (id)
);
```

添加测试数据

```mysql
INSERT INTO product (id, NAME, price) VALUES (1, '外星人笔记本', 100);
```

删除mybatis_plus库中的product表

```mysql
use mybatis_plus;
DROP TABLE IF EXISTS product;
```



### 9.2、引入依赖

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
    <version>3.5.0</version>
</dependency>
```

### 9.3、配置多数据源

说明：注释掉之前的数据库连接，添加新配置

```yaml
spring:
  # 配置数据源信息
  datasource:
    dynamic:
      # 设置默认的数据源或者数据源组,默认值即为master
      primary: master
      # 严格匹配数据源,默认false.true未匹配到指定数据源时抛异常,false使用默认数据源
      strict: false
      datasource:
        master:
          url: jdbc:mysql://localhost:3306/mybatis_plus?characterEncoding=utf-8&useSSL=false
          driver-class-name: com.mysql.cj.jdbc.Driver
          username: root
          password: jc951753
        slave_1:
          url: jdbc:mysql://localhost:3306/mybatis_plus_1?characterEncoding=utf-8&useSSL=false
          driver-class-name: com.mysql.cj.jdbc.Driver
          username: root
          password: jc951753
```

### 9.4、创建用户Service

```java
public interface UserService extends IService<User>{
}
```

```java
@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```

### 9.5、创建商品Service

```java
public interface ProductService extends IService<Product> {
}
```

```java
@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
```

### 9.6、测试

```java
@Autowired
private UserService userService;

@Autowired
private ProductService productService;

@Test
public void test(){
    System.out.println(userService.getById(1));
    System.out.println(productService.getById(1));
}
```



## 10、MyBatisX插件

MyBatis-Plus为我们提供了强大的mapper和service模板，能够大大的提高开发效率 

但是在真正开发过程中，MyBatis-Plus并不能为我们解决所有问题，例如一些复杂的SQL，多表 联查，我们就需要自己去编写代码和SQL语句，我们该如何快速的解决这个问题呢，这个时候可 以使用MyBatisX插件 

**MyBatisX一款基于 IDEA 的快速开发插件**，为效率而生。

**[MyBatisX插件使用方法](https://baomidou.com/pages/ba5b24/)**

### 10.1、安装插件

IEDEA中直接安装插件就OK

### 10.2、代码快速生成

1. IDEA连接数据库

	| ![image-20221013150151772](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210131507491.png) |
	| :----------------------------------------------------------: |

2. 使用MyBatisX

	> 鼠标选中表，右键，选择 **MyBatisX-Generator**

3. 配置生成的代码等信息

	| ![image-20221013150624846](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210131506942.png) |
	| :----------------------------------------------------------: |
	| ![image-20221013150720725](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210131507032.png) |



### 10.3、快速生成CRUD

例：

| ![image-20221013152107617](https://cdn.jsdelivr.net/gh/a-jingchao/picture-bed/BlogImages/202210131521702.png) |
| :----------------------------------------------------------: |

**[详情参见官网](https://baomidou.com/pages/ba5b24/)**

