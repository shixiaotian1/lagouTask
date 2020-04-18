### 笔记

### spring boot简介和常用功能

##### spring boot约定优于配置

- 约定优于配置（Convention over Configuration），又称为约定编程；

- 起步依赖，具备某种功能的坐标打包到一起，并提供默认功能；
- 自动配置，spring boot会自动将一些配置类的bean注册进ioc容器；

##### 全局配置文件

- spring Boot使用applaction.properties或applaciton.yaml文件作为全局配置文件；

##### 自定义属性配置properties版本

- @Component注解，@ConfigurationProperties(prefix="前缀")；
- pom中添加配置文件属性值注入；
- ctrl + F9重新构建项目；

##### yaml配置文件

- YAML文件扩展名可以使用yaml或yml；
- 先加载yml再yaml最后properties，在pom文件spring-boot-starte-parent中，可以看到`<includes>`标签，配置文件加载顺序；
- spring boot已有属性，会自动扫描并覆盖；自定义实体属性，需要在程序中注入；
- @ConfigurationProperties(prefix="前缀")和@value进行属性注入；
- @Value无需get/set方法，会通过反射注入；

##### 自定义配置文件和自定义配置类

- @PropertySource("classpath:名称")配置自定义配置文件名称和位置；

- @Configuration标明类为配置类，@Bean将返回值对象作为组件添加到spring容器中；

##### 随机值设置和参数间引用

- ${random.xxx}进行随机值注入；
- 配置文件的属性值支持参数间引用，就是后一个配置的属性值中直接引用先前已经定义过的属性；

### spring Boot原理

##### 依赖管理

- 父pom文件负责，编码默认设置，JDK版本默认设置，引入配置文件（yml），进行插件管理；
- 爷pom文件负责，spring-boot-dependencies底层原文件中将常用技术框架统一进行了版本管理；
- spring boot提供了很多第三方框架的依赖启动器，只需要导入依赖启动器，就可以使用；

##### 自动配置

- 添加jar包依赖的时候，自动为我们配置一些组件相关配置，我们无需配置或只需要少量配置；
- @SpringBootApplication能够扫描Spring组件并自动配置Spring Boot；
  1. 启动springboot应用；
  2. @SpringBootApplication起作用；
  3. @EnableAutoConfiguration;
  4. @AutoConfigurationPackage这个组合注解主要是@Import(AutoConfigurationPackages.Registra.class)，它通过Registrar类导入到容器中，而Registrar类作用是扫描主配置类同级目录及子包，并将组件导入到springboot创建管理的容器中；
  5. @Import(AutoConfigurationImportSelector.class)它通过将AutoConfigurationImportSelector类导入到容器中，AutoConfigurationImportSelector类作用是通过selectImports方法执行的过程中，会使用内部工具类SpringFactoriesLoader，查找classpath上所有jar包中的META-INF/spring。factories进行加载，实现将配置类信息交给springFactory加载器进行一系列容器创建过程；
- @ComponentScan()包扫描器；

##### SpringBoot starter机制

- SpringBoot由众多starter组成；
- starter可以理解为一个可插拔式插件，可以让开发者不需要关注各种依赖库处理，不需要具体配置信息，由springBoot通过自动发现需要的Bean并组织相应的Bean；
- 独立于业务之外的功能模块封装成一个starter，复用的时候只需要将其在pom中引用依赖，Spring Boot完成自动装配；
- 官方建议自定义starter命名规则xxx-spring-boot-starter；

##### springApplication的run方法

- new SpringApplication();
- 执行run()方法；
  1. 获取并启动监听器；
  2. 项目运行环境Environment的预配置；
  3. 创建spring容器；
  4. spring容器前置处理；容器刷新之前的准备动作，将启动类注入容器；
  5. 刷新容器，开启spring容器，注册ShutdownHook钩子；
  6. spring容器后置处理；扩展接口，默认空实现；
  7. 发出结束执行的事件通知；
  8. 执行Runers，spring提供了ApplicationRunner和CommandLineRunner两种；项目启动成功后立即执行某些特定程序；
  9. 发布应用上下文就绪事件；

##### Thymeleaf标准表达式

- 变量表达式${xxx}，用于获取上下文中的变量值；
- 选择变量表达式*{xxx}，类似于变量表达式，用于从被选定对象获取属性值；
- 消息表达式#{xxx}，用于国际化内容的动态替换和展示；
- 链接表达式@{xxx}，用于页面跳转或者资源的引入；
- 片段表达式~{xxx}，用来标记一个片段模板，并根据需要移动或传递给其他模板。