package com.capgemini.onboarding.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.type.TrueFalseType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NamedQueries(value = { @NamedQuery(name = "listManagers", query = "from Employee e where e.grade.gradeId > 5"),
		@NamedQuery(name = "listManagersByBundleEm", query = "from Employee e where e.globalGrade.globalGradeId IN (3,4,5)"),
		@NamedQuery(name = "listManagersByEm", query = "from Employee e where e.globalGrade.globalGradeId IN (2,3,4,5)"),
		@NamedQuery(name = "listEmployeesByGrade", query = "from Employee e where e.grade.gradeId in (:gradeList)"),
		@NamedQuery(name = "listAllEmployees", query = "from Employee"),
		@NamedQuery(name = "listBuddyByEmail", query = "from Employee e where e.email in (:email) "),
		@NamedQuery(name = "listEmployeeById", query = "from Employee e where  e.id = :id "),
		//@NamedQuery(name = "getLoggedUserID", query = "from Employee where corpId = :corpID "),
		@NamedQuery(name = "getLoggedUserID", query = "from Employee where corpId = :corpId and archive_type is null "),
		@NamedQuery(name = "offBoardEmployee", query = "update Employee e set e.actualEndDate =:endDate where e.id = :id"),
		@NamedQuery(name = "listAllEmployeesByCountry", query = "SELECT COUNT(*),e.country.countryId from Employee e where e.empId not in ('NA') group by e.country.countryId "),	
		@NamedQuery(name = "listInternalEmpCountryWise", query = "SELECT COUNT(*),e.country.countryId FROM Employee e where e.empId not in ('NA') and e.empType='Internal' group by e.country.countryId"),
		@NamedQuery(name = "listExternalEmpCountryWise", query = "SELECT COUNT(*),e.country.countryId FROM Employee e where e.empId not in ('NA') and e.empType='External'  group by e.country.countryId"),
		
		@NamedQuery(name = "listActiveEmpCountryWise", query = "SELECT COUNT(*),e.country.countryId FROM Employee e where e.empId not in ('NA') and endDate >= CURDATE() group BY e.country.countryId"),
		@NamedQuery(name = "listActiveInternalEmpCountryWise", query = "SELECT COUNT(*),e.country.countryId FROM Employee e where e.empId not in ('NA') and e.empType='Internal' and endDate >= CURDATE() group by e.country.countryId"),	
		@NamedQuery(name = "listActiveExternalEmpCountryWise", query = "SELECT COUNT(*),e.country.countryId FROM Employee e where  e.empId not in ('NA') and e.empType='External' and endDate >= CURDATE() group by e.country.countryId")})

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author capgemini
 *
 */
@Entity
@Table(name = "EMPLOYEE")
//@Audited
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Proxy(lazy = false)
public class Employee implements java.io.Serializable, Comparable<Employee> {

	/*
	 * @EmbeddedId private EmployeePK employeePK;
	 */

	private String empId;
	private String ggId; //new
	private String rr_no;
	private String corpId;
	private String repCorpId;
	private String repfirstName;
	private String firstName;
	private String lastName;
	private String email;
	private Grade grade;
	private GlobalGrade globalGrade;
	private Technology primaryTechnology;
	private Technology secondaryTechnology;//mehens - new
	private String criticality; //mehens - new
	private Boolean vco = false; //dkaushik-new
	private Date joiningDate;
	private Date endDate;
	private Date actualEndDate;
	private String billingStartDate;
	private Date billingDate;
	private String onboardingDate;
	private String offboardingDate;
	private String actualOffboardingDate;
	private String contact;
	private Date entryDate;
	private String createdBy;
	private Country country;
	private State location;
	private Vendor vendor;
	private Spoc spoc;
	private String spocName;
	
	private String spocEmail;
	private List<EmployeeTraining> employeeTrainings = new ArrayList<EmployeeTraining>(0);
	private Employee manager;
	private Employee buddy;
	@OneToOne
	@JoinColumn(name = "buddy_Id", nullable = true)
	public Employee getBuddy() {
		return buddy;
	}

