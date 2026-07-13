import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillingServiceTest {

    @Mock
    private InvoiceRepository repository;

    @InjectMocks
    private BillingService service;

    @Test
    void findById_存在する請求書IDのとき請求書が返る() {
        Invoice expected = new Invoice("INV-001", "C001", "田中商事",
                100000, 10, 0, "2026-05-01", "2026-05-31", "未払い");
        when(repository.findById("INV-001")).thenReturn(expected);

        Invoice result = service.findById("INV-001");

        assertNotNull(result);
        assertEquals("INV-001", result.getInvoiceId());
        assertEquals("田中商事", result.getCustomerName());
    }

    @Test
    void calcTotalWithTax_割引なし税率10パーセントのとき正しい税込金額が返る() {
        Invoice invoice = new Invoice("INV-002", "C002", "鈴木物産",
                50000, 10, 0, "2026-05-05", "2026-05-31", "未払い");
        when(repository.findById("INV-002")).thenReturn(invoice);

        int total = service.calcTotalWithTax("INV-002");

        assertEquals(55000, total);//60000 → 55000 期待値の数値修正
    }

    @Test
    void calcTotalWithTax_割引あり税率10パーセントのとき正しい税込金額が返る() {
        Invoice invoice = new Invoice("INV-003", "C003", "佐藤工業",
                100000, 10, 10000, "2026-05-06", "2026-05-31", "未払い");
        when(repository.findById("INV-003")).thenReturn(invoice);

        int total = service.calcTotalWithTax("INV-003");

        assertEquals(99000, total);
    }

    @Test
    void markAsPaid_未払いの請求書を入金済みにできること() {
        Invoice invoice = new Invoice("INV-004", "C001", "田中商事",
                80000, 10, 0, "2026-05-10", "2026-05-31", "未払い");
        when(repository.findById("INV-004")).thenReturn(invoice);//INV-005 → INV-004 に修正

        service.markAsPaid("INV-004");

        verify(repository, times(1)).update(invoice);
        assertEquals("入金済", invoice.getStatus());
    }

    @Test
    void markAsPaid_すでに入金済みのとき例外が発生すること() {
        Invoice invoice = new Invoice("INV-006", "C004", "山田産業",
                30000, 10, 0, "2026-05-12", "2026-05-31", "入金済");
        when(repository.findById("INV-006")).thenReturn(invoice);

        assertThrows(BillingException.class, () -> service.markAsPaid("INV-006"));
    }

    @Test
    void issue_金額がゼロのとき例外が発生すること() {
        Invoice invoice = new Invoice("INV-007", "C005", "中村商会",
                0, 10, 0, "2026-05-15", "2026-05-31", "未払い");

        assertThrows(BillingException.class, () -> service.issue(invoice));
        verify(repository, never()).save(any());
    }

    @Test
    void issue_不正な税率のとき例外が発生すること() {
        Invoice invoice = new Invoice("INV-008", "C006", "加藤製作所",
                50000, 120, 0, "2026-05-15", "2026-05-31", "未払い");

        assertThrows(BillingException.class, () -> service.issue(invoice));
    }
}