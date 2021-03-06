<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>系统提示</title>

<style type="text/css">
.boder {
	border: #35bded 1px solid;
}

.boder1 {
	border: #fbd44b 1px solid;
}

.left1 {
	font-size: 15px;
}

.shadow {
	FILTER: dropshadow(color =   #d0cece, offx =   1, offy =   1, positive =   1);
}

.font1 {
	font-size: 18px;
}

.font2 {
	font-size: 14px;
	color: #FFFFFF;
}

.font3 {
	font-size: 18px;
	color: #923e01;
	font-weight: bold;
}

.font31 {
	font-size: 14px;
	color: #923e01;
	font-weight: bold;
}

.font4 {
	color: #923e01;
	font-size: 12px;
}

.font5 {
	color: #0a6092;
	font-size: 14px;
	font-weight: bold;
	background-color: #e9edf3;
}

.red {
	color: red;
}

.biao td {
	border: 1px solid #d3d3e6;
	border-width: 0 1px 1px 0;
	margin: 2px 0 2px 0;
	text-align: center;
	height: 30px;
}

.boxhead {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10pt;
	font-weight: bold;
	text-decoration: none;
	color: #888888;
	background: #EEFFEE;
	padding-top: 3px;
	padding-bottom: 3px;
	padding-right: 0px;
	padding-left: 0px;
}
.sticky-wrap {
    overflow-x: auto; /* Allows wide tables to overflow its containing parent */
    position: relative;
    margin: 0 auto 1.5em;
}
a{
	text-decoration:none;
}
#valTable tr{ background: #F0FFF0;}
#valTable tr:nth-child(2n){ background: #FFFFFF;}
#valTable tr{ background-color: expression((this.sectionRowIndex % 2 == 0) ?   "#F0FFF0" : "#FFFFFF" );} 

</style>
<script language="javascript" type="text/javascript"
	src="/wheatfield/js/DatePicker/WdatePicker.js"></script>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="JavaScript" type="text/JavaScript">
	function check(obj)
	{
		if (obj == 1) {
			document.getElementById("flg_OK").value = "fenrun";
	 		if(""==document.getElementById("fileUpload").value){
	 			alert("分润文件不能为空");
	 			return false;			
	 		}
			return confirm('是否重新通过分润文件更新账户信息？'); 
		} else if (obj == 2) {
			document.getElementById("flg_OK").value = "duizhang";
	 		if(""==document.getElementById("startDate").value){
	 			alert("请输入对账日期！");
	 			return false;			
	 		}
			return confirm('是否重新生成对账文件？'); 
		}
 		
	}
</script>
</head>
<body>
	<form name="accountFrm" action="updateSettleFile" target="_self"
		method="post" ENCTYPE="multipart/form-data">
		<!-- /settlement/JR_router.jsp -->
		<input id="flg_OK" name="flg_OK" type="hidden" value=""/>
		<table border="0" width="500px" height="200px">
		<tr height="20px">
		  <td width="20%" nowrap align="right">对账日期:</td>
		  <td width="80%"><input type="text" id="startDate"
			name="startDate" value="${startDate}"
			onFocus="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate"
			style="width:100%" readonly /></td>	
		</tr>
		<tr height="20px">
		  <td width="20%" nowrap align="right">分润文件:</td>
		  <td width="80%"><input type="file" id="fileUpload"
			name="fileUpload" style="width:100%" /></td>	
		</tr>
		<tr height="20px">
			<td align="right" height="20px">
				<input type="submit" value="分润更新" onclick="return check(1);" />
				<input type="submit" value="生成对账文件" onclick="return check(2);" />
			</td>
            <td color="red" >${errCode} ${errMsg}</td>     
		</tr>
		</table>
	</form>
</body>
</html>
