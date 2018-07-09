function UserCookie_other(){
    if($.cookie("my")!=null){
        var cookie_arr =  $.cookie("my").split("|");
        userName = URLdecode(decodeURI(cookie_arr[1]));
        userId = cookie_arr[0];
        isLogin = true;
    }
}
UserCookie_other();
var ajax_url = 'https://interface.dmzj.com/';
var location_host = window.location.host;
var domain_s = '';
//是否订阅
function isSubscribe(subId,sub_type){
    if(isLogin){
        var url = ajax_url+"api/subscribe/checkSubscribe";
        T.ajaxJsonp(url,{sub_id:subId,uid:userId,sub_type:sub_type}, function (data) {
            if(data.data==1){
                $("#subscribe_id").val("已订阅");
                $("#subscribe_id_mh").html("已订阅");
                $("#subscribe_id_mh").css("width","130px").css("text-align","center").css("padding-right","0");
                $("#subscribe_id").attr("onmouseover","btn_over(this)").attr("onmouseout","btn_out(this)");
                $("#subscribe_id_mh").attr("onmouseover","btn_over(this)").attr("onmouseout","btn_out(this)");
                $("#subscribe_id").attr("onclick","subscribe_remove("+subId+","+sub_type+",this)");
                $("#subscribe_id_mh").attr("onclick","subscribe_remove("+subId+","+sub_type+",this)");
            }
        })
    }
}


//是否关注
function isFocus(author_id,author){
    if(isLogin){
        var url = ajax_url+"api/focus/judgeIsFocused";
        $.ajax({
            type: 'get',
            url: url,
            cache: true,
            dataType: 'jsonp',
            data:"uid="+userId+"&author_id="+author_id ,
            timeout: 30000,
            success: function (json) {
                if(json.data==true){
                    $("#focus_id").html("已关注");
                    $("#focus_id").attr("onclick","delFocus("+author_id+",\'"+author+"\',this)")
                }
            }
        });
    }
}

$(function(){
    $("#focus_id").hover(function(){
        if($("#focus_id").html()=="已关注"){
            $("#focus_id").css("width","67px").css("text-align","center").css("padding-right",0)
            $("#focus_id").html("取消关注");
        }
    },function(){
        if($("#focus_id").html()=="取消关注"){
            $("#focus_id").html("已关注");
        }
    })
})

function btn_over(obj){
    $(obj).val("取消订阅");
    $(obj).html("取消订阅");
}
function btn_out(obj){
    $(obj).val("已订阅");
    $(obj).html("已订阅");
}
//添加订阅
function other_subscribe(subId,sub_type,obj) {
    if(!isLogin){
        alert("请登录");
        return false
    }
    var url = ajax_url+"api/subscribe/add";
    T.ajaxJsonp(url,{sub_id:subId,uid:userId,sub_type:sub_type}, function (data) {
        if(data.result==1000){
            window_lay(0,data.page_data);
            $(obj).val("已订阅");
            $(obj).html("已订阅");
            if($(obj).attr("id")=="subscribe_id_mh"){
                $(obj).css("width","130px").css("text-align","center").css("padding-right","0");
            }
            $(obj).attr("onmouseover","btn_over(this)").attr("onmouseout","btn_out(this)")
            $(obj).attr("onclick","subscribe_remove("+subId+","+sub_type+",this)")
        }else{
            window_lay(1,data.page_data);
        }
    })
}

//取消订阅
function subscribe_remove(subId,sub_type,obj){
    if(!isLogin){
        alert("请登录");
        return false
    }
    var url = ajax_url+"api/subscribe/del";
    T.ajaxJsonp(url,{sub_id:subId,uid:userId,sub_type:sub_type}, function (data) {
        if(data.result==1000){
            window_lay(2,data.page_data);
            if($(obj).attr("id")=="subscribe_id_mh"){
                $(obj).css("width","110px").css("text-align","right").css("padding-right","20px");
                $(obj).html("<span></span>订阅收藏");
            }else{
                $(obj).val("添加订阅");
                $(obj).html("添加订阅");

            }
            $(obj).removeAttr("onmouseover");
            $(obj).removeAttr("onmouseout");
            $("#subscribe_id_mh").removeAttr("onmouseover");
            $("#subscribe_id_mh").removeAttr("onmouseout");
            $(obj).attr("onclick","other_subscribe("+subId+","+sub_type+",this)")
        }else{
            alert(data.msg)
        }
    })
}

