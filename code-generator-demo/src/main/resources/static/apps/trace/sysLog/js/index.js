define(['commonutil', 'ompage', 'pluspop'], function(ajaxUtil, page, pop) {
    var edit_sys_log_dialog;
    // 全局变量
    var globalData = {};
    // 默认加载
    var init = function() {
        bindEvent(); //绑定点击事件
        resetForm(); //清空表单
        initSysLogList(1, true);//初始化
    };

    //页面的所有点击事件, 由于先于页面加载,使用$(document)才能生效
    function bindEvent() {
        //[查询]按钮的onclick事件
        $(document).on('click', '#btn_search', function() {
            initSysLogList(1, true);
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
                    $(this).parent('.js-checkbox').removeClass('checked');
                });
            }
        });
        // ----------------------------- 新增 & 修改 & 删除按钮----------------------------------------
        //新增
        $(document).on('click', '#add_sys_log', function() {
            require(['../tpl/edit.tpl'], function(editTpl) {
                $('#edit_sys_log_div').html(editTpl({}));
                edit_sys_log_dialog = $('#edit_sys_log_div').ui_dialog({
                    title: '新增',
                    width: 800,
                    height: 500
                }).showModal();
            });
        });
        //修改
        $(document).on("click", "#edit_sys_log", function() {
            var checked = $('input[name="checkboxs"]:checked');
            if (checked.length <= 0) {
                pop.warn(pop.msg.please_choose_one, function(){});
                return;
            }
            var entity_id = getSysLogId();
            if (entity_id === null) {
                pop.warn(pop.msg.please_choose_one, function(){});
                return;
            }

            require(['../tpl/edit.tpl'], function(editTpl) {
                ajaxUtil.callrest("/mm/sysLog/" + entity_id, function(result) {
                    if(result && result.code !== 'SUCCESS') {
                        pop.warn(pop.msg.failure + result.msg, function(){});
                        return;
                    }
                    $('#edit_sys_log_div').html(editTpl(result.data));
                    edit_sys_log_dialog = $('#edit_sys_log_div').ui_dialog({
                        title: '修改',
                        width: 800,
                        height: 500
                    }).showModal();
                });
            });
        });
        //[删除]按钮的onclick事件
        $(document).on('click', '#delete_sys_log', function() {
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
            ajaxUtil.delrest("/mm/sysLog/" + ids, function(result) {
                if (result && result.code !== 'SUCCESS') {
                    pop.warn(pop.msg.delete_failure + result.msg, function(){});
                } else {
                    initSysLogList(1, false);
                }
            })
        });
        // 新增和修改界面（edit.html）保存按钮
        $(document).on("click", "#save_edit_sys_log", function() {
            var edit_sys_log_form = $('#edit_sys_log_form');
            if (!edit_sys_log_form.valid()) {
                pop.warn(pop.msg.failure, function(){});
                return;
            }
            // 防止重复提交
            $(this).attr("disabled", "true");
            var edit_sys_log_form_data = edit_sys_log_form.serializeObject();
            if (!edit_sys_log_form_data.id) {
                addSysLog(edit_sys_log_form_data);
            } else {
                updateSysLog(edit_sys_log_form_data);
            }
        });
        // 新增和修改界面（edit.html）取消按钮
        $(document).on("click", "#cancel_edit_sys_log", function() {
            $("#edit_sys_log_form").find("input").val("");
            edit_sys_log_dialog.close();
        });
    }

    //清空查询字段
    function resetForm() {
        $(".form-ipt").val("");
    }
    // 数据列表
    function initSysLogList(current_page_no, first) {
        var queryParam = getQueryParam();
        queryParam.currentPage = current_page_no;
        queryParam.pageLimit = 10;
        require(['../tpl/list.tpl'], function(listTpl) {
            ajaxUtil.callrest("/mm/sysLog", function(result) {
                if (result && result.code !== 'SUCCESS') {
                    pop.warn("加载数据列表失败：" + result.msg, function(){});
                    return;
                }
                $("#tbody_sys_log").html(listTpl(result));
                var paging = {
                    "everyPage": result.everyPage,
                    "totalPage": result.totalCount
                };
                if (first) {
                    page.init('page_sys_log', paging, initSysLogList);
                }
            }, queryParam);
        });
    }

    //获取模糊查询参数
    function getQueryParam() {
        globalData.condition = $.trim($("#fuzzy_search_input").val());
        globalData.sort = 'updateTime-';
        return globalData;
    }

    //获取选择的对象id
    function getSysLogId() {
        var checkedBox =  $("input[name='checkboxs']:checked");
        if (checkedBox.length !== 1 ){
            pop.warn(pop.msg.please_choose_one, function(){});
            return null;
        }
        return checkedBox.first().attr('value');
    }

    //新增
    function addSysLog(edit_sys_log_form_data) {
        ajaxUtil.postrest("/mm/sysLog", function(result) {
            if(result && result.code !== 'SUCCESS') {
                pop.warn(pop.msg.add_failure + result.msg, function(){
                    $("#save_edit_sys_log").removeAttr("disabled");
                });
            } else {
                pop.warn("保存数据成功!", function(){
                    edit_sys_log_dialog.close();
                    initSysLogList(1, false);
                    $("#save_edit_sys_log").removeAttr("disabled");
                });
            }
        }, edit_sys_log_form_data);
    }
    //修改
    function updateSysLog(edit_sys_log_form_data) {
        ajaxUtil.putrest("/mm/sysLog/" + edit_sys_log_form_data.id, function(result) {
            if(result && result.code !== 'SUCCESS') {
                pop.warn(pop.msg.update_failure + result.msg, function(){
                    $("#save_edit_sys_log").removeAttr("disabled");
                });
            } else {
                pop.warn("更新数据成功！", function(){
                    edit_sys_log_dialog.close();
                    initSysLogList(1, false);
                    $("#save_edit_sys_log").removeAttr("disabled");
                });
            }
        }, edit_sys_log_form_data);
    }

    // 初始化(放在最下面return时使用)
    init();
});