package com.admin.pojo;
import lombok.Data;

import java.util.Date;

@Data
public class User {
        private    Integer	 userId;  //int(11) NOT NULL AUTO_INCREMENT ,
        private    String  userName;  //varchar(255) '用户名' ,
        private	    String  userPassword; // varchar(255)  '用户密码' ,
        private 	String  userPhone;  //varchar(255)  '用户电话' ,
        private 	String  userEmail; // varchar(255)  '用户邮箱' ,
        private 	String  userRoleName;  //varchar(255)  '用户角色' ,
        private	    String  userQuestion; // varchar(255)  '用户密码找回' ,
        private    Integer  userDel;  //int(11) '用户是否启用' ,
        private 	String  userAnswer;  //varchar(255) '用户密码找回的问题答案' ,
        private 	String  userShopName; // varchar(255)  '用户管理分类' ,
        private 	String  userSectionName;  //varchar(255)  '部门Id' ,
        private    Date    userDate; // datetime  '注册日期' ,
        private    String userPictureUri;//用户图片

        public void setUserDate(Date userDate) {
                this.userDate = userDate;
        }

        public Date getUserDate() {
                return userDate;
        }


}
