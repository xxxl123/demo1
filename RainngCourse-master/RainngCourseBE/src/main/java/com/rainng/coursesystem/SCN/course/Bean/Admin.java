package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

@Data
public class Admin {
    private int admin_id;//管理员id
    private String admin_username;//用户名
    private String admin_password;//密码
    private int admin_privilege;//权限
    private int flag;//表示删除
}
