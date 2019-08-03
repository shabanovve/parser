package parser.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Measurement {
    private Timestamp date;
    private List<Observer> observers = new ArrayList<>();
}
