package dynamic_programming;

public class P {
    public static void main(String[] args) {
        int n = 5;
        int m=4;
        for(int i=1;i<=5;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j);

            }
            System.out.println();
        }
        for(int i=m;i>=1;i--)
        {
            for(int j=1;j<=i;j++)
            {

                System.out.print(j);

            }
            System.out.println();
        }



    }
}