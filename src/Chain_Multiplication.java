import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chain_Multiplication {
    static int[][] m = new int[6][6];
    static int[][] s = new int[6][6];

    public static int[][] matrix_Chain_Order(int[] p){
        int n = p.length-1;
        for(int i=1; i<=n; i++){
            m[i][i] = 0;
        }

        for(int l=2; l<=n; l++){
            for(int i=1; i<=n-1+1; i++){
                int j = i+l-1;
                m[i][j] = Integer.MAX_VALUE;

                for(int k=i; k<=j-1; k++){
                    int q = m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[i];

                    if(q<m[i][j]){
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        System.out.println("---------------------------------------------");
        for(int i=0; i<m.length; i++){
            for(int j=0; j<n; j++){
                System.out.println(m[i][j]+" ");
            }
        }
        System.out.println("--------------------------------------------");
        for(int i=0; i<m.length; i++){
            for(int j=0; j<n; j++){
                System.out.println(s[i][j]+" ");
            }
        }
        System.out.println("--------------------------------------------");

        return s;
    }

    public static void print_optimal_parens(int[][] s, int i, int j){
        if(i==j){
            System.out.println("A"+i+" ");
        } else {
            System.out.print("( ");
            print_optimal_parens(s,i,s[i][j]);
            print_optimal_parens(s, s[i][j]+1, j);
            System.out.println(")");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] p = new int[6];

        for(int i=1; i<=6; i++){
            System.out.print("A("+i+") = ");int row = Integer.parseInt(br.readLine());
            System.out.print(" x ");
            int column = Integer.parseInt(br.readLine());

            p[i-1] = row;
        }

        int[][] s = matrix_Chain_Order(p);
        print_optimal_parens(s,0,0);

    }
}
