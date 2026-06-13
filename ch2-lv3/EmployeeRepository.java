// package com.example.employee;

import java.util.ArrayList;
import java.util.List;

/**
 * 従業員データの保存・検索を担うリポジトリクラス。
 */
public class EmployeeRepository {

    private final List<Employee> store        = new ArrayList<>();
    private final List<Department> departments = new ArrayList<>();

    /** 従業員を保存する。 */
    public void saveEmployee(Employee emp) {
        store.add(emp);
    }

    /** 部署を保存する。 */
    public void saveDepartment(Department dept) {
        departments.add(dept);
    }

    /**
     * 従業員IDで従業員を検索する。
     *
     * @param employeeId 検索する従業員ID
     * @return 見つかった従業員、または null
     */
    public Employee findEmployeeById(String employeeId) {
        for (Employee emp : store) {
            if (emp.getEmployeeId().equals(employeeId)) {
                return emp;
            }
        }
        return null;
    }

    /**
     * 部署IDで部署を検索する。
     *
     * @param deptId 検索する部署ID
     * @return 見つかった部署、または null
     */
    public Department findDepartmentById(String deptId) {
        for (Department dept : departments) {
            if (dept.getDeptId().equals(deptId)) {
                return dept;
            }
        }
        return null;
    }

    /** 全従業員を返す。 */
    public List<Employee> findAllEmployees() {
        return store;
    }

    /** 従業員数を返す。 */
    public int countEmployees() {
        return store.size();
    }
}