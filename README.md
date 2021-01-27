用于根据配置模板，快速生成controller,service,serviceimpl 代码
![生成代码演示](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/21449365672346a5a173ed07225b927d~tplv-k3u1fbpfcp-zoom-1.image)


![配置演示](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/552babcd1275451eb91c0fc889ba2c63~tplv-k3u1fbpfcp-zoom-1.image)







## 为什么要开发这款插件
##### 市面上有很多基于数据库生成代码的工具，但是我自己的工作流，是只用数据库生成代码工具生成pojo,mapper,mapper.xml,至于控制层，业务层的代码，还是自己手动new的。因为我觉得并不是每一个表都要对应一个控制层和业务层，所以开发了这个插件。

## 安装

#### 1.下载地址
https://gitee.com/healingtjx/tjx-cold/releases/1.0.0

https://github.com/healingtjx/tjx-cold/releases/tag/1.0.0

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/fea3bd4cee104380929ffd56a2b1d5f5~tplv-k3u1fbpfcp-watermark.image)

![](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c0db2cf2d1ba453eacb2b0b1c6586885~tplv-k3u1fbpfcp-watermark.image)

#### 2.idea 安装插件
idea -》 设置 -》 Plugins -》 Install Plugin from Disk（本地安装）
![](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7652c25bd34e4cfeb14ea13556167a2b~tplv-k3u1fbpfcp-watermark.image)

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c7591adf51fd454c9645d0cb2834bd09~tplv-k3u1fbpfcp-watermark.image)

![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/944255a99b7e49699b30ec84de8c4421~tplv-k3u1fbpfcp-watermark.image)


## 配置
![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/60c648f3a4c6417fbae9c71dea228f35~tplv-k3u1fbpfcp-watermark.image)
### 模式选择
![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6e93a69b349b404f8b4c338b1bd396f5~tplv-k3u1fbpfcp-watermark.image)
* 默认    
controller/{fileName}.java

service/{fileName}.java

service/impl/{fileName}.java
* 模式1(controller,service 都加 Package)
controller/{Package}/{fileName}.java

service/{Package}/{fileName}.java

service/impl/{Package}/{fileName}.java

* 模式2(只有contrller 加 Package)

* 模式3(只有service,impl 加 Package)


## 模版代码配置
根据自己的工程配置自己的模版代码（后续我会把定义号的变量整理出来）

我的存储用的poject 级别所以每一个工程都可以配置一个
![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f126f41e3d934b64898d9b5f868959d1~tplv-k3u1fbpfcp-watermark.image)
![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8189237cf81a4f30b3dda3825f6d83d5~tplv-k3u1fbpfcp-watermark.image)


### 使用
![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6f25112bf5894b52b5db4972914bc6f4~tplv-k3u1fbpfcp-watermark.image)

![](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4c8c997a2d484902990b6ea0ee6fbfef~tplv-k3u1fbpfcp-watermark.image)

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/23cf79d6112144a0982b3f4ef0651904~tplv-k3u1fbpfcp-watermark.image)

## 后续展望
我觉得不仅仅是后台代码有很多重复的操作，同理也可以把前端代码根据后台接口自动生成。所以后续我会实现根据 controller 代码生成前端代码(基于自己搭建好的vue的后台模版项目)

最终想实现效果：利用插件生成后台 controller,service,serviceimpl, 前端 xxx.vue,xxapi.js(接口请求)，路由代码。

后台只需要实现 列表，新增/修改，删除的3个impl 下面的代码(基于mybatis-plus) 几行代码就可以实现。

前端只需要实现 修改列表 的字段名称，校验相关的rules下的代码。


## 参考项目和文献
环境搭建
https://cloud.tencent.com/developer/article/1348741

参考项目
https://github.com/xiaoxinglai/crudboy
https://github.com/x-hansong/CodeMaker

解决了项目级别配置
https://www.zhihu.com/question/378975906/answer/1096139536
