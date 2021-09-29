package service;

import bean.Master;
import dao.MasterDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterService {
    //登录
    public Map login(String account, String password, HttpServletRequest request){
        Map map = new HashMap();
        //service 层要调用dao层
        MasterDao masterDao = new MasterDao();
        Master masterFromDB = masterDao.login(account, password);
        if(null == masterFromDB){
            //没查出来，即：账户名或密码不正确
            map.put("code",4001);
            map.put("msg","账户或密码不正确");
            return map;
        }else{
            //当登陆成功后，把信息放入到session作用域中
            HttpSession session = request.getSession();
            session.setAttribute("master",masterFromDB);
            map.put("code",0);
            map.put("msg","登陆成功");
            //给一个账户  给前端进行渲染
            map.put("account",account);

            return map;
        }
    }

    //全查
    public Map selectAllByService(){
        MasterDao dao=new MasterDao();
        List<Master> masters=dao.selectAllByService();
        Map codeMap=new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","ok");
        codeMap.put("data",masters);
        return codeMap;
    }

    //单行删除
    public Map delMaster(Integer id){
        Map codeMap = new HashMap();
        MasterDao dao = new MasterDao();
        int i = dao.deleteByMaster(id);
        codeMap.put("code",0);
        codeMap.put("msg","ok");
        codeMap.put("data",i);
        return codeMap;
    }

    //修改全部
    public Map updateMaster(Master master){
        Map codeMap = new HashMap();
        MasterDao dao = new MasterDao();
        int i = dao.updateMaster(master);
        if(i==1){
            codeMap.put("code",0);
            codeMap.put("msg","修改成功");
        }else{
            codeMap.put("code",400);
            codeMap.put("msg","修改不成功");
        }
        return codeMap;
    }

    //按照id 查询1个master
    public Map selectUserById(Integer id){
        MasterDao dao = new MasterDao();
        Master master = dao.selectMasterById(id);
        Map codeMap = new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","ok");
        codeMap.put("data",master);
        return codeMap;

    }

    //增加
    public Map insertMaster(Master master){
        System.out.println("进入到 service 层了---");
        Map map = new HashMap();
        MasterDao masterDao = new MasterDao();
        System.out.println("masterDao = " + masterDao);
        int i = masterDao.addMaster(master);
        System.out.println("i = " + i);
        if(i==1){
            map.put("code",0);
            map.put("msg","添加成功");
        }else{
            map.put("code",400);
            map.put("msg","添加不成功");
        }
        return map;
    }
}
