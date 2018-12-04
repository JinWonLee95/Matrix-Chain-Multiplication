import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Chain_Multiplication {
    static int[][] s;
    static int[][] m;

    public static void matrix_Chain_Order(ArrayList<Integer> p) {
        m = new int[p.size()][p.size()];
        s = new int[p.size()][p.size()];
        int n = p.size() - 1;
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p.get(i - 1) * p.get(k) * p.get(j);

                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        System.out.println("------------------------------------------------------------------");
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i > j)
                    m[i][j] = -1;
                System.out.printf("%8d", m[i][j]);
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------------------------------");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > j)
                    s[i][j] = -1;
                System.out.printf("%8d", s[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------");

    }

    public static void print_optimal_parens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print(" A" + (i) + " ");
        } else {
            System.out.print(" ( ");
            print_optimal_parens(s, i, s[i][j]);
            print_optimal_parens(s, s[i][j] + 1, j);
            System.out.print(" ) ");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> p = new ArrayList<>();
        int i = 1;

        while (true) {
            System.out.print(i + "번째 수: ");
            int temp = sc.nextInt();
            if (temp == 0) {
                break;
            }
            p.add(temp);
            i++;
        }

        System.out.println("=========================================================================================");
        for (int k = 1; k < i - 1; k++) {
            System.out.println("A(" + k + ") = " + p.get(k - 1) + " x " + p.get(k));
        }

        matrix_Chain_Order(p);

        System.out.println("Optimal Solution : "+m[1][m.length-1]);
        System.out.print("Optimal Parens: "); print_optimal_parens(s, 1, i - 2);


    }
}
