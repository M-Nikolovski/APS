import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.abs;

public class Array<E> {

    private E elementi[];
    private int brojNaElementi;
    public Array(int brojNaElementi) {
        this.brojNaElementi = brojNaElementi;
        elementi = (E[]) new Object[brojNaElementi];
    }
    public double prosek() {
        double zbir = 0;
        for (E element : elementi) {
            zbir += (Integer) element;
        }
        return zbir / brojNaElementi;
    }
    public void setElementAt(int i, E n) {
        elementi[i] = n;
    }
    public E getElementAt(int i) {
        return elementi[i];
    }
    public int getBrojNaElementi() {
        return brojNaElementi;
    }


    public static int brojDoProsek(Array<Integer> niza) {
        int broj = niza.getElementAt(0);
        double prosek = niza.prosek();
        double minRazlika = abs(niza.getElementAt(0) - prosek);
        for (int i = 1; i < niza.getBrojNaElementi(); i++) {
            if ((abs(niza.getElementAt(i) - prosek) < minRazlika) ||
                    ((abs(niza.getElementAt(i) - prosek) == minRazlika) && (niza.getElementAt(i) < broj))) {
                minRazlika = abs(niza.getElementAt(i) - prosek);
                broj = niza.getElementAt(i);
            }
        }
        return broj;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        Array<Integer> niza = new Array<>(N);
        for (int i = 0; i < N; i++) {
            niza.setElementAt(i, Integer.parseInt(stdin.readLine()));
        }

        System.out.println(brojDoProsek(niza));
    }

}
