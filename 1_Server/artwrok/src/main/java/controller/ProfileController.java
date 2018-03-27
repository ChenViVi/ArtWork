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
import utils.Tools;

import java.sql.Date;
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
    public BaseEntity detail(@RequestParam(value="email") String email) {
        BaseEntity entity = new BaseEntity();
        //code=206
        List<UserEntity> users = userRepository.findByEmail(email);
        if (users.size() == 0){
            entity.setCode(206);
            entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
        }
        else {
            UserEntity user = users.get(0);
            //code=200
            entity.setCode(200);
            entity.setData(user);
        }
        return entity;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity edit(@RequestParam(value="uid") int uid, @RequestParam(value="name") String name, @RequestParam(value="avatar") String avatar,
                           @RequestParam(value="birth") long birth,@RequestParam(value="sex") int sex) {
        BaseEntity entity = new BaseEntity();
        //code=206
        List<UserEntity> users = userRepository.findById(uid);
        if (users.size() == 0){
            entity.setCode(206);
            entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
        }
        else {
            UserEntity user = users.get(0);
            user.setName(name);
            user.setAvatar(Tools.def_img + avatar);
            user.setBirth(new Date(birth));
            user.setSex(sex);
            //code=200
            userRepository.save(user);
            entity.setCode(200);
            entity.setData(user);
        }
        return entity;
    }
}
