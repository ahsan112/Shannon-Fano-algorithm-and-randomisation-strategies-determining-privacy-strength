import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author Ahsan
 *
 *
 */
public class Question2 {

    public static void main(String args[]) throws FileNotFoundException {

        String[] cities = {"London", "Birmigham", "Leeds", "Sheffield","Bradford", "bristol"};

        // city subsitution
  	    int[] y = {1,2,3,4,5,6};

        //masked city sub
        int[] x = new int[6];


        // calling the methods
        Question2.Uniform(cities, y, x);
        Question2.NonUniFormed(cities, y, x);

    }



     /**
      *
      * The Uniform method of randomization
      *
     * @param cities
     * @param x
     * @param y
     * @throws java.io.FileNotFoundException
     *
      **/


    public static void Uniform(String[] cities , int[] x , int[] y) throws FileNotFoundException {

        // creating the writeout object to use to print out the unifomend data into a file
        PrintWriter writeOut = new PrintWriter("anonCityUni.dat");

        try {
            //creating a random generator and assinging a seed to it
            Random random = new Random();
            long seed = System.currentTimeMillis();
            random.setSeed(seed);

    	      writeOut.write("Cities\t\t" + "Masked city" + "\n" + "\n");


            // declaring a for loop to go through the city sub array
            for(int i = 0; i < y.length; i++) {

                //assinging a random number to c
                // the random number is not going to be greating than 8
                // this is done to make sure we have 1/3 distribution
                int c = random.nextInt(9);

                // if the random number assinged to c is less than 3
                // execute the contents of the if statment
                if(c <3){
                    // assign variable u with -2
                    // this is because -2 had a ditribution value of 1/3
                    // the distribution value is calucated by the random number
                    // if the random number is 0,1,2 out of 9 numnbers it has a 1/3 probability
                    int u = -2;

    		            // this is where y is calculated by using the value of the index i of array x
                    // plus the value of u. the total is then mod 7 which would be the value of the
                    // index i in array y.
                    y[i] = (x[i] + u) % 7;

                }

                    // if the value is not less than 3
                    // move to the else if
                    // if the random value c is greater than or equal 3 and less than 6
                    // execute the contents of the if statment.

            		else if (c >=3 && c < 6) {

            		    // the value of u = 0 with a 1/3 distribution
                    // this is calculated by 0,1,2 being 1/3 and in this if statment
                    // 3,4,5 being 1/3 out of 9 numbers
                    int u = 0;

                    // the value of y is then calculated using the value of u
                    //using the value of the index i of array x
                    // plus the value of u. the total is then mod 7 which would be the value of the
                    // index i in array y.
                    y[i] = (x[i] + u) % 7;

                }

    		        //else if the random number is not equal to 0 - 5
                // execute the contents of else
    		        else {

                    // u is equal 2
                    // it has a 1/3 distribution as the random number is not
                    // equal to 0-5 therefore the number is 6,7,8 which is 1/3 prob
                    // out of 9 numbers.
                    int u = 2;

                    // calculating the value of y
                    y[i] = (x[i] + u) % 7;

                    if (y[i] < 0) {
                        y[i] = y[i] + 7;

                    }
                }

                // printing out the uniformed values to the file anonCityUni.da
                writeOut.write(cities[i] + "\t\t" + y[i] + "\n");
            }
        }

        finally {
            writeOut.close();
        }
    }


    /**
     *
     *
     * The NonuniForm method for randomization
     *
     * @param cities
     * @param x
     * @param y
     * @throws java.io.FileNotFoundException
     **/

    public static void NonUniFormed(String[] cities, int[] x , int[] y) throws FileNotFoundException{

        // creating the writeout object to use to print out the unifomend data into a file
        PrintWriter writeOut = new PrintWriter("anonCityNonUni.dat");

        try {

            //creating a random generator and assinging a seed to it
            Random random = new Random();
            long seed = System.currentTimeMillis();
            random.setSeed(seed);

    	       writeOut.write("Cities\t\t" + "Masked city" + "\n" + "\n");

            // declaring a for loop to go through the city sub array
            for(int i = 0; i < y.length; i++){

                //assinging a random number to b
                // the random number is not going to be greating than 9
                // this is done to make sure we have distribution 0f 0.4 , 0.4 , 0.2
                int b = random.nextInt(10);

                //if the random numebr is less than 4 execute the contents of the if
                // statment
                if(b < 4){

                    //variable int u is equal -2
                    // it has a probability distribution of 0.4
                    // this is calculated as 0,1,2,3 out of 10 numbers.
                    // which gives a probability of 0.4
                    int u = -2;

                    // the value of y is then calculated using the value of u
                    //using the value of the index i of array x
                    // plus the value of u. the operation mod 7 is performed on the total
                    //which would be the value of the index i in array y.
                    y[i] = (x[i] + u) % 7;

                }

                //if the random numebr is greater than 3 and less than 8 execute
                //the contents of the if statment.
    	          else if(b >3 && b < 8) {

                    //variable int u is equal 2
                    // it has a probability distribution of 0.4
                    // this is calculated as 4,5,6,7 out of 10 numbers.
                    // which gives a probability of 0.4
                    int u = 2;

                    // the value of y is then calculated using the value of u
                    //using the value of the index i of array x
                    // plus the value of u. the operation mod 7 is performed on the total
                    //which would be the value of the index i in array y.
                    y[i] = (x[i] + u) % 7;

                }

                // else if the number is greater than 7
                // execute the contents of the else
    	          else {

                    //variable int u is equal 0
                    // it has a probability distribution of 0.2
                    // this is calculated as 8,9 out of 10 numbers.
                    // which gives a probability of 0.2
                    int u = 0;

                    // the value of y is then calculated using the value of u
                    //using the value of the index i of array x
                    // plus the value of u. the operation mod 7 is performed on the total
                    //which would be the value of the index i in array y.
                    y[i] = (x[i] + u) % 7;

                }



                 // printing out the nonuniformed data to the file anonCityNonUni.dat
                 writeOut.write(cities[i] + "\t\t" + y[i] + "\n");
            }

        }

        finally {
            writeOut.close();
        }
    }

}
