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

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.tls_sigature.GenTLSSignatureEx;


/**
 * Created by vivi on 2017/9/10.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private MsgRepository msgRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity register(@RequestParam(value="email") String email, @RequestParam(value="password") String password,
                               @RequestParam(value="name") String name, @RequestParam(value="avatar") String avatar, @RequestParam(value="birth") long birth,@RequestParam(value="sex") int sex) {
        BaseEntity entity = new BaseEntity();
        //code=202
        if(userRepository.findByEmail(email).size() != 0){
            entity.setCode(202);
            entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
        }
        //code=204
        else if(userRepository.findByName(name).size() != 0){
            entity.setCode(204);
            entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
        }
        //code=200
        else{
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setAvatar(Tools.def_img + avatar);
            user.setBirth(new Date(birth));
            user.setSex(sex);
            utils.tls_sigature.GenTLSSignatureResult result = null;
            try {
                result = GenTLSSignatureEx(1400077891, email, utils.tls_sigature.privStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setQqSign(result.urlSig);
            userRepository.save(user);
            user = userRepository.findByEmail(email).get(0);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("user", user);
            entity.setCode(200);
            entity.setData(dataMap);
        }
        return entity;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity login(@RequestParam(value="email") String email,@RequestParam(value="password") String password) {
        BaseEntity entity = new BaseEntity();
        //code=205
        List<UserEntity> users = userRepository.findByEmail(email);
        if (users.size() == 0){
            entity.setCode(205);
            entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
        }
        else {
            UserEntity user = users.get(0);
            //code=200
            if (user.getPassword().equals(password)){
                entity.setCode(200);
                Map<String,Object> dataMap = new HashMap<>();
                dataMap.put("user", user);
                entity.setData(dataMap);
            }
            else {
                entity.setCode(201);
                entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
            }
        }
        return entity;
    }
}
