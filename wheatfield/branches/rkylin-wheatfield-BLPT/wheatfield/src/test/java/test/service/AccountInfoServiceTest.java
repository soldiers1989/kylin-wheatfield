package test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.wheatfield.BaseJUnit4Test;

import com.rkylin.gaterouter.dto.bankpayment.BatchPaymentRespDto;
import com.rkylin.gaterouter.dto.bankpayment.PaymentRespDto;
import com.rkylin.wheatfield.api.AccountInfoApi;
import com.rkylin.wheatfield.api.AccountInfoDubboService;
import com.rkylin.wheatfield.constant.Constants;
import com.rkylin.wheatfield.model.AccountInfoQueryResponse;
import com.rkylin.wheatfield.model.BalanceDeResponse;
import com.rkylin.wheatfield.model.CommonResponse;
import com.rkylin.wheatfield.model.FinAccountResponse;
import com.rkylin.wheatfield.model.PingAnParam;
import com.rkylin.wheatfield.pojo.AccountInfor;
import com.rkylin.wheatfield.pojo.TransOrderInfo;
import com.rkylin.wheatfield.pojo.User;
import com.rkylin.wheatfield.service.AccountInfoService;
import com.rkylin.wheatfield.service.ParameterInfoService;
import com.rkylin.wheatfield.service.PaymentAccountService;
import com.rkylin.wheatfield.service.SettlementServiceThr;

public class AccountInfoServiceTest   extends BaseJUnit4Test{

	@Autowired
	private AccountInfoService accountInfoService; 
	
    @Autowired
    private AccountInfoDubboService accountInfoDubboService;
	
    @Autowired
    private PaymentAccountService paymentAccountService;
    
    @Autowired
    private SettlementServiceThr settlementServiceThr;
    
    @Autowired
    private ParameterInfoService parameterInfoService;
    
    @Autowired
    private AccountInfoApi accountInfoApi;
 
    @Test
    public void accountInfoQueryByDubboTest(){
        AccountInfor accountInfor = new AccountInfor();
        accountInfor.setRootInstCd("M000011");
        accountInfor.setUserId("5");
        accountInfor.setAccountNumber("232323");
        accountInfor.setBankHeadName("中国工商银行");
        accountInfor.setWhetherRealName("1");
        accountInfor.setStatusId(6);
        AccountInfoQueryResponse res = accountInfoApi.accountInfoQueryByDubbo(accountInfor);
        System.out.println(res);
    }
    
    @Test
    public void noticeTransferGaterouterTest1(){
        PaymentRespDto paymentRespDto =new PaymentRespDto();
        paymentRespDto.setOrgNo("M000001");
        paymentRespDto.setTransNo("14742654853642824653");
        paymentRespDto.setStatusId(17);
        paymentRespDto.setChannelMsg("通知错误信息");
        String res = accountInfoDubboService.noticeTransferGaterouter(paymentRespDto);
        System.out.println(res);
    }
    
    @Test
    public void noticeTransferGaterouterTest2(){
        com.rkylin.gaterouter.dto.bankpayment.BatchPaymentRespDto dto = new BatchPaymentRespDto();
        dto.setBatchNo("AchillesHonor2");
        List<PaymentRespDto> respDtoList = new ArrayList<PaymentRespDto>();
        PaymentRespDto paymentRespDto =new PaymentRespDto();
        paymentRespDto.setTransNo("14742654853642824653");
        paymentRespDto.setStatusId(17);
        paymentRespDto.setChannelMsg("通知错误信息");
        respDtoList.add(paymentRespDto);
//        PaymentRespDto paymentRespDto2 =new PaymentRespDto();
//        paymentRespDto2.setTransNo("14696221596638985195");
//        paymentRespDto2.setStatusId(16);
//        respDtoList.add(paymentRespDto2);
        dto.setPaymentRespDtoList(respDtoList);
        String res = accountInfoDubboService.noticeTransferGaterouter(dto);
        System.out.println(res);
    }
    
