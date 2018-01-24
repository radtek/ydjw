package com.ehootu.park.model;

import java.io.Serializable;
import java.util.Date;



/**
 * 园区企业矛盾纠纷调处工作记录
 * 
 * @author yinyujun
 * @email 
 * @date 2017-09-21 10:21:44
 */
public class DisputeMediationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//所属派出所
	private String policeStation;
	//所属警务室
	private String policeOffice;
	//调处时间
	private Date mediationTime;
	//调处地点
	private String mediationSite;
	//调处人
	private String mediationAgent;
	//调处内容
	private String mediationContent;
	//调处结果
	private String mediationResult;
	//四巡平台(第三方链接)
	private String fourPatrolPlatform;
	//闪存平台(第三方链接)
	private String flashMemoryPlatform;
	//工作照片
	private String workingPicture;
	//警察id
	private Integer policeId;
	//添加时间
		private Date date;
		//通用信息
		private Integer publicId;
		
		private PublicInformationEntity publicInformation;
		
		private Integer taskId;
		public Integer getTaskId() {
			return taskId;
		}
		public void setTaskId(Integer taskId) {
			this.taskId = taskId;
		}

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
	 * 设置：调处时间
	 */
	public void setMediationTime(Date mediationTime) {
		this.mediationTime = mediationTime;
	}
	/**
	 * 获取：调处时间
	 */
	public Date getMediationTime() {
		return mediationTime;
	}
	/**
	 * 设置：调处地点
	 */
	public void setMediationSite(String mediationSite) {
		this.mediationSite = mediationSite;
	}
	/**
	 * 获取：调处地点
	 */
	public String getMediationSite() {
		return mediationSite;
	}
	/**
	 * 设置：调处人
	 */
	public void setMediationAgent(String mediationAgent) {
		this.mediationAgent = mediationAgent;
	}
	/**
	 * 获取：调处人
	 */
	public String getMediationAgent() {
		return mediationAgent;
	}
	/**
	 * 设置：调处内容
	 */
	public void setMediationContent(String mediationContent) {
		this.mediationContent = mediationContent;
	}
	/**
	 * 获取：调处内容
	 */
	public String getMediationContent() {
		return mediationContent;
	}
	/**
	 * 设置：调处结果
	 */
	public void setMediationResult(String mediationResult) {
		this.mediationResult = mediationResult;
	}
	/**
	 * 获取：调处结果
	 */
	public String getMediationResult() {
		return mediationResult;
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
	 * 设置：警察id
	 */
	public void setPoliceId(Integer policeId) {
		this.policeId = policeId;
	}
	/**
	 * 获取：警察id
	 */
	public Integer getPoliceId() {
		return policeId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getPublicId() {
		return publicId;
	}
	public void setPublicId(Integer publicId) {
		this.publicId = publicId;
	}
	public PublicInformationEntity getPublicInformation() {
		return publicInformation;
	}
	public void setPublicInformation(PublicInformationEntity publicInformation) {
		this.publicInformation = publicInformation;
	}
	
}
