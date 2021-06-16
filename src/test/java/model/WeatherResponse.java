package model;

public class WeatherResponse {
    //vlozhili coord v response;
    private Coord coord;
    private String base;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
