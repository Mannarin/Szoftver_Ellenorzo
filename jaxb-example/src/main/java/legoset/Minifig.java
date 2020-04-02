package legoset;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Minifig {

    @XmlAttribute
    private int count;

    @XmlValue
    private String minifigname;

    public Minifig () {}

    public Minifig (String minifigname,int count){
        this.minifigname=minifigname;
        this.count=count;
    }
}
