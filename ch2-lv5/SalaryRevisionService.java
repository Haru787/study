// package com.example.employee;

import java.util.List;

/**
 * 給与改定業務ロジックを集約するサービスクラス。
 */
public class SalaryRevisionService {

    private final EmployeeRepository repository;

    public SalaryRevisionService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * 全従業員に一律の昇給率を適用する。
     *
     * @param raiseRate 昇給率（例: 0.08 = 8%アップ）
     */
    // public void applyRaise(double raiseRate) {
    // for (Employee emp : repository.findAllEmployees()) {
    // int newSalary = (int)(emp.getAnnualSalary() * (1.0 + raiseRate));
    // emp.updateAnnualSalary(newSalary);
    // }
    // }
    public void applyRaise(double raiseRate) {
        for (Employee emp : repository.findAllEmployees()) {
            int newMonthlySalary = (int) (emp.calcMonthlySalary() * (1.0 + raiseRate));

            if (emp instanceof FullTimeEmployee) {
                FullTimeEmployee fullTime = (FullTimeEmployee) emp;
                emp.updateAnnualSalary((newMonthlySalary - fullTime.getAllowance()) * 12);
            } else {
                emp.updateAnnualSalary(newMonthlySalary * 12);
            }
        }
    }

    /**
     * 給与改定レポートを表示する（改定前と改定後を比較）。
     *
     * @param beforeSalaries 改定前の月額給与配列（従業員順）
     */
    public void printRevisionReport(int[] beforeSalaries) {
        List<Employee> employees = repository.findAllEmployees();
        System.out.println("===== 給与改定レポート =====");
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            int before = beforeSalaries[i];
            int after = emp.calcMonthlySalary();
            int diff = after - before;
            System.out.printf("[%s] %-10s  改定前：%,d円 → 改定後：%,d円  増減：%+,d円%n",
                    emp.getEmployeeId(), emp.getName(), before, after, diff);
        }
    }

    /**
     * 部署別給与合計を表示する。
     */
    public void printDeptSummary() {
        List<Department> departments = repository.findAllDepartments();
        String[] deptIds = new String[departments.size()];
        int[] deptTotals = new int[departments.size()];

        for (int i = 0; i < departments.size(); i++) {
            deptIds[i] = departments.get(i).getDeptId();
        }

        for (Employee emp : repository.findAllEmployees()) {
            for (int i = 0; i < deptIds.length; i++) {
                if (deptIds[i].equals(emp.getDeptId())) {
                    deptTotals[i] += emp.calcMonthlySalary();
                }
            }
        }

        System.out.println("===== 部署別給与集計 =====");
        for (int i = 0; i < deptTotals.length; i++) {// <=を < に修正
            Department dept = departments.get(i);
            System.out.printf("%-10s（%s）  月額合計：%,d円%n",
                    dept.getDeptName(), dept.getLocation(), deptTotals[i]);
        }
    }

    /**
     * 平均月額給与を返す。
     *
     * @return 平均月額給与
     */
    public double calcAverageMonthlySalary() {
        List<Employee> employees = repository.findAllEmployees();
        if (employees.isEmpty())
            return 0.0;
        int total = 0;
        for (Employee emp : employees) {
            total += emp.calcMonthlySalary();
        }
        return (double) total / employees.size();
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