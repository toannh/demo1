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

    public function addImages($param) {
        $task = "item/addimages.api";
        $param = new ItemForm();
        if (@@$_GET["sellerSku"] != null && @@$_GET["sellerSku"] != "") {
            $param->sellerSku = @$_GET["sellerSku"];
        }
        if (@@$_GET["imageUrl"] != null && @@$_GET["imageUrl"] != "") {
            $param->imageUrl = @$_GET["imageUrl"];
        }
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function delImages($param) {
        $task = "item/delimage.api";
        if (@@$_GET["sellerSku"] != null && @@$_GET["sellerSku"] != "") {
            $param->sellerSku = @$_GET["sellerSku"];
        }
        if (@@$_GET["imageUrl"] != null && @@$_GET["imageUrl"] != "") {
            $param->imageUrl = @$_GET["imageUrl"];
        }
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function addItem($param) {
        $task = "item/additem.api";
        $param = new ItemPost();
        if (@@$_GET["sellerSku"] != null && @@$_GET["sellerSku"] != "") {
            $param->sellerSku = @@$_GET["sellerSku"];
        }
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function delItem($param) {
        $task = "item/delete.api";
        if (@@$_GET["sellerSku"] != null && @@$_GET["sellerSku"] != "") {
            $param->sellerSku = @@$_GET["sellerSku"];
        }
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function submitAdd($param) {
        $task = "item/addsubmit.api";
        $param = new ItemPost();

        if (@@$_GET["name"] != null && @$_GET["name"] != "") {
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
<?php
?>
<div class="form-group" style="width:800px">
    <label class="col-sm-3 control-label">Tên API:</label>
    <div class="col-sm-7">
        <select name="function" class="form-control">
            <option value="">-----Chọn API cần dùng-----</option>
            <option <?php
            if (@$_GET["table"] != null && @$_GET["table"] == 'additem') {
                echo 'selected';
            }
            ?> value="addItem">Đăng thông tin cơ bản của sản phẩm</option>
            <option  <?php
            if (@$_GET["table"] != null && @$_GET["table"] == 'addimages') {
                echo 'selected';
            }
            ?> value="addImage">Thêm ảnh sản phẩm</option>
            <option  <?php
            if (@$_GET["table"] != null && @$_GET["table"] == 'delimage') {
                echo 'selected';
            }
            ?> value="delImage">Xóa ảnh sản phẩm</option>
            <option  <?php
            if (@$_GET["table"] != null && @$_GET["table"] == 'submitadd') {
                echo 'selected';
            }
            ?> value="submitAdd">Chính thức đăng sản phẩm</option>
            <option  <?php
            if (@$_GET["table"] != null && @$_GET["table"] == 'delItem') {
                echo 'selected';
            }
            ?> value="delItem">Xóa sản phẩm</option>
        </select>
    </div>
</div>

<div class="clearfix"></div>
<div class="row" style="margin-top: 50px;margin-left: auto;margin-right: auto;width: 700px" >
    <form class="form-horizontal" role="form" method='GET' hidden="true" name="addItem">
        <h2>Đăng thông tin cơ bản sản phẩm</h2>
        <input name="func" type="hidden" value="additem" class="form-control">
        <input name="table" type="hidden" value="additem" class="form-control">
        <div class="form-group">
            <label class="col-sm-3 control-label">Mã SKU:</label>
            <div class="col-sm-7">
                <input name="sellerSku" type="text" class="form-control"  placeholder="Mã SKU">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>


    <form class="form-horizontal" role="form" method='GET' hidden="true" name="addImage">
        <h2>Đăng ảnh cho sản phẩm</h2>
        <input name="func" type="hidden" value="addimages" class="form-control" >
        <input name="table" type="hidden" value="addimages" class="form-control">
        <div class="form-group">
            <label class="col-sm-3 control-label">Mã SKU:</label>
            <div class="col-sm-7">
                <input name="sellerSku" type="text" class="form-control"  placeholder="Mã SKU">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Link ảnh:</label>
            <div class="col-sm-7">
                <input name="imageUrl" type="text" class="form-control"  placeholder="Link ảnh">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" role="form" method='GET' hidden="true" name="delImage">
        <h2>Xóa ảnh cho sản phẩm</h2>
        <input name="func" type="hidden" value="delimage" class="form-control" >
        <input name="table" type="hidden" value="delimage" class="form-control">
        <div class="form-group">
            <label class="col-sm-3 control-label">Mã SKU:</label>
            <div class="col-sm-7">
                <input name="sellerSku" type="text" class="form-control"  placeholder="Mã SKU">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Link ảnh:</label>
            <div class="col-sm-7">
                <input name="imageUrl" type="text" class="form-control"  placeholder="Link ảnh cần xóa">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>


    <form class="form-horizontal" role="form" method='GET' hidden="true" name="delItem">
        <h2>Xóa sản phẩm</h2>
        <input name="func" type="hidden" value="delete" class="form-control" >
        <input name="table" type="hidden" value="delItem" class="form-control">
        <div class="form-group">
            <label class="col-sm-3 control-label">Mã SKU:</label>
            <div class="col-sm-7">
                <input name="sellerSku" type="text" class="form-control"  placeholder="Mã SKU">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-default">Xóa</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" role="form" method='GET' hidden="true"  name="submitAdd">
        <h2>Đăng chính thức sản phẩm</h2>
        <input name="func" type="hidden" value="submitadd" class="form-control">
        <input name="table" type="hidden" value="submitadd" class="form-control">
        <div class="form-group">
            <label class="col-sm-3 control-label">Mã SKU:</label>
            <div class="col-sm-7">
                <input name="sellerSku" type="text"  value='<?php
                if (isset($_GET["sellerSku"])) {
                    echo $_GET["sellerSku"];
                }
                ?>'  class="form-control"  placeholder="Mã SKU">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Tên sản phẩm:</label>
            <div class="col-sm-7">
                <input name="name" type="text" value='<?php
                if (isset($_GET["name"])) {
                    echo $_GET["name"];
                }
                ?>' class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Danh mục:</label>
            <div class="col-sm-7">
                <input name="categoryId" value='<?php
                if (isset($_GET["categoryId"])) {
                    echo $_GET["categoryId"];
                }
                ?>'  type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Danh mục shop:</label>
            <div class="col-sm-7">
                <input name="shopCategoryId" value='<?php
                if (isset($_GET["shopCategoryId"])) {
                    echo $_GET["shopCategoryId"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Hình thức bán:</label>
            <div class="col-sm-7">
                <input name="listingType" value='<?php
                if (isset($_GET["listingType"])) {
                    echo $_GET["listingType"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Số lượng:</label>
            <div class="col-sm-7">
                <input name="quantity" value='<?php
                if (isset($_GET["quantity"])) {
                    echo $_GET["quantity"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Tình trạng:</label>
            <div class="col-sm-7">
                <input name="condition" value='<?php
                if (isset($_GET["condition"])) {
                    echo $_GET["condition"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Thương hiệu:</label>
            <div class="col-sm-7">
                <input name="manufacturerId" value='<?php
                if (isset($_GET["manufacturerId"])) {
                    echo $_GET["manufacturerId"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Model:</label>
            <div class="col-sm-7">
                <input name="modelId" value='<?php
                if (isset($_GET["modelId"])) {
                    echo $_GET["modelId"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Detail:</label>
            <div class="col-sm-7">
                <textarea name="detail"  class="form-control"><?php
                if (isset($_GET["detail"])) {
                    echo $_GET["detail"];
                }
                ?></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Giá gốc:</label>
            <div class="col-sm-7">
                <input name="startPrice" value='<?php
                if (isset($_GET["startPrice"])) {
                    echo $_GET["startPrice"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Giá bán:</label>
            <div class="col-sm-7">
                <input name="sellPrice" value='<?php
                if (isset($_GET["sellPrice"])) {
                    echo $_GET["sellPrice"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Bước giá:</label>
            <div class="col-sm-7">
                <input name="bidStep" value='<?php
                if (isset($_GET["bidStep"])) {
                    echo $_GET["bidStep"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Cân nặng:</label>
            <div class="col-sm-7">
                <input name="weight" value='<?php
                if (isset($_GET["weight"])) {
                    echo $_GET["weight"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Hình thức vận chuyển:</label>
            <div class="col-sm-7">
                <input name="shipmentType" value='<?php
                if (isset($_GET["shipmentType"])) {
                    echo $_GET["shipmentType"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Phí vận chuyển:</label>
            <div class="col-sm-7">
                <input name="shipmentPrice" value='<?php
                if (isset($_GET["shipmentPrice"])) {
                    echo $_GET["shipmentPrice"];
                }
                ?>' type="text" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Thời gian bắt đầu:</label>
            <div class="col-sm-7">
                <input name="startTime" value='<?php
                if (isset($_GET["startTime"])) {
                    echo $_GET["startTime"];
                }
                ?>' type="text" class="form-control" placeholder="Nhập timeStamp" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Thời gian kết thúc:</label>
            <div class="col-sm-7">
                <input name="endTime" value='<?php
                if (isset($_GET["endTime"])) {
                    echo $_GET["endTime"];
                }
                ?>' type="text" class="form-control"  placeholder="Nhập timeStamp" >
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
if (@@$_GET["func"] != null && @$_GET["func"] != "") {
    $func = @$_GET["func"];
    switch ($func) {
        case "additem":
            $obj->addItem(null);
            break;
        case "addimages":
            $obj->addImages(null);
            break;
        case "delimage":
            $obj->delImages(null);
            break;
        case "submitadd":
            $obj->submitAdd(null);
            break;
        case "delete":
            $obj->delItem(null);
            break;
        default:
            echo 'Chó đi lạc';
            break;
    }
}
?>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
<link rel="stylesheet" href="css/bootstrap.css.map" type="text/css"/>

<script src="http://sinhvienit.net/forum/skin/SinhVienIT.Net-IT_Pro-Ver4/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
    window.onload = checkSelect();
    function checkSelect() {
        var value = $('select[name=function]').val();
        switch (value) {
            case "addItem":
                $('form[name=addItem]').show();
                $('form[name=addImage]').hide();
                $('form[name=delImage]').hide();
                $('form[name=submitAdd]').hide();
                $('form[name=delItem]').hide();
                break;
            case "addImage":
                $('form[name=addItem]').hide();
                $('form[name=addImage]').show();
                $('form[name=delImage]').hide();
                $('form[name=submitAdd]').hide();
                $('form[name=delItem]').hide();
                break;
            case "submitAdd":
                $('form[name=addItem]').hide();
                $('form[name=addImage]').hide();
                $('form[name=delImage]').hide();
                $('form[name=submitAdd]').show();
                $('form[name=delItem]').hide();
                break;
            case 'delImage':
                $('form[name=addItem]').hide();
                $('form[name=addImage]').hide();
                $('form[name=delImage]').show();
                $('form[name=submitAdd]').hide();
                $('form[name=delItem]').hide();
                break;
            case 'delItem':
                $('form[name=addItem]').hide();
                $('form[name=addImage]').hide();
                $('form[name=delImage]').hide();
                $('form[name=submitAdd]').hide();
                $('form[name=delItem]').show();
                break;
            default:
                $('form[name=addItem]').hide();
                $('form[name=addImage]').hide();
                $('form[name=delImage]').hide();
                $('form[name=submitAdd]').hide();
                $('form[name=delItem]').hide();
                break;
        }
    }
    $('select[name=function]').change(function() {
        checkSelect();
    });
</script>