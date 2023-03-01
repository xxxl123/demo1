package com.rainng.coursesystem.manager.admin;

import com.rainng.coursesystem.dao.SysDao;
import com.rainng.coursesystem.manager.BaseManager;
import com.rainng.coursesystem.model.entity.SysEntity;
import org.springframework.stereotype.Component;

@Component
public class SysManager extends BaseManager {

    private final SysDao sysDao;


    public SysManager(SysDao sysDao) {
        this.sysDao = sysDao;
    }

    //改变选课状态
    public void changeChoseCourseState(SysEntity entity){
        sysDao.update(entity);
    }
    public int getChoseCourseState(){
        return sysDao.getChoseCourseState();
    }
    //改变退课状态
    public void changeCloseCourseState(SysEntity sys){
        sysDao.update(sys);
    }

    public int getCloseCourseState(){
        return sysDao.getCloseCourseState();
    }

}
