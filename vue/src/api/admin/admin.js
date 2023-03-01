import * as ajax from "../../common/ajax";

export const get = id => ajax.get("/admin/admin/" + id);

export const create = entity => ajax.post("/admin/admin", entity);

export const deleteItem = id => ajax.pureDelete("/admin/admin/" + id);

export const update = entity => ajax.put("/admin/admin", entity);

export const list = () => ajax.pureGet("/admin/admin");

export const cancelChoseCourse = ()=>ajax.pureDelete("/admin/admin/cancelChoseCourse")

export const openChoseCourse = ()=>ajax.post("/admin/admin/openChoseCourse")

export const cancelReturnCourse = ()=>ajax.pureDelete("/admin/admin/cancelReturnCourse")

export const openReturnCourse = ()=>ajax.post("/admin/admin/openReturnCourse")

