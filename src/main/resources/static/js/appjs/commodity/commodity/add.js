$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    // 获取form内容
    var form = $('#signupForm')[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/commodity/commodity/save",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg)
            }
        },
        error: function (e) {
            parent.layer.alert("Connection error");
        }
    });
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}