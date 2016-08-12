/**
 * Created by dmkov on 20.04.2016.
 */
public class Point {
    private String name;

    public Point(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return name;
    }
}
