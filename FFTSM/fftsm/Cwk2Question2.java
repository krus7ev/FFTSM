//Wrapper class for question 2
import java.io.*;
import java.util.*;
import java.lang.String;
import java.lang.Math;

class Cwk2Question2
{
    public static void main (String[] args) throws IOException
    {
        try
        {
            if (args.length != 1)
            {
                System.out.println("Input Error: One input file required!");
                System.exit(1);
            }
                
            File inputFile = new File(args[0]);
            FileReader reader = new FileReader(inputFile);            
            BufferedReader buffer = new BufferedReader(reader);         
            
            String lineIn;
            lineIn = buffer.readLine();
            
            if(lineIn == null || buffer.readLine() != null)
            {
                System.out.println("Input Error: One line of input required!");
                System.exit(1);
            }
            
            String[] strVector = lineIn.split("\\s+");
            double[] coefVector = new double[strVector.length];
            for(int i=0; i<strVector.length; i++)
            {
                coefVector[i] = Double.parseDouble(strVector[i]);
            }

            Complex[] pointValues = FFT.FFT(coefVector);
            for(int i=0; i<pointValues.length-1; i++)
            {
                System.out.print(pointValues[i].toString() + " ");
            }
            System.out.println(pointValues[pointValues.length-1].toString());
                         
        }
        catch (IOException e)
        {
            System.out.println("Input Error: Reading from file");
        }
        catch (NumberFormatException e)
        {
            System.out.println("Input Error: Number format");
        }
    }
}
