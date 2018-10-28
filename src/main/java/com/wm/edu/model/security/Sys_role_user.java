package com.wm.edu.model.security;

public class Sys_role_user {
    private int id;
    private int sys_user_id;
    private  int sys_role_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSys_user_id() {
        return sys_user_id;
    }

    public void setSys_user_id(int sys_user_id) {
        this.sys_user_id = sys_user_id;
    }

    public int getSys_role_id() {
        return sys_role_id;
    }

    public void setSys_role_id(int sys_role_id) {
        this.sys_role_id = sys_role_id;
    }
}
