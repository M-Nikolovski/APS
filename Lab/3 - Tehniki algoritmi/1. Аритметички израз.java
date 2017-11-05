import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {

        char sign = '0';

        if(r - l == 4) { // ako sme doshle do elementaren izraz

            sign = c[l+2];
            if (sign == '+')
                return c[l+1] + c[l+3] - 2 * '0';
            else
                return c[l+1] - c[l+3];

        } else { // ako izrazot se ushte moze da se deli na pomali izrazi

            int paranthesisCounter = 0;

            for (int i = l; i <= r; i++) {
                
                if (c[i] == '(')
                    paranthesisCounter++;
                
                else if (c[i] == (')'))
                    paranthesisCounter--;

                if (paranthesisCounter == 1 && c[i+1] != '(') {
                    
                    sign = c[i+1];
                    if (sign == '+')
                        return presmetaj(c, l + 1, i) + presmetaj(c, i + 2, r - 1);
                    else
                        return presmetaj(c, l + 1, i) - presmetaj(c, i + 2, r - 1);
                    
                }
            }
        }
        return 0;
    }



    public static void main(String[] args) throws Exception {
        
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
