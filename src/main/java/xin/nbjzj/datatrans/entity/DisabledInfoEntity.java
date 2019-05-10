package xin.nbjzj.datatrans.entity;
import java.util.Date;

import lombok.Data;


/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.8.0
 */

/**
 * 残疾人信息
 * 
 * @author bianj
 * @version 1.0.0 2019-02-27
 */
@Data
public class DisabledInfoEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 4375222417210620584L;

    /** guardianName */
    private String guardianName;

    /** pkId */
    private String pkId;

    /** address */
    private String address;

    /** gender */
    private String gender;

    /** nation */
    private String nation;

    /** birthDate */
    private String birthDate;

    /** guardianAddress */
    private String guardianAddress;

    /** guardianIdentityCard */
    private String guardianIdentityCard;

    /** disableCardNum */
    private String disableCardNum;

    /** disableLevel */
    private String disableLevel;

    /** disableType */
    private String disableType;

    /** guardianRelation */
    private String guardianRelation;

    /** identityCard */
    private String identityCard;

    /** nativePlace */
    private String nativePlace;

    /** issueDate */
    private String issueDate;

    /** phone */
    private String phone;

    /** name */
    private String name;

    /** tongTime */
    private Date tongTime;

    /** guardianPhone */
    private String guardianPhone;

}