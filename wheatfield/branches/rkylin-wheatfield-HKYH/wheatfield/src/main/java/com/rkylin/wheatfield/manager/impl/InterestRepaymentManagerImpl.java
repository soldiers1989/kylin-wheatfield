/*
 * Powered By chanjetpay-code-generator
 * Web Site: http://www.chanjetpay.com
 * Since 2014 - 2015
 */

package com.rkylin.wheatfield.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.rkylin.wheatfield.dao.InterestRepaymentDao;
import com.rkylin.wheatfield.manager.InterestRepaymentManager;
import com.rkylin.wheatfield.pojo.InterestRepayment;
import com.rkylin.wheatfield.pojo.InterestRepaymentQuery;

@Component("interestRepaymentManager")
public class InterestRepaymentManagerImpl implements InterestRepaymentManager {
	
	@Autowired
	@Qualifier("interestRepaymentDao")
	private InterestRepaymentDao interestRepaymentDao;
	
	@Override
	public void saveInterestRepayment(InterestRepayment interestRepayment) {
		if (interestRepayment.getInterId() == null) {
			interestRepaymentDao.insertSelective(interestRepayment);
		} else {
			interestRepaymentDao.updateByPrimaryKeySelective(interestRepayment);
		}
	}
	
	@Override
	public InterestRepayment findInterestRepaymentById(Long id) {
		return interestRepaymentDao.selectByPrimaryKey(id);
	}
	
	@Override
	public List<InterestRepayment> queryList(InterestRepaymentQuery query) {
		return interestRepaymentDao.selectByExample(query);
	}
	
	@Override
	public void deleteInterestRepaymentById(Long id) {
		interestRepaymentDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteInterestRepayment(InterestRepaymentQuery query) {
		interestRepaymentDao.deleteByExample(query);
	}

	@Override
	public List<InterestRepayment> queryListAndParam(
			InterestRepaymentQuery query) {
		return interestRepaymentDao.selectInterAndParamByExample(query);
	}
	
	@Override
	public List<InterestRepayment> queryInterestRepaymentList(
			InterestRepaymentQuery query) {
		return interestRepaymentDao.selectInterestRepaymentExample(query);
	}

	@Override
	public List<InterestRepayment> queryForRepayment(InterestRepaymentQuery query) {
		return interestRepaymentDao.selectForRepayment(query);
	}
	
	@Override
	public List<InterestRepayment> queryForUpload(InterestRepaymentQuery query) {
		return interestRepaymentDao.selectRepaymentForUpload(query);
	}

	@Override
	public int countByExample(InterestRepaymentQuery query){
		return interestRepaymentDao.countByExample(query);
	}
	
	@Override
	public List<InterestRepayment> queryForCapital(String rootInstCd) {
		return interestRepaymentDao.selectForCapital(rootInstCd);
	}
	
	@Override
	public List<InterestRepayment> queryForInterest(String rootInstCd) {
		return interestRepaymentDao.selectForInterest(rootInstCd);
	}
}

