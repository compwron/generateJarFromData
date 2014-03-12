package populator.item;

import lombok.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Division {
    private String name;
    private List<Department> departments = newArrayList();
}
