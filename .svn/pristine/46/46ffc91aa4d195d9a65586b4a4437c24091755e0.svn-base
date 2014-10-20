<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html" target="_blank" >
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active" >Đăng bán sản phẩm</li>
    </ol>
    <h1 class="title-pages">Đăng bán sản phẩm</h1>
    <div class="tabs-content-user">
        <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-dang-ban-nhanh-42616303723.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn đăng  bán nhanh
            </a></div>
        <ul class="tab-title-content">
            <li><a href="${baseUrl}/user/dang-ban.html">Đăng 1 tin bán</a></li>
            <li class="active"><a onclick="additemquick.facebook();" >Đăng nhanh</a></li>
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <p class="mgt-15"> • Chức năng này phù hợp với việc đăng bán cùng một lúc nhiều sản phẩm với các thông tin cơ bản nhất, giúp người bán tiết kiệm thời gian tổ chức dữ liệu bán hàng. <br>
                        • Hình thức thu tiền và chính sách vận chuyển của người bán được mặc định sử dụng theo <a href="${baseUrl}/user/cau-hinh-tich-hop.html">chế độ cài đặt hiện tại</a>. Nếu bạn chưa cấu hình, ChợĐiệnTử sẽ mặc định người mua phải liên hệ trực tiếp với nhau để làm rõ phí vận chuyển và hình thức thanh toán.
                    </p>

                    <div class="row mgt-25">
                        <div class="form-inline quick-fr-category">
                            <div class="form-group">
                                <label class="control-label"><strong>Chọn danh mục chợ cần đăng: <span class="clr-red">*</span>   </strong> </label>
                            </div>

                            <div class="form-group">
                                <input type="hidden" name="categoryId" value="" />
                                <select class="form-control category_0" for="cate" level="0" >
                                    <option value="" >Chọn danh mục chợ</option>
                                </select>
                            </div>
                        </div>
                        <c:if test="${shop != null && shopCategoryCount >0}" >
                            <div class="form-inline quick-fr-category">
                                <div class="form-group">
                                    <label class="control-label"><strong>Chọn danh mục shop cần đăng: <span class="clr-red">*</span></strong> </label>
                                </div>

                                <div class="form-group">
                                    <input type="hidden" name="shopCategoryId" value="" />
                                    <select class="form-control shopCate_0" for="shopcate" level="0" >
                                        <option value="" >Chọn danh mục shop</option>
                                    </select>
                                </div>
                            </div>
                        </c:if>
                    </div>                                    
                    <div class="row mgt-25 listItem">
                        <div class="title-config-shop title-mobile">
                            Đăng bán sản phẩm
                        </div>
                        <div class="title-config-shop clearfix">
                            <div class="col-sm-5">Tiêu đề</div>
                            <div class="col-sm-3">Tình trạng sản phẩm</div>
                            <div class="col-sm-2">Giá (VNĐ)	</div>
                            <div class="col-sm-2">Cân nặng (Gram)</div>
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <a href="javascript:;" onclick="additemquick.createNewItem();" class="btn btn-lg btn-primary">Soạn thêm</a>
                        <a href="javascript:;" onclick="additemquick.submit();" class="btn btn-lg btn-danger">Đăng bán</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- ModalNoBottom -->
<div class="modal fade" id="ModalNoBottom" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
            </div><!-- end modal-body -->
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->  