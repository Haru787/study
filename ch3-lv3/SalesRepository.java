import java.util.ArrayList;
import java.util.List;

/**
 * 売上レコードのリポジトリクラス。
 */
public class SalesRepository {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(SalesRepository.class);

    private final List<SalesRecord> records = new ArrayList<>();

    /**
     * 売上レコードを追加する。
     * @param record 追加する売上レコード
     */
    public void add(SalesRecord record) {
        records.add(record);
        log.info("売上登録: salesId={}", record.getSalesId());
    }

    /** 全レコードを返す。 */
    public List<SalesRecord> findAll() {
        return new ArrayList<>(records);
    }
}
