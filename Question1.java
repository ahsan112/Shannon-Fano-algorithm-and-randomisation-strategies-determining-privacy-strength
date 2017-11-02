import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ahsan
 *
 */
public class Question1 {

    public static void main(String args[]) throws IOException {

        //Calling the Histogram method to print the histogram of the character occurence
        Question1.HistogramAndProbability("cityData.dat");

        // assinging the character with their bits obtained from the shano fano algorithn
        char[] letters = {'r'  , 'd'  , 'e'  , 'i'   , 'a'   ,'s'   ,'o'   ,'b'   ,'l'   ,'n'   ,'f'   ,'h'    ,'t'    ,'m'    ,'g'};
        String[] SFAssign = {"000", "001", "010", "0110", "01111","1000","1001","1010","1011","1100","1101","11100","11101","11110","11111"};

        // String to encode / compress
        String origStr = "abilondonibabirminghamronnleedsransheffieldrinbradfordsetbristoltesaleedsabirsheffieldmirbradfordbagirlondondinobirminghamhalleedssibsheffielddinaleedsharsheffieldmooresbradfordrambristolforestlondonthesabirminghambastileedsmattsheffielddaarbradfordtonibristoldresbradford";

	      char[] charOrigStr = origStr.toCharArray();



        // Encoded sequence
        String SFEncOrigStr = Encode(origStr, letters, SFAssign);

        // To decompress the coded String and print out to the file
        Question1.Decode(SFEncOrigStr, letters,SFAssign);

    }


    /**
     *
     * Method for Printing the occurence of string in a histogram with the probability
     *
     * @param file
     * @throws java.io.FileNotFoundException
     **/


    public static void HistogramAndProbability(String file) throws FileNotFoundException, IOException {

         // To read from input file and write to output file.

        /**
         * Buffer reader used to read the contents of the file which is being read
         * PrintWrite is used to write to the output file saved as testing.dat
         **/

        BufferedReader fileIn = new BufferedReader(new
        FileReader(file));
        PrintWriter writeOut = new PrintWriter("HistogramWithProbability.dat");



         //A try block used to catch any exceptions/

        try{
            // Constructing string builder for the file

            StringBuilder stTextbd = new StringBuilder();

            //assinging the String builder to the first line of the file being read

            String stline = fileIn.readLine();

            // while the stringbuilder is not null meaning that there is still contents to
            // reed exectue the while loop;
            // inside the while loop stTextbd appends the text i.e joins them together when the line is read
            // insert a new line

            while (stline != null) {
                stTextbd.append(stline);
                stTextbd.append("\n");
                stline = fileIn.readLine();
            }

           // Verify that string from the input file has been assigned to
           // variable stText

           String readFile = stTextbd.toString();
           readFile = removeAll(readFile);


           // Convert strng to array of characters for sorting
           // then user the sort method to sort the characters

           char[] charArray = readFile.toCharArray();
           Arrays.sort(charArray);

           // going to be used to calculate the probability distribution
           double dis = 0;
           double Tdis = 0;


             // Define two Array Lists to record unique characters
            // and their occurrence
           // we use this arraylist becasue we dont need to
           // define a fixed size which is important as we dont know the amount of characters

           List<Character> uniqueCharList = new ArrayList<Character>();
           List<Integer> uniqueCharCount = new ArrayList<Integer>();

           // Identify by going through the array of characters

           // use this int variable to go throught the index of
           // of the array
           int k = 0;

           // while the value of k is less than the value of the length of the file
           // execute the loop
           while (k < readFile.length()){

            // Start with character at index k
           char temp = charArray[k];

           // add the character to the uniqueCharList Array
           uniqueCharList.add(temp);

           // Find the number of occurrence
           // count is equal the length of the file - the length of the file when the
           // value of temp is replaced with ""
           // replace temp with "" in the readFile then get the length
           // minus the readFile.length with the readFile with the replace temp length

           int count = readFile.length() - readFile.replace(String.valueOf(temp), "").length();
           uniqueCharCount.add(count);


           Tdis += (double)count / (double)readFile.length();
           dis  = (double)count / (double)readFile.length();

           writeOut.write(temp +" Occurs " + count + " Times " + star(count)+ "\n");
           writeOut.write("Probability Disribution : " + String.format( "%.3f", dis ) + "\n");
           writeOut.write("\n");

           // Why do we increment k by count?
           // we increment k by k + count becuase the char are in order
           // and count is the number of those characters therefore we have to
           // incremnt using the count to get the next value of the char
           // for example a b b b c c in an array k is at index b
           // count is equal 3 when we increment by k + count we move to
           // the next unkown value
           k = k + count;

           }

           writeOut.write("Total Probability Distribution: " + String.format( "%.3f", Tdis ));




       }
       finally{
            // closing the filein and writeOut
            fileIn.close();
            writeOut.close();
        }

    }


    /**
     *
     * the star method used to print out the astrix for the histogram
     *
     * @param count
     * @return
     **/

    public static String star(int count){

        String s = "";
        for(int i=0; i< count; i++){
            s += "*";
        }

        return s;
    }


    /**
     *
     * method used to remove the \n and - from the string.
     *
     * @param string
     * @return
     **/

    public static String removeAll(String string) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
                char ch = string.charAt(i);

