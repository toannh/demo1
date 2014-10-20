<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" value="0" name="category" />
<input type="hidden" value="0" name="mf" />
<input type="hidden" value="0" name="modelId" />

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html" target="_blank" >
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">
            <c:if test="${same == 1}">
                Đăng bán tin tương tự
            </c:if>
            <c:if test="${same != 1}">
                <c:if test="${id.trim() != ''}">Sửa tin đăng bán</c:if><c:if test="${id.trim() == ''}">Đăng bán sản phẩm</c:if></li>
            </c:if>
    </ol>
    <h1 class="title-pages">
        <c:if test="${same == 1}">
            Đăng bán tin tương tự
        </c:if>
        <c:if test="${same != 1}">
            <c:if test="${id.trim() != ''}">Sửa tin đăng bán</c:if><c:if test="${id.trim() == ''}">Đăng bán sản phẩm</c:if>
        </c:if>
    </h1>
    <div class="tabs-content-user">
        <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-dang-mot-tin-ban--604982063071.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn đăng một tin bán 
            </a></div>
        <ul class="tab-title-content">
            <li class="active"><a href="${baseUrl}/user/dang-ban.html">Đăng 1 tin bán</a></li>
            <li><a href="${baseUrl}/user/dang-ban-nhanh.html">Đăng nhanh</a></li>
            <!--                <li><a href="#">Đăng Exel</a></li>
                            <li><a href="#">Đăng Dịch vụ API, Crawling</a></li>-->
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="form-group checkerror">
                        <h4 class="pull-left control-label">Tên sản phẩm: <span class="clr-red">*</span></h4> <span class="pull-right small mgt-15">Tối đa 180 ký tự - <span class="clr-org"><strong class="countName">Còn 180/180</strong></span> ký tự</span>                                          
                        <p class="clearfix">
                            <input onkeyup="additem.countCharacters();
                                    additem.drawDataByKeyword(this);" maxlength="180" onkeypress="additem.countCharacters();"  name="name" type="text" class="form-control" placeholder="Đặt tên dễ hiểu, chính xác, hấp dẫn, phù hợp SEO để hiển thị lên đầu công cụ tìm kiếm Google. ">
                            <span class="errorMessage" for="error_name" style="color: red;" ></span>
                        </p>

                    </div>
                    <div class="form-group small">
                        Tên sản phẩm phù hợp nhất với SEO thường có cấu trúc sau: Tên loại sản phẩm + Thương hiệu + Model + Đặc điểm chính + màu sắc. 
                        Ví dụ: Máy ảnh số Sony Cybershot S8500 14 Megapixel (màu đen); hoặc Áo Polo Raplph Lauren cổ V màu vàng
                    </div>
                    <div class="form-group checkerror">
                        <h4 class="control-label">Danh mục sản phẩm: <span class="clr-red">*</span></h4>
                        <div class="row">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#suggestion-cat" data-toggle="tab" rel="tabgen">Danh mục gợi ý</a></li>
                                <li><a href="#suggestion-list" data-toggle="tab" rel="tabcate">Danh sách danh mục</a></li>
                            </ul>                                        
                            <!-- Tab panes -->
                            <div class="tab-content ">
                                <div class="tab-pane active" id="suggestion-cat" >
                                    <div class="nosubmit" >Gợi ý dựa trên tiêu đề tin bán.</div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="tab-pane" id="suggestion-list" >
                                    <div class="box-step"> 
                                        <div class="row suggestions-list-category" style="height: 155px; overflow-x: auto;">
                                            <div class="category-scroll" style="height: 155px;" rel="category" ></div><!--category-scroll-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="categoryId" />
                        <input type="hidden" name="shopCategoryId" />
                    </div>
                </div>
            </div>
            <div class="row form-inline form-inline-magin" rel="shopCategories">
            </div>
            <span class="errorMessage" for="error_shopCategoryId" style="color: red;" ></span>
            <div class="row form-horizontal mgt-25">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left">
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">Thương hiệu:</label>
                        <div class="col-sm-8">
                            <select class="form-control" name="manufacturer">
                                <option value="0">--Chọn thương hiệu--</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">Model:</label>
                        <div class="col-sm-9">
                            <select class="form-control" name="model" >
                                <option>-- Chọn model --</option>
                            </select> 
                        </div>
                        <span class="tool-tip pull-left" data-toggle="tooltip" data-placement="left" title="Ảnh Model được tự động trở thành Ảnh Sản phẩm của bạn. ChợĐiệnTử đã xây dựng sẵn các thông số kỹ thuật chi tiết của từng model, tuy nhiên bạn có thể sửa lại cho phù hợp bên dưới.">
                            <span class="glyphicon glyphicon-question-sign fs16"></span>
                        </span>                                        
                    </div>
                </div>
                <div class="clearfix"></div>

                <div data-rel="box-size-postItem"></div>
                <div data-rel="box-color-postItem"></div>

                <div class="clearfix"></div>
                <div class="form-full mgt-15">
                    <label class="control-label">Hình thức bán: <span class="clr-red">*</span></label>
                    <div class="row">
                        <ul class="nav nav-tabs">
                            <li rel="bynow" class="active"><a href="#muangay" data-toggle="tab">Mua ngay</a></li>
                            <li rel="auction"><a href="#auction" data-toggle="tab">Đấu giá</a></li>
                        </ul>                                        
                        <!-- Tab panes -->
                        <div class="tab-content ">
                            <div class="tab-pane active" id="muangay" rel="tab_bynow">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left">

                                    <div class="form-group checkerror">
                                        <label class="col-sm-3 col-xs-12 control-label">Giá bán:</label>
                                        <div class="col-sm-6 col-xs-7 reset-padding-all">
                                            <input type="text" class="form-control inputnumber" name="sellPrice" value="0">
                                        </div>
                                        <div class="col-sm-3 col-xs-5">
                                            <select class="form-control">
                                                <option>đ</option>
                                                <option>USD</option>
                                            </select>
                                        </div>

                                    </div>


                                    <div class="form-group">
                                        <label class="col-sm-3 col-xs-12 control-label">Thời điểm bán:</label>
                                        <div class="col-sm-9 col-xs-12 reset-padding">
                                            <div class="control-group">
                                                <div class="controls">
                                                    <div class="date-picker-block">
                                                        <input id="date-picker-2" type="text" class="date-picker form-control" placeholder="ngày/ tháng/ năm" name="startTime" >
                                                        <label for="date-picker-2"><span class="glyphicon glyphicon-calendar" for="date-picker-2"></span></label>     
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right reset-padding-all">
                                    <div class="form-group">
                                        <label class="col-sm-3 col-xs-12 control-label">Giá gốc:</label>
                                        <div class="col-sm-6 col-xs-7 reset-padding-all">
                                            <input type="text" class="form-control inputnumber"  name="startPrice" value="0">
                                        </div>
                                        <div class="col-sm-3 col-xs-5">
                                            <select class="form-control">
                                                <option>đ</option>
                                                <option>USD</option>
                                            </select>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-xs-12 control-label">Hạn đăng bán:</label>
                                        <div class="col-sm-3 col-xs-11 reset-padding-all">
                                            <select name="endDay" class="form-control">
                                                <option value="30" >30 ngày</option>
                                                <option value="40" >40 ngày</option>
                                                <option value="60" >60 ngày</option>
                                            </select>
                                        </div>
                                        <div class="col-sm-6 col-xs-12 reset-padding-right">
                                            <div class="form-group checkerror">
                                                <label class="col-sm-4 col-xs-12 control-label">Số lượng</label>
                                                <div class="col-sm-4 col-xs-7 reset-padding-all">
                                                    <input type="text" class="form-control inputnumber" name="quantity" value="1">
                                                </div>
                                                <div class="col-sm-4 col-xs-5 reset-padding-right">
                                                    <p class="form-control-static">đơn vị</p>
                                                </div>

                                            </div>
                                        </div>
                                    </div>  
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="tab-pane" id="auction">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left">
                                    <div class="form-group checkerror">
                                        <label class="col-sm-3 col-xs-12 control-label">Giá khởi điểm:</label>
                                        <div class="col-sm-6 col-xs-7 reset-padding-all">
                                            <input type="text" class="form-control inputnumber" name="bidStartPrice" value="0">
                                        </div>
                                        <div class="col-sm-3 col-xs-5">
                                            <p class="form-control-static">đ</p>
                                        </div>
                                    </div>
                                    <div class="form-group checkerror">
                                        <label class="col-sm-3 col-xs-12 control-label">Giá bán ngay:</label>
                                        <div class="col-sm-6 col-xs-7 reset-padding-all">
                                            <input type="text" class="form-control inputnumber" name="bidSellPrice" value="0">
                                        </div>
                                        <div class="col-sm-3 col-xs-5">
                                            <p class="form-control-static">đ</p>
                                        </div>
                                    </div>                                    
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right reset-padding-all">
                                    <div class="form-group checkerror">
                                        <label class="col-sm-2 col-xs-12 control-label">Bước giá:</label>
                                        <div class="col-sm-6 col-xs-7 reset-padding-right">
                                            <input type="text" class="form-control inputnumber" name="bidStep" value="0">
                                        </div>
                                        <div class="col-sm-3 col-xs-5">
                                            <p class="form-control-static">đ</p>
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <div class="col-sm-12 col-xs-12 reset-padding-left">
                                            <div class="col-sm-6 col-xs-6">
                                                <div class="form-group checkerror">
                                                    <label class="col-sm-5 control-label reset-padding">Thời điểm đấu:</label>
                                                    <div class="col-sm-7 reset-padding">
                                                        <div class="control-group">
                                                            <div class="controls">
                                                                <div class="input-group date-picker-block">
                                                                    <input id="date-picker-3" type="text" class="date-picker form-control" placeholder="ngày/ tháng/ năm" name="bidStartTime" />
                                                                    <label for="date-picker-3" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
                                                                </div>
                                                            </div>
                                                        </div>                                          
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-xs-6">
                                                <div class="form-group checkerror">
                                                    <label class="col-sm-4 control-label">Kết thúc:</label>
                                                    <div class="col-sm-8 reset-padding">
                                                        <div class="control-group">
                                                            <div class="controls">
                                                                <div class="input-group date-picker-block">
                                                                    <input id="date-picker-4" type="text" class="date-picker form-control" placeholder="ngày/ tháng/ năm" name="bidEndTime" />
                                                                    <label for="date-picker-4" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
                                                                </div>
                                                            </div>
                                                        </div>                                                      
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>  
                                </div>

                                <div class="clearfix"></div>                                          
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group form-full">
                    <label class="control-label">Tình trạng sản phẩm: <span class="clr-red">*</span></label>
                    <label class="checkbox-inline">
                        <input name="condition" type="radio" value="1" checked="checked" /> Mới
                    </label>
                    <label class="checkbox-inline">
                        <input name="condition" type="radio" value="2" /> Cũ
                    </label>
                </div>
                <div class="form-group form-full checkerror">                                	
                    <p class="mgt-25"><label class="control-label clearfix">Mô tả sản phẩm: <span class="clr-red">*</span></label></p>
                    <div class="col-lg-4 col-md-4 col-sm-5 col-xs-12 pull-left">
                        <p><i>Click ô dưới để đăng ảnh:</i></p>  
                        <input name="images" type="hidden" />
                        <div class="thumbnail capture-icon" id="photos">
                        </div>
                        <p><i>Di hình bên trong để chọn góc đẹp nhất</i></p>
                        <form id="img-add-form-bylink" method="post" >
                            <input type="hidden" name="id" value="" />
                            <input type="text" name="imageUrl" onchange="additem.addImageByLink();" class="form-control" style="max-width:235px;" placeholder="Nhập URL hình ảnh vào đây">
                        </form>
                        <div class="submit-choose-img-item">
                            <div rel="img" ></div>
                            <div class="img-item" rel="loading" style="display: none" >
                                <span class="loading-item"></span>
                            </div>                                                                                                                   
                            <div class="submit-img-item">
                                <div class="img">
                                    <form id="img-add-form" method="post" >
                                        <a class="img-upload-item"> </a>
                                        <input type="hidden" name="id" value="" />
                                        <input name="image" id="image" onchange="additem.addImageByLocal();" type="file" style="display: none" />
                                    </form>
                                </div>
                            </div>                                                                                                                      
                        </div>

                    </div>
                    <div class="col-lg-8 col-md-8 col-sm-7 col-xs-12 pull-right reset-padding-all">
                        <p><i>Soạn thảo nội dung mô tả sản phẩm:</i></p>
                        <textarea class="form-control" rows="26" id="detail" name="detail" placeholder="- Bạn có thể kéo ảnh ở bên thả vào đây để làm mô tả.
                                  <br />- Bạn có thể copy nội dung mô tả sản phẩm của mình tại website khác vào đây. Ảnh trong bài sẽ tự động được đưa vào Ảnh sản phẩm ở bên."></textarea>
                    </div>
                </div>    
                <div class="form-group form-full">
                    <p class="mgt-15"><label class="control-label clearfix">Thông số sản phẩm:</label></p>
                    <div class="row">
                        <div class="submit-param" rel="properties">

                        </div><!--submit-param-->
                        <p><strong>Lưu ý: </strong>Các sản phẩm có mô tả đặc tính cụ thể, rõ ràng và kỹ lưỡng sẽ thu hút người mua hơn, giúp người mua yên tâm và chọn đúng sản phẩm theo nhu cầu. </p>
                    </div>
                </div> 
                <div class="form-group form-full">
                    <div class="clearfix"><label class="control-label clearfix">Phí vận chuyển:</label></div>                                    <div class="box-check form-inline shipping-block-statics">
                        <p>(Người mua trả phí vận chuyển theo quy định của người bán) </p>
                        <div class="form-group">
                            <label class="checkbox-inline"><input checked name="shipmentType" type="radio" value="3"> Phí vận chuyển linh hoạt theo địa chỉ người mua và cân nặng sản phẩm (theo bảng phí ChợĐiệnTử)</label>
                        </div> 
                        <div class="form-group checkerror" style="padding-left:17px;">
                            <label class="checkbox-inline"> Cân nặng <input type="text" class="form-control inputnumber" name="weight" value="0">  gram  <a href="#" data-toggle="modal" data-target="#ModalNoBottom">(Tính cân nặng sản phẩm cổng kềnh)</a></label>                                           
                        </div>
                        <div class="form-group">
                            <label class="checkbox-inline"><input name="shipmentType" type="radio" value="1"> Không ghi rõ phí vận chuyển (người mua có thể khó chịu nếu sau đó bạn thu thêm phí vận chuyển)</label>
                            <div class="clearfix"></div>
                        </div>
                        <div class="form-group">
                            <p class="form-control-static">
                                <label class="checkbox-inline">
                                    <input name="shipmentType" type="radio" value="2" style="margin-top:7px;"> Phí vận chuyển cố định toàn quốc:	<input type="text" class="form-control inputnumber" name="shipmentTypePrice" value="0"> đ</label></p>
                            <div class="clearfix"></div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="form-group">
                            <p class="form-control-static">
                                <label class="checkbox-inline"><input type="radio" name="shipmentType" value="4"> Miễn phí</label></p>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="line-more-button"><a class="btn-more-button">&nbsp;</a></div>                                    
                    </div>
                </div>  
                <div class="form-group text-center">
                    <c:if test="${id.trim() != ''}">
                        <div class="submit-button">
                            <a onclick="additem.addItem(${same});" class="btn btn-lg btn-danger">Lưu</a>
                        </div><!--submit-button-->
                    </c:if>
                    <c:if test="${id.trim() == ''}">
                        <a onclick="additem.addItem();" class="btn btn-lg btn-danger">Đăng bán</a>
                    </c:if>
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
                <p><strong>Điền kích thước</strong><span class="small clr-red">Chỉ áp dụng cho hàng hóa cồng kềnh</span></p>
                <div class="form-inline conversion-feature">
                    <div class="form-group">
                        <input class="form-control inputnumber" name="dataLength" type="text" placeholder="Dài (cm)">
                    </div>
                    <div class="form-group">
                        <input class="form-control inputnumber" name="dataWidth" type="text" placeholder="Rộng (cm)">
                    </div>
                    <div class="form-group">
                        <input class="form-control inputnumber" name="dataHeight" type="text" placeholder="Cao (cm)">
                    </div>

                    <div class="text-center mgt-15"><button class="btn btn-default btn-sm " onclick="additem.calcWeight()" data-dismiss="modal">Tính năng quy đổi</button></div>                                                      

                </div>

            </div><!-- end modal-body -->
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->  