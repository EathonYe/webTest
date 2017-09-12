$(document).ready(function () {
   query();

    /* 弹窗插件 */
    toastr.options = {
        "closeButton" : true,//是否显示关闭按钮
        "debug" : false,//是否使用debug模式
        "positionClass" : "toast-top-right",//弹出窗的位置
        "onclick" : null,
        "showDuration" : "300",//显示的动画时间
        "hideDuration" : "1000",//消失的动画时间
        "timeOut" : "5000",//展现时间
        "extendedTimeOut" : "1000",//加长展示时间
        "showEasing" : "swing",//显示时的动画缓冲方式
        "hideEasing" : "linear",//消失时的动画缓冲方式
        "showMethod" : "fadeIn",//显示时的动画方式
        "hideMethod" : "fadeOut", //消失时的动画方式
        'progressBar' : true
    };

    $('.modalClose').on('click', function () {
        $('#userForm')[0].reset();
        $('#updateUserForm')[0].reset();
    });
    $('#addUser').on('click', function () {
       var name = $('#add-name').val();
       var sex = $('#add-sex').val();
       var age = $('#add-age').val();
       if(name && sex && age) {
           $.ajax({
               url: '/operate.user.webTest',
               type: 'post',
               data: {
                   flag: 'insert',
                   name: name,
                   sex: sex,
                   age: age
               },
               success: function (resData) {
                   if(resData.success === true) {
                       toastr.success("successful");
                       $('#myModal').modal('hide');
                       query();
                       $('#userForm')[0].reset();
                   }else {
                        toastr.error("failed");
                   }
               },
               error: function () {

               }
           });
       }else {
           toastr.error("请正确填写各项", "提示");
       }
   })

    $('#update').on('click', function () {
        if($('[type=radio]:checked').length) {
            $.ajax({
                url: '/operate.user.webTest',
                type: 'get',
                data: {
                    flag: 'query',
                    id: $('[type=radio]:checked').val()
                },
                success: function (resData) {
                    var data = resData.rows[0];
                    $('#update-name').val(data.name);
                    $('#update-sex').val(data.sex);
                    $('#update-age').val(data.age);
                    $('#MyModal').modal('show');
                },
                error: function () {
                    
                }
            })
        }else {
            x0p('error', 'Please check a item.')
        }
    })

    $('#delete').on('click', function () {
        if($('[name=userId]:checked').val()) {
            x0p('Confirmation', 'Are you sure to delete the item?', 'warning', function (button) {
                if(button == 'warning') {
                    $.ajax({
                        url: '/operate.user.webTest',
                        type: 'post',
                        data: {
                            flag: 'delete',
                            id: $('[name=userId]:checked').val()
                        },
                        success: function (data) {
                            if(data.success == true) {
                                x0p('Your operation is successful.');
                                query();
                            }else {
                                x0p('error', 'Failed to delete this item.');
                            }
                        },
                        error: function () {

                        }
                    })
                }
            });
        }else {
            x0p('message', 'Please selected.');
        }
    })

    $('#query').on('click', function () {
        query();
    })

    $('#updateUser').on('click', function () {
        var name = $('#update-name').val();
        var sex = $('#update-sex').val();
        var age = $('#update-age').val();
        if(name && sex && age) {
            $.ajax({
                url: '/operate.user.webTest',
                type: 'post',
                data: {
                    flag: 'update',
                    name: name,
                    sex: sex,
                    age: age,
                    id: $('[type=radio]:checked').val()
                },
                success: function (resData) {
                    if(resData.success === true) {
                        toastr.success("successful");
                        $('#MyModal').modal('hide');
                        query();
                        $('#updateUserForm')[0].reset();
                    }else {
                        toastr.error("failed");
                    }
                },
                error: function () {

                }
            });
        }else {
            toastr.error("请正确填写各项", "提示");
        }
    })
});

function query() {
    $.ajax({
        url: '/operate.user.webTest',
        type: 'get',
        data: {
            flag: 'query',
            name: $('#nameSearch').val()
        },
        success: function (resData) {
            var data = resData.rows;
            var template = Handlebars.compile($("#seller-template").html());
            var titleTemplate = Handlebars.compile($("#title-template").html());
            // 把数据装入预编译后的页面
            $('#tableList').html(template(data));
            $('#titleList').html(titleTemplate(data));
        },
        error: function () {

        }
    });
}

