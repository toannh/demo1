<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://chodientu.vn/text" prefix="text" %>
<%@taglib uri="http://chodientu.vn/url" prefix="url" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-tags"></i>
            Danh sách sản phẩm cần duyệt
        </a>
    </li>
</ul>
<div class="func-container" >
    <form:form modelAttribute="itemSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 35px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="id" type="text" class="form-control" placeholder="Mã sản phẩm"/>
            </div>
            <div class="form-group">
                <form:input path="keyword" type="text" class="form-control" placeholder="Từ khóa"/>
            </div>
            <div class="form-group">                
                <form:select path="status" type="text" class="form-control">
                    <form:option value="0" label="-- Chọn trạng thái --" />
                    <form:option value="1" label="Đang bán" />
                    <form:option value="2" label="Đã kết thúc" />
                    <form:option value="3" label="Hết hàng" />
                    <form:option value="4" label="Không được duyệt" />
                    <form:option value="5" label="Đã bị xóa" />
                    <form:option value="6" label="Lưu tạm" />
                </form:select>
            </div>
            <div class="form-group">                
                <form:select path="source" type="text" class="form-control">
                    <form:option value="" label="-- Chọn nguồn --" />
                    <form:option value="SELLER" label="SELLER" />
                    <form:option value="API" label="API" />
                    <form:option value="CRAWL" label="CRAWL" />
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <div class="input-group">
                    <form:input type="text" path="manufacturerIds" class="form-control" placeholder="Nhập mã thương hiệu" />
                    <span class="input-group-btn">
                        <button class="btn btn-default" onclick="item.loadmf('manufacturerIds', '', 'itemSearch');"  type="button">Tìm</button>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <form:input type="text" path="modelIds" class="form-control" placeholder="Nhập mã model" />
                    <span class="input-group-btn">
                        <button class="btn btn-default" onclick="item.loadmodel('modelIds', '', 'itemSearch');"  type="button">Tìm</button>
                    </span>
                </div>                
            </div>
            <div class="form-group">
                <div class="input-group">
                    <form:input type="text" path="sellerId" class="form-control" placeholder="Nhập mã người bán" />
                    <span class="input-group-btn">
                        <button class="btn btn-default" onclick="item.loadseller('sellerId', 'itemSearch');"  type="button">Tìm</button>
                    </span>
                </div>                
            </div>
            <div class="form-group ">
                <button type="submit"  class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc sản phẩm </button>
            </div>
        </div>
        <div class="col-sm-4" id="selectcategorys" ></div>          

        <form:input type="hidden" path="categoryId" class="form-control" placeholder="Nhập mã model" />          
        <div class="clearfix" style="margin-bottom: 20px;"></div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px;">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${text:numberFormat(itemPage.dataCount)} </strong> kết quả <strong>${text:numberFormat(itemPage.pageCount)}</strong> trang.
        </h5> 
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${itemPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${itemPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>            
    <table class="table table-striped table-bordered table-responsive">
        <tbody>
            <tr>
                <th class="text-center">Ảnh đại diện</th>
                <th class="text-center" >Thông tin sản phẩm</th>
                <th class="text-center" style="width: 150px;" >Mô tả</th>
                <th class="text-center" style="width: 150px;" >Thời gian</th>
                <th class="text-center">Thông tin giá</th>
                <th class="text-center">Tình trạng</th>
                <th class="text-center" style="width: 150px;">Chức năng</th>                
            </tr>
            <c:forEach var="item" items="${itemPage.data}">
                <tr for="${item.id}" class="${item.approved && item.completed && item.active?'success':''}" >
                    <td class="text-center" style="vertical-align: middle;">
                        <img class="itemImage" style="alignment-baseline:central; max-width: 150px;" src="${item.images[0]}">
                    </td>
                    <td>
                        <p>
                            <a href="${url:item(item.id,item.name)}" target="_blank">${item.name}</a> 
                            (${item.id})
                        </p>
                        <p>Người bán: <a href="${baseUrl}/user/${item.sellerId}/ho-so-nguoi-ban.html" target="_blank">${item.sellerName}</a></p>
                        <p>Danh mục chợ: 
                            <c:if test="${itemCates==null || itemCates==''}">
                                <span class="label label-danger" >Chưa map</span>
                            </c:if>
                            <c:if test="${itemCates!=null && itemCates!=''}">
                                <c:set var="flag" value="false" />
                                <c:forEach items="${itemCates}" var="icate">
                                    <c:if test="${icate.id == item.categoryId && !flag}">
                                        <a href="${url:browse(icate.id,icate.name)}" target="_blank">${icate.name}</a>
                                        <c:set var="flag" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${!flag}">
                                    <span class="label label-warning" >Không tìm thấy</span>
                                </c:if>
                            </c:if>
                        </p>                        
                        <p>Thương hiệu: 
                            <c:if test="${itemManuf==null || itemManuf==''}"><span class="label label-danger" >Chưa map</span></c:if>
                            <c:if test="${itemManuf!=null && itemManuf!=''}">
                                <c:set var="flag" value="false" />
                                <c:forEach items="${itemManuf}" var="imanuf">
                                    <c:if test="${imanuf.id == item.manufacturerId && !flag}">
                                        <a href="${baseUrl}" target="_blank">${imanuf.name}</a>
                                        <c:set var="flag" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${!flag}">
                                    <span class="label label-warning" >Không tìm thấy</span>
                                </c:if>
                            </c:if>
                        </p>                        
                        <p>Model: 
                            <c:if test="${itemModels==null || itemModels==''}"><span class="label label-danger" >Chưa map</span></c:if>
                            <c:if test="${itemModels!=null && itemModels!=''}">
                                <c:set var="flag" value="false" />
                                <c:forEach items="${itemModels}" var="imodel">
                                    <c:if test="${imodel.id == item.modelId && !flag}">
                                        <a href="${baseUrl}" target="_blank">${imodel.name}</a>
                                        <c:set var="flag" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${!flag}">
                                    <span class="label label-warning" >Không tìm thấy</span>
                                </c:if>
                            </c:if>
                        </p>
                        <c:if test="${item.sellerSku != null && item.sellerSku != ''}">
                            <p>Link gốc: <a style="word-break: break-all;" href="${item.sellerSku}" target="_blank">${item.sellerSku}</a></p>
                            </c:if>
                        <p>Tỉnh/ Thành: 
                            <c:if test="${item.cityId!=null && item.cityId!='' && item.cityId!='0'}">
                                <c:forEach items="${cities}" var="city">
                                    <c:if test="${city.id == item.cityId}">
                                        <strong>${city.name}</strong>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </p>                  
                    </td>
                    <td >
                        <p class="text-center" >
                            <c:choose> 
                                <c:when test="${item.source == 'SELLER'}">Người bán đăng</c:when>
                                <c:when test="${item.source == 'CRAWL'}">Crawl</c:when>
                                <c:when test="${item.source == 'API'}">Đăng qua API</c:when>
                                <c:otherwise> <span class="label label-danger" >Không tìm thấy</span></c:otherwise>
                            </c:choose>
                        </p>
                        <hr/>
                        <p>Số lượng: <b style="color:red">${text:numberFormat(item.quantity)}</b></p>
                        <p>Khối lượng: <b style="color:red">${text:numberFormat(item.weight)} <sub>g</sub></b></p>
                    </td>
                    <td>
                        <jsp:useBean id="date" class="java.util.Date" />
                        <p>Ngày đăng: <br/>
                            <jsp:setProperty name="date" property="time" value="${item.createTime}" />                            
                            <strong><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></strong>
                            </p>
                            <p>Ngày cập nhật <br/>
                            <jsp:setProperty name="date" property="time" value="${item.updateTime}" />                            
                            <strong><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></strong>
                            </p>
                            <p>Ngày bắt đầu: <br/>
                            <jsp:setProperty name="date" property="time" value="${item.startTime}" />
                            <strong><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></strong>
                            </p>
                            <p>Ngày kết thúc: <br/>
                            <jsp:setProperty name="date" property="time" value="${item.endTime}" />
                            <strong><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></strong>
                            </p>      
                        </td>
                        <td>
                            <p>Giá gốc: <b style="color:red">${text:numberFormat(item.startPrice)} <sup>đ</sup></b></p>
                        <p>Giá bán: <b style="color:red">${text:numberFormat(item.sellPrice)} <sup>đ</sup></b></p>
                        <c:if test="${item.listingType == 'BUYNOW' && item.discount}">
                            <p>Giá khuyến mại (hiện tại): <b style="color:red">${text:numberFormat(item.sellPrice)} <sup>đ</sup></b></p>
                        </c:if>
                        <c:if test="${item.listingType ==  'AUCTION'}">
                            <p>Bước giá: <b style="color:red">${text:numberFormat(item.bidStep)} <sup>đ</sup></b></p>
                        </c:if>
                        <p>Hình thức: 
                            <c:if test='${item.listingType == "BUYNOW"}'>
                                <b>Mua ngay</b>
                            </c:if>
                            <c:if test='${item.listingType == "AUCTION"}'>
                                <b>Đấu giá</b>
                            </c:if>
                        </p>
                    </td>    
                    <td> 
                        <p>                        
                            <c:if test="${item.approved}">
                                <label class="label label-success">Đã duyệt</label>
                            </c:if>
                            <c:if test="${!item.approved}">
                                <label class="label label-danger">Chưa duyệt</label>
                            </c:if>
                        </p>
                        <p>                    
                            <c:if test="${item.completed}">
                                <label class="label label-success">Đã hoàn thành</label>
                            </c:if>
                            <c:if test="${!item.completed}">
                                <label class="label label-warning">Lưu tạm</label>
                            </c:if> 
                        </p>
                        <p>
                            <jsp:useBean id="now" class="java.util.Date" />                            
                            <c:if test="${item.endTime < now.time}">
                                <label class="label label-danger">Hết hạn bán</label>
                            </c:if>
                            <c:if test="${item.startTime > now.time}">
                                <label class="label label-warning">Chưa bán</label>
                            </c:if>     
                            <c:if test="${item.startTime < now.time && item.endTime > now.time}">
                                <label class="label label-success">Đang bán</label>
                            </c:if>
                            <c:if test="${!item.active}">
                                <label class="label label-danger">Đã xóa</label>
                            </c:if>
                        </p>
                    </td>                                       
                    <td style="vertical-align: middle;" class="text-center">                        
                        <div class="btn-group form-group">
                            <button type="button" onclick="reviewitem.edit('${item.id}');" class="btn btn-default">
                                <span class="fa fa-edit pull-left"></span> Sửa
                            </button>
                            <button type="button" onclick="reviewitem.editImages('${item.id}');" class="btn btn-default" >
                                <span class="fa fa-photo pull-left"></span> Ảnh
                            </button>
                        </div>
                        <div class="btn-group form-group">
                            <button type="button" onclick="reviewitem.editProperties('${item.id}');" class="btn btn-default" style="width: 140px;">
                                <span class="fa fa-tags"></span> Map thuộc tính
                            </button>
                        </div>
                        <div class="btn-group form-group">
                            <button type="button" onclick="reviewitem.editDetails('${item.id}');" class="btn btn-default">
                                <span class="fa fa-desktop"></span> Mô tả
                            </button>
                            <button title="Phí vận chuyển" type="button" onclick="reviewitem.editFeeShip('${item.id}');" class="btn btn-default">
                                PVC
                            </button>
                        </div>
                        <div class="form-group btn-group">
                            <button type="button" onclick="reviewitem.submitEdit('${item.id}');" class="btn btn-success" style="width: 140px;" ><span class="glyphicon glyphicon-floppy-save"></span> Duyệt</button>
                        </div>

                    </td>
                </tr>
            </c:forEach>
            <c:if test="${itemPage.dataCount <= 0}">
                <tr>
                    <td class="text-center text-danger" colspan="4">Không tìm thấy sản phẩm nào</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <div style="margin-top: 10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${itemPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${itemPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix" style="margin-bottom: 10px;"></div>
    </div>
</div>
