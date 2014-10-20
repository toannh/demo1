<form class="form-horizontal" id="aliasForm">
    <input type="hidden" name="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>"/>

    <%
    for(i=1;i <= 4;i++){
    var topic = null;
    for(j = 0; j< 4; j++){
    if(typeof(data) !== 'undefined' &&  data.topics!=null && typeof(data.topics) !== 'undefined' 
    && typeof(data.topics[j]) !== 'undefined' && i == data.topics[j].position){
    topic = data.topics[j];
    break;
    }
    }
    %>
    <div class="form-group">
        <label class="control-label col-sm-2">Topic <%=i%>:</label>
        <div class="col-sm-10">
            <input name="title<%=i%>" value="<%= topic!== null? topic.title:''%>" type="text" class="form-control" placeholder="Tiêu đề của topic, không quá 70 ký tự"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">&nbsp;</label>
        <div class="col-sm-5">
            <input name="url<%=i%>" value="<%=(topic!== null)? topic.url:''%>" type="text" class="form-control" placeholder="Url topic"/>
        </div>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="text" class="form-control" <%= 'id="photoCover' + i+ '"' %>  value="<%=(topic!== null)? topic.image:''%>" placeholder="đường dẫn ảnh topic">
                       <a onclick="$('#lefile<%=i%>').click();" class="btn btn-default input-group-addon">Chọn file</a>                    
            </div>
            <input type="file" style="display:none" <%= 'id="lefile' + i + '"' %> name="image<%=i%>" for='lefile' pos='<%=i%>' />
        </div>
    </div>
    <% }%>

</form>