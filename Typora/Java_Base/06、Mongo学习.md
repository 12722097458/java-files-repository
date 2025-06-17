### 一、Mongo的基础知识学习

Windows版本安装及使用

```shell
压缩包版本启动方法
cd D:\Java\mongodb-win32-x86_64-2012plus-4.2.13\bin
启用服务
mongod --dbpath D:\Java\mongodb-win32-x86_64-2012plus-4.2.13\data
打开客户端
mongo

```

http://dl.mongodb.org/dl/win32/x86_64

http://dl.mongodb.org/dl/linux/x86_64



windows64位mongodb安装：

1、进入网站http://dl.mongodb.org/dl/win32/x86_64，下载相应版本mongodb-win32-x86_64-2012plus-v4.2-latest-signed.msi

2、运行此msi文件， 并指定相应的安装目录。

3、配置mongodb的环境变量

4、进入cmd窗口输入mongod，mongo命令，看是否安装成功。



数据库（database）

数据库是一个仓库，在仓库中可以存放集合。

集合（collection）

集合类似于数组，在集合中可以存文档。

文档（document）

文档数据库中的最小单位，我们存储和操作的内容都是文档。

在mongodb中，数据库和集合不需要手动创建，当我们创建文档时，如果文档所在的集合或数据库不存在，则会自动创建数据库和集合。

```shell
-- 启动服务时指定数据库路径以及端口号
mongod --dbpath D:\Java\mongodb\data
mongod --dbpath D:\Java\mongodb\data --port 10086
```

基本操作

```shell
show dbs
use test   -- 进入到指定的数据库中
db   -- 常看当前使用的数据库
show collections    -- 查看当前数据库有多少集合

# 1、增，向数据库中插入文档
db.<collection>.insert(doc)
向当前数据库的collection集合中插入一个doc文档。
eg：
	向test数据库中的students集合中插入一个学生对象
	{name:"Jack",age:19,gender:"male"}
即：	
db.students.insert({name:"Jack",age:19,gender:"male"})
也可以插入多个
db.students.insert([{name:"Tom",age:12,gender:"male"},{name:"Rose",age:23,gender:"female"}])

# 可以用insertOne()和insertMany()对数据量多少进行详细区分
db.students.insertOne({name:"insertOne",age:19,gender:"male"})
db.students.insertMany([{name:"insertMany1",age:12,gender:"male"},{name:"insertMany2",age:23,gender:"female"}])


# 2、查，查找所以符合条件的元素  -- 返回的是一个数组
db.<collection>.find()
	-- find()用来查找集合中所有符合条件的文档
	-- find()可以接收一个对象作为条件参数
		find({}) 表示为空对象，相当于不写，查询所有
		find({name:"Rose"}) 查找name为Rose的对象
        find({name:"Rose"，age:12}) 查找name为Rose且年龄为12的对象
db.students.findOne()
-- 统计有几个元素
db.students.find({}).count();


# 3、改，修改
db.collection.update(查询条件，新对象)
db.students.update({name:"Jack"},{age:38})  --默认情况下是用新对象替代旧的对象（用age:38对象替换(清空)name为Jack的原始对象）

如果需要修改指定的属性而不更改其他的字段，需要使用修改操作符：
$set可以用来修改和指定文档中指定的属性。
db.students.update({name:"Tom"},
					{$set:{gender:"男"}}
					)             #  默认只会修改第一个，可以添加muli:参数来进行实现多个修改

-- 修改所有符合条件的对象
db.students.updateMany({gender:"male"}, {$set:{gender:"男"}} )


# 4、 删，删除
db.students.remove({name:"insertOne"})   # 可以删除一个或多个
db.students.deleteOne({age:38})
db.students.deleteMany({age:23})

db.students.drop();  # 删除集合，相当于删除一个表
```



练习：

