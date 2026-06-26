/**
 * 価格を持つオブジェクトを表すインターフェース。
 */
public interface Priceable {
    /** 単価を返す。 */
    int getUnitPrice();
    /** 小計を返す。 */
    int calcSubtotal();
}
