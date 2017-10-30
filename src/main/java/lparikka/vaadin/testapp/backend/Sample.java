package lparikka.vaadin.testapp.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sample {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String sampleID;
    private String plateID;
    private String row;
    private int column;
    private float volume;

    public float getVolume()
    {
        return volume;
    }

    public void setVolume(float volume)
    {
        this.volume = volume;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public String getRow()
    {
        return row;
    }

    public void setRow(String row)
    {
        this.row = row;
    }

    public String getPlateID()
    {
        return plateID;
    }

    public void setPlateID(String plateID)
    {
        this.plateID = plateID;
    }

    public String getSampleID()
    {
        return sampleID;
    }

    public void setSampleID(String sampleID)
    {
        this.sampleID = sampleID;
    }




}
