package xin.nbjzj.datatrans.service;

import java.io.IOException;

public interface DataTransService {
	
	/**
	 * 从接口中获取数据存储到三张表中
	 * 1.获取优先需要查询的number个人
	 * 2.通过接口获得每个人的数据
	 * 3.将每个人的数据存储到三张表中
	 * 4.向TB_ST_ZJB增加一条日志,更新TB_YL_PATIENT_INFORMATION表中的TB_ST_GAGRXX字段
	 * @param number
	 * @throws IOException 
	 */
	 public void getNewCardData(Integer number) throws IOException;
	 
	 public void getDibaoInfoData(Integer number) throws IOException;
	 
	 public void getDisabledInfoData(Integer number) throws IOException;
}
