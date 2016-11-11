package org.liuxinyi.utils.xml.adaptor;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Eric.Liu on 2016/11/11.
 */
@Slf4j
public class AdaptorCDATA extends XmlAdapter<String, String> {

    @Override
    public String marshal(String arg0) throws Exception {
        log.info("arg0 : {} ", arg0);
        return "<![CDATA[" + arg0 + "]]>";
    }

    @Override
    public String unmarshal(String arg0) throws Exception {
        return arg0;
    }

}
