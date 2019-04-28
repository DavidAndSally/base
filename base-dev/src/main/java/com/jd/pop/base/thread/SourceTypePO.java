package com.jd.pop.base.thread;

import java.io.Serializable;

/**
 * 榜单来源 PO
 * <p>
 *
 * @author sunboyu
 * @date 2017/11/16
 */
public class SourceTypePO implements Serializable {

    private static final long serialVersionUID = 7843047273552528943L;

    public SourceTypePO() {
    }

    /**
     * 榜单来源 code
     */
    private Integer code;

    /**
     * 榜单来源 名称
     */
    private String name;


}
