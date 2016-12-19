package co.ibisa.kairosdb;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

/**
 * Created by Luis.Bravo on 12/16/2016.
 */
public class IbisaModule  extends AbstractModule
{
    public static final Logger logger = LoggerFactory.getLogger(IbisaModule.class);
    private Properties m_properties;

    public IbisaModule(Properties props)
    {
        m_properties = props;
    }

    @Override
    protected void configure() {
        logger.info("Configuring IbisaModule");

        bind(StrappingTableDataPointFactory.class).in(Singleton.class);
    }
}