```shell
1、切换到my_test数据库
use my_test
2、向数据库my_test的user集合中插入一个文档
db.user.insert({name:"Jack",gender:"male"})
3、查询user集合中的文档
db.user.find({})
4、查询user集合中文档数量
db.user.find({}).count();
5、查询name为Jack的文档
db.user.find({name:"Jack"})
6、在{ "_id" : ObjectId("6071a8bbe6fa3f1e0bc78237"), "name" : "Jack", "gender" : "male" }中添加一个address=河南字段
db.user.update({name:"Jack"},
					{$set:{address:"河南"}}
					)
7、将{ "_id" : ObjectId("6071a8bbe6fa3f1e0bc78237"), "name" : "Jack", "gender" : "male", "address" : "河南" }的name改为Tom
db.user.update({name:"Jack"},
					{$set:{name:"Tom"}}
					)
8、删除Tom的address属性
db.user.remove({name:"Tom"})

9、向{ "_id" : ObjectId("6071a928e6fa3f1e0bc78238"), "name" : "Rose", "gender" : "female" }文档中添加一个hobby属性--》包括movies和sports=====》》》  hobby:{movies:["三国演义","水浒传"],sports:["Soccer","Basketball"]}
db.user.update({name:"Rose"},
					{$set:{hobby:{movies:["三国演义","水浒传"],sports:["Soccer","Basketball"]}}}
					)
10、查询出喜欢的运动sports包含Soccer的文档信息
db.user.find({"hobby.sports":"Soccer"})
					
11、对Rose喜欢的运动中加一个Running（此时是对内嵌文档并且是数组类型的数据进行修改，$set不行了，需要新的$push）
db.user.update({name:"Rose"},
					{$push:{"hobby.sports":"Running"}}
					)

==================================================================================================
批量
12、向numbers集合中插入20000条数据
for(var i = 1; i <= 20000; i++) {
	db.numbers.insert({num:i})
}            # 此时执行了20000次insert方法，效率较低
插完查一下db.numbers.find().count()，删表db.numbers.remove({})

优化，放入数组，然后一次插入
var arr = [];
for(var i = 1; i <= 20000; i++) {
	arr.push({num:i});
} 
db.numbers.insert(arr)
13、查询num为500的文档
db.numbers.find({num:500})

14、查询num大于19990的文档
db.numbers.find({num:{$gt:19990}})
15、查询num小于等于10的文档
db.numbers.find({num:{$lte:10}})
16、查询num  [40,50]
db.numbers.find({num:{$gte:40,$lte:50}})
17、查询num  [40,50]，并且_id = 6071b53ae6fa3f1e0bc7d08a
db.numbers.find({num:{$gte:40,$lte:50},"_id":ObjectId("6071b53ae6fa3f1e0bc7d08a")})

18、查询集合中前10条数据
db.numbers.find().limit(10);
19、查询第11到20条数据[11,20]
db.numbers.find().skip(10).limit(10);

```



结论：

1、Mongodb的属性值可以是一个文档（套娃），如果属性是文档的话，我们称之为内嵌文档。

2、Mongodb支持直接通过内嵌文档的属性进行查询，如果要查询内嵌文档可以通（.）的形式来进行匹配。如果通过内嵌文档方式查询，此时属性名必须使用引号（单引号或双引号）。

3、$push是向数组中添加一个新的元素，不会去重

​      $addToSet也是添加新的元素，会去重

4、条件操作符：$lt   <     ;     $gt     >    ;     $lte     <=          ;            $gte       >=

5、limit和skip

```shell
db.COLLECTION_NAME.find().limit(NUMBER)
db.COLLECTION_NAME.find().limit(NUMBER).skip(NUMBER)
		  
                 skip
第一页	 [1,10]     0
第二页	 [11,20]    10
第三页	 [21,30]    20
第一页	 [31,40]    30

skip((页码-1) * 每页的条数).limit(每页显示的条数)
```





文档之间的关系：

1、一对一 1:1

一个身份证号对应一个人

```shell
db.idnum.insert({
	id:"No000002",
	person:{
		name: "李四"
	}}
)

```

2、一对多1:n

