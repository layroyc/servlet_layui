package dao;

import bean.Master;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDao {
    //登录
    public Master login(String account, String password){
        Master master =null;
        //创建链接
        Connection conn= DBHelper.getConnection();
        //创建sql 语句
        String sql="select * from master where account=? and password=?";
        //获取预编译对象
        PreparedStatement ps=null;
        //执行预编译对象
        ResultSet rs=null;
        try {
            //获取预编译对象
            ps=conn.prepareStatement(sql);
            ps.setString(1,account);
            ps.setString(2,password);
            //执行预编译对象
            rs=ps.executeQuery();
            if(rs.next()){
                master=new Master();
                master.setId(rs.getInt("id"));
                master.setName(rs.getString("name"));
                master.setSex(rs.getString("sex"));
                master.setAge(rs.getString("age"));
                master.setAccount(rs.getString("account"));
                master.setPassword(rs.getString("password"));
                master.setDid(rs.getString("did"));
                master.setPhone(rs.getString("phone"));
                master.setDel(rs.getString("del"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return master;

    }

    //查询全查 select * from t_user
    public List<Master> selectAllByService(){
        ArrayList<Master> masters = new ArrayList<>();
        //1.创建出 连接对象
        Connection connection = DBHelper.getConnection();
        //2.创建出SQL语句
        String sql = "select * from master";
        //3.使用连接对象 获取 预编译对象
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            //4.执行预编译，得到结果集
            rs = ps.executeQuery();
            //5.遍历结果集
            while (rs.next()){
                System.out.println("account = " + rs.getString("account"));
                Master master = new Master();
                master.setId(rs.getInt("id"));
                master.setName(rs.getString("name"));
                master.setSex(rs.getString("sex"));
                master.setAge(rs.getString("age"));
                master.setAccount(rs.getString("account"));
                master.setPassword(rs.getString("password"));
                master.setDid(rs.getString("did"));
                master.setPhone(rs.getString("phone"));
                master.setDel(rs.getString("del"));
                masters.add(master);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return masters;
    }

    // 删除
    public int deleteByMaster(Integer id) {
        //1.加载连接
        Connection connection = DBHelper.getConnection();
        //2.书写sql语句
        String sql = "delete from master where id=?";
        //预编译
        PreparedStatement ps = null;
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            //执行
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return i;

        }
    }

    //修改
    public int updateMaster(Master master){
        //1.创建出 连接对象
        Connection conn = DBHelper.getConnection();
        //2.创建出SQL语句
        String sql = "update master set name=?,sex=?,age=?,account=?,password=?,did=?,phone=?,del=? where id=?";
        //3.创建preparedStatement,执行sql
        PreparedStatement ps = null;
        int i = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,master.getName());
            ps.setString(2,master.getSex());
            ps.setString(3,master.getAge());
            ps.setString(4,master.getAccount());
            ps.setString(5,master.getPassword());
            ps.setString(6,master.getDid());
            ps.setString(7,master.getPhone());
            ps.setString(8,master.getDel());
            ps.setInt(9,master.getId());
            i= ps.executeUpdate();
            System.out.println("成功修改"+i+"条数据");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    //根据 id 做查询
    public Master selectMasterById(Integer id){
        Master master = new Master();
        //1.创建出 连接对象
        Connection conn = DBHelper.getConnection();
        //2.创建出SQL语句
        String sql ="select * from master where id=?";
        //3.创建preparedStatement,执行sql
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                master.setName(rs.getString("name"));
                master.setSex(rs.getString("sex"));
                master.setAge(rs.getString("age"));
                master.setAccount(rs.getString("account"));
                master.setPassword(rs.getString("password"));
                master.setDid(rs.getString("did"));
                master.setPhone(rs.getString("phone"));
                master.setDel(rs.getString("del"));
                master.setId(rs.getInt("id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return master;
    }

    //新增
    public int addMaster(Master master){
        //1 创建连接对象
        Connection conn=DBHelper.getConnection();
        //2 sql语句 因为添加的数据是变量 所以用？代替
        String sql="insert into master values (null,?,?,?,?,?,?,?,?)";
        //3 获取预编译sql
        PreparedStatement ps=null;
        //4 执行预编译对象
        int i=0;
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,master.getName());
            ps.setString(2,master.getSex());
            ps.setString(3,master.getAge());
            ps.setString(4,master.getAccount());
            ps.setString(5,master.getPassword());
            ps.setString(6,master.getDid());
            ps.setString(7,master.getPhone());
            ps.setString(8,master.getDel());
            //4 执行预编译对象
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close(); //关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void main(String[] args) {
        //全查
        MasterDao masterDao = new MasterDao();
        /*List<Master> masters = masterDao.selectAllByService();
        for (Master master : masters) {
            System.out.println("master = " + master);
        }*/

        //删除
        /*int i = masterDao.deleteByMaster(9);
        System.out.println("i = " + i);*/

        /*修改*/
//        Scanner sc= new Scanner(System.in);
//        System.out.println("你选择了要修改的id：");
//        int id = sc.nextInt();
//
//        System.out.println("请输入要修改的姓名：");
//        String name = sc.next();
//        System.out.println("请输入要修改的性别：");
//        String sex = sc.next();
//        System.out.println("请输入要修改的年龄：");
//        String age = sc.next();
//        System.out.println("请输入要修改的账号：");
//        String account = sc.next();
//        System.out.println("请输入要修改的用户密码：");
//        String password = sc.next();
//        System.out.println("请输入要修改的部门ID：");
//        String did = sc.next();
//        System.out.println("请输入要修改的手机号：");
//        String phone = sc.next();
//        System.out.println("请输入要修改是否删除：");
//        String del = sc.next();
//
//
//        Master m = new Master(id,name,sex,age,account,password,did,phone,del);
//        System.out.println(m.toString());
//        int i = masterDao.updateMaster(m);
//        if(i>0){
//            System.out.println("恭喜你修改成功！");
//        }else{
//            System.out.println("修改失败！");
//        }

        //单查
        /*Master id = masterDao.selectMasterById(10);
        System.out.println("id = " + id);*/

        //添加
        /*Master master = new Master();
        master.setName("啧啧啧");
        master.setSex("男");
        master.setAge("27");
        master.setAccount("zeze");
        master.setPassword("123");
        master.setPhone("14785236932");
        master.setDel("0");
        int i = masterDao.addMaster(master);
        System.out.println("i = " + i);*/
    }
}
