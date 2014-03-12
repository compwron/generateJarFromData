package presenter;

import org.junit.Test;
import populator.HierarchyJsonPopulator;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HierarchyTest {

    @Test
    public void itemHierarchyShouldHaveDivisions() throws IOException {
        String fileLocation = "test.json";
        HierarchyJsonPopulator populator = new HierarchyJsonPopulator("configuration.xml");
        populator.writeItemJsonFile(fileLocation);
        Hierarchy hierarchy = new Hierarchy(fileLocation);

       assertThat(hierarchy.getItemHierarchy().getDivisions().size(), is(10));
    }
}
