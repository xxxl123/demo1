import * as ajax from "../../common/ajax";

export const list = () => ajax.pureGet("/student/course/list");
export const getReturnCourseState =() =>ajax.get("/student/course/select/getReturnCourseState");  

export const deleteItem = studentCourseId =>
  ajax.pureDelete("/student/course/" + studentCourseId);
