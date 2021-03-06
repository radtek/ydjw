package com.ehootu.correct.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 吸毒类重点人员动态管控
 * 
 * @author Liaoxb
 * @date 2017/11/30
 */
public class DrugControlEntity implements Serializable {

	
    /**
	 * id
	 */
	private Integer id;
    /**
	 * 所属派出所
	 */
	private String policeStation;
    /**
	 * 所属警务室
	 */
	private String policeOffice;
    /**
	 * 吸毒者姓名
	 */
	private String drugAddicts;
    /**
	 * 见面时间
	 */
	private Date meetingTime;
    /**
	 * 见面地点
	 */
	private String meetingPlace;
    /**
	 * 尿液检测
	 */
	private String urineExamination;
    /**
	 * 现实表现
	 */
	private String realityExpression;
    /**
	 * 社会交往
	 */
	private String socialCommunication;
    /**
	 * 习惯爱好
	 */
	private String habits;
    /**
	 * 活动轨迹
	 */
	private String activeTrajectory;
    /**
	 * 生活来源
	 */
	private String sourceOfLife;
    /**
	 * 是否失控
	 */
	private String ifControl;
    /**
	 * 四巡平台(第三方链接)
	 */
	private String fourPatrolPlatform;
    /**
	 * 闪存平台(第三方链接)
	 */
	private String flashMemoryPlatform;
    /**
	 * 工作照片
	 */
	private String workingPicture;
    /**
	 * 记录登记人员（警察）id
	 */
	private Integer policeId;
    /**
	 * 添加时间
	 */
	private Date createTime;
    /**
	 * 任务 id
	 */
	private Integer taskId;


	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：所属派出所
	 */
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	/**
	 * 获取：所属派出所
	 */
	public String getPoliceStation() {
		return policeStation;
	}
	/**
	 * 设置：所属警务室
	 */
	public void setPoliceOffice(String policeOffice) {
		this.policeOffice = policeOffice;
	}
	/**
	 * 获取：所属警务室
	 */
	public String getPoliceOffice() {
		return policeOffice;
	}
	/**
	 * 设置：吸毒者姓名
	 */
	public void setDrugAddicts(String drugAddicts) {
		this.drugAddicts = drugAddicts;
	}
	/**
	 * 获取：吸毒者姓名
	 */
	public String getDrugAddicts() {
		return drugAddicts;
	}
	/**
	 * 设置：见面时间
	 */
	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}
	/**
	 * 获取：见面时间
	 */
	public Date getMeetingTime() {
		return meetingTime;
	}
	/**
	 * 设置：见面地点
	 */
	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}
	/**
	 * 获取：见面地点
	 */
	public String getMeetingPlace() {
		return meetingPlace;
	}
	/**
	 * 设置：尿液检测
	 */
	public void setUrineExamination(String urineExamination) {
		this.urineExamination = urineExamination;
	}
	/**
	 * 获取：尿液检测
	 */
	public String getUrineExamination() {
		return urineExamination;
	}
	/**
	 * 设置：现实表现
	 */
	public void setRealityExpression(String realityExpression) {
		this.realityExpression = realityExpression;
	}
	/**
	 * 获取：现实表现
	 */
	public String getRealityExpression() {
		return realityExpression;
	}
	/**
	 * 设置：社会交往
	 */
	public void setSocialCommunication(String socialCommunication) {
		this.socialCommunication = socialCommunication;
	}
	/**
	 * 获取：社会交往
	 */
	public String getSocialCommunication() {
		return socialCommunication;
	}
	/**
	 * 设置：习惯爱好
	 */
	public void setHabits(String habits) {
		this.habits = habits;
	}
	/**
	 * 获取：习惯爱好
	 */
	public String getHabits() {
		return habits;
	}
	/**
	 * 设置：活动轨迹
	 */
	public void setActiveTrajectory(String activeTrajectory) {
		this.activeTrajectory = activeTrajectory;
	}
	/**
	 * 获取：活动轨迹
	 */
	public String getActiveTrajectory() {
		return activeTrajectory;
	}
	/**
	 * 设置：生活来源
	 */
	public void setSourceOfLife(String sourceOfLife) {
		this.sourceOfLife = sourceOfLife;
	}
	/**
	 * 获取：生活来源
	 */
	public String getSourceOfLife() {
		return sourceOfLife;
	}
	/**
	 * 设置：是否失控
	 */
	public void setIfControl(String ifControl) {
		this.ifControl = ifControl;
	}
	/**
	 * 获取：是否失控
	 */
	public String getIfControl() {
		return ifControl;
	}
	/**
	 * 设置：四巡平台(第三方链接)
	 */
	public void setFourPatrolPlatform(String fourPatrolPlatform) {
		this.fourPatrolPlatform = fourPatrolPlatform;
	}
	/**
	 * 获取：四巡平台(第三方链接)
	 */
	public String getFourPatrolPlatform() {
		return fourPatrolPlatform;
	}
	/**
	 * 设置：闪存平台(第三方链接)
	 */
	public void setFlashMemoryPlatform(String flashMemoryPlatform) {
		this.flashMemoryPlatform = flashMemoryPlatform;
	}
	/**
	 * 获取：闪存平台(第三方链接)
	 */
	public String getFlashMemoryPlatform() {
		return flashMemoryPlatform;
	}
	/**
	 * 设置：工作照片
	 */
	public void setWorkingPicture(String workingPicture) {
		this.workingPicture = workingPicture;
	}
	/**
	 * 获取：工作照片
	 */
	public String getWorkingPicture() {
		return workingPicture;
	}
	/**
	 * 设置：记录登记人员（警察）id
	 */
	public void setPoliceId(Integer policeId) {
		this.policeId = policeId;
	}
	/**
	 * 获取：记录登记人员（警察）id
	 */
	public Integer getPoliceId() {
		return policeId;
	}
	/**
	 * 设置：添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：任务 id
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：任务 id
	 */
	public Integer getTaskId() {
		return taskId;
	}
}
