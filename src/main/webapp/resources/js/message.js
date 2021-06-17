console.log("Message Module......");
var messageService = (function (){

    function add(message, callback, error){
        console.log("new message ................ ");

        $.ajax({
            type:'post',
            url : '/group/ajax/new',
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

    // function remove(group_id,callback,error){
    //     $.ajax({
    //         type : 'delete',
    //         url : '/group/ajax/message/'+group_id,
    //         data : JSON.stringify(delete),
    //         contentType : "application/json; charset=utf-8",
    //         success:function (deleteResult,status,xhr){
    //             if(callback){
    //                 callback(deleteResult);
    //             }
    //         },
    //         error:function (xhr,status,er){
    //             if(error){
    //                 error(er);
    //             }
    //         }
    //     });
    // }

    return {
        add:add,
        //remove:remove
    };

})();