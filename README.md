# 1 这是什么
前端JavaFx+后端Spring Boot实现的小型薪酬管理系统，包含了源码和可执行jar与exe。

# 2 使用
本项目已经打包，前端包括jar与exe，后端包括jar与war，首先把后端运行（先开启数据库服务）：
使用jar：
```bash
java -jar Backend.jar
```
使用war直接放到Tomcat的webapps下然后到bin下：
```bash
./startup.sh
```
接着运行前端，Windows的话可以直接运行exe，当然也可以jar，Linux的话jar：
```bash
java -jar Frontend.jar
```
若运行失败可以用IDEA打开项目直接在IDEA中运行或者自行打包运行。

# 3 管理员帐号
默认管理员帐号与密码为
```
admin
password
```
可以自行修改application.properties中的帐号与密码，建议加密。

# 4 建议配合博客食用

[CSDN](https://blog.csdn.net/qq_27525611/article/details/105083135)

[博客园](https://www.cnblogs.com/6b7b5fc3/p/13054733.html)

[Github Pages](https://www.bingling.site/post/javafxspringbootyan-zheng-ma-gong-neng-de-xiao-xing-xin-chou-guan-li-xi-tong/)




