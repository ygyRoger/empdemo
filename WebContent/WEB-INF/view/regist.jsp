<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<title>regist</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style2.css" />
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript">
			function preview(){
				var headimg = document.getElementById("file");
				var file = headimg.files[0];
				var r = new FileReader();
				r.readAsDataURL(file);
				r.onload=function(){
					document.getElementById("p").src = this.result;
				}
			}
			
			function qiehuan(){
				//切换验证码
				document.getElementById("num").src = "code.do?"+Math.random();
				//让按钮不可点击
				document.getElementById("qh").setAttribute("disabled","disabled");
				//定时器读秒
				var time = 59;
				var inter = setInterval(function(){
					document.getElementById("qh").innerHTML = time--;
					if(time === 0){
						document.getElementById("qh").removeAttribute("disabled");
						document.getElementById("qh").innerHTML = "换一张";
						clearInterval(inter);
					}
				},1000);
			}
			
			function validateusername(){
				var username = $("[name=username]").val();
				$.ajax({
					url:"validate.do?username="+username,
					type:"get",
					success:function(data){
						if (data.trim() == "ok") {
							$("#uuuu").html("√").css("color","green");
						}else{
							$("#uuuu").html("×").css("color","red");
						}
					}
				});
			}
			
		</script>
	</head>
	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								${date}
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								<a href="#">main</a>
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						注册
					</h1>
					<form action="regist.do" method="post" enctype="multipart/form-data">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username" onblur="validateusername();"/><span id="uuuu"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									真实姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="realname" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password" />
								</td>
							</tr><tr>
								<td valign="middle" align="right">
									确认密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password2"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									性别:
								</td>
								<td valign="middle" align="left">
									男
									<input type="radio" class="inputgri" name="sex" value="m" checked="checked"/>
									女
									<input type="radio" class="inputgri" name="sex" value="f"/>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" align="right">
									头像:
								</td>
								<td valign="middle" align="left">
									<input type="file" class="inputgri" name="headimg" onchange="preview();" id="file"/>
									<img alt="无预览" src="" id="p" width="100px" height="100px">
								</td>
							</tr>
							
							<tr>
								<td valign="middle" align="right">
									验证码:
									<img id="num" src="code.do"/>
									<button type="button" onclick="qiehuan();" id="qh">换一张</button>
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="number" onblur="validatecode();" id="number"/>
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="Submit &raquo;" disabled="disabled" id="sub"/>
						</p>
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
				ABC@126.com
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function validatecode(){
			//获得输入框里输入的id
			var srk = document.getElementById("number").value;
			//加ajax无刷新的请求
			//整体表单提交
			//js可以操作cookie
			var co = document.cookie;
			var cos = co.split(";");
			for(var i = 0; i < cos.length;i++){
				var tmp = cos[i];
				if (tmp.substring(0,tmp.indexOf("=")).trim() == "code") {
					var code = tmp.substring(tmp.indexOf("=")+1);
					if (code == srk) {
						document.getElementById("sub").removeAttribute("disabled");
					}
				}
			}
		}
	</script>
</html>
