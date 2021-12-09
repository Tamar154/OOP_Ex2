package api;

public class NodeDataImpl implements NodeData {
    GeoLocationImpl pos;
    int id;
    double weight;

    public NodeDataImpl(GeoLocationImpl pos, int id) {
        this.pos = new GeoLocationImpl(pos.toString());
        this.id = id;
    }

    @Override
    public int getKey() {
        return id;
    }

    @Override
    public GeoLocation getLocation() {
        if (pos != null)
            return pos;
        return null;
    }

    @Override
    public void setLocation(GeoLocation p) {
        pos.x = p.x();
        pos.y = p.y();
        pos.z = p.z();
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {
        weight = w;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }

    public String toString() {
        return id + "";
    }
}
