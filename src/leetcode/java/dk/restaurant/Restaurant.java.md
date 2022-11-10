```java
package leetcode.java.dk.restaurant;

import java.io.File;
import java.util.*;

public class Restaurant {

    private Set<OrderRecord> orders;
    private Restaurant() {
        orders = new HashSet<>();

    }

    public static void main(String[] args) {

    }

}

//interface Staff {
//    public void startWork();
//}

class OrderStorage {
    private static OrderStorage orderStorage = new OrderStorage();
    public List<OrderRecord> orders = new LinkedList<>();
    private OrderStorage() {}
    public static OrderStorage getInstance() {
        return orderStorage;
    }
}

// polling vs subscribe vs broadcast
class Kitchen {        // Singleton
    private static Kitchen kitchen;
    public Queue<OrderRecord> orderQueue;
    public Map<Waiter, Queue<OrderRecord>> serveQueue;
    private Kitchen() {}
    public static Kitchen getInstance() {
        if (kitchen == null) {
            kitchen = new Kitchen();
        }
        return kitchen;
    }

    public EventManager eventManager;

}

class Staff {
    private int workSince;
    private int salary;
    private int staffId;
}

class Dish {
    private String dishName;
    private int price;
}

class Table { }

class Customer { // Actually CustomerParty
    private int customerId;
    private int partySize;
}
class OrderRecord {
    private int startTime;
    private int endTime;

    Set<Dish> dishes;
    Customer customer;
    Waiter waiter;
    Table table;
    boolean cooked = false;
    boolean fullfilled = false;
    boolean paid = false;

    OrderRecord(Set<Dish> dishes, Customer customer, Waiter waiter, Table table) {
        this.customer = customer;
        this.dishes = dishes;
        this.waiter = waiter;
        this.table = table;
    }
}

class EventManager {  // observer mode pattern
    // Ref: https://refactoringguru.cn/design-patterns/observer/java/example
    Map<String, List<Waiter>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        this.listeners.put("working", new ArrayList<>());
//        for (String operation : operations) {
//            this.listeners.put(operation, new ArrayList<>());
//        }
    }

    public void subscribe(String eventType, Waiter waiter) {
        List<Waiter> users = listeners.get("working");
        users.add(waiter);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(Waiter waiter, OrderRecord orderRecord) {
//        List<Waiter> waiters = listeners.get("working");
//        for (Waiter waiter : waiters) {
            waiter.passOrderToTable(orderRecord);
//        }
    }
}

class Waiter extends Staff {  // inheritance  // Service

    public OrderRecord takeOrder(Customer customer, Set<Dish> dishes, Table table) {
        OrderRecord order = new OrderRecord(dishes, customer, this, table);
        return order;
    }

    public boolean passOrderToKitchen(OrderRecord orderRecord) throws Exception {
        Kitchen k = Kitchen.getInstance();
        k.orderQueue.offer(orderRecord);
        return true;
    }

    public boolean passOrderToTable(OrderRecord orderRecord) {
        Kitchen k = Kitchen.getInstance();
        Queue<OrderRecord> orders = k.serveQueue.get(this);
        orders.poll().fullfilled = true;
        return true;
    }

    public boolean checkOrderReady(OrderRecord orderRecord) {

    }

    public void getUpdate(String eventType, File file) {
        System.out.println("Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }

}

//class WaiterService {
//
//}

class Chef extends Staff {
    private int occupied;
    private int lastAvailableTime;
    Kitchen kitchen = Kitchen.getInstance();  // Kitchen Service

    public void cook(Queue<OrderRecord> orders) {
    }
    public void cook(OrderRecord order) {
        kitchen.eventManager.notify(order.waiter, order);
    }
    public void cook(Dish dish) {}
}

class Cashier extends Staff {
    public void checkout(OrderRecord orderRecord) {
        OrderStorage os = OrderStorage.getInstance();
        os.orders.add(orderRecord);
    }
}
```