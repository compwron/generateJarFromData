package populator;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class HierarchyJsonPopulatorTest {
    HierarchyJsonPopulator populator;

    @Before
    public void setUp() throws IOException {
        populator = new HierarchyJsonPopulator("configuration.xml");
    }

    @Test
    public void populatorShouldWriteItemData() throws IOException {
        String fileName = "itemHierarchy.json";
        populator.writeItemJsonFile(fileName);
        new File(fileName).delete();
    }
}
