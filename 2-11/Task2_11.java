/**
* Task2-11 : 課題内容
* Java2章中間チェックテスト
*
* 本課題では、これまでの内容の振り返りを行い知識を深めていきましょう。
* 問①から問③まであります。
* 指定された値や条件を守って記述して下さい。
*
* 問③に関しては、記述してある内容を含めて結果通りに出力されるように記載を行って下さい。
*
*/

// import java.util.ArrayList;

public class Task2_11 {
    public static void main(String[] args){
    /*
    *問①
    int型の変数 a と b を宣言し、それぞれ10と3を代入して、次の演算を行い、結果を表示して下さい：
    ・a と b の和
    ・a と b の差
    ・a と b の積
    ・a と b の商
    ・a を b で割った余り
    */
    int a = 10, b = 3;
    System.out.println("和：" + (a + b));
    System.out.println("差：" + (a - b));
    System.out.println("積：" + (a * b));
    System.out.println("商：" + (a / b));
    System.out.println("余り：" + (a % b));

    /*
    * 問題②
    * int型の配列を宣言し、1から20までの偶数を格納して、ループを使用してそれらの整数を順番に表示して下さい。
    */
  
int[] arr = new int[10];

for(int i = 0; i < arr.length; i++){
    arr[i] = (i + 1) * 2;
}

for(int num : arr){
    System.out.println(num);
}

/*
     【配列数が未定や数えるのが大変な時の書き方】
ArrayList<Integer> arr = new ArrayList<>();
   int i = 1;
   while(i <= 20){
    if(0 == i % 2){
    arr.add(i);
    }
    i++;
   }
 *    【ちゃんと書いた出力】
   for(int j = 0; j < arr.size(); j++){
    System.out.println(arr.get(j));
   }
 *    【拡張for文(for-each)を使った出力】
   for(int num : arr){
       System.out.println(num);
   }
*/


    /*
    * int型の配列「numbers」内の奇数の合計を、メソッド「sumOddNumbers」を使って表示して下さい。
    * ただし、0は偶数として扱います。
    */
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int add = sumOddNumbers(numbers);
        System.out.println("奇数の合計: " + add);
    }

    public static int sumOddNumbers(int[] numbers) {
    int add = 0;

    for (int i = 0; i < numbers.length; i++) {
    //問題③ 奇数かどうかの判定をするために、for文で取得したnumberを2で割り、余りが0にならないような条件文(if文)を記載して下さい。
        if(0 != numbers[i] % 2){
        add += numbers[i];
        }
    }
    return add;
    }
}