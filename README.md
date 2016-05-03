
##基于dubbo的一个分布式项目基架##  
实现每层之前互相独立,后台系统以service为分割点，打包为一个一个的服务jar包,服务之间的通信目前使用dubbo协议。  
服务的管理、集群、负载均衡，均使用dubbo内置功能。  
为了方便使用,使用了控制器进行一次代理和数据验证等等工作,采用了一个springMVC+fastJSON的方式进行json处理和jsonp的支持。  
在前端使用angularJS(MVC)实现了后台的独立开发,即后台服务器只需要运行控制器即可,webpage则可以直接跑在nginx下,完全将页面  
和后台开发进行分离,开发部署。  

主要使用的技术：  

* spring(ioc)并没有使用AOP进行事物的管理,采用手动的方式进行的处理
* spring mvc(控制器)
* mybatis (持久)
* quartz(调度集群)
* dubbo (服务治理)
* fastjson (json处理)
* logback (日志处理)
* Jquery (简化DOM操作)
* angularJS (MVC框架，实现前后端分离开发) [前端页面点我](https://github.com/247687009/soa-page)
* bootstarp2 (CSS 布局)

