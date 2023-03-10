package com.rainng.coursesystem.service.student;

import com.rainng.coursesystem.manager.OptionManager;
import com.rainng.coursesystem.manager.admin.SysManager;
import com.rainng.coursesystem.manager.student.CourseSelectManager;
import com.rainng.coursesystem.model.bo.StudentCourseSelectItemBO;
import com.rainng.coursesystem.model.entity.CourseEntity;
import com.rainng.coursesystem.model.entity.StudentCourseEntity;
import com.rainng.coursesystem.model.entity.StudentEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.model.vo.response.table.StudentCourseSelectItemVO;
import com.rainng.coursesystem.service.BaseService;
import com.rainng.coursesystem.util.LessonTimeConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseSelectService extends BaseService {
    private final CourseSelectManager manager;
    private final OptionManager optionManager;
    private final LessonTimeConverter lessonTimeConverter;

    private final SysManager sysManager;


    public CourseSelectService(CourseSelectManager manager, OptionManager optionManager, LessonTimeConverter lessonTimeConverter,SysManager sysManager) {
        this.sysManager = sysManager;
        this.manager = manager;
        this.optionManager = optionManager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO getPageCount(String courseName, String teacherName) {
        Integer studentId = getUserId();
        return result(manager.getPageCount(studentId, courseName, teacherName));
    }

    public ResultVO getPage(Integer index, String courseName, String teacherName) {
        Integer studentId = getUserId();

        List<StudentCourseSelectItemBO> boList = manager.getPage(index, studentId, courseName, teacherName);
        List<StudentCourseSelectItemVO> voList = new ArrayList<>(boList.size());

        for (StudentCourseSelectItemBO bo : boList) {
            StudentCourseSelectItemVO vo = new StudentCourseSelectItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    public ResultVO create(Integer courseId) {
        Integer studentId = getUserId();

        if (!optionManager.getAllowStudentSelect()) {
            return failedResult("????????????????????????!");
        }
        StudentEntity student = manager.getStudentById(studentId);
        CourseEntity course = manager.getCourseById(courseId);
        if (student == null) {
            return failedResult("??????Id:" + studentId + "?????????!");
        }
        if (course == null) {
            return failedResult("??????Id:" + courseId + "?????????!");
        }
        if (!manager.inSameDepartment(courseId, studentId)) {
            return failedResult("???????????????????????????????????????!");
        }
        if (course.getSelectedCount() >= course.getMaxSize()) {
            return failedResult("???????????????!");
        }
        if (manager.getStudentCourseByCourseIdAndStudentId(courseId, studentId) != null) {
            return failedResult("????????????????????????!");
        }
        if (!manager.getStudentGradeById(student.getId()).equals(course.getGrade())) {
            return failedResult("?????????????????????????????????");
        }
        String timePart = splitTimePart(course.getTime());
        if (manager.countStudentCourseSelectedByTimePart(studentId, timePart) > 0) {
            return failedResult("??????????????????!");
        }

        StudentCourseEntity studentCourse = new StudentCourseEntity();
        studentCourse.setCourseId(courseId);
        studentCourse.setStudentId(studentId);
        manager.create(studentCourse);

        return result("????????????");
    }

    private String splitTimePart(String time) {
        String[] spilt = time.split("-");
        return spilt[0] + "-" + spilt[1];
    }

    public ResultVO getChoseCourseState(){
        int choseCourseState = sysManager.getChoseCourseState();
        return result(choseCourseState,"????????????");
    }
    public ResultVO getReturnCourseState(){
        int ReturnCourseState = sysManager.getCloseCourseState();
        return result(ReturnCourseState,"????????????");
    }
}
