import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = readValidInt(scanner);

        System.out.print("Enter number of columns: ");
        int columns = readValidInt(scanner);

        double[][] matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                System.out.print("Entry at row " + (i + 1) + ", column " + (j + 1) + ": ");
                matrix[i][j] = readValidDouble(scanner);
            }
        }

        System.out.println("\nOriginal matrix:");
        printMatrix(matrix);

        GaussJordanReducer.reduce(matrix);

        System.out.println("Reduced matrix (RREF):");
        printMatrix(matrix);

        scanner.close();
    }

    public static int readValidInt(Scanner scanner)
    {
        while (true)
        {
            try
            {
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.print("Invalid input. Please enter a whole number: ");
            }
        }
    }

    public static double readValidDouble(Scanner scanner)
    {
        while (true)
        {
            try
            {
                return Double.parseDouble(scanner.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public static void printMatrix(double[][] matrix) 
    {
        for (int i = 0; i < matrix.length; i++) 
        {
            for (int j = 0; j < matrix[0].length; j++) 
            {
                double value = matrix[i][j];

                if (Math.abs(value) < GaussJordanReducer.getTolerance())
                {
                    value = 0.0; 
                }

                System.out.printf("%10.3f ", value);
            }

            System.out.println();
        }

        System.out.println();
    }
}