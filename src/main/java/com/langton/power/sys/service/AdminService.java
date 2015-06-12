package com.langton.power.sys.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.langton.power.sys.bean.AdminBean;
import com.langton.power.sys.bean.Right;
import com.langton.power.sys.dao.impl.AdminRightDao;
import com.langton.power.sys.dao.impl.CommonDao;

@Service
public class AdminService {

    @Resource
    private AdminRightDao rightDao;
    @Resource
    private CommonDao commonDao;

    /**
     * 加载左侧导航栏
     * 
     * @param adminId
     * @return json string
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public String naviTree(int adminId) {
        AdminBean bean = commonDao.findById(AdminBean.class, "id", adminId);
        if (bean == null) {
            return "[]";
        }
        List<Right> rights = null;
        if (bean.getType().equalsIgnoreCase(AdminBean.Type.SUPER)) {
            rights = rightDao.listRights();
        } else { // 普通管理员
            rights = bean.getRights();
        }
        if(rights == null){
            return "[]";
        }
        LinkedHashMap<String, ArrayList<String[]>> map = new LinkedHashMap<String, ArrayList<String[]>>();
        for (Right right : rights) {
            if (map.containsKey(right.getRightGroup())) {
                map.get(right.getRightGroup()).add(new String[] { right.getRightName(),right.getRightPath() });
                continue;
            }
            ArrayList<String[]> arrayList = new ArrayList<String[]>();
            arrayList.add(new String[] { right.getRightName(), right.getRightPath() });
            map.put(right.getRightGroup(), arrayList);
        }
        JSONArray root = new JSONArray();
        int i = 1;
        for (String key : map.keySet()) {
            JSONObject item = new JSONObject(true);
            item.put("groupId", i++);
            item.put("groupName", key);
            ArrayList<String[]> list = map.get(key);
            JSONArray childs = new JSONArray();
            for (String[] s : list) {
                JSONObject jsonObject = new JSONObject(true);
                jsonObject.put("name", s[0]);
                jsonObject.put("path", s[1]);
                childs.add(jsonObject);
            }
            item.put("groupChilds", childs);
            root.add(item);
        }
        return root.toString();
    }

    @Transactional
    public void changeAdminRights(int adminId, String... ids) {
        rightDao.changeAdminRight(adminId, ids);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Right> listRights() {
        return rightDao.listRights();
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Right> loadRight(int adminId) {
        return rightDao.loadRight(adminId);
    }

}
