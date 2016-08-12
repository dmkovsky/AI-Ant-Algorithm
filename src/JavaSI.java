import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmkov on 17.04.2016.
 */
public class AntAlgorithm {
    public static void main(String[] args) {
        ArrayList<Roads> roadList = new ArrayList<Roads>();
        Point p1 = new Point("p1");
        Point p2 = new Point("p2");
        Point p3 = new Point("p3");
        Point p4 = new Point("p4");
        Point p5 = new Point("p5");
        Point p6 = new Point("p6");
        Point p7 = new Point("p7");
        Point p8 = new Point("p8");


        Roads r1 = new Roads(p1, p2, 0.2);
        Roads r2 = new Roads(p1, p3, 0.6);
        Roads r3 = new Roads(p2, p3, 0.3);
        Roads r4 = new Roads(p3, p4, 0.1);
        Roads r5 = new Roads(p3, p6, 0.5);
        Roads r6 = new Roads(p3, p7, 0.8);
        Roads r7 = new Roads(p4, p5,0.1);
        Roads r8 = new Roads(p4, p6, 0.3);
        Roads r9 = new Roads(p5, p6, 0.2);
        Roads r10 = new Roads(p6, p8, 0.4);
        Roads r11 = new Roads(p7, p8, 0.3);

        roadList.add(r1);
        roadList.add(r2);
        roadList.add(r3);
        roadList.add(r4);
        roadList.add(r5);
        roadList.add(r6);
        roadList.add(r7);
        roadList.add(r8);
        roadList.add(r9);
        roadList.add(r10);
        roadList.add(r11);
        Route test = new Route(p1, p8);
        test.setRoadsLists(roadList);
        for(int i=0; i<100; i++){
            test.findRoads(p1);
            test.setSumOfPoints(0);
            test.setSumOfWeights(0.0);
        }
        List<ResultPackage> minLenghtRoute = new ArrayList<>();
        double minPoints = 0;
        double maxWeight = 0;
        test.setVisitedRoads(new ArrayList<Roads>());
        test.setSumOfPoints(0);
        test.setSumOfWeights(0.0);
        test.secondStep(p1);
        minPoints = test.getSumOfPoints();
        minLenghtRoute.add(new ResultPackage(test.getRoadHistory(), test.getSumOfPoints(), test.getSumOfWeights()));
        for(int i=0; i<100; i++){
            test.setVisitedRoads(new ArrayList<Roads>());
            test.setSumOfPoints(0);
            test.setSumOfWeights(0.0);
            test.secondStep(p1);
            List<Roads> roadHistory = test.getRoadHistory();
            System.out.println(roadHistory.size());
            if(test.getSumOfPoints() < minPoints){
                minLenghtRoute.set(0,new ResultPackage(roadHistory, test.getSumOfPoints(), test.getSumOfWeights()));
                checkRoutes(minLenghtRoute);
            }
            else if(test.getSumOfPoints() == minPoints){
                minLenghtRoute.add(new ResultPackage(roadHistory, test.getSumOfPoints(), test.getSumOfWeights()));
            }
        }
        maxWeight = minLenghtRoute.get(0).getSumOfWeight();
        ResultPackage winner = minLenghtRoute.get(0);
        for(ResultPackage rest : minLenghtRoute){
            if(rest.getSumOfWeight() > maxWeight){
                winner = rest;
            }
        }
        System.out.println(winner);
    }

    private static void checkRoutes(List<ResultPackage> minLenghtRoute) {
        double min = minLenghtRoute.get(0).getSumOfPoints();
        for(ResultPackage rest : minLenghtRoute.subList(0,minLenghtRoute.size())){
            if(rest.getSumOfPoints() != min){
                minLenghtRoute.remove(rest);
            }
        }
    }
}
