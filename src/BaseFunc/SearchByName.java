package BaseFunc;

import java.util.Vector;

public class SearchByName {
    public SearchByName() {
    }

    public static Vector<Ranking> searchByName(String name, Vector<Ranking> ranking) {
        Vector<Ranking> result = new Vector<Ranking>();
        for (Ranking r : ranking) {
            if (r.getName().equals(name)) {
                result.add(r);
            }
        }
        return result;
    }
}
