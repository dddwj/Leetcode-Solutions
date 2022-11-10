package leetcode.java.dk.discount_validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class Solution {

    private Map<Customer, List<Discount>> customerDiscount = new HashMap<>();
    private Map<Customer, Float> customerDiscountValues = new HashMap<>();
    private Map<Integer, Discount> discountMap = new HashMap<>();
    private Map<Integer, Customer> customerMap = new HashMap<>();

    private Set<Discount> usedDiscount = new HashSet<>();

    public static boolean validateDiscounts(
            List<DiscountAssignment> assignedDiscounts,
            List<Customer> customers,
            List<Discount> discounts
    ) {

        Solution s = new Solution();
        s.init(assignedDiscounts, customers, discounts);

        return s.validateFirst(assignedDiscounts, customers, discounts) &&
                s.validateSecond(assignedDiscounts, customers, discounts) &&
                s.validateThird(assignedDiscounts, customers, discounts) &&
                s.validateFourth(assignedDiscounts, customers, discounts);
    }

    public void init(
            List<DiscountAssignment> assignedDiscounts,
            List<Customer> customers,
            List<Discount> discounts
    ) {
        for (Discount d : discounts) {
            discountMap.put(d.getDiscountId(), d);
        }
        for (Customer c : customers) {
            customerMap.put(c.getCustomerId(), c);
        }
    }

    public boolean validateFirst(
            List<DiscountAssignment> assignedDiscounts,
            List<Customer> customers,
            List<Discount> discounts
    ) {
        for (DiscountAssignment da : assignedDiscounts) {
            int customerID = da.getCustomerId(), discountID = da.getDiscountId();
            Customer c = customerMap.get(customerID);
            Discount d = discountMap.get(discountID);
            if (!customerDiscount.containsKey(c)) {
                customerDiscount.put(c, new LinkedList<>());
            }
            customerDiscount.get(c).add(d);
        }

//        for (Map.Entry<Customer, List<Discount>> record : customerDiscount.entrySet()) {
        for (List<Discount> list : customerDiscount.values()) {
//            List<Discount> list = record.getValue();
            if (list.size() >= 3) {
                return false;
            }
        }
        return true;
    }

    public boolean validateSecond(
            List<DiscountAssignment> assignedDiscounts,
            List<Customer> customers,
            List<Discount> discounts
    ) {
        for (List<Discount> list : customerDiscount.values()) {
            for (Discount d : list) {
                usedDiscount.add(d);
            }
        }
        for (Discount x : discounts) {
            if (!usedDiscount.contains(x)) {
                return false;
            }
        }
        return true;
//        return usedDiscount.containsAll(discounts);
    }

    public boolean validateThird(
            List<DiscountAssignment> assignedDiscounts,
            List<Customer> customers,
            List<Discount> discounts
    ) {
        for (Map.Entry<Customer, List<Discount>> entry : customerDiscount.entrySet()) {
            Customer c = entry.getKey();
            float totalDiscount = 0;
            List<Discount> list = entry.getValue();
//            float sum = list.stream().mapToDouble(Discount::getDollarValue).sum();
            for (Discount discount : list) {
                totalDiscount += discount.getDollarValue();
            }

            customerDiscountValues.put(c, totalDiscount);

            if (totalDiscount > c.getYearlySpend() * 0.2) {
                return false;
            }
        }
        return true;
    }

    public boolean validateFourth(
            List<DiscountAssignment> assignedDiscounts,
            List<Customer> customers,
            List<Discount> discounts
    ) {

         Collections.sort(customers, new Comparator<Customer>(){
             @Override
             public int compare(Customer c1, Customer c2) {
                 float v1 = customerDiscountValues.get(c1);
                 float v2 = customerDiscountValues.get(c2);
//                 if (v1 > v2)
//                     return 1;
//                 else if (v1 == v2)
//                     return 0;
//                 else
//                     return -1;
                 return Float.compare(v1, v2);
             }
         });

         for (int i = 1; i < customers.size(); i++) {
             Customer cPrev = customers.get(i-1);
             Customer c = customers.get(i);
             if (cPrev.getYearlySpend() <= c.getYearlySpend())
                 continue;
             else
                 return false;
         }

         return true;
    }

    public static void main(String[] args) {
        List<DiscountAssignment> assignedDiscounts = new ArrayList<>();
        List<Customer> customers = new LinkedList<>();
        List<Discount> discounts = new LinkedList<>();
        System.out.println(
                validateDiscounts(assignedDiscounts, customers, discounts)
        );

//        CustomerWithDiscount cd = new CustomerWithDiscount();
//        cd.discounts;
    }


}

class SolutionTest {
    @Test
    public void testFunction() {
        Solution s = new Solution();
        Assertions.assertTrue(s.validateFirst(null, null, null));
    }
}




class CustomerWithDiscount extends Customer {
    public CustomerWithDiscount(int customerId, float yearlySpend, List<Discount> discounts) {
        super(customerId, yearlySpend, discounts);
    }
    public List<Discount> discounts;
}

class Customer {
    private int customerId;
    private float yearlySpend;
    private List<Discount> discounts;

    public Customer(int customerId, float yearlySpend, List<Discount> discounts) {
        this.customerId = customerId;
        this.yearlySpend = yearlySpend;
        this.discounts = discounts;
    }

    public int getCustomerId() {
        return customerId;
    }

    public float getYearlySpend() {
        return yearlySpend;
    }

    public List<Discount> getDiscounts() {
        return this.discounts;
    }
}

class Discount {
    private int discountId;
    private int productId;
    private float dollarValue;

    public Discount(int discountId, int productId, float value) {
        this.discountId = discountId;
        this.productId = productId;
        this.dollarValue = value;
    }

    public int getDiscountId() {
        return discountId;
    }

    public int getProductId() {
        return productId;
    }

    public float getDollarValue() {
        return dollarValue;
    }
}

class DiscountAssignment {
    private int discountId;
    private int customerId;

    public DiscountAssignment(int discountId, int customerId) {
        this.discountId = discountId;
        this.customerId = customerId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public int getCustomerId() {
        return customerId;
    }
}