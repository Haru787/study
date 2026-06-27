package com.example.billing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 請求書の永続化を担う JDBC リポジトリクラス。
 * ※ このクラスにコンパイルエラーが 1 箇所あります。
 */
public class InvoiceRepository {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(InvoiceRepository.class);

    private final javax.sql.DataSource dataSource;

    /**
     * コンストラクタ。
     * @param dataSource データソース
     */
    public InvoiceRepository(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 請求書を DB に INSERT する。
     * @param invoice 登録する請求書
     * @throws DatabaseException INSERT 失敗時
     */
    public void insert(Invoice invoice) {
        String sql = "INSERT INTO invoices (invoice_id, client_id, issue_date, due_date, status) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, invoice.getInvoiceId());
            ps.setString(2, invoice.getClientId());
            ps.setDate(3, java.sql.Date.valueOf(invoice.getIssueDate()));
            ps.setDate(4, java.sql.Date.valueOf(invoice.getDueDate()));
            ps.setString(5, invoice.getStatus());
            ps.executeUpdate();

            log.info("請求書INSERT完了: invoiceId={}", invoice.getInvoiceId());

        } catch (SQLException e) {
            throw new DatabaseException("INSERT invoice id=" + invoice.getInvoiceId(), e);
        }
    }

    /**
     * 請求書 ID で請求書を取得する。
     * @param invoiceId 検索する請求書 ID
     * @return 見つかった Invoice、存在しない場合は null
     * @throws DatabaseException SELECT 失敗時
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
            throw new DatabaseException("SELECT invoice id=" + invoiceId, e);
        }
    }

    /**
     * 指定した取引先の請求書一覧を取得する。
     * @param clientId 取引先 ID
     * @return 請求書のリスト
     * @throws DatabaseException SELECT 失敗時
     */
    public List<Invoice> findByClientId(String clientId) {
        String sql = "SELECT invoice_id, client_id, issue_date, due_date, status "
                   + "FROM invoices WHERE client_id = ? ORDER BY issue_date DESC";
        List<Invoice> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, clientId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice inv = new Invoice(
                        rs.getString("invoice_id"),
                        rs.getString("client_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate()
                    );
                    inv.setStatus(rs.getString("status"));
                    result.add(inv);
                }
            }
            return result;

        } catch (SQLException e) {
            throw new DatabaseException("SELECT invoices clientId=" + clientId, e);
        }
    }

    /**
     * 請求書ステータスを UPDATE する。
     * @param invoiceId 更新する請求書 ID
     * @param newStatus 新しいステータス
     * @throws DatabaseException UPDATE 失敗時
     */
    public void updateStatus(String invoiceId, String newStatus) {
        String sql = "UPDATE invoices SET status = ?, updated_at = NOW() WHERE invoice_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setString(2, invoiceId);
            int updated = ps.executeUpdate();
            log.info("ステータス更新: invoiceId={}, status={}, rows={}", invoiceId, newStatus, updated);

        } catch (SQLException e) {
            throw new DatabaseException("UPDATE invoice status id=" + invoiceId, e);
        }
    }

    /**
     * 全請求書件数を返す。
     * ★ コンパイルエラー1: 戻り値の型が間違っている（String ではなく int が正しい）
     * @return 登録件数
     */
    public int countAll() {//戻り値の型を String → int に変更
        String sql = "SELECT COUNT(*) FROM invoices";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new DatabaseException("SELECT COUNT invoices", e);
        }
    }
}