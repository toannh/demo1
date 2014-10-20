<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Danh sách khách hàng</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-quan-tri-danh-sach-khach-hang-917926264718.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn quản trị danh sách khách hàng
        </a></div>      
    <h1 class="title-pages" ${!isMarketing?"style='border-bottom: none'":""} >Danh sách khách hàng</h1>
    <div class="tabs-content-user">
        <c:if test="${isMarketing}">
            <div class="mgt-25 box-search-customers">

                <div class="form-horizontal clearfix">
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
                        <div class="form-group"><input type="text" name="name" value="${(name != '')? name:''}" class="form-control" placeholder="Tìm tên khách hàng"></div>
                    </div>
                    <div class="col-lg-4 col-md-3 col-sm-3 col-xs-6">
                        <div class="form-group"><input type="text" name="phone" value="${(phone != '')? phone:''}" class="form-control" placeholder="Tìm theo số điện thoại"></div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                        <div class="form-group"><input type="text" name="emails" value="${(email != '')? email:''}" class="form-control" placeholder="Tìm email khách hàng"></div>
                    </div>
                    <div class="col-lg-1 col-md-2 col-sm-2 col-xs-6">
                        <div class="form-group"><button type="button" class="btn btn-default" onclick="sellercustomer.search()">Tìm kiếm</button></div>
                    </div>
                </div>

                <div class="alpha-search">
                    <input type="hidden" name="cname" />
                    <ul class="clearfix">
                        <li><a href="${basicUrl}/user/custormers.html">#</a></li>
                            <c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
                            <c:forTokens var="letter" items="${alphabet}" delims="," varStatus="stt">
                            <li><a href="javascript:;" ${letter==customerSearch.cname?'class="active"':''} for="${letter}">${letter}</a></li>
                            </c:forTokens>


                    </ul>
                </div>
                <div class="clearfix">Hiện có: <strong>${sellerCustomer.dataCount}</strong> khách hàng</div>
                <p class="mgt-10">Bạn có thể thêm khách hàng để gửi SMS Marketing.<br>
                    Email Marketing chỉ gửi được cho những khách hàng đã Verify trên Chợ Điện Tử</p>
            </div>   
        </c:if>


        <div class="table-list-product table-responsive mgt-10">
            <c:if test="${isMarketing}">
                <table class="table" width="100%">
                    <tr>
                        <th><div class="text-center"><input type="checkbox" name="checkall"></div></th>
                    <th colspan="3">
                    <div class="form-inline pull-left">
                        <div class="form-group">
                            <button type="button" class="btn btn-primary" onclick="sellercustomer.add()"><span class="glyphicon glyphicon-plus"></span> Thêm</button>
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-danger" onclick="sellercustomer.removeall()"><span class="glyphicon glyphicon-trash"></span> Xoá</button>
                        </div>   
                        <div class="form-group">
                            <button type="button" class="btn btn-success" onclick="sellercustomer.sendSMS()"><span class="icon-sms"></span> Gửi SMS</button>
                        </div>                                             
                        <div class="form-group">
                            <button type="button" class="btn btn-warning" onclick="sellercustomer.sendEmail()"><span class="fa fa-envelope-o"></span> Gửi E-Mail</button>
                        </div>                                                
                        <div class="form-group">
                            <a class="btn btn-primary" href="${baseUrl}/user/exceltemplate.html"><span class="fa fa-download"></span> Excel Template</a>
                        </div>                                                
                        <div class="form-group">
                            <form id="fileBean" method="post" enctype="multipart/form-data">
                                <input id="fileData" name="fileData" type="file" style="display:none" onchange="sellercustomer.uploadExcel();"/>
                                <button type="button" class="btn btn-success" id="uploadExcel"><span class="fa fa-upload"></span> Thêm bằng excel</button>
                            </form>
                        </div>                                                
                    </div>
                    <div class="pull-right order-block-form">
                        <label class="control-label">Sắp xếp theo: </label>
                        <div class="form-group">
                            <select class="form-control" name="option" onchange="sellercustomer.search();">
                                <option value="0" ${option == ''?'selected':''}>Chọn</option>
                                <option value="1" ${option == '1'?'selected':''}>Khách hàng mới</option>
                                <option value="4" ${option == '4'?'selected':''}>Khách hàng mới sửa gần đây</option>
                                <option value="2" ${option == '2'?'selected':''}>Danh sách email đủ điều kiện</option>
                                <option value="3" ${option == '3'?'selected':''}>Danh sách phone đủ điều kiện</option>
                            </select>
                        </div>
                    </div>
                    </th>
                    </tr>
                    <c:if test="${sellerCustomer.dataCount > 0}">
                        <tr class="warning">
                            <th width="5%" align="center" valign="top">&nbsp;</th>
                            <th width="40%" valign="top"><strong>Khách hàng</strong></th>
                            <th width="30%">
                                <strong>Order / Order thanh toán<br>Giá trị order / Giá trị order thanh toán</strong>
                            </th>
                            <th width="25%"><div  class="text-center"><strong>Thao tác</strong></div></th>

                        </tr>
                    </c:if>
                    <c:if test="${sellerCustomer.dataCount > 0}">
                        <c:forEach items="${sellerCustomer.data}" var="sellerCustomer">
                            <tr>
                                <td valign="top" align="center"><div class="text-center"><input type="checkbox" for="checkall" value="${sellerCustomer.id}"></div></td>
                                <td>
                                    <div class="table-content-inner">
                                        <p><strong>${sellerCustomer.name}</strong> </p>
                                        <p>
                                            <span class="text-${sellerCustomer.phoneVerified?'success':'danger'}" ><strong style="color: black">Tel:</strong> ${sellerCustomer.phone}</span>
                                            |   
                                            <span class="text-${sellerCustomer.emailVerified?'success':'danger'}" ><strong style="color: black">Email:</strong> ${sellerCustomer.email}</span>
                                        </p>
                                        <p>${sellerCustomer.address}</p>
                                    </div>
                                </td>
                                <td>
                                    <div class="table-content-inner">
                                        <p>${sellerCustomer.order} / ${sellerCustomer.orderPayment}</p>
                                        <p>${text:numberFormat(sellerCustomer.orderPrice)} / ${text:numberFormat(sellerCustomer.orderPricePayment)}</p>
                                        <p><a href="${baseUrl}/user/hoa-don-ban-hang.html?buyerId=${sellerCustomer.userId}" target="_blank">Xem lịch sử giao dịch</a></p>
                                    </div>                            
                                </td>
                                <td valign="top">
                                    <div class="btn-control-block mgt-15">
                                        <a href="javascript:;" class="" title="Sửa" onclick="sellercustomer.edit('${sellerCustomer.id}')"><span class="fa fa-edit"></span></a>
                                        <a href="javascript:;" class="" title="Xoá" onclick="sellercustomer.del('${sellerCustomer.id}')"><span class="fa fa-trash-o"></span></a>
                                        <a href="javascript:;" class="" title="Gửi Email" onclick="sellercustomer.sendEmail('${sellerCustomer.id}')"><span class="fa  fa-envelope-o"></span></a>
                                        <a href="javascript:;" class="" title="Gửi SMS" onclick="sellercustomer.sendSMS('${sellerCustomer.id}')"><span class="fa fa-mobile"></span></a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
                <c:if test="${sellerCustomer.dataCount <= 0}">
                    <div class="cdt-message bg-danger text-center">Không tìm thấy khách hàng nào.</div>
                </c:if>
            </c:if>
            <c:if test="${!isMarketing}">
                <table class="table" width="100%">
                    <tr>
                        <td colspan="4">
                            <div class="table-content-inner alert-not-active">
                                <p>Để sử dụng được chức năng <b>Email marketing</b> và  <b>SMS marketing</b>, bạn phải kích hoạt chức năng <b>"Danh sách khách hàng"</b></p>
                                <p>Phí kích hoạt: <strong class="clr-red">300.000 xèng</strong> <button type="button" class="btn btn-danger" onclick="sellercustomer.activeNow()">Kích hoạt ngay</button></p>
                                <p>Tài khoản xèng hiện có: <strong class="clr-red">${text:numberFormat(numberCash)} xèng</strong>
                                    <c:if test="${numberCash<300000}">
                                        <a href="${basicUrl}/user/tai-khoan-xeng.html" target="_blank" class="btn btn-primary">Nạp thêm xèng</a>
                                    </c:if>
                                </p>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>

        </div>
        <c:if test="${isMarketing}">
            <hr>
            <div class="page-ouner clearfix">
                <ul class="pagination pull-right">
                    <c:if test="${sellerCustomer.pageIndex > 3}"><li><a href="${baseUrl}/user/custormers.html?page=1" href="javascript:;"><<</a></li></c:if>
                    <c:if test="${sellerCustomer.pageIndex > 2}"><li><a href="${baseUrl}/user/custormers.html?page=${sellerCustomer.pageIndex}" ><</a></li></c:if>
                    <c:if test="${sellerCustomer.pageIndex > 3}"><li><a>...</a></li></c:if>
                    <c:if test="${sellerCustomer.pageIndex >= 3}"><li><a href="${baseUrl}/user/custormers.html?page=${sellerCustomer.pageIndex - 2}">${sellerCustomer.pageIndex-2}</a></li></c:if>
                    <c:if test="${sellerCustomer.pageIndex >= 2}"><li><a href="${baseUrl}/user/custormers.html?page=${sellerCustomer.pageIndex - 1}" >${sellerCustomer.pageIndex-1}</a></li></c:if>
                    <c:if test="${sellerCustomer.pageIndex >= 1}"><li><a href="${baseUrl}/user/custormers.html?page=${sellerCustomer.pageIndex}" >${sellerCustomer.pageIndex}</a></li></c:if>
                    <li class="active" ><a class="btn btn-primary">${sellerCustomer.pageIndex + 1}</a>
                    <c:if test="${sellerCustomer.pageCount - sellerCustomer.pageIndex > 1}"><li><a href="${baseUrl}/user/custormers.html?page=${sellerCustomer.pageIndex+2}" >${sellerCustomer.pageIndex+2}</a></li></c:if>
                    <c:if test="${sellerCustomer.pageCount - sellerCustomer.pageIndex > 2}"><li><a href="${baseUrl}/user/custormers.html?page=${sellerCustomer.pageIndex+3}" >${sellerCustomer.pageIndex+3}</a></li></c:if>
                    <c:if test="${sellerCustomer.pageCount - sellerCustomer.pageIndex > 3}"><li><a >...</a></c:if>
                    <c:if test="${sellerCustomer.pageCount - sellerCustomer.pageIndex > 2}"><li><a href="${baseUrl}/user/custormers.html?page=${sellerCustomer.pageIndex+2}" >></a></li></c:if>
                    <c:if test="${sellerCustomer.pageCount - sellerCustomer.pageIndex > 2}"><li><a href="${baseUrl}/user/custormers.html?page=${sellerCustomer.pageCount}" >>></a></li></c:if>
                    </ul>
                </div> 
        </c:if>

    </div>
</div>