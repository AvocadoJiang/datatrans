package xin.nbjzj.datatrans.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import xin.nbjzj.datatrans.entity.DisabledInfoEntity;
@Mapper
public interface DisabledInfoDao {
	public static String TABLE_NAME = "TB_ST_CJRXX";
	@Insert("insert into TB_ST_CJRXX (GUARDIAN_NAME,PK_ID,ADDRESS,GENDER,NATION,BIRTH_DATE,GUARDIAN_ADDRESS,GUARDIAN_IDENTITY_CARD,DISABLE_CARD_NUM,DISABLE_LEVEL,DISABLE_TYPE,GUARDIAN_RELATION,IDENTITY_CARD,NATIVE_PLACE,ISSUE_DATE,PHONE,NAME,TONG_TIME,GUARDIAN_PHONE)"
			+ " values (#{guardianName},#{pkId},#{address},#{gender},#{nation},#{birthDate},#{guardianAddress},#{guardianIdentityCard},#{disableCardNum},#{disableLevel},#{disableType},#{guardianRelation},#{identityCard},#{nativePlace},#{issueDate},#{phone},#{name},#{tongTime},#{guardianPhone})")
	public int insert(DisabledInfoEntity entity);
}
