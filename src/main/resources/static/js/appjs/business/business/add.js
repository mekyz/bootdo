$().ready(function () {
    getDetail();
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    // 获取form内容
    var form = $('#signupForm')[0];
    var data = new FormData(form);
    debugger;
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/business/business/save",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                $("#img").attr("src", data.url);
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
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入姓名"
            }
        }
    })
}

function getDetail() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/business/business/detail",
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                var b = data.business;
                $("#tbStoreName").val(b.tbStoreName);
                $("#tbStartingPrice").val(b.tbStartingPrice);
                $("#tbAddress").val(b.tbAddress);
                $("#tbPhone").val(b.tbPhone);
                $("#tbPhoto").val(b.tbPhoto);
                if (b.tbPhoto != '') {
                    $("#img").attr("src", b.tbPhoto);
                }

            } else {
                // parent.layer.alert(data.msg)
            }

        }
    });

}