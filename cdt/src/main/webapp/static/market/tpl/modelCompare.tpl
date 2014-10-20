<div class="compare-table">
    <table class="table">
        <thead>
            <tr rel='head' >
                <th for='model' ></th>
                <th <%= "for='model_" + modelId + "'" %> ><div class="compare-upload"></div></th>
        <th><div class="compare-upload"></div></th>
        <th>
        <div class="compare-upload"></div>
        <div class="compare-search">
            <input onfocus="modelCompare.searchModel(this);" onkeyup="modelCompare.searchModel(this);" type="text" class="text" placeholder="Nhập tên sản phẩm để so sánh" />
            <a class="cs-search"><span class="glyphicon glyphicon-search"></span></a>
            <div class="compare-autosearch" rel='autosearch' style="overflow-y: auto; max-height: 400px;" ></div>
        </div>
        </th>
        </tr>
        </thead>
        <tbody>
            <% $.each(properties, function(){ %>
            <tr for="c_<%= this.id %>" rel='body' >
                <td for='model' ><b><%= this.name %>:</b></td>
                <td <%= "for='model_" + modelId + "'" %> ></td>
                <td></td>
                <td></td>
            </tr>
            <% }); %>
        </tbody>
    </table>
</div><!-- compare-table -->