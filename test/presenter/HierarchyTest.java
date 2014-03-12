package presenter;

import org.junit.After;
import org.junit.Test;
import populator.HierarchyJsonFetch;
import populator.HierarchyJsonPopulate;

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
        HierarchyJsonFetch populator = new HierarchyJsonFetch("configuration.xml");

        new HierarchyJsonPopulate(fileLocation, populator.getItemHierarchy()).writeItemJsonFile();
        Hierarchy hierarchy = new Hierarchy(fileLocation);

        assertThat(hierarchy.getItemHierarchy().getDivisions().size(), is(10));
    }
}
