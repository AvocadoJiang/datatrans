package xin.nbjzj.datatrans.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import xin.nbjzj.datatrans.entity.NewCardEntity;

@Mapper
public interface NewCardDao {
	public static String TABLE_NAME = "TB_ST_GAGRXX";
	@Insert("insert into TB_ST_GAGRXX (CZRKGMSFHM,CZRKXM,CZRKXB,CZRKCSRQ,CZRKMZ,CZRKZZ,CZRKQFJG,CZRKYXQXQSRQ,CZRKYXQXJZRQ,CZRKSDZP,ELCLICENCECODE)"
			+ " values (#{czrkgmsfhm},#{czrkxm},#{czrkxb},#{czrkcsrq},#{czrkmz},#{czrkzz},#{czrkqfjg},#{czrkyxqxqsrq},#{czrkyxqxjzrq},#{czrksdzp},#{elcLicenceCode})")
	public int insert(NewCardEntity entity);
}