    @Test
    public void noticeBatchTransferTest(){
        com.rkylin.gaterouter.dto.bankpayment.BatchPaymentRespDto dto = new BatchPaymentRespDto();
        dto.setBatchNo("345677");;
        List<PaymentRespDto> respDtoList = new ArrayList<PaymentRespDto>();
        PaymentRespDto paymentRespDto =new PaymentRespDto();
        paymentRespDto.setTransNo("14696221596638918450");
        paymentRespDto.setStatusId(14);
        respDtoList.add(paymentRespDto);
        PaymentRespDto paymentRespDto2 =new PaymentRespDto();
        paymentRespDto2.setTransNo("14696221596638985195");
        paymentRespDto2.setStatusId(16);
        respDtoList.add(paymentRespDto2);
        dto.setPaymentRespDtoList(respDtoList);
        String res = accountInfoDubboService.noticeBatchTransfer(dto);
        System.out.println(res);
    }
    
    @Test
    public void parameterInfoServiceTest(){
        PingAnParam pingAnParam = parameterInfoService.getPingAnInteriorTransferParam();
        pingAnParam = parameterInfoService.getPingAnOutTransferParam();
        pingAnParam = parameterInfoService.getPingAnOpenParam();
        System.out.println(pingAnParam);
    }
    
    @Test
    public void noticeSingleTransferTest(){
        com.rkylin.gaterouter.dto.bankpayment.PaymentRespDto dto = new PaymentRespDto();
//        dto.setTransNo("121796");
        dto.setTransNo("324252352352525252");
        dto.setStatusId(16);
        String res = accountInfoDubboService.noticeSingleTransfer(dto);
        System.out.println(res);
    }
    
    @Test
    public void summarizing40142Test(){
        CommonResponse res = settlementServiceThr.summarizing40142();
        System.out.println(res);
    }
    
    @Test
    public void adjustmentWithhold40142Test(){
        CommonResponse res = settlementServiceThr.adjustmentWithhold40142(null,null);
        System.out.println(res);
    }
    
       @Test
       public void refundPingAnTest(){
           com.rkylin.gaterouter.dto.bankpayment.PaymentRespDto dto = new PaymentRespDto();
           dto.setTransNo("3");
           String res = accountInfoDubboService.refundPingAn(dto);
           System.out.println(res);
       }
	
       @Test
       public void withhold40141Test(){
           TransOrderInfo transOrderInfo = new TransOrderInfo();
           transOrderInfo.setOrderNo("wewr52465gh2324");
           transOrderInfo.setAmount(1L);
           transOrderInfo.setUserId("18701514648");
           transOrderInfo.setFuncCode("40141");
           transOrderInfo.setInterMerchantCode("18801438893");
           transOrderInfo.setMerchantCode("M000003");
           transOrderInfo.setOrderAmount(1L);
           transOrderInfo.setOrderCount(1);
           transOrderInfo.setOrderDate(new Date());
           transOrderInfo.setRequestTime(new Date());
           transOrderInfo.setStatus(1);
           transOrderInfo.setUserFee(0L);
           transOrderInfo.setProductIdd("P000005");
           CommonResponse res = paymentAccountService.withhold40141(transOrderInfo);
           System.out.println(res);
       }
       
       @Test
       public void withhold40142Test(){
           TransOrderInfo transOrderInfo = new TransOrderInfo();
           transOrderInfo.setOrderNo("655U65U6");
           transOrderInfo.setAmount(1L);
           transOrderInfo.setUserId("18701514648");
           transOrderInfo.setFuncCode("40142");
           transOrderInfo.setInterMerchantCode("18801438893");
           transOrderInfo.setMerchantCode("M000003");
           transOrderInfo.setOrderAmount(1L);
           transOrderInfo.setOrderCount(1);
           transOrderInfo.setOrderDate(new Date());
           transOrderInfo.setRequestTime(new Date());
           transOrderInfo.setStatus(1);
           transOrderInfo.setUserFee(0L);
           transOrderInfo.setProductIdd("P000005");
           CommonResponse res = paymentAccountService.withhold40142(transOrderInfo);
           System.out.println(res);
       }
	   
       @Test
       public void getAllSubAccountMiceTest(){
           List<com.rkylin.wheatfield.bean.User> userList = new ArrayList<com.rkylin.wheatfield.bean.User>();
           com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
           user.setInstCode("M000003");
           user.setProductId(Constants.HT_PINGAN_PAY_CHILD);
           userList.add(user);
//           com.rkylin.wheatfield.bean.User user1 = new com.rkylin.wheatfield.bean.User();
//           user1.setInstCode("M000003");
//           user1.setProductId(Constants.HT_PINGAN_PAY_CHILD);
//           user1.setUserId("9879798");
//           userList.add(user1);
           CommonResponse res = accountInfoDubboService.getSubAccountMice(userList);
           System.out.println(res);
       }
       
