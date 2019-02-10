<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Shop | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <!-- zTree CSS -->
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css"/>
    <!-- DropZone CSS -->
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dropzone.css"/>
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css"/>
    <!-- wangEditor CSS -->
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容管理</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id == null ? "新增" : "编辑"}管理</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post"
                                   modelAttribute="tbContent">
                            <div class="box-body">
                                <form:hidden path="id"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">父级类目</label>
                                    <div class="col-sm-10">
                                        <fomr:hidden id="categoryId" path="tbContentCategory.id"/>
                                        <input id="categoryName" class="form-control required" placeholder="请选择"
                                               readonly="true" data-toggle="modal"
                                               data-target="#modal-default"
                                               value="${tbContent.tbContentCategory.name}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>
                                    <div class="col-sm-10">
                                        <form:input path="title" cssClass="form-control required" placeholder="标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>
                                    <div class="col-sm-10">
                                        <form:input path="subTitle" cssClass="form-control required"
                                                    placeholder="子标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>
                                    <div class="col-sm-10">
                                        <form:input path="titleDesc" cssClass="form-control required"
                                                    placeholder="标题描述"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>
                                    <div class="col-sm-10">
                                        <form:input path="url" cssClass="form-control"
                                                    placeholder="链接"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片1</label>
                                    <div class="col-sm-10">
                                        <form:input path="pic" cssClass="form-control"
                                                    placeholder="图片1" readonly="true"/>
                                        <div id="dropz" class="dropzone">

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>
                                    <div class="col-sm-10">
                                        <form:input path="pic2" cssClass="form-control"
                                                    placeholder="图片2" readonly="true"/>
                                        <div id="dropz2" class="dropzone">

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="content" class="col-sm-2 control-label">内容</label>
                                    <div class="col-sm-10">
                                        <form:hidden path="content"/>
                                        <div id="editor">
                                                ${tbContent.content}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button id="btnSubmit" type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                        <!-- /.box -->
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp"/>
</div>
<jsp:include page="../includes/footer.jsp"/>
<!-- zTree JS -->
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<!-- DropZone JS -->
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<!-- wangEditor CSS -->
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<!-- 加载自定义模态对话框 -->
<sys:modal title="请选择" message="<ul id='zTree' class='ztree'></ul>"/>

<script>
    $(function () {
        var url = "/content/category/tree/data";
        var autoParam = ["id"];
        var callback = function (nodes) {
            var node = nodes[0];
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            $("#modal-default").modal("hide");
        }
        App.initZTree(url, autoParam, callback);

        App.initWangEdtor("/upload/uploadImg", "editorFiles", "#content");
    });

    App.initDropzone({
        id: "#dropz",
        url: "/upload/uploadImg",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic").val(data.fileName);
            });
            this.on("removedfile", function (file) {
                // 删除文件时触发的方法
                remove("#pic");
            });
        }
    });

    App.initDropzone({
        id: "#dropz2",
        url: "/upload/uploadImg",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic2").val(data.fileName);
            });
            this.on("removedfile", function (file) {
                // 删除文件时触发的方法
                remove("#pic2");
            });
        }
    });

    /**
     * 移除函数
     * @param id 文本框的id
     */
    function remove(id) {
        var fileName = $(id).val();
        $.ajax({
            type: "POST",
            url: "/upload/remove",
            data: {
                fileName: fileName
            },
            success: function (data) {
                var msg = data.msg;
                alert(msg);
                $(id).val("");
            }
        });
    }

</script>
</body>
</html>