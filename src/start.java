import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class start {
    public static void fillWithAllowed (int [] matrix){
        //generates a vector of allowed values
        List<Integer> allowedList = IntStream.range(1, matrix.length + 1).boxed().collect(Collectors.toList());
        Collections.shuffle( allowedList );
        for (int i = 0; i < matrix.length; i++){
            matrix[i] = allowedList.get(i);
        }
        //fills the matrix in a random order with allowed values
    }
    public static void printSquareMatrix (int [] matrix, int n){ //prints to console a matrix o f nxn
        for (int i = 0; i < n ; i++ ){
            for (int j = 0; j < n ; j++ ){
                if (matrix[i * n + j] <= 10 && n*n > 10)
                    System.out.print("(0" + matrix[i * n + j] + ")");

                else
                    System.out.print("(" + matrix[i * n + j] + ")");
            }
            System.out.print("\n");
        }
    }
    public static boolean checkRowsColumns(int [] matrix, int n, int toMatch){ //checks the columns and rows
        int sumRows;
        int sumColumns;
        for (int i = 0; i < n; i++){
            sumRows = 0;
            sumColumns = 0;
            for (int j = 0; j < n; j++){
                sumRows += matrix[i*n + j];
                sumColumns += matrix[i + j*n];
                if (sumRows > toMatch || sumColumns > toMatch){
                    return false;
                }
            }
            if (sumRows != toMatch || sumColumns != toMatch){
                return false;
            }
        }
        return true;
    }
    public static boolean checkDiagonals (int [] matrix, int n, int toMatch){
        int sumDiag1 = 0;
        int sumDiag2 = 0;
        int value1, value2;
        int n0 = 0;
        for (int i = 0; i < n; i++){
            value1 = i * (n + 1);
            value2 = n0 + (n - 1);
            sumDiag1 += matrix[value1];
            sumDiag2 += matrix[value2];
            if (sumDiag1 > toMatch || sumDiag2 > toMatch){
                return false;
            }
            n0 = value2;
        }
        if (sumDiag1 == toMatch && sumDiag2 == toMatch) {
            return checkRowsColumns(matrix,n,toMatch);
        }
        return false;
    }
    public static int getToMatch (int n){
        return (n *( ( n * n ) + 1))/2;
    }

    public static void main (String [] args){
       int n = 4;
        int [] matrix = new int[n * n];
        int toMatch = getToMatch(n);
        double combinations = 0;
        do {
            fillWithAllowed(matrix);
            combinations++;
            if ((combinations % 10_000_000) == 0) {
                System.out.print("Tested combinations: " + combinations + "\n");
            }
        } while (!(checkDiagonals(matrix, n, toMatch)));
        System.out.print("Result: \n");
        printSquareMatrix(matrix, n);
    }
}

