<?php
class Promotion{
    public $pId;
    public $name;
    public $startTime;
    public $endTime;
    public $type;
    public $target;
    public $minOrderPrice;
    public $page;
    
}
class PromotionItem{
    public $itemId;
    public $discountPrice;
    public $discountPercent;
}
class PromotionCategory{
    public $categoryId;
    public $discountPrice;
    public $discountPercent;
}

?>
