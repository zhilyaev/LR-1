package lr.tfya.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int i = 0;
    static Stack<Integer> stack = new Stack<>();
    static String lenta;
    final static String e = "Error";
    static String[][] table =  {
            {"o","S2",e,e,"S3",e,"S4","S11",e},//1
            {e,e,e,e,e,e,e,e,"R1"},//2
            {e,e,e,e,e,e,e,e,"R2"},//3
            {e,e,"S5",e,e,e,e,"S6","R5"},//4
            {e,e,e,e,e,e,e,e,"R3"},//5
            {e,e,e,e,e,e,"S7",e,e},//6
            {e,e,e,e,e,e,e,"S8",e},//7
            {e,e,e,e,e,e,"S9",e,e},//8
            {e,e,"S10",e,e,e,e,"S6","R5"},//9
            {e,e,e,e,e,e,e,e,"R4"},//10
            {e,e,e,e,e,"S12","S13",e,"R8"},//11
            {e,e,e,e,e,e,e,e,"R6"},//12
            {e,e,e,e,e,e,"S14",e,e},//13
            {e,e,e,e,"S15",e,e,"S11",e},//14
            {e,e,e,e,e,e,e,e,"R7"}//15
    };
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input line: ");
        lenta = input.readLine() + "$";

        stack.push(0);

        while(true){
            System.out.println("Lenta = "+lenta);
            //System.out.println("Stack = "+stack);
            String command = table[stack.peek()][getCol(String.valueOf(lenta.charAt(i)))];
            System.out.println("table["+(stack.peek()+1)+"]["+lenta.charAt(i)+"] = "+command);

            if(command.startsWith("S")){
                // shift
                shift(Integer.parseInt(command.substring(1)));
            }
            else if(command.startsWith("R")){
                // reduction 
                reduction(Integer.parseInt(command.substring(1)));
            }
            else if(command=="o"){
                // Допуск
                System.out.println("String is true");
                break;
            }
            else if(command==e){
                // Ошибка
                System.out.println(e);
                break;
            }
            else { // never
                System.out.println("index of table is not correct");
                break;
            }
        }
    }
    /* Получить номер колонки */
    static int getCol(String chr){
        return "ABZCDXab$".indexOf(chr);
    }
    
    /* shift */
    static void shift(int rule){
        System.out.println("Shift -> "+rule);
        stack.push(rule-1);
        i++;
    }

     /* reduction */
    static void reduction(int rule){
        System.out.println("Reduction -> "+rule);
        switch (rule-1){
            case 0:
                drop(1);
                pushAt("A");
                break;
            case 1:
                drop(1);
                pushAt("A");
                break;
            case 2:
                drop(2);
                pushAt("B");
                break;
            case 3:
                drop(5);
                pushAt("Z");
                break;
            case 4:
                pushAt("Z");
                break;
            case 5:
                drop(2);
                pushAt("D");
                break;
            case 6:
                drop(3);
                pushAt("X");
                break;
            case 7:
                pushAt("X");
                break;
        }
    }
    /* Удалить N символов из стека */
    static void drop(int n){
        for(int k=0;k<n;k++) stack.pop();
    }

    /* поместить в ленту  */
    static void pushAt(String chr){
        lenta = lenta.substring(0,i)+chr+lenta.substring(i);

    }

}
