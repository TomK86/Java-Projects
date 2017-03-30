import java.io.*;
import java.utils.*;

/* KMP algorithm - searches for a substring W within a string S and returns the starting index of the first match */

public class KMP {

    private static String S;
    private static int N;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        S = in.next();
        String W = in.next();
        in.close();
        N = S.length();
        int M = W.length();
        if (M == 0) {
            System.out.println("Must enter a valid search string");
        } else if (M == 1) {
            System.out.println(S.indexOf(W));
        } else {
            System.out.println(kmp(W, M));
        }
    }

    private static int kmp(String W, int M) {
        int m = 0, i = 0;
        int[] T = kmpTable(W, M);
        while (m + i < N) {
            if (W.charAt(i) == S.charAt(m+i)) {
                if (i == M - 1) { return m; }
                i++;
            } else {
                if (T[i] > -1) {
                    m += i - T[i];
                    i = T[i];
                } else {
                    m++;
                    i = 0;
                }
            }
        }
        return N;
    }

    private static int[] kmpTable(String W, int M) {
        int pos = 2, cnd = 0;
        int[] T = new int[M];
        T[0] = -1;
        T[1] = 0;
        while (pos < M) {
            if (W.charAt(pos-1) == W.charAt(cnd)) {
                T[pos] = cnd + 1;
                cnd++;
                pos++;
            } else if (cnd > 0) {
                cnd = T[cnd];
                T[pos] = 0;
            } else {
                T[pos] = 0;
                pos++;
            }
        }
        return T;
    }
}