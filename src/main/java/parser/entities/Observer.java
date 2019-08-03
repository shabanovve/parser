package parser.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Observer {
    private String name;
    private String code;
    private List<Substance> substances = new ArrayList<>();
}
