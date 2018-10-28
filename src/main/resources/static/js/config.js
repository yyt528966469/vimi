var resList=null;
function getRes(res_name) {

    if(!resList){
        var count=false;
        $.ajax({
            url:"resources/getRes",
            type:"post",
            data:{},
            dataType:"json",
            async:false,
            success:function(data){
                resList=data.resList;
                for(var i=0;i<resList.length;i++){
                    if(resList[i].res_name==res_name){
                        return count=true;
                    }
                }
            }
        })
        return count;
    }else {
        for(var i=0;i<resList.length;i++){
            if(resList[i].res_name==res_name){
                return true;
            }
        }
    }

    return false;

    
}