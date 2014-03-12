package collect;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import populator.item.Cls;
import populator.item.Department;
import populator.item.Division;
import populator.item.ItemHierarchy;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Collector {

    public List<Cls> allCls(ItemHierarchy itemHierarchy) {
        return determineClsesFromDivisions(itemHierarchy.getDivisions());
    }

    public List<Cls> allCls(Division... divisions) {
        return determineClsesFromDivisions(newArrayList(divisions));
    }

    public List<Cls> allCls(Department... departments) {
        return determineClsesFromDepartments(newArrayList(departments));
    }

    public List<Department> allDepartments(ItemHierarchy itemHierarchy) {
        return allDepartments(makeVarArgs(itemHierarchy));
    }

    private Division[] makeVarArgs(ItemHierarchy itemHierarchy) {
        List<Division> divisionsList = itemHierarchy.getDivisions();
        Division[] divisions = new Division[divisionsList.size()];
        for (int i = 0; i < divisionsList.size(); i++) {
            divisions[i] = divisionsList.get(i);
        }
        return divisions;
    }

    public List<Department> allDepartments(Division... divisions) {
        return departmentsFromDivisions(newArrayList(divisions));
    }

    private List<Cls> determineClsesFromDivisions(List<Division> divisions) {
        return determineClsesFromDepartments(departmentsFromDivisions(divisions));
    }

    private List<Department> departmentsFromDivisions(List<Division> divisions) {
        return newArrayList(Iterables.concat(Collections2.transform(divisions, departmentsFromDivision())));
    }

    private List<Cls> determineClsesFromDepartments(List<Department> departments) {
        List<Cls> clses = newArrayList(Iterables.concat(Collections2.transform(departments, divisionsFromDepartment())));

        return clses;
    }

    private Function<? super Department, List<Cls>> divisionsFromDepartment() {
        return new Function<Department, List<Cls>>() {
            @Override
            public List<Cls> apply(Department input) {
                return input.getClsList();
            }
        };
    }

    private Function<Division, List<Department>> departmentsFromDivision() {
        return new Function<Division, List<Department>>() {
            @Override
            public List<Department> apply(Division input) {
                return input.getDepartments();
            }
        };
    }

    public List<Division> findDivisionsByCls(ItemHierarchy itemHierarchy, Cls cls) {
//        It is possible for a Cls to be in several departments or even divisions
        List<Division> divisionsContainingCls = newArrayList();
        for (Division division : itemHierarchy.getDivisions()){
           if (containsCls(division, cls)){
               divisionsContainingCls.add(division);
           }
        }
        return divisionsContainingCls;
    }

    private boolean containsCls(Division division, Cls cls) {
        for (Department department : division.getDepartments()){
            if (department.getClsList().contains(cls)){
                return true;
            }
        }
        return false;
    }

    public List<Division> findDivisionsByDepartment(ItemHierarchy itemHierarchy, Department department) {
        return null;
    }
}
