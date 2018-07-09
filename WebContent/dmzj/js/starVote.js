/*星星投票*/
var star_arry = ["15","35","55","75","95"];//每个星星宽度
var data_span = '<span id="tip" class="span_tip"></span>';//提示框
var ul_html = '<li id="currentData" type_val="0"></li> ' +
    '<li><a href="javascript:;" style="cursor:default;">1</a></li> <li><a href="javascript:;" style="cursor:default;">2</a></li>' +
    ' <li><a href="javascript:;" style="cursor:default;">3</a></li> <li><a href="javascript:;" style="cursor:default;">4</a></li> ' +
    '<li><a href="javascript:;" style="cursor:default;">5</a></li>';

var star_module = (function () {
    return {
        show_star_data: function () {/*展示*/
            $.ajax({
                type: "get",
                url: I_URL+'/ajax/getScoreInfo',
                dataType : "jsonp",
                jsonp: 'callback',
                jsonpCallback:"success_jsonpCallback_201509221731",
                data: "comic_id="+comic_id,
                success: function (data) {
                    var data_json = $.parseJSON(data);
                    console.log(data_json);
                    var input_html = '<input type="hidden" value='+star_arry[data_json.score-1]+' class="inp_wid">';
                    if(data_json.score==null){
                        $("#currentData").css("width","0px");
                        $("#scorData").html(data_json.show_points);
                        if(data_json.total_ticket>=10){
                            $("#spanScore").show();
                            $("#peopData").html("("+data_json.total_ticket+"人评分)");
                        }else{
                            $("#spanScore").hide();
                        }
                    }else{
                        $("#starDes").html("您的评分:");
                        $(".pf_star").html(ul_html);
                        $("#currentData").css("width",star_arry[data_json.score-1]+"px");
                        $("#scorData").html(data_json.show_points);
                        if(data_json.total_ticket>10){
                            $("#spanScore").show();
                            $("#peopData").html("("+data_json.total_ticket+"人评分)");
                        }else{
                            $("#spanScore").hide();
                        }
                        $(".pf_star li a").attr("title","");
                        $(".comic_deCon_pf").append(input_html);
                        $(".comic_deCon_pf").append(data_span);
                        $("#tip").hide();
                        star_module.judge_val(data_json.score);
                    }
                }
            })
        },
        get_star_data: function (val) {/*获取及提交*/
            $.ajax({
                type: "get",
                url: I_URL + '/ajax/ComicScore',
                dataType: "jsonp",
                jsonp: 'callback',
                jsonpCallback: "success_jsonpCallback_201509221732",
                data: "comic_id=" + comic_id + "&comic_score=" + val,
                success: function (data) {
                    var data_json = $.parseJSON(data);
                    if (data_json.code == 1000) {
                        $(".point_wrap").html('<div class="close"></div><div class="point_wrap_head"><p>提示信息</p></div><div class="point_wrap_con  point_udl"><p class="dy_success">感谢您的评分</p><a href="javascript:;" class="qd_btn" >确定</a></div>');
                        $(".point_wrap").show();
                        $(".show").show();
                        $("#starDes").html("您的评分:");
                        $("#scorData").html(data_json.show_points);
                        if (data_json.total_ticket >= 10) {
                            $("#spanScore").show();
                            $("#peopData").html("(" + data_json.total_ticket + "人评分)");
                        } else {
                            $("#spanScore").hide();
                        }
                        $("#currentData").attr("type_val", "1");
                        $(".pf_star").html(ul_html);
                        $("#currentData").css("width", star_arry[val - 1] + "px");
                        $(".comic_deCon_pf").append(data_span);
                        $("#tip").hide();
                        star_module.judge_val(val);
                    } else {
                        var stat_wid = $(".inp_wid").val();
                        $(".pf_star").html(ul_html);
                        $("#currentData").css("width", stat_wid + "px");
                        $(".point_wrap").html('<div class="close"></div><div class="point_wrap_head"><p>提示信息</p></div><div class="point_wrap_con  point_udl"><p class="dy_success">' + data_json.msg + '</p><a href="javascript:;" class="qd_btn" >确定</a></div>');
                        $(".point_wrap").show();
                        $(".show").show();
                    }
                }
            })
        },
        judge_val:function(data){/*提示语*/
            if(data==1){
                $("#tip").text("1分,很差");
            }else if(data==2){
                $("#tip").text("2分,有点弱");
            }else if(data==3){
                $("#tip").text("3分,一般");
            }else if(data==4){
                $("#tip").text("4分,不错");
            }else if(data==5){
                $("#tip").text("5分,神作");
            }
            $(".pf_star").hover(function(){
                $("#tip").show();
            },function(){
                $("#tip").hide();
            });
        }
    };
})();

/*初始化*/
star_module.show_star_data();

/*点击评分*/
$(".pf_star a").click(function(){
    var num = $(this).html();
    var type_val = $("#currentData").attr("type_val");
    if(!isLogin){
        $("#login_id").click();
    }else{
        if(type_val==0){
            star_module.get_star_data(num);
        }else if(type_val==1){
            return false;
        }
    }
});


