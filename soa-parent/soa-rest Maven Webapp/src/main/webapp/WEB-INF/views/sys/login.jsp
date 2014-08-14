<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${staticCtx}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span12">
						<div class="navbar">
							<div class="navbar-inner">
								<div class="container-fluid">
									<a data-target=".navbar-responsive-collapse"
										data-toggle="collapse" class="btn btn-navbar"><span
										class="icon-bar"></span><span class="icon-bar"></span><span
										class="icon-bar"></span></a> <a href="#" class="brand">SOA</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="row-fluid">
							<div class="span4"></div>
							<div class="span4">
								<form class="form-inline form-horizontal">
									<fieldset>
										<legend>用户登录</legend>
										
										<div class="control-group">
											<label class="control-label" for="loginId" style="width:30px">账号</label>
											<div class="controls" style="margin-left:50px">
												<input id="loginId" type="text" name="loginId" />
												<span class="help-inline"></span>
											</div>
										</div>
										
										<div class="control-group">
											<label class="control-label" for="password" style="width:30px">密码</label>
											<div class="controls" style="margin-left:50px">
												<input id="password" type="password" name="password" /> 
												<span class="help-inline"></span> 
											</div>
										</div>
										
										<div class="control-group warning">
											<div class="controls" style="margin-left:50px">
												<a class="btn btn-success" id="login" >登录</a>
												
											</div>
										</div>
									</fieldset>
								</form>
							</div>
							<div class="span4" id="table">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script  src="${staticCtx}/commons/js/validata/login_validata.js"></script>
	<script  src="${appJs}/sys/user/login.js"></script>
	<script type="text/javascript">
	Validata.bind(document.forms[0]);
/* 	$("#table").html(new Table({
		columns:[
		    {
		    	title : '编号',
		    	column : 'id',
		    },{
		    	title : '姓名',
		    	column : 'name',
		    }
		   ],
		 data:[{
			 			id:'123',
			 			name:'张三'
		 				},{
		 					id:'456',
				 			name:'李四'
		 				}]
	}  
			
	).toString()); */
	//new Table();
	</script>
</body>
</html>
