/**
 * Created by Felix on 07.04.2015.
 */
public class NavigateTheRobot {

    public static void main(String args []){
        double distance = dist(10.0,2.0);
        System.out.println(distance);
        // distance = dist(10.1,2.0);
        // distance = dist(10.0,2.1);
        double xN = 10.1;
        double yN = 2.1;
        double xNMinus1 = 10.0;
        double yNMinus1 = 2.0;
        double xPlus1 = 0.0;
        double yPlus1 = 0.0;

        while(distance >= 1.0){
        //for (int i = 0; i < 5; i++) {
             xPlus1 = xN - (dist(xN,yN) - dist(xNMinus1,yNMinus1)) / (xN - xNMinus1);
            double newXDist = dist(xPlus1,yN);
            //System.out.println("xNPlus1: " + xPlus1 + " xN: " + xN + " neue Distanz mit xNPlus1: " + newXDist);


            yPlus1 = yN - (dist(xN,yN) - dist(xNMinus1, yNMinus1)) / (yN - yNMinus1);
            double newYDist = dist(xN,yPlus1);
            //System.out.println("yNPlus1: " + yPlus1 + " yN: " + yN + " neue Distanz mit yNPlus1: " + newYDist);

            distance = dist(xPlus1, yPlus1);
            //System.out.println("neue Distanz mit xNPlus1 UND yNPlus1: " + distance);


            //double newXDist = dist(xPlus1,yN);
            //double newYDist = dist(xN,yPlus1);
            System.out.println("yNPlus1: " + yPlus1 + " neue Distanz mit yNPlus1: " + newYDist);
            System.out.println("xNPlus1: " + xPlus1 + " neue Distanz mit xNPlus1: " + newXDist);
            System.out.println("neue Distanz mit xNPlus1("+xN+") UND yNPlus1(" + yN + "): " + distance);
            if (distance<newYDist && distance < newXDist){
                xNMinus1 = xN;
                xN = xPlus1;
                yNMinus1 = yN;
                yN = yPlus1;
                System.out.println("Entscheidung: neue Distanz mit xNPlus1("+xN+") UND yNPlus1(" + yN + "): " + distance);
            }else if(newXDist<newYDist){
                xNMinus1 = xN;
                xN = xPlus1;
                distance = newXDist;
                System.out.println("Entscheidung: neue Distanz mit neuem xNPlus1(" + xN + ") und yN("+yN+"): " + newXDist);
            }else if(newYDist<newXDist){
                yNMinus1 = yN;
                yN = yPlus1;
                distance = newYDist;
                System.out.println("Entscheidung: neue Distanz mit neuem yNPlus1(" + yN+ ") und xN(" + xN + "): " + newYDist);
            }


           // distance = dist(xN,yN);
           // System.out.println("new xN: " + xN + " new yN: " + yN + " neue Distanz: " + distance);

        }



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
