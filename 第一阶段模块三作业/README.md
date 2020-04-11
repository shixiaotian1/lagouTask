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

##### 