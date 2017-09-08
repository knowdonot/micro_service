var menu = {
    baseUrl: "/admin/tool",
    entity: "file",
    tableId: "menuTable",
    toolbarId: "toolbar",
    unique: "id",
    order: "asc",
    currentItem: {},
    code: "id",
    parentCode: "parentId",
    rootValue: -1,
    explandColumn: 1
};
menu.columns = function () {
    return [
        {
            field: 'selectItem',
            radio: true
        }, {
            field: 'name',
            title: '菜单'
        }, {
            field: 'permission',
            title: '权限'
        }, {
            field: 'href',
            title: 'url'
        }];
};
//得到查询的参数
menu.queryParams = function () {
    var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        name: $("#type").val()
    };
    return temp;
};

menu.clickRow = function () {
    element.refresh();
};
menu.refresh = function () {
    menu.table.bootstrapTreeTable("refresh");
};


layui.use(['form', 'layedit', 'laydate'], function () {
    menu.init();
    $('#' + menu.tableId + '>.treegrid-tbody>tr').click(function () {
        var rows = menu.table.bootstrapTreeTable('getSelections');
        menu.currentItem = rows[0];
      
    });
    var allMenu = null;
    var editIndex;
    var layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        form = layui.form(),
        layedit = layui.layedit;
    $.get(menu.baseUrl + '/all', null, function (data) {
		allMenu = eval('(' + data + ')'); 
		})
    
    
    
//初始化页面上面的按钮事件
    $('#btn_query').on('click', function () {
        menu.table.bootstrapTreeTable('refresh', menu.queryParams());
    });
    var addBoxIndex = -1;
    $('#btn_add').on('click', function () {
        if (addBoxIndex !== -1)
            return;
        var rows = menu.table.bootstrapTreeTable('getSelections');
        var id = "-1";
        if (rows.length == 1) {
            id = rows[0].id;
        }
        //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
        $.get(menu.entity + '/edit', null, function (form) {
            addBoxIndex = layer.open({
                type: 1,
                title: '添加菜单',
                content: form,
                btn: ['保存', '取消'],
                shade: false,
                offset: ['20px', '20%'],
                area: ['600px', '400px'],
                maxmin: true,
                yes: function (index) {
                    layedit.sync(editIndex);
                    //触发表单的提交事件
                    $('form.layui-form').find('button[lay-filter=edit]').click();
                },
                full: function (elem) {
                    var win = window.top === window.self ? window : parent.window;
                    $(win).on('resize', function () {
                        var $this = $(this);
                        elem.width($this.width()).height($this.height()).css({
                            top: 0,
                            left: 0
                        });
                        elem.children('div.layui-layer-content').height($this.height() - 95);
                    });
                },
                success: function (layero, index) {
//                	$.get(menu.baseUrl + '/all', null, function (data) {
//                		allMenu = eval('(' + data + ')'); 
                		var form = layui.form();
                        editIndex = layedit.build('description_editor');
                        form.render();
                        for (var i = 0; i < allMenu.length; i++){
                        	layero.find('#parentId').append('<option value="' + allMenu[i].id + '" >' + allMenu[i].name + '</option>');
                        }
                        layero.find("select[name='parentId']").val(id);
                        form.render('select');
                        form.on('submit(edit)', function (data) {
                        	$.ajax({
                                url: menu.baseUrl,
                                type: "post",
                                data: data.field,
                                dataType: "json",
                                success: function (data) {
                                	if(data==1){
                                		 layerTips.msg('保存成功');
                                	}else{
                                		 layerTips.msg('保存错误');
                                	}
                                   
                                    layer.close(addBoxIndex);
                                    menu.refresh();
                                }
                            });
                        	 return false; 
                        })
                        
                        //这里可以写ajax方法提交表单
                       //阻止表单跳转。如果需要表单跳转，去掉这段即可。
//                    });
                },
                end: function () {
                	addBoxIndex = -1;
                }
            });
        });
    });
    
    
    
    $('#btn_edit').on('click', function () {
        if (addBoxIndex !== -1)
            return;
        var rows = menu.table.bootstrapTreeTable('getSelections');
        if (menu.select(layerTips)) {
            var id = menu.currentItem.id;
            $.get(menu.baseUrl + '/' + id, null, function (data) {
                var result = eval('(' + data + ')') ;
                $.get(menu.entity + '/edit', null, function (form) {
                    addBoxIndex = layer.open({
                        type: 1,
                        title: '编辑菜单',
                        content: form,
                        btn: ['保存', '取消'],
                        shade: false,
                        offset: ['20px', '20%'],
                        area: ['600px', '400px'],
                        maxmin: true,
                        yes: function (index) {
                            layedit.sync(editIndex);
                            //触发表单的提交事件
                            $('form.layui-form').find('button[lay-filter=edit]').click();
                        },
                        full: function (elem) {
                            var win = window.top === window.self ? window : parent.window;
                            $(win).on('resize', function () {
                                var $this = $(this);
                                elem.width($this.width()).height($this.height()).css({
                                    top: 0,
                                    left: 0
                                });
                                elem.children('div.layui-layer-content').height($this.height() - 95);
                            });
                        },
                        success: function (layero, index) {
                            var form = layui.form();
                            setFromValues(layero, result);
                            //result.remark
                            layero.find('#description_editor').val("aa");
                            editIndex = layedit.build('description_editor');
                            for (var i = 0; i < allMenu.length; i++)
                                layero.find('#parentId').append('<option value="' + allMenu[i].id + '" >' + allMenu[i].name + '</option>');
                            layero.find("select[name='parentId']").val(result['parentId']);
                            form.render('select');
                            layero.find(":input[name='permission']").attr("disabled", "");
                            form.render();
                            console.info(result);
                            console.info(result.id);
                            form.on('submit(edit)', function (data) {
                                $.ajax({
                                    url: menu.baseUrl + '/' + result.id,
                                    type: 'put',
                                    data: data.field,
                                    dataType: "json",
                                    success: function () {
                                        layerTips.msg('更新成功');
                                        layer.close(addBoxIndex);
                                        menu.refresh();
                                    }

                                });
                                //这里可以写ajax方法提交表单
                                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                            });
                        },
                        end: function () {
                            addBoxIndex = -1;
                        }
                    });
                });
            });
        }
    });
    $('#btn_del').on('click', function () {
        if (menu.select(layerTips)) {
            var id = menu.currentItem.id;
            layer.confirm('确定删除数据吗？', null, function (index) {
                $.ajax({
                    url: menu.baseUrl + "/" + id,
                    type: "DELETE",
                    success: function (data) {
                        console.log(data);
                        if (data=="1") {
                            layerTips.msg("移除成功！");
                            location.reload();
                        } else {
                            layerTips.msg("移除失败！")
                            location.reload();
                        }
                    }
                });
                layer.close(index);
            });
        }
    });
})
;
