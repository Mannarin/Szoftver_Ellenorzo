package legoset;

import java.time.Year;
import java.util.List;
import java.util.Set;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class LegoSet{

    @XmlAttribute
    private String number;

    private String name;
    private String theme;
    private String subtheme;

    @XmlJavaTypeAdapter(YearAdapter2.class)
    private Year year;
    private int pieces;

    @XmlElementWrapper(name="tags")
    @XmlElement(name="tag")
    private Set<String> tags ;

    @XmlElementWrapper(name="minifigs")
    @XmlElement(name="minifig")
    private List<Minifig> minifigs;

    private Weight weight;
    private String url;


}
