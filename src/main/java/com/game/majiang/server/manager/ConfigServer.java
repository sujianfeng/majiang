package com.game.majiang.server.manager;

import com.game.majiang.base.dao.ConfigDao;
import com.game.majiang.base.dao.ManagerDao;
import com.game.majiang.base.entity.Config;
import com.game.majiang.base.entity.Manager;
import com.game.majiang.constants.ConfigConstants;
import com.game.majiang.constants.ManagerConstants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/4.
 */
@Service
public class ConfigServer {
    @Autowired
    private ConfigDao configDao;

    @Autowired
    private ManagerDao managerDao;

    public Config getStartBalance(){
        return configDao.findByType(ConfigConstants.START_BALANCE_TYPE);
    }

    public JSONObject updateStartBalance(Manager  manager,String balance){
        JSONObject jsonObject =new JSONObject();
        int code=0;
        if(manager.getLevel()!= ManagerConstants.ADMIN_MANAGER){
            code=2;
        }
        Config config= configDao.findByType(ConfigConstants.START_BALANCE_TYPE);
        config.setConfigValue(balance);
        configDao.save(config);
        code=1;
        jsonObject.put("code",code);
        return jsonObject;
    }
}
