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
        <li class="active">Tài khoản xèng</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-nap-xeng-811634643984.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn nạp xèng
        </a></div>
    <h1 class="title-pages">Tài khoản xèng</h1>
    <div class="tabs-content-user">
        <div class="row row-reset mgt-25">
            <div class="col-lg-9 col-md-9 col-sm-8 col-xs-8">
                <p>Dùng để mua các dịch vụ Up tin, tin Vip và nhiều dịch vụ giá trị gia tăng khác</p>
                <p>Để có xèng, bạn có thể mua ngay !</p>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4">
                <div class="account-xeng-info">
                    <p>Bạn đang có</p>
                    <p><span class="clr-red text-big">${text:numberFormat(cash.balance)} </span>xèng</p>
                </div>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 pages-account-xeng ">
                <h4>Mua xèng bằng SMS. CỰC NHANH VÀ TIỆN!</h4>
                <div class="border-box-cod">
                    <p>Soạn tin nhắn SMS 
                        <span class="text-normal clr-red">CDT NAP ${viewer.user.id}</span> gửi 
                        <span class="text-normal clr-red">8755</span></p>
                    <p>1 tin nhắn 15.000 vnđ nạp được 6.000 xèng</p>
                    <p><strong class="red">Lưu ý</strong>: mỗi số điện thoại chỉ nhắn được 10tin/ngày, mỗi lần cách nhau ít nhất 3 phút</p>
                </div>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 pages-account-xeng ">
                <h4>Mua xèng và thanh toán qua NgânLượng. RẺ HƠN!</h4>
                <div class="border-box-cod">
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-lg-3 col-md-3 col-xs-3 control-label">Gói xèng:</label>
                                <div class="col-lg-6 col-md-6 col-xs-6">
                                    <select class="form-control" name="amount" onchange="cash.changeAmount();">
                                        <option value="20000">20.000</option>
                                        <option value="50000">50.000</option>
                                        <option value="100000">100.000</option>
                                        <option value="200000">200.000</option>
                                        <option value="300000">300.000</option>
                                        <option value="400000">400.000</option>
                                        <option value="500000">500.000</option>          
                                        <option value="1000000">1.000.000</option>          
                                        <option value="2000000">2.000.000</option>          
                                        <option value="3000000">3.000.000</option>          
                                        <option value="4000000">4.000.000</option>          
                                        <option value="5000000">5.000.000</option>          
                                        <option value="10000000">10.000.000</option>          
                                    </select>
                                </div>
                                <div class="col-lg-3 col-md-3 col-xs-3 reset-padding"><label class="control-label">Xèng</label></div>
                            </div>                                  
                            <div class="form-group">
                                <label class="col-lg-3 col-md-3 col-xs-3 control-label">Số lượng:</label>
                                <div class="col-lg-6 col-md-6 col-xs-6">
                                    <input type="text" value="1" name="spentQuantity" onchange="cash.changeAmount();" class="form-control" />                               
                                </div>
                                <div class="col-lg-3 col-md-3 col-xs-3 reset-padding"><label class="control-label">Gói</label></div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 col-md-3 col-xs-3 control-label">Hình thức thanh toán:</label>
                                <div class="col-lg-8 col-md-8 col-xs-9">
                                    <label class="radio-inline">
                                        <input onchange="cash.changeAmount();" name="paymentMethod" type="radio" value="nl" checked=""> Ví NgânLượng
                                    </label>
                                    <label class="radio-inline">
                                        <input onchange="cash.changeAmount();" name="paymentMethod" type="radio" value="visa"> Visa
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 col-md-3 col-xs-3 control-label">Thành tiền:</label>
                                <label class="col-lg-8 col-md-8 col-xs-9 control-label">
                                    <span class="clr-red text-normal totalAmount">20.000 <sup class="u-price">đ</sup></span>
                                </label>                                    
                            </div> 
                            <div class="form-group">
                                <label class="col-lg-3 col-md-3 col-xs-3 control-label">Thanh toán:</label>
                                <label class="col-lg-8 col-md-8 col-xs-9 control-label">
                                    <span class="clr-red text-normal paymentAmount">20.000 <sup class="u-price">đ</sup></span>
                                    <i style="display: none" class="discountAmount" >(<span>0 <sup class="price">đ</sup></span>)</i>
                                </label>                                    
                            </div>                                                 
                        </div>                                                               	
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <button type="button" class="btn btn-lg btn-danger" onclick="cash.paymentNL();">Thanh toán Ngân lượng</button>
                        <!--                        <button type="button" class="btn btn-lg btn-danger">Thanh toán thẻ cào</button>-->
                        <div class="cdt-message bg-warning">
                            <b>Khi thanh toán với các gói xèng từ:</b>
                            <br>
                            1.000.000 - 2.000.000 Giảm 5%
                            <br>
                            3.000.000 - 5.000.000 Giảm 10%
                            <br>
                            10.000.000 Giảm 15%
                            <br>
                            <br>
                            Giảm tiếp <b>20%</b> cho các khách hàng thanh toán bằng thẻ Visa qua NgânLượng khi mua Xèng.
                        </div>
                    </div>  
                    <div class="clearfix"></div>                              
                </div>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 pages-account-xeng ">
                <h4>Điện thoại hỗ trợ</h4>
                <div class="border-box-cod">
                    <p>Hà Nội: <span class="text-normal clr-red">Ms Thắm 0912.059.048</span> &nbsp; - &nbsp; 	TP. Hồ Chí Minh: <span class="text-normal clr-red">Ms Vương 0977.647.939</span></p>
                </div>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 pages-account-xeng ">
                <h4>Lịch sử mua và sử dụng xèng 
                    <span class="pull-right reset-font display-view">Số xèng hiện có: <strong>${text:numberFormat(cash.balance)} xèng</strong></span>
                </h4>
                <div class="row fillter-xeng">
                    <form:form modelAttribute="transactionSearch" method="POST" role="form">
                        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-lg-2 col-md-2 col-sm-2 col-xs-3 control-label reset-padding">Từ ngày:</label>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-9 reset-padding">
                                        <div class="date-picker-block">
                                            <form:hidden path="startTime" size="10" placeholder="Từ ngày" class="form-control timeselect" /> 
                                            <span class="glyphicon glyphicon-calendar"></span>     
                                        </div>
                                    </div>
                                    <label class="col-lg-2 col-md-2 col-sm-2 col-xs-3 control-label reset-padding">Tới:</label>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-9 reset-padding">
                                        <div class="date-picker-block">
                                            <form:hidden path="endTime" size="10" placeholder="Đến ngày" class="form-control timeselect" />
                                            <span class="glyphicon glyphicon-calendar"></span>     
                                        </div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-lg-2 col-md-2 col-sm-2 col-xs-3 control-label reset-padding">Hoạt động:</label>
                                    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-9 reset-padding">
                                        <form:select path="type" class="form-control" >
                                            <option label="Toàn bộ" />
                                            <form:option value="TOPUP_NL" label="Nạp xèng NL" />
                                            <form:option value="TOPUP_SMS" label="Uptin SMS" />
                                            <form:option value="SMS_NAP" label="Nạp xèng SMS" />
                                            <form:option value="SPENT_UPITEM" label="Mua uptin" />
                                            <form:option value="SPENT_VIPITEM" label="Mua VIP" />
                                            <form:option value="SPENT_EMAIL" label="Mua gửi email" />
                                            <form:option value="SPENT_SMS" label="Mua gửi SMS" />
                                            <form:option value="ACTIVE_MARKETING" label="Mở danh sách người bán" />
                                        </form:select>
                                    </div>
                                    <label class="col-lg-2 col-md-2 col-sm-2 col-xs-3 control-label reset-padding">Tình trạng:</label>
                                    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-9 reset-padding">
                                        <form:select path="status" class="form-control">
                                            <form:option value="0" label="Toàn bộ" />
                                            <form:option value="1" label="Thành công" />
                                            <form:option value="2" label="Chưa thành công" />
                                        </form:select>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 reset-padding-all">
                                        <button type="submit" class="btn btn-default btn-block">Xem</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>  
                <div class="list-table-content table-responsive">
                    <c:if test="${fn:length(transactions.data) <= 0 }">
                        <div class="cdt-message bg-danger text-center">Chưa có giao dịch nào!</div>
                    </c:if>
                    <c:if test="${fn:length(transactions.data) > 0 }">
                        <table class="table" width="100%">
                            <tr class="warning">
                                <th width="10%" align="center" valign="top"><div class="text-center">Mã giao dịch</div></th>
                            <th width="10%" align="center" valign="top"><div class="text-center">Ngày</div></th>
                            <th width="15%" valign="middle"><div class="text-left">Hoạt động</div></th>
                            <th width="22%"><div class="text-left">Mô tả</div></th>
                            <th width="10%" valign="middle"><div class="text-left">Số xèng</div></th>
                            <th width="10%" valign="middle"><div class="text-left">Thanh toán</div></th>
                            <th width="10%" valign="middle"><div class="text-left">Số tiền sau giao dịch</div></th>
                            <th width="15%" valign="middle"><div class="text-left">Tình trạng</div></th>
                            </tr>
                            <c:forEach items="${transactions.data}" var="trans">
                                <tr>
                                    <td>${trans.id}</td>
                                    <!-- Date -->
                                    <jsp:useBean id="date" class="java.util.Date" />
                                    <jsp:setProperty name="date" property="time" value="${trans.time}" />
                                    <td valign="top" align="center">
                                        <div class="text-left"><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${date}" />
                                            <br><fmt:formatDate type="date" pattern="H:mm" value="${date}" />
                                        </div>
                                    </td>
                                    <!-- Hoat dong -->
                                    <td>
                                        <div class="text-left">
                                            <c:choose>
                                                <c:when test="${trans.type=='TOPUP_NL' || trans.type=='SMS_NAP'}">
                                                    <c:if test="${trans.type=='TOPUP_NL'}">
                                                         Nạp xèng NL
                                                    </c:if>
                                                    <c:if test="${trans.type=='SMS_NAP'}">
                                                         Nạp xèng SMS
                                                    </c:if>
                                                </c:when>
                                                <c:when test="${trans.type=='SPENT_UPITEM' || trans.type=='SPENT_VIPITEM'|| trans.type=='SPENT_EMAIL'||trans.type=='SPENT_SMS' || trans.type=='ACTIVE_MARKETING' || trans.type =='ACTIVE_QUICK_SUBMIT' || trans.type == 'CLOSE_ADV' || trans.type == 'PANALTY_BLANCE' || trans.type == 'TOP_UP'}">Sử dụng xèng</c:when>
                                                <c:otherwise>Kiếm xèng</c:otherwise>
                                            </c:choose>
                                        </div>
                                    </td>
                                    <!-- Mo ta -->
                                    <td>
                                        <div class="text-left">
                                            <c:choose>
                                                <c:when test="${trans.type=='TOPUP_NL'||trans.type=='TOPUP_SMS'}">
                                                    Gói xèng: ${text:numberFormat(trans.amount)} xèng
                                                    <c:if test="${trans.discount > 0}">
                                                        . Được khuyến mại ${text:numberFormat(trans.discount)} <span>đ</span>
                                                    </c:if>
                                                </c:when>
                                                <c:when test="${trans.type=='SPENT_UPITEM'}">
                                                    Mua <strong>${text:numberFormat(trans.spentQuantity)}</strong> lượt uptin 
                                                </c:when>
                                                <c:when test="${trans.type=='SPENT_VIPITEM'}">
                                                    Mua <strong>1</strong> tin VIP trong ${text:numberFormat(trans.spentQuantity)} ngày
                                                </c:when>
                                                <c:when test="${trans.type=='SPENT_EMAIL'}">
                                                    Mua <strong>${text:numberFormat(trans.spentQuantity)}</strong> lượt gửi email
                                                </c:when>
                                                <c:when test="${trans.type=='SPENT_SMS'}">
                                                    Mua <strong>${text:numberFormat(trans.spentQuantity)}</strong> lượt gửi tin nhắn
                                                </c:when>
                                                <c:when test="${trans.type=='ACTIVE_MARKETING'}">
                                                    Mở chức năng danh sách người bán
                                                </c:when>
                                                <c:when test="${trans.type=='CLOSE_ADV'}">
                                                    Tắt box quảng cáo trang detail
                                                </c:when>
                                                <c:when test="${trans.type=='TOP_UP'}">
                                                    Nạp thẻ điện thoại
                                                </c:when>
                                                <c:when test="${trans.type=='PANALTY_BLANCE'}">
                                                    Bị phạt xèng
                                                </c:when>
                                                <c:when test="${trans.type=='SELLER_POST_NEWS'}">
                                                    Đăng tin tức shop
                                                </c:when>
                                                <c:when test="${trans.type=='SIGNIN'}">
                                                    Đăng nhập lần đầu trong ngày
                                                </c:when>
                                                <c:when test="${trans.type=='REGISTER'}">
                                                    Đăng kí
                                                </c:when>
                                                <c:when test="${trans.type=='PAYMENT_SUSSESS_NL'}">
                                                    Thanh toán thành công qua ngân lượng
                                                </c:when>
                                                <c:when test="${trans.type=='INTEGRATED_NL'}">
                                                    Tích hợp Ngân Lượng lần đầu
                                                </c:when>
                                                <c:when test="${trans.type=='INTEGRATED_COD'}">
                                                    Tích hợp ShipChung lần đầu
                                                </c:when>
                                                <c:when test="${trans.type=='SELLER_POST_ITEM'}">
                                                    Đăng bán sản phẩm
                                                </c:when>
                                                <c:when test="${trans.type=='OPEN_SHOP'}">
                                                    Mở shop
                                                </c:when>
                                                <c:when test="${trans.type=='SELLER_CREATE_PROMOTION'}">
                                                    Người bán tạo khuyến mại
                                                </c:when>
                                                <c:when test="${trans.type=='BROWSE_LADING'}">
                                                    Duyệt vận đơn
                                                </c:when>
                                                <c:when test="${trans.type=='EMAIL_VERIFIED'}">
                                                    Xác nhận email
                                                </c:when>
                                                <c:when test="${trans.type=='PHONE_VERIFIED'}">
                                                    Xác nhận số điện thoại
                                                </c:when>
                                                <c:when test="${trans.type=='COMMENT_MODEL_REWARD'}">
                                                    Comment đánh giá model
                                                </c:when>
                                                <c:when test="${trans.type=='COMMENT_ITEM_REWARD'}">
                                                    Comment đánh giá sản phẩm
                                                </c:when>
                                                <c:otherwise>Sử dụng trên hệ thống chợ điện tử</c:otherwise>
                                            </c:choose>
                                        </div>
                                    </td>
                                    <!-- So xeng -->
                                    <td>
                                        <div class="text-left">
                                            <c:choose>
                                                <c:when test="${trans.type=='TOPUP_NL' || trans.type=='TOPUP_SMS'}"><span class="text-success"></c:when>
                                                    <c:when test="${trans.type=='SPENT_UPITEM' || trans.type=='SPENT_VIPITEM' || trans.type=='SPENT_EMAIL' || trans.type=='SPENT_SMS'}"><span class="clr-red"></c:when>
                                                        <c:otherwise><span class="clr-red"></c:otherwise>
                                                        </c:choose>
                                                        <strong>${text:numberFormat(trans.amount*trans.spentQuantity)}</strong>
                                                    </span>
                                                    </div>
                                                    </td>
                                                    <!-- Thanh toan -->
                                                    <td>
                                                        <div class="text-left">
                                                            <c:choose>
                                                                <c:when test="${trans.type=='TOPUP_NL'}">Ngân lượng</c:when>
                                                                <c:when test="${trans.type=='TOPUP_SMS'}">SMS</c:when>
                                                                <c:when test="${trans.type=='SPENT_UPITEM' || trans.type=='SPENT_VIPITEM' || trans.type=='SPENT_EMAIL' || trans.type=='SPENT_SMS'}">Xèng</c:when>
                                                                <c:otherwise>Xèng</c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                    </td>
                                                    <!-- So tien con lai sao giao dich -->
                                                    <td>
                                                        <div class="text-left">${text:numberFormat(trans.newBalance)} xèng</div>
                                                    </td>
                                                    <!-- Thanh cong -->
                                                    <td>
                                                        <div class="text-left ${trans.nlStatus != 2?'text-success':'' } ">
                                                            <c:if test="${trans.nlStatus != 2}">Thành công</c:if>
                                                            </div>
                                                        <c:if test="${trans.nlStatus == 2}">
                                                            Chưa thành công
                                                            <a href="javascript:;" onclick="cash.paymentNL('${trans.id}')">thanh toán</a>
                                                        </c:if>
                                                    </td> 
                                                    </tr>
                                                </c:forEach>
                                                </table>
                                            </c:if>
                                            </div>
                                            <hr>
                                            <div class="page-ouner clearfix">
                                                <ul class="pagination pull-right">
                                                    <c:if test="${transactions.pageCount > 1}">
                                                        <!-- Generate first link -->
                                                        <c:if test="${transactions.pageIndex!=0}">
                                                            <li><a href="${baseUrl}/user/tai-khoan-xeng.html">«</a></li>
                                                            </c:if>
                                                        <!--/ End first link -->
                                                        <!-- Number of page to display -->
                                                        <c:set var="displayLink" value="3"></c:set>
                                                            <!-- Set begin link and end link -->
                                                        <c:if test="${transactions.pageIndex==0}">
                                                            <c:set value="1" var="beginLink"></c:set>
                                                            <c:set value="${transactions.pageCount}" var="endLink"></c:set>
                                                        </c:if>
                                                        <c:if test="${transactions.pageIndex!=0}">
                                                            <c:set value="${transactions.pageIndex}" var="beginLink"></c:set>
                                                            <c:set value="${transactions.pageIndex+2}" var="endLink"></c:set>
                                                        </c:if>
                                                        <c:if test="${(transactions.pageIndex+1)==transactions.pageCount}">
                                                            <c:if test="${transactions.pageIndex==1}">
                                                                <c:set value="${transactions.pageCount-displayLink+2}" var="beginLink"></c:set>
                                                            </c:if>
                                                            <c:if test="${transactions.pageIndex!=1}">
                                                                <c:set value="${transactions.pageCount-displayLink+1}" var="beginLink"></c:set>
                                                            </c:if>
                                                            <c:set value="${transactions.pageCount}" var="endLink"></c:set>
                                                        </c:if>
                                                        <!--/ End set begin and end link -->
                                                        <!-- Generate link to other page -->
                                                        <c:forEach begin="${beginLink}" end="${endLink}" step="1" var="p">
                                                            <li class="${(transactions.pageIndex+1)==p ?'active':''}"><a href="${baseUrl}/user/tai-khoan-xeng.html?page=${p}">${p}</a></li>
                                                            </c:forEach>
                                                        <!--/ End generate link -->
                                                        <!-- Generate last link -->
                                                        <c:if test="${(transactions.pageIndex+1)!=transactions.pageCount}">
                                                            <li><a href="${baseUrl}/user/tai-khoan-xeng.html?page=${transactions.pageCount}">»</a></li>
                                                            </c:if>
                                                        <!--/ End last link -->
                                                    </c:if>
                                                </ul>
                                            </div>

                                            </div>
                                            </div>
                                            </div>
                                            </div>