package leetcode.java.LC1452;

import java.util.*;

public class Main_1452 {

    public static void main(String[] args) {
        Main_1452 main_1452 = new Main_1452();
        System.out.println(main_1452.peopleIndexes_mysolution_opt1(new ArrayList<List<String>>(){{
                add(new ArrayList<>(Arrays.asList("leetcode","google","facebook")));
                add(new ArrayList<>(Arrays.asList("leetcode","amazon")));
                add(new ArrayList<>(Arrays.asList("google","facebook")));
            }}));
        System.out.println(main_1452.peopleIndexes_mysolution_opt1(new ArrayList<List<String>>(){{
                add(new ArrayList<>(Arrays.asList("google")));
                add(new ArrayList<>(Arrays.asList("leetcode")));
                add(new ArrayList<>(Arrays.asList("facebook")));
            }}));
    }



    // My Solution: Brute Force
    // Time: O(N*N*M), N = count of people, M = avg(count of company)
    // Space: O(1)
    public List<Integer> peopleIndexes_mysolution_opt1(List<List<String>> favoriteCompanies) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        for (List<String> person : favoriteCompanies) {
            if (!isSubset(person, favoriteCompanies))
                res.add(i);
            i++;
        }
        return res;
    }

    private boolean isSubset(List<String> person, List<List<String>> allPerson) {
        for (List<String> target : allPerson) {
            if (target == person || target.size() <= person.size())
                continue;
            if (allContains(person, target))
                return true;
        }
        return false;
    }

    private boolean allContains(List<String> person, List<String> target) {
        // opt2: contains all
        // return target.containsAll(person);

        // opt1: traverse list
        for (String company : person) {
            if (!target.contains(company))
                return false;
        }
        return true;
    }



    // Solution: Brute Force (Concise version)
    // Ref: https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/discuss/636344/Java-Use-HashMap-to-group-and-HashSet-to-intersect.
    public List<Integer> peopleIndexes_solution2(List<List<String>> favoriteCompanies) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Set<String>> map = new HashMap<>();
        for (int i = 0; i < favoriteCompanies.size(); ++i)
            map.put(i, new HashSet<>(favoriteCompanies.get(i)));

        outer:
        for (int i = 0; i < favoriteCompanies.size(); ++i) {
            for (int j = 0; j < favoriteCompanies.size(); ++j)
                if (i != j && map.get(j).containsAll(map.get(i)))
                    continue outer;
            res.add(i);
        }

        return res;
    }
}
