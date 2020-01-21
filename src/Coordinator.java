import java.util.ArrayList;

public class Coordinator {

    public static boolean searchBands(ArrayList<Band> sortedBands, String query) {
        if (sortedBands.size() == 0) {
            return false;
        }
        if (sortedBands.get(sortedBands.size() / 2).getBandName().equals(query)) {
            return true;
        } else {
            // searchBands()
        }
        return true;
    }
}

class Band {
    private String bandName;
    private float setTime;

    public Band(String bandName, float setTime) {
        this.bandName = bandName;
        this.setTime = setTime;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public float getSetTime() {
        return setTime;
    }

    public void setSetTime(float setTime) {
        this.setTime = setTime;
    }
}
