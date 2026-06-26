/**
 * 売上レコードが見つからない場合にスローされる例外。
 */
public class SalesNotFoundException extends RuntimeException {

    private final String salesId;

    public SalesNotFoundException(String salesId) {
        super("売上レコードが見つかりません: salesId=" + salesId);
        this.salesId = salesId;
    }

    public String getSalesId() { return salesId; }
}
