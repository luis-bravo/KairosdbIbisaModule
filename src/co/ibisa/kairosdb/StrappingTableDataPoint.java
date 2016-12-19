package co.ibisa.kairosdb;

import org.json.JSONException;
import org.json.JSONWriter;
import org.kairosdb.core.datapoints.DataPointHelper;

import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StrappingTableDataPoint extends DataPointHelper
{
    private static final String API_TYPE = "strappingtable";
    private List<StrappingTableItem> m_items;

    public StrappingTableDataPoint(long timestamp, List<StrappingTableItem> items)
    {
        super(timestamp);
        m_items = new ArrayList<>();

        items.forEach(item -> m_items.add(new StrappingTableItem(item.getLevel(),item.getVolume(),item.getStVolume())) );
    }

    @Override
    public void writeValueToBuffer(DataOutput buffer) throws IOException
    {
        for(StrappingTableItem item :  m_items)
        {
            buffer.writeFloat(item.getLevel());
            buffer.writeFloat(item.getVolume());
            buffer.writeFloat(item.getStVolume());
        }
    }

    @Override
    public void writeValueToJson(JSONWriter writer) throws JSONException
    {
        writer.object();

        writer.key("data").array();
        for(StrappingTableItem item :  m_items)
        {
            writer.object();

            writer.key("level").value(item.getLevel());
            writer.key("volume").value(item.getVolume());
            writer.key("stvolume").value(item.getStVolume());

            writer.endObject();
        }
        writer.endArray();
        writer.endObject();
    }

    @Override
    public String getApiDataType()
    {
        return API_TYPE;
    }

    @Override
    public String getDataStoreDataType()
    {
        return StrappingTableDataPointFactory.DST_STRAPPING;
    }

    @Override
    public boolean isLong()
    {
        return false;
    }

    @Override
    public long getLongValue()
    {
        return 0;
    }

    @Override
    public boolean isDouble()
    {
        return false;
    }

    @Override
    public double getDoubleValue()
    {
        return 0;
    }
}
