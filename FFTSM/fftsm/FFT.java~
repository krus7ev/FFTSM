//package fftsm;

import java.io.*;
import java.util.*;
import java.lang.String;
import java.lang.Math;


public class FFT
{
    public static void main(String[] args) throws IOException 
    {
        //Some initial tests on the FFT, IFFT and squaring
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
            
            System.out.println("A[x]:" + Arrays.toString(coefVector));
            System.out.println();

            Complex[] pointValues = FFT(coefVector);            
            System.out.println("FFT:" + Arrays.toString(pointValues));
            
            Complex[] invFFT = IFFT(pointValues);
            normalize(invFFT);
            
            System.out.println("IFFT:" + Arrays.toString(invFFT));
            System.out.println();
            
            double[] copyVect = square(coefVector);
            System.out.println("A[x] squared:" + Arrays.toString(copyVect));
                                    
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
    
    //The FFT - evaluate recursively at complex roots of unity 
    public static Complex[] FFT (double[] A)
    {
        int N = A.length;
        
        //Base case for the recursion
        if (N==1)
        {
            Complex[] y = new Complex[1];
            y[0] = new Complex(A[0], 0);  
            return y;
        }
        
        Complex omega1 = new Complex(Math.cos(2*(Math.PI)/N), Math.sin(2*(Math.PI)/N)); 
        Complex omega = new Complex(1,0);
        
        double[] A0 = new double[A.length/2];
        double[] A1 = new double[A.length/2];
        split(A, A0, A1);
        
        Complex[] y0 = FFT(A0);
        Complex[] y1 = FFT(A1);
                
        Complex[] y = new Complex[N];
        for (int k=0; k<N/2; k++)
        {
            y[k] = y0[k].plus(omega.times(y1[k]));
            y[k+N/2] = y0[k].minus(omega.times(y1[k]));
            omega = omega.times(omega1);
        }
        return y;                       
    }
    
    //Reassemble a the Coefficient Form of a polynomial from a vector of values
    //Since this is a recursive procedure, they still need to be divided by N
    public static Complex[] IFFT (Complex[] Y)
    {
        int N = Y.length;
        if (N==1)
        {  
            return Y;
        }
        
        Complex omega1 = new Complex(Math.cos(2*(Math.PI)/N), Math.sin(2*(Math.PI)/N)); 
        Complex omega = new Complex(1,0);
        
        Complex[] Y0 = new Complex[Y.length/2];
        Complex[] Y1 = new Complex[Y.length/2];
        split(Y, Y0, Y1);
        
        Complex[] a0 = IFFT(Y0);
        Complex[] a1 = IFFT(Y1);
                
        Complex[] a = new Complex[N];     
        for (int k=0; k<N/2; k++)
        {
            a[k] = a0[k].plus((omega.conjugate()).times(a1[k]));
            
            a[k+N/2] = a0[k].minus((omega.conjugate()).times(a1[k]));
            omega = omega.times(omega1);
        }
        
        return a;                       
    }
    
    //Iterate through the output coeffs of IFFT and divide reals by length N
    public static void normalize(Complex[] A)
    {
        int N = A.length;
        for (int q=0; q<N; q++)
        {
            A[q] = new Complex(A[q].getReal()/N, A[q].getImagine());
        }
    }
    
    //Method to split an array of double into odd and even members
    private static void split (double[] A, double[] A0, double[] A1)
    {        
        for (int i=0; i<A.length; i++)
        {
            if(i%2 == 0)
                A0[i/2] = A[i];
            else
                A1[i/2] = A[i];
        }        
    }
    
    //Method to split an array of Complex into odd and even members
    private static void split (Complex[] Y, Complex[] Y0, Complex[] Y1)
    {        
        for (int i=0; i<Y.length; i++)
        {
            if(i%2 == 0)
                Y0[i/2] = Y[i];
            else
                Y1[i/2] = Y[i];
        }        
    }
    
    //Method to square a polynomial using the FFT & IFFT
    public static double[] square (double[] A)
    {
        int N = A.length;
        double[] polly2N = new double[2*N];
        Arrays.fill(polly2N, 0);
        System.arraycopy(A, 0, polly2N, 0, N);
        
        Complex[] pollyFFT = FFT(polly2N);
        Complex[] fftSQRD = new Complex[2*N];
        
        for (int i=0; i<2*N; i++)
        {
            fftSQRD[i] = pollyFFT[i].times(pollyFFT[i]);
        }
        
        Complex[] pollySQRD = IFFT(fftSQRD);
        normalize(pollySQRD);
        
        
        double[] polly = new double[2*N-1];               
        for (int i=0; i<2*N-1; i++)
        {
            polly[i] = pollySQRD[i].getReal() * Math.pow(10,6);
            polly[i] = Math.round(polly[i]) / Math.pow(10,6);       
        }
        
        return polly;
    }
    
    //Multiply two polynomials for the purposes of string matching
    public static double[] multiply (double[] A, double[] B)
    {
        int N = A.length;
        
        double[] a2N = new double[2*N];
        Arrays.fill(a2N, 0);
        System.arraycopy(A, 0, a2N, 0, N);
        
        double[] b2N = new double[2*N];
        Arrays.fill(b2N, 0);
        System.arraycopy(B, 0, b2N, 0, N);
             
        Complex[] aFFT = FFT(a2N);
        Complex[] bFFT = FFT(b2N);
        
        Complex[] fftProd = new Complex[2*N];
        
        for (int i=0; i<2*N; i++)
        {
            fftProd[i] = aFFT[i].times(bFFT[i]);
        }
        
        Complex[] abProd = IFFT(fftProd);
        normalize(abProd);
        
        double[] outPut = new double[N];
               
        for (int i=0; i<N; i++)
        {
            outPut[i] = abProd[i].getReal() * Math.pow(10,6);
            outPut[i] = Math.round(outPut[i]) / Math.pow(10,6);       
        }
        
        return outPut;
    }  
}
