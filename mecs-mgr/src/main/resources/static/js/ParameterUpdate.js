	
$().ready(function() {
	initUpdatePanel();
});
// 日期控件
layui
		.use(
				[ 'form', 'layedit', 'laydate' ],
				function() {
					var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					// 日期
					laydate.render({
						elem : '#date'
					});
					laydate.render({
						elem : '#date1'
					});

					// 日期
					laydate.render({
						elem : '#date2'
					});
					laydate.render({
						elem : '#date3'
					});

				});

var dictId ="";
// 初始化界面 消息回填
function initUpdatePanel() {
	$.ajax({
		url : "../showDictMessage.action",
		data : {
		},
		type : "post",
		dataType : "json",
		success : function(respData) {
			dictId = respData.dictId;
			//回填参数信息
			$("#dictname").val(respData.dictName);
			$("#dictDescribe").val(respData.dictDescribe);

		},
		error : function() {
			alert("操作失败")
		}
	});

}

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

// 修改人员确定
var dictName = "";
var dictDescribe = "";

// 修改参数
$("#sure").on("click", function() {
	var layer = layui.layer;
	dictName = $("#dictname").val();
	dictDescribe = $("#dictDescribe").val();
	if (dictName == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('参数名不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
                icon : 2
			});
		});
		return;
	}
	if (dictDescribe == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('参数描述不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
                icon : 2
			});
		});
		return;
	}

	$.ajax({
		url : "../editDict.action",
		data : {
			dictId:dictId,
			dictName : dictName,
			dictDescribe : dictDescribe,

		},
		type : "post",
		dataType : "json",
		success : function(respData) {
			layui.use('layer', function() {
				layer.msg(respData.res);
				setTimeout(function() {
					// 关闭弹出的html页面
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
				}, 2000);// 2秒
			});

		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('操作失败，联系超管！', {
					time : 2000, // 10s后自动关闭
					area : '230px',
	                icon : 2
				});
			});
		}
	});

});
