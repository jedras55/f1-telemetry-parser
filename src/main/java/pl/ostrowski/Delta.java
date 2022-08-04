package pl.ostrowski;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Delta {
    private Map<Integer, Double> deltaMap = new LinkedHashMap<>();
}
