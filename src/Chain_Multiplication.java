public class Chain_Multiplication {
    static int[][] m;
    static int[][] s;

    public int[][] matrix_Chain_Order(int[] p){
        int n = p.length;
        for(int i=1; i<n; i++){
            m[i][i] = 0;
        }

        for(int l=2; l<n; l++){
            for(int i=1; i<n-1+1; i++){
                int j = i+l-1;
                m[i][j] = Integer.MAX_VALUE;

                for(int k=i; k<j-1; k++){
                    int q=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[i];

                    if(q<m[i][j]){
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        return m; //s도 return
    }
}
