package populator;

import populator.item.Cls;
import populator.item.Department;
import populator.item.Division;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DivisionBuilder {
    private String divisionName = "division name";
    private List<Department> departments = newArrayList();

    public DivisionBuilder withCls(Cls cls) {
        if (departments.size() == 0) {
            departments.add(new Department("department name", new ArrayList<Cls>()));
        } else {
            Department department = departments.get(0);
            department.getClsList().add(cls);
            departments.remove(0);
            departments.add(new Department(department.getName(), department.getClsList()));
        }
        return this;
    }

    public Division build() {
        return new Division(divisionName, departments);
    }
}
