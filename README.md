spring cloud demo 项目
-
#### 项目使用到的技术
1.Spring cloud  
* eureka注册中心
* gateway网关
* feign声明式调用
* hystrix熔断机制  
* zipkin服务追踪治理
    
2.Spring boot  
3.Redis  
4.Mongodb  
5.Rabbitmq

#### 项目框架版本
- spring cloud RC.2
- spring boot 2.x
- maven 3.9
- jdk 1.8
- redis 2.9
- mongodb 3.x
- rabbitmq

#### 服务说明
- common-service 通用服务
- eureka-service 注册中心
- order-service 订单服务
- product-service 商品服务
- user-service 用户服务
- address-service 地址服务
- gateway-service 网关服务
- zipkin-service 服务跟踪治理



##### 简要说明
address-service 使用的技术框架比较全，包含redis,mongodb,rabbitmq

**mongodb:**  
mongodb使用了mongodbAdmin可视化工具，所以首先需要安装这个工具，
然后使用git命令 "npm start"启动后然后再访问 http://127.0.0.1:1234 即可进入mongodb
可视化页面,进去后就可以看到Mongodb的数据。

**rabbitmq:**
rabbitmq 可视化界面需要安装插件，安装完后访问  http://127.0.0.1:15672/ 既可以进入可视化界面
进去后就可以看到消息队列，交换机等信息。

**redis:**
redis 可视化工具使用 redis desktop manager 下载安装连接到redis即可使用





