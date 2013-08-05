package com.xpsoft.oa.action.hrm;


import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.TrainCourse;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.hrm.TrainCourseService;
import com.xpsoft.oa.service.system.FileAttachService;
/**
 * 
 * @author 
 *
 */
public class TrainCourseAction extends BaseAction{
	@Resource
	private TrainCourseService trainCourseService;
	private TrainCourse trainCourse;
	@Resource
	private  FileAttachService fileAttachService;
	
	private Long courseId;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public TrainCourse getTrainCourse() {
		return trainCourse;
	}

	public void setTrainCourse(TrainCourse trainCourse) {
		this.trainCourse = trainCourse;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		QueryFilter filter=new QueryFilter(getRequest());
		List<TrainCourse> list= trainCourseService.getAll(filter);
		Type type=new TypeToken<List<TrainCourse>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				trainCourseService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		TrainCourse trainCourse=trainCourseService.get(courseId);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(trainCourse));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		trainCourseService.save(trainCourse);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public String listNew(){
		QueryFilter filter=new QueryFilter(getRequest());
		List<TrainCourse> list= trainCourseService.findByNewList(filter.getPagingBean());
		Type type=new TypeToken<List<TrainCourse>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		jsonString=buff.toString();
		return SUCCESS;
	}
	
	public  String  upload() throws Exception{
        String trainFileIds =getRequest().getParameter("trainFileIds");
        String[]ids =trainFileIds.split(",");
		String[] filePath = new String[ids.length];
		for(int i = 0; i<ids.length; i++){
			FileAttach fileAttach = fileAttachService.get(new Long(ids[i]));
			filePath[i] = ServletActionContext.getServletContext().getRealPath("/") + "attachFiles/" + fileAttach.getFilePath();
		}
		List<TrainCourse> list = TrainCourseJxlRead.ready2(filePath);
		trainCourseService.saveFile(list);
		for(int i = 0; i<ids.length; i++){
			FileAttach fileAttach = fileAttachService.get(new Long(ids[i]));
			fileAttachService.removeByPath(fileAttach.getFilePath());
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
