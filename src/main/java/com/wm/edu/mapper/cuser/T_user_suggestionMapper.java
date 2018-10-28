package com.wm.edu.mapper.cuser;

import com.wm.edu.model.cuser.T_user_suggestion;

import java.util.List;

public interface T_user_suggestionMapper {
    int deleteByPrimaryKey(String suggestion_id);

    int insert(T_user_suggestion record);

    int insertSelective(T_user_suggestion record);

    T_user_suggestion selectByPrimaryKey(String suggestion_id);

    int updateByPrimaryKeySelective(T_user_suggestion record);

    int updateByPrimaryKey(T_user_suggestion record);

    List<T_user_suggestion> getSuggestionList(T_user_suggestion t_user_suggestion);
}