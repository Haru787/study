import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    @Test
    void findById_存在する注文IDのとき注文が返る() {
        List<OrderItem> items = Arrays.asList(
                new OrderItem("P001", "ノートPC", 128000, 1)
        );
        Order expected = new Order("ORD-001", "C001", "田中 一郎", items,
                "受付済", "2026-05-20", "東京都新宿区", "");
        when(repository.findById("ORD-001")).thenReturn(expected);

        Order result = service.findById("ORD-001");

        assertNotNull(result);
        assertEquals("ORD-001", result.getOrderId());
        assertEquals("田中 一郎", result.getCustomerName());
    }

    @Test
    void calcTotalAmount_注文の合計金額が正しく計算されること() {
        List<OrderItem> items = Arrays.asList(
                new OrderItem("P001", "ノートPC", 128000, 2),
                new OrderItem("P002", "マウス", 3800, 3)
        );
        Order order = new Order("ORD-002", "C002", "鈴木 花子", items,
                "受付済", "2026-05-21", "大阪府大阪市", "");
        when(repository.findById("ORD-002")).thenReturn(order);//ORD-003 → ORD-002へ修正

        int total = service.calcTotalAmount("ORD-002");

        assertEquals(267400, total);
    }

    @Test
    void place_明細が空のとき例外が発生すること() {
        Order order = new Order("ORD-003", "C003", "佐藤 次郎", Collections.emptyList(),
                "受付済", "2026-05-21", "神奈川県横浜市", "");

        assertThrows(IllegalArgumentException.class, () -> service.place(order));
        verify(repository, never()).save(any());
    }

    @Test
    void cancel_受付済みの注文をキャンセルできること() {
        List<OrderItem> items = Arrays.asList(
                new OrderItem("P003", "キーボード", 12000, 1)
        );
        Order order = new Order("ORD-004", "C001", "田中 一郎", items,
                "受付済", "2026-05-19", "東京都新宿区", "");
        when(repository.findById("ORD-004")).thenReturn(order);

        service.cancel("ORD-004");

        verify(repository, times(1)).update(order);
        assertEquals("キャンセル", order.getStatus());
    }

    @Test
    void cancel_すでにキャンセル済みのとき例外が発生すること() {
        List<OrderItem> items = Arrays.asList(
                new OrderItem("P004", "モニター", 45000, 1)
        );
        Order order = new Order("ORD-005", "C004", "山田 三郎", items,
                "キャンセル", "2026-05-18", "愛知県名古屋市", "");
        when(repository.findById("ORD-005")).thenReturn(order);

        assertThrows(OrderCancelledException.class, () -> service.cancel("ORD-005"));
    }

    @Test
    void findByCustomerId_顧客IDで注文一覧が返ること() {
        List<OrderItem> items = Arrays.asList(
                new OrderItem("P005", "プリンター", 32000, 1)
        );
        Order order = new Order("ORD-006", "C005", "中村 四郎", items,
                "発送済", "2026-05-15", "福岡県福岡市", "");
        when(repository.findByCustomerId("C005")).thenReturn(Arrays.asList(order));

        List<Order> result = service.findByCustomerId("C005");

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}