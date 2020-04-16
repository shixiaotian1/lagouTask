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