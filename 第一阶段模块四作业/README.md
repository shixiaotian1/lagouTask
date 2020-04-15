### 笔记

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

