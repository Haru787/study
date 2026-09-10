# API仕様書（顧客・注文管理システム）

対象コード: `Customer.java` / `OrderRecord.java` / `CustomerDao.java` / `OrderSummaryService.java`

## システム概要

`CustomerDao` / `OrderSummaryService` を Spring Boot の REST API として設計した場合の仕様。
顧客（`Customer`）と、顧客に紐づく注文（`OrderRecord`）をリソースとして扱う。
注文は顧客の配下リソースとして表現し、注文履歴・合計金額は顧客IDを起点に取得する。

- Content-Type：`application/json`（リクエスト・レスポンスとも）
- 文字コード：UTF-8

## エンドポイント一覧

| # | 操作 | 対象 | エンドポイント | メソッド | 関連クラス |
|---|---|---|---|---|---|
| 1 | 顧客登録 | Customer | `/api/customers` | POST | `CustomerDao#insert`、`Customer` |
| 2 | 顧客取得（ID指定） | Customer | `/api/customers/{customerId}` | GET | `CustomerDao#findById`、`Customer` |
| 3 | 注文登録 | OrderRecord | `/api/orders` | POST | `OrderSummaryService#addOrder`、`OrderRecord` |
| 4 | 注文履歴取得（顧客ID指定） | OrderRecord | `/api/customers/{customerId}/orders` | GET | `OrderSummaryService#printOrderHistory`、`OrderRecord` |
| 5 | 合計金額取得（顧客ID指定） | OrderRecord | `/api/customers/{customerId}/orders/total` | GET | `OrderSummaryService#calcTotalAmount` |

## HTTPステータスコードの目安

| コード | 意味 | 使用例 |
|---|---|---|
| 200 | OK | 取得成功 |
| 201 | Created | 登録成功 |
| 400 | Bad Request | 入力値不正（必須項目欠落・型不正・不正な値） |
| 404 | Not Found | 対象リソース（顧客・注文）が存在しない |
| 409 | Conflict | 一意制約違反（顧客IDの重複登録） |
| 500 | Internal Server Error | サーバー内部エラー |

---

## 1. 顧客登録

`POST /api/customers`

顧客情報を新規登録する。

### リクエストパラメータ

なし（パス・クエリパラメータともになし）。

### リクエストボディ

| 項目名 | 型 | 必須/任意 | 説明 |
|---|---|:---:|---|
| customerId | string | 必須 | 顧客ID。例：`C001` |
| customerName | string | 必須 | 顧客名 |
| email | string | 必須 | メールアドレス。email形式 |

```json
{
  "customerId": "C001",
  "customerName": "山田 太郎",
  "email": "yamada@example.com"
}
```

### レスポンスボディ（201）

| 項目名 | 型 | 説明 |
|---|---|---|
| customerId | string | 登録された顧客ID |
| customerName | string | 顧客名 |
| email | string | メールアドレス |

```json
{
  "customerId": "C001",
  "customerName": "山田 太郎",
  "email": "yamada@example.com"
}
```

### ステータスコード

| コード | ケース |
|---|---|
| 201 Created | 登録成功 |
| 400 Bad Request | 必須項目欠落、email形式不正 |
| 409 Conflict | customerId が既に登録済み |
| 500 Internal Server Error | サーバー内部エラー |

---

## 2. 顧客取得（ID指定）

`GET /api/customers/{customerId}`

顧客IDを指定して顧客情報を1件取得する。

### リクエストパラメータ

| 種別 | 項目名 | 型 | 必須/任意 | 説明 |
|---|---|---|:---:|---|
| パスパラメータ | customerId | string | 必須 | 取得対象の顧客ID |

### リクエストボディ

なし。

### レスポンスボディ（200）

| 項目名 | 型 | 説明 |
|---|---|---|
| customerId | string | 顧客ID |
| customerName | string | 顧客名 |
| email | string | メールアドレス |

```json
{
  "customerId": "C001",
  "customerName": "山田 太郎",
  "email": "yamada@example.com"
}
```

### ステータスコード

| コード | ケース |
|---|---|
| 200 OK | 取得成功 |
| 404 Not Found | 指定した customerId の顧客が存在しない |
| 500 Internal Server Error | サーバー内部エラー |

---

## 3. 注文登録

`POST /api/orders`

注文情報を新規登録する。

