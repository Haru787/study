// package com.example.employee;

import java.util.ArrayList;
import java.util.List;

/**
 * 従業員データの保存・検索を担うリポジトリクラス。
 */
public class EmployeeRepository {

    private final List<Employee> store = new ArrayList<>();

    public void save(Employee emp) {
        store.add(emp);
    }

    public Employee findById(String employeeId) {
        for (Employee emp : store) {
            if (emp.getEmployeeId().equals(employeeId)) {
                return emp;
            }
        }
        return null;
    }

    public List<Employee> findAll() {
        return store;
    }

    public int count() {
        return store.size();
    }
}