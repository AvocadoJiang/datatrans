package xin.nbjzj.datatrans.service;

import java.io.IOException;

import xin.nbjzj.datatrans.entity.PatientInforEntity;

public interface NewCardService {
	
	/**
	 * 从接口中获取数据存储到TB_ST_GAGRXX表中
	 * 1.获取优先需要查询的number个人
	 * 2.通过接口获得每个人的数据
	 * 3.将每个人的数据存储到TB_ST_GAGRXX表中
	 * 4.向TB_ST_ZJB增加一条日志,更新TB_YL_PATIENT_INFORMATION表中的TB_ST_GAGRXX字段
	 * @param number
	 * @throws IOException 
	 */
	 public void getData(PatientInforEntity entity) throws IOException;
}
