// package com.example.employee;

import java.util.List;

/**
 * 従業員業務ロジックを集約するサービスクラス。
 */
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * 従業員を登録する。
     *
     * @param emp 登録する従業員
     */
    public void register(Employee emp) {
        repository.saveEmployee(emp);
    }

    /**
     * 全従業員の給与明細を出力する。
     * 部署情報が紐付いている場合は部署名・勤務地も表示する。
     */
    public void printPaySlips() {
        List<Employee> employees = repository.findAllEmployees();
        System.out.println("===== 給与明細一覧 =====");
        for (Employee emp : employees) {
            Department dept = repository.findDepartmentById(emp.getDeptId());
            System.out.printf("[%s] %-12s  %s（%s）  月給：%,d円%n",
                    emp.getEmployeeId(),
                    emp.getName(),
                    dept.getDeptName(),
                    dept.getLocation(),
                    emp.calcMonthlySalary()
            );
        }
    }

    /**
     * 全従業員の給与合計を返す。
     *
     * @return 月額給与合計
     */
    public int calcTotalMonthlySalary() {
        int total = 0;
        for (Employee emp : repository.findAllEmployees()) {
            total += emp.calcMonthlySalary();
        }
        return total;
    }

    /**
     * 指定部署の従業員一覧を返す。
     *
     * @param deptId 部署ID
     * @return 該当従業員リスト
     */
    public List<Employee> findByDept(String deptId) {
        List<Employee> result = new java.util.ArrayList<>();
        for (Employee emp : repository.findAllEmployees()) {
            if (deptId.equals(emp.getDeptId())) {
                result.add(emp);
            }
        }
        return result;
    }

    /**
     * 従業員数を返す。
     *
     * @return 従業員数
     */
    public int getEmployeeCount() {
        return repository.countEmployees();
    }
}