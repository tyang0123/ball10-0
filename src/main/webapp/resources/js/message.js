console.log("Message Module......");
var messageService = (function (){

    function add(message, callback, error){
        console.log("new message ................ ");
        $.ajax({
            type:'post',
            url : '/read/ajax/new',
            data : JSON.stringify(message),
            contentType : "application/json; charset=utf-8",
            success : function(result,status,xhr){
                if (callback){
                    callback(result);
                }
            },
            error:function(xhr,status,er){
                if(error){
                    error(er);
                }
            }
        })
    }

    function getList(param,callback,error){
        var group_id = param.group_id;
        $.getJSON("/read/ajax/list"+group_id,
            function (data){
            if(callback){
                callback(data);
            }
            }).fail(function (xhr,status,err){
                if(error){
                    error();
                }
        });
    }

    function remove(group_message_id,callback,error){
        $.ajax({
            type : 'delete',
            url : '/read/ajax/delete/'+group_message_id,
            success:function (deleteResult,status,xhr){
                if(callback){
                    callback(deleteResult);
                }
            },
            error:function (xhr,status,er){
                if(error){
                    error(er);
                }
            }
        });
    }

    return {
        add:add,
        getList:getList,
        remove:remove
    };

})();