	public void setBuddy(Employee buddy) {
		this.buddy = buddy;
	}

	private String capManager; //new
	private String capManagerEmail; //new
	private String capSupervisor; // new 
	private String capSupervisorEmail;// new 
	private int allocation = 100; // new
	private Boolean securityTraining;
	private Boolean onboardingChecked;
	private boolean replacementRequired;
	private String replacementRequiredVal;
	private String empType;
	private String totalWeeks;
	private String isTrainingCompleted;
	private EntityDetail entity;
	private Date dob;
	private String psaId;
	private String comment;
	private String offboardcomment;
	private int heritageType;
	private RollOffType rollOffType;
	private int rollOffTypeVal = 0;
	private ReplacementType replacementType;
	private int replacementTypeVal = 0;
	private String issueType;
	private String heritageValue;
	private String empSex;
	private String cgEntity;
	private OffshoreEm offshoreEm;
	private String managerEmail;
	private Long id;
	private String externalEmpId;
	private String staffingRR;
	private String countryNameDashBoard;
	private int internalCountryWise; 
	private int extrnalCountryWise; 
	private int totalInternal; 
	private int totalExtrnal; 
	private int totalEmployeeCountryWise;
	private int totalEmployee;
	private Date creationdate;
	
	private String percentage;
	private int resourceCountGradeWise;
	private String globalGradePyramid;
	private Employee EM;
	private BundleEm bundleEM;
	private boolean userReadOnly;
	private boolean EMReadOnly;//EM

	private ResourceManager resourceManager;
	/*private int phase;
	private String phasevalue;*/
	private Bis bis;
	private Bis workingBis; //new
	private DemandType demandType;
	private float monthlyFTE ;
	
	private Integer emprole;
	private EmployeeRoles roleid;
	
	private float secondFTE ;
	private float thirdFTE ;
	private float fourthFTE ;
	private float fifthFTE ;
	private float sixthFTE ;
	private int headCountEmployee ;

	private LocalDate current_month_startDate;
	private LocalDate current_Month_Enddate;
	private boolean decisionValByGP;
	private String decisionVal;
	private boolean userManagement;
	public String resourceStatus;
	private PrimaryProgram primaryprogram;
	private String hiddenCorpIdValue;
	
	private Date requestedDate;
	private Date generatedDate;
	
	private Date vmrequestedDate;
	private Date vmgeneratedDate;
	private String project;
	private PSALibType psaLibType;
	private IndusGoals indus;
	private RoleTech role;
	private int psaLibTypeID;
	private int indusGoals;
	private int roleTech;
	private Boolean PSAIdReq;
	private Boolean HRAReq; 
	private String vmNumber;
	private String PCAEmail;
	private String pcSerialNumber;
	private String macAddress=null;
	private Integer archiveType;
	private Integer welcomeEmailFlag;

	private boolean upperVCycle = false; //new
	private boolean timeNMaterial = false; //new
	private String timeMat ; //mehens
	
	//Engg - Start
	private boolean isEngg = false;//new flag for Engineering. For other PP it's false
	private String orderG;
	private boolean sdm = false;
	private String otherAs;
	private BusinessApplicationService businessAs;
	private CfaoGroup cfao;
	//Engg - End
	
	//private boolean coreTeam = false; //new	
	private boolean bi = false; //mehens
	
	private String jiraNumber;
	//private String clarityCodeAllocation;
	private Employee requestor;
	private boolean isVLANmailDone;
	private PreOnbEmployee preonbemp;
	private int orphanpara;
	
    //private Employee onboardingId;
	
	private String onboardingEmail;
	

	
	
	/*public Employee() {
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));
	}*/



	public String getOnboardingEmail() {
		return onboardingEmail;
	}

	/*public Employee getOnboardingId() {
		return onboardingId;
	}

	public void setOnboardingId(Employee onboardingId) {
		this.onboardingId = onboardingId;
	}*/

	public void setOnboardingEmail(String onboardingEmail) {
		this.onboardingEmail = onboardingEmail;
	}

