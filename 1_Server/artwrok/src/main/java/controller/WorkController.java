package controller;

import model.TypeEntity;
import model.UserEntity;
import model.Work;
import model.WorkEntity;
import model.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.MsgRepository;
import repository.TypeRepository;
import repository.UserRepository;
import repository.WorkRepository;
import utils.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by vivi on 2017/9/10.
 */
@Controller
@RequestMapping(value = "/work")
public class WorkController {

    @Autowired
    private MsgRepository msgRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private TypeRepository typeRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity list(@RequestParam(value="uid") int uid, @RequestParam(value="type_id") int type_id,
                           @RequestParam(value="img") String img){
        BaseEntity entity = new BaseEntity();
        //code=206
        List<UserEntity> users = userRepository.findById(uid);
        if (users.size() == 0){
            entity.setCode(206);
            entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
        }
        else {
            //code=207
            List<TypeEntity> types = typeRepository.findById(type_id);
            if (types.size() == 0){
                entity.setCode(207);
                entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
            }
            //code=207
            else {
                WorkEntity work = new WorkEntity();
                work.setUid(uid);
                work.setTypeId(type_id);
                work.setUrl(Tools.def_img + img);
                workRepository.save(work);
                entity.setCode(200);
            }
        }
        return entity;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity user(@RequestParam(value="uid") int uid) {
        BaseEntity entity = new BaseEntity();
        //code=206
        List<UserEntity> users = userRepository.findById(uid);
        if (users.size() == 0){
            entity.setCode(206);
            entity.setMsg(msgRepository.findOne(entity.getCode()).getMsg());
        }
        else{
            //code=200
            entity.setCode(200);
            List<WorkEntity> works  = workRepository.findByUid(uid);
            ArrayList<Work> data = new ArrayList<>();
            for (WorkEntity work: works) {
                data.add(new Work(work, userRepository.findOne(work.getUid())));
            }
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("works", data);
            entity.setData(dataMap);
        }
        return entity;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity list(@RequestParam(value="id") int id) {
        BaseEntity entity = new BaseEntity();
        List<WorkEntity> works;
        if (id == -1){
            works = workRepository.findAll();
        }
        else {
            works = workRepository.findByTypeId(id);
        }
        ArrayList<Work> data = new ArrayList<>();
        for (WorkEntity work: works) {
            data.add(new Work(work, userRepository.findOne(work.getUid())));
        }
        //code=200
        entity.setCode(200);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("works", data);
        entity.setData(dataMap);
        return entity;
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity type() {
        BaseEntity entity = new BaseEntity();
        //code=200
        entity.setCode(200);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("types", typeRepository.findAll());
        entity.setData(typeRepository.findAll());
        return entity;
    }
}
