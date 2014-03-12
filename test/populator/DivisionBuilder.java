package populator;

import populator.item.Cls;
import populator.item.Department;
import populator.item.Division;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DivisionBuilder {
    private String divisionName = "division name";
    private List<Department> departments = newArrayList();

    public DivisionBuilder withCls(Cls cls) {
        departments.add(new Department("department name", newArrayList(cls)));
        return this;
    }

    public DivisionBuilder withDepartment(Department department) {
        departments.add(department);
        return this;
    }

    public Division build() {
        return new Division(divisionName, departments);
    }
}
