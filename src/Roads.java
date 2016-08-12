/**
 * Created by dmkov on 18.04.2016.
 */
public class Roads {
    private Point startPoint;
    private Point endPoint;
    private double weightOfRoad;
    private double antWeight = 0;

    public Roads(){}
    public Roads(Point startPoint, Point endPoint,  double weightOfRoad) {
        this.weightOfRoad = weightOfRoad;
        this.endPoint = endPoint;
        this.startPoint = startPoint;
    }

    public Point getStartPoint(){
        return this.startPoint;
    }
    public Point getEndPoint(){
        return this.endPoint;
    }
    public double getAntWeight() {
        return antWeight;
    }

    public void setAntWeight(double antWeight) {
        this.antWeight = antWeight;
    }

    public double getWeightOfRoad(){
        return this.weightOfRoad;
    }

    @Override
    public String toString() {
        return "Roads{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", weightOfRoad=" + weightOfRoad +
                ", antWeight=" + antWeight +
                '}';
    }
}
