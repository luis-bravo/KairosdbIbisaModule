package co.ibisa.kairosdb;

import java.io.Serializable;

public class StrappingTableItem implements Serializable
{
    private float m_level;
    private float m_volume;
    private float m_stVolume;

    public StrappingTableItem(float level, float volume, float stVolume)
    {
        m_level = level;
        m_volume = volume;
        m_stVolume = stVolume;
    }

    public float getLevel()
    {
        return m_level;
    }

    public void setLevel(float level)
    {
        m_level = level;
    }

    public float getVolume()
    {
        return m_volume;
    }

    public void getVolume(float volume)
    {
        m_volume = volume;
    }

    public float getStVolume()
    {
        return m_stVolume;
    }

    public void setStVolume(float stVolume)
    {
        m_stVolume = stVolume;
    }
}