用户-订单

```shell
# 用户
db.user.insert({
	id:"u001",
	name:"Jack"
})
####      { "_id" : ObjectId("6072f5b4f1a0f1cb6a01c57c"), "id" : "u001", "name" : "Jack" }
# 订单
db.order.insert({
	userid:ObjectId("6072f5b4f1a0f1cb6a01c57c"),
	products:["苹果","香蕉","梨"]
})
```

查询出Jack的订单信息

```shell
var user_id = db.user.findOne({name:"Jack"})._id;
db.order.find({userid:user_id});

```





文档间的关系练习：

```shell
插入dept和emp数据
db.dept.insertMany([{
  "_id" : ObjectId("5941f2bac1bc86928f4de49a"),
  "deptno" : 10.0,
  "dname" : "财务部",
  "loc" : "北京"
},
{
  "_id" : ObjectId("5941f2bac1bc86928f4de49b"),
  "deptno" : 20.0,
  "dname" : "办公室",
  "loc" : "上海"
},
{
  "_id" : ObjectId("5941f2bac1bc86928f4de49c"),
  "deptno" : 30.0,
  "dname" : "销售部",
  "loc" : "广州"
},
{
  "_id" : ObjectId("5941f2bac1bc86928f4de49d"),
  "deptno" : 40.0,
  "dname" : "运营部",
  "loc" : "深圳"
}]);



db.emp.insertMany([
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4ac"),
  "empno" : 7369.0,
  "ename" : "林冲",
  "job" : "职员",
  "mgr" : 7902.0,
  "hiredate" : ISODate("1980-12-16T16:00:00Z"),
  "sal" : 800.0,
  "depno" : 20.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4ad"),
  "empno" : 7499.0,
  "ename" : "孙二娘",
  "job" : "销售",
  "mgr" : 7698.0,
  "hiredate" : ISODate("1981-02-19T16:00:00Z"),
  "sal" : 1600.0,
  "comm" : 300.0,
  "depno" : 30.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4ae"),
  "empno" : 7521.0,
  "ename" : "扈三娘",
  "job" : "销售",
  "mgr" : 7698.0,
  "hiredate" : ISODate("1981-02-21T16:00:00Z"),
  "sal" : 1250.0,
  "comm" : 500.0,
  "depno" : 30.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4af"),
  "empno" : 7566.0,
  "ename" : "卢俊义",
  "job" : "经理",
  "mgr" : 7839.0,
  "hiredate" : ISODate("1981-04-01T16:00:00Z"),
  "sal" : 2975.0,
  "depno" : 20.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b0"),
  "empno" : 7654.0,
  "ename" : "潘金莲",
  "job" : "销售",
  "mgr" : 7698.0,
  "hiredate" : ISODate("1981-09-27T16:00:00Z"),
  "sal" : 1250.0,
  "comm" : 1400.0,
  "depno" : 30.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b1"),
  "empno" : 7698.0,
  "ename" : "西门庆",
  "job" : "经理",
  "mgr" : 7839.0,
  "hiredate" : ISODate("1981-04-30T16:00:00Z"),
  "sal" : 2850.0,
  "depno" : 30.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b2"),
  "empno" : 7782.0,
  "ename" : "柴进",
  "job" : "经理",
  "mgr" : 7839.0,
  "hiredate" : ISODate("1981-06-08T16:00:00Z"),
  "sal" : 2450.0,
  "depno" : 10.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b3"),
  "empno" : 7788.0,
  "ename" : "公孙胜",
  "job" : "分析师",
  "mgr" : 7566.0,
  "hiredate" : ISODate("1987-07-12T16:00:00Z"),
  "sal" : 3000.0,
  "depno" : 20.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b4"),
  "empno" : 7839.0,
  "ename" : "宋江",
  "job" : "董事长",
  "hiredate" : ISODate("1981-11-16T16:00:00Z"),
  "sal" : 5000.0,
  "depno" : 10.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b5"),
  "empno" : 7844.0,
  "ename" : "阎婆惜",
  "job" : "销售",
  "mgr" : 7698.0,
  "hiredate" : ISODate("1981-09-07T16:00:00Z"),
  "sal" : 1500.0,
  "comm" : 0.0,
  "depno" : 30.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b6"),
  "empno" : 7876.0,
  "ename" : "李逵",
  "job" : "职员",
  "mgr" : 7902.0,
  "hiredate" : ISODate("1987-07-12T16:00:00Z"),
  "sal" : 1100.0,
  "depno" : 20.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b7"),
  "empno" : 7900.0,
  "ename" : "武松",
  "job" : "职员",
  "mgr" : 7782.0,
  "hiredate" : ISODate("1981-12-02T16:00:00Z"),
  "sal" : 950.0,
  "depno" : 10.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b8"),
  "empno" : 7902.0,
  "ename" : "吴用",
  "job" : "分析师",
  "mgr" : 7566.0,
  "hiredate" : ISODate("1981-12-02T16:00:00Z"),
  "sal" : 3000.0,
  "depno" : 20.0
},
{
  "_id" : ObjectId("5941f5bfc1bc86928f4de4b9"),
  "empno" : 7934.0,
  "ename" : "鲁智深",
  "job" : "职员",
  "mgr" : 7782.0,
  "hiredate" : ISODate("1982-01-22T16:00:00Z"),
  "sal" : 1300.0,
  "depno" : 10.0
}
])
```



