<tr rel="<%= data.id %>">
    <td class="text-center">1</td>
    <td class="text-center">
        <input name="keyword" id="keyword" type="text" value="<%= data.keyword %>" onchange="footerkeyword.changeKeyword('<%= data.id %>', this.value);" class="form-control" />
    </td>
    <td><input name="url" id="_url" type="text" value="<%= data.url %>" onchange="footerkeyword.changeUrl('<%= data.id %>', this.value)" class="form-control" /></td>
    <td class="text-center">
        <input name="position" id="_position" type="text" value="<%= data.position %>" onchange="footerkeyword.changePosition('<%= data.id %>', this.value);" class="form-control" style="width: 50px; height: 30px; text-align: center;">
    </td>
    <td class="text-center">
        <a href="javascript:void(0);" onclick="footerkeyword.changeStatus('<%= data.id %>');" editStatus="<%= data.id %>">
            <% if(!data.active){%> 
            <img src="<%= staticUrl %>/cp/img/icon-disable.png" />
            <%}%>
            <% if(data.active){%> 
            <img src="<%= staticUrl %>/cp/img/icon-enable.png" />
            <%}%>
        </a>
    </td>
    <td class="text-center">
        <a href="javascript:void(0);" onclick="footerkeyword.changeCommon('<%= data.id %>');" editCommon="<%= data.id %>">
            <% if(!data.common){%> 
            <img src="<%= staticUrl %>/cp/img/icon-disable.png" />
            <%}%>
            <% if(data.common){%> 
            <img src="<%= staticUrl %>/cp/img/icon-enable.png" />
            <%}%>
        </a>
    </td>
    <td style="vertical-align: middle;" class="text-center">
        <div class="btn-group" id="615139734549">
            <button id="reviews" onclick="footerkeyword.del('<%= data.id %>');" class="btn btn-danger" style="width: 80px;"><span class="glyphicon glyphicon-remove"></span>Xóa</button>
        </div>
    </td>
</tr>