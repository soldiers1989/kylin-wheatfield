package com.rkylin.wheatfield.task;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.rkylin.wheatfield.service.AccountManageService;

public class AccountTask {
	private static Logger logger = LoggerFactory.getLogger(AccountTask.class);
	@Autowired
	private AccountManageService accountManageService;
	
	/**
	 * 代付一分钱校验对公账户（定时调度）  2015-09-06
	 * @return 1：正常；                 其他会返回相应的错误码
	 */
	public void payJudgePublicAccount(){
		logger.info("--------------------一分钱代付(新)task  开始----------------------------------");
		accountManageService.paymentJudgePublicAccount();
		logger.info("--------------------一分钱代付(新)task  结束 ----------------------------------");
	}
	
	/**
	 * 查询对公账户一分钱代付结果，修改状态（定时调度）
	 */
	public void updatePubAccByPayResult(){
		logger.info("--------------------查询对公账户一分钱代付结果，修改状态(新)task  开始----------------------------------");
		accountManageService.updatePubAccountByPayResult();
		logger.info("--------------------查询对公账户一分钱代付结果，修改状态(新)task  结束----------------------------------");
	}
	
}
