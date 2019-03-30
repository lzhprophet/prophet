**这个项目搭建运行起来可能有点复杂，后续慢慢的减少项目中使用的中间件以及优化部署方式**

**PS：寻求服务器赞助商，提供几台服务器，运行个DEMO版本，供大家学习试用**



Prophet项目的开源QQ交流群号:808833989 欢迎您的加入。 

----
## 关于Prophet
Prophet是一个大数据分析平台，面向用户行为、产品、系统性能及可用性三个主题的数据统计与分析。帮助运营、产品、技术人员提升工作质量及验证工作价值，辅助公司高层进行决策。

Prophet通过前端SDK在接入系统中进行手动数据埋点实时将用户触发的事件数据发送到数据收集接口，通过高性能的消息队列（kakfa），及数据处理管道组件（logstash），将数据转换存储至HDFS。每日定时通过数据分析模块对海量数据进行计算，获得三个主题相关维度的统计分析结果，通过Prophet-dashboard数据可视化系统进行展现。

#### 数据可视化部分功能截图

![blockchain](http://easr.panhaidata.com/images/1.png "dashboard")

![blockchain](http://easr.panhaidata.com/images/2.png "dashboard")

#### 元数据管理部分功能截图
![blockchain](http://easr.panhaidata.com/images/3.png "dashboard")
![blockchain](http://easr.panhaidata.com/images/4.png "dashboard")





## 系统结构
![blockchain](http://easr.panhaidata.com/images/5.png "architecture")

prophet-dashboard-api：数据可视化&Prophet平台管理后端接口程序

prophet-dashboard-ui：数据可视化&Prophet平台管理WEB-UI

prophet-data-analysis：ETL及数据统计分析程序

prophet-hadoop-script：数据模型创建脚本&hadoop等组件配置文件

prophet-js-sdk：web项目埋点数据采集jssdk

prophet-weixin-sdk：微信小程序项目埋点数据采集wxsdk (开发中)

prophet-receivedata-server：数据收集服务接口

prophet-pom：父pom







查看wiki更多内容: https://github.com/lzhprophet/prophet/wiki




版本：

2019-03-15   prophet-0.7.0   



