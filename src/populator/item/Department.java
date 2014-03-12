package populator.item;

import lombok.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private String name;
    private List<Cls> clsList = newArrayList();
}
