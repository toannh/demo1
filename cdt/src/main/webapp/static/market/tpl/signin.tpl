<div class="form-user">
	<form method="POST" action="<%= baseUrl %>/user/signin.html?ref=<%= baseUrl %><%= uri %>" >
		<div class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label">Tài khoản:</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" value="" id="username" name="username" placeholder="Sử dụng Tên đăng nhập hoặc Email để đăng nhập">
					<!--<div class="help-block">Tài khoản bắt buộc phải nhập.</div>-->
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Mật khẩu:</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" id="password" name="password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8 col-sm-offset-2">
					<div class="checkbox">
						<label><input type="checkbox" name="checkCookie"> Tự động đăng nhập</label>
						&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%= baseUrl %>/user/forgot.html"><b>Quên mật khẩu?</b></a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8 col-sm-offset-2">
					<input name="" type="submit" class="btn btn-danger" value="Đăng nhập">
				</div>
			</div>
		</div><!-- /form-horizontal -->
	</form>
	<div class="login-other">
		Bạn có thể đăng nhập với:
		<a href="<%= baseUrl %>/user/sso.html?provider=facebook"><span class="icon-facebooklogin"></span></a>
		<a href="<%= baseUrl %>/user/sso.html?provider=yahoo"><span class="icon-yahoologin"></span></a>
		<a href="<%= baseUrl %>/user/sso.html?provider=googleplus"><span class="icon-googlelogin"></span></a>
	</div>
</div><!-- /form-user -->
<div class="clearfix"></div>