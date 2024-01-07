import java.util.List;
import java.util.Objects;

public class Route {
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private List<String> locationPoints;

    public Route(String id, double distance, boolean isFavorite, List<String> locationPoints) {
        this.id = id;
        this.distance = distance;
        this.popularity = 0;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public String getId() {
        return id;
    }



    public double getDistance() {
        return distance;
    }



    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }



    public List<String> getLocationPoints() {
        return locationPoints;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(route.getDistance(), getDistance()) == 0 &&
                Objects.equals(getLocationPoints().getFirst(), route.getLocationPoints().getFirst()) &&
                Objects.equals(getLocationPoints().getLast(), route.getLocationPoints().getLast());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationPoints().getFirst(), getLocationPoints().getLast(), getDistance());
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", locationPoints=" + locationPoints +
                '}';
    }
}