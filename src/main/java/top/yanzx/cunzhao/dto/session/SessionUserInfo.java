package top.yanzx.cunzhao.dto.session;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 保存在session中的用户信息
 */
@Data
public class SessionUserInfo {
    private int userId;
    private String username;
    private String nickname;
    private String phone_number;
    private List<Integer> roleIds;
    private Set<String> menuList;
    private Set<String> permissionList;
}
