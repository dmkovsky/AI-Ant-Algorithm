import java.util.List;

/**
 * Created by dmkov on 07.06.2016.
 */
public class ResultPackage {
    private List<Roads> Roads ;
    private double sumOfPoints;
    private double sumOfWeight;

    public ResultPackage(List<Roads> roads, double sumOfPoints, double sumOfWeight) {
        Roads = roads;
        this.sumOfPoints = sumOfPoints;
        this.sumOfWeight = sumOfWeight;
    }

    public List<Roads> getRoads() {
        return Roads;
    }

    public void setRoads(List<Roads> roads) {
        Roads = roads;
    }

    public double getSumOfPoints() {
        return sumOfPoints;
    }

    public void setSumOfPoints(double sumOfPoints) {
        this.sumOfPoints = sumOfPoints;
    }

    public double getSumOfWeight() {
        return sumOfWeight;
    }

    public void setSumOfWeight(double sumOfWeight) {
        this.sumOfWeight = sumOfWeight;
    }

    @Override
    public String toString() {
        return "ResultPackage{" +
                "Roads=" + Roads +
                ", sumOfPoints=" + sumOfPoints +
                ", sumOfWeight=" + sumOfWeight +
                '}';
    }
}
