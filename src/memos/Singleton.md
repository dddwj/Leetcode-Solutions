## 懒汉式 - 线程不安全
```java
public class Singleton_unsafe {

    private static Singleton uniqueInstance;

    private Singleton_unsafe() {
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
```

## 饿汉式 - 线程安全
```java
private static Singleton uniqueInstance = new Singleton();
```

## 懒汉式 - 线程安全
```java
public class Singleton_safe {

    private static Singleton uniqueInstance;

    private Singleton_safe() {
    }

    public static synchronized Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }   
}
```
