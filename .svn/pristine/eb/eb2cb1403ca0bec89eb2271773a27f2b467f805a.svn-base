<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  
            <a href="${baseUrl}"> Trang chủ</a>
        </li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username}</a></li>
        <li class="active">Tài khoản</li>
    </ol>
    <h1 class="title-pages">Thông báo &amp; tin nhắn</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li><a href="${baseUrl}/user/quan-ly-thu.html?tab=inbox">Hòm thư <span id="inboxCount"></span></a></li>
            <li><a href="${baseUrl}/user/quan-ly-thu.html?tab=sent">Thư đã gửi <span id="outboxCount"></span></a></li>
            <li class="active"><a href="${baseUrl}/user/soan-thu.html">Soạn thư</a></li>                   
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="messenger-detail">
                        <div class="row">
                            <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                                <form id="send-Message">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Tiêu đề:</label>
                                            <div class="col-sm-10">
                                                <input type="text" name="subject" class="form-control" placeholder="Nhập tiêu đề tin nhắn">                                      
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Người nhận:</label>
                                            <div class="col-sm-10 message-relative">
                                                <input type="text" name="toEmail" placeholder="Chọn 1 email" class="form-control" onkeypress="message.searchUserByEmail(this.value);">    
                                                <div class="compare-autosearch" style="display:block;">
                                                </div>                                     
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Nội dung:</label>
                                            <div class="col-sm-10">
                                                <textarea class="form-control" name="content" rows="5" placeholder="Nhập nội dung"></textarea>                                                </div>
                                        </div>                                    
                                        <div class="form-group mgt-25">
                                            <label class="col-sm-2 control-label">&nbsp;</label>
                                            <div class="col-sm-10">
                                                <a class="btn btn-lg btn-danger" onclick="message.send();">Gửi thư</a> <a href="#" class="btn-remove"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a> 
                                            </div>

                                        </div>                                                   
                                    </div>
                                </form>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 reset-padding" style="display: none">
                                <div class="border-product-relations">
                                    <h4>Nội dung thư liên quan tới sản phẩm: </h4>
                                    <div class="box-buy-product">
                                        <span class="img-buy-product pull-left"><img src="images/data/image2.jpg" class="img-responsive"></span>
                                        <div class="desc-product">
                                            <a href="#" title="#">H&amp;M Trend collection dress red black sold out everywhere!!</a>
                                            <p>Mã SP: 171045753288</p>
                                            <p>Người bán: store247</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>