1、查询工资小于2000的员工

```shell
db.emp.find({sal:{$lt:1000}})
```

2、查询工资[1000,2000]的员工

```shell
db.emp.find({sal:{$gte:1000,$lte:2000}})
```

3、查询工资小于1000**或**大于2500的员工

```shell
db.emp.find(
	{$or:
		[
			{sal:{$lt:1000}}, 
			{sal:{$gt:2500}}
		]
	}
)
```

4、查询财务部的所有员工

```shell
var financeDeptNo = db.dept.findOne({dname : "财务部"}).deptno;
db.emp.find({depno:financeDeptNo})
```

5、为所有薪资低于1000的员工增加工资500元

```shell
db.emp.updateMany({sal:{$lt:1000}},
					{$inc:{sal:500}}
					)
```

db.emp.find();

查询文档时，默认是按照_id的值进行升序排列，其实就是时间从小到大排列。

db.emp.find().sort({sal:1})   # 1 / -1   1表示升序，-1表示降序



在查询时，可以在find()的第二个位置处设置查询的结果的投影

db.emp.find({}, {ename:1, _id:0, job:1})



### 二、Mongo结合spring进行CRUE操作

> 1. MongoTemplate
>2. MongoRepository



首先导入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>

<dependency>
    <groupId>joda-time</groupId>
    <artifactId>joda-time</artifactId>
    <version>2.10.1</version>
</dependency>
```



编写实体类entity

```java
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("User")
public class User {

