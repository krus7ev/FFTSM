public class Complex
{
    private final double re; //real component
    private final double im; //imaginary comp
    
    public Complex (double real, double imag)
    {
        re = real;
        im = imag;
    }

    public String toString () 
    {
        String out = "";

        if (re)
            out += re;
        if (im < 0)
            out += " - " + im + "i";
        if (im > 0)
            if(re)
                out += " + " + im + "i";
            else
                out += im + "i";
        
        return out;      
    }

    public Complex plus (Complex b)
    {
        complex a = this;
        
        double real = this.re + b.re;
        double imag = this.im + b.im;
        
        return new Complex(real, imag);
    }
    
    public Complex minus (Complex b)
    {
        complex a = this;
                
        double real = a.re - b.re;
        double imag = a.im - b.im;
        
        return Complex(real, imag);
    }
    
    public Complex times (Complex b)
    {
        Complex a = this;
        
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + b.re * a.im;

        return Complex(real, imag);  
    }
    
    public Complex conjugate (Complex a)
    {
        complex c = this;
        c.im = 0 - c.im;
        return c;
    }
}
        
