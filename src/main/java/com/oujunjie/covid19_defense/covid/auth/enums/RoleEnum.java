package com.oujunjie.covid19_defense.covid.auth.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @despriction
 * @auther ChunKitAu
 * @create 2020-02-27 27
 */
public enum RoleEnum {

    /**
     * 普通用户
     */
    USER(0L, "user"),

    /**
     * 管理员
     */
    ADMIN(1L, "admin"),


    /**
     * 超级用户
     */
    ROOT(2L, "root");

    private static final Map<Long, RoleEnum> enumTypeMap = new HashMap<>();


    static {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            enumTypeMap.put(roleEnum.getRoleId(), roleEnum);
        }
    }

    private final Long roleId;

    private final String roleName;

    RoleEnum(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public static Map<Long, RoleEnum> getEnumTypeMap() {
        return enumTypeMap;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
