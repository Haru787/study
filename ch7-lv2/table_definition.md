# テーブル定義書（在庫管理システム）

対象コード: `Category.java` / `Product.java` / `Stock.java` / `ProductImage.java` / `ProductRepository.java`

DBMS 想定: MySQL / 文字コード: utf8mb4 / `updated_at` は `ON UPDATE CURRENT_TIMESTAMP` を付与

## テーブル一覧

| テーブル物理名 | テーブル論理名 | 説明 |
|---|---|---|
| `categories` | カテゴリマスタ | 商品カテゴリの階層構造を管理する |
| `products` | 商品マスタ | 商品の基本情報・価格を管理する |
| `stocks` | 在庫 | 商品ごとの在庫数・発注点を管理する |
| `product_images` | 商品画像 | 商品に紐づく画像URLと表示順を管理する |

## categories（カテゴリマスタ）

商品カテゴリの階層構造を管理する。`parent_category_id` が `NULL` の行はルートカテゴリを表す。

| # | カラム名 | 論理名 | データ型 | NOT NULL | PK | FK | デフォルト値 | コメント |
|---:|---|---|---|:---:|:---:|:---:|---|---|
| 1 | `category_id` | カテゴリID | VARCHAR(50) | ○ | ○ |  |  | 例：`CAT-01` |
| 2 | `category_name` | カテゴリ名 | VARCHAR(255) | ○ |  |  |  | カテゴリ名（例：家電、書籍） |
| 3 | `parent_category_id` | 親カテゴリID | VARCHAR(50) |  |  | ○ | NULL | `categories.category_id` への自己参照外部キー。NULL = ルートカテゴリ |
| 4 | `description` | カテゴリ説明 | TEXT |  |  |  | NULL | カテゴリの補足説明 |
| 5 | `display_order` | 表示順 | INT | ○ |  |  | 0 | 一覧表示時の並び順（昇順）。0以上 |
| 6 | `is_active` | 有効フラグ | TINYINT(1) | ○ |  |  | 1 | 0 = 無効、1 = 有効 |
| 7 | `created_at` | 登録日時 | DATETIME | ○ |  |  | NOW() | レコード登録日時 |
| 8 | `updated_at` | 更新日時 | DATETIME | ○ |  |  | NOW() | レコード更新日時（更新時に現在日時へ自動更新） |

## products（商品マスタ）

商品の基本情報・価格を管理する。`category_id` で `categories` に紐づく。

| # | カラム名 | 論理名 | データ型 | NOT NULL | PK | FK | デフォルト値 | コメント |
|---:|---|---|---|:---:|:---:|:---:|---|---|
| 1 | `product_id` | 商品ID | VARCHAR(50) | ○ | ○ |  |  | 例：`PROD-0001` |
| 2 | `product_name` | 商品名 | VARCHAR(255) | ○ |  |  |  | 商品名 |
| 3 | `category_id` | カテゴリID | VARCHAR(50) | ○ |  | ○ |  | `categories.category_id` への外部キー |
| 4 | `sku` | SKUコード | VARCHAR(255) | ○ |  |  |  | SKUコード（例：`SKU-ABC-001`）。UNIQUE制約 |
| 5 | `unit_price` | 販売単価（税抜） | INT | ○ |  |  | 0 | 販売単価（税抜）。0以上 |
| 6 | `cost_price` | 仕入れ単価 | INT | ○ |  |  | 0 | 仕入れ単価。0以上 |
| 7 | `description` | 商品説明 | TEXT |  |  |  | NULL | 商品の詳細説明 |
| 8 | `is_active` | 販売中フラグ | TINYINT(1) | ○ |  |  | 1 | 0 = 販売停止、1 = 販売中 |
| 9 | `created_at` | 登録日時 | DATETIME | ○ |  |  | NOW() | レコード登録日時 |
| 10 | `updated_at` | 更新日時 | DATETIME | ○ |  |  | NOW() | レコード更新日時（更新時に現在日時へ自動更新） |

## stocks（在庫）

商品ごとの在庫数・発注点を管理する。1商品につき1件（`product_id` は UNIQUE）。`id` は自動採番の代理キー（Java の `stockId` に対応）。

