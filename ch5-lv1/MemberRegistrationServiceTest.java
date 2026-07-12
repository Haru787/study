import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberRegistrationServiceTest {

    @Mock
    private MemberRepository repository;

    @InjectMocks
    private MemberRegistrationService service;

    @Test
    void findById_存在する会員IDのとき正しい会員が返る() {
        Member expected = new Member("M001", "田中 一郎", "tanaka@example.com",
                "090-1234-5678", "東京都新宿区", "ゴールド", true, "2025-01-10");
        when(repository.findById("M001")).thenReturn(expected);

        Member result = service.findById("M001");

        assertNotNull(result);
        assertEquals("M001", result.getMemberId());
        assertEquals("田中 一郎", result.getName());
        assertEquals("ゴールド", result.getRank());
    }

    @Test
    void findById_存在しない会員IDのとき例外が発生すること() {
        when(repository.findById("M999")).thenReturn(null);

        assertThrows(MemberNotFoundException.class, () -> service.findById("M999"));
    }

    @Test
    void register_正常な会員情報のとき保存されること() {
        Member member = new Member("M002", "鈴木 花子", "suzuki@example.com",
                "080-9876-5432", "大阪府大阪市", "シルバー", true, "2026-05-01");
        when(repository.existsByEmail("suzuki@example.com")).thenReturn(false);

        service.register(member);

        verify(repository, times(1)).save(member);
    }

    @Test
    void register_メールアドレスが重複しているとき例外が発生すること() {
        Member member = new Member("M003", "佐藤 次郎", "tanaka@example.com",
                "070-1111-2222", "神奈川県横浜市", "ブロンズ", true, "2026-05-10");
        when(repository.existsByEmail("tanaka@example.com")).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> service.register(member));
        verify(repository, never()).save(any());
    }

    @Test
    void register_名前が空のとき例外が発生すること() {
        Member member = new Member("M004", "", "empty@example.com",
                "090-0000-0000", "東京都渋谷区", "ブロンズ", true, "2026-05-10");

        assertThrows(IllegalArgumentException.class, () -> service.register(member));
    }

    @Test
    void register_メールアドレス形式が不正のとき例外が発生すること() {
        Member member = new Member("M005", "山田 三郎", "invalid-email",
                "090-3333-4444", "愛知県名古屋市", "ブロンズ", true, "2026-05-10");

        assertThrows(IllegalArgumentException.class, () -> service.register(member));
    }

    @Test
    void delete_存在しない会員IDのとき例外が発生すること() {
        when(repository.findById("M999")).thenReturn(null);

        assertThrows(MemberNotFoundException.class, () -> service.delete("M999"));//assertThrow → assertThrows 修正
    }
}
