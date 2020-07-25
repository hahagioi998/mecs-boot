$().ready(function() {
	
	getResultById();
});
function getResultById(){
	$.ajax({
		type : "POST", // 提交方式
		url : "../getResult.action", // 路径
		data : {
		},
		dataType : "json",
		success : function(respData) {
			var ret = respData.result;
	      $("#divn").append(ret);
		},
		error : function() {
			alert("请求失败");
		},
	});
	
	
}
 