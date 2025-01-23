package org.example;

import org.example.entity.Employee;

import java.util.*;

public class Main {
//    private static Map<Integer, Employee> employeeMap;
//    private static List<Employee> duplicatedEmployee;

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Ezgi", "Oztuna"));
        employees.add(new Employee(2, "Nevzatcem", "Oztuna"));
        employees.add(new Employee(3, "Mecitalp", "Kolçak"));
        employees.add(new Employee(4, "Ilgın", "Kolçak"));
        employees.add(new Employee(5, "Pelin", "Uygun Kolçak"));
        employees.add(new Employee(1, "Ezgi", "Oztuna"));
        employees.add(new Employee(4, "Ilgın", "Yılmaz"));
        employees.add(new Employee(4, "Ilgın", "Kolçak"));
    }

    public static List<Employee> findDuplicates(List<Employee> employees) {

        List<Employee> duplicates = new LinkedList<>();
        List<Employee> seen = new LinkedList<>();
        for (Employee employee : employees) {
            if (employee == null) {
                continue; // Eğer null ise işlemi atla
            }
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
            if (employee == null) { // Null kontrolü ekliyoruz
                continue;
            }
            freqMap.put(employee.getId(), freqMap.getOrDefault(employee.getId(), 0) + 1);
        }
        Map<Integer, Employee> uniques = new LinkedHashMap<>();
        for(Employee employee : employees) {
            if (employee == null) { // Null kontrolü burada da olmalı
                continue;
            }
            if (freqMap.get(employee.getId()) >= 1 && !uniques.containsKey(employee.getId())) {
                uniques.put(employee.getId(), employee);
            }
        }

        return uniques;
    }

    public static List<Employee> removeDuplicates(List<Employee> employees) {
        List<Employee> duplicates = findDuplicates(employees);
        Map<Integer, Employee> uniques = findUniques(employees);
        List<Employee> onlyUnique = new LinkedList<>(uniques.values());
        onlyUnique.removeAll(duplicates);
        return onlyUnique;
    }

}
