/************************************************************************
 * @file Proj1.java
 * @brief This program implements java to take in command line args and initialize the file parser
 * @author Dylan Kane
 * @date September 25, 2025
 *************************************************************************/

import java.io.IOException;

public class Proj1 {
    public static void main(String[] args) throws IOException{
        if(args.length != 1) {
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser(args[0]);
    }
}