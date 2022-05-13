import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class start {
    private static void printSquareMatrix (int [] matrix, int n){ //prints to console a matrix of nxn
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

    public static void main (String [] args){
        GUI myGuy = new GUI();
    }
}

