package com.capgemini.onboarding.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

@NamedQueries(value = { 	
		@NamedQuery(name = "listAllPreOnbEmployees", query = "from PreOnbEmployee"),
		@NamedQuery(name = "getEmpForPSAID", query = "from PreOnbEmployee where corpId = :corpID and resourceStatus in ('BISPMOSubmitted', 'OnboardingInitiated','VMInProccess','OnboardingCompleted')"),
		@NamedQuery(name = "getRMApprovedEmp", query = "from PreOnbEmployee where corpId = :corpID and resourceStatus='RMApproved'")
		})

@Entity
@Table(name = "PREONBEMPLOYEE")
//@Audited
public class PreOnbEmployee implements java.io.Serializable, Comparable<PreOnbEmployee> {

	
	private Logger logger = Logger.getLogger(PreOnbEmployee.class);

	private Long id;
	private String empId;
	private String ggId; 
	private Vendor vendor;
	private String corpId;
	private String firstName;
	private String lastName;
	private String email;
	private Date dob;
	private String managerEmail;
	private String capManager; //new
	private String capManagerEmail; //new
	private String capSupervisor; // new 
	private String capSupervisorEmail;// new 
	private int allocation = 100; // new
	private Employee EM;
	private BundleEm bundleEM;
	private Bis bis;
	private Bis workingBis; //new
	private DemandType demandType;
	private PrimaryProgram primaryprogram;
	private String empType;
	private Country country;
	private State location;
	private Grade grade;
	private GlobalGrade globalGrade; 
	private Technology primaryTechnology;
	private Employee manager;
	private Employee buddy;
	private Technology secondaryTechnology;//mehens-new 
	private String criticality; //mehens - new
	private Boolean vco = false; //dkaushik-new
	
	@OneToOne
	@JoinColumn(name = "buddy_Id", nullable = true)
	public Employee getBuddy() {
		return buddy;
	}

	public void setBuddy(Employee buddy) {
		this.buddy = buddy;
	}

	private int type;
	private int indusGoals;
	private IndusGoals indus;
	private int roleTech;
	private String staffingRR;
	private RoleTech role;
	private Integer emprole;
	private EmployeeRoles roleid;
	private int service;
	private String empSex;
	private String onboardingDate;
	private Date endDate;
	private String offboardingDate;
	private ResourceManager resourceManager;
	/*private int projectCode;*/
	private String project;

	private String comment;
	private Date joiningDate;
	private String billingStartDate;
	private Date billingDate;
	private boolean userReadOnly;
	private boolean EMReadOnly;//EM
	public String resourceStatus;
	private boolean userManagement;
	private String createdBy;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creationdate;
	
	private String creationdateString;
	private boolean PSAIdReq =true;
	//private String clarityCodeAllocation;
	private String dobDate;
	private PSALibType psaLibType;
	private int psaLibTypeID;
	private String jiraNumber;
	private Employee requestor;
	private String pcSerialNumber;
	private String macAddress=null;
	private String cgEntity;
	private boolean HRAReq = false; 
	private boolean upperVCycle = false;
	private boolean timeNMaterial = false;
	private String timeMat ; //mehens
	
	//Engg - Start
	private boolean isEngg = false;//new flag for Engineering. For other PP it's false
	private String orderG;
	private boolean sdm = false;
	private String otherAs;
	private BusinessApplicationService businessAs;
	private CfaoGroup cfao;
	//Engg - End
	
	//private boolean coreTeam = false; //New
	//private boolean bi = false; //mehens
	
	//private Employee onboardingId;
	
	private String onboardingEmail;

	/*public Employee getOnboardingId() {
		return onboardingId;
	}

	public void setOnboardingId(Employee onboardingId) {
		this.onboardingId = onboardingId;
	}*/

	public String getOnboardingEmail() {
		return onboardingEmail;
	}

	public void setOnboardingEmail(String onboardingEmail) {
		this.onboardingEmail = onboardingEmail;
	}

	private Integer archiveType;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date EMSubmitDT; //new
	
	private String EMSubmitDTString;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date RMSubmitDT; //new
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date BISPMOSubmitDT; //new
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date RMPMOSubmitDT; //new
	
	private boolean isVLANmailDone = false;
	
	
	
	/*public PreOnbEmployee() {
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));
	}*/

	@Id
	@Column(name="id",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "emp_id")
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	@Column(name = "corp_id", nullable = true)
	public String getCorpId() {
		return corpId;
	}

	
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	
	@Column(name = "first_name")

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/*@ManyToOne
	@Join*/
	@Column(name = "role_tech", nullable = true)
	public int getRoleTech() {
		return roleTech;
	}

