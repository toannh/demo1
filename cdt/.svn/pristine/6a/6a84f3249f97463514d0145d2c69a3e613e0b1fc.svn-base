<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa"></i>
            Đăng nhanh
        </a>
    </li>
</ul>
<div class="func-container" >
    <div class="row">
        <div class="form-inline" style="margin:15px;">
            <div class="form-group seller">
                <label class="control-label"></label>
                <input type="text" class="form-control" name="sellerName" value="" placeholder="Username,email" />
            </div>
        </div>
        <div for="quick">
            <div class="form-inline" style="margin:15px;">
                <div class="form-group">
                    <label class="control-label">Số lượng sản phẩm cần đăng bán: </label>
                    <input type="text" class="form-control inputnumber" maxlength="2" name="numberItem" value="0" placeholder="Số lượng sản phẩm" />
                </div>
            </div>
        </div>

        <div class="form-inline" style="margin:15px;">
            <div class="form-group">
                <label class="control-label"><strong>Chọn danh mục chợ cần đăng: <span style="color: red;">*</span>   </strong> </label>
            </div>

            <div class="form-group">
                <input type="hidden" name="categoryId" value="" />
                <select class="form-control category_0" for="cate" level="0" >
                    <option value="" >Chọn danh mục chợ</option>
                </select>
            </div>
        </div>
        <div class="shopCates">
        </div>
    </div>                                    
    <div class="listItem">
        <div style="margin: 15px 0; background: #fff8c9;border-radius: 5px; padding: 10px; font-weight: bold;" class="row">
            <div class="col-sm-4">Tiêu đề</div>
            <div class="col-sm-2">Tình trạng sản phẩm</div>
            <div class="col-sm-2">Giá gốc(VNĐ)</div>
            <div class="col-sm-2">Giá bán(VNĐ)</div>
            <div class="col-sm-2">Cân nặng (Gram)</div>
        </div>
    </div>
    <div class="form-group text-center">
        <a href="javascript:;" onclick="itemquick.createNewItem();" class="btn btn-lg btn-primary">Soạn thêm</a>
        <a href="javascript:;" onclick="itemquick.submit();" class="btn btn-lg btn-danger">Đăng bán</a>
    </div>
</div>

<!-- ModalNoBottom -->
<div class="modal fade" id="ModalNoBottom" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
            </div><!-- end modal-body -->
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->  