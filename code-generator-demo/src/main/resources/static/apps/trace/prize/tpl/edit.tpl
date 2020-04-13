<!-- 新增和修改 -->
<div class="" id="edit_lottery_prize_div">
    <form enctype="multipart/form-data" id="edit_lottery_prize_form">
        <input name="id" type="hidden" value="{{id}}">
        <div class="fs-14 bgc-white pd-20">
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>adminId：</span>
                    <input type="text" id="adminId" name="adminId" value="{{adminId}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span>grade：</span>
                    <input type="text" id="grade" name="grade" value="{{grade}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>prize：</span>
                    <input type="text" id="prize" name="prize" value="{{prize}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span>serial：</span>
                    <input type="text" id="serial" name="serial" value="{{serial}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>stock：</span>
                    <input type="text" id="stock" name="stock" value="{{stock}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span>resetStock：</span>
                    <input type="text" id="resetStock" name="resetStock" value="{{resetStock}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>image：</span>
                    <input type="text" id="image" name="image" value="{{image}}" required="true"/>
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
        <a href="#" class="button-blue ok" id="save_edit_lottery_prize">确认</a>
        <a href="#" class="button-green cancel" id="cancel_edit_lottery_prize">取消</a>
    </div>
</div>
