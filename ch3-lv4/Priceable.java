/**
 * 価格を持つオブジェクトを表すインターフェース。
 */
public interface Priceable {
    int getUnitPrice();
    int calcSubtotal();
}
