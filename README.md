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
- mysql 5.9
- redis 2.9
- mongodb 3.x
- rabbitmq

#### 服务说明
- common-service 通用服务，端口号 5005
- eureka-service 注册中心，端口号 5000
- order-service 订单服务，端口号 5100
- product-service 商品服务，端口号 5300
- user-service 用户服务，端口号 5200
- address-service 地址服务，端口号 5400
- gateway-service 网关服务，端口号 5001
- zipkin-service 服务跟踪治理，端口号 5003



##### 简要说明
由于使用了网关所以所有的服务都统一个地址访问 http://localhost:5001/*， 为了方便测试服务是否正常
在每个服务中都有一个 getPort() 获取端口号的接口，比如：</br>
http://localhost:5001/ADDRESS-SERVICE/address/getPort </br>
http://localhost:5001/ORDER-SERVICE/order/getPort </br>
由于使用了spring cloud RC版本的网关gateWay，所以在访问的时候采取的是默认的服务名大写的方式访问。

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

**druid监控平台:**
druid是阿里提供的一个管理sql的工具，首先配置引入druid相关jar包详见address-service的pom.xml配置文件
然后新增DruidStatFilter和DruidStatViewServlet配置文件即可。启动eureka-service和gateway-service
后再启动address-service，在浏览器中输入 http://localhost:5400/druid 即可进入到druid监控页面
输入账号admin，密码123456即可进入。






