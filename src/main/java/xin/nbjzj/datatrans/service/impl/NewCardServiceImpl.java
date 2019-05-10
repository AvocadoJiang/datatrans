package xin.nbjzj.datatrans.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import okhttp3.Response;
import xin.nbjzj.datatrans.dao.DibaoInfoDao;
import xin.nbjzj.datatrans.dao.DisabledInfoDao;
import xin.nbjzj.datatrans.dao.NewCardDao;
import xin.nbjzj.datatrans.dao.PatientInfoDao;
import xin.nbjzj.datatrans.dao.ProgressLogDao;
import xin.nbjzj.datatrans.entity.NewCardEntity;
import xin.nbjzj.datatrans.entity.PatientInforEntity;
import xin.nbjzj.datatrans.entity.ProgressLogEntity;
import xin.nbjzj.datatrans.entity.response.ApiRespEntity;
import xin.nbjzj.datatrans.service.NewCardService;
import xin.nbjzj.datatrans.utils.OkHttpUtils;

@Service("NewCardService")
public class NewCardServiceImpl implements NewCardService{
	
	
	protected static final Logger logger = LoggerFactory.getLogger(NewCardServiceImpl.class);
	
	@Autowired
	PatientInfoDao patientInfoDao;
	@Autowired
	NewCardDao newCardDao;
	@Autowired
	ProgressLogDao progressLogDao;
	@Autowired
	DibaoInfoDao dibaoInfoDao;
	/**
	 * 从接口中获取数据存储到TB_ST_GAGRXX表中
	 * 1.获取优先需要查询的number个人
	 * 2.通过接口获得每个人的数据
	 * 3.将每个人的数据存储到TB_ST_GAGRXX表中
	 * 4.向TB_ST_ZJB增加一条日志,更新TB_YL_PATIENT_INFORMATION表中的TB_ST_GAGRXX字段
	 * @param number
	 * @throws IOException 
	 */
	@Override
    public void getData(PatientInforEntity entity) throws IOException {
		boolean flag = false;
		String IdCard = entity.getZjhm();
		
		Response response = OkHttpUtils.NewCardApi(IdCard);
		
    	String json = response.body().string();
    	logger.info("_______________newCardjson________"+json);
    	System.out.println("newCardjson"+json);
    	ApiRespEntity apiEntity = JSON.parseObject(json,ApiRespEntity.class);
    	if(apiEntity!=null && apiEntity.getDatas()!=null&&!apiEntity.getDatas().equals("[]")&&apiEntity.getCode().equals("00")) {
    		List<NewCardEntity> newCardlist = JSONArray.parseArray(apiEntity.getDatas(), NewCardEntity.class);
    		//3.将每个人的数据存储到TB_ST_GAGRXX表中
    		for (NewCardEntity newCardEntity : newCardlist) {
    			logger.info("_______________newCardEntity________"+newCardEntity.toString());
				newCardDao.insert(newCardEntity);
			}
    		flag = true;
    	}
    	
		//检验response并将response转换成entity
		ProgressLogEntity logEntity = new ProgressLogEntity();
		logEntity.setBm(newCardDao.TABLE_NAME);
		logEntity.setZjhm(IdCard);
		logEntity.setGxsj(new Date());
		if(flag) {
			//成功日志
			logEntity.setCgbs("1");
			logger.info(IdCard+"省公安厅居民身份证信息录入成功!!");
			System.out.println(IdCard+"省公安厅居民身份证信息录入成功!!");
		}else {
			//失败日志
			logEntity.setCgbs("2");
			logger.info(IdCard+"省公安厅居民身份证信息录入失败!");
			System.out.println(IdCard+"省公安厅居民身份证信息录入失败!");
		}
		//4.向TB_ST_ZJB增加一条日志,更新TB_YL_PATIENT_INFORMATION表中的TB_ST_GAGRXX字段
		progressLogDao.insert(logEntity);
		
    }
	
   
}
