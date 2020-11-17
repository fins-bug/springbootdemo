package work.finsbug.auth.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description Jwt的自定义配置类
 * @author fins
 * @date 2020/11/17
 **/
@Data
@ConfigurationProperties(value = "security.jwt")
public class JwtProperties {
    private Integer tokenExpirationTime;
    private String tokenIssuer;
    private String tokenSigningKey;
    private Integer refreshTokenExpTime;
}
