$(".dread_btn").click(function(){
    if(!isLogin){
        $(".point_wrap").html(' <div class="close"></div><div class="point_wrap_head"><p>提示信息</p></div><div class="point_wrap_con  point_udl"><p class="dy_success">你还没有登录，请登录后订阅</p><a href="javascript:;" class="qd_btn" >确定</a></div>');
        $(".point_wrap").show();
        $(".show").show();
        return false;
    }
    $.ajax({
        type: "POST",
        url: sub_url,
        data: "uid="+userId+"&comic_id="+comic_id+"&copyright="+copyright,
        success: function(msg){
            var msg = eval('('+msg+')');
            msg.subscribe_status=1;
            switch(msg.subscribe_status){
                case 0:
                    $(".point_wrap").html('<div class="close"></div><div class="point_wrap_head"><p>提示信息</p></div><div class="point_wrap_con  point_udl"><p class="dy_success">订阅失败，请重试</p><a href="javascript:;" class="qd_btn" >确定</a></div>');
                    $(".point_wrap").show();
                    $(".show").show();
                    break;
                case 1:
                    $(".point_wrap").html('<div class="close"></div><div class="point_wrap_head"><p>提示信息</p></div><div class="point_wrap_con point_succes"><p class="dy_success">您已经成功订阅了"<a href="javascript:;" class="c">'+comic_name+'</a>"</p><p class="noti">如果该漫画更新，系统将及时通知您。</p><p class="manag"><a href="'+subscribe+'" target="_blank"><i>管理你的订阅</i></a></p><a href="javascript:;" class="qd_btn">确定</a></div>');
                    $(".point_wrap").show();
                    $(".show").show();
                    break;
                case 2:
                    $(".point_wrap").html('<div class="close"></div><div class="point_wrap_head"><p>提示信息</p></div><div class="point_wrap_con point_succed"><p class="dy_success">您的订阅已经超过500个</p><p class="manag"><a href="'+subscribe+'" target="_blank"><i>管理你的订阅</i></a></p><a href="javascript:;" class="qd_btn" >确定</a></div>');
                    $(".point_wrap").show();
                    $(".show").show();
                    break;

                default:
                    break;
            }
        }
    });
});

$(".close").live("click",function(){
    $(this).parent().hide();
    $(".show").hide();

});

$(".qd_btn").live("click",function(e){
    e.preventDefault();
    $(this).parent().parent().hide();
    $(".show").hide();

});



//投票功能
    var comicUrl ="https://interface.dmzj.com";//接口连接
    var ballot_id;
    var options;
    //ajax请求
    var voteAjax = function (url,data,callback,success_jsonpCallback) {
        $.ajax({
            type: 'get',
            url: url,
            cache: false,
            dataType: 'jsonp',
            jsonpCallback: success_jsonpCallback,
            data: data,
            timeout: 30000,
            success: function (json) {
                callback(json);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                callback({"succ": false, "msg": "未知错误"});
            }
        });
    };
    //获取投票
    var getVote = function(){
        var url = comicUrl+'/api/ballot/getBallot';
        var data = {'comicId': obj_id,'uid':author_id };
        var callback = function(json){
            var data = json.data;
            if(json.result == 1000){
            	ballot_id = data.id;
            	options = JSON.stringify(data.option);
                $('.intro_l_vote').show();
                $('.vote_Num em').html(data.votes);
                var html = '';
                html += '<div class="intro_l_vote_main"><h3>'+data.title+'</h3><form id="voteForm"> <div id="voteRadios">';
                switch(data.is_multiple){
                    case 1:
                        if(data.option){
                            for( var i in data.option){
                                html += '<span class="voteRadio"><input type="radio" name="choice" id="'+i+'" value="'+i+'" />';
                                html += '<label for="'+i+'">'+data.option[i]+'</label></span>';
                            }
                        }
                        break;
                    case 2:
                        if(data.option){
                            for(var i in data.option){
                                html += '<span class="voteRadio"><input type="checkbox" name="choice" id="'+i+'" value="'+i+'" />';
                                html += '<label for="'+i+'">'+data.option[i]+'</label></span>';
                            }
                        }
                        break;
                }
                
                html += '</div> </form>';
                html += '<span id="SubvoteForm" onclick="submitVote()">投票</span> <span class="moreVote" onclick="voteView()">查看结果>></span> </div>';
                $('.intro_l_vote').append(html);
            }
        };
        voteAjax(url,data,callback,'getVote_success_jsonpCallback');
    };
    //提交投票
    var submitVote = function(){
        if($.cookie("my")!=='' && $.cookie("my") !=null){
            var myinfo = $.cookie("my").split("|");
            var user_id = myinfo[0];
            if(myinfo[0] == '' || myinfo[1] == '' ){
                $.cookie('my',null, {expires: -1, path: '/', domain: '.dmzj.com', secure: true});
                alert("请重新登陆！");
                myinfo = '';
            }else{
                if($('#voteForm').serialize() == ''){
                    alert('请选择您要投票的选项！');
                    return false
                }else{
                    var choice_arry = $('#voteForm').serialize();
                    var choice_val = choice_arry.split('&').toString().replace(/choice=/g,"");
                    var choice_arryNews = '['+choice_val+']';
                    var url = comicUrl+'/api/ballot/addBallot';
                    var data = {'user_id':user_id,'ballot_id':ballot_id,options:choice_arryNews};
                    var callbackTo = function(json){
                        if(json.result == 1000){
                            $('.voteRadio input[name=choice]').each(function(){
                                $(this).prop("checked", false);
                            });
                            var voteNum = $('.vote_Num em');
                            voteNum.html(parseInt(voteNum.html())+1);
                            alert('投票成功！');
                            return false
                        }else if(json.result == 1101){
                            $('.voteRadio input[name=choice]').each(function(){
                                $(this).prop("checked", false);
                            });
                            alert('您已经投过票了，请不要重复投票喔！');
                            return false
                        }else{
                            alert('投票失败！');
                            return false
                        }
                    };
                    voteAjax(url,data,callbackTo,'subVote_success_jsonpCallback');
                }
            }
        }else{
            alert('请登录后再投票！')
        }

    };
    //查看投票详情
    var voteView = function(){
        var url = comicUrl+'/api/ballot/votesInfo';
        var data = {'ballot_id':ballot_id,'options':options};
        var callback = function(json){
            var jsonNum = json.num;
            var options = json.options;
            var html = '';
            html += '<div class="window_show"></div>';
            html += '<div class="vote_wrap"><div class="close_vote" onclick="closeView()"></div>';
            html += '<div class="vote_til">'+json.title+'</div><div class="vote_main">';
            var optN = 1;
            for( var i in options){
                var perec = Math.round((options[i].option_num/jsonNum)*1000)/10;//计算所占百分比
                if(isNaN(perec)){perec = 0;};
                html += '<div class="optionView"><span class="optName">'+options[i].option_title+'</span><span class="optImg"><div  class="opt'+optN+'" style="width:'+perec+'%;">';
                html += '</div></span><span class="optNum">'+options[i].option_num+' &nbsp;&nbsp;( '+perec+'% )'+'</span></div>';
                optN++;
            }
            html += '</div></div>';
            $('body').append(html);
        };
        voteAjax(url,data,callback,'seeVote_success_jsonpCallback');
    };


    var closeView = function(){
        $('.window_show,.vote_wrap').remove();
    };

$(function(){
    getVote();
});