### リクエストパラメータ

なし。

### リクエストボディ

| 項目名 | 型 | 必須/任意 | 説明 |
|---|---|:---:|---|
| orderId | string | 必須 | 注文ID。例：`ORD-001` |
| customerId | string | 必須 | 注文者の顧客ID（`Customer` への外部キー） |
| productName | string | 必須 | 商品名 |
| amount | int | 必須 | 金額。1以上の整数 |

```json
{
  "orderId": "ORD-001",
  "customerId": "C001",
  "productName": "ノートPC",
  "amount": 120000
}
```

### レスポンスボディ（201）

| 項目名 | 型 | 説明 |
|---|---|---|
| orderId | string | 登録された注文ID |
| customerId | string | 顧客ID |
| productName | string | 商品名 |
| amount | int | 金額 |

```json
{
  "orderId": "ORD-001",
  "customerId": "C001",
  "productName": "ノートPC",
  "amount": 120000
}
```

### ステータスコード

| コード | ケース |
|---|---|
| 201 Created | 登録成功 |
| 400 Bad Request | 必須項目欠落、amount が0以下・数値以外 |
| 404 Not Found | customerId に該当する顧客が存在しない |
| 409 Conflict | orderId が既に登録済み |
| 500 Internal Server Error | サーバー内部エラー |

---

## 4. 注文履歴取得（顧客ID指定）

`GET /api/customers/{customerId}/orders`

指定した顧客の注文履歴を一覧取得する（`printOrderHistory` に相当する内容をJSONで返す）。

### リクエストパラメータ

| 種別 | 項目名 | 型 | 必須/任意 | 説明 |
|---|---|---|:---:|---|
| パスパラメータ | customerId | string | 必須 | 対象顧客ID |

### リクエストボディ

なし。

### レスポンスボディ（200）

| 項目名 | 型 | 説明 |
|---|---|---|
| customerId | string | 対象顧客ID |
| orders | array | 注文一覧（下記の要素を持つ配列） |
| orders[].orderId | string | 注文ID |
| orders[].productName | string | 商品名 |
| orders[].amount | int | 金額 |

```json
{
  "customerId": "C001",
  "orders": [
    { "orderId": "ORD-001", "productName": "ノートPC", "amount": 120000 },
    { "orderId": "ORD-002", "productName": "マウス", "amount": 3000 }
  ]
}
```

### ステータスコード

| コード | ケース |
|---|---|
| 200 OK | 取得成功（注文が0件の場合も `orders: []` で200を返す） |
| 404 Not Found | customerId に該当する顧客が存在しない |
| 500 Internal Server Error | サーバー内部エラー |

---

## 5. 合計金額取得（顧客ID指定）

`GET /api/customers/{customerId}/orders/total`

指定した顧客の注文金額の合計を取得する（`calcTotalAmount` に相当）。

### リクエストパラメータ

| 種別 | 項目名 | 型 | 必須/任意 | 説明 |
|---|---|---|:---:|---|
| パスパラメータ | customerId | string | 必須 | 対象顧客ID |

### リクエストボディ

なし。

### レスポンスボディ（200）

| 項目名 | 型 | 説明 |
|---|---|---|
| customerId | string | 対象顧客ID |
| totalAmount | int | 注文金額の合計 |

```json
{
  "customerId": "C001",
  "totalAmount": 123000
}
```

### ステータスコード

| コード | ケース |
|---|---|
| 200 OK | 取得成功（注文が0件の場合は `totalAmount: 0`） |
| 404 Not Found | customerId に該当する顧客が存在しない |
| 500 Internal Server Error | サーバー内部エラー |

---

## 補足

- 注文系のエンドポイント（3〜5）は `/api/customers/{customerId}/orders` を基点とし、顧客配下のリソースとして設計した。ただし注文登録（3）は `orderId` を主キーとする独立リソースのため `/api/orders` に POST する設計とした（作成時にボディへ `customerId` を含める）。
- 404 は「パスで指定した顧客が存在しない」場合に使用し、400 は「リクエスト自体の形式・値が不正」な場合に使い分けている。
- 409 は必須項目には含まれない `HTTPステータスコードの目安` 表にはないが、ID重複という現実的な異常系のため追加した。
- `amount` ・`totalAmount` は `int`（円）を想定し、小数は扱わない。
