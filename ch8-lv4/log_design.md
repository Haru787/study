# ログ設計書（請求書発行システム）

対象コード: `Customer.java` / `InvoiceItem.java` / `CustomerRepository.java` / `InvoiceService.java`

## ログ出力方針

- ログ出力形式：`[レベル] クラス名 - メッセージ`（例：`[INFO] InvoiceService - 請求書生成開始: customerId=CUS-001, 明細件数=3`）
- ログメッセージ例の変数部分は `{}` で表記する（SLF4J 等のプレースホルダ形式：`logger.info("...customerId={}", customerId)` を想定）
- 個人情報（会社名 `companyName`、請求先住所 `billingAddress`）は原則ログに出力しない。顧客IDなど識別子のみを出力する
- 例外発生時は、メッセージに加えてスタックトレースも出力する（`logger.error(message, exception)`）

## ログ出力先の使い分け

| 出力先 | 用途 |
|---|---|
| アプリケーションログ | 通常の業務処理の記録（DEBUG / INFO / WARN） |
| エラーログ | 例外発生時の記録（ERROR）。スタックトレースを含む。障害調査・監視アラートの対象 |

## ログ一覧

| ログID | ログレベル | 出力タイミング | ログメッセージ例 | 出力先 | 備考 |
|---|---|---|---|---|---|
| LOG-001 | DEBUG | 顧客検索開始（`CustomerRepository#findById` 呼び出し時） | `顧客検索開始: customerId={}` | アプリケーションログ | 検索キー（ID）のみ出力。開発・デバッグ用途で本番は出力抑制も可 |
| LOG-002 | DEBUG | 顧客検索成功（該当顧客が見つかった時） | `顧客検索成功: customerId={}` | アプリケーションログ | 会社名・住所などの個人情報は含めない |
| LOG-003 | WARN | 顧客検索失敗（該当顧客が存在せず `null` を返却した時） | `顧客が見つかりません（検索結果なし）: customerId={}` | アプリケーションログ | `CustomerRepository` 層での「結果なし」。この時点では例外ではなく、呼び出し元の使われ方次第では正常系（存在チェック）の場合もあるため WARN とする |
| LOG-004 | INFO | 請求書生成開始（`InvoiceService#generateInvoice` 呼び出し時） | `請求書生成開始: customerId={}, 明細件数={}` | アプリケーションログ | 業務イベントの開始として記録。処理時間計測の起点にも利用可能 |
| LOG-005 | INFO | 請求書生成完了（正常に処理が完了した時） | `請求書生成完了: customerId={}, 合計={}円, 消費税={}円, 税込合計={}円` | アプリケーションログ | 金額（`total` / `tax` / `totalWithTax`）を記録。監査・問い合わせ対応のため必須 |
| LOG-006 | ERROR | 例外発生（顧客が見つからず `IllegalArgumentException` がスローされた時） | `顧客が見つかりません: customerId={}` | エラーログ | 例外メッセージと同一内容。スタックトレースを合わせて出力し、監視ツールのアラート対象とする |
| LOG-007 | INFO | 顧客登録（`CustomerRepository#save` 呼び出し時） | `顧客登録: customerId={}` | アプリケーションログ | 会社名・住所は出力しない（個人情報保護の観点） |
| LOG-008 | DEBUG | 明細小計計算時（各 `InvoiceItem#calcSubtotal` 呼び出し・合計への加算時） | `明細小計計算: description={}, quantity={}, unitPrice={}, subtotal={}` | アプリケーションログ | 明細数が多いと出力量が増えるため、通常運用では DEBUG を無効化して出力抑制する想定 |
| LOG-009 | ERROR | 予期しない例外発生（`IllegalArgumentException` 以外の実行時例外。例：`items` が `null`／要素に不正値がある場合など） | `請求書生成中に予期しないエラーが発生しました: customerId={}, error={}` | エラーログ | 想定外の例外を握りつぶさず記録するためのフォールバック。スタックトレースを出力し原因調査に備える |

## ログメッセージの出力例（実値を当てはめた場合）

```
[DEBUG] CustomerRepository - 顧客検索開始: customerId=CUS-001
[DEBUG] CustomerRepository - 顧客検索成功: customerId=CUS-001
[WARN]  CustomerRepository - 顧客が見つかりません（検索結果なし）: customerId=CUS-099
[INFO]  InvoiceService - 請求書生成開始: customerId=CUS-001, 明細件数=3
[INFO]  InvoiceService - 請求書生成完了: customerId=CUS-001, 合計=610,000円, 消費税=61,000円, 税込合計=671,000円
[ERROR] InvoiceService - 顧客が見つかりません: customerId=CUS-099
```

## 対象タイミングとの対応

| # | 課題要件のタイミング | 対応するログID |
|---|---|---|
| 1 | 顧客検索開始 | LOG-001 |
| 2 | 顧客検索成功 | LOG-002 |
| 3 | 顧客検索失敗 | LOG-003 |
| 4 | 請求書生成開始 | LOG-004 |
| 5 | 請求書生成完了 | LOG-005 |
| 6 | 例外発生 | LOG-006（想定外の例外は LOG-009 で補完） |

## 補足

- LOG-007〜LOG-009 は課題の最低要件（6タイミング）には含まれないが、対象コードから読み取れる処理（`save`、明細計算のループ、`IllegalArgumentException` 以外の例外系）を踏まえて設計を補強するために追加した。
- ログレベルは「ログレベルの目安」に沿い、開発時のみ必要な情報は DEBUG、業務イベントの記録は INFO、まだ処理継続可能だが注意すべき状態は WARN、処理が中断するエラーは ERROR とした。
- 個人情報（`companyName` / `billingAddress`）は監査ログ等の別要件がない限り出力しない方針とした。出力が必要な場合は、マスキングの上での出力を検討する。
