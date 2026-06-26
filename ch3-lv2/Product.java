import java.time.LocalDateTime;

/**
 * 販売商品エンティティ。
 */
public class Product implements Priceable {

    private String        productId;
    private String        productName;
    private String        category;
    private int           unitPrice;
    private int           stock;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String productId, String productName, String category, int unitPrice, int stock) {
        this.productId   = productId;
        this.productName = productName;
        this.category    = category;
        this.unitPrice   = unitPrice;
        this.stock       = stock;
        this.active      = true;
        this.createdAt   = LocalDateTime.now();
        this.updatedAt   = LocalDateTime.now();
    }

    @Override public int getUnitPrice()  { return unitPrice; }
    @Override public int calcSubtotal()  { return unitPrice * stock; }

    public String  getProductId()   { return productId; }
    public String  getProductName() { return productName; }
    public String  getCategory()    { return category; }
    public int     getStock()       { return stock; }
    public boolean isActive()       { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
