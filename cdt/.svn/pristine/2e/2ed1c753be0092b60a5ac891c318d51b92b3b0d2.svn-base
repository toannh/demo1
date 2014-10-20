<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách giao dịch xèng
        </a>
    </li>
</ul>
<div class="func-container">  
    <form:form modelAttribute="cpCashTransactionSearch" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="id" type="text" class="form-control" placeholder="Mã giao dịch"/>
            </div>
            <div class="form-group">
                <form:input path="userId" type="text" class="form-control" placeholder="Mã người dùng"/>
            </div>
            <div class="form-group">
                <form:input path="startTime" type="hidden" class="form-control timeselect" placeholder="Từ ngày"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="email" type="text" class="form-control" placeholder="Nhập Email"/>
            </div>
            <div class="form-group">
                <form:select path="type" type="text" class="form-control">
                    <form:option value="" label="Chọn kiểu giao dịch" />
                    <form:option value="TOPUP_NL" label="Nạp ngân lượng" />
                    <form:option value="TOPUP_SMS" label="Uptin SMS" />
                    <form:option value="SMS_NAP" label="Nạp bằng SMS" />
                    <form:option value="SPENT_UPITEM" label="Mua UpTin" />
                    <form:option value="SPENT_VIPITEM" label="Mua Tin Vip" />
                    <form:option value="SPENT_EMAIL" label="Mua gửi Email" />
                    <form:option value="SPENT_SMS" label="Mua gửi SMS" />
                    <form:option value="ACTIVE_QUICK_SUBMIT" label="Kích hoạt đăng nhanh" />
                    <form:option value="CLOSE_ADV" label="Tắt quảng cáo" />
                    <form:option value="ACTIVE_MARKETING" label="Kích hoạt danh sách khách hàng" />
                    <form:option value="TOP_UP" label="Nạp thẻ điện thoại" />
                    <form:option value="COMMENT_MODEL_REWARD" label="Kiếm xèng từ đánh giá Model" />
                    <form:option value="COMMENT_ITEM_REWARD" label="Kiếm xèng từ đánh giá Item" />
                    <form:option value="SELLER_POST_NEWS" label="Kiếm xèng từ đăng bài tin" />
                    <form:option value="VIEW_PAGE" label="Kiếm xèng từ xem trang web" />
                    <form:option value="SIGNIN" label="Kiếm xèng từ đăng nhập" />
                    <form:option value="REGISTER" label="Kiếm xèng từ đăng ký" />
                    <form:option value="PAYMENT_SUSSESS_NL" label="Kiếm xèng từ thanh toán thành công qua NL" />
                    <form:option value="INTEGRATED_NL" label="Kiếm xèng từ tích hợp thành công NL" />
                    <form:option value="INTEGRATED_COD" label="Kiếm xèng từ tích hợp thành công ShipChung" />
                    <form:option value="SELLER_POST_ITEM" label="Kiếm xèng từ người bán đăng sản phẩm" />
                    <form:option value="OPEN_SHOP" label="Kiếm xèng từ mở shop" />
                    <form:option value="SELLER_CREATE_PROMOTION" label="Kiếm xèng từ tạo khuyến mãi" />
                    <form:option value="BROWSE_LADING" label="Kiếm xèng từ duyệt vận đơn" />
                    <form:option value="EMAIL_VERIFIED" label="Kiếm xèng từ xác minh email" />
                    <form:option value="PHONE_VERIFIED" label="Kiếm xèng từ xác minh điện thoại" />
                </form:select>
            </div>
            <div class="form-group">
                <form:input path="endTime" type="hidden" class="form-control timeselect" placeholder="Đến ngày"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:select path="status" type="text" class="form-control">
                    <form:option value="0" label="Trạng thái thanh toán" />
                    <form:option value="1" label="Đã thanh toán" />
                    <form:option value="2" label="Chưa thanh toán" />
                </form:select>
            </div>
            <div class="form-group">
                <form:select path="transactionStatus" type="text" class="form-control">
                    <form:option value="0" label="Trạng thái giao dịch xèng" />
                    <form:option value="1" label="Nạp xèng" />
                    <form:option value="2" label="Tiêu xèng" />
                    <form:option value="3" label="Kiếm xèng" />
                </form:select>
            </div>
            <div class="form-group">
                <form:input path="support" type="text" class="form-control" placeholder="Người chăm sóc"/>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3" style="margin-bottom:  10px">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc </button>
            <button type="button" class="btn btn-default" onclick="news.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
            <button type="button" class="btn btn-success" onclick="cash.exPortExcel();"><span class="glyphicon glyphicon-list"></span> Xuất excel</button>
            <button type="button" class="btn btn-success" onclick="cash.exPortExcelBySeller();"><span class="glyphicon glyphicon-list"></span> Xuất excel theo NB</button>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div>
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${cashSearchs.dataCount} </strong> kết quả <strong>${cashSearchs.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${cashSearchs.pageIndex}"/>
                <jsp:param name="pageCount" value="${cashSearchs.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
        <h5>Tổng số: <strong class="clr-red"> ${text:numberFormat(sumCash)}</strong> xèng</h5>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center" style="vertical-align: middle" >Ngày</th>
            <th class="text-center" style="vertical-align: middle">Giao dịch</th>
            <th class="text-center" style="vertical-align: middle">Hoạt động</th>       
            <th class="text-center" style="vertical-align: middle">Thanh toán</th>
            <th class="text-center" style="vertical-align: middle">Mô tả</th>
            <th class="text-center" style="vertical-align: middle">Số xèng</th>
            <th class="text-center" style="vertical-align: middle">Sau giao dịch</th>
            <th class="text-center" style="vertical-align: middle">Tình trạng</th>    
            <th class="text-center" style="vertical-align: middle">NV chăm sóc</th>    
        </tr>
        <jsp:useBean id="date" class="java.util.Date" />
        <c:forEach items="${cashSearchs.data}" var="cashtransaction">
            <c:set var="flag" value="true"></c:set>
            <c:forEach items="${users}" var="user">
                <c:if test="${cashtransaction.userId == user.id && flag}">
                    <c:set var="flag" value="false"></c:set>
                    <tr class="${cashtransaction.nlStatus != 2?'success':'danger'} cashColor${cashtransaction.id}" >
                        <td class="text-center" style="vertical-align: middle" >
                            <jsp:setProperty name="date" property="time" value="${cashtransaction.time}" /> 
                            <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>

                                <p><a href="javascript:;" <c:if test="${(cashtransaction.nlTransactionId==null || cashtransaction.nlTransactionId=='') &&cashtransaction.support!=null}">  title="Cập nhật bằng tay thanh toán qua ngân lượng" onclick="cash.updateNlTransactionId(${cashtransaction.id})" </c:if>>${cashtransaction.id}</a></p>


                            </td>
                            <td class="text-center" style="vertical-align: middle" >${user.username}</td>
                        <td class="text-center" style="vertical-align: middle" >
                            <c:choose>
                                <c:when test="${cashtransaction.type=='TOPUP_NL' || cashtransaction.type=='SMS_NAP'}">
                                    <c:if test="${cashtransaction.type=='TOPUP_NL'}">
                                        Nạp xèng NL
                                    </c:if>
                                    <c:if test="${cashtransaction.type=='SMS_NAP'}">
                                        Nạp xèng SMS
                                    </c:if>
                                </c:when>
                                <c:when test="${cashtransaction.type=='SPENT_UPITEM' || cashtransaction.type=='SPENT_VIPITEM'|| cashtransaction.type=='SPENT_EMAIL'||cashtransaction.type=='SPENT_SMS' || cashtransaction.type=='ACTIVE_MARKETING' || cashtransaction.type =='ACTIVE_QUICK_SUBMIT' || tcashtransactionrans.type == 'CLOSE_ADV' || cashtransaction.type == 'PANALTY_BLANCE' || cashtransaction.type == 'TOP_UP'}">
                                    Sử dụng xèng
                                </c:when>
                                <c:otherwise>Kiếm xèng</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center" style="vertical-align: middle">
                            <span class="cashType${cashtransaction.id}">
                                <c:choose>
                                    <c:when test="${cashtransaction.type=='TOPUP_NL'}">Nạp ngân lượng
                                        <c:if test="${cashtransaction.nlTransactionId!=null && cashtransaction.nlTransactionId!=''}">
                                            <p><a href="#">${cashtransaction.nlTransactionId}</a>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${cashtransaction.type=='SMS_NAP'}" >Nap bằng SMS</c:when>
                                        <c:when test="${cashtransaction.type=='TOPUP_SMS'}" >UpTin SMS</c:when>
                                        <c:when test="${cashtransaction.type=='SPENT_UPITEM'}" >Mua lượt up tin</c:when>
                                        <c:when test="${cashtransaction.type=='SPENT_VIPITEM'}" >Mua tin vip</c:when>
                                        <c:when test="${cashtransaction.type=='SPENT_EMAIL'}" >Mua gửi Email</c:when>
                                        <c:when test="${cashtransaction.type=='SPENT_SMS'}" >Mua gửi SMS</c:when>
                                        <c:when test="${cashtransaction.type=='COMMENT_ITEM_REWARD'}" >Comment đánh giá sản phẩm</c:when>
                                        <c:when test="${cashtransaction.type=='COMMENT_MODEL_REWARD'}" >Comment đánh giá model</c:when>
                                        <c:when test="${cashtransaction.type=='ACTIVE_QUICK_SUBMIT'}" >Kích hoạt chức năng đăng nhanh</c:when>
                                        <c:when test="${cashtransaction.type=='ACTIVE_MARKETING'}" >Mở chức năng danh sách người bán</c:when>
                                        <c:when test="${cashtransaction.type=='CLOSE_ADV'}" >Tắt box quảng cáo trang chi tiết sản phẩm</c:when>
                                        <c:when test="${cashtransaction.type=='PAYMENT_SUSSESS_NL'}" >Thanh toán thành công qua Ngân Lượng</c:when>
                                        <c:when test="${cashtransaction.type=='SELLER_CREATE_PROMOTION'}" >Tạo khuyến mại</c:when>
                                        <c:when test="${cashtransaction.type=='BROWSE_LADING'}" >Duyệt vận đơn</c:when>
                                        <c:when test="${cashtransaction.type=='EMAIL_VERIFIED'}" >Kích hoạt email</c:when>
                                        <c:when test="${cashtransaction.type=='PHONE_VERIFIED'}" >Kích hoạt phone</c:when>
                                        <c:when test="${cashtransaction.type=='PANALTY_BLANCE'}" >Phạt xèng</c:when>
                                        <c:when test="${cashtransaction.type=='INTEGRATED_NL'}" >Liên kết Ngân Lượng</c:when>
                                        <c:when test="${cashtransaction.type=='REGISTER'}" >Đăng ký</c:when>
                                        <c:when test="${cashtransaction.type=='SIGNIN'}" >Đăng nhập</c:when>
                                        <c:when test="${cashtransaction.type=='SELLER_POST_ITEM'}" >Đăng bán</c:when>
                                        <c:when test="${cashtransaction.type=='SELLER_POST_NEWS'}" >Viết bài tin tức</c:when>
                                        <c:when test="${cashtransaction.type=='OPEN_SHOP'}" >Mở shop</c:when>
                                        <c:when test="${cashtransaction.type=='INTEGRATED_COD'}" >Liên kết Shipchung</c:when>
                                        <c:when test="${cashtransaction.type=='PANALTY_BLANCE'}" >Bị phạt xèng</c:when>
                                        <c:when test="${cashtransaction.type=='TOP_UP'}" >Nạp thẻ điện thoại</c:when>
                                        <c:otherwise>${cashtransaction.type}</c:otherwise>
                                    </c:choose>
                            </span>
                        </td>
                        <td class="text-center" style="vertical-align: middle" >
                            <c:choose>
                                <c:when test="${cashtransaction.type=='TOPUP_NL'||cashtransaction.type=='TOPUP_SMS'}">
                                    Gói xèng: ${text:numberFormat(cashtransaction.amount)} xèng
                                    <c:if test="${cashtransaction.discount > 0}">
                                        . Được khuyến mại ${text:numberFormat(cashtransaction.discount)} <span>đ</span>
                                    </c:if>
                                </c:when>
                                <c:when test="${cashtransaction.type=='SPENT_UPITEM'}">
                                    Mua <strong>${text:numberFormat(cashtransaction.spentQuantity)}</strong> lượt uptin 
                                </c:when>
                                <c:when test="${cashtransaction.type=='SPENT_VIPITEM'}">
                                    Mua <strong>1</strong> tin VIP trong ${text:numberFormat(cashtransaction.spentQuantity)} ngày
                                </c:when>
                                <c:when test="${cashtransaction.type=='SPENT_EMAIL'}">
                                    Mua <strong>${text:numberFormat(cashtransaction.spentQuantity)}</strong> lượt gửi email
                                </c:when>
                                <c:when test="${cashtransaction.type=='SPENT_SMS'}">
                                    Mua <strong>${text:numberFormat(cashtransaction.spentQuantity)}</strong> lượt gửi tin nhắn
                                </c:when>
                                <c:when test="${cashtransaction.type=='ACTIVE_MARKETING'}">
                                    Mở chức năng danh sách người bán
                                </c:when>
                                <c:when test="${cashtransaction.type=='ACTIVE_QUICK_SUBMIT'}">
                                    Mở chức năng đăng nhanh
                                </c:when>
                                <c:when test="${cashtransaction.type=='CLOSE_ADV'}">
                                    Tắt quảng cáo trang chi tiết sản phẩm
                                </c:when>
                                <c:when test="${cashtransaction.type=='SELLER_POST_NEWS'}">
                                    Đăng tin tức lên shop
                                </c:when>
                                <c:when test="${cashtransaction.type=='VIEW_PAGE'}">
                                    Xem trang 30s
                                </c:when>
                                <c:when test="${cashtransaction.type=='SIGNIN'}">
                                    Đăng nhập lần đầu trong ngày
                                </c:when>
                                <c:when test="${cashtransaction.type=='REGISTER'}">
                                    Đăng kí
                                </c:when>
                                <c:when test="${cashtransaction.type=='OPEN_SHOP'}" >Mở shop</c:when>
                                <c:when test="${cashtransaction.type=='INTEGRATED_COD'}" >Liên kết Shipchung</c:when>
                                <c:when test="${cashtransaction.type=='PANALTY_BLANCE'}" >Bị phạt xèng</c:when>
                                <c:when test="${cashtransaction.type=='TOP_UP'}" >Nạp thẻ điện thoại</c:when>
                                <c:otherwise>Sử dụng trên hệ thống chợ điện tử</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center" style="vertical-align: middle" >
                            <c:choose>
                                <c:when test="${cashtransaction.type=='TOPUP_NL' || cashtransaction.type=='TOPUP_SMS'}">
                                    <span class="text-success">
                                    </c:when>
                                    <c:when test="${cashtransaction.type=='SPENT_UPITEM' || cashtransaction.type=='SPENT_VIPITEM' || cashtransaction.type=='SPENT_EMAIL' || cashtransaction.type=='SPENT_SMS'}">
                                        <span class="clr-red">
                                        </c:when>
                                        <c:otherwise><span class="clr-red"></c:otherwise>
                                        </c:choose>
                                        <strong>${text:numberFormat(cashtransaction.amount*cashtransaction.spentQuantity)}</strong>
                                    </span>
                                    </td>
                                    <td class="text-center" style="vertical-align: middle" >
                                        <span class="newBalance${cashtransaction.id}">
                                            ${text:numberFormat(cashtransaction.newBalance)}
                                        </span>
                                    </td>
                                    <td class="text-center" style="vertical-align: middle">
                                        <span class="payment${cashtransaction.id}">
                                            <c:if test="${cashtransaction.nlStatus != 2 }">Đã thanh toán</c:if>
                                            <c:if test="${cashtransaction.nlStatus == 2}">Chưa thanh toán</c:if>
                                        </span>
                                        </td>  
                                        <td class="text-center" style="vertical-align: middle" >
                                            <div class="cashtransaction${cashtransaction.id}">
                                            <c:if test="${(cashtransaction.support==null || cashtransaction.support=='') && cashtransaction.nlStatus == 2 }">
                                                <button type="button" class="btn btn-info" onclick="cash.addSupport(${cashtransaction.id})">
                                                    <span class="glyphicon glyphicon-user"></span> Nhận chăm sóc
                                                </button>
                                            </c:if>
                                            <c:if test="${cashtransaction.support!=null && cashtransaction.support==viewer.administrator.email}">
                                                <button type="button" class="btn btn-default" onclick="cash.addNote(${cashtransaction.id})">
                                                    <span class="glyphicon glyphicon-new-window"></span> Ghi chú
                                                </button>
                                                <c:if test="${cashtransaction.note!= null && cashtransaction.note!= ''}">
                                                    <span class="badge badge-success cdt-tooltip" data-toggle="tooltip" data-placement="top" title="${cashtransaction.note}">!</span>
                                                </c:if>

                                            </c:if>
                                            <c:if test="${cashtransaction.support!=null && cashtransaction.support!=viewer.administrator.email}">
                                                ${cashtransaction.support}<c:if test="${cashtransaction.note!= null && cashtransaction.note!= ''}">
                                                    <span class="badge badge-success cdt-tooltip" data-toggle="tooltip" data-placement="top" title="${cashtransaction.note}">!</span>
                                                </c:if>
                                            </c:if>
                                        </div>
                                    </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>

                        </table>
                        <div style="margin-top: 10px">
                            <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
                                <jsp:include page="/view/cp/widget/paging.jsp">
                                    <jsp:param name="pageIndex" value="${cashSearchs.pageIndex}"/>
                                    <jsp:param name="pageCount" value="${cashSearchs.pageCount}"/>
                                </jsp:include>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        </div>