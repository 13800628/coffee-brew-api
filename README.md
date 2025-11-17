# Coffee Brew API ☕️

学習のため作成したものです。
コーヒー豆の情報を管理・検索できるシンプルなRESTful APIです。  
フロントエンド（React）と連携し、豆の登録や検索が可能です。

---

## 🔧 使用技術

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database（開発用DB）
- Maven
- Jakarta Validation

---

## 🚀 機能概要

### エンドポイント一覧

| `GET`   | `/beans` | 条件（name, origin, flavor, brewMethod）でコーヒー豆を検索 |
| `GET`   | `/beans/search?keyword=` | キーワードで豆を検索（複数フィールド対象） |
| `POST`  | `/beans` | 新しいコーヒー豆の登録（JSON形式） |
| `GET`   | `/brew-methods` | 抽出方法の一覧取得 |

---

AIを活用しながら作成したものです
