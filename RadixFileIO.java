

/*
name: Elaine Gombos
date: 9/22
description: The program will convert from octal or hexademical or binary 
        to decimal. It also converts from decimal into binary.
the inputs, which includes 8 numbers in either binary, octal, decimal, or
hexidecimal, will all be saved ahead of time in a file called "integers.txt." - 
this avoids errors with typing things into council. - each of the 8 numbers is
preceeded by a letter character that represents the base.
*/

package radixfileio;


import cs.ssa.*;

public class RadixFileIO 
{
    static InFile input;
    
    
   
    public static void main(String[] args) 
    {
        //introducing and welcoming the user to my program
        System.out.println("welcome to Elaine's radix convertor!! :D");
        System.out.println("All numbers this program converts is pre-saved in"
                + " the file named 'integers.txt'.");
        System.out.println("Please edit the file to "
                + "convert different numbers.");
        
        //telling the program to read the numbers in the integers.txt file
        input = new InFile("integers.txt");
        
        //converts numbers 8 times for 8 numbers
        for (int count=1; count <= 8; count ++)
        {
            convertNumber();
        }
    }
    
    
    //below, I use char types bc when u get to converting hex,
    //can't read int bc doesn't include A, B, etc. Thus, int is too limited. 
    
    
    //identifies the base of the number being converted and then directs
    //the program to a set of instructions based on that.
    static void convertNumber()
    {
        char base = input.read(); //reads the first character, which tells you
        //the base of the number you're converting
        
       
        if (base == 'b')
        {
            binaryToDecimal();
        }
        
        if (base == 'h')
        {
            hexToDecimal();
        }
        
        if (base=='o')
        {
            octToDecimal();
        }
        
        if (base=='d')
        {
            decimalToBinary();
        }
        
    }
    
    
    
    
    //the programs below look at one line in my input file and converts it
    //note that knowing the base of the number in the file also tells the 
    //program what base to convert it to, since this program only has one
    //direction for each base (ie. binary, hex, oct to decimal; decimal to 
    //binary).
    //it has already read the first character (which is the base). all the 
    //chars following the base are digits
    
    //with binary conversion, read through each digit from left to right and
    //multiply by two each time you move a place value to the right. If that
    //digit is a 1, then you add 1 before doubling and moving to the next digit.
    static void binaryToDecimal()
    {
        int total = 0;
        char digit;
        digit = input.read();
        while (digit !='\n') //the program knows it is done converting one 
            //number when it detects \n (line break)
        {
            total = total * 2;
            if (digit == '1') //needs quotes here bc you are reading a 
                //character.  bc in computer, 1 you type on computer is stored
                //as 49. ASCII 
                total = total + 1;
            digit = input.read();
        }
        System.out.println(total+" (binary converted to decimal)");
    }
    
    
    //hex to decimal conversion is similar to binary to decimal, expect we are
    //now multiplying by 16 every time we move a place value to the right and.
    //adding the next digit. there are also more symbols than 0 and 1 to 
    //consider. 
    static void hexToDecimal()
    {
        int total = 0;
        char digit;
        digit = input.read();
        while (digit !='\n')
        {
            //here, you have to account for the different in ASCII values for
            //the letters and numbers in a hex number, as they are different.
            if ('A'<=digit) 
            {
                total = total*16;
                total=total+digit -'A'+10; //+10 because A is worth 10 in decimal
            }
           else   
            {
                total = total * 16;
                total = total + (digit - '0');
            }
            
            digit=input.read();

        }
        System.out.println(total+" (hexadecimal converted to decimal)");
        
    }
    
    //oct to decimal conversions follow the same pattern: multiply by 8
    //then add the next digit. 
    static void octToDecimal() 
    {
        int total = 0;
        char digit;
        digit = input.read();
        while (digit !='\n')
        {
           
            total = total * 8;
            total = total + (digit - '0');
            digit=input.read();

        }
        System.out.println(total+" (octal converted to decimal)");
        
    }
    
    
    //process for dec to binary conversion: if the number is odd, you write a 1 
    //to the right-most place value of ur binary number. If even, write a 0.
    //then, divide the dec number by 2 and repeat the even/odd step with the
    //binary place value to the left.
    static void decimalToBinary()
    {
        int number;
        int binary;
        String answer = new String ("");
        
        number=input.readInt(); //to do this, we now need to read the entire
        //number in the file and not individual characters. so the data type
        // is int
            while (number>0)
            {
                binary = (int) number%2; //mod 2 tells us if the number is 
                // even or odd. number mod2 = 0 means number was even. 
                //=1 means odd.
                
                if (binary==1)
                {
                    answer = ("1"+answer); //defining a string like this and
                    //adding the digits as such allows the binary number to be
                    //built from right to left.
                }
                if (binary==0)
                {
                    answer=("0"+answer);
                }
                
                number = (int) number/2;

            }
            System.out.println(answer+" (decimal converted to binary)");
            
    }
            
    
}
