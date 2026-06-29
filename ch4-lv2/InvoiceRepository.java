package com.example.billing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 請求書の JDBC リポジトリクラス。
 */
public class InvoiceRepository {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(InvoiceRepository.class);

    private final javax.sql.DataSource dataSource;

    public InvoiceRepository(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 請求書を INSERT する。
     * @param invoice 登録する請求書
     */
    public void insert(Invoice invoice) {
        String sql = "INSERT INTO invoices (invoice_id, client_id, issue_date, due_date, status) "
                   + "VALUES (?, ?, ?, ?, ?)";
                   
        // データソースが null の場合は何もしない
        if (dataSource == null) {
            return;
        }
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, invoice.getInvoiceId());
            ps.setString(2, invoice.getClientId());
            ps.setDate(3, java.sql.Date.valueOf(invoice.getIssueDate()));
            ps.setDate(4, java.sql.Date.valueOf(invoice.getDueDate()));
            ps.setString(5, invoice.getStatus());
            ps.executeUpdate();
            log.info("INSERT invoice: {}", invoice.getInvoiceId());
        } catch (SQLException e) {
            throw new RuntimeException("INSERT 失敗: " + invoice.getInvoiceId(), e);
        }
    }

    /**
     * 請求書 ID で検索する。
     * @param invoiceId 検索する請求書 ID
     * @return 見つかった Invoice、存在しない場合は null
     */
    public Invoice findById(String invoiceId) {
        String sql = "SELECT invoice_id, client_id, issue_date, due_date, status "
                   + "FROM invoices WHERE invoice_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, invoiceId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Invoice inv = new Invoice(
                        rs.getString("invoice_id"),
                        rs.getString("client_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate()
                    );
                    inv.setStatus(rs.getString("status"));
                    return inv;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("SELECT 失敗: " + invoiceId, e);
        }
    }

    /**
     * ステータスを UPDATE する。
     * @param invoiceId 対象の請求書 ID
     * @param status    新しいステータス
     */
    public void updateStatus(String invoiceId, String status) {
        String sql = "UPDATE invoices SET status = ?, updated_at = NOW() WHERE invoice_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, invoiceId);
            ps.executeUpdate();
            log.info("UPDATE status: invoiceId={}, status={}", invoiceId, status);
        } catch (SQLException e) {
            throw new RuntimeException("UPDATE 失敗: " + invoiceId, e);
        }
    }
}