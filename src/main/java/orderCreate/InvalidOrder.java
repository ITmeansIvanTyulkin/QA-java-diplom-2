package orderCreate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvalidOrder {

    List<String> ingredientsInvalid;
    private static List<Data> data;

    public InvalidOrder(ArrayList<String> ingredientsInvalid) {
        this.ingredientsInvalid = new ArrayList<>(Arrays.asList("61c0c5a71d1f82001bdaaz6d"));
    }

    public static List<Data> getData() {
        return data;
    }
}