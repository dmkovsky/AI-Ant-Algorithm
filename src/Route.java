import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dmkov on 20.04.2016.
 */
public class Route {
    private ArrayList<Roads> roadsLists = new ArrayList<>();
    private ArrayList<Point> visitedPoints = new ArrayList<>();
    private ArrayList<Roads> chooseRoadList = new ArrayList<>();
    private ArrayList<Roads> visitedRoads = new ArrayList<>();
    private double antWeight = 0;
    private double sumOfPoints = 0;
    private double sumOfWeights = 0;
    private Point startPoint;
    private Point endPoint;
    private Point tempPoint;
    private Random randGenerator = new Random();
    private int sizeOfRoads = 0;
    private Roads tempRoad;
    private double probability = 0;
    private Point newstartPoint;
    private List<Roads> theBestRoads = new ArrayList<>();
    private List<Roads> roadHistory = new ArrayList<>();

    public ArrayList<Point> getVisitedPoints() {
        return visitedPoints;
    }

    public void setVisitedPoints(ArrayList<Point> visitedPoints) {
        this.visitedPoints = visitedPoints;
    }

    public Route(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Route(){};
    public ArrayList<Roads> getRoadsLists() {
        return roadsLists;
    }

    public ArrayList<Roads> getVisitedRoads() {
        return visitedRoads;
    }

    public void setVisitedRoads(ArrayList<Roads> visitedRoads) {
        this.visitedRoads = visitedRoads;
    }

    public void setRoadsLists(ArrayList<Roads> roadsLists) {
        this.roadsLists = roadsLists;
    }

    public double getSumOfPoints() {
        return sumOfPoints;
    }

    public void setSumOfPoints(double sumOfPoints) {
        this.sumOfPoints = sumOfPoints;
    }

    public double getSumOfWeights() {
        return sumOfWeights;
    }

    public void setSumOfWeights(double sumOfWeights) {
        this.sumOfWeights = sumOfWeights;
    }

    public void findRoads(Point startPoint){
        chooseRoadList.clear();
        tempPoint = startPoint;
        visitedPoints.add(startPoint);
        List<Roads> posibilities = getPosibilities(startPoint);

        sizeOfRoads = posibilities.size();
        tempRoad = posibilities.get(randGenerator.nextInt(sizeOfRoads));
        sumOfWeights += tempRoad.getWeightOfRoad();
        sumOfPoints += 1;
        newstartPoint = tempRoad.getEndPoint();
        visitedPoints.add(tempRoad.getEndPoint());
        if(antWeight <= 0.9){
            antWeight = tempRoad.getAntWeight() + 0.1;
        }
        tempRoad.setAntWeight(antWeight);
        visitedRoads.add(tempRoad);

        if (tempRoad.getEndPoint() == endPoint){
            for(Point p : visitedPoints){
                System.out.println("Koniec kroku 1szego "+p.toString());
            }
            visitedPoints.clear();
            return;
        }
        else findRoads(tempRoad.getEndPoint());
    }

    private List<Roads> getPosibilities(Point startPoint) {

        List<Roads> result = new ArrayList<Roads>();
        for(Roads road : roadsLists){
            if(startPoint.getName().compareTo(road.getStartPoint().getName())==0){
//                if(road.getEndPoint().getName().compareTo(startPoint.getName()) != 0){
                result.add(road);

                //}
            }
        }
        List<Roads> mirror = new ArrayList<Roads>();
        mirror.addAll(result);
        for(Roads road : mirror){
            for(Point point : visitedPoints){
                if(road.getEndPoint().getName().compareTo(point.getName()) == 0){
                    result.remove(road);
                }
            }
        }

        return result;
    }

    public void secondStep(Point startPoint) {
        Roads bestRoad = new Roads();
        List<Roads> posibilities = getPosibilities(startPoint);
        double max = 0.0;
        for(Roads road : posibilities){
            probability = probabilityCalc(road);
            if(max < probability){
                max = probability;
                bestRoad = road;
                //System.out.println(road.toString());

            }

        }
        sumOfWeights += bestRoad.getWeightOfRoad();
        sumOfPoints += 1;
        vaporization(posibilities);
        newstartPoint = bestRoad.getEndPoint();
        visitedPoints.add(bestRoad.getEndPoint());
        visitedRoads.add(bestRoad);

        if (bestRoad.getEndPoint() == endPoint){
            for(Point p : visitedPoints){
                System.out.println("Koniec kroku 2iego "+p.toString());
            }
            theBestRoads.addAll(visitedRoads);
            roadHistory.addAll(visitedRoads);
            visitedPoints.clear();
            return ;
        }
        else secondStep(bestRoad.getEndPoint());
    }

    public Double probabilityCalc(Roads road){
        double resault = 0.0;
        resault = randGenerator.nextDouble()*0.2+road.getAntWeight()*0.8;
        return resault;
    }
    public void vaporization(List<Roads> posibilities){

        for(Roads road : posibilities){
            if(road.getAntWeight() > 0.01){
                road.setAntWeight(road.getAntWeight() - 0.01);
            }
        }

    }


    public List<Roads> getRoadHistory() {
        ArrayList<Roads> resault = new ArrayList<>();
        resault.addAll(roadHistory);
        roadHistory.clear();
        return resault;
    }

    public void setRoadHistory(List<Roads> roadHistory) {
        this.roadHistory = roadHistory;
    }
}
