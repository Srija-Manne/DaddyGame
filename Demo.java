// Java Program to Extract Content from a HTML document

// Importing input/output java libraries
import java.io.*;

public class Demo {

    // Main driver method
    public static void main(String[] args)
            throws FileNotFoundException {

        /*
         * Constructing String Builder to
         * append the string into the html
         */
        StringBuilder html = new StringBuilder();

        // Reading html file on local directory
        FileReader fr = new FileReader(
                "s.html");

        // Try block to check exceptions
        try {

            // Initialization of the buffered Reader to get
            // the String append to the String Builder
            BufferedReader br = new BufferedReader(fr);

            String val;

            // Reading the String till we get the null
            // string and appending to the string
            while ((val = br.readLine()) != null) {
                html.append(val);
            }

            // AtLast converting into the string
            String result = html.toString();
            System.out.println(result);

            // Closing the file after all the completion of
            // Extracting
            br.close();
        }

        // Catch block to handle exceptions
        catch (Exception ex) {

            /*
             * Exception of not finding the location and
             * string reading termination the function
             * br.close();
             */
            System.out.println(ex.getMessage());
        }
    }
}
