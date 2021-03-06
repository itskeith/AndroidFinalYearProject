package com.example.keith.finalyearproject;

import android.graphics.drawable.Drawable;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/

/**
 * Activity called for information from other reference activities and fragments
 */
public class ReferenceInfo {

    public static final String[] GATES =
            {
                    "AND",
                    "OR",
                    "NOT",
                    "NAND",
                    "NOR",
                    "XOR",
                    "XNOR",
                    "Decimal",
                    "Binary",
                    "Octal",
                    "Hexadecimal",
                    "1's complement",
                    "2's complement",
                    "Fixed point arithmetic",
                    "Floating point arithmetic"

            };

    /*
    Array containing logic gate images
     */
    public static final int[] GATESPIC =
            {
                    R.drawable.and,
                    R.drawable.orgate,
                    R.drawable.not,
                    R.drawable.nandgate,
                    R.drawable.norgate,
                    R.drawable.xorgate,
                    R.drawable.xnor,
                    R.drawable.decimal,
                    R.drawable.binary,
                    R.drawable.octal,
                    R.drawable.hexadecimal,
                    R.drawable.onescomplement,
                    R.drawable.twoscomplement,
                    R.drawable.fixedpoint,
                    R.drawable.floatingpoint

            };

    public static final int[] GATESTT =
            {
                    R.drawable.andtable,
                    R.drawable.ortable,
                    R.drawable.nottable,
                    R.drawable.nandtable,
                    R.drawable.nortable,
                    R.drawable.xortable,
                    R.drawable.xnortable,
                    R.drawable.base10,
                    R.drawable.base2,
                    R.drawable.base8,
                    R.drawable.base16,
                    R.drawable.onescomplementpicture,
                    R.drawable.twoscomplementpicture,
                    R.drawable.fixedpoinpicture,
                    R.drawable.floatingpointpicture
            };

    public static final String[] TEXTINFO =
            {
                    "The AND gate typically takes in 2 inputs, usually reffered to as A and B. \n" +
                            "For the AND gate if both inputs are a 1, then the output will be a 1," +
                            "otherwise the output returns a 0.",
                    "The OR gate typically takes in 2 inputs, usually reffered to as A and B. \n" +
                            "The OR gate returns a 1 if any of its input's are a 1, it is only a 0 " +
                            "if both of its inputs are 0.",
                    "The NOT gate or inverter typically only takes in one input and inverts it.\n" +
                            "So if a 1 passes into a NOT gate then a 0 will be the output and vice versa.",
                    "The NAND gate typically takes in 2 inputs, usually reffered to as A and B. \n" +
                            "The NAND gate behaves opposite to the AND gate, hence the name NAND" +
                            "(Not AND). So anywhere you would expect a 1 from an AND gate you" +
                            " instead get a 0 from a NAND, likewise when you would expect a 0 from " +
                            "an AND gate the NAND returns a 1.",
                    "The NOR gate typically takes in 2 inputs, usually reffered to as A and B. \n" +
                            "The NOR gate behaves the opposite to the OR gate, hence the name NOR(Not OR)" +
                            "Anywhere you would normally expect a 1 or 0 to be the output of an OR " +
                            "gate is instead the opposite, so a NOR gate only returns a 1 when there" +
                            "are only 0's on the inputs.",
                    "The XOR gate typically takes in 2 inputs, usually reffered to as A and B. \n" +
                            "The XOR gate behaves simlar to the OR gate except it only outputs a " +
                            "one when the inputs have opposite values i.e. a 1 and a 0, hence the name " +
                            "XOR(Exclusive OR).",
                    "The XNOR gate typically takes in 2 inputs, usually reffered to as A and B. \n" +
                            "The XNOR gate behaves simlar to the NOR gate except it outputs a 1 when" +
                            "there are two 1's or two 0's as the inputs, hence the name XNOR (Exclusive Not OR)",
                    "Decimal is a base 10 number system \n" +
                            "The decimal system goes from 1 to 9, A decimal number is the sum of its " +
                            "digits multiplied with 10 to the power of n (with n being the position of the digit)" +
                            "",
                    "Binary is a base 2 number system \n" +
                            "The binary system goes from 0 to 1. A binary number is the sum of its " +
                            "digits multiplied with 2 to the power of n",
                    "Octal is a base 8 number system \n" +
                            "The binary system goes from 0 to 7. An octal number is the sum of its " +
                            "digits multiplied with 8 to the power of n",
                    "Hexadecimal is a base 16 number system \n" +
                            "The hexadeicmal system goes from 0 to F (With A as the first digit after 9)." +
                            " A hexadecimal number is the sum" +
                            "of its digits multiplied with 16 to the power of n\n" +
                            "A in hexadecimal represents a 10 in decimal and F represents 15\n" +
                            "a 10 in hexadecimal represents 16 in decimal and 100 represents 256",
                    "One's complement is a way of representing binary numbers as negative values.\n" +
                            "This involves taking the binary value and inverting the bits so that a 3 typically represented as " +
                            "0101 would instead be represented as 1010 for -3",
                    "Two's complement is another way of representing binary numbers as negative values that is similar to one's complement.\n" +
                            "The difference between one's and two's complement is that a 1 is added to the one's complement" +
                            "so if -3 is 1010 in one's complement then it would be 1011 in two's complement",
                    "Fixed point arithmetic, is a means of expressing a fractional number in an indirect" +
                            "manner by using scaling, so that a number such as 1.23 would instead be represented as 1230" +
                            ",this would be a scaling factor of 1000.",
                    "Floating point is a similar representation to scientific notation, floating point approximates a real number in a trade-off between the size of the number that can be used" +
                            "and the precision of that number",
                    "",

            };
}
