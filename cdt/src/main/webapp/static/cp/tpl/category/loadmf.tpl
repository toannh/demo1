
<div class="row">
    <input name="name" type="text" class="form-control" placeholder="Tên thương hiệu" onkeyup="category.searchmf(this, '<%= objName %>');" />
</div>
<div class="row">
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr class="success">
            <th class="text-center">Mã</th>
            <th class="text-center" >Thương hiệu</th>
            <th class="text-center" >Chọn</th>
        </tr>   
        <tbody class="loadmf" >
            <tr>
                <td colspan="3" class="text-center text-danger">Không tìm thấy thương hiệu</td>
            </tr>   
        </tbody>
    </table>
</div>
