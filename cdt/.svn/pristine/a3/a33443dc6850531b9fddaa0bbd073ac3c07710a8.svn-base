<?php
include_once '/entity/Item.php';
error_reporting(-1);

class CdtAPI {

    public $apiUrl = "http://beta.chodientu.vn/api/";
    private $code = "301382101889";
    private $email = "huyenht@peacesoft.net";

    public function callPost($task, $param) {
        $url = $this->apiUrl . $task;
//        echo $url;
        $requestBody = new ArrayObject();
        $requestBody["code"] = $this->code;
        $requestBody["email"] = $this->email;
        $requestBody["params"] = @json_encode($param);
        $json = @json_encode($requestBody);
//        echo $json;
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $json);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_VERBOSE, 1);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");
        curl_setopt($ch, CURLOPT_ENCODING, 'UTF-8');
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
        $result = curl_exec($ch);
        curl_close($ch);
        $rs = json_decode($result, false);
        return $rs;
    }

    public function getItem($param) {
        $task = "item/getitem.api";
        $param = new ItemPost();
        if (@$_GET["sellerSku"] != null && @$_GET["sellerSku"] != "") {
            $param->sellerSku = @$_GET["sellerSku"];
        }
        $result = $this->callPost($task, $param);
        echo "<script> var item = " . json_encode($result->data) . " </script>";
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function submitEdit($param) {
        $task = "item/editsubmit.api";
        $param = new ItemPost();
        if (@$_GET["name"] != null && @$_GET["name"] != "") {
            $param->name = @$_GET["name"];
        }
        if (@$_GET["categoryId"] != null && @$_GET["categoryId"] != "") {
            $param->categoryId = @$_GET["categoryId"];
        }
        if (@$_GET["shopCategoryId"] != null && @$_GET["shopCategoryId"] != "") {
            $param->shopCategoryId = @$_GET["shopCategoryId"];
        }
        if (@$_GET["listingType"] != null && @$_GET["listingType"] != "") {
            $param->listingType = @$_GET["listingType"];
        }
        if (@$_GET["condition"] != null && @$_GET["condition"] != "") {
            $param->condition = @$_GET["condition"];
        }
        if (@$_GET["sellerSku"] != null && @$_GET["sellerSku"] != "") {
            $param->sellerSku = @$_GET["sellerSku"];
        }
        if (@$_GET["quantity"] != null && @$_GET["quantity"] != "") {
            $param->quantity = @$_GET["quantity"];
        }
        if (@$_GET["modelId"] != null && @$_GET["modelId"] != "") {
            $param->modelId = @$_GET["modelId"];
        }
        if (@$_GET["sellPrice"] != null && @$_GET["sellPrice"] != "") {
            $param->sellPrice = @$_GET["sellPrice"];
        }
        if (@$_GET["startPrice"] != null && @$_GET["startPrice"] != "") {
            $param->startPrice = @$_GET["startPrice"];
        }
        if (@$_GET["manufacturerId"] != null && @$_GET["manufacturerId"] != "") {
            $param->manufacturerId = @$_GET["manufacturerId"];
        }
        if (@$_GET["detail"] != null && @$_GET["detail"] != "") {
            $param->detail = @$_GET["detail"];
        }
        if (@$_GET["bidStep"] != null && @$_GET["bidStep"] != "") {
            $param->bidStep = @$_GET["bidStep"];
        }
        if (@$_GET["weight"] != null && @$_GET["weight"] != "") {
            $param->weight = @$_GET["weight"];
        }
        if (@$_GET["shipmentType"] != null && @$_GET["shipmentType"] != "") {
            $param->shipmentType = @$_GET["shipmentType"];
        }
        if (@$_GET["shipmentPrice"] != null && @$_GET["shipmentPrice"] != "") {
            $param->shipmentPrice = @$_GET["shipmentPrice"];
        }
        if (@$_GET["startTime"] != null && @$_GET["startTime"] > 0) {
            $param->startTime = @$_GET["startTime"];
        }
        if (@$_GET["endTime"] != null && @$_GET["endTime"] > 0) {
            $param->endTime = @$_GET["endTime"];
        }
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

}
?>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="http://sinhvienit.net/forum/skin/SinhVienIT.Net-IT_Pro-Ver4/jquery-1.8.0.min.js" type="text/javascript"></script>

<script type="text/javascript">
    function drawlItem() {
        if (typeof item === "undefined") {
            return false;
        }
        $("input[data-rel=sellerSku]").val(item.sellerSku);
        $("input[data-rel=id]").val(item.id);
        $("input[data-rel=name]").val(item.name);
        $("input[data-rel=categoryId]").val(item.categoryId);
        $("input[data-rel=modelId]").val(item.modelId);
        $("input[data-rel=manufacturerId]").val(item.manufacturerId);
        $("input[data-rel=startTime]").val(item.startTime);
        $("input[data-rel=endTime]").val(item.endTime);
        $("input[data-rel=listingType]").val(item.listingType);
        $("input[data-rel=condition]").val(item.condition);
        $("input[data-rel=quantity]").val(item.quantity);
        $("input[data-rel=startPrice]").val(item.startPrice);
        $("input[data-rel=sellPrice]").val(item.sellPrice);
        $("input[data-rel=bidStep]").val(item.bidStep);
        $("input[data-rel=shipmentType]").val(item.shipmentType);
        $("input[data-rel=shipmentPrice]").val(item.shipmentPrice);
        $("input[data-rel=weight]").val(item.weight);
        $("input[data-rel=shopCategoryId]").val(item.shopCategoryId);
    }
</script>

<div class="form-group" style="width:800px">
    <label class="col-sm-3 control-label">Tên API:</label>
    <div class="col-sm-7">
        <select name="function" class="form-control">
            <option value="">-----Chọn API cần dùng-----</option>
            <option <?php
            if (@$_GET["table"] != null && @$_GET["table"] == 'getItem') {
                echo 'selected';
            }
            ?> value="getItem">Lấy thông tin  của sản phẩm</option>
            <option  <?php
            if (@$_GET["table"] != null && @$_GET["table"] == 'submitEdit') {
                echo 'selected';
            }
            ?> value="submitEdit">Sửa chính thức sản phẩm</option>
        </select>
    </div>
</div>
<div class="clearfix"></div>
<div class="row" style="margin-top: 50px;margin-left: auto;margin-right: auto;width: 700px" >
    <form class="form-horizontal" role="form" method='GET' hidden="true" name="getItem">
        <h2>Lấy thông thông tin  sản phẩm</h2>
        <input name="func" type="hidden" value="getitem" class="form-control">
        <input name="table" type="hidden" value="getItem" class="form-control">
        <div class="form-group">
            <label class="col-sm-3 control-label">Mã SKU:</label>
            <div class="col-sm-7">
                <input name="sellerSku"  type="text" class="form-control"  placeholder="Mã SKU">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" role="form" method='GET' hidden="true"  name="submitEdit">
        <h2>Sửa chính thức sản phẩm</h2>
        <input name="func" type="hidden" value="editsubmit" class="form-control">
        <input name="table" type="hidden" value="submitEdit" class="form-control">
        <div class="form-group">
            <label class="col-sm-3 control-label"></label>
            <div class="col-sm-7">
                <button type="button" onclick="drawlItem();" class="btn btn-success">Lấy thông tin</button>
            </div>
        </div>
        <input type="hidden" name="id" data-rel="id" />
        <div class="form-group">
            <label class="col-sm-3 control-label">Mã SKU:</label>
            <div class="col-sm-7">
                <input name="sellerSku" data-rel="sellerSku" type="text" class="form-control"  placeholder="Mã SKU">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Tên sản phẩm:</label>
            <div class="col-sm-7">
                <input name="name" data-rel="name" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Danh mục:</label>
            <div class="col-sm-7">
                <input name="categoryId" data-rel="categoryId" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Danh mục shop:</label>
            <div class="col-sm-7">
                <input name="shopCategoryId" data-rel="shopCategoryId" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Hình thức bán:</label>
            <div class="col-sm-7">
                <input name="listingType" data-rel="listingType" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Số lượng:</label>
            <div class="col-sm-7">
                <input name="quantity" data-rel="quantity" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Tình trạng:</label>
            <div class="col-sm-7">
                <input name="condition" data-rel="condition"  type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Thương hiệu:</label>
            <div class="col-sm-7">
                <input name="manufacturerId" data-rel="manufacturerId" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Model:</label>
            <div class="col-sm-7">
                <input name="modelId" data-rel="modelId" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Detail:</label>
            <div class="col-sm-7">
                <textarea name="detail" data-rel="detail" class="form-control"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Giá gốc:</label>
            <div class="col-sm-7">
                <input name="startPrice" data-rel="startPrice"  type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Giá bán:</label>
            <div class="col-sm-7">
                <input name="sellPrice" data-rel="sellPrice" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Bước giá:</label>
            <div class="col-sm-7">
                <input name="bidStep" data-rel="bidStep" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Cân nặng:</label>
            <div class="col-sm-7">
                <input name="weight" data-rel="weight" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Hình thức vận chuyển:</label>
            <div class="col-sm-7">
                <input name="shipmentType" data-rel="shipmentType" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Phí vận chuyển:</label>
            <div class="col-sm-7">
                <input name="shipmentPrice" data-rel="shipmentPrice" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Thời gian bắt đầu:</label>
            <div class="col-sm-7">
                <input name="startTime" data-rel="startTime" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Thời gian kết thúc:</label>
            <div class="col-sm-7">
                <input name="endTime" data-rel="endTime" type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>

</div>
<?php
$obj = new CdtAPI();
if (@$_GET["func"] != null && @$_GET["func"] != "") {
    $func = @$_GET["func"];
    switch ($func) {
        case "getitem":
            $obj->getItem(null);
            break;
        case "editsubmit":
            $obj->submitEdit(null);
            break;
        default:
            echo 'Mèo đi lạc';
            break;
    }
}
?>

<link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
<link rel="stylesheet" href="css/bootstrap.css.map" type="text/css"/>

<script type="text/javascript">
                    window.onload = checkSelect();
                    function checkSelect() {
                        var value = $('select[name=function]').val();
                        switch (value) {
                            case "getItem":
                                $('form[name=getItem]').show();
                                $('form[name=submitEdit]').hide();
                                break;
                            case "submitEdit":
                                $('form[name=getItem]').hide();
                                $('form[name=submitEdit]').show();
                                break;
                            default:
                                $('form[name=getItem]').hide();
                                $('form[name=submitEdit]').hide();
                                break;
                        }
                    }
                    $('select[name=function]').change(function() {
                        checkSelect();
                    });


</script>