	public void setRoleTech(int roleTech) {
		this.roleTech = roleTech;
	}

	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "vendor_id", nullable = true)
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	
	@OneToOne
	@JoinColumn(name = "EM", nullable = true)
	public Employee getEM() {
		return EM;
	}

	public void setEM(Employee eM) {
		this.EM = eM;
	}
	
	
	@ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "bundleEm_id", nullable = true)
	public BundleEm getBundleEM() {
		return bundleEM;
	}

	public void setBundleEM(BundleEm bundleEM) {
		this.bundleEM = bundleEM;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "bis_id", nullable = true)
	public Bis getBis() {
		return bis;
	}

	public void setBis(Bis bis) {
		this.bis = bis;
	}
	
	@ManyToOne
	@JoinColumn(name = "workingbis_id", nullable = true)
	public Bis getWorkingBis() {
		return this.workingBis;
	}

	public void setWorkingBis(Bis workingBis) {
		this.workingBis = workingBis;
	}

	@ManyToOne
	@JoinColumn(name = "demand_id", nullable = true)
	public DemandType getDemandType() {
		return demandType;
	}

	public void setDemandType(DemandType demandType) {
		this.demandType = demandType;
	}

	@ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "primary_program_id", nullable = false)
    public PrimaryProgram getPrimaryprogram() {
		return primaryprogram;
	}

	public void setPrimaryprogram(PrimaryProgram primaryprogram) {
		this.primaryprogram = primaryprogram;
	}
	
	
	@Column(name = "emp_type")
	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	public State getLocation() {
		return location;
	}

	public void setLocation(State location) {
		this.location = location;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "grade_id")
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	@ManyToOne
	@JoinColumn(name = "global_grade_id")
	public GlobalGrade getGlobalGrade() {
		return globalGrade;
	}

	public void setGlobalGrade(GlobalGrade globalGrade) {
		this.globalGrade = globalGrade;
	}
	
	
	@ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "tech_id", nullable = false)
	public Technology getPrimaryTechnology() {
		return primaryTechnology;
	}

	public void setPrimaryTechnology(Technology primaryTechnology) {
		this.primaryTechnology = primaryTechnology;
	}
	
	@ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "secondary_tech_id")
	public Technology getSecondaryTechnology() {
		return secondaryTechnology;
	}

	public void setSecondaryTechnology(Technology secondaryTechnology) {
		this.secondaryTechnology = secondaryTechnology;
	}
	
	@OneToOne
	@JoinColumn(name = "mgr_id", nullable = true)
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	@Column(name = "manager_email", nullable = true)
	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	
	
	@Column(name = "type", nullable = true) //Industrialisator or Developer or Transversal
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	/*@ManyToOne
    @Join*/
	@Column(name = "indus_goal_id", nullable = false)
	public int getIndusGoals() {
		return indusGoals;
	}

	public void setIndusGoals(int indusGoals) {
		this.indusGoals = indusGoals;
	}

	
	@Column(name = "service", nullable = true)
	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}
	
	
	@Column(name = "emp_sex", nullable = true)
	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	
	
	@Transient
	public String getOffboardingDate() {
		return offboardingDate;
	}

	public void setOffboardingDate(String offboardingDate) {
		this.offboardingDate = offboardingDate;
	}
	
	
	@Transient
	public String getBillingStartDate() {
		return billingStartDate;
	}

	public void setBillingStartDate(String billingStartDate) {
		this.billingStartDate = billingStartDate;
	}
	
	@Transient
	public boolean isUserReadOnly() {
		return userReadOnly;
	}

	public void setUserReadOnly(boolean userReadOnly) {
		this.userReadOnly = userReadOnly;
	}
	
	@Transient
	public boolean isUserManagement() {
		return userManagement;
	}

	public void setUserManagement(boolean userManagement) {
		this.userManagement = userManagement;
	}
	
	
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "end_date", nullable = false)
	public Date getEndDate() {
		
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "resource_mgr_id", nullable = true)
	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}
	
	
	@Column(name = "comment", nullable = true)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@Transient
	public String getOnboardingDate() {
		return onboardingDate;
	}

	public void setOnboardingDate(String onboardingDate) {
		this.onboardingDate = onboardingDate;
	}

	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "joining_date" , nullable = true)
	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date date) {
		//logger.info("Joining date setter "+joiningDate);
		this.joiningDate = date;
		
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "billing_date" , nullable = true)
	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	

	@Column(name = "res_status", nullable = true)
	public String getResourceStatus() {
		return resourceStatus;
	}

	public void setResourceStatus(String resourceStatus) {
		this.resourceStatus = resourceStatus;
	}
	
	
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
	@Column(name = "creation_date")
	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	
	@Column(name = "PSAIdReq")
	public boolean isPSAIdReq() {
		return PSAIdReq;
	}

	public void setPSAIdReq(boolean pSAIdReq) {
		PSAIdReq = pSAIdReq;
	}
	
