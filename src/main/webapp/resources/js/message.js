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

    return {
        add:add
    };

})();