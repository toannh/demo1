<form class="form-horizontal" id="add-note" >
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr class="text-center">
            <td><label class="control-label"><b>Mã thẻ</b></label></td>
            <td>
                <p><%= (typeof data !== 'undefined' ? data.cardCode : '')%></p>
            </td>
        </tr>
        <tr class="text-center">
            <td><label class="control-label"><b>Số Serial</b></label></td>
            <td>
                <p><%= (typeof data !== 'undefined' ?  data.cardSerial : '' ) %></p>
            </td>
        </tr>
        <tr class="text-center">
            <td><label class="control-label"><b>Loại thẻ</b></label></td>
            <td>
                <p><%= (typeof data !== 'undefined' ? data.cardType : '')  %></p>
            </td>
        </tr>
        <tr class="text-center">
            <td><label class="control-label"><b>Mệnh giá</b></label></td>
            <td>
                <script>
                    var num = <%= (typeof data !== 'undefined' ? data.cardValue : '') %>;
                    var changenum = num.toLocaleString();
                    $('#showVal').text(changenum + " VNĐ");
                </script>
                <p id='showVal'></p>
            </td>
        </tr>
        <tr class="text-center">
            <td><label class="control-label"><b>Ngày hết hạn</b></label></td>
            <td>
                <p><%= data.expiryDate %></p>
            </td>
        </tr>
    </table>
</form>