	@Id
	@Column(name="id",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the empId
	 */
	@Column(name = "emp_id")
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}


	
	
	@Column(name = "rr_no")
	public String getRr_no() {
		return rr_no;
	}

	public void setRr_no(String rr_no) {
		this.rr_no = rr_no;
	}



	@Column(name = "rep_corp_id", nullable = true)
	public String getRepCorpId() {
		return repCorpId;
	}

	public void setRepCorpId(String repCorpId) {
		this.repCorpId = repCorpId;
	}

	
	/**
	 * @return the corpId
	 */
	@Column(name = "corp_id", nullable = true)
	public String getCorpId() {
		return corpId;
	}

	/**
	 * @param corpId
	 *            the corpId to set
	 */
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	
	
	/**
	 * @return the firstName
	 */
	@Column(name = "first_name")

	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the grade
	 */

	@ManyToOne
	@JoinColumn(name = "grade_id")
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	/**
	 * @return the joiningDate
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "joining_date" , nullable = false)
	public Date getJoiningDate() {
		return joiningDate;
	}

	/**
	 * @param joiningDate
	 *            the joiningDate to set
	 */
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	

	/**
	 * @return the contact
	 */
	@Column(name = "contact")
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the entryDate
	 */
	@Column(name = "entry_date")
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the createdBy
	 */
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 
	 * @return trainings of an employee
	 */
	@OneToMany(mappedBy = "employeeTrainingId.employee", cascade = CascadeType.ALL, targetEntity = EmployeeTraining.class)
	public List<EmployeeTraining> getEmployeeTrainings() {
		return this.employeeTrainings;
	}

	/**
	 * 
	 * @param employeeTrainings
	 *            trainings of an employee
	 */
	public void setEmployeeTrainings(List<EmployeeTraining> employeeTrainings) {
		this.employeeTrainings = employeeTrainings;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToOne
	@JoinColumn(name = "mgr_id", nullable = true)
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	/**
	 * @return the onboardingDate
	 */
	@Transient
	public String getOnboardingDate() {
		return onboardingDate;
	}

	/**
	 * @param onboardingDate
	 *            the onboardingDate to set
	 */
	public void setOnboardingDate(String onboardingDate) {
		this.onboardingDate = onboardingDate;
	}
	
	/**
	 * @return the offboardingDate
	 */
	@Transient
	public String getOffboardingDate() {
		return offboardingDate;
	}

	/**
	 * @param offboardingDate
	 *            the offboardingDate to set
	 */
	public void setOffboardingDate(String offboardingDate) {
		this.offboardingDate = offboardingDate;
	}

	/**
	 * @return the country
	 */
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the primaryProgram
	 */
    @ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "primary_program_id", nullable = false)
    public PrimaryProgram getPrimaryprogram() {
		return primaryprogram;
	}

	public void setPrimaryprogram(PrimaryProgram primaryprogram) {
		this.primaryprogram = primaryprogram;
	}
    

