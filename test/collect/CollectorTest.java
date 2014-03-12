package collect;

import org.junit.Test;
import populator.DivisionBuilder;
import populator.ItemHierarchyBuilder;
import populator.item.Cls;
import populator.item.Division;
import populator.item.ItemHierarchy;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CollectorTest {
    @Test
    public void shouldFindClsInHierarchy(){
        Cls cls = new Cls("foo");
        Division division = new DivisionBuilder().withCls(cls).build();
        ItemHierarchy itemHierarchy = new ItemHierarchyBuilder().withDivision(division).build();

        List<Division> divisions = new Collector().findDivisionsByCls(itemHierarchy, cls);
        assertThat(divisions.contains(division), is(true));
    }

}
