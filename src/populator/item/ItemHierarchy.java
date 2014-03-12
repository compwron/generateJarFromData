package populator.item;

import lombok.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemHierarchy {
    private List<Division> divisions = newArrayList();
}
