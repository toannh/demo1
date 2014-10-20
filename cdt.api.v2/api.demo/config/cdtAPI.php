<?php

include_once '../entity/Item.php';
include_once '../entity/Orders.php';
include_once '../entity/Model.php';
include_once '../entity/Promotion.php';
error_reporting(-1);
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
        $param = new ItemGet();
        $param->pageIndex = 0;
        $param->pageSize = 20;
        $result = $this->callPost($task, $param);
        print_r($result);
    }

    public function postItem($param) {
        $task = "item/post.api";
        $param = new ItemPost();
        $param->name = "Sản phẩm test API hahahhhhhaaaaaa";
        $param->sourceUrl = "http://test.net";
        $param->images = array();
        $param->images[] = 'http://www.1top.vn/images-static/1000-1000-/1top/products-36df067866df0734a34eec7d2455f990.jpeg';

        $param->categoryId = "999";
        $param->listingType = 1;
        $param->sellPrice = 6000;
        $param->quantity = 6;
        $param->bidStep = 1000;
        $param->condition = 3;
        $param->sku = "";
        $param->startTime = 1396928557000;
        $param->endTime = 1399520557000;
        $param->detail = "Đây là detail :v :v";

        $result = $this->callPost($task, $param);
        print_r($result);
    }
    
    public function deleteItem($param) {
        $task = "item/delete.api";
        $param = new ItemGet();
        $param->sourceUrl = "http://test.net";
        $result = $this->callPost($task, $param);
        print_r($result);
    }
    

    public function getShopCategory($param) {
        $task = "cate/shopcate.api";
        $result = $this->callPost($task, $param);
        print_r($result);
    }

    public function getCDTCategory($param) {
        $task = "cate/cdtcate.api";
        $param["cateId"] = 0;
        $result = $this->callPost($task, $param);
        var_dump($result);
    }

    public function listOrder($param) {
        $task = "order/list.api";
        $param = new Orders();
        $param->pageIndex = 0;
        $param->pageSize = 20;
        $result = $this->callPost($task, $param);
        print_r($result);
    }
    public function updateOrder($param) {
        $task = "order/update.api";
        $param = new Orders();
        $param->code = "710206481759";
        $param->paymentStatus = 3;
        //$param->formPayment = 2;
        //$param->refundStatus = 1;
        $result = $this->callPost($task, $param);
        print_r($result);
    }

    public function listModel($param) {
        $task = "model/list.api";
        $param = new Model();
        $param->manufacturerId = 54;
        $param->categoryId = 999;
        $param->size = 20;
        $param->page = 0;
        $result = $this->callPost($task, $param);
        print_r($result);
    }

    public function listManufacturer($param) {
        $task = "manufacturer/list.api";
        $param = new Manufacturer();
        $param->categoryId = 999;
        $param->pageSize = 20;
        $param->pageIndex = 0;
        $result = $this->callPost($task, $param);
        print_r($result);
    }
    public function listPromotion($param) {
        $task = "promotion/list.api";
        $param = new Promotion();
        $param->type = 1;
        $param->page = 0;
        $result = $this->callPost($task, $param);
        print_r($result);
    }
    public function endPromotion($param) {
        $task = "promotion/stop.api";
        $param = new Promotion();
//        $param->pId = "819118815475";
        $param->pId = "720334967216";
        $result = $this->callPost($task, $param);
        print_r($result);
    }
    public function createPromotion($param) {
        $task = "promotion/stop.api";
        $param = new Promotion();
        $param->name = "test khuyến mại";
        $param->startTime = 1396414628980;
        $param->endTime = 1397019428980;
        $param->type = 1;
        $param->target = 2;
        
        $promotionItem = new PromotionItem();
        $promotionItem ->itemId = "596601483126";
        $promotionItem->discountPrice = 20000;
        
        $promotionItem1 = new PromotionItem();
        $promotionItem1 ->itemId = "195438136357";
        $promotionItem1->discountPercent = 10;
        
        $param->items = array();
        $param->items[] = $promotionItem;
        $param->items[] = $promotionItem1;
        
        $result = $this->callPost($task, $param);
        print_r($result);
    }

}

$obj = new CdtAPI();
if ($_GET["func"] != null && $_GET["func"] != "") {
    $func = $_GET["func"];
    switch ($func) {
        case "listitem":
            $obj->listItem(null);
            break;
        case "postitem":
            $obj->postItem(null);
            break;
        case "deleteitem":
            $obj->deleteItem(null);
            break;
        case "getshopcategory":
            $obj->getShopCategory(null);
            break;
        case "getcdtcategory":
            $obj->getCDTCategory(null);
            break;
        case "listorders":
            $obj->listOrder(null);
            break;
        case "updateorder":
            $obj->updateOrder(null);
            break;
        case "listmodels":
            $obj->listModel(null);
            break;
        case "listmanufacturer":
            $obj->listManufacturer(null);
            break;
        case "listpromotion":
            $obj->listPromotion(null);
            break;
        case "endpromotion":
            $obj->endPromotion(null);
            break;
        case "createpromotion":
            $obj->createPromotion(null);
            break;
        default:
            echo 'bò đi lạc';
            break;
    }
}
?>