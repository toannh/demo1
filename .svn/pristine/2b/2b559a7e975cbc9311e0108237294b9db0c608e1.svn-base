<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span><a href="${baseUrl}"> Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Tài khoản</li>
    </ol>
    <h1 class="title-pages">Thông tin cá nhân</h1>
    <div class="tabs-content-user">
        <div class="row config-shop-layout row-reset">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="list-table-content clearfix">    		                               	
                    <div class="box-step"> 
                        <div class="title-config-shop">Thông tin tài khoản</div>
                        <div class="config-shop-interface-item">
                            <div class="form-horizontal">
                                <div class="form-group" for="username">
                                    <input name="id" value="${user.id}" type="hidden" />
                                    <c:if test="${user.username!=null && user.username!=''}">
                                        <label class="col-sm-2 col-xs-3 control-label">Tên đăng nhập:</label>
                                        <div class="col-sm-7 col-xs-9">
                                            <label class="control-label">${user.username}</label>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="form-group" for="email">
                                    <label class="col-sm-2 col-xs-3 control-label">E-Mail:</label>
                                    <div class="col-sm-7 col-xs-9">
                                        <label class="control-label" name="email"><span class="val">${user.email}</span>
                                            <span class="icon-check-${user.emailVerified?"ok":"false"}"><span class="glyphicon glyphicon-ok"></span>  Email</span> &nbsp; 
                                        </label>
                                        <c:if test="${!user.emailVerified}">
                                            <div class="help-block"><a target="_blank" href="${baseUrl}/user/requestverify.html">Tài khoản của bạn chưa kích hoạt email, yêu cầu gửi lại mail kích hoạt!</a></div>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-xs-3 control-label">Mật khẩu:</label>
                                    <div class="col-sm-7 col-xs-9">
                                        <label class="control-label"><a onclick="user.changePass()" style="cursor: pointer">Đổi mật khẩu</a></label>
                                    </div>
                                </div>
                            </div>                                            
                            <div class="clearfix"></div>  
                        </div>
                    </div>
                    <div class="box-step"> 
                        <div class="title-config-shop">Thông tin cá nhân</div>
                        <div class="config-shop-interface-item">
                            <div class="form-horizontal">
                                <div class="form-group" for="name">
                                    <label class="col-sm-2 col-xs-3 control-label">Tên đầy đủ:</label>
                                    <div class="col-sm-7 col-xs-9">
                                        <label class="control-label" name="name">
                                            <input type="hidden" rel="change" value="${user.name}" />
                                            <span class="val">${user.name}</span> &nbsp; 
                                            <a href="javascript:void(0)"><span class="glyphicon glyphicon-pencil" onclick="user.quickEdit(this);"></span></a> 
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group" for="avatar">
                                    <label class="col-sm-2 col-xs-3 control-label">Ảnh đại diện:</label>
                                    <form method="post" enctype="multipart/form-data" id="avatarUpload">
                                        <input type="hidden" value="${user.id}" name="userId" />
                                        <div class="col-sm-7 col-xs-9">
                                            <label class="control-label">
                                                <c:if test="${user.avatar == null || user.avatar == ''}">
                                                    <img src="${baseUrl}/static/lib/no-avatar.png"  width="60" />
                                                </c:if>
                                                <c:if test="${user.avatar != null && user.avatar != ''}">
                                                    <img src="${user.avatar}" alt="${user.username}" width="60"/>
                                                </c:if>
                                            </label>
                                            <span class="btn btn-default" onclick="user.changeAvatar('${user.id}')">Chọn ảnh</span>
