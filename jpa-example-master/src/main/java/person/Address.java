package person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Embeddable
public class Address {

    @Column(nullable = false)
    public String country;

    @Column(nullable = false)
    public String state;

    @Column(nullable = false)
    public String city;

    @Column(nullable = false)
    public String streetAddress;

    @Column(nullable = false)
    public String zip;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
