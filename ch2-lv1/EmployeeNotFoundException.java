// package com.example.employee;

/**
 * 従業員が見つからない場合にスローされるカスタム例外クラス。
 */
public class EmployeeNotFoundException extends RuntimeException {

    private final String employeeId;

    /**
     * @param employeeId 見つからなかった従業員ID
     * @param message    エラーメッセージ
     */
    public EmployeeNotFoundException(String employeeId, String message) {
        super("[従業員ID: " + employeeId + "] " + message);
        this.employeeId = employeeId;
    }

    /**
     * @return 見つからなかった従業員ID
     */
    public String getEmployeeId() {
        return employeeId;
    }
}