//全部订阅
function allsubscribe(){
    if(!isLogin){
        alert("请登录");
        return false
    }
    var json = [];
    var hidden_len = $(".ajaxParams").length;
    for(var i=0; i<hidden_len; i++){
        var ajax_val = $(".ajaxParams").eq(i).val();
        var comic_id = ajax_val.split("-")[0];
        var sub_type = ajax_val.split("-")[1];
        var item_obj = {};
        item_obj[comic_id] = sub_type;
        json.push(item_obj)
    }
    var jsonData = JSON.stringify(json);
    var url = ajax_url+"api/subscribe/addMulity";
    T.ajaxJsonp(url,{uid:userId,jsonData:jsonData}, function (data) {
        if(data.result==1000){
            alert(data.msg);
            var dataArrt = $.parseJSON(jsonData);
            for(var j=0; j<dataArrt.length; j++){
                var sub_id = $(".ajaxParams").eq(j).val().split("-")[0];
                $(".addSubBtn").eq(j).html("取消订阅");
                $(".addSubBtn").eq(j).attr("onclick","subscribe_remove("+sub_id+",this)");
            }
        }
    })
}

//关注
function addFocus(author_id,author,obj) {
    if(!isLogin){
        alert("请登录");
        return false
    }
    if(userId==author_id){
        alert("自己不要关注自己!");
        return false
    }
    var url = ajax_url+"/api/focus/addFocus";
    T.ajaxJsonp(url,{uid:userId,author_id:author_id,author:author}, function (data) {
        if(data.result==1000){
            if($(obj).attr("id")!="not_change"){
                $(obj).html("已关注");
                $(obj).attr("onmouseover","$(this).html('取消关注')");
                $(obj).attr("onmouseout","$(this).html('已关注')");
                $(obj).attr("onclick","delFocus("+author_id+",\'"+author+"\',this)");
            }else{
                alert("关注成功")
                location.reload();
            }
        }else{
            alert(data.msg);
        }
    })
}
//取消关注
function delFocus(author_id,author,obj) {
    if(!isLogin){
        alert("请登录");
        return false
    }
    var url = ajax_url+"/api/focus/delFocus";
    T.ajaxJsonp(url,{uid:userId,author_id:author_id,author:author}, function (data) {
        if(data.result==1000){
            if($(obj).attr("id")=="focus_id"){
                $(obj).html("<span></span>关注");
            }else{
                $(obj).html("添加关注");
                $(obj).removeAttr("onmouseover");
                $(obj).removeAttr("onmouseout");
            }
            $(obj).attr("onclick","addFocus("+author_id+",\'"+author+"\',this)");
        }
    })
}

$(function(){
    $(".check").live("click",function(){
        if($(this).attr("checked")){
            $(this).attr("checked",true).change();
        }else{
            $(this).attr("checked",false).change();
        }
    })
});


//批量关注
function delFocusMulity(){
    if(!isLogin){
        alert("请登录");
        return false
    }
    if(confirm("确定要取消对这些漫友的关注吗？")){
        var json = [];
        var _checked = $(".check:checked");
        for(var i=0; i<_checked.length; i++){
            var ajax_val = _checked.eq(i).val();
            var comic_id = ajax_val.split("-")[0];
            var sub_type = ajax_val.split("-")[1];
            var item_obj = {};
            item_obj[comic_id] = sub_type;
            json.push(item_obj)
        }
        var jsonData = JSON.stringify(json);
        var url = ajax_url+"/api/focus/delFocusMulity";
        T.ajaxJsonp(url,{uid:userId,jsonData:jsonData}, function (data) {
            if(data.result==1000){
                alert("取消关注成功");
                location.reload();
            }
        })
    }
}

//页面引用css文件
var linkCss ='<link rel="stylesheet" href="https://static.dmzj.com/public/css/scribe_layer.css"/>';

//插入css文件
$("head").append(linkCss);

//弹层
function window_lay(result,name,msg){
    var html = '<div class="window_show"></div>';
        html += '<div class="point_wrap" id="scribe_window" style="display: block;">';
        html +='<div class="close_scri" onclick="close_window_s()"></div>';
        html +='<div class="point_wrap_head"><p>提示信息</p></div>';
        html +='<div class="point_wrap_con point_succes">';
    if(result==0){
        html +='<p class="dy_success">您已经成功订阅了"<a href="javascript:;" class="c">'+name+'</a>"</p>';
        html +='<p class="noti">如果该作品更新，系统将及时通知您。</p>';
    }else if(result==1){
        html +='<p class="dy_success" style="margin-bottom: 20px;">您已订阅过该作品了！</p>';
    }else{
        html +='<p class="dy_success" style="margin-bottom: 20px;">取消订阅成功！</p>';
    }
        html +='<p class="manag"><a href="https://i.dmzj.com/subscribe" target="_blank">';
        html +='<i>管理你的订阅</i></a></p><a href="javascript:;" class="su_btn_scri" onclick="close_window_s()">确定</a></div></div>';
    $("body").append(html)
}
function close_window_s(){
    $(".window_show").remove();
    $("#scribe_window").remove();
}


