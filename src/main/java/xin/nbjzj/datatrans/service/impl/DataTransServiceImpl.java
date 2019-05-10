package xin.nbjzj.datatrans.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.nbjzj.datatrans.dao.DibaoInfoDao;
import xin.nbjzj.datatrans.dao.DisabledInfoDao;
import xin.nbjzj.datatrans.dao.NewCardDao;
import xin.nbjzj.datatrans.dao.PatientInfoDao;
import xin.nbjzj.datatrans.dao.ProgressLogDao;
import xin.nbjzj.datatrans.entity.PatientInforEntity;
import xin.nbjzj.datatrans.entity.ProgressLogEntity;
import xin.nbjzj.datatrans.service.DataTransService;
import xin.nbjzj.datatrans.service.DibaoInfoService;
import xin.nbjzj.datatrans.service.DisabledInfoService;
import xin.nbjzj.datatrans.service.NewCardService;
import xin.nbjzj.datatrans.utils.IdCardUtils;



@Service("DataTransService")
public class DataTransServiceImpl implements DataTransService {

	protected static final Logger logger = LoggerFactory.getLogger(DataTransServiceImpl.class);
	@Autowired
	PatientInfoDao patientInfoDao;
	@Autowired
	NewCardService newCardService;
	@Autowired
	DibaoInfoService dibaoInfoService;
	@Autowired
	DisabledInfoService disabledInfoService;
	@Autowired
	NewCardDao newCardDao;
	@Autowired
	ProgressLogDao progressLogDao;

	@Autowired
	DisabledInfoDao disabledInfoDao;
	
	
  
	@Override
	public void getNewCardData(Integer number) throws IOException {
		//1.获取优先需要查询的number个人
		logger.info("NewCardService number check:"+number);
		int start = progressLogDao.countByBM(NewCardDao.TABLE_NAME);
		logger.info("_________LogCount_______:"+start);
		
		List<PatientInforEntity> lists = patientInfoDao.findAll(start,number);
		logger.info("共有"+lists.size()+"人将进行接口请求录入身份信息表");
		if(lists!=null) {
			for (PatientInforEntity entity : lists) {
				//2.通过接口获得每个人的数据
				String IdCard = entity.getZjhm();
				logger.info("idcard"+IdCard);
				if(IdCardUtils.isIDCard(IdCard)) {
					newCardService.getData(entity);
				}else {
					ProgressLogEntity logEntity = new ProgressLogEntity();
					logEntity.setBm(NewCardDao.TABLE_NAME);
					logEntity.setZjhm("");
					logEntity.setGxsj(new Date());
					logEntity.setCgbs("2");
					logger.info(IdCard+"省公安厅居民身份证信息录入失败!错误的证件号码");
					System.out.println(IdCard+"省公安厅居民身份证信息录入失败!错误的证件号码");
					progressLogDao.insert(logEntity);
				}
			}
		}
	}
	@Override
	public void getDibaoInfoData(Integer number) throws IOException {
		//1.获取优先需要查询的number个人
		logger.info("DibaoInfoService number check:"+number);
		int start = progressLogDao.countByBM(DibaoInfoDao.TABLE_NAME);
		logger.info("_________LogCount_______:"+start);
		
		List<PatientInforEntity> lists = patientInfoDao.findAll(start,number);
		logger.info("共有"+lists.size()+"人将进行接口请求录入身份信息表");
		if(lists!=null) {
			for (PatientInforEntity entity : lists) {
				//2.通过接口获得每个人的数据
				String IdCard = entity.getZjhm();
				if(IdCardUtils.isIDCard(IdCard)) {
					dibaoInfoService.getData(entity);
				}else {
					ProgressLogEntity logEntity = new ProgressLogEntity();
					logEntity.setBm(DibaoInfoDao.TABLE_NAME);
					logEntity.setZjhm("");
					logEntity.setGxsj(new Date());
					logEntity.setCgbs("2");
					logger.info(IdCard+"低保信息录入失败录入失败!错误的证件号码");
					System.out.println(IdCard+"低保信息录入失败录入失败!错误的证件号码");
					progressLogDao.insert(logEntity);
				}
			}
		}
	}
	
	@Override
	public void getDisabledInfoData(Integer number) throws IOException {
		//1.获取优先需要查询的number个人
		logger.info("DisabledInfoService number check:"+number);
		int start = progressLogDao.countByBM(DisabledInfoDao.TABLE_NAME);
		logger.info("_________LogCount_______:"+start);
		
		List<PatientInforEntity> lists = patientInfoDao.findAll(start,number);
		logger.info("共有"+lists.size()+"人将进行接口请求录入身份信息表");
		if(lists!=null) {
			for (PatientInforEntity entity : lists) {
				//2.通过接口获得每个人的数据
				String IdCard = entity.getZjhm();
				if(IdCardUtils.isIDCard(IdCard)) {
					disabledInfoService.getData(entity);
				}else {
					ProgressLogEntity logEntity = new ProgressLogEntity();
					logEntity.setBm(DisabledInfoDao.TABLE_NAME);
					logEntity.setZjhm("");
					logEntity.setGxsj(new Date());
					logEntity.setCgbs("2");
					logger.info(IdCard+"残疾人信息录入失败!错误的证件号码");
					System.out.println(IdCard+"残疾人信息录入失败!错误的证件号码");
					progressLogDao.insert(logEntity);
				}
			}
		}
	}

}
