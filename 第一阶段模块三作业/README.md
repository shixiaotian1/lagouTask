### 项目说明

##### 作业一

- mvc文件夹为作业一代码；
- 示例地址：http://localhost:8080/demo/query?username=lisi
- 可成功用户：zhangsan、lisi

##### 作业二



### 笔记

##### MVC体系结构

- 三层架构，表现层、业务层和持久层；
- MVC模式，M（model模型）、V（view视图）和C（controller控制器）；

##### Spring MVC

- Spring Web MVC，是基于Java的实现MVC设计模型的请求驱动类型的轻量级Web框架；
- 用户请求 — DispatcherServlet — HandlerMapping — HandlerAdapter — Handler — ViewResolver — Jsp、Freemarker

##### Spring MVC九大组件

- HandlerMapping处理器映射器，用来查找Handler的，Handler的实现可以是类，也可以是方法；
- HandlerAdapter处理器适配器；
- HandlerExceptionResolver异常处理；
- ViewResolver视图解析器，处理jsp并返回view视图；
- RequestToViewNameTranslator，根据ViewName查找View；
- LocaleResolver，处理国际化；
- ThemeResolver，解析主题；
- MultipartResolver，用于上传请求，用来处理文件上传；
- FlashMapManager，用于重定向时参数传递；

##### 返回数据封装

- Model、Map和ModelMap运行时具体类型是BindingAwaerModelMap；
- ModelMap实现了Map接口；
- BindingAwareModelMap继承了ModelMap实现了Model接口；

##### 绑定日期类型参数

- 自定义类型转换器，实现`Converter<String,Date>`完成字符串向日期类型转换；
- 注册自定义类型转换器；

##### Restful风格

- REST(Representational State Transfer)，认为互联网中所有东西都是资源，会有一个唯一的uri标识它；
- 根据请求方式不同，代表做不同的操作；

##### Spring MVC队Restful风格的支持

- 使用@PathVariable("value")注解，解析路径参数；
- 过滤器解决post提交乱码问题`<filter/>`，encoding；
- springMvc请求方式转换过滤器`<filter>`，hiddenHttpMethodFilter；

##### Spring MVC监听器、过滤器和拦截器

- Filter过滤器，对请求起到过滤作用，作用在Servlet之前；
- Listener监听器，随Web应用启动而启动，随Web应用停止而销毁；
- Interceptor拦截器，是框架自己组件，不会拦截静态资源，只会拦截Handler方法；

##### Spring MVC拦截器

- 多个拦截器，拦截器会按照顺序执行；

##### Spring MVC异常处理机制

- @ExceptionHandler注解方法，编写异常处理逻辑，仅在当前类中生效；
- @ControllerAdvice注解类，全局处理异常；

##### Spring MVC重定向传参

- RedirectAttributes.addFlashAttribute("")设置了一个flash类型的属性，该属性会被暂存在session中，在跳转页面之后销毁；

##### Spring Data JPA

- 应用于Dao层的框架，简化数据库开发，作用于Mybatis框架一样；
- Spring Data JPA提供的一套对JPA操作更加高级的封装，是在JPA规范下专门用来运行数据持久化解决方案的；
##### Spring Data JPA实体

- 实体类对应数据库中表；
- 主键生成策略，@GeneratedValue()，GenerationType.IDENTITY依赖数据库中自增；GenerationType.SEQUENCE依靠序列产生主键；
- @Cloumn(name="name")标识字段；

##### Spring Data JPA应用Dao层接口规范

- Dao接口层，继承`JpaRepository<T,ID>`，`JpaSpecificationExecutor<T>`接口，因为Dao是接口，所以可以多继承；
- JpaRepository封装了基本的CRUD操作；JpaSpecificationExecutor封装了复杂的查询（分页、排序等）；

##### Spring Data JPA查询方式

- 调用继承接口中的方法，直接进行查询；
- 引入jpql语句进行查询，jpql类似于sql，只不过操作的是对象和属性；
- 引入原生sql语句进行查询，需要将nativeQuery设置为true；
- 方法命名规则查询，方法名以findBy开头，属性名首字母大写查询；
- 动态查询，传入specification参数；