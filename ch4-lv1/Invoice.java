package com.example.billing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 請求書エンティティ。
 */
public class Invoice {

    private String        invoiceId;
    private String        clientId;
    private LocalDate     issueDate;
    private LocalDate     dueDate;
    private String        status;
    private List<InvoiceDetail> details;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 請求書コンストラクタ。
     */
    public Invoice(String invoiceId, String clientId,
                   LocalDate issueDate, LocalDate dueDate) {
        this.invoiceId = invoiceId;
        this.clientId  = clientId;
        this.issueDate = issueDate;
        this.dueDate   = dueDate;
        this.status    = "DRAFT";
        this.details   = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /** 明細を追加する。 */
    public void addDetail(InvoiceDetail detail) {
        details.add(detail);
        this.updatedAt = LocalDateTime.now();
    }

    /** 合計金額（税別）を返す。 */
    public int calcTotal() {
        int total = 0;
        for (InvoiceDetail d : details) {
            total += d.calcSubtotal();
        }
        return total;
    }

    /** 税込合計金額を返す。消費税率 10%。 */
    public int calcTotalWithTax() {
        return (int) (calcTotal() * 1.1);
    }

    public String    getInvoiceId() { return invoiceId; }
    public String    getClientId()  { return clientId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate()   { return dueDate; }
    public String    getStatus()    { return status; }
    public List<InvoiceDetail> getDetails() { return new ArrayList<>(details); }
    public LocalDateTime getCreatedAt()     { return createdAt; }
    public LocalDateTime getUpdatedAt()     { return updatedAt; }

    public void setStatus(String status) {
        this.status    = status;
        this.updatedAt = LocalDateTime.now();
    }
}