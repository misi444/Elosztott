package hu.miskolc.uni.iit.dist.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "userType")
@XmlEnum
public enum UserType {

    Teacher,
    Student,
    Admin;

    public String value() {
        return name();
    }

    public static UserType fromValue(String v) {
        return valueOf(v);
    }

}
