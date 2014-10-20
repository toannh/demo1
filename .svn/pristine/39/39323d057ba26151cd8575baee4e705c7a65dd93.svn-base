<tr class="draggable droppable <%=data.id%>" for="<%=data.id%>">
<input type="hidden" name="id" style="width: 25px" value="<%=data.id%>"/>
<td class="text-center"><input type="text" name="position" class="form-control pro" for="<%=data.id%>" style="width: 45px" value="<%=data.position%>"/></td>
<td class="text-center"><input name="name" type="text" class="form-control col-sm-8 pro" for="<%=data.id%>" value="<%=data.name%>"/></td>
<td class="text-center">
    <select name="type" class="form-control pro t<%=data.id%>" for="<%=data.id%>" >
        <option value="0">Vui lòng chọn</option>
        <option value="1" <%= data.type==1?'selected':'' %> >Chọn một</option>
        <option value="2" <%= data.type==2?'selected':'' %> >Chọn và định nghĩa</option>
        <option value="3" <%= data.type==3?'selected':'' %> >Chọn nhiều</option>
        <option value="4" <%= data.type==4?'selected':'' %> >Tự định nghĩa</option>
    </select>
</td>
<td class="text-center">
    <select name="operator" class="form-control pro o<%=data.id%>" for="<%=data.id%>" >
        <option value="0">Chọn</option>
        <option value="1" <%= data.operator==1?'selected':'' %> >=</option>
        <option value="2" <%= data.operator==2?'selected':'' %> >≤</option>
        <option value="3" <%= data.operator==3?'selected':'' %> >≥</option>
    </select>
</td>
<td class="text-center">
    <label >
        <input style="text-align: center" class="pro" for="<%= data.id %>" name="filter" type="checkbox" value="<%= data.filter %>" <%= data.filter?"checked='checked'":'' %> >
    </label>
</td>
<td class="text-center">
    <div class="btn-group" style="vertical-align: text-top;width: 77px">
        <button type="button" class="btn btn-xs btn-primary" style="width:45px" onclick="category.viewPropertyValue('<%=data.id%>')">Giá trị</button>
        <button type="button" class="btn btn-xs btn-danger" onclick="category.removeProperty('<%=data.id%>')">Xóa</button>
    </div>
</td>
</tr>