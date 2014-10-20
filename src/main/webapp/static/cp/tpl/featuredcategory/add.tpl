    
<form class="form-horizontal" id="form-categorys" role="form">
   
    <div class="form-group">
         <label class="control-label col-sm-4">Danh mục</label>
        <div class="col-sm-8">
            <select name="categoryId" id="cateC" class="form-control">
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Chọn template</label>
        <div class="col-sm-8">
            <select class="form-control" name="template" onchange="featuredcategory.changeTemplate(this.value)">
                <option value="0">Chọn</option>
                <option value="template1">Template số 1</option>
                <option value="template2">Template số 2</option>
                <option value="template3">Template số 3</option>
                <option value="template4">Template số 4</option>
                <option value="template5">Template số 5</option>
                <option value="template6">Template số 6</option>
            </select>
        </div>
    </div>
    <div class="col-sm-12" class="form-group">
        <div id="demoTem" ></div>
    </div>
    
</form>