//	@Column(name = "clarityCodeAllocation")
//	public String getClarityCodeAllocation() {
//		return clarityCodeAllocation;
//	}
//
//	public void setClarityCodeAllocation(String clarityCodeAllocation) {
//		this.clarityCodeAllocation = clarityCodeAllocation;
//	}

	@OneToOne
	@JoinColumn(name = "indus_goal_id", insertable = false, updatable=false)
	public IndusGoals getIndus() {
		return indus;
	}

	public void setIndus(IndusGoals indus) {
		this.indus = indus;
	}
	
	@OneToOne
	@JoinColumn(name = "role_tech", insertable = false, updatable=false)
	public RoleTech getRole() {
		return role;
	}

	public void setRole(RoleTech role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "psalib_id", insertable = false, updatable=false)
	public PSALibType getPsaLibType() {
		return psaLibType;
	}

	public void setPsaLibType(PSALibType psaLibType) {
		this.psaLibType = psaLibType;
	}

	@Column(name = "psalib_id")
	public int getPsaLibTypeID() {
		return psaLibTypeID;
	}

	public void setPsaLibTypeID(int psaLibTypeID) {
		this.psaLibTypeID = psaLibTypeID;
	}

	@Column(name = "jira_num")
	public String getJiraNumber() {
		return jiraNumber;
	}

	public void setJiraNumber(String jiraNumber) {
		this.jiraNumber = jiraNumber;
	}

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "requestor", nullable = true)
	public Employee getRequestor() {
		return requestor;
	}

	public void setRequestor(Employee requestor) {
		this.requestor = requestor;
	}
	
	@Column(name = "pc_serial_number")
	public String getPcSerialNumber() {
		return pcSerialNumber;
	}

	public void setPcSerialNumber(String pcSerialNumber) {
		this.pcSerialNumber = pcSerialNumber;
	}

	@Column(name = "mac_address")
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Column(name = "HRAReq")
	public boolean getHRAReq() {
		return HRAReq;
	}

	public void setHRAReq(boolean hRAReq) {
		HRAReq = hRAReq;
	}

	@Column(name = "UpperVCycle")
	public boolean isUpperVCycle() {
		return upperVCycle;
	}

	public void setUpperVCycle(boolean upperVCycle) {
		this.upperVCycle = upperVCycle;
	}
	
	
	/*@Column(name = "CoreTeam")
	public boolean isCoreTeam() {
		return coreTeam;
	}

	public void setCoreTeam(boolean coreTeam) {
		this.coreTeam = coreTeam;
	}*/

	@Column(name = "TimeNMaterial")
	public boolean isTimeNMaterial() {
		return timeNMaterial;
	}

	public void setTimeNMaterial(boolean timeNMaterial) {
		this.timeNMaterial = timeNMaterial;
	}

	@Column(name = "gg_id",  nullable = true)
	public String getGgId() {
		return ggId;
	}

	public void setGgId(String ggId) {
		this.ggId = ggId;
	}

	@Column(name = "archive_type") 
	public Integer getArchiveType() {
		return archiveType;
	}

	public void setArchiveType(Integer archiveType) {
		this.archiveType = archiveType;
	}

	@Column(name = "EMSubmitDT")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getEMSubmitDT() {
		return EMSubmitDT;
	}

	public void setEMSubmitDT(Date eMSubmitDT) {
		EMSubmitDT = eMSubmitDT;
	}

	@Column(name = "RMSubmitDT")
	public Date getRMSubmitDT() {
		return RMSubmitDT;
	}

	public void setRMSubmitDT(Date rMSubmitDT) {
		RMSubmitDT = rMSubmitDT;
	}

	@Column(name = "BISPMOSubmitDT")
	public Date getBISPMOSubmitDT() {
		return BISPMOSubmitDT;
	}

	public void setBISPMOSubmitDT(Date bISPMOSubmitDT) {
		BISPMOSubmitDT = bISPMOSubmitDT;
	}

	@Column(name = "RMPMOSubmitDT")
	public Date getRMPMOSubmitDT() {
		return RMPMOSubmitDT;
	}

	public void setRMPMOSubmitDT(Date rMPMOSubmitDT) {
		RMPMOSubmitDT = rMPMOSubmitDT;
	}

	@Transient
	public String getCreationdateString() {
		return creationdateString;
	}

	public void setCreationdateString(String creationdateString) {
		this.creationdateString = creationdateString;
	}

	@Transient
	public String getEMSubmitDTString() {
		return EMSubmitDTString;
	}

	public void setEMSubmitDTString(String eMSubmitDTString) {
		EMSubmitDTString = eMSubmitDTString;
	}

	@Column(name = "isVLANmailDone")
	public boolean getIsVLANmailDone() {
		return isVLANmailDone;
	}

	public void setIsVLANmailDone(boolean isVLANmailDone) {
		this.isVLANmailDone = isVLANmailDone;
	}

	
	@Column(name = "cg_entity", nullable = true)
	public String getCgEntity() {
		return cgEntity;
	}

	public void setCgEntity(String cgEntity) {
		this.cgEntity = cgEntity;
	}

	@Column(name = "emp_roles", nullable = true)
	public Integer getEmprole() {
		return emprole;
	}

	public void setEmprole(Integer emprole) {
		this.emprole = emprole;
	}
	
	@OneToOne
	@JoinColumn(name = "emp_roles", insertable = false, updatable=false)
	public EmployeeRoles getRoleid() {
		return roleid;
	}

	public void setRoleid(EmployeeRoles roleid) {
		this.roleid = roleid;
	}
	
	@Column(name = "staffing_rr", nullable = true)
	public String getStaffingRR() {
		return staffingRR;
	}

	public void setStaffingRR(String staffingRR) {
		this.staffingRR = staffingRR;
	}

	@Transient
	public String getDobDate() {
		return dobDate;
	}

	public void setDobDate(String dobDate) {
		this.dobDate = dobDate;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dob", nullable = true)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Column(name = "capManager", nullable = true)
	public String getCapManager() {
		return capManager;
	}

	public void setCapManager(String capManager) {
		this.capManager = capManager;
	}

	@Column(name = "capManagerEmail", nullable = true)
	public String getCapManagerEmail() {
		return capManagerEmail;
	}

	public void setCapManagerEmail(String capManagerEmail) {
		this.capManagerEmail = capManagerEmail;
	}

	@Column(name = "capSupervisor", nullable = true)
	public String getCapSupervisor() {
		return capSupervisor;
	}

	public void setCapSupervisor(String capSupervisor) {
		this.capSupervisor = capSupervisor;
	}

	@Column(name = "capSupervisorEmail", nullable = true)
	public String getCapSupervisorEmail() {
		return capSupervisorEmail;
	}

	public void setCapSupervisorEmail(String capSupervisorEmail) {
		this.capSupervisorEmail = capSupervisorEmail;
	}

	@Column(name = "allocation", nullable = true)
	public int getAllocation() {
		return allocation;
	}

	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}
	
	@Column(name = "project", nullable = true)
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@Override
	public int compareTo(PreOnbEmployee preOnbEmployee) {
		return this.getFirstName().compareTo(preOnbEmployee.getFirstName());
	}

	@Column(name = "criticality", nullable = true)
	public String getCriticality() {
		return criticality;
	}

	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}

	@Column(name = "vco")
	public Boolean getVco() {
		return vco;
	}

	public void setVco(Boolean vco) {
		this.vco = vco;
	}

	/*@Column(name = "bi")
	public boolean isBi() {
		return bi;
	}

	public void setBi(boolean bi) {
		this.bi = bi;
	}*/

	@Transient
	public boolean isEMReadOnly() {
		return EMReadOnly;
	}

	public void setEMReadOnly(boolean eMReadOnly) {
		EMReadOnly = eMReadOnly;
	}

	@Column(name = "timeMat", nullable = true)
	public String getTimeMat() {
		return timeMat;
	}

	public void setTimeMat(String timeMat) {
		this.timeMat = timeMat;
	}

	@Column(name = "isEngg")
	public boolean getEngg() {
		return isEngg;
	}

	public void setEngg(boolean isEngg) {
		this.isEngg = isEngg;
	}

	@Column(name = "orderG")
	public String getOrderG() {
		return orderG;
	}

	public void setOrderG(String orderG) {
		this.orderG = orderG;
	}

	@Column(name = "sdm")
	public boolean isSdm() {
		return sdm;
	}

	public void setSdm(boolean sdm) {
		this.sdm = sdm;
	}

	@Column(name = "otherAs")
	public String getOtherAs() {
		return otherAs;
	}

	public void setOtherAs(String otherAs) {
		this.otherAs = otherAs;
	}

	@ManyToOne
	@JoinColumn(name = "businessAs")
	public BusinessApplicationService getBusinessAs() {
		return businessAs;
	}

	public void setBusinessAs(BusinessApplicationService businessAs) {
		this.businessAs = businessAs;
	}

	@ManyToOne
	@JoinColumn(name = "cfao")
	public CfaoGroup getCfao() {
		return cfao;
	}

	public void setCfao(CfaoGroup cfao) {
		this.cfao = cfao;
	}


}
