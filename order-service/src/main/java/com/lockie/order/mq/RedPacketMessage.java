package com.lockie.order.mq;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: zouxiliang
 * @Date: 2020/7/8 11:08
 * @Description:
 */
@Getter
@Setter
public class RedPacketMessage implements Serializable {
    private long redPacketId;
    private long timestamp;

    public RedPacketMessage() {
    }

    public RedPacketMessage(long redPacketId) {
        this.redPacketId = redPacketId;
        this.timestamp = System.currentTimeMillis();
    }
}