<!--                                            <input name="avatar" style="margin:5px 0px;" type="file" onchange="user.avatarUpload(this)" />-->
                                        </div>
                                    </form>
                                </div>
                                <div class="form-group" for="phone">
                                    <label class="col-sm-2 col-xs-3 control-label">ĐTDĐ:</label>
                                    <div class="col-sm-7 col-xs-9">
                                        <label class="control-label" name="phone">
                                            <input type="hidden" rel="change" value="${user.phone}" />
                                            <c:if test="${user.phone!=null && user.phone!=''}">
                                                <span class="val">
                                                    ${user.phone}
                                                </span>	&nbsp; 
                                                <span class="icon-check-${user.phoneVerified?"ok":"false"}"><span class="glyphicon glyphicon-remove"></span>  Phone</span> &nbsp; 
                                            </c:if>
                                            <c:if test="${user.phone==null}">
                                                <span class="val"></span> &nbsp; 
                                                <span class="icon-check-false"><span class="glyphicon glyphicon-remove"></span>  Phone</span> &nbsp; 
                                            </c:if>
                                            <a href="javascript:void(0)"><span class="glyphicon glyphicon-pencil" onclick="user.quickEdit(this);"></span></a> 
                                        </label>
                                        <c:if test="${!user.phoneVerified}"><span class="help-block">Số điện thoại này chưa xác minh, bạn cần xác minh bằng cách nhắn tin theo cú pháp <strong class="redfont"></br>CDT XM ${user.id}</strong> gửi <strong class="redfont">8255</strong> (Phí 2.000đ/tin nhắn)</span></c:if>
                                        </div>
                                    </div>
                                <jsp:useBean id="date" class="java.util.Date" />
                                <jsp:setProperty name="date" property="time" value="${user.dob}" />
                                <div class="form-group" for="dob">
                                    <label class="col-sm-2 col-xs-3 control-label">Ngày sinh:</label>
                                    <div class="col-sm-4 col-xs-9">
                                        <div class="date-picker-block">
                                            <input type="text" name="dob" class="form-control" <c:if test="${user.dob>0}">value="<fmt:formatDate value="${date}" type="date" pattern="dd/MM/yyyy"></fmt:formatDate>"</c:if>>
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </div>
                                            </div> 
                                        </div>       
                                        <div class="form-group">
                                            <label class="col-sm-2 col-xs-3 control-label">Giới tính:</label>
                                            <div class="col-sm-10 col-xs-9">
                                                <label class="radio-inline">
                                                    <input type="radio" name="gender" value="MALE" ${(user.gender=="MALE")?'checked':''}> Nam
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="gender" value="FEMALE" ${(user.gender=="FEMALE")?'checked':''}> Nữ
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group" for="address">
                                    <label class="col-sm-2 col-xs-3 control-label">Địa chỉ:</label>
                                    <div class="col-sm-4 col-xs-9">
                                        <input type="text" name="address" class="form-control" value="${user.address}">
                                    </div>
                                </div>
                                <div class="form-group" for="city">
                                    <label class="col-sm-2 col-xs-3 control-label">Thành phố:</label>
                                    <div class="col-sm-4 col-xs-9">
                                        <select class="form-control" name="cityId" onchange="user.findDistrict(this)">
                                            <option value="0">Chọn tỉnh, thành phố</option>
                                        </select>
                                    </div>                                                    
                                </div>
                                <div class="form-group" for="district">
                                    <label class="col-sm-2 col-xs-3 control-label">Quận huyện:</label>
                                    <div class="col-sm-4 col-xs-9">
                                        <select class="form-control" name="districtId">
                                            <option value="0">Chọn quận, huyện</option>
                                        </select>
                                    </div>                                                  
                                </div>
                                <div class="form-group" for="yahoo">
                                    <label class="col-sm-2 col-xs-3 control-label">Nick yahoo:</label>
                                    <div class="col-sm-4 col-xs-9">
                                        <input type="text" name="yahoo" class="form-control" value="${user.yahoo}">
                                    </div>
                                </div>
                                <div class="form-group" for="skype">
                                    <label class="col-sm-2 col-xs-3 control-label">Nick skype:</label>
                                    <div class="col-sm-4 col-xs-9">
                                        <input type="text" name="skype" class="form-control" value="${user.skype}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-xs-3 control-label">&nbsp;</label>
                                    <div class="col-sm-9 col-xs-9">
                                        <button class="btn btn-danger" id="btnUpdate" onclick="user.updateProfile()">Cập nhật</button> 
                                    </div>                                    
                                </div>
                            </div>
                            <div class="clearfix"></div>  
                        </div>
                    </div>
                </div>                                                     
            </div>                            
        </div>
    </div>
</div>