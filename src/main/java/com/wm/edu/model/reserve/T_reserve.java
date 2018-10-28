package com.wm.edu.model.reserve;

import com.wm.edu.model.base.PageEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class T_reserve extends PageEntity {
    private String id;

    private String c_user_id;

    private Integer person_num;

    private Date reserve_time;

    private String position;

    private Date create_time;

    private Integer status;

    private String name;

    private String phone;

    private String sex;

    private String remarks;

    private String status_name;

    private String r_date;

    private String r_time;

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getR_date() {
        SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
        if(reserve_time!=null){
            r_date=sdf.format(reserve_time);
        }
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    public String getR_time() {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        if(reserve_time!=null){
            r_time=sdf.format(reserve_time);
        }
        return r_time;
    }

    public void setR_time(String r_time) {
        this.r_time = r_time;
    }

    public String getStatus_name() {
        if(status!=null){
            status_name=ReserveEnum.getName(status);
        }
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getC_user_id() {
        return c_user_id;
    }

    public void setC_user_id(String c_user_id) {
        this.c_user_id = c_user_id == null ? null : c_user_id.trim();
    }

    public Integer getPerson_num() {
        return person_num;
    }

    public void setPerson_num(Integer person_num) {
        this.person_num = person_num;
    }

    public Date getReserve_time() {
        return reserve_time;
    }

    public void setReserve_time(Date reserve_time) {
        this.reserve_time = reserve_time;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}