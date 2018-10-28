package com.wm.edu.service.record;

import com.wm.edu.mapper.record.T_payment_recordMapper;
import com.wm.edu.model.record.T_payment_record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentRecordService {

    @Autowired
    private T_payment_recordMapper t_payment_recordMapper;

    public T_payment_record getRecordById(String id){
        return t_payment_recordMapper.selectByPrimaryKey(id);
    }

    public Integer insertRecord(T_payment_record record){
        Integer code=200;
        try {
            t_payment_recordMapper.insertSelective(record);
        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }
        return code;
    }

    public void deleteRecord(String id){
        t_payment_recordMapper.deleteByPrimaryKey(id);
    }

    public List<T_payment_record> getPayRecord(String c_user_id){
        return t_payment_recordMapper.getPayList(c_user_id);
    }
}
