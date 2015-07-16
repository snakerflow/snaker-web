# snaker-web
snakerflow web application.

snaker-web项目是基于snaker流程引擎开发的web应用，其中包含流程部署、设计、启动；任务的执行、驳回、撤回、转派、委托代理等。

+ 基于SpringMVC、Spring3、Hibernate3、Shiro、Snaker搭建的基础演示应用
+ 包含：用户管理、部门管理、角色管理、权限管理、资源管理、菜单管理、流程管理、数据字典等常用功能模块

与snakerflow-2.0.0配套的web应用snaker-web-1.0.0增加： 
  + 表单自定义、流程节点可绑定表单、状态图增强等 
  
## 运行方法
1. 获取程序
>git clone https://github.com/snakerflow/snaker-web.git

2. 导入工作区间

使用IDEA或者Eclipse导入项目

3. 建立数据库

在所使用的数据库中，建立一个数据库。

4. 修改程序db配置文件

修改`src/main/resoucres/application.properties`,将其中的数据库配置部分修改为自己的数据库配置。

5. 运行程序

程序会自动填充数据。

6. 初始化流程

演示系统提供了两个流程，但是默认数据库没有数据。使用admin登录，点击左侧流程定义，右边页面刷新之后，点击“初始化”按钮。
“初始化”按钮往向工作流引擎中部署流程。之后就可以测试流程了。

Enjoy!
