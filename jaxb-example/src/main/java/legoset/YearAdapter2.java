package legoset;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Year;

public class YearAdapter2 extends XmlAdapter<String, Year> {

        public Year unmarshal(String s) throws Exception {
            return Year.parse(s);
        }

        public String marshal(Year year) throws Exception {
            return year.toString();
        }

    }