                // Allow only letters
                if (Character.isLetterOrDigit(ch)) {
                       stringBuilder.append(ch);
                }
        }

        return stringBuilder.toString();
    }


     /**
     *
     * METHOD TO ENCODE USING SHANNON FANO
     *
     * @param str
     * @param letters
     * @param bits
     * @return
     * @throws java.io.FileNotFoundException
     */


    public static String Encode(String str, char[] letters, String[] bits) throws FileNotFoundException{

            PrintWriter writeOut = new PrintWriter("compRes.dat");


        try {
            /**
             * creating a char array to store the incoming string into characters.
             **/

            char[] charStr = str.toCharArray();

            /** creating a String variable to store the encoded string. */

            String SFEncOrigStr = "";


            /** Looping through the Char array
             *  if the integer i is less than the length of the char array increment i
             *  this loop will continue to execute until i is not less than the length
             *  of the char array.
             */

            for(int k = 0; k < charStr.length; k++){

                /** Calling the searchChar method and assign its return value to the int variable r. */
                int r = searchChar(letters, charStr[k]);

                /**The string value is equal the string value plus the value of the element in the bist array at index r. */
                SFEncOrigStr = SFEncOrigStr + bits[r];

            }
            writeOut.print(SFEncOrigStr);
            return SFEncOrigStr;
        }
            /**Returning the encoded String value. */
        finally {
            writeOut.close();
        }

    }


     /******* END OF THE ENCODING METHOD. ******/



    /**
     *
     * METHOD TO DECODE USING SHANNON FANO
     *
     * @param encBits
     * @param letters
     * @param bits
     *
     * @throws java.io.FileNotFoundException
     */


    public static void Decode(String encBits, char[] letters, String[] bits) throws FileNotFoundException{

        PrintWriter Out = new PrintWriter("decompRes.dat");

        try{
            /**Declaring a String value to hold the decoded String. */
            String decompStr = "";

            /**declaring a int variable k which will be used for the while loop condition. */
            int k = 0;

            /** declaring a while loop with the condition of whether the value of k is less than the
             *  length of the encoded String while K is less than the length execute the while loop.
             */


            while(k < encBits.length()){

                // asinging temp to the value of the encbits with substring of k too k+3
                // this means temp is == the value k too k+3 character.

                String temp = encBits.substring(k,k+3);

                // r is equal to the value return value of the searchString method
                int r = searchString(bits, temp);

                //
                // assing x to the value of r to be used later
                int x = r;

                //if the value of r < than 0 execute the contents of the if stamtent
                if (r < 0){

                   // temp is equal the charactes of encbit at k to k+ 4 posistion
                   temp = encBits.substring(k,k+4);
                   // assing the return value of searchString to r
                   r = searchString(bits, temp);
                   // x is == r
                   x = r;


                   // if the value of x is less than 0 exeute
                   // this means than the value of k to k+4 was not found
                   if(x < 0) {

                       // temp is equal the charactes of encbit at k to k+ 5 posistion
                        temp = encBits.substring(k,k+5);
                        // assing the return value of searchString to r
                        r = searchString(bits, temp);

                        // the decompString is = the decomStr + the value index r from
                        // letters array
                        decompStr = decompStr + String.valueOf(letters[r]);
                        // increment k by k + 5
                        // we increment by k + 5 as the bit length was 5 therefore
                        // to move to the next bit we have to incremtent by k + 5.
                        k = k + 5;

                    }

                   // if x is not less than 0 which means the value of temp was found
                   // execute the contents of the else
                   else{

                        // the decompString is = the decomStr + the value index r from
                        // letters array
                        decompStr = decompStr + String.valueOf(letters[r]);
                        // we increment by k + 4 as the bit length was 4 therefore
                        // to move to the next bit we have to incremtent by k + 4.
                        k = k + 4;

                   }

                }


                // if r is not less than 0 execute the contents of the else
                // this means that temp was equal encBits.substring(k,k+3);
                else {

                    // the decompString is = the decomStr + the value index r from
                    // letters array
                    decompStr = decompStr + String.valueOf(letters[r]);

                    // we increment by k + 3 as the bit length was 3 therefore
                    // to move to the next bit we have to incremtent by k + 3.
                    k = k + 3;

                }

            }

            Out.write(decompStr);
        }

        finally {
            Out.close();
        }

    }

    /******* END OF THE DECODING METHOD. ******/




    /**
     *
     * METHOD TO SEARCH FOR STRING
     *
     * @param array
     * @param val
     * @return
     *
     **/


    // Method to search a particular string inside of array of strings
    // return the first index of the string found
    // otherwise return -1
    public static int searchString(String[] array, String val) {

            int ind = -1;

            for(int i=0; i<array.length; i++) {
                if(array[i].equals(val)){
                    ind = i;
                break;
                }
            }

            return ind;
    }


     /******* END OF THE SEARCH STRING METHOD ******/



    /**
     *
     * METHOD TO SEARCH FOR CHAR
     *
     * @param array
     * @param val
     * @return
     */


    // Method to search a particular char inside of array of characters
    // return the first index of the character found
    // otherwise return -1
    public static int searchChar(char[] array, char val) {

        int ind = -1;

        for(int i=0; i<array.length; i++) {
            if(array[i] == val){
            ind = i;
            break;
            }
        }
        return ind;
    }



}
