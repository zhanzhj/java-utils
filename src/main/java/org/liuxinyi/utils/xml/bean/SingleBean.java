package org.liuxinyi.utils.xml.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.liuxinyi.utils.xml.adaptor.AdaptorCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * Created by Eric.Liu on 2016/11/11.
 */
@ToString
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "email"})
@XmlRootElement(name = "root")
public class SingleBean implements Serializable {

    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String name;

    @XmlElement
    private String email;

}
