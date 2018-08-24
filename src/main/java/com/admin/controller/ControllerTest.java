package com.admin.controller;
import com.admin.pojo.User;
import com.admin.service.base.SocketServer;
import com.admin.service.impl.LimtServiceImpl;
import com.admin.service.impl.UserServiceImpl;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultJson;
import utils.TimeUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/admin/controllerTest")
@Controller
public class ControllerTest {
    @Resource
    UserServiceImpl userService;
    @Autowired
    DruidDataSource druidDataSource;
    @Autowired
    LimtServiceImpl limtServiceImpl;
    @RequestMapping(value = "test")
    @ResponseBody
    public String test(){
        //controllerTest/test
        return "test";
    }
    @RequestMapping(value = "/redirect")
    public String redirect(){
       List<Map<String,Object>> lists=userService.query();
        System.out.println("数据;"+lists.toString());
        List<Map<String,Object>> list= userService.queryBySql("select * from sysadmin");
        System.out.println("数据;"+list.toString());
      Map<String,Object> map=   userService.queryOneById(10);
      System.out.println(map.toString());
        return "/admin/index.html";
    }
    @RequestMapping(value = "queryPage")
    @ResponseBody
    public PageInfo<Map<String,Object>> queryPage(Integer pageNum, Integer pageSize){
        return limtServiceImpl.pageQuery("select * from  syslimit where 1=1",pageNum,pageSize,"limitId desc");
    }
    @RequestMapping(value = "jsonTest")
    @ResponseBody
    public ResultJson jsonTest(){
        User user = new User();
        user.setUserDate(TimeUtil.stringToDate(new Date().toString()));
        return userService.insertOneByObjectReJson(user);
      // return limtServiceImpl.q
    }
    //oracle以及webscoket请求测试
    @RequestMapping(value = "oracleTest")
    @ResponseBody
    public List<Map<String,Object>> testOracle() {
        try {
            SocketServer.sendInfo("老子是群发后台来的");
        }catch (Exception e){
            e.printStackTrace();;
        }
        return    userService.queryBySql("select * from admin ");
    }
    @RequestMapping(value = "redisTest")
    @ResponseBody
    public List<Map<String,Object>> redisTest(){

        return userService.radisTest("select * from admin ");
    }
   //redis的测试
    @RequestMapping(value = "radisTestEvict")
    @ResponseBody
    public List<Map<String,Object>> radisTestEvict(){

        return userService.radisTestEvict("select * from admin ");
    }
    //session的测试
    @RequestMapping(value = "sessionTestSet")
    @ResponseBody
    public String sessionTestSet(HttpServletRequest request){
        request.getSession().setAttribute("message","session测试");
        return "session测试";
    }
    //session的测试
    @RequestMapping(value = "sessionTestGet")
    @ResponseBody
    public Object sessionTestGet(HttpServletRequest request){
        request.getSession().getAttribute("message");
        return request.getSession().getAttribute("message");
    }
}
