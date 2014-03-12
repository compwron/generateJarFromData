package populator;

import data.beans.ItemHierarchyRecord;
import data.mappers.HierarchyMapper;
import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import populator.item.ItemHierarchy;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class HierarchyJsonFetch {
    @Getter
    private ItemHierarchy itemHierarchy;

    public HierarchyJsonFetch(String resource) throws IOException {
        this.itemHierarchy = new ItemHierarchyTranslator(itemHierarchyFromDb(resource)).getHierarchy();
    }

    private List<ItemHierarchyRecord> itemHierarchyFromDb(String resource) throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sqlMapper.openSession();
        HierarchyMapper mapper = session.getMapper(HierarchyMapper.class);
        return mapper.allLocations();
    }
}