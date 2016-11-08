//Wrapper class for question 3
import java.io.*;
import java.util.*;
import java.lang.String;
import java.lang.Math;

class Cwk2Question3
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
            int len = strVector.length;

            Complex[] pValues = new Complex[len];
            
            for(int i=0; i<len; i++)
            {
                String[] comp = strVector[i].split(",");
                double re = Double.parseDouble(comp[0]);
                double im = Double.parseDouble(comp[1]);
                pValues[i] = new Complex(re,im);
            }

            Complex[] invFFT = FFT.IFFT(pValues);
            FFT.normalize(invFFT);
            
            for(int i=0; i<invFFT.length-1; i++)
            {
                System.out.print(invFFT[i].toString() + " ");
            }
            System.out.println(invFFT[invFFT.length-1].toString());
                         
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
