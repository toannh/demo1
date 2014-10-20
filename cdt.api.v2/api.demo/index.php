<?php
error_reporting(-1);
include_once '/entity/Item.php';
include_once '/entity/Orders.php';
include_once '/entity/Model.php';
include_once '/entity/Manufacturer.php';
include_once '/entity/Promotion.php';
include_once '/entity/Category.php';

class CdtAPI {

    public $apiUrl = "http://localhost:8080/api/";
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

    public function listItem($param) {
        $task = "item/list.api";
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function deleteItem($param) {
        $task = "item/delete.api";
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function getShopCategory($param) {
        $task = "shop/list.api";
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function getCDTCategory($param) {
        $task = "cate/getbycategory.api";
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function listOrder($param) {
        $task = "order/list.api";
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function updateOrder($param) {
        $task = "order/update.api";
        $result = $this->callPost($task, $param);
         echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function listModel($param) {
        $task = "model/list.api";
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

    public function listManufacturer($param) {
        $task = "manufacturer/list.api";
        $result = $this->callPost($task, $param);
        echo "<pre>";
        print_r($result);
        echo "</pre>";
    }

}
?>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
<link rel="stylesheet" href="css/bootstrap.css.map" type="text/css"/>
<form method='GET' class="form-group">
    <table id='table1' class="table table-striped table-bordered table-responsive" style="margin-top: 10px; width: 600px">
        <tr>
            <td style="width: 300px">Tên API:</td>
            <td>
                <select name="func" class="form-control">
                    <option value="listmodels">Model</option>
                    <option value="listmanufacturer">Manufacturer</option>
                    <option value="listorders">Order</option>
                    <option value="updateorder">Update Order</option>
                    <option value="getcdtcategory">Category</option>
                    <option value="getshopcategory">Category Shop</option>
                    <option value="listitem">Item</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>pageIndex:</td>
            <td><input type='text' value='<?php
                if (isset($_GET["pageIndex"])) {
                    echo $_GET["pageIndex"];
                }
                ?>' name='pageIndex' size='20' class="form-control"></td>
        </tr>
        <tr>
            <td>pageSize:</td>
            <td><input type='text' value='<?php
                if (isset($_GET["pageSize"])) {
                    echo $_GET["pageSize"];
                }
                ?>' name='pageSize' size='20' class="form-control"></td>
        </tr>

    </table>
    <input type='button' value='Thêm trường' name='addField' size='20' class="btn btn-default">
    <input type='submit' value='Submit' class="btn btn-primary"> <input type='reset' value='Xóa' name='B2' class="btn btn-default">
</form>
<form method="POST" action="?act=delete">
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px; width: 600px">
        <tr>
            <td style="width: 300px">Tên API:</td>
            <td>
                <select name="funcx" class="form-control">
                    <option value="deleteitem">Delete Item</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>sellerSku:</td>
            <td><input type='text' value='<?php
                if (isset($_POST["sellerSku"])) {
                    echo $_POST["sellerSku"];
                }
                ?>' name='sellerSku' size='20' class="form-control">
                <input type='submit' value='Submit' class="btn btn-primary">
                <input type='reset' value='Xóa' name='B2' class="btn btn-default">
            </td>
        </tr>
    </table>


</form>
<?php
$obj = new CdtAPI();
if (isset($_GET["func"]) && $_GET["func"] != null && $_GET["func"] != "") {
    $func = $_GET["func"];
    switch ($func) {
        case "listitem":
            $param = new ItemGet();
            if (isset($_GET["pageIndex"])) {
                $param->pageIndex = $_GET["pageIndex"];
            }
            if (isset($_GET["pageSize"])) {
                $param->pageSize = $_GET["pageSize"];
            }
            if (isset($_GET["id"])) {
                $param->id = $_GET["id"];
            }
            if (isset($_GET["keyword"])) {
                $param->keyword = $_GET["keyword"];
            }
            if (isset($_GET["categoryId"])) {
                $param->categoryIds = array($_GET["categoryId"]);
            }
            if (isset($_GET["shopCategoryId"])) {
                $param->shopCategoryId = $_GET["shopCategoryId"];
            }
            if (isset($_GET["listingType"])) {
                $param->listingType = $_GET["listingType"];
            }
            if (isset($_GET["condition"])) {
                $param->condition = $_GET["condition"];
            }
            if (isset($_GET["onlinePayment"])) {
                $param->onlinePayment = $_GET["onlinePayment"];
            }
            if (isset($_GET["priceFrom"])) {
                $param->priceFrom = $_GET["priceFrom"];
            }
            if (isset($_GET["priceTo"])) {
                $param->priceTo = $_GET["priceTo"];
            }
            if (isset($_GET["orderBy"])) {
                $param->orderBy = $_GET["orderBy"];
            }
            if (isset($_GET["status"])) {
                $param->status = $_GET["status"];
            }
            $obj->listItem($param);
            break;
        case "deleteitem":
            break;
        case "getshopcategory":
            $param = new Category();
            $obj->getShopCategory($param);
            break;
        case "getcdtcategory":
            $param = new Category();
            if (isset($_GET["cateId"])) {
                $param->cateId = $_GET["cateId"];
            }
            $obj->getCDTCategory($param);
            break;
        case "listorders":
            $param = new Orders();
            if (isset($_GET["pageIndex"])) {
                $param->pageIndex = $_GET["pageIndex"];
            }
            if (isset($_GET["pageSize"])) {
                $param->pageSize = $_GET["pageSize"];
            }
            if (isset($_GET["id"])) {
                $param->id = $_GET["id"];
            }
            if (isset($_GET["createTimeFrom"])) {
                $param->createTimeFrom = $_GET["createTimeFrom"];
            }
            if (isset($_GET["createTimeTo"])) {
                $param->createTimeTo = $_GET["createTimeTo"];
            }
            if (isset($_GET["paidTimeFrom"])) {
                $param->paidTimeFrom = $_GET["paidTimeFrom"];
            }
            if (isset($_GET["paidTimeTo"])) {
                $param->paidTimeTo = $_GET["paidTimeTo"];
            }
            if (isset($_GET["itemId"])) {
                $param->itemId = $_GET["itemId"];
            }
            if (isset($_GET["paymentStatus"])) {
                $param->paymentStatus = $_GET["paymentStatus"];
            }
            if (isset($_GET["shipmentStatusSearch"])) {
                $param->shipmentStatusSearch = $_GET["shipmentStatusSearch"];
            }
            if (isset($_GET["nlId"])) {
                $param->nlId = $_GET["nlId"];
            }
            if (isset($_GET["scId"])) {
                $param->scId = $_GET["scId"];
            }
            if (isset($_GET["sortOrderBy"])) {
                $param->sortOrderBy = $_GET["sortOrderBy"];
            }
            if (isset($_GET["buyPhone"])) {
                $param->buyPhone = $_GET["buyPhone"];
            }
            if (isset($_GET["buyEmail"])) {
                $param->buyEmail = $_GET["buyEmail"];
            }
            if (isset($_GET["buyerCityId"])) {
                $param->buyerCityId = $_GET["buyerCityId"];
            }
            if (isset($_GET["buyerDistrictId"])) {
                $param->buyerDistrictId = $_GET["buyerDistrictId"];
            }
            if (isset($_GET["receiverPhone"])) {
                $param->receiverPhone = $_GET["receiverPhone"];
            }
            if (isset($_GET["receiverEmail"])) {
                $param->receiverEmail = $_GET["receiverEmail"];
            }
            if (isset($_GET["receiverCityId"])) {
                $param->receiverCityId = $_GET["receiverCityId"];
            }
            if (isset($_GET["receiverDistrictId"])) {
                $param->receiverDistrictId = $_GET["receiverDistrictId"];
            }
            $obj->listOrder($param);
            break;
        case "updateorder":
            $param = new OrderUpdate();
            if (isset($_GET["id"])) {
                $param->id = $_GET["id"];
            }
            if (isset($_GET["markSellerPayment"])) {
                $param->markSellerPayment = $_GET["markSellerPayment"];
            }
            if (isset($_GET["markSellerShipment"])) {
                $param->markSellerShipment = $_GET["markSellerShipment"];
            }
            if (isset($_GET["refundStatus"])) {
                $param->refundStatus = $_GET["refundStatus"];
            }
             $obj->updateOrder($param);
            break;
        case "listmodels":
            $param = new Model();
            if (isset($_GET["pageIndex"])) {
                $param->pageIndex = $_GET["pageIndex"];
            }
            if (isset($_GET["pageSize"])) {
                $param->pageSize = $_GET["pageSize"];
            }
            if (isset($_GET["manufacturerId"])) {
                $param->manufacturerId = $_GET["manufacturerId"];
            }
            if (isset($_GET["categoryId"])) {
                $param->categoryId = $_GET["categoryId"];
            }
            $obj->listModel($param);
            break;
        case "listmanufacturer":
            $param = new Manufacturer();
            if (isset($_GET["pageIndex"])) {
                $param->pageIndex = $_GET["pageIndex"];
            }
            if (isset($_GET["pageSize"])) {
                $param->pageSize = $_GET["pageSize"];
            }
            if (isset($_GET["categoryId"])) {
                $param->categoryId = $_GET["categoryId"];
            }
            $obj->listManufacturer($param);
            break;
        default:
            echo 'API ChợĐiệnTử';
            break;
    }
}
if (isset($_GET['act']) && $_GET['act'] == "delete") {
    $param = new ItemGet();
    if (isset($_POST["sellerSku"])) {
        $param->sellerSku = $_POST["sellerSku"];
    }
    $obj->deleteItem($param);
}
?>
<script src="http://sinhvienit.net/forum/skin/SinhVienIT.Net-IT_Pro-Ver4/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
    var htmlTR = '<tr>' +
            '<td><input type="text" class="changeField form-control" onchange="changeText(this)" size="20"></td>' +
            '<td><input type="text" class="changeValue form-control" value="" size="20"><a href="javascript:;" style="font-size: 12px;" class="removeTR" onclick="removeTR(this);">Remove</a></td> ' +
            '</tr>';
    $('input[name=addField]').click(function() {
        $('#table1').append(htmlTR);
    });
    function changeText(el) {
        var htmlTR = '<td><input type="text" class="changeField form-control" onchange="changeText(this)" size="20" value="' + $(el).val() + '"></td>' +
                '<td><input type="text" class="changeValue form-control" name="' + $(el).val() + '" value="" size="20"><a href="javascript:;" style="font-size: 12px;" class="removeTR" onclick="removeTR(this);">Remove</a></td>';
        $(el).parents('tr').html(htmlTR);
    }
    function removeTR(el) {
        $(el).parents('tr').remove();
    }
    var parseQueryString = function() {
        var str = window.location.search;
        var objURL = {};

        str.replace(
                new RegExp("([^?=&]+)(=([^&]*))?", "g"),
                function($0, $1, $2, $3) {
                    objURL[ $1 ] = $3;
                }
        );
        return objURL;
    };
    var objX = parseQueryString();
    $.each(objX, function(key, val) {
        if (key !== 'func' && key !== 'pageIndex' && key !== 'pageSize') {
            var htmlTR = '<tr>' +
                    '<td><input type="text" class="changeField form-control" onchange="changeText(this)" value="' + key + '" size="20"></td>' +
                    '<td><input type="text" class="changeValue form-control" name="' + key + '" value="' + val + '" size="20"><a href="javascript:;" style="font-size: 12px;" class="removeTR" onclick="removeTR(this);">Remove</a></td>' +
                    '</tr>';
            $('#table1').append(htmlTR);
        }
        if (key === 'func') {
            $('select[name=func]').val(val);
        }
    });
</script>