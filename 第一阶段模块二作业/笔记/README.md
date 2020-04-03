### 笔记

#####IOC
- 控制反转；
- 控制：控制对象创建；
- 反转：控制权交给外部环境IOC创建；
- 目的：IOC实现的类与类之间的依赖解耦；
#####DI
- DI与IOC描述的是同一样东西，只不过站在不同的角度上进行描述的
- IOC是站在对象的角度上描述，对象交给谁管理和创建；
- DI是站在容器的角度上，对象的创建过程是通过注入的方式；
#####AOP
- OOP，封装、继承和多态，其中继承是纵向,AOP是OOP思想的一种延续；
- AOP是面向切面编程，对流式代码进行横切,横向抽取机制，将横切逻辑代码与业务逻辑代码分离；
- [切]，原有业务代码不能动，只能操作横切逻辑代码；
- [面]，横切逻辑影响多个方法，每个方法都是一个点，多个点连成一个面；
#####手写IOC

#####手写AOP
- 给service层，添加事物

- 从线程获取链接，判断当前线程是否已绑定链接，绑定直接返回，没有绑定则从连接池获取一个返回；

- 1
  #####BeanFactory与ApplicationContext区别

- BeanFactory是Spring框架中IOC容器的顶层接口，只是用来定义了一些基础功能和基础规范；

- ApplicationContext是它的一个子接口，拥有BeanFactory的全部功能；
  #####SpringIOC XML模式配置
  #####SpringIOC 实例化Bean的三种方式

- 使用无参构造器

- 静态方法

- 实例化方法

#####Bean作用范围及生命周期

- Scope定义Bean作用范围
	1. singleton,单例；
	2. prototype,原型，每次使用该类，都会返回一个新对象；
	3. 默认单例；
	
- 单例模式Bean生命周期与容器相同；原型模式中，Spring只负责创建对象，不复杂管理对象，由垃圾回收器回收；
#####Bean标签属性

- id、class、name、init-method、destroy-method
#####DI依赖注入

- set注入，使用property标签，ref注入对象，value注入值；

- 构造器注入，使用constructor-arg标签，index表示入参位置，或者name入参名称，进行传值；
#####SpringIOC XML和注解模式配置

#####SpringIOC 注解模式配置

  - @Configuration注解，表明当前类是一个配置类；
  - @ComponentScan注解，扫描包；
  - @PropertySource注解，引入外部属性配置文件；
  - @Import引入其他配置类；
  - @Bean将方法返回对象加入SpringIOC容器；

#####Spring IOC高级特性 —— lazy-Init延迟加载

  - ApplicationContext容器默认行为是在启动服务时将所有的bean提前实例化，放入内存中；
  - 延迟加载是在使用时，才去创建bean；
  - 延迟加载仅适用于singleton bean，默认是false；
  - 全局开启延迟加载，beans标签中加入default-lazy-init;
  - 延迟加载可以提高容器启动和运转性能；
  - 不常使用的Bean设置延迟加载，减少资源占用；

#####Spring IOC高级特性 —— FactoryBean

  - Spring中Bean有两种，一种普通Bean，一种工厂Bean（FactoryBean）；
  - 可以生成某一个类型的Bean实例，可以借助于它来自定义Bean的创建过程；
  - 实现FactoryBean接口，并重写方法；

#####后置处理器
- BeanPostProcessor和BeanFactoryProcessor；
#####SpringIOC源码解读注意事项
- 定焦原则，抓主线；宏观原则，关注结构和流程；
- 断点，观察调用栈；反调，Find Usages；
#####Spring源码构建
- 下载源码
- 安装gradle5.6.3
- 导入，编译工程，core-oxm-context-beans-aspects-aop，工程-tasks-compileTask.java