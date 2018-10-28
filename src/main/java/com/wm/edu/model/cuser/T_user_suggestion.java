package com.wm.edu.model.cuser;

import com.wm.edu.model.base.PageEntity;

import java.util.Date;

public class T_user_suggestion extends PageEntity {
    private String suggestion_id;

    private String c_user_id;

    private String title;

    private String reason;

    private Integer status;

    private Date create_time;

    private String answer;

    private String reserved1;

    private C_user_info c_user_info;

    public C_user_info getC_user_info() {
        return c_user_info;
    }

    public void setC_user_info(C_user_info c_user_info) {
        this.c_user_info = c_user_info;
    }

    public String getSuggestion_id() {
        return suggestion_id;
    }

    public void setSuggestion_id(String suggestion_id) {
        this.suggestion_id = suggestion_id == null ? null : suggestion_id.trim();
    }

    public String getC_user_id() {
        return c_user_id;
    }

    public void setC_user_id(String c_user_id) {
        this.c_user_id = c_user_id == null ? null : c_user_id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }
}