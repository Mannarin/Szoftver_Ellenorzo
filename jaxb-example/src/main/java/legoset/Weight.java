package legoset;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Weight {
   @XmlAttribute
    private String unit;

   @XmlValue
    private double value;

    public Weight(){ }

    public Weight(String unit, double value) {
        this.unit = unit;
        this.value = value;
    }

}
