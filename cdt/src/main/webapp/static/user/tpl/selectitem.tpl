<h5 class="title-popup">
    <div class="row row-popup">
        <div class="col-lg-5 col-xs-5 reset-padding"><input type="text" class="form-control" name="itemId" placeholder="Tìm theo ID"></div>
        <div class="col-lg-5 col-xs-5 reset-padding-all"><input type="text" class="form-control" name="itemKeyword" placeholder="Tìm theo tên sản phẩm"></div>
        <div class="col-lg-2 col-xs-2"><button type="button" onclick="shophomeitem.search()" class="btn btn-default">Tìm kiếm</button></div>
    </div>
</h5>
<div class="popup-form-choose-pro">                    	
    <div class="popup-form-choose-content clearfix">
        <table class="table tblSelectItem">
            
        </table>
    </div>
</div>                    
<div class="footer-form-choose-pro page-ouner clearfix">
    <div class="pull-left"><button type="button" class="btn btn-default btn-block btn-sm"onclick="shophomeitem.selectAll()">Lưu</button></div>
    <ul class="pagination pull-right" id="pagination">
        <li><a href="#">«</a></li>
        <li><a href="#">»</a></li>
    </ul>
</div>

