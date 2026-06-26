// package com.example.employee;

import java.util.ArrayList;
import java.util.List;

/**
 * 従業員データの保存・検索を担うリポジトリクラス。
 */
public class EmployeeRepository {

    private final List<Employee>   store       = new ArrayList<>();
    private final List<Department> departments = new ArrayList<>();

    public void saveEmployee(Employee emp)     { store.add(emp); }
    public void saveDepartment(Department d)   { departments.add(d); }

    public Employee findById(String employeeId) {
        for (Employee emp : store) {
            if (emp.getEmployeeId().equals(employeeId)) return emp;
        }
        return null;
    }

    public Department findDeptById(String deptId) {
        for (Department dept : departments) {
            if (dept.getDeptId().equals(deptId)) return dept;
        }
        return null;
    }

    public List<Employee>   findAllEmployees()   { return store; }
    public List<Department> findAllDepartments() { return departments; }
    public int countEmployees()                  { return store.size(); }
}