    @Id
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String createDate;
}
```





### 1、MongoTemplate使用

```shell
import com.ityj.mongo.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.jws.soap.SOAPBinding;
import java.time.Instant;
import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    // 查
    @Test
    public void queryAllDataTest() {
        List<User> userList = mongoTemplate.findAll(User.class);
        System.out.println("userList = " + userList);
    }

    @Test
    public void queryByIdTest() {
        User user = mongoTemplate.findById("61192b76e10e2151c35c9665", User.class);
        System.out.println("user = " + user);
    }
    // 条件查询
    @Test
    public void queryByConditionTest() {
        Criteria criteria = Criteria.where("name").is("zhangsan").and("age").is(21);
        Query query = new Query(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println("users = " + users);
    }

    // 模糊查询
    @Test
    public void findUsersLikeName() {
        String name = "test";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Query query = new Query(Criteria.where("name").regex(pattern));
        List<User> userList = mongoTemplate.find(query, User.class);
        System.out.println(userList);
    }

    // 分页查询
    @Test
    public void findUsersByPage() {
        int pageNo = 2;
        int pageSize = 3;
        Query query = new Query();
        // 根据skip 和 limit 进行分页
        List<User> users = mongoTemplate.find(query.skip((pageNo - 1) * pageSize).limit(pageSize), User.class);
        System.out.println("users = " + users);

    }


    // 增
    @Test
    public void addDataTest() {
        User user = new User();
        user.setAge(20);
        user.setName("test");
        user.setEmail(Instant.now().getEpochSecond() + "@qq.com");
        User insert = mongoTemplate.insert(user);
        System.out.println("insert = " + insert);
    }

    // 删
    @Test
    public void deleteDataTest() {
        Criteria criteria = Criteria.where("_id").is("6119ab4c85e1321cd1793404");
        Query query = new Query(criteria);
        DeleteResult remove = mongoTemplate.remove(query, User.class);
        System.out.println("remove = " + remove);
    }

    // 改
    @Test
    public void updateDataTest() {
        User user = mongoTemplate.findById("61192b76e10e2151c35c9665", User.class);
        if (user == null) {
            user = new User();
        }
        user.setName("test_update");
        user.setAge(25);
        user.setEmail("116@qq.com");
        Criteria criteria = Criteria.where("_id").is("61192b76e10e2151c35c9665");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("name", user.getName());
        update.set("age", user.getAge());
        update.set("email", user.getEmail());
        UpdateResult upsert = mongoTemplate.upsert(query, update, User.class);
        long matchedCount = upsert.getMatchedCount();
        System.out.println("matchedCount = " + matchedCount);
    }

}
```



### 2、MongoRepository使用

#### 2.1  首先编写接口UserRepository

```java
import com.ityj.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

```

#### 2.2 test编写

```java
import com.ityj.mongo.config.UserRepository;
import com.ityj.mongo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
public class MongoRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // 查
    @Test
    public void queryAllDataTest() {
        List<User> userList = userRepository.findAll();
        System.out.println("userList = " + userList);
        System.out.println(userList.size());
    }

    @Test
    public void queryByIdTest() {
        Optional<User> user = userRepository.findById("611ef214c8e69c70563227ed");
        System.out.println("user = " + user);
    }

    // 条件查询
    @Test
    public void queryByConditionTest() {
        User user = new User();
        user.setName("test");
        user.setEmail("1629418004@qq.com");
        Example<User> userExample = Example.of(user);
        List<User> users = userRepository.findAll(userExample);
        System.out.println("users = " + users);
    }

    // 模糊查询
    @Test
    public void findUsersLikeName() {
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        User user = new User();
        user.setName("TEST");
        Example<User> userExample = Example.of(user, matcher);
        List<User> userList = userRepository.findAll(userExample);
        System.out.println("userList = " + userList);
    }

    // 分页查询
    @Test
    public void findUsersByPage() {
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        //0为第一页
        Pageable pageable = PageRequest.of(1, 3, sort);
        //创建匹配器，即如何使用查询条件
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        User user = new User();
        user.setName("TEST");
        Example<User> userExample = Example.of(user, matcher);
        Page<User> userPage = userRepository.findAll(userExample, pageable);
        System.out.println("users = " + userPage.get().collect(Collectors.toList()));
    }

    // 增
    @Test
    public void addDataTest() {
        User user = new User();
        user.setAge(20);
        user.setName("test_repository");
        user.setEmail(Instant.now().getEpochSecond() + "@163.com");
        User insert = userRepository.save(user);
        System.out.println("insert = " + insert);
    }

    // 删
    @Test
    public void deleteDataTest() {
        userRepository.deleteById("611534de981b56ab41592b4a");
    }

    // 改
    @Test
    public void updateDataTest() {
        User user = userRepository.findById("61192b76e10e2151c35c9665").get();
        user.setName("张三_update");
        user.setAge(25);
        user.setEmail("88888888@qq.com");
        User save = userRepository.save(user);
        System.out.println(save);
    }

}
```

