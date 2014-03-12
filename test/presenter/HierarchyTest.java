package presenter;

import org.junit.After;
import org.junit.Test;
import populator.HierarchyJsonPopulator;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HierarchyTest {
    String fileLocation = "test.json";

    @After
    public void tearDown() {
        new File(fileLocation).delete();
    }

    @Test
    public void itemHierarchyShouldHaveDivisions() throws IOException {
        HierarchyJsonPopulator populator = new HierarchyJsonPopulator("configuration.xml");
        populator.writeItemJsonFile(fileLocation);
        Hierarchy hierarchy = new Hierarchy(fileLocation);

        assertThat(hierarchy.getItemHierarchy().getDivisions().size(), is(10));
    }
}
