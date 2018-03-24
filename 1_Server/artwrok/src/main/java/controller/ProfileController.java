package controller;

import model.UserEntity;
import model.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.MsgRepository;
import repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by vivi on 2017/9/10.
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private MsgRepository msgRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity detail(@RequestParam(value="id") long id) {
        BaseEntity entity = new BaseEntity();
        //code=206
        List<UserEntity> users = userRepository.findById(id);
        if (users.size() == 0){
            entity.setCode(206);
            entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
        }
        else {
            UserEntity user = users.get(0);
            //code=200
            entity.setCode(200);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("user", user);
            entity.setData(dataMap);
        }
        return entity;
    }
}
