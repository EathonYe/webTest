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

   $('#addUser').on('click', function () {
       var name = $('#add-name').val();
       var sex = $('#add-sex').val();
       var age = $('#add-age').val();
       if(name && sex && age) {
           $.ajax({
               url: '/operate.user.webTest',
               type: 'get',
               data: {
                   flag: 'insert',
                   name: name,
                   sex: sex,
                   age: age
               },
               success: function (resData) {
                   if(resData.success === true) {
                       toastr.success("successful");
                       setTimeout(function () {
                           window.location.reload();
                       }, 1000);
                   }else {
                        toastr.error("failed");
                   }
               },
               error: function () {

               }
           });
       }
   })
});

function query() {
    $.ajax({
        url: '/operate.user.webTest',
        type: 'get',
        data: {
            flag: 'query'
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

