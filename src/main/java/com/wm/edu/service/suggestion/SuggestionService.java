package com.wm.edu.service.suggestion;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.cuser.T_user_suggestionMapper;
import com.wm.edu.model.cuser.T_user_suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService {

    @Autowired
    private T_user_suggestionMapper t_user_suggestionMapper;

    /**
     *
     * @param t_user_suggestion
     * @return
     */
    public Page<T_user_suggestion> getSuggestionList(T_user_suggestion t_user_suggestion){
        String orderStandesc=t_user_suggestion.getSidx()+" "+t_user_suggestion.getSord();
        Page<T_user_suggestion> page=PageHelper.startPage(t_user_suggestion.getPage(),t_user_suggestion.getRows(),orderStandesc);
        t_user_suggestionMapper.getSuggestionList(t_user_suggestion);
        return page;
    }

    public T_user_suggestion getSuggestionById(String suggestion_id){
        return t_user_suggestionMapper.selectByPrimaryKey(suggestion_id);
    }

    /**
     * 更新投诉建议
     * @param t_user_suggestion
     * @return
     */
    public String updateSuggestion(T_user_suggestion t_user_suggestion){
        String result="0";
        try {
            t_user_suggestionMapper.updateByPrimaryKeySelective(t_user_suggestion);
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }
        return result;
    }
}
