import java.io.PrintWriter;

/**
 * Created by Felix on 08.04.2015.
 */
public class BruteForceNavigation {
    public static void main(String args[]) throws Exception{
        PrintWriter datei = new PrintWriter("C://Users/Felix/Desktop/ausgabe.txt");


        double[][] searchField = new double[151][151];

        for (double i = 0.0; i < 15.0;) {
            for (double j = 0.0; j < 15.0;) {

                int iInt = (int) (i*10);
                int jInt = (int) (j*10);
                searchField[iInt][jInt] = dist(i,j);
                //System.out.print(String.format( "%.2f", searchField[iInt][jInt]) + " ");
                System.out.print( searchField[iInt][jInt] + " ");
                datei.print( searchField[iInt][jInt] + " ");
                /*if (jInt == 149){
                    System.out.println("");
                }*/
                j = j + 0.1;


            }
             i = i + 0.1;
            System.out.println("");
            datei.println("");
        }
        datei.close();

    }

    public static double dist(double x, double y){
        double xHome = 5.0;
        double yHome = 5.0;
        double result = 0.0;
        double xPart = Math.pow((x - xHome), 2.0);
        double yPart = Math.pow((y - yHome), 2.0);
        result = Math.sqrt((xPart + yPart));
        return result;
    }
}
