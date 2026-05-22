/**
* Task2-8 : 課題内容
*
* 本課題では、演算子の使い方を学んでいきましょう。
* 問①から問⑦まであります。
* 指定された数値、式、変数名を守って記述して下さい。
*
* 問③,⑤については、コメントを入れて下さい。
* 問⑦については、変数名を使って出力して下さい。
*
*/
public class Task2_8 {


    public static void main(String[] args) {


    // ① 5 と 16 を加算した値を代入する、変数 plus を作成して下さい。
        int plus = 0;
        plus = 5 + 16;


    // ② 27 から 7 を減算した値を代入する、変数 minus を作成して下さい。
        int minus = 0;
        minus = 27 - 7;


    // ③下記の処理について、何をしているのかコメントを記入して下さい。
    // 数値、10と2を掛け算して、その結果を変数「multiply」に格納している。
    int multiply = 10 * 2;


    // ④ 20 を 6 で割った余りを代入する、変数 remi を作成して下さい。
        int remi = 0;
        remi = 20 % 6;


    // ⑤下記の処理について、何をしているのかコメントを記入して下さい。
    // 変数「town」に「中央」を代入。変数「line」に「線」を代入している。
    String town = "中央";
    String line = "線";


    // ⑥ ⑤で作成した変数を連結させた値を代入する変数 train を作成して下さい。
        String train = town + line;


    // ⑦ 変数plus, minus, multiply, remi, train をそれぞれ出力して下さい。
        System.out.printf(plus + "\n" + minus + "\n" + multiply + "\n" + remi + "\n" + train);
    }
}