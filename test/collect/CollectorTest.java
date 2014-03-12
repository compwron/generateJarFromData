package collect;

import org.junit.Test;
import populator.DepartmentBuilder;
import populator.DivisionBuilder;
import populator.ItemHierarchyBuilder;
import populator.item.Cls;
import populator.item.Department;
import populator.item.Division;
import populator.item.ItemHierarchy;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CollectorTest {
    @Test
    public void shouldFindClsInHierarchy() {
        Cls cls = new Cls("foo");
        Division division = new DivisionBuilder().withCls(cls).build();
        ItemHierarchy itemHierarchy = new ItemHierarchyBuilder().withDivision(division).build();

        List<Division> divisions = new Collector().findDivisionsByCls(itemHierarchy, cls);
        assertThat(divisions.contains(division), is(true));
    }

    @Test
    public void shouldFindDivisionWhichContainsDepartments() {
        Department department = new DepartmentBuilder().build();
        Division division = new DivisionBuilder().withDepartment(department).build();
        ItemHierarchy itemHierarchy = new ItemHierarchyBuilder().withDivision(division).build();

        List<Division> divisions = new Collector().findDivisionsByDepartment(itemHierarchy, department);

        assertThat(divisions.contains(division), is(true));
    }

    @Test
    public void shouldNotFindDivisionWhichDoesNotContainDepartments() {
        Department department = new DepartmentBuilder().build();
        ItemHierarchy itemHierarchy = new ItemHierarchyBuilder().build();
        Division division = new DivisionBuilder().build();

        List<Division> divisions = new Collector().findDivisionsByDepartment(itemHierarchy, department);

        assertThat(divisions.contains(division), is(false));
    }

    @Test
    public void shouldGiveAllDepartmentsInHierarchy() {
        ItemHierarchy itemHierarchy = new ItemHierarchyBuilder()
                .withDivision(new DivisionBuilder()
                        .withDepartment(new DepartmentBuilder().build())
                        .build())
                .withDivision(new DivisionBuilder()
                        .withDepartment(new DepartmentBuilder().build())
                        .withDepartment(new DepartmentBuilder().build())
                        .build()
                ).build();

        List<Department> departments = new Collector().allDepartments(itemHierarchy);
        assertThat(departments.size(), is(3));
    }

    @Test
    public void shouldGiveAllDepartmentsInMultipleDivisions() {
        Division division1 = new DivisionBuilder()
                .withDepartment(new DepartmentBuilder().build()).build();
        Division division2 = new DivisionBuilder()
                .withDepartment(new DepartmentBuilder().build())
                .withDepartment(new DepartmentBuilder().build())
                .build();
        List<Department> departments = new Collector().allDepartments(division1, division2);

        assertThat(departments.size(), is(3));
    }

    @Test
    public void shouldFindAllClsInDepartments(){
        Department department1 = new DepartmentBuilder().withCls(new Cls("1")).build();
        Department department2 = new DepartmentBuilder()
                .withCls(new Cls("2"))
                .withCls(new Cls("3"))
                .build();
        List<Cls> clses = new Collector().allCls(department1, department2);

        assertThat(clses.size(), is(3));
    }

    @Test
    public void shouldFindAllClsInMultipleDivisions(){
        Division division1 = new DivisionBuilder()
                .withDepartment(new DepartmentBuilder()
                        .withCls(new Cls("1"))
                        .build()).build();
        Division division2 = new DivisionBuilder()
                .withDepartment(new DepartmentBuilder()
                        .withCls(new Cls("2"))
                        .build())
                .withDepartment(new DepartmentBuilder()
                        .withCls(new Cls("3"))
                        .build())
                .build();

        List<Cls> clses = new Collector().allCls(division1, division2);
        assertThat(clses.size(), is(3));
    }

    @Test
    public void shouldFindAllClsInHierarchy(){
        ItemHierarchy itemHierarchy = new ItemHierarchyBuilder()
                .withDivision(new DivisionBuilder().withCls(new Cls("1")).build())
                .withDivision(new DivisionBuilder()
                        .withCls(new Cls("2"))
                        .withCls(new Cls("3"))
                        .build())
                .build();


        List<Cls> clses = new Collector().allCls(itemHierarchy);
        assertThat(clses.size(), is(3));
    }

}
