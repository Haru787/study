package com.example.billing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 取引先の JDBC リポジトリクラス。
 */
public class ClientRepository {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(ClientRepository.class);

    private final javax.sql.DataSource dataSource;

    public ClientRepository(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 取引先を INSERT する。
     * @param client 登録する取引先
     */
    public void insert(Client client) {
        String sql = "INSERT INTO clients (client_id, client_name, address, contact_email, phone_number) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, client.getClientId());
            ps.setString(2, client.getClientName());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getContactEmail());
            ps.setString(5, client.getPhoneNumber());
            ps.executeUpdate();
            log.info("INSERT client: {}", client.getClientId());
        } catch (SQLException e) {
            throw new RuntimeException("INSERT 失敗", e);
        }
    }

    /**
     * 取引先 ID で検索する。
     * @param clientId 検索する取引先 ID
     * @return 見つかった Client、存在しない場合は null
     */
    public Client findById(String clientId) {
        String sql = "SELECT client_id, client_name, address, contact_email, phone_number "
                   + "FROM clients WHERE client_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                        rs.getString("client_id"),
                        rs.getString("client_name"),
                        rs.getString("address"),
                        rs.getString("contact_email"),
                        rs.getString("phone_number")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("SELECT 失敗", e);
        }
    }
}