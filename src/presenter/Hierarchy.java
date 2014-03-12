package presenter;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.Getter;
import org.codehaus.jackson.map.ObjectMapper;
import populator.item.Division;
import populator.item.ItemHierarchy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Getter
public class Hierarchy {

    private ItemHierarchy itemHierarchy;

    public Hierarchy(String fileLocation) {
        this.itemHierarchy = readFromItemJson(fileLocation);
    }

    public Hierarchy() {  // for library usage
        this.itemHierarchy = readFromItemJson("itemHierarchy.json");
    }

    private ItemHierarchy readFromItemJson(String fileName) {
        try {
            String content = Files.toString(new File(fileName), Charsets.UTF_8);
            return new ObjectMapper().readValue(content, ItemHierarchy.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ItemHierarchy(new ArrayList<Division>());
    }

}
