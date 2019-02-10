<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Shop | 分类管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <!-- zTree CSS -->
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css"/>
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
                分类管理
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">分类管理</li>
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
                            <h3 class="box-title">${tbContentCategory.id == null ? "新增" : "编辑"}管理</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/category/save"
                                   method="post"
                                   modelAttribute="tbContentCategory">
                            <div class="box-body">
                                <form:hidden path="id"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">父级类目</label>
                                    <div class="col-sm-10">
                                        <fomr:hidden path="parent.id"/>
                                        <input id="parentName" class="form-control required" placeholder="请选择"
                                               readonly="true" data-toggle="modal"
                                               data-target="#modal-default" value="${tbContentCategory.parent.name}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">分类名称</label>
                                    <div class="col-sm-10">
                                        <form:input path="name" cssClass="form-control required" placeholder="分类名称"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="sortOrder" class="col-sm-2 control-label">排序</label>
                                    <div class="col-sm-10">
                                        <form:input path="sortOrder" cssClass="form-control required digits"
                                                    placeholder="排序"/>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
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
<!-- 加载自定义模态对话框 -->
<sys:modal title="请选择" message="<ul id='zTree' class='ztree'></ul>"/>

<script>
    $(function () {
        var url = "/content/category/tree/data";
        var autoParam = ["id"];
        var callback = function (nodes) {
            var node = nodes[0];
            $("#parentId").val(node.id);
            $("#parentName").val(node.name);
            $("#modal-default").modal("hide");
        }
        App.initZTree(url, autoParam, callback);
    });
</script>
</body>
</html>