       @Test
       public void openSubAccountMiceTest(){
           com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
           user.setUserId("18701514648");
           user.setInstCode("M000003");
           user.setProductId("P000005");
           user.setName("哈德良长城");//子账户姓名
           user.setRoleCode("232");
           CommonResponse res = accountInfoDubboService.openSubAccountMice(user);
           System.out.println(res);
       }
	   
	    @Test
	    public void openSubAccountTest(){
	        User user = new User();
	        user.userId="18701514648";
//	      user.userId="18801438893";
	      user.constId ="M000001";
//	      user.productId ="P000002";
	        user.productId ="P000012";
//	      user.referUserId="M000002";
	        user.referUserId="M000002";
//	        user.creditType="101";
//	        FinanaceEntry fin = new FinanaceEntry();
//	        fin.setPaymentAmount(0L);
//	        fin.setReferId("8989800");
	        CommonResponse res = accountInfoService.openSubAccount(user,null);
	        System.out.println(res);
	    }
	
       @Test
        public void openSubAccountsTest(){
           List<User> list = new ArrayList<User>();
            User user = new User();
            user.userId="18701514648";
            user.constId ="M000001";
            user.productId ="P000012";
            list.add(user);
            User user2 = new User();
            user2.userId="18701514648";
            user2.constId ="M000001";
            user2.productId ="P000013";
            list.add(user2);
            CommonResponse res = accountInfoDubboService.openSubAccounts(list);
            System.out.println(res);
        }
	    
	@Test
	public void getBalanceTest(){
		com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
		user.setInstCode("M000001");;
//		user.setProductId("P000002");
		user.setUserId("18701514648");
//		user.setCardNo("000781030476");;
		BalanceDeResponse res = accountInfoService.getBalance(user);
		System.out.println(res);
		
	}
	
	   @Test
	    public void getFinAccountTest(){
	        com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
	        user.setInstCode("M000001");
	        user.setUserId("18701514648");
	        user.setType(new String[0]);;;
	        FinAccountResponse res = accountInfoService.getFinAccount(user);
	        System.out.println(res);
	    }
	
	@Test
	public void passwordCheckTest(){
		com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
		user.setInstCode("M000001");
		user.setPassword("444444");
		user.setProductId("P000002");
		user.setUserId("18701514648");
//		user.setType("Q");
		CommonResponse res = accountInfoService.passwordCheck(user);
		System.out.println(res);
		
	}

	   @Test
	    public void addOrUpdatePasswordTest(){
	        com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
	        user.setInstCode("M000001");
	        user.setPassword("444444");
	        user.setProductId("P000002");
	        user.setUserId("18701514648");
	        user.setOperType("insert");
	        user.setType(new String[]{"Q"});
	        CommonResponse res = accountInfoDubboService.addOrUpdatePassword(user);
	        System.out.println(res);
	        
	    }
	
       @Test
       public void checkPasswordIfExistTest(){
           com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
           user.setInstCode("M000001");
//           user.setPassword("444444");
//           user.setProductId("P000002");
           user.setUserId("18701514648");
//           user.setOperType("insert");
           user.setType(new String[]{"Q"});
           CommonResponse res = accountInfoDubboService.checkPasswordIfExist(user);
           System.out.println(res);
           
       }
       
       @Test
       public void passLockOrUnlockTest(){
           com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
           user.setInstCode("M000001");
           user.setProductId("P000002");
           user.setUserId("18701514648");
           user.setOperType("unlock");
           user.setType(new String[]{"Q"});
           CommonResponse res = accountInfoDubboService.passLockOrUnlock(user);
           System.out.println(res);
           
       }       
       
	@Test
    public void getFinAccountBalanceTest(){
        com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
	    user.setInstCode("M000001");
        user.setProductId("P000002");
        user.setUserId("18701514648");
        CommonResponse res = accountInfoDubboService.getFinAccountBalance(user);
        System.out.println(res);
        
    }
	
	   @Test
	    public void getAllUserInfoTest(){
	        com.rkylin.wheatfield.bean.User user = new com.rkylin.wheatfield.bean.User();
	        user.setInstCode("M000001");
	        user.setUserId("18701514648");
	        CommonResponse res = accountInfoDubboService.getAllUserInfo(user);
	        System.out.println(res);
	        
	    }
}
