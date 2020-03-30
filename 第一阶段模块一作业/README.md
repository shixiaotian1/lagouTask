### 项目说明

第一阶段模块一作业

### 简答题

##### 一、Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？
- 在复杂组合查询时，会遇到繁琐的拼接字符串的问题，而动态sql就是通过OGNL表达式来消除拼接可能产生的其他元素；

- 常见的动态sql标签有:`<if>`、`<choose>`、`<when>`、`<trim>`、`<where>`、`<set>`、`<foreach>`、`<bind>`；

- XMLScriptBuilder类中的parseScriptNode()方法中解析sql中，当遇到子标签会递归解析得到sqlNode，解析完成后放入SqlSource中，并返回最终放入Configuration中；

##### 二、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？
- Mybatis是支持延迟加载的，也就是懒加载；例如在一对多查询的时候，只查询出一方，当程序中需要数据时mybatis再发出sql语句进行查询；
- ResultSetHandler在创建返回对象的时候，会查看resultMap标签中有没有需要懒加载的属性，如果有会为这个返回对象创建一个代理，是使用javassist方式创建，代理类继承了返回对象的所有属性并根据配置参数重写了被代理类中的方法，默认重写get/set、clone、equals、hashCode、toString方法，当调用重写方法时会判断是否执行过懒加载，如果执行过则返回结果，如果没有则执行一次查询sql，完成懒加载；

##### 三、Mybatis都有哪些Executor执行器？它们之间有什么区别?
- 分别是：SimpleExecutor、ReuseExecutor和BatchExecutor；
- SimpleExecutor，默认是这种，每次执行sql会开启一个statement对象，使用完成之后立即关闭；
- ReuseExecutor，执行sql时会查询Statement是否存在，存在则使用，不存在则创建一个，使用完之后存入Map中；
- BatchExecutor，批处理执行器，将sql添加到批处理中，统一执行executorBatch()；

##### 四、简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？
- 一级缓存作用范围是sqlSession级别的，在sqlSession对象中有一个HashMap结构cache来存储查询结果，不同的sqlSession中cache是互不影响的；当进行sqlSession.commit()的时候会情况一级缓存；
- 二级缓存作用范围是mapper级别的，是跨sqlSession级别的；底层也是使用HashMap实现的缓存结构，但是POJO需要实现Serializable接口；当进行commit的时候，二级缓存会被清空；

##### 五、简述Mybatis的插件运行原理，以及如何编写一个插件？
- Mybatis插件其实就是拦截器，对四大核心对象（Executor、StatmentHandler、ParamterHandler、ResultSetHandler）进行功能增强；
- 四大核心对象创建出来的对象不是直接返回的，而是获取到所有的Interceptor，调用plugin(target)返回包装后的对象；
- 编写插件首先需要实现Interceptor接口，然后自定义类上添加@Intercepts注解，在注解中添加@Signature注解来指定拦截哪个对象以及哪个方法；最后在mapperConfig.xml中添加`<plugins>`标签；