| # | カラム名 | 論理名 | データ型 | NOT NULL | PK | FK | デフォルト値 | コメント |
|---:|---|---|---|:---:|:---:|:---:|---|---|
| 1 | `id` | 在庫ID | INT | ○ | ○ |  | AUTO_INCREMENT | 自動採番（PK） |
| 2 | `product_id` | 商品ID | VARCHAR(50) | ○ |  | ○ |  | `products.product_id` への外部キー。UNIQUE制約（商品と1対1） |
| 3 | `quantity` | 現在在庫数 | INT | ○ |  |  | 0 | 現在の在庫数。0以上 |
| 4 | `reorder_point` | 発注点 | INT | ○ |  |  | 0 | この数を下回ったら発注する。0以上 |
| 5 | `reorder_quantity` | 発注数量 | INT | ○ |  |  | 0 | 発注時の数量。0以上 |
| 6 | `warehouse_location` | 倉庫内保管場所 | VARCHAR(255) |  |  |  | NULL | 倉庫内の保管場所（例：`A-3-2`） |
| 7 | `created_at` | レコード作成日時 | DATETIME | ○ |  |  | NOW() | レコード登録日時 |
| 8 | `updated_at` | 在庫更新日時 | DATETIME | ○ |  |  | NOW() | 在庫更新日時（更新時に現在日時へ自動更新） |

## product_images（商品画像）

商品に紐づく画像URLと表示順を管理する。1商品に複数画像。`id` は自動採番の代理キー（Java の `imageId` に対応）。

| # | カラム名 | 論理名 | データ型 | NOT NULL | PK | FK | デフォルト値 | コメント |
|---:|---|---|---|:---:|:---:|:---:|---|---|
| 1 | `id` | 画像ID | INT | ○ | ○ |  | AUTO_INCREMENT | 自動採番（PK） |
| 2 | `product_id` | 商品ID | VARCHAR(50) | ○ |  | ○ |  | `products.product_id` への外部キー |
| 3 | `image_url` | 画像URL | VARCHAR(255) | ○ |  |  |  | 画像ファイルのURL |
| 4 | `alt_text` | 代替テキスト | VARCHAR(255) |  |  |  | NULL | 画像の代替テキスト（alt属性） |
| 5 | `is_primary` | メイン画像フラグ | TINYINT(1) | ○ |  |  | 0 | 0 = 通常画像、1 = メイン画像 |
| 6 | `sort_order` | 表示順 | INT | ○ |  |  | 0 | 画像の表示順（昇順）。0以上 |
| 7 | `created_at` | 登録日時 | DATETIME | ○ |  |  | NOW() | レコード登録日時 |
| 8 | `updated_at` | 更新日時 | DATETIME | ○ |  |  | NOW() | レコード更新日時（更新時に現在日時へ自動更新） |

## 制約・補足

- **PK**: `categories.category_id` / `products.product_id` / `stocks.id` / `product_images.id`
- **FK**: `products.category_id` → `categories.category_id`
- **FK**: `categories.parent_category_id` → `categories.category_id`（自己参照。NULL許可）
- **FK**: `stocks.product_id` → `products.product_id`（UNIQUE。1商品:1在庫）
- **FK**: `product_images.product_id` → `products.product_id`（1商品:N画像）
- **UNIQUE**: `products.sku`、`stocks.product_id`
- **CHECK**: `unit_price` / `cost_price` / `quantity` / `reorder_point` / `reorder_quantity` / `display_order` / `sort_order` は 0 以上
- 全テーブルに `created_at`・`updated_at`（DATETIME, NOT NULL, DEFAULT NOW()）を保持。`updated_at` は `ON UPDATE CURRENT_TIMESTAMP`
- `boolean` 型（`is_active` / `is_primary`）は TINYINT(1) で表現し、0 = false / 1 = true

---

参照: スプレッドシート「テーブル定義書の作成」シート
https://docs.google.com/spreadsheets/d/1UsVeFtGBc83eR-jCIm6Zb6_WjOm-o-ZK-xHDbtf59AM/edit?gid=1969294282#gid=1969294282
