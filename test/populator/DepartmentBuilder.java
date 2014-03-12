package populator;

import populator.item.Cls;
import populator.item.Department;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DepartmentBuilder {
    private String departmentName = "department name";
    private List<Cls> clses = newArrayList();

    public Department build() {
        return new Department(departmentName, clses);
    }

    public DepartmentBuilder withCls(Cls cls) {
       clses.add(cls);
        return this;
    }
}
