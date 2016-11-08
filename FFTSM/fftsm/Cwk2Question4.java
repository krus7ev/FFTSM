//Wrapper class for question 4
import java.io.*;
import java.util.*;
import java.lang.String;
import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class Cwk2Question4
{
    public static void main (String[] args) 
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

            double[] sqrVector = FFT.square(coefVector);
            int len = sqrVector.length;
            for(int i=0; i<len-1; i++)
            {
                if (sqrVector[i] == (int)sqrVector[i])
                    System.out.print((int)sqrVector[i] + " ");
                else
                    System.out.print(sqrVector[i] + " ");
            }
            if (sqrVector[len-1] == (int)sqrVector[len-1])
                System.out.println((int)sqrVector[len-1]);
            else
                System.out.println(sqrVector[len-1]);
                         
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
