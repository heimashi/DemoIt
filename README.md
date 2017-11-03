# Kotlin是什么？

新语言，针对java平台的，简洁、安全、务实，专注于Java平台互操作


### 特征：
* 1，目标平台：server，android，其他所有java平台
* 2，静态类型 所有表达式的类型在编译器已确定  动态类型 Groovy
        Vs Java：都是静态，但多了自动推断，多了对可空类型、函数类型的支持
* 3，函数式与面向对象
        函数式编程：头等函数、不可变性、无副作用（简洁，多线程安全，易测试）
* 4，免费并开源 apache2


### 编译过程：
*.kt      ---->kotlin编译器（kotlinc） ---->*.class.   ----> *.jar    +kotlin运行时   ---->run


### 安装：
* 1，kotlin插件 As3.0自带
* 2，命令行REPL
* 3，https://try.kotl.in



### 捷径：
java kotlin互转


### Android项目导入kotlin
* 1,
dependencies {
    classpath 'com.android.tools.build:gradle:3.0.0'
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version”
}
* 2,
compile "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"













# Kotlin快速入门

### 基本要素：函数和变量：


#### 函数：

语句和表达式：
* 表达式有值，并且能作为另一个表达式的一部分使用；
* 语句总是包围着它的代码块中的顶层元素，并且没有值
* Java中所有的控制结构都是语句
* kotlin中除了循环（for、do和do／while）以外大多数控制结构都是表达式


#### 变量：
* val 
* var


### 字符串模板：



### 类和属性：

* 类中自动生成get set
* 成员和函数默认都是public


### 目录和包：

* import package
* kotlin不要求包和目录对应


### 枚举：
enum class 


### When   If：
* 加强版的switch
* 代码块中的最后一个表达式就是结果
* 用when取代if
* kotlin中没有三目运算符 ？：


### 智能转换 is as：


### While   for：
While跟java一致，for in



### 异常：
* try catch finally
* throw是一个表达式 不用new
* try也是一个表达式
* 没有函数申明后的throws
* java区分受检异常（IOException ）和未受检异常（NumberFormatException， NPE）,受检异常必须显示的处理或者抛出
* kotlin中不区分受检异常和未受检异常















# 函数专题


### 创建集合：
* listOf 
* hashSetOf
* arrayListOf
* hashMapOf
* 集合复用java的集合类



### 用更简化的函数：
* 命名参数
* 参数默认值
* kotlin中没有静态static的方法和成员——>顶层函数和属性



### 扩展函数和属性：
* 扩展函数不能访问接受者类型里的私有和受保护的成员
* 导入 import xx.xx.lastChar as last
* 本质上是静态函数，参数中传入了接受者类型
* 扩展函数不能被override



### 可变参数 中缀调用
* vararg 
* *
* to
* 中缀调用可以与只有一个参数的函数一起使用
* Infix
* 解构声明
* 三重引号的字符串



### 局部函数和扩展
* 函数嵌套
* 抽取重复代码为局部函数
















# 类和接口专题

* 接口 interface
* 接口可以带默认实现的方法
* 不同接口中同名的函数必须实现
* 默认实现本质上是编译成一个java接口和一个静态函数



### final open abstract
* 默认是final
* 接口中的成员默认是open的
* 主构造函数，从构造函数



### 可见性修饰符：public private protected internal
* 默认是public
* 没有java中的默认可见性 包私有
* internal标示“只在模块内部可见”， 一个模块就是一组一起编译的kotlin文件。
* 例如：一个as项目，一个gradle项目
* 对模块实现真正封装，java可通过声明相同的包名来实现可见



### 内部类和嵌套类
* 默认是嵌套类，不能访问外部成员
* inner
* 密封类 sealed



### 数据类和类委托
* toString()
* Equal()
* hashCode()
* data数据类自动生成上面三个方法，并实现了copy方法
* 装饰器模式



### object
将声明一个类与创建一个实例结合起来
#### 作用1:对象声明  可以快速实现单例模式
    不允许有构造函数   
    java中调用的时候用INSTANCE调用该实例
#### 作用2:伴生对象
    kotlin中无static，作为替代，可以用顶层函数和对象声明来替代，顶层函数不能访问类的private成员
    用companion声明伴生对象
#### 作用3:对象表达式
    用来声明匿名对象，代替java中的匿名内部类
    访问没有被限制在final变量













# Lambda专题


### lambda和成员引用
* { x:Int, y:Int  ->   x+y }
* 参数 -> 表达式    并且始终在大括号中
* run来直接执行
* lambda如果是函数调用中的最后一个参数，可以放到括号外边
* It作为默认参数名
* Lambda捕捉，当捕捉final变量时，它的值和lambda代码一起存储，
* 而非final变量，它的值被封装在一个特殊的包装器中，而这个包装器的引用会和lambda代码一起存储




### 带接收者的lambda：with apply
* 减少重复的代码
* with结构不是一种特殊的语法结构，实际上是带两个参数的函数，只是lambda被提出来了
* apply被声明为扩展函数，始终会返回接受者对象，用于初始化实例时特别方便














# 类型系统专题


### 可空性
* 运行时的错误变成编译器的错误
* 可空类型
* 对可空类型显示支持，类型默认都是非空，声明可空用？
* 安全调用 ?.  空的时候返回null
* Elvis运算符 ?:
* 安全转换 as?
* 非空断言 !!



### 基本数据类型
* kotlin不区分基本数据类型和其包装类型
* java中分为基本数据类型和引用类型，基本存的是值，引用存的地址
* 整数类型 Byte Short Int Long
* 浮点数类型 Float Double
* 字符类型 
* 好处就是 “aaa”.length
* 数字转换



### 其他基本类型  Any  Any?  Unit  Nothing
* Any类型 是kotlin所有非空类型的超类型，相当于java里的Object
* Any不可以持有null
* 包含三个方法 toString()  equals()  hashCode()
* 不能使用其他的Object其他如wait notify

#### Unit类型 相当于java里的void
* 与void的区别是Unit是一个完备的类型，可以作为类型参数
* Unit 确实有值 只有一个实例 object

#### Nothing类型 这个函数永不返回




### 运算符重载
* 使用operator修饰plus函数
* 可重载的二元算术符
* A * B  times
* A / B  div
* A % B  mod
* A + B  plus
* A - B  minus














# 高阶函数专题


### 高阶函数
* 概念：以另一个函数作为参数或者返回值的函数
* 函数类型   
* (Int, String) -> Unit
* 参数类型->返回类型 Unit不能省略

* 函数作为参数
* 函数作为返回值
