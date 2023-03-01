package com.rainng.coursesystem.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@TableName("rc_sys")
@Data
public class SysEntity {

    public static final String ID = "sys_id";

    public static final String OPEN_CHOSE_COURSE = "open_chose_course";

    public static final String OPEN_CLOSE_COURSE = "open_close_course";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "必须选择开启或关闭")
    @TableField(OPEN_CHOSE_COURSE)
    private Integer openChoseCourse;

    @NotNull(message = "必须选择开启或关闭")
    @TableField(OPEN_CLOSE_COURSE)
    private Integer openCloseCourse;
}
