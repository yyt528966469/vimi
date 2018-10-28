package com.wm.edu.controller.suggestion;

import com.github.pagehelper.Page;
import com.wm.edu.model.cuser.T_user_suggestion;
import com.wm.edu.service.suggestion.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/suggestion")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping("/main")
    public String main(){
        return "suggestion/suggestionList";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(T_user_suggestion t_user_suggestion){
        Map<String,Object> map=new HashMap<>();
        Page<T_user_suggestion> page=suggestionService.getSuggestionList(t_user_suggestion);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }

    @RequestMapping("/suggestionDetails")
    public String suggestionDetails(String suggestion_id, Model model){
        T_user_suggestion suggestion=suggestionService.getSuggestionById(suggestion_id);
        model.addAttribute("suggestion",suggestion);
        return "suggestion/suggestionDetails";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/updateSuggestion")
    @ResponseBody
    public Map<String,Object> updateSuggestion(T_user_suggestion t_user_suggestion){
        Map<String,Object> map=new HashMap<>();
        t_user_suggestion.setStatus(1);
        String result=suggestionService.updateSuggestion(t_user_suggestion);
        map.put("result",result);
        return map;
    }
}
