package populator;

import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import populator.item.ItemHierarchy;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
public class HierarchyJsonPopulate {
    private String fileName;
    private ItemHierarchy itemHierarchy;

    public void writeItemJsonFile() throws IOException {
        writeJsonFile(fileName, itemHierarchy);
    }

    private void writeJsonFile(String fileName, Object object) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String s = ow.writeValueAsString(object);
        File file = new File(fileName);
        file.createNewFile();
        Files.write(s.getBytes(), file);
    }
}
