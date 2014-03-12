package populator;

import com.google.common.io.Files;
import data.beans.ItemHierarchyRecord;
import data.mappers.HierarchyMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import populator.item.ItemHierarchy;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class HierarchyJsonPopulator {
    private ItemHierarchy itemHierarchy;

    public HierarchyJsonPopulator(String resource) throws IOException {
        this.itemHierarchy = new ItemHierarchyTranslator(itemHierarchyFromDb(resource)).getHierarchy();
    }

    private List<ItemHierarchyRecord> itemHierarchyFromDb(String resource) throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sqlMapper.openSession();
        HierarchyMapper mapper = session.getMapper(HierarchyMapper.class);
        return mapper.allLocations();
    }

    public void writeItemJsonFile(String fileName) throws IOException {
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