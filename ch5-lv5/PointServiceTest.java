import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @Mock
    private PointAccountRepository repository;

    @InjectMocks
    private PointService service;

    @Test
    void findByMemberId_存在する会員IDのときポイント口座が返る() {
        PointAccount expected = new PointAccount("ACC-001", "M001", "田中 一郎",
                1500, 8500, "シルバー", "2026-05-20", true);
        when(repository.findByMemberId("M001")).thenReturn(expected);

        PointAccount result = service.findByMemberId("M001");

        assertNotNull(result);
        assertEquals("ACC-001", result.getAccountId());//AssertEquals → assertEqualsに修正
        assertEquals("田中 一郎", result.getMemberName());
    }

    @Test
    void findByMemberId_存在しない会員IDのとき例外が発生すること() {
        when(repository.findByMemberId("M999")).thenReturn(null);

        assertThrows(PointException.class, () -> service.findByMemberId("M999"));
    }

    @Test
    void add_正常なポイント付与で残高が更新されること() {
        PointAccount account = new PointAccount("ACC-002", "M002", "鈴木 花子",
                2000, 2000, "ブロンズ", "2026-05-18", true);
        when(repository.findByMemberId("M002")).thenReturn(account);

        service.add("M002", 500);

        verify(repository, times(1)).update(account);
        assertEquals(2500, account.getCurrentPoints());
    }

    @Test
    void add_累積ポイントが10000を超えたときランクがゴールドになること() {
        PointAccount account = new PointAccount("ACC-003", "M003", "佐藤 次郎",
                500, 9800, "シルバー", "2026-05-15", true);
        when(repository.findByMemberId("M003")).thenReturn(account);

        service.add("M003", 300);

        assertEquals("ゴールド", account.getRank());//ブロンズ → ゴールドに修正
    }

    @Test
    void add_ポイントがゼロのとき例外が発生すること() {
        assertThrows(PointException.class, () -> service.add("M004", 0));
    }

    @Test
    void use_残高が十分あるときポイントが消費されること() {
        PointAccount account = new PointAccount("ACC-005", "M005", "中村 五郎",
                3000, 15000, "ゴールド", "2026-05-10", true);
        when(repository.findByMemberId("M005")).thenReturn(account);

        service.use("M005", 1000);

        verify(repository, times(1)).update(account);
        assertEquals(2000, account.getCurrentPoints());
    }

    @Test
    void use_残高不足のとき例外が発生すること() {
        PointAccount account = new PointAccount("ACC-006", "M006", "山田 六郎",
                200, 5000, "シルバー", "2026-05-08", true);
        when(repository.findByMemberId("M006")).thenReturn(account);

        assertThrows(PointException.class, () -> service.use("M006", 500));
    }

    @Test
    void add_無効な口座のとき例外が発生すること() {
        PointAccount account = new PointAccount("ACC-007", "M007", "加藤 七郎",
                500, 500, "ブロンズ", "2026-04-01", false);
        when(repository.findByMemberId("M007")).thenReturn(account);

        assertThrows(PointException.class, () -> service.add("M007", 100));
        verify(repository, never()).update(any());//Verify → verifyに修正
    }
}