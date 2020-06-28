English | [中文]()

# 1 这是什么
前端JavaFx+后端Spring Boot实现的小型薪酬管理系统，包含了源码和前端打包生成的exe与jar，还有后端打包生成的war与jar。

# 2 运行
本项目已经打包，对应打包文件位于release下，需要先进行解压。

需要的环境：

- 后端
- OpenJDK 11
- MySQL 8.0+
- Tomcat 9+（可选，使用jar部署可以不需要Tomcat）


- 前端
- OpenJDK 11
- （若是Windows无需JDK，已包含独立exe）

## 2.1 后端
首先把后端运行（注意需要先开启数据库服务，同时需要确保存在对应的数据库，用户名与密码，resources下有一个init.sql文件，直接导入即可），后端提供了jar与war两种方式：
## 使用jar

```bash
java -jar Backend.jar
```

## 使用war

直接放到Tomcat的webapps下然后到bin下：

```bash
./startup.sh

```

## 2.2 前端

前端提供了exe与jar两种方式运行，其中exe为一个独立文件，无需JDK环境，其他系统可以jar运行：

```bash
java -jar Frontend.jar
```

## 2.3 若运行失败

若运行失败可以用IDEA打开项目直接在IDEA中运行或者自行打包运行。

为了方便删去了idea自带的.gitignore文件，实测运行前端的项目点击直接运行后需要指定模块：

![](https://img-blog.csdnimg.cn/20200606171719997.png)

![](https://img-blog.csdnimg.cn/20200606171810118.png)

后端项目直接打开运行即可，同理需要确保开启数据库服务以及存在对应的用户名与密码。

若项目不能直接运行，解决的办法是

- 1、新建工程，前端为Maven，后端为Spring Boot
- 2、复制源码，同时修改为对应的包
- 3、复制依赖，即`pom.xml`中的`<dependencies>`，并进行导入
- 4、复制资源文件，即`resources`下的文件
- 5、运行

若上述方法都不能运行，请提issue或私信笔者。

# 3 管理员帐号
默认管理员帐号与密码为

```
admin
password
```

可以自行修改application.properties中的帐号与密码，建议加密。

# 4 加密

可以先修改`pom.xml`中的`Jasypt Spring Boot Starter`为最新版本，已配备测试文件，先把明文写在配置文件`application.properties`中，然后运行测试，接着使用密文替换原来的明文。

# 5 博客详细说明

[CSDN](https://blog.csdn.net/qq_27525611/article/details/105083135)

[博客园](https://www.cnblogs.com/6b7b5fc3/p/13054733.html)

[Github Pages](https://www.bingling.site/post/javafxspringbootyan-zheng-ma-gong-neng-de-xiao-xing-xin-chou-guan-li-xi-tong/)


# 更新日志
# 2020.06.15 更新
更新博客的HTTPS部分以及验证码部分的说明，默认没有加上HTTPS以及验证码的功能，需要使用HTTPS请自行修改：

- `com.test.network.OKHTTP`
- `resources/key/pem.pem`

同时后端需要部署，详情见博客。


需要短信功能的话，如果使用腾讯云API可以直接修改配置文件，使用其他API请自行对接，需要修改的部分如下：

- 前端：

- `com.test.network.OKHTTP`
- `com.test.network.request.SendSmsRequest`
- `com.test.network.requestBuilder.SendSmsRequestBuilder`
- `com.test.controller.start.RetrievePasswordController`

- 后端：
- `com.test.controller.SmsController`

# 2020.06.11 更新
修复跨域请求问题：

![](https://s1.ax1x.com/2020/06/11/tqZ6JO.png)

使用Postwoman测试，发送GET/POST请求会出现CORS问题，解决方法是在Controller上加上了@CrossOrigin注解，默认使用3000端口，使用其他端口请自行修改@CrossOrigin中的值。

# 2020.06.10 更新

修复Field injection is not recommended提示：

![](https://img-blog.csdnimg.cn/20200610210255455.png)

参考了[这篇文章](https://blog.csdn.net/jianzhang11/article/details/105283642)

使用了`@RequiredArgsConstructor`代替了直接使用`@Autowired`。




