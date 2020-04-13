<!-- 新增和修改 -->
<div class="" id="edit_lottery_user_div">
    <form enctype="multipart/form-data" id="edit_lottery_user_form">
        <input name="id" type="hidden" value="{{id}}">
        <div class="fs-14 bgc-white pd-20">
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>adminId：</span>
                    <input type="text" id="adminId" name="adminId" value="{{adminId}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span>prizeId：</span>
                    <input type="text" id="prizeId" name="prizeId" value="{{prizeId}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>headImg：</span>
                    <input type="text" id="headImg" name="headImg" value="{{headImg}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span>username：</span>
                    <input type="text" id="username" name="username" value="{{username}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>description：</span>
                    <input type="text" id="description" name="description" value="{{description}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span>createTime：</span>
                    <input type="text" id="createTime" name="createTime" value="{{createTime}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>updateTime：</span>
                    <input type="text" id="updateTime" name="updateTime" value="{{updateTime}}" required="true"/>
                </div>
        </div>
    </form>
    <div class="pu-btn bottom-button pd-10 tc">
        <a href="#" class="button-blue ok" id="save_edit_lottery_user">确认</a>
        <a href="#" class="button-green cancel" id="cancel_edit_lottery_user">取消</a>
    </div>
</div>
