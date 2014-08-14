<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>list</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
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
								 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">网站名</a>
								<div class="nav-collapse collapse navbar-responsive-collapse">
									<ul class="nav">
										<li class="active">
											<a href="#">主页</a>
										</li>
										<li>
											<a href="#">链接</a>
										</li>
										<li>
											<a href="#">链接</a>
										</li>
										<li class="dropdown">
											 <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
											<ul class="dropdown-menu">
												<li>
													<a href="#">下拉导航1</a>
												</li>
												<li>
													<a href="#">下拉导航2</a>
												</li>
												<li>
													<a href="#">其他</a>
												</li>
												<li class="divider">
												</li>
												<li class="nav-header">
													标签
												</li>
												<li>
													<a href="#">链接1</a>
												</li>
												<li>
													<a href="#">链接2</a>
												</li>
											</ul>
										</li>
									</ul>
									<ul class="nav pull-right">
										<li>
											<a href="#">右边链接</a>
										</li>
										<li class="divider-vertical">
										</li>
										<li class="dropdown">
											 <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
											<ul class="dropdown-menu">
												<li>
													<a href="#">下拉导航1</a>
												</li>
												<li>
													<a href="#">下拉导航2</a>
												</li>
												<li>
													<a href="#">其他</a>
												</li>
												<li class="divider">
												</li>
												<li>
													<a href="#">链接3</a>
												</li>
											</ul>
										</li>
									</ul>
								</div>
								
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span2">
					<div class="accordion" id="accordion-836085">
						<div class="accordion-group">
							<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-836085" href="#accordion-element-653046">调度管理</a>
								 <!-- <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-836085" href="#accordion-element-653046">选项卡 #1</a> -->
							</div>
							<div id="accordion-element-653046" class="accordion-body in collapse">
						         <ul class="nav nav-list ">
						              <li class="active">
							              <a href="#">首页</a>
						             </li>
						             <li>
							               <a href="#">库</a>
						             </li>
					             </ul>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-836085" href="#accordion-element-336363">系统设置</a>
							</div>
							<div id="accordion-element-336363" class="accordion-body collapse">
								       <ul class="nav nav-list ">
						              <li class="active">
							              <a href="#">用户管理</a>
						             </li>
						             <li>
							               <a href="#">角色管理</a>
						             </li>
					             </ul>
							</div>
						</div>
					</div>
				</div>
				
				<div class="span10">
				
					<div class="form-inline">
						<fieldset>
							 <legend>表单项</legend> 
							 <label>表签名<input type="text" /> </label>
							 <label>表签名<input type="text" /> </label>
							 <label>表签名<input type="text" /> </label>
							 <br>
							 <label>表签名<input type="text" /> </label>
							 <label>表签名<input type="text" /> </label>
							 <button type="submit" class="btn">提交</button>
						</fieldset>
					</div>
					<div>
						<label><button class="btn btn-success">新增</button></label>
					
					</div>
					<table class="table table-hover table-bordered table-condensed">
						<thead>
							<tr>
								<th>
									编号
								</th>
								<th>
									产品
								</th>
								<th>
									交付时间
								</th>
								<th>
									状态
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									1
								</td>
								<td>
									TB - Monthly
								</td>
								<td>
									01/04/2012
								</td>
								<td>
									Default
								</td>
							</tr>
							<tr class="success">
								<td>
									1
								</td>
								<td>
									TB - Monthly
								</td>
								<td>
									01/04/2012
								</td>
								<td>
									Approved
								</td>
							</tr>
							<tr class="error">
								<td>
									2
								</td>
								<td>
									TB - Monthly
								</td>
								<td>
									02/04/2012
								</td>
								<td>
									Declined
								</td>
							</tr>
							<tr class="warning">
								<td>
									3
								</td>
								<td>
									TB - Monthly
								</td>
								<td>
									03/04/2012
								</td>
								<td>
									Pending
								</td>
							</tr>
							<tr class="info">
								<td>
									4
								</td>
								<td>
									TB - Monthly
								</td>
								<td>
									04/04/2012
								</td>
								<td>
									Call in to confirm
								</td>
							</tr>
						</tbody>
					</table>
					<div class="pagination pagination-centered pagination-small">
						<ul>
							<li>
								<a href="#">上一页</a>
							</li>
							<li>
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#">4</a>
							</li>
							<li>
								<a href="#">5</a>
							</li>
							<li>
								<a href="#">下一页</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


</body>
</html>
