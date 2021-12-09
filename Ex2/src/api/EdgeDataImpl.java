package api;

public class EdgeDataImpl implements EdgeData {
    int src;
    double w;
    int dest;


    public EdgeDataImpl(int src, double weight, int dest) {
        this.src = src;
        this.w = weight;
        this.dest = dest;

    }

    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDest() {
        return dest;
    }

    @Override
    public double getWeight() {
        return w;
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
        return "(" + src + ", " + w + ", " + dest + ")";
    }
}
