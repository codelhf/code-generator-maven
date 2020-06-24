{{if data.length == 0}}
<tr>
    <td colspan="10">
        <p class="pdt-50 pdb-50 fs-30 color-9">
            <img src="/baseop/nresource/pub-ui/images/alltable-nullimg1.png" alt="" />
            <span class="mgl-20">暂无查询结果</span>
        </P>
    </td>
</tr>
{{/if}}
{{each data as item index}}
<tr>
    <td>
        <label class="js-checkbox replace-checkbox">
            <input type="checkbox" name="checkboxs" value="{{item.id}}"/>
            <span></span>
        </label>
    </td>
    <td>{{item.id}}</td>
    <td>{{item.type}}</td>
    <td>{{item.targetId}}</td>
    <td>{{item.oldValue}}</td>
    <td>{{item.newValue}}</td>
    <td>{{item.operator}}</td>
    <td>{{item.operateTime}}</td>
    <td>{{item.operateIp}}</td>
    <td>{{item.status}}</td>
</tr>
{{/each}}
