package com.lockie.common.util;

import com.lockie.common.enums.IdEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author: lockie
 * @Date: 2020/11/3 14:30
 * @Description: 雪花算法ID生成器
 */
@Configuration
public class IdGenerateUtil {
    /**
     * 使用该注解方式的话，服务的APP需要扫描common包，导致需要增加以下代码
     *     @SpringBootApplication(scanBasePackages = {"com.dstcar.common.utils.uuid", "com.dst" })
     *     在使用类中需要注入该工具类  @Autowired  private IdGenerateUtil idGenerateUtil;
     *     idGenerateUtil.getPrimaryKey()获取ID
     */
    @Value("${spring.application.name}")
    private String serverName;

    private Snowflake snowflake;

    @PostConstruct
    public void createSnowflake() {
        IdEnum idEnum = IdEnum.getIdByServerName(serverName);
        snowflake = new Snowflake(idEnum.getWorkId(), idEnum.getDataCenterId());
    }

    /**
     * 获取主键ID
     * @return
     */
    public Long getPrimaryKey() {
        return snowflake.nextId();
    }
}
