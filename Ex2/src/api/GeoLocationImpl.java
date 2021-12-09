package api;

public class GeoLocationImpl implements GeoLocation {
    double x, y, z;

    public GeoLocationImpl() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public GeoLocationImpl(String str) {
        String[] temp = str.split(",");
        this.x = Double.parseDouble(temp[0]);
        this.y = Double.parseDouble(temp[1]);
        this.z = Double.parseDouble(temp[2]);
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        double d = Math.sqrt(Math.pow(this.x - g.x(), 2) + Math.pow(this.y - g.y(), 2) + Math.pow(this.z - g.z(), 2));
        return d;
    }

    public String toString() {
        return x + "," + y + "," + z;
    }
}
