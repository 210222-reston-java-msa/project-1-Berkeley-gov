package com.revature.models;

import java.util.Objects;

public class Role {
    private int roleId;
    private String roleName;

    // Role Constructors
    public Role() {

    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(int roleId, String roleName) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
    }

    // Role Getter and Setters
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // Role Overriden Methods
    @Override
    public String toString() {
        return "Role {" +
                "roleId = " + roleId +
                ", roleName = '" + roleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getRoleId() == role.getRoleId() && Objects.equals(getRoleName(), role.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId(), getRoleName());
    }
}
