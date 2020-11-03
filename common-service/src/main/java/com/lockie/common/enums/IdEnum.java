package com.lockie.common.enums;

/**
 * @author: lockie
 * @Date: 2020/11/3 14:20
 * @Description: 雪花算法生成ID
 */
public enum  IdEnum {
    /** 默认机器 **/
    ID_DEFAULT("default",0L,1L),
    ID_ORDER_SERVER("default",0L,1L),
    ID_USER_SERVER("default",0L,1L),
    ID_ADDRESS_SERVER("default",0L,1L);


    private final String serverName;
    private final Long workId;
    private final Long dataCenterId;

    IdEnum(String serverName, Long workId, Long dataCenterId) {
        this.serverName = serverName;
        this.workId = workId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 根据服务获取ID生成器
     * @param serverName
     * @return
     */
    public static IdEnum getIdByServerName(String serverName) {
        for (IdEnum idEnum : IdEnum.values()) {
            if (idEnum.serverName.equals(serverName)) {
                return idEnum;
            }
        }
        return ID_DEFAULT;
    }

    public String getServerName() {
        return serverName;
    }

    public Long getWorkId() {
        return workId;
    }

    public Long getDataCenterId() {
        return dataCenterId;
    }
}
