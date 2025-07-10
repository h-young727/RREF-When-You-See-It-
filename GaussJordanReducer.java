public class GaussJordanReducer 
{
    private static final double TOLERANCE = Math.pow(10, -7.27);

    public static double getTolerance()
    {
        return TOLERANCE;
    }

    public static void reduce(double[][] matrix)
    {
        int currentRow = 0;
        int pivotIndex = 0;

        while (currentRow < matrix.length && pivotIndex < matrix[0].length)
        {
            if (Math.abs(matrix[currentRow][pivotIndex]) < TOLERANCE)
            {
                boolean pivotFound = false;

                for (int rowIndex = currentRow + 1; rowIndex < matrix.length; rowIndex++) 
                {
                    if (Math.abs(matrix[rowIndex][pivotIndex]) >= TOLERANCE) 
                    {
                        swapRow(matrix, currentRow, rowIndex);
                        pivotFound = true;
                        break;
                    }
                }

                if (!pivotFound) 
                {
                    pivotIndex++;
                    continue;
                }
            }

            double pivotValue = matrix[currentRow][pivotIndex];
            scaleRow(matrix, 1.0 / pivotValue, currentRow);

            for (int rowIndex = currentRow + 1; rowIndex < matrix.length; rowIndex++)
            {
                double valueBelowPivot = matrix[rowIndex][pivotIndex];
                subtractRow(matrix, valueBelowPivot, currentRow, rowIndex);
            }

            currentRow++;
            pivotIndex++;
        }

        for (int pivotRow = matrix.length - 1; pivotRow >= 0; pivotRow--) 
        {
            int pivotColumn = -1;
            
            for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++) 
            {
                if (Math.abs(matrix[pivotRow][columnIndex] - 1.0) < TOLERANCE) 
                {
                    pivotColumn = columnIndex;
                    break;
                }
            }
            
            if (pivotColumn == -1) 
            {
                continue;
            }
            
            for (int rowAbove = pivotRow - 1; rowAbove >= 0; rowAbove--) 
            {
                double valueAbovePivot = matrix[rowAbove][pivotColumn];
                subtractRow(matrix, valueAbovePivot, pivotRow, rowAbove);
            }
        }
    }

    public static void swapRow(double[][] matrix, int row1, int row2)
    {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    public static void scaleRow(double[][] matrix, double scalar, int targetRow)
    {
        for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++)
        {
            matrix[targetRow][columnIndex] *= scalar;
        }
    }

    public static void subtractRow(double[][] matrix, double scalar, int sourceRow, int targetRow)
    {
        for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++)
        {
            matrix[targetRow][columnIndex] -= scalar * matrix[sourceRow][columnIndex];
        }
    }
}