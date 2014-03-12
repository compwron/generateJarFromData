package populator;

import populator.item.Division;
import populator.item.ItemHierarchy;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ItemHierarchyBuilder {
    private List<Division> divisions = newArrayList();

    public ItemHierarchyBuilder withDivision(Division division) {
        divisions.add(division);
        return this;
    }

    public ItemHierarchy build() {
        return new ItemHierarchy(divisions);
    }
}
