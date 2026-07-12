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
class InventoryServiceTest {

    @Mock
    private InventoryRepository repository;

    @InjectMocks
    private InventoryService service;

    @Test
    void findByCode_存在するコードのとき在庫が返る() {
        StockItem expected = new StockItem("SKU-001", "コピー用紙A4", "消耗品",
                200, 50, 800, "A-01", "2026-05-20");
        when(repository.findByCode("SKU-001")).thenReturn(expected);

        StockItem result = service.findByCode("SKU-001");

        assertNotNull(result);
        assertEquals("SKU-001", result.getItemCode());
        assertEquals("コピー用紙A4", result.getItemName());
    }

    @Test
    void isInStock_在庫ゼロのときfalseが返る() {
        StockItem item = new StockItem("SKU-002", "ボールペン", "文具",
                0, 20, 120, "B-03", "2026-05-18");
        when(repository.findByCode("SKU-002")).thenReturn(item);

        boolean result = service.isInStock("SKU-002");

        assertFalse(result);
    }

    @Test
    void receive_正常な入庫数のとき在庫が更新されること() {
        StockItem item = new StockItem("SKU-003", "蛍光ペン", "文具",
                30, 10, 200, "B-05", "2026-05-15");
        when(repository.findByCode("SKU-003")).thenReturn(item);

        service.receive("SKU-003", 50);

        verify(repository, times(1)).update(item);
    }

    @Test
    void receive_入庫数ゼロのとき例外が発生すること() {
        assertThrows(IllegalArgumentException.class, () -> service.receive("SKU-004", 0));
    }

    @Test
    void ship_在庫が十分あるとき出庫が成功すること() {
        StockItem item = new StockItem("SKU-005", "ノート", "文具",
                100, 20, 180, "C-02", "2026-05-12");
        when(repository.findByCode("SKU-005")).thenReturn(item);

        service.ship("SKU-005", 30);

        verify(repository, times(1)).update(item);
    }

    @Test
    void ship_在庫が不足しているとき例外が発生すること() {
        StockItem item = new StockItem("SKU-006", "付箋", "文具",
                5, 15, 150, "C-04", "2026-05-10");
        when(repository.findByCode("SKU-006")).thenReturn(item);

        assertThrows(InsufficientStockException.class, () -> service.ship("SKU-006", 10));
    }

    @Test
    void findBelowReorderPoint_発注点割れ商品一覧が返ること() {
        StockItem item1 = new StockItem("SKU-007", "クリップ", "文具",
                3, 10, 50, "D-01", "2026-05-08");
        StockItem item2 = new StockItem("SKU-008", "輪ゴム", "文具",
                2, 15, 40, "D-02", "2026-05-08");
        when(repository.findBelowReorderPoint()).thenReturn(Arrays.asList(item1, item2));

        List<StockItem> result = service.findBelowReorderPoint();

        assertNotNull(result);
        assertEquals(2, result.size());//AssertEquals → assertEqualsへ修正
    }

    @Test
    void isInStock_存在しない商品コードのときfalseが返る() {
        when(repository.findByCode("SKU-999")).thenReturn(null);//nul → nullへ修正

        boolean result = service.isInStock("SKU-999");

        assertFalse(result);
    }
}