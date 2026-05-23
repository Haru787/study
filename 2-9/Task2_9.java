/**
* Task2-9: 課題内容
*
* 問1：if文の課題
* ログイン機能をイメージして処理を書いてみましょう。
* ・アカウント情報としてデータベースに登録されている情報
* 　名前：alice
* 　パスワード：alice123
* 本課題では、すべての定数(合計６種類)を使いつつ、if文の使い方に慣れていきましょう。
* 問①〜問④まで回答お願いします。
*
* 問2：switch文の課題
* 曜日に応じた営業時間を表示するプログラムを作成しましょう。
* ・曜日は文字列（String型）で管理されています
* ・各曜日に応じた営業時間を表示してください
* 
* 営業時間：
* 月曜日〜金曜日：「平日営業：9:00〜18:00」
* 土曜日：「土曜営業：10:00〜17:00」
* 日曜日：「定休日」
* その他：「エラー：不正な曜日です」
*
*/
public class Task2_9 {

  // ========== 問1：if文用の定数 ==========
  // 定数（アカウント情報）
  private static final String USER_NAME = "alice";
  private static final String USER_PASSWORD = "alice123";

  // 定数（メッセージ）
  private static final String CONST_MSG_SUCCESS = "ログイン成功です。";
  private static final String CONST_MSG_ERROR_NAME = "名前に誤りがあります。";
  private static final String CONST_MSG_ERROR_PASS = "パスワードに誤りがあります。";
  private static final String CONST_MSG_ERROR_INPUT = "入力情報に誤りがあります。";

 // ========== 問2：switch文用の定数 ==========
  // 定数（曜日）
  private static final String MON = "月曜日";
  private static final String TUE = "火曜日";
  private static final String WED = "水曜日";
  private static final String THU = "木曜日";
  private static final String FRI = "金曜日";
  private static final String SAT = "土曜日";
  private static final String SUN = "日曜日";
  
  // 定数（営業時間メッセージ）
  private static final String MSG_WEEKDAY = "平日営業：9:00〜18:00";
  private static final String MSG_SATURDAY = "土曜営業：10:00〜17:00";
  private static final String MSG_SUNDAY = "定休日";
  private static final String MSG_ERROR = "エラー：不正な曜日です";

  // 「ここへ記述」部分へ適当な値を記述しましょう。

  public static void main(String[] args) {

    // ========== 問1：if文の課題 ==========
    // 以下の変数「name」「pass」を使用して、①〜④の条件を満たす処理を記述して下さい。
    // nameとpassはログイン画面からの入力値だと想定してみましょう。

    String name = "alice";
    String pass = "alice123";

    // ① 「name」の値が「USER_NAME」と等しく、「 pass 」の値が「USER_PASSWORD」と等しい場合。
    // 定数を使用して「 ログイン成功です。 」と出力して下さい。
    if(name.equals(USER_NAME) && pass.equals(USER_PASSWORD)) {
        System.out.println(CONST_MSG_SUCCESS);
    }

    // ② 「USER_NAME」の値のみ等しい場合。
    // 定数を使用して「 パスワードに誤りがあります。 」 と出力して下さい。
    else if(name.equals(USER_NAME)) {
        System.out.println(CONST_MSG_ERROR_PASS);
    }

    // ③ 「USER_PASSWORD」の値のみ等しい場合。
    //定数を使用して「 名前に誤りがあります。 」と出力して下さい。
    else if(pass.equals(USER_PASSWORD)){
        System.out.println(CONST_MSG_ERROR_NAME);
    }

    // ④ 「USER_NAME」も「USER_PASSWORD」の値も間違っていた場合。
    //定数を使用して「 入力情報に誤りがあります。 」と出力して下さい。
    else {
        System.out.println(CONST_MSG_ERROR_INPUT);
    }

    // ========== 問2：switch文の課題 ==========
    // 曜日
    String dayOfWeek = "水曜日";
    
    // ① switch文を使って、dayOfWeekの値に応じた営業時間を表示してください
    // 必ず定数を使用してください
    // ここにswitch文を記述
    switch(dayOfWeek) {
        case MON, TUE, WED, THU, FRI ->
            System.out.println(MSG_WEEKDAY);

        case SAT ->
            System.out.println(MSG_SATURDAY);

        case SUN ->
            System.out.println(MSG_SUNDAY);

        default ->
            System.out.println(MSG_ERROR);
    }

  }
}