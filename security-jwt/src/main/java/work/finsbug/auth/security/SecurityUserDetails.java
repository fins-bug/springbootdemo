package work.finsbug.auth.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @description
 * @author fins
 * @date 2020/11/17
 **/
@Data
@NoArgsConstructor
@Accessors(fluent = true)
public class SecurityUserDetails implements UserDetails {

    /**
     * 用户密码
     */
    private String password;

    /**
     * 账号
     */
    private String username;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户权限
     */
    private List<GrantedAuthority> authorities;

    /**
     * 构造函数
     * @param id 用户id
     * @param username 账号
     * @param authorities 权限列表
     */
    public SecurityUserDetails(Integer id, String username, List<GrantedAuthority> authorities) {
        this.userId = id;
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 是否过期
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 是否禁用
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 是否凭证过期
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否启用
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
