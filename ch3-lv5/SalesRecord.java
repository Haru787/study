import java.time.LocalDateTime;

/**
 * 売上レコードエンティティ。
 */
public class SalesRecord {

    private String        salesId;
    private String        productId;
    private String        category;
    private String        salesRepName;
    private int           quantity;
    private int           unitPrice;
    private LocalDateTime soldAt;
    private LocalDateTime createdAt;

    public SalesRecord(String salesId, String productId, String category,
                       String salesRepName, int quantity, int unitPrice, LocalDateTime soldAt) {
        this.salesId      = salesId;
        this.productId    = productId;
        this.category     = category;
        this.salesRepName = salesRepName;
        this.quantity     = quantity;
        this.unitPrice    = unitPrice;
        this.soldAt       = soldAt;
        this.createdAt    = LocalDateTime.now();
    }

    public int    calcAmount()      { return quantity * unitPrice; }
    public String getSalesId()      { return salesId; }
    public String getProductId()    { return productId; }
    public String getCategory()     { return category; }
    public String getSalesRepName() { return salesRepName; }
    public int    getQuantity()     { return quantity; }
    public int    getUnitPrice()    { return unitPrice; }
    public LocalDateTime getSoldAt()    { return soldAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}