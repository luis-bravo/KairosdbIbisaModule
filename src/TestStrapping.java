import co.ibisa.kairosdb.StrappingTableItem;
import org.json.JSONWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis.Bravo on 12/16/2016.
 */
public class TestStrapping {

    public static void main(String [] args){
        List<StrappingTableItem> items = new ArrayList<>();

        items.add(new StrappingTableItem(100,101,102) );
        items.add(new StrappingTableItem(200,201,202) );
        items.add(new StrappingTableItem(300,301,302) );
        items.add(new StrappingTableItem(400,401,402) );
        items.add(new StrappingTableItem(500,501,502) );

        StringWriter w = new StringWriter();

        JSONWriter writer = new JSONWriter(w);
        try {
            writer.object();

            writer.key("data").array();
            for(StrappingTableItem item :  items)
            {
                writer.object();

                writer.key("level").value(item.getLevel());
                writer.key("volume").value(item.getVolume());
                writer.key("stvolume").value(item.getStVolume());

                writer.endObject();
            }
            writer.endArray();
            writer.endObject();
        } catch (Exception ex) {

        }
    }
}
