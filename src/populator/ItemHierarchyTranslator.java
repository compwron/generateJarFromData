package populator;

import lombok.Getter;
import data.beans.ItemHierarchyRecord;
import populator.item.Cls;
import populator.item.Department;
import populator.item.Division;
import populator.item.ItemHierarchy;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

@Getter
public class ItemHierarchyTranslator {
    private ItemHierarchy hierarchy;

    public ItemHierarchyTranslator(List<ItemHierarchyRecord> itemHierarchyRecords) {
        this.hierarchy = new ItemHierarchy(buildDivisions(itemHierarchyRecords));
    }

    private List<Division> buildDivisions(List<ItemHierarchyRecord> itemHierarchyRecords) {
        Map<String, Set<String>> divisionPiecesMap = new HashMap<String, Set<String>>();
        Map<String, Set<String>> departmentPiecesMap = new HashMap<String, Set<String>>();
        for (ItemHierarchyRecord record : itemHierarchyRecords) {
            if (divisionPiecesMap.get(record.getDivision()) == null) {
                divisionPiecesMap.put(record.getDivision(), new HashSet<String>());
            }
            divisionPiecesMap.get(record.getDivision()).add(record.getDepartment());

            if (departmentPiecesMap.get(record.getDepartment()) == null) {
                departmentPiecesMap.put(record.getDepartment(), new HashSet<String>());
            }
            departmentPiecesMap.get(record.getDepartment()).add(record.getCls());
        }

        List<Division> divisions = newArrayList();
        for (String division : divisionPiecesMap.keySet()) {
            divisions.add(divisionFrom(division, divisionPiecesMap.get(division), departmentPiecesMap));
        }
        return divisions;
    }

    private Division divisionFrom(String divisionName, Set<String> departmentNames, Map<String, Set<String>> departmentPiecesMap) {
        List<Department> departments = newArrayList();
        for (String department : departmentNames) {
            departments.add(departmentFrom(department, departmentPiecesMap.get(department)));
        }

        return new Division(divisionName, departments);
    }

    private Department departmentFrom(String departmentName, Set<String> clsNames) {
        List<Cls> clses = newArrayList();
        for (String cls : clsNames) {
            clses.add(new Cls(cls));
        }

        return new Department(departmentName, clses);
    }
}
