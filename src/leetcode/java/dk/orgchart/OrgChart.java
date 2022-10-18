package leetcode.java.dk.orgchart;
import java.io.File;
import java.io.IOException;
import java.util.*;

class Employee {
    public String employeeId;
    public String employeeName;
    public String managerId;
//    private List<Employee> reports;

    Employee(String employeeId, String employeeName, String managerId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.managerId = managerId;
//        this.reports = reports;
    }

}

public class OrgChart {
    private Map<String, List<Employee>> report_map;
    private Map<String, Employee> employees;

    public OrgChart() {
        this.report_map = new HashMap<>();
        this.employees = new HashMap<>();
        this.report_map.put("-1", new LinkedList<>());
    }

    public void add(String id, String name, String managerId) {
        if (this.employees.containsKey(id)) {
            return;
        }
        if (!this.employees.containsKey(managerId)) {
            managerId = "-1";
        }
        Employee employee = new Employee(id, name, managerId);
        this.employees.put(id, employee);
        List<Employee> reports = this.report_map.get(managerId);
        reports.add(employee);
        this.report_map.put(id, new LinkedList<>());
        return;
    }

    public void print() {
        List<Employee> reports = this.report_map.get("-1");
        for (Employee report : reports) {
            this.printEmployee(report, 0);
        }
    }

    private void printEmployee(Employee employee, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        sb.append(employee.employeeName).append(" [").append(employee.employeeId).append("]");
        System.out.println(sb.toString());

        List<Employee> reports = this.report_map.get(employee.employeeId);
        for (Employee report : reports) {
            this.printEmployee(report, level + 1);
        }
        return;
    }

    public void remove(String employeeId) {
        // Remove from manager's reports list
        Employee employee = this.employees.get(employeeId);
        List<Employee> manager_reports = this.report_map.get(employee.managerId);
        manager_reports.remove(employee);

        // Set reports' manager to employee's manager
        List<Employee> reports = this.report_map.get(employeeId);
        for (Employee report : reports) {
            report.managerId = employee.managerId;
            manager_reports.add(report);
        }

        this.employees.remove(employeeId);
        this.report_map.remove(employeeId);
    }

    public void move(String employeeId, String newManagerId) {
        if (!this.employees.containsKey(newManagerId) || !this.employees.containsKey(employeeId)) {
            return;
        }
        // Remove from manager's reports list
        Employee employee = this.employees.get(employeeId);
        List<Employee> manager_reports = this.report_map.get(employee.managerId);
        manager_reports.remove(employee);

        // Update manager
        employee.managerId = newManagerId;
        List<Employee> reports = this.report_map.get(newManagerId);
        reports.add(employee);
    }

    public int count(String employeeId) {
        int res = 0;
        for (Employee report : this.report_map.get(employeeId)) {
            res += (1 + this.count(report.employeeId));
        }
        return res;
    }

    public static void main(String[] args) {
//        test0();
        OrgChart oc = new OrgChart();
        try {
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input000.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input001.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input002.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input003.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input004.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input005.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input006.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input007.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input008.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input009.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input010.txt");
//            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input011.txt");
            File file = new File("/Users/dddwj/Downloads/dk-oa/dk_testcase/input012.txt");
            Scanner reader = new Scanner(file);
            String count = reader.nextLine();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] ops = data.split(",");
                System.out.println(Arrays.toString(ops));
                String op = ops[0];
                switch (op) {
                    case "add": {
                        oc.add(ops[1], ops[2], ops[3]);
                        break;
                    }
                    case "remove": {
                        oc.remove(ops[1]);
                        break;
                    }
                    case "print": {
                        oc.print();
                        break;
                    }
                    case "move": {
                        oc.move(ops[1], ops[2]);
                        break;
                    }
                    case "count": {
                        System.out.println(oc.count(ops[1]));
                    }
                    default: {
                        System.out.println(op);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void test0() {
        OrgChart oc = new OrgChart();
        oc.add("1", "Sharilyn Gruber", "-1");
        oc.add("2", "Denice Mattice", "-1");
        oc.add("3", "Lawana Futrell", "-1");
        oc.add("4", "Lissette Gorney", "-1");
        oc.add("5", "Lan Puls", "-1");
        oc.print();
    }
}
