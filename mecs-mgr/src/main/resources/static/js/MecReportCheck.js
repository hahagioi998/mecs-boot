$().ready(function() {
	toPrint();
});
function toPrint() {
	$.ajax({
		type : "POST", // 提交方式
		url : "../getPrintMecReportInfo.action", // 路径
		data : {},
		dataType : "json",
		success : function(respData) {
			if (respData.res == "isNull") {
				alert("信息丢失");
			} else {
				var mrNum = respData.mrNum;
				showPrintInfo(respData, mrNum);
			}
		},
		error : function() {
			alert("请求失败");
		},
	});
}

/*
 * 将详情信息显示出来
 */
function showPrintInfo(respData, mrNum) {
	/*
	 * 显示个人信息
	 */
	var user = respData.user;
	$("#userName").append(user.userName);
	$("#userName2").append(user.userName);
	$("#userSex").append(user.userSex);
	$("#userAge").append(user.userAge);
	$("#userPhone").append(user.userPhone);
	var currTime = getCurrTime();
	$("#printTime").append(currTime);
	$("#mecCode").append(mrNum);
	/*
	 * 显示体检开单时间
	 */
	var mecRecord = respData.mecRecord;
	$("#time").append(mecRecord.bilTime);

	/*
	 * 显示二维码图片
	 */
	var img = $("#img");
	img.attr("src", "../getQRcode.action?mrNum=" + mrNum);

	var tbody = $("#itemsTbody");
	tbody.html("");
	var itemsList = respData.itemsList;
	if (itemsList.length > 0) {
		/*
		 * 将体检项目信息加入到打印单的表格中
		 */
		for (var i = 0; i < itemsList.length; i++) {
			var items = itemsList[i]; // 单个项目
			var tr = $("<tr></tr>");
			var seq = $("<td style=\"width: 10%;\"></td>"); // 序号
			seq.text(i + 1);
			var dep = $("<td style=\"width: 15%;\"></td>"); // 科室
			dep.text(items.depId);
			var itemsName = $("<td style=\"width: 30%;\"></td>"); // 项目名
			itemsName.text(items.itemsName);

			tr.append(seq);
			tr.append(dep);
			tr.append(itemsName);

			tbody.append(tr);
		}
	} else {
		var tr = $("<tr></tr>");
		var nullCombo = $("<td></td>");// 空数据
		nullCombo.text("没有项目");
		nullCombo.attr("colspan", 3);
		tr.append(nullCombo);
		tbody.append(tr);
	}

	/*
	 * 将体检信息结果append到页面中
	 */
	var itemsRecordRes = $("#itemsRecordRes");
	itemsRecordRes.html("");
	for (var i = 0; i < itemsList.length; i++) {
		var items = itemsList[i];
		itemsRecordRes.append(items.itemsRes);

		itemsRecordRes.append("<br /><br /><br />");
	}

	/*
	 * 添加总结和建议
	 */
	var mecRecordReq = $("#mecRecordReq");
	mecRecordReq.html("");
	var p = $("<p></p>");
	p.append(mecRecord.mrReq);
	mecRecordReq.append(p);
	/*
	 * 添加生活指导
	 */
	var mecRecordGuide = $("#mecRecordGuide");
	mecRecordGuide.html("");
	var p1 = $("<p></p>");
	p1.append(mecRecord.mrGuide);
	mecRecordGuide.append(p1);
}

function exportWord() {
	$("#export_word").wordExport();
}

function print() {
//	bdhtml = window.document.body.innerHTML; // 前页面body内容
//	sprnstr = "<!--startprint-->"; // 开始打印内容的标识
//	eprnstr = "<!--endprint-->"; // 结束打印的标识
//	prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17); // 截取开始标识
//	prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr)); // 截取结束标识
//	window.document.body.innerHTML = prnhtml; // 截取部分重新赋予给body内容 替换
//	window.print(); // 打印
//	 var newstr = document.getElementById("printDiv").innerHTML;
//	 var oldstr = document.body.innerHTML;
//	 document.body.innerHTML = newstr;
//	 window.print();
//	 document.body.innerHTML = oldstr;
}

//
//function printpreview(){
//
//	  // 打印页面预览
//
//	  wb.execwb(7,1);
//
//	}
//
//	function printit(){
//
//	 if (confirm('确定打印吗？')){
//
//	  wb.ExecWB(6,1);
//
//	 }
//	}

/*
 * 系统获取当前时间
 */
function getCurrTime() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date
			.getMonth() + 1;
	var strDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	var currentdate = date.getFullYear() + seperator1 + month + seperator1
			+ strDate + " " + date.getHours() + seperator2 + date.getMinutes()
			+ seperator2 + date.getSeconds();
	return currentdate;
}
