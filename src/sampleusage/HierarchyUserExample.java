package sampleusage;

import collect.Collector;
import populator.item.Cls;
import populator.item.Department;
import populator.item.Division;
import populator.item.ItemHierarchy;
import presenter.Hierarchy;

import java.io.IOException;

public class HierarchyUserExample {
    public static void main(String... args) throws IOException {

        ItemHierarchy itemHierarchy = new Hierarchy("test/presenter/data/sampleItemHierarchy.json").getItemHierarchy();

//        Print data from json

        System.out.println("All Divisions in hierarchy: " + itemHierarchy.getDivisions());
        System.out.println("All Cls in hierarchy: " + new Collector().allCls(itemHierarchy));
        System.out.println("All Cls in division: " + new Collector().allCls(itemHierarchy.getDivisions().get(0)).size());
        Division division1 = itemHierarchy.getDivisions().get(0);
        Division division2 = itemHierarchy.getDivisions().get(1);
        System.out.println("All Cls in one or multiple divisions: " + new Collector().allCls(division1, division2).size());

        Department department = itemHierarchy.getDivisions().get(0).getDepartments().get(0);
        System.out.println("All Cls in department: " + new Collector().allCls(department));
        System.out.println("All departments in one or multiple divisions: " + new Collector().allDepartments(division1, division2));
        System.out.println("All departments in hierarchy: " + new Collector().allDepartments(itemHierarchy));
        System.out.println("Division containing CLS with name: " + new Collector().findDivisionsByCls(itemHierarchy, new Cls("UNDETERMINABLES")));
    }
}
