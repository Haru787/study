/**
 * 不正な売上データが渡された場合にスローされる例外。
 */
public class InvalidSalesDataException extends RuntimeException {

    private final String field;
    private final Object value;

    public InvalidSalesDataException(String field, Object value) {
        super("不正な売上データ: field=" + field + ", value=" + value);
        this.field = field;
        this.value = value;
    }

    public String getField() { return field; }
    public Object getValue() { return value; }
}