	/**
	 * @return the location
	 */
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	public State getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(State location) {
		this.location = location;
	}
	
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "resource_mgr_id", nullable = true)
	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	/**
	 * @return the primaryTechnology
	 */
	@ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@JoinColumn(name = "tech_id", nullable = false)
	public Technology getPrimaryTechnology() {
		return primaryTechnology;
	}

	/**
	 * @param primaryTechnology
	 *            the primaryTechnology to set
	 */
	public void setPrimaryTechnology(Technology primaryTechnology) {
		this.primaryTechnology = primaryTechnology;
	}

	/**
	 * @return the globalGrade
	 */
	@ManyToOne
	@JoinColumn(name = "global_grade_id")
	public GlobalGrade getGlobalGrade() {
		return globalGrade;
	}

	/**
	 * @param globalGrade
	 *            the globalGrade to set
	 */
	public void setGlobalGrade(GlobalGrade globalGrade) {
		this.globalGrade = globalGrade;
	}

	/**
	 * @return the vendor
	 */
	@ManyToOne
	
	@JoinColumn(name = "vendor_id", nullable = true)
	public Vendor getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the spoc
	 */
	@ManyToOne
	@JoinColumn(name = "spoc_id", nullable = true)
	public Spoc getSpoc() {
		return spoc;
	}

	/**
	 * @param spoc
	 *            the spoc to set
	 */
	public void setSpoc(Spoc spoc) {
		this.spoc = spoc;
	}

	/**
	 * @return the securityTraining
	 */
	@Column(name = "security_training", nullable = true)
	public Boolean getSecurityTraining() {
		return securityTraining;
	}

	/**
	 * @param securityTraining
	 *            the securityTraining to set
	 */
	public void setSecurityTraining(Boolean securityTraining) {
		this.securityTraining = securityTraining;
	}

	
	/**
	 * @return the onboardingChecked
	 */
	@Column(name = "onboarding_checked", nullable = true)
	
	public Boolean getOnboardingChecked() {
		return onboardingChecked;
	}

	public void setOnboardingChecked(Boolean onboardingChecked) {
		this.onboardingChecked = onboardingChecked;
	}

	
	
	
	
	@Column(name = "replacementreq", nullable = true)
	
	public boolean isReplacementRequired() {
		return replacementRequired;
	}

	public void setReplacementRequired(boolean replacementRequired) {
		this.replacementRequired = replacementRequired;
	}

	@Transient
	public String getReplacementRequiredVal() {
		return replacementRequiredVal;
	}

	public void setReplacementRequiredVal(String replacementRequiredVal) {
		this.replacementRequiredVal = replacementRequiredVal;
	}

	/**
	 * @return the spocName
	 */
	@Transient
	public String getSpocName() {
		return spocName;
	}

	

	
	/**
	 * @param spocName
	 *            the spocName to set
	 */
	public void setSpocName(String spocName) {
		this.spocName = spocName;
	}

	
	
	
	
	


	/**
	 * @return the spocEmail
	 */
	@Transient
	public String getSpocEmail() {
		return spocEmail;
	}

	/**
	 * @param spocEmail
	 *            the spocEmail to set
	 */
	public void setSpocEmail(String spocEmail) {
		this.spocEmail = spocEmail;
	}

	/**
	 * @return the actualEndDate
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "actual_end_date")
	public Date getActualEndDate() {
		return actualEndDate;
	}

	/**
	 * @param actualEndDate
	 *            the actualEndDate to set
	 */
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	/**
	 * @return the empType
	 */
	@Column(name = "emp_type")
	public String getEmpType() {
		return empType;
	}

	/**
	 * @param empType
	 *            the empType to set
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}

	/**
	 * @return the actualOffboardingDate
	 */
	@Transient
	public String getActualOffboardingDate() {
		return actualOffboardingDate;
	}

	/**
	 * @param actualOffboardingDate
	 *            the actualOffboardingDate to set
	 */
	public void setActualOffboardingDate(String actualOffboardingDate) {
		this.actualOffboardingDate = actualOffboardingDate;
	}

	/**
	 * @return the totalWeeks
	 */
	@Transient
	public String getTotalWeeks() {
		return totalWeeks;
	}

	/**
	 * @param totalWeeks
	 *            the totalWeeks to set
	 */
	public void setTotalWeeks(String totalWeeks) {
		this.totalWeeks = totalWeeks;
	}

	/**
	 * @return the isTrainingCompleted
	 */
	@Transient
	public String getIsTrainingCompleted() {
		return isTrainingCompleted;
	}

	/**
	 * @param isTrainingCompleted
	 *            the isTrainingCompleted to set
	 */
	public void setIsTrainingCompleted(String isTrainingCompleted) {
		this.isTrainingCompleted = isTrainingCompleted;
	}

	/**
	 * @return the entity
	 */
	@ManyToOne
	@JoinColumn(name = "entity_id", nullable = true)
	public EntityDetail getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(EntityDetail entity) {
		this.entity = entity;
	}

	/**
	 * @return the dob
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dob", nullable = true)
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the psaId
	 */
	@Column(name = "psa_id", nullable = true)
	public String getPsaId() {
		return psaId;
	}

	/**
	 * @param psaId
	 *            the psaId to set
	 */
	public void setPsaId(String psaId) {
		this.psaId = psaId;
	}

	/**
	 * @return the comment
	 */
	@Column(name = "comment", nullable = true)
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "heritage_type", nullable = true)
	public int getHeritageType() {
		return heritageType;
	}
	
	public void setHeritageType(int heritageType) {
		this.heritageType = heritageType;
	}
	
	@ManyToOne
	@JoinColumn(name = "rollofftype", nullable = true)
	/*@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)*/
	@JsonIgnoreProperties(ignoreUnknown = true) 
	public RollOffType getRollOffType() {
		return rollOffType;
	}

	public void setRollOffType(RollOffType rollOffType) {
		this.rollOffType = rollOffType;
	}

	
	@ManyToOne
	@JoinColumn(name = "replacement_type", nullable = true)
	/*@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)*/
	@JsonIgnoreProperties(ignoreUnknown = true) 
	public ReplacementType getReplacementType() {
		return replacementType;
	}

	public void setReplacementType(ReplacementType replacementType) {
		this.replacementType = replacementType;
	}

	@Transient
	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	/*@Column(name = "phase", nullable = true)
	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}*/
	
	@Column(name = "emp_sex", nullable = true)
	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	@Column(name = "manager_email", nullable = true)
	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	
	
	
	@OneToOne
	@JoinColumn(name = "EM", nullable = true)
	public Employee getEM() {
		return EM;
	}

	public void setEM(Employee eM) {
		this.EM = eM;
	}

	
/*	@OneToOne
	@JoinColumn(name = "bundleEm_id", nullable = true)
	public Employee getBundleEM() {
		return bundleEM;
	}
	
	public void setBundleEM(Employee bundleEM) {
		this.bundleEM = bundleEM;
	}*/
	
	
	
    @ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@NotFound(
		        action = NotFoundAction.IGNORE)
	@JoinColumn(name = "bundleEm_id", nullable = true)
	public BundleEm getBundleEM() {
		return bundleEM;
	}

	public void setBundleEM(BundleEm bundleEM) {
		this.bundleEM = bundleEM;
	}
	
	
	
	
	
	

	
	@Column(name = "cg_entity", nullable = true)
	public String getCgEntity() {
		return cgEntity;
	}

	public void setCgEntity(String cgEntity) {
		this.cgEntity = cgEntity;
	}
	
	
	
	
	
	
	
	
	@ManyToOne(optional = true,  fetch = FetchType.LAZY)
	  @NotFound(
		        action = NotFoundAction.IGNORE)
	@JoinColumn(name = "offshore_em_id", nullable = true)
	public OffshoreEm getOffshoreEm() {
		return offshoreEm;
	}

	public void setOffshoreEm(OffshoreEm offshoreEm) {
		this.offshoreEm = offshoreEm;
	}

	
	
	/*@OneToOne(optional=true)
	@JoinColumn(name = "offshore_em", nullable = true)
	public Employee getOffShoreEm() {
		return offShoreEm;
	}*/

	
	
	
	@Column(name = "decision_val_by_gp", nullable = true)
	public boolean isDecisionValByGP() {
		return decisionValByGP;
	}

	public void setDecisionValByGP(boolean decisionValByGP) {
		this.decisionValByGP = decisionValByGP;
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
	
	@Transient
	public String getDecisionVal() {
		return decisionVal;
	}

	public void setDecisionVal(String decisionVal) {
		this.decisionVal = decisionVal;
	}

	@Transient
	public String getExternalEmpId() {
		return externalEmpId;
	}

	public void setExternalEmpId(String externalEmpId) {
		this.externalEmpId = externalEmpId;
	}
	
	
	@Transient
	public String getCountryNameDashBoard() {
		return countryNameDashBoard;
	}

	public void setCountryNameDashBoard(String countryNameDashBoard) {
		this.countryNameDashBoard = countryNameDashBoard;
	}

	@Transient
	public int getInternalCountryWise() {
		return internalCountryWise;
	}

	public void setInternalCountryWise(int internalCountryWise) {
		this.internalCountryWise = internalCountryWise;
	}

	@Transient
	public int getExtrnalCountryWise() {
		return extrnalCountryWise;
	}

	public void setExtrnalCountryWise(int extrnalCountryWise) {
		this.extrnalCountryWise = extrnalCountryWise;
	}

	@Transient
	public int getTotalInternal() {
		return totalInternal;
	}

	public void setTotalInternal(int totalInternal) {
		this.totalInternal = totalInternal;
	}
	
	@Transient
	public int getTotalExtrnal() {
		return totalExtrnal;
	}

	public void setTotalExtrnal(int totalExtrnal) {
		this.totalExtrnal = totalExtrnal;
	}

	@Transient
	public int getTotalEmployeeCountryWise() {
		return totalEmployeeCountryWise;
	}

	public void setTotalEmployeeCountryWise(int totalEmployeeCountryWise) {
		this.totalEmployeeCountryWise = totalEmployeeCountryWise;
	}

	@Transient
	public int getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(int totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	
	@Column(name = "creation_date", nullable = true)
	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	@Transient
	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	@Transient
	public int getResourceCountGradeWise() {
		return resourceCountGradeWise;
	}

	public void setResourceCountGradeWise(int resourceCountGradeWise) {
		this.resourceCountGradeWise = resourceCountGradeWise;
	}
	@Transient
	public String getGlobalGradePyramid() {
		return globalGradePyramid;
	}

	public void setGlobalGradePyramid(String globalGradePyramid) {
		this.globalGradePyramid = globalGradePyramid;
	}

	@Transient
	public String getHeritageValue() {
		return heritageValue;
	}

	public void setHeritageValue(String heritageValue) {
		this.heritageValue = heritageValue;
	}
	
	@Transient
	public boolean isUserReadOnly() {
		return userReadOnly;
	}

	public void setUserReadOnly(boolean userReadOnly) {
		this.userReadOnly = userReadOnly;
	}
	
	
	@Transient
	public int getHeadCountEmployee() {
		return headCountEmployee;
	}

	public void setHeadCountEmployee(int headCountEmployee) {
		this.headCountEmployee = headCountEmployee;
	}

	/*@Transient
	public String getPhasevalue() {
		return phasevalue;
	}

	public void setPhasevalue(String phasevalue) {
		this.phasevalue = phasevalue;
	}*/

	@Transient
	public float getMonthlyFTE() {
		return monthlyFTE;
	}

	public void setMonthlyFTE(float monthlyFTE) {
		this.monthlyFTE = monthlyFTE;
	}

	@Transient
	public LocalDate getCurrent_month_startDate() {
		return current_month_startDate;
	}

	public void setCurrent_month_startDate(LocalDate current_month_startDate) {
		this.current_month_startDate = current_month_startDate;
	}

	@Transient
	public LocalDate getCurrent_Month_Enddate() {
		return current_Month_Enddate;
	}

	public void setCurrent_Month_Enddate(LocalDate current_Month_Enddate) {
		this.current_Month_Enddate = current_Month_Enddate;
	}
	@Transient
	public float getSecondFTE() {
		return secondFTE;
	}

	public void setSecondFTE(float secondFTE) {
		this.secondFTE = secondFTE;
	}
	
	
	@Transient
	public float getThirdFTE() {
		return thirdFTE;
	}

	public void setThirdFTE(float thirdFTE) {
		this.thirdFTE = thirdFTE;
	}

	@Transient
	public float getFourthFTE() {
		return fourthFTE;
	}

	public void setFourthFTE(float fourthFTE) {
		this.fourthFTE = fourthFTE;
	}

	@Transient
	public float getFifthFTE() {
		return fifthFTE;
	}

	
	public void setFifthFTE(float fifthFTE) {
		this.fifthFTE = fifthFTE;
	}

	@Transient
	public float getSixthFTE() {
		return sixthFTE;
	}

	public void setSixthFTE(float sixthFTE) {
		this.sixthFTE = sixthFTE;
	}


	@Transient
	public boolean isUserManagement() {
		return userManagement;
	}

	public void setUserManagement(boolean userManagement) {
		this.userManagement = userManagement;
	}
	
	@Transient
	public String getResourceStatus() {
		return resourceStatus;
	}

	public void setResourceStatus(String resourceStatus) {
		this.resourceStatus = resourceStatus;
	}
	

	@Transient
	public String getRepfirstName() {
		return repfirstName;
	}

	public void setRepfirstName(String repfirstName) {
		this.repfirstName = repfirstName;
	}
	
	@Transient
	public int getRollOffTypeVal() {
		return rollOffTypeVal;
	}

	public void setRollOffTypeVal(int rollOffTypeVal) {
		this.rollOffTypeVal = rollOffTypeVal;
	}
	
	@Transient
	public int getReplacementTypeVal() {
		return replacementTypeVal;
	}

	public void setReplacementTypeVal(int replacementTypeVal) {
		this.replacementTypeVal = replacementTypeVal;
	}
	
	@Transient
	public String getHiddenCorpIdValue() {
		return hiddenCorpIdValue;
	}

	public void setHiddenCorpIdValue(String hiddenCorpIdValue) {
		this.hiddenCorpIdValue = hiddenCorpIdValue;
	}

	@Override
	public int compareTo(Employee employee) {
		return this.getFirstName().compareTo(employee.getFirstName());
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
	
	@Column(name = "archive_type") 
	public Integer getArchiveType() {
		return archiveType;
	}

	public void setArchiveType(Integer archiveType) {
		this.archiveType = archiveType;
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
	
	@Column(name = "psaid_req_date") 
	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	
	@Column(name = "psaid_gen_date") 
	public Date getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}
	
	@Column(name = "vmno_req_date") 
	public Date getVmrequestedDate() {
		return vmrequestedDate;
	}

	public void setVmrequestedDate(Date vmrequestedDate) {
		this.vmrequestedDate = vmrequestedDate;
	}

	@Column(name = "vmno_gen_date") 
	public Date getVmgeneratedDate() {
		return vmgeneratedDate;
	}

	public void setVmgeneratedDate(Date vmgeneratedDate) {
		this.vmgeneratedDate = vmgeneratedDate;
	}
	
	private String dobDate;
	private String shadowDate;

	@Transient
	public String getDobDate() {
		return dobDate;
	}

	public void setDobDate(String dobDate) {
		this.dobDate = dobDate;
	}
	
	@Transient
	public String getShadowDate() {
		return shadowDate;
	}

	public void setShadowDate(String shadowDate) {
		this.shadowDate = shadowDate;
	}

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@JoinColumn(name = "psalib_id", insertable = false, updatable=false)
	public PSALibType getPsaLibType() {
		return psaLibType;
	}

	public void setPsaLibType(PSALibType psaLibType) {
		this.psaLibType = psaLibType;
	}

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@Column(name = "psalib_id")
	public int getPsaLibTypeID() {
		return psaLibTypeID;
	}

	public void setPsaLibTypeID(int psaLibTypeID) {
		this.psaLibTypeID = psaLibTypeID;
	}

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@OneToOne
	@JoinColumn(name = "indus_goal_id", insertable = false, updatable=false)
	public IndusGoals getIndus() {
		return indus;
	}

	public void setIndus(IndusGoals indus) {
		this.indus = indus;
	}

	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@Column(name = "indus_goal_id")
	public int getIndusGoals() {
		return indusGoals;
	}

	public void setIndusGoals(int indusGoals) {
		this.indusGoals = indusGoals;
	}

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@OneToOne
	@JoinColumn(name = "role_tech", insertable = false, updatable=false)
	public RoleTech getRole() {
		return role;
	}

	public void setRole(RoleTech role) {
		this.role = role;
	}

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@Column(name = "role_tech")
	public int getRoleTech() {
		return roleTech;
	}

	public void setRoleTech(int roleTech) {
		this.roleTech = roleTech;
	}

	@Column(name = "PSAIdReq") 
	public Boolean getPSAIdReq() {
		return PSAIdReq;
	}

	public void setPSAIdReq(Boolean pSAIdReq) {
		PSAIdReq = pSAIdReq;
	}
	
	@Column(name = "HRAReq")
	public Boolean getHRAReq() {
		return HRAReq;
	}

	public void setHRAReq(Boolean hRAReq) {
		HRAReq = hRAReq;
	}

	@Column(name = "vmNumber") 
	public String getVmNumber() {
		return vmNumber;
	}

	public void setVmNumber(String vmNumber) {
		this.vmNumber = vmNumber;
	}

	@Column(name = "PCA_email") 
	public String getPCAEmail() {
		return PCAEmail;
	}

	public void setPCAEmail(String pCAEmail) {
		PCAEmail = pCAEmail;
	}
	
	
//	@Column(name = "clarityCodeAllocation")
//	public String getClarityCodeAllocation() {
//		return clarityCodeAllocation;
//	}
//
//	public void setClarityCodeAllocation(String clarityCodeAllocation) {
//		this.clarityCodeAllocation = clarityCodeAllocation;
//	}
	
	
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

	@Column(name = "gg_id",  nullable = true)
	public String getGgId() {
		return ggId;
	}

	public void setGgId(String ggId) {
		this.ggId = ggId;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "billing_date" , nullable = true)
	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	
	@Transient
	public String getBillingStartDate() {
		return billingStartDate;
	}

	public void setBillingStartDate(String billingStartDate) {
		this.billingStartDate = billingStartDate;
	}

	
	@Column(name = "isVLANmailDone")
	public boolean getIsVLANmailDone() {
		return isVLANmailDone;
	}

	public void setIsVLANmailDone(boolean isVLANmailDone) {
		this.isVLANmailDone = isVLANmailDone;
	}


	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@OneToOne
	@JoinColumn(name = "emp_roles", insertable = false, updatable=false)
	public EmployeeRoles getRoleid() {
		return roleid;
	} 

	public void setRoleid(EmployeeRoles roleid) {
		this.roleid = roleid;
	}
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@Column(name = "emp_roles")
	public Integer getEmprole() {
		return emprole;
	}

	public void setEmprole(Integer emprole) {
		this.emprole = emprole;
	}
	
	@Column(name = "staffing_rr", nullable = true)
	public String getStaffingRR() {
	
		return staffingRR;
	}

	public void setStaffingRR(String staffingRR) {
		this.staffingRR = staffingRR;
	}

	@Column(name = "offboardcomment", nullable = true)
	public String getOffboardcomment() {
		return offboardcomment;
	}

	public void setOffboardcomment(String offboardcomment) {
		this.offboardcomment = offboardcomment;
	}

	//@Column(name = "preonbemp", nullable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@OneToOne
	@JoinColumn(name = "preonbemp")
	public PreOnbEmployee getPreonbemp() {
		return preonbemp;
	}

	public void setPreonbemp(PreOnbEmployee preonbemp) {
		this.preonbemp = preonbemp;
	}
	
	@Transient
	public int getOrphanpara() {
		return orphanpara;
	}

	public void setOrphanpara(int orphanpara) {
		this.orphanpara = orphanpara;
	}
	
	@Column(name = "welcomeEmailFlag", nullable = true)
	public Integer getWelcomeEmailFlag() {
		return welcomeEmailFlag;
	}

	public void setWelcomeEmailFlag(Integer welcomeEmailFlag) {
		this.welcomeEmailFlag = welcomeEmailFlag;
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

	@ManyToOne(optional = true,  fetch = FetchType.LAZY)
	@JoinColumn(name = "secondary_tech_id")
	public Technology getSecondaryTechnology() {
		return secondaryTechnology;
	}

	public void setSecondaryTechnology(Technology secondaryTechnology) {
		this.secondaryTechnology = secondaryTechnology;
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

	@Column(name = "gCopilot")
	public boolean isBi() {
		return bi;
	}

	public void setBi(boolean bi) {
		this.bi = bi;
	}

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
