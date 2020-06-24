<!-- 新增和修改 -->
<div class="" id="edit_sys_log_div">
    <form enctype="multipart/form-data" id="edit_sys_log_form">
        <input name="id" type="hidden" value="{{id}}">
        <div class="fs-14 bgc-white pd-20">
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span><span style="color: red;width: 10px">*</span> type：</span>
                    <input type="text" id="type" name="type" value="{{type}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span><span style="color: red;width: 10px">*</span> target_id：</span>
                    <input type="text" id="targetId" name="targetId" value="{{targetId}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span><span style="color: red;width: 10px">*</span> old_value：</span>
                    <input type="text" id="oldValue" name="oldValue" value="{{oldValue}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span><span style="color: red;width: 10px">*</span> new_value：</span>
                    <input type="text" id="newValue" name="newValue" value="{{newValue}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span><span style="color: red;width: 10px">*</span> operator：</span>
                    <input type="text" id="operator" name="operator" value="{{operator}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span><span style="color: red;width: 10px">*</span> operate_time：</span>
                    <input type="text" id="operateTime" name="operateTime" value="{{operateTime}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span><span style="color: red;width: 10px">*</span> operate_ip：</span>
                    <input type="text" id="operateIp" name="operateIp" value="{{operateIp}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span><span style="color: red;width: 10px">*</span> status：</span>
                    <input type="text" id="status" name="status" value="{{status}}" required="true"/>
                </div>
            </div>
        </div>
    </form>
    <div class="pu-btn bottom-button pd-10 tc">
        <a href="#" class="button-blue ok" id="save_edit_sys_log">确认</a>
        <a href="#" class="button-green cancel" id="cancel_edit_sys_log">取消</a>
    </div>
</div>
