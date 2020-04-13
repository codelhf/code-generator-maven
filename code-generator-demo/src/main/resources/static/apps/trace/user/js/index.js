require(['commonutil', 'ompage', 'pluspop'], function(ajaxUtil, page, pop) {
    var edit_lottery_user_dialog;
    // 默认加载
    $(function() {
        bindEvent(); //绑定点击事件
        resetForm(); //清空表单
        initUserList(1, true);//初始化
    });
    //页面的所有点击事件, 由于先于页面加载,使用$(document)才能生效
    function bindEvent() {
        //[查询]按钮的onclick事件
        $(document).on('click', '#btn_search', function() {
            initUserList(1, true);
        });
        //[重置]按钮的onclick事件
        $(document).on('click', '#btn_reset', function() {
            resetForm();
        });
        // 全选或全不选
        $(document).on('click','#checkboxAll', function(){
            var _this = $(this);
            if(_this.prop('checked')) {
                $("input[name='checkboxs']").each(function () {
                    _this.checked = true;
                    $(this).parent('.js-checkbox').addClass('checked');
                });
            } else {
                $("input[name='checkboxs']").each(function () {
                    _this.checked = false;
                    $(this).parent('.js-checkbox').addClass('checked');
                });
            }
        });
        // ----------------------------- 新增 & 修改 & 删除按钮----------------------------------------
        //新增
        $(document).on('click', '#add_lottery_user', function() {
            edit_lottery_user_dialog = $('#edit_lottery_user_div').ui_dialog({
                title: '新增',
                width: 800,
                height: 500
            }).showModal();
            require(['../tpl/edit.tpl'], function(editTpl) {
                $('#edit_lottery_user_div').html(editTpl({}));
            });
        });
        //修改
        $(document).on("click", "#edit_lottery_user", function() {
            var checked = $('input[name="checkboxs"]:checked');
            if (checked.length <= 0) {
                pop.warn(pop.msg.please_choose_one, function(){});
                return;
            }
            var entity_id = getUserId();
            if (entity_id === null) {
                pop.warn(pop.msg.please_choose_one, function(){});
                return;
            }
            edit_lottery_user_dialog = $('#edit_lottery_user_div').ui_dialog({
                title: '修改',
                width: 800,
                height: 500
            }).showModal();

            require(['../tpl/edit.tpl'], function(editTpl) {
                ajaxUtil.callrest("/mm/user/" + entity_id, function(result) {
                    if(result && result.code !== 'SUCCESS') {
                        pop.warn("加载数据失败：" + result.msg, function(){});
                    } else {
                        $('#edit_lottery_user_div').html(editTpl(result.data));
                    }
                });
            });
        });
        //[删除]按钮的onclick事件
        $(document).on('click', '#delete_lottery_user', function() {
            var checked = $('input[name="checkboxs"]:checked');
            if (checked.length <= 0) {
                pop.warn(pop.msg.please_choose_one, function(){});
                return;
            }
            var ids = [];
            for(var i = 0; i < checked.length; i++) {
                ids.push($(checked[i]).attr('value'));
            }
            ids = ids.join(',');
            ajaxUtil.delrest("/mm/user/" + ids, function(result) {
                if (result && result.code !== 'SUCCESS') {
                    pop.warn(pop.msg.delete_failure + "失败原因：" + result.msg, function(){});
                } else {
                    initUserList(1, true);
                }
            })
        });
        // 新增和修改界面（edit.html）保存按钮
        $(document).on("click", "#save_edit_lottery_user", function() {
            var edit_lottery_user_form = $('#edit_lottery_user_form');
            if (!edit_lottery_user_form.valid()) {
                return;
            }
            // 防止重复提交
            $(this).attr("disabled", "true");
            var edit_lottery_user_form_data = edit_lottery_user_form.serializeObject();
            if (!edit_lottery_user_form_data.id) {
                addUser(edit_lottery_user_form_data);
            } else {
                updateUser(edit_lottery_user_form_data);
            }
        });
        // 新增和修改界面（edit.html）取消按钮
        $(document).on("click", "#cancel_edit_lottery_user", function() {
            $("#edit_lottery_user_form").find("input").val("");
            edit_lottery_user_dialog.close();
        });
    }

    //清空查询字段
    function resetForm() {
        $(".form-ipt").val("");
    }
    // 数据列表
    function initUserList(current_page_no, first) {
        var queryParam = getQueryParam();
        queryParam.currentPage = current_page_no;
        queryParam.pageLimit = 10;
        require(['../tpl/list.tpl'], function(listTpl) {
            ajaxUtil.callrest("/mm/user", function(result) {
                if (result && result.code !== 'SUCCESS') {
                    pop.warn("加载数据列表失败：" + result.msg, function(){});
                    return;
                }
                $("#tbody_lottery_user").html(listTpl(result));
                var paging = {
                    "everyPage": result.data.pageNum,
                    "totalPage": result.data.total
                };
                if (first) {
                    page.init('page_lottery_user', paging, initUserList);
                }
            }, queryParam);
        });
    }

    //获取模糊查询数据
    function getQueryParam() {
        var queryParam = {
            condition: $.trim($("#fuzzy_search_input").val()),
            sort: 'updateTime-'
        };
        return queryParam;
    }

    //获取选择的对象id
    function getUserId() {
        var checkedBox =  $("input[name='checkboxs']:checked");
        if (checkedBox.length !== 1 ){
            pop.warn(pop.msg.please_choose_one, function(){});
            return null;
        }
        return checkedBox.first().attr('value');
    }

    //新增
    function addUser(edit_lottery_user_form_data) {
        ajaxUtil.postrest("/mm/user", function(result) {
            if(result && result.code === 'SUCCESS') {
                pop.warn("保存数据成功!", function(){
                    edit_lottery_user_dialog.close();
                    initUserList(1, true);
                    $("#save_edit_lottery_user").removeAttr("disabled");
                });
            } else {
                pop.warn(pop.msg.update_failure + "失败原因：" + result.msg, function(){
                    $("#save_edit_lottery_user").removeAttr("disabled");
                });
            }
        }, edit_lottery_user_form_data);
    }
    //修改
    function updateUser(edit_lottery_user_form_data) {
        ajaxUtil.putrest("/mm/user/" + edit_lottery_user_form_data.id, function(result) {
            if(result.code == 'SUCCESS') {
                pop.warn("更新数据成功！", function(){
                    edit_lottery_user_dialog.close();
                    initUserList(1, true);
                    $("#save_edit_lottery_user").removeAttr("disabled");
                });
            } else {
                pop.warn(pop.msg.update_failure + "失败原因：" + result.msg, function(){
                    $("#save_edit_lottery_user").removeAttr("disabled");
                });
            }
        }, edit_lottery_user_form_data);
    }
});