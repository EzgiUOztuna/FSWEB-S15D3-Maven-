package org.example;

import org.example.entity.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Ezgi", "Oztuna"));
        employees.add(new Employee(2, "Nevzatcem", "Oztuna"));
        employees.add(new Employee(3, "Mecitalp", "Kaya"));
        employees.add(new Employee(4, "Ilgın", "Yılmaz"));
        employees.add(new Employee(5, "Pelin", "Kara"));
        employees.add(new Employee(1, "Ezgi", "Oztuna")); // Duplicate
        employees.add(new Employee(4, "Ilgın", "Yılmaz")); // Duplicate

        System.out.println("Employee List: " + employees);

        // findDuplicates metodu çağrılıyor
        System.out.println("Duplicates: " + findDuplicates(employees));

        // findUniques metodu çağrılıyor
        Map<Integer, Employee> uniques = findUniques(employees);
        System.out.println("Uniques: " + uniques);

        // removeDuplicates metodu çağrılıyor
        List<Employee> withoutDuplicates = removeDuplicates(employees);
        System.out.println("Without Duplicates: " + withoutDuplicates);
    }

    public static List<Employee> findDuplicates(List<Employee> employees) {
        List<Employee> duplicates = new LinkedList<>();
        List<Employee> seen = new LinkedList<>();
        for (Employee employee : employees) {
            if (!seen.contains(employee)) { // Eğer daha önce görülmemişse
                seen.add(employee); // Görülenler listesine ekle
            } else if (!duplicates.contains(employee)) { // Tekrar edenler listesine ekle
                duplicates.add(employee);
            }
        }
        return duplicates;
    }

    public static Map<Integer, Employee> findUniques(List<Employee> employees) {
        Map<Integer, Integer> freqMap = new LinkedHashMap<>();
        for (Employee employee : employees) {
            freqMap.put(employee.getId(), freqMap.getOrDefault(employee.getId(), 0) + 1);
        }
        Map<Integer, Employee> uniques = new LinkedHashMap<>();
        for(Employee employee : employees) {
            if (freqMap.get(employee.getId()) >= 1 && !uniques.containsKey(employee.getId())) {
                uniques.put(employee.getId(), employee);
            }
        }

        return uniques;
    }

    public static List<Employee> removeDuplicates(List<Employee> employees) {
        List<Employee> remSeen = new LinkedList<>();
        List<Employee> remDuplicates = new LinkedList<>();

        for (Employee employee : employees) {
            if (!remSeen.contains(employee)) {
                remSeen.add(employee); // Daha önce görmediğimiz bir çalışan
            } else if (!remDuplicates.contains(employee)) {
                remDuplicates.add(employee); // Daha önce görülen bir çalışan, yalnızca bir kez eklenmeli
            }
        }
        return remDuplicates;
    }

}
