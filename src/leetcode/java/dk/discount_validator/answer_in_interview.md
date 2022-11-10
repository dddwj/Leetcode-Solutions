import java.io.*;

import java.util.*;

public class DiscountValidator {

    /// <summary>
    /// Determines whether or not a list of assignment discounts are valid
    /// </summary>
    /// <param name="assignedDiscounts">collection of DiscountAssignments that contain a mapping between DiscountId and CustomerId</param>
    /// <param name="customers">A collection of customers of the super store</param>
    /// <param name="discounts">A collection of discounts that are available to offer to customers of the super store</param>
    /// <returns><c>Boolean</c> Determines whether or not it is a valid assignment of discounts to customers</returns>
    /// <remarks>
    
    // assignedDiscounts => (customerID, discountID)   customerID -> List<Customer> // O(lgN) , O(N)
    
    // Map<Integer (discountID), Discount>   // O(1)
    // Map<Integer (customerID), Customer>
    
    // customerDiscounts:  Map<Customer, List<Discounts>> 
    // {
    //     'customer': [Discount1 ,D2, D3, ... ]
    // }
    // customerDiscounts:
    //     customer size(list) >= 3 return false
    
    public Map<Integer, Discount> discountMap;
    public Map<Integer, Customer> customerMap;
    public Map<Customer, List<Discounts>> customerDiscounts;
    public Set<Discount> usedDiscount;
    public Map<Customer, Float> customerDiscountValues;
    
    
    public void preprocessing(List<DiscountAssignment> assignedDiscounts, List<Customer> customers, List<Discount> discounts) { // O(C) + O(D) + O(A) => O(N)
        // Map<Integer (discountID), Discount>
        discountMap = new HashMap<>();
        for (Discount d : discounts) {
            discountMap.put(d.getDiscountId(), d);
        }
        
        // Map<Integer (customerID), Customer>
        customerMap = new HashMap<>();
        for (Customer c : customers) {
            customerMap.put(c.getCustomerId(), c);
        }
        
        // customerDiscounts:  Map<Customer, List<Discounts>> 
        customerDiscounts = new HashMap<>();
        for (DiscountAssignment da : assignedDiscounts) {
            int customerID = da.getCustomerId();
            int discountID = da.getDiscountId();
            // if map.containKey
            Customer c = customerMap.get(customerID);  // always exist
            Discount d = discountMap.get(discountID);
            if (!customerDiscounts.containsKey(c)) {
                customerDiscounts.put(c, new LinkedList()); // ArrayList()
            }
            customerDiscounts.get(c).add(d);
            usedDiscount.add(d);
        }
    }
    
    public boolean validateRule1() { // O(C) => O(N)
        for (List<Discount> list : customerDiscounts.values()) {
            if (list.size() > 3) {
                return false;
            }
        }
        return true;
    }
    
    public boolean validateRule2(List<Discount> discounts) {
        for (Discount d : discounts) { // O(N*N) -> O(N)
            // d --> in  discountassignment // O(1)
            if (!usedDiscount.contains(d)) { // O(1)
                return false;
            }
        }
        return true;
        // return usedDiscount.containsAll(discounts);
    }
    
    // c -> CustomerDiscounts customerDiscounts
    // ys = c.yearlySpend
    // tdv = sum([d.discountValue, d.discountValue2])
    // tdv > 0.2 * ys : return false
    
    public boolean validateRule3() { // O(D) => O(N)
        // java.util.Map.Entry
        for (Map.Entry<Customer, List<Discount>> entry : customerDiscounts.entrys()) {
            Customer c = entry.getKey();
            List<Discount> list = entry.getValue();
            float yearlySpend = c.getYearlySpend();
            float total = 0;
            for (Discount d : list) {
                total += d.getDollarValue();
            }
            customerDiscountValues.put(c, total);
            if (total > 0.2 * yearlySpend) {
                return false;
            }
        }
        return true;
    }
    
    // Sort(Customers) key = discountValue  
    // [c1.yearlySpend, c2.yearlySpend]                    yearlySpend
    // c1 > c2: return false
    
    
    public boolean validateRule4() { // O(NlgN) in time / space + O(N) ==> O(NlgN)
        Collections.sort(customer, new Comparator<Customer> comparator(){
            @Override
            public int compare(Customer c1, Customer c2) { // O(1)
                return customerDiscountValues.get(c1) - customerDiscountValues.get(c2);
            }
        });
        for (int i = 1; i < customer.size(); i++) { // O(N)
            Customer cPrev = customer.get(i - 1);
            Customer c = customer.get(i);
            if (c.yearlySpend < cPrev.yearlySpend) {
                return false;
            }
        }
        return true;
    }
    
    
    
    
    public static boolean validateDiscounts(List<DiscountAssignment> assignedDiscounts, List<Customer> customers, List<Discount> discounts) { // O(NlgN)
          // solution goes here
          DiscountValidator solution = new DiscountValidator();
          solution.preprocessing(assignedDiscounts, customers, discounts);
          
        return solution.validateRule1() && solution.validateRule2(discounts) && solution.validateRule3() && solution.validateRule4();
      }
    
    /* Do not modify the main method, this is just IO setup for running test cases */
     public static void main(String[] args)
     {
        Scanner scanner = new Scanner(System.in);
        boolean isCustomer = false;
        boolean isDiscount = false;
        
        List<DiscountAssignment> assignedDiscounts = new ArrayList<DiscountAssignment>();
        List<Customer> customers = new ArrayList<Customer>();
        List<Discount> discounts = new ArrayList<Discount>();
        while (scanner.hasNextLine()) {
            String arg = scanner.nextLine();
            if (arg.equals("discountAssignment")) {
                continue;
            }
            
            if (arg.equals("customer")) {
                isCustomer = true;
                continue;
            }
            
            if (arg.equals("discount")) {
                isCustomer = false;
                isDiscount = true;
                continue;
            }
            
            if (!isCustomer && !isDiscount) {
                int[] values = splitStr(arg);
                assignedDiscounts.add(new DiscountAssignment(values[0], values[1]));
            }
            else if (isCustomer) {
                int[] values = splitStr(arg);
                customers.add(new Customer(values[0], values[1]));
            }
            else if (isDiscount) {
                int[] values = splitStr(arg);
                discounts.add(new Discount(values[0], 0, (float)values[1]));
            }
        }
        
        System.out.println(validateDiscounts(assignedDiscounts, customers, discounts));
    }
    
    private static int[] splitStr(String arg) {
        String[] splitString = arg.split(",");
        int[] nums = new int[2];
        for (int i = 0; i < 2; i++) {
            nums[i] = Integer.parseInt(splitString[i].trim());
        }
        
        return nums;
     }
}

class Customer {
private int customerId;
private float yearlySpend;

    public Customer(int customerId, float yearlySpend) {
        this.customerId = customerId;
        this.yearlySpend = yearlySpend;
    }

    public int getCustomerId() {
        return customerId;
    }
    
    public float getYearlySpend() {
        return yearlySpend;
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