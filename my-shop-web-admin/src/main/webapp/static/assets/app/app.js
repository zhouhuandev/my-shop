var App = function () {


    var _masterCheckbox;
    var _checkbox

    //用户存放id的数组
    var _idArray;

    //默认的Dropzone配置
    var defaultDropzoneOpts = {
        url: "", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropzFile", // 约定为dropzFile
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        dictRemoveFile: "移除"
    };

    /**
     * 私有方法，初始化ICheck
     */
    var
        handlerInitCheckbox = function () {
            //iCheck for checkbox and radio inputs
            //激活
            $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                checkboxClass: 'icheckbox_minimal-blue',
                radioClass: 'iradio_minimal-blue'
            });

            //获取控制端Checkbox
            _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

            //获取全部Checkbox集合
            _checkbox = $('input[type="checkbox"].minimal');
        };

    /**
     * 全选功能
     */
    var handlerCheckboxAll = function () {
        //绑定_masterCheckbox点击事件
        _masterCheckbox.on('ifClicked', function (event) {
            //返回true表示未选中
            if (event.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            //选中状态
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    var handlerDeleteSingle = function (url, id, msg) {
        //可选参数
        if (!msg) msg = null;

        //将id放入数组中，以便和批量删除通用
        _idArray = new Array();
        _idArray.push(id);

        $("#modal-message").html(msg == null ? "您确定要删除数据项吗?" : msg);
        $("#modal-default").modal("show");
        //模态对话框的确认按钮绑定删除事件，如果用户选择了数据项，则调用删除方法
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });

    };
    /**
     * 批量删除
     */
    var handlerDeleteMulti = function (url) {

        _idArray = new Array();

        //将选中元素的id放入数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });
        //判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("您还未选择任何数据项，请至少选择一项数据！");
        }

        else {
            $("#modal-message").html("您确定要删除数据项吗！" + _idArray.toString());
        }
        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");
        //模态对话框的确认按钮绑定删除事件，如果用户选择了数据项，则调用删除方法
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });
    };

    /**
     * AJAX 异步删除
     * @param url
     */
    var handlerDeleteData = function (url) {

        $("#modal-default").modal("hide");

        //如果没有选择数据项的处理
        if (_idArray.length === 0) {
            //...
        }

        //删除操作
        else {
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {
                        "ids": _idArray.toString()
                    },
                    "dataType": "JSON",
                    "success": function (data) {
                        //请求成功后，无论是成功或者是失败，都需要弹出模态对话框提示，所以这里需要先解绑原来的click事件
                        $("#btnModalOk").unbind("click");
                        //如果返回成功，状态码为200，页面刷新一下，重新加载
                        if (data.status === 200) {
                            //刷新页面
                            $("#btnModalOk").bind("click", function () {
                                setTimeout(function () {
                                    $("#modal-default").modal("hide");
                                }, 200);
                                window.location.reload();
                            });
                        }
                        //否则为失败状态码为500，然后解绑模态对话框的确定按钮事件，重新绑定新事件
                        else {
                            //确定按钮的事件改为隐藏模态对话框
                            $("#btnModalOk").bind("click", function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        //因为无论如何都需要提示信息，所以这里的模态框是必须调用的
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            }, 500);

        }
    }

    /**
     * 初始化 DataTables
     * @param url
     * @param columns
     * @returns {*|jQuery}
     */
    var handlerInitDataTables = function (url, columns) {

        //封装成对象返回
        var _dataTable = $("#dataTable").DataTable({
            "paging": true, //关闭分页功能
            "info": true, //控制是否显示表格左下角的信息
            "lengthChange": false,//是否允许用户改变表格每页显示的记录数
            "ordering": false,//是否允许Datatables开启排序
            "processing": true,//是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)
            "searching": false,//是否允许Datatables开启本地搜索
            "serverSide": true,//是否开启服务器模式
            "deferRender": true,//控制Datatables的延迟渲染，可以提高初始化的速度
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            //表格每次重绘回调函数
            "drawCallback": function (settings) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    /**
     * 初始化ZTree
     * @param url 地址
     * @param autoParam 数组
     * @param callback 回调函数
     */
    var handlerInitZTree = function (url, autoParam, callback) {
        var setting = {
            view: {
                selectedMulti: false //是否可以多选
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam
            }
        };

        $.fn.zTree.init($("#zTree"), setting);

        $("#btnModalOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("zTree");
            var nodes = zTree.getSelectedNodes();

            //未选中
            if (nodes.length == 0) {
                alert("请至少选择一个节点");
            }
            //已选择
            else {
                //回调函数
                callback(nodes);
            }
        })
    };

    /**
     * 初始化 Dropzone
     *
     * @param opts
     */
    var handlerInitDropZone = function (opts) {
        //关闭 Dropzone 的自动发现功能
        Dropzone.autoDiscover = false;
        //defaultDropzoneOpts 默认配置对象集成 opts 对象
        $.extend(defaultDropzoneOpts, opts);
        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    }

    /**
     * 初始化编辑器
     * @param url 上传图片的服务器地址
     * @param fileName 约定文件名
     * @param hideId 隐藏域 id
     */
    var handlerInitWangEdtor = function (url, fileName, hideId) {
        //初始化编辑器
        var E = window.wangEditor;
        var editor = new E('#editor');
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = url;
        editor.customConfig.uploadFileName = fileName;

        // 进行下文提到的其他配置
        // ……
        // ……
        // ……
        editor.create();

        $("#btnSubmit").bind("click", function () {
            var contentHtml = editor.txt.html();
            $(hideId).val(contentHtml);
        })
    }

    /**
     * 查看详情
     * @param url
     */
    var handlerInitShowDetail = function (url) {
        //这里是通过 Ajax 请求 html 的方式，将 jsp 装载进模态框
        $.ajax({
            url: url,
            async: true,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    }

    //开放方法接口
    return {
        /**
         * 初始化
         */
        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        /**
         * 删除单独的数据项
         * @param url
         * @param id
         * @param msg
         */
        deleteSingle: function (url, id, msg) {
            handlerDeleteSingle(url, id, msg);
        },
        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },
        
        /**
         * 初始化 DataTables
         * @param url
         * @param columns
         * @returns {*|jQuery}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        initZTree: function (url, autoParam, callback) {
            handlerInitZTree(url, autoParam, callback);
        },

        /**
         * 初始化 Dropzone
         * @param opts
         */
        initDropzone: function (opts) {
            handlerInitDropZone(opts);
        },
        /**
         * 初始化 挖能Editor
         * @param url
         * @param fileName
         * @param hideId
         */
        initWangEdtor: function (url, fileName, hideId) {
            handlerInitWangEdtor(url, fileName, hideId);
        },
        /**
         * 显示详情
         * @param url
         */
        initShowDetail: function (url) {
            handlerInitShowDetail(url);
        }
    }

}();

$(document).ready(function () {
    App.init();
});