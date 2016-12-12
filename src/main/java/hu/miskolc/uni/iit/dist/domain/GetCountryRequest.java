package hu.miskolc.uni.iit.dist.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name"
})
@XmlRootElement(name = "getCountryRequest")
public class GetCountryRequest {

    @XmlElement(required = true)
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
}