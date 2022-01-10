  /*
   * Number.java
   *
   * Version: $Id$
   *
   * Revisions: $Log$
   */

  import java.util.ArrayList;

  /**
   * Description - This class works onsplitting a number by digits and taking square of each individual digit
   * until one or any seen number is introduced in the list
   *
   * @author Author name
   * @author Dibyanshu Chatterjee
   * @author Muskan Mall
   */


  class NumberSolution_2 extends Thread {
      // a global boolean array to store the numbers that we have already been seen as
      // the sum
      static boolean[] arr = new boolean[1001];
      static ArrayList<Integer> GlobalStorage;

      NumberSolution_2(ArrayList<Integer> GlobalStorage) {
          NumberSolution_2.GlobalStorage = GlobalStorage;
      }

      /*
       *
       * The main function only calls the check function. to check if a number has the
       * property we are looking for
       *
       */

      /*
       *
       * The split function splits the number into its different digits, and then sums
       * up the square of all the digits we send the number we wanna split and sum as
       * a parameter -> n
       *
       */
      public int split(int n) {
          int sum = 0;
          while (n != 0) {
              // this line will divide the number, so we recieve the last number
              sum = sum + ((n % 10) * (n % 10));
              // this line will divide the number by 10, and keep the remaining numbers
              // after we squared and summed our desired digit from the number
              n = n / 10;

          }
          return sum;
      }

      /*
       *
       * The check function is being used to check if a number has a desired property
       * or not.
       *
       */
      public void check() {
          int i, sum;
          // this array runs from 0 to 1001, to store all sums we have already seen
          for (i = 0; i < 1001; i++) {
              // boolean array gets initialised for every new digit we are checking
              arr = new boolean[1001];
              // calling the split function
              sum = split(i);
              // setting our number's place in the boolean array, marking it true
              arr[i] = true;
              // running this while loop until we see the sum become 1
              // or see if its repeating any sums
              while (sum != 1 && arr[sum] != true) {
                  // setting each sum, index value, into true
                  arr[sum] = true;
                  // repeatedly spliting the digit, so we can go until we find 1
                  // or see a sum repeat itself
                  sum = split(sum);
                  // if we find the number with the desired property
                  if (sum == 1) {
                      // funcion to print the number and digits and its sum
                      GlobalStorage.add(i);
                  }
              }
          }
      }

      /*
       *
       * this function is splitting the digits with our desired property and then
       * printing out, the path until reaching one
       * @param n -> to execute the loop
       */
      public void split_digit(int n) {
          while (n > 0) {
              // printing out each digit and its square
              System.out.print(n % 10 + "^2");
              if (n / 10 > 1 || n / 1 > 1)
                  System.out.print(" + ");
              n = n / 10;

          }
      }

      /*
       *
       * this function prints the number with our desired property and prints the
       * sums, and the numbers who's sum added upto 1
       * @param i -> to help with printing
       */
      public void printnumber(int i) {
          int j = 0;
          System.out.println(" ");
          System.out.println("------------");
          System.out.print(i + " = ");
          split_digit(i);
          // we want to iteratively keep on pritnting all the sums and the digits
          // that led to this sum
          j = split(i);
          // spliting and printing it until we reach all the sums
          while (j != 1) {
              System.out.print(" = " + j + " = ");
              split_digit(j);
              j = split(j);
          }
      }

      @Override
      public void run() {
          synchronized (GlobalStorage) {
              for (int i = 0; i < GlobalStorage.size(); i++) {
                  printnumber(GlobalStorage.get(i));
              }
          }
          check();
      }
  }

