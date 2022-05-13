import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixTester {
    private void fillWithAllowed (int [] matrix){
        //generates a vector of allowed values
        List<Integer> allowedList = IntStream.range(1, matrix.length + 1).boxed().collect(Collectors.toList());
        Collections.shuffle( allowedList );
        for (int i = 0; i < matrix.length; i++){
            matrix[i] = allowedList.get(i);
        }
        //fills the matrix in a random order with allowed values
    }
    private  boolean checkRowsColumnsDiagonals(int [] matrix, int n, int toMatch){ //checks the columns, rows and diagonals
        //values needed for rows and columns
        int sumRows;
        int sumColumns;
        //values needed for diagonals
        int sumDiag1 = 0;
        int sumDiag2 = 0;
        int value1, value2;
        int n0 = 0;
        //prevents checking the diagonals of the same matrix more than once
        boolean checkedDiagonals = false;
        //max allowed value
        int maxAllowed = matrix.length + 1;

        for (int i = 0; i < n; i++){

            sumRows = 0;
            sumColumns = 0;
            for (int j = 0; j < n; j++){
                sumRows += matrix[i*n + j];
                sumColumns += matrix[i + j*n];
                //check if upper or lower bound is crossed
                if (sumRows > toMatch || sumColumns > toMatch || (sumRows + (n - j-1)*maxAllowed) < toMatch ||  (sumColumns + (n - j - 1)*maxAllowed) < toMatch){
                    return false;
                }
                if (!checkedDiagonals){
                    value1 = j * (n + 1);
                    value2 = n0 + (n - 1);
                    sumDiag1 += matrix[value1];
                    sumDiag2 += matrix[value2];
                    if (sumDiag1 > toMatch || sumDiag2 > toMatch || (sumDiag1 + (n - j-1)*maxAllowed) < toMatch ||  (sumDiag2 + (n - j - 1)*maxAllowed) < toMatch){
                        return false;
                    }
                    n0 = value2;
                }


            }
            if (sumRows != toMatch || sumColumns != toMatch || sumDiag1 != toMatch || sumDiag2 != toMatch ){
                return false;
            }
            checkedDiagonals = true;
        }
        return true;
    }
    private int  getToMatch (int n){
        return (n *( ( n * n ) + 1))/2;
    }

    public int [] startTest(int n, GUI gui){
        MatrixTester tester = new MatrixTester();
        return tester.testMatrix(n, gui);
    }

    private int [] testMatrix (int n, GUI gui){
        int [] matrix = new int[n * n];
        int toMatch = getToMatch(n);
        double combinations = 0;
        gui.setCombinations (combinations);
        do {

            fillWithAllowed(matrix);
            combinations++;

        } while (!(checkRowsColumnsDiagonals(matrix, n, toMatch)));
        gui.setCombinations (combinations);
        return matrix;
    }


}
