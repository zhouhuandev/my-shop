/**
 * 函数对象
 * 闭包
 * 增强了js的安全性
 * 一个方法只做一件事
 */
var Validata = function () {


    /**
     * 初始化jQuery Validate
     * 私有方法函数
     */
    var handleerInitValidate = function () {
        //初始化inputForm表单的验证
        $(function () {
            $("#inputForm").validate({
                errorElement: 'span',//错误追加span标签
                errorClass: 'help-block',//错误追加help-block类

                //错误执行的方法
                errorPlacement: function (error, element) {
                    element.parent().parent().attr("class", "form-group has-error");
                    error.insertAfter(element);
                }
            });
        });

    };
    /**
     * 增加自定义验证规则
     */
    var handlerInitCustomValidate = function () {
        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    };

    /**
     * 公共方法函数
     */
    return {
        /**
         * 初始化
         */
        init: function () {
            handlerInitCustomValidate();
            handleerInitValidate();
        }
    }
}();

//初始化验证表单功能
$(document).ready(function () {
    Validata.init();
});