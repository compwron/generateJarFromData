package populator;

import data.beans.ItemHierarchyRecord;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemHierarchyTranslatorTest {
    @Test
    public void shouldTranslateNone() {
        ItemHierarchyTranslator translator = new ItemHierarchyTranslator(new ArrayList<ItemHierarchyRecord>());
        assertThat(translator.getHierarchy().getDivisions().size(), is(0));
    }

    @Test
    public void shouldTranslateOne() {
        ArrayList<ItemHierarchyRecord> itemHierarchyRecords = newArrayList(new ItemHierarchyRecord("division", "department", "cls"));
        ItemHierarchyTranslator translator = new ItemHierarchyTranslator(itemHierarchyRecords);
        assertThat(translator.getHierarchy().getDivisions().size(), is(1));
        assertThat(translator.getHierarchy().getDivisions().get(0).getDepartments().size(), is(1));
        assertThat(translator.getHierarchy().getDivisions().get(0).getDepartments().get(0).getClsList().size(), is(1));
    }

    @Test
    public void shouldTranslateMultipleSame() {
        ItemHierarchyRecord itemHierarchyRecord1 = new ItemHierarchyRecord("division1", "department", "cls");
        ItemHierarchyRecord itemHierarchyRecord2 = new ItemHierarchyRecord("division2", "department", "cls");
        ArrayList<ItemHierarchyRecord> itemHierarchyRecords = newArrayList(itemHierarchyRecord1, itemHierarchyRecord2);

        ItemHierarchyTranslator translator = new ItemHierarchyTranslator(itemHierarchyRecords);
        assertThat(translator.getHierarchy().getDivisions().size(), is(2));
    }
}
