package com.rainng.coursesystem.service.admin;

import com.rainng.coursesystem.manager.admin.AdminManager;
import com.rainng.coursesystem.manager.admin.SysManager;
import com.rainng.coursesystem.model.entity.AdminEntity;
import com.rainng.coursesystem.model.entity.SysEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.BaseService;
import com.rainng.coursesystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends BaseService {
    private final AdminManager manager;
    private final UserService userService;
    private final SysManager sysManager;

    public AdminService(AdminManager manager, UserService userService,SysManager sysManager) {
        this.sysManager = sysManager;
        this.manager = manager;
        this.userService = userService;
    }

    public ResultVO get(Integer id) {
        AdminEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("管理员Id: " + id + "不存在!");
        }

        entity.setPassword("");

        return result(entity);
    }

    public ResultVO update(AdminEntity entity) {
        AdminEntity originEntity = manager.get(entity.getId());
        if (originEntity == null) {
            return failedResult("管理员Id: " + entity.getId() + "不存在!");
        }

        if (entity.getPassword().equals("")) {
            entity.setPassword(originEntity.getPassword());
        } else {
            entity.setPassword(userService.computePasswordHash(entity.getPassword()));
        }


        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("管理员Id: " + id + "不存在!");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(AdminEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("管理员Id: " + entity.getId() + "已存在!");
        }

        manager.create(entity);
        return result("添加成功");
    }
    public void cancelChoseCourse(){
        SysEntity sys = new SysEntity();
        sys.setId(1);
        sys.setOpenChoseCourse(0);
        sysManager.changeChoseCourseState(sys);
    }
    public void openChoseCourse(){
        SysEntity sys = new SysEntity();
        sys.setOpenChoseCourse(1);
        sys.setId(1);
        sysManager.changeChoseCourseState(sys);
    }

    public void cancelReturnCourse(){
        SysEntity sys = new SysEntity();
        sys.setId(1);
        sys.setOpenCloseCourse(0);
        sysManager.changeCloseCourseState(sys);
    }
    public void openReturnCourse(){
        SysEntity sys = new SysEntity();
        sys.setOpenCloseCourse(1);
        sys.setId(1);
        sysManager.changeCloseCourseState(sys);
    }



    public ResultVO list() {
        return result(manager.list());
    }
}
