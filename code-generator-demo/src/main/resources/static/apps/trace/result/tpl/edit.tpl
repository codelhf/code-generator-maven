<!-- 新增和修改 -->
<div class="" id="edit_lottery_result_div">
    <form enctype="multipart/form-data" id="edit_lottery_result_form">
        <input name="id" type="hidden" value="{{id}}">
        <div class="fs-14 bgc-white pd-20">
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>username：</span>
                    <input type="text" id="username" name="username" value="{{username}}" required="true"/>
                </div>
                <div class="col-6 select-ty">
                    <span>prize：</span>
                    <input type="text" id="prize" name="prize" value="{{prize}}" required="true"/>
                </div>
            </div>
            <div class="clear mgb-20">
                <div class="col-6 select-ty">
                    <span>grade：</span>
                    <input type="text" id="grade" name="grade" value="{{grade}}" required="true"/>
                </div>
        </div>
    </form>
    <div class="pu-btn bottom-button pd-10 tc">
        <a href="#" class="button-blue ok" id="save_edit_lottery_result">确认</a>
        <a href="#" class="button-green cancel" id="cancel_edit_lottery_result">取消</a>
    </div>
</div>
