package co.ibisa.kairosdb;

import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.kairosdb.core.DataPoint;
import org.kairosdb.core.datapoints.DataPointFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StrappingTableDataPointFactory implements DataPointFactory
{
    public static final String DST_STRAPPING = "kairos_strappingtable";
    public static final String GROUP_TYPE = "strappingtable";

    @Override
    public String getDataStoreType()
    {
        return DST_STRAPPING;
    }

    @Override
    public String getGroupType()
    {
        return GROUP_TYPE;
    }

    @Override
    public DataPoint getDataPoint(long timestamp, JsonElement json) throws IOException
    {
        List<StrappingTableItem> items = new ArrayList<>();

        if (json.isJsonObject())
        {
            JsonObject data = json.getAsJsonObject();
            JsonArray array = data.get("data").getAsJsonArray();

            for (JsonElement element : array) {
                if (element.isJsonObject())
                {
                    JsonObject object = element.getAsJsonObject();
                    float level = object.get("level").getAsFloat();
                    float volume = object.get("volume").getAsFloat();
                    float stVolume = object.get("stvolume").getAsFloat();
                    StrappingTableItem item = new StrappingTableItem(level, volume, stVolume);
                    items.add(item);
                }
                else
                    throw new IOException("JSON object does not contain valid strapping table data");
            }
            return new StrappingTableDataPoint(timestamp, items);
        }
        else
            throw new IOException("JSON object is not a valid strapping table data point");
    }

    @Override
    public DataPoint getDataPoint(long timestamp, DataInput buffer) throws IOException
    {
        List<StrappingTableItem> items = new ArrayList<>();

        while (true)
        {
            try
            {
                float level = buffer.readFloat();
                float volume = buffer.readFloat();
                float stVolume = buffer.readFloat();
                StrappingTableItem item = new StrappingTableItem(level, volume, stVolume);
                items.add(item);
            }
            catch(EOFException ex) {
                break;
            }
        }

        return new StrappingTableDataPoint(timestamp, items);
    }
}
