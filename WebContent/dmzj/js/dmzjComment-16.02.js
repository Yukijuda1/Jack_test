var Deal = location.protocol;

//页面引用css文件
var linkCss ='<link rel="stylesheet" href="'+Deal+'//static.dmzj.com/module/css/conmment_new-15.10.css"/>';

//接口连接
var comment_Url = Deal+"//interface.dmzj.com";

//新接口链接
var comment_Url_n = Deal + "//comment.dmzj.com";

//链接地址
var link_url = Deal+"//i.dmzj.com";

//图片地址
var Img_url = Deal+'//images.dmzj.com/';

//表情连接
var urlImg = Deal+"//user.dmzj.com";

//插入css文件
$("head").append(linkCss);


var is_show=1;
var upload_imgArr = [];
var cb_url = Deal+"//"+window.location.host+"/images";
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
var tel_result = 0;


var telZc = '<div id="box_zc" style="display: block"></div><!-- 温馨提示-发表评论没有绑定手机 --><div class="Choose_way box_show" style="display: block"><div class="pwdno_bound_tit"><p class="account_tit_font" style="font-size: 18px;float: left;">温馨提示</p><span class="account_close"></span></div><div class="account_btm_cont"><p class="hint_noPhoneTied" style="padding: 0 32px;">根据《互联网跟帖评论服务管理规定》的要求，需要绑定手机号码后才能发表评论，请先绑定手机号码</p><button class="hint_setBtnStyle">立刻设置</button></div></div>';


/*phpURLdecode*/
function URLdecode(str) {
    var ret = "";
    for(var i=0;i<str.length;i++) {

        var chr = str.charAt(i);
        if(chr == "+") {
            ret += " ";
        }else if(chr=="%") {
            var asc = str.substring(i+1,i+3);
            if(parseInt("0x"+asc)>0x7f) {
                ret += decodeURI("%"+ str.substring(i+1,i+9));
                i += 8;
            }else {
                ret += String.fromCharCode(parseInt("0x"+asc));
                i += 2;
            }
        }else {
            ret += chr;
        }
    }
    return ret;
}
var commentHtml = '<iframe id="upload_target" name="upload_target" src="about:blank" style="display: none"></iframe>';
    commentHtml +='<input type="hidden" id="hiddenInput" value="" />';
    commentHtml +='<div class="commentBox"><h2><span class="comment_num"><em class="total">0</em>条评论</span><span class="t_c">网友</span>评论</h2>';
    commentHtml +='<div class="textareawrap"><div class="textareaDiv">';
    commentHtml +='<textarea name="textfield" class="textare" onkeyup="text_s(this,0,0,0)"></textarea></div>';
    commentHtml +='<div class="func_area">';
    //表情
    commentHtml +='<div class="ficon_face"><a class="ficon_faceBtn"></a><div class="faceBox">';
    commentHtml +='<div class="fBar"><ul>';
    commentHtml +='<li class="cur" id="sort1">默认</li><li id="sort2">小幺鸡</li><li id="sort3">小胖鸟</li><li id="sort4">小黑猫</li>';
    commentHtml +='</ul><a class="close"></a></div><ul class="face"></ul></div></div>';
    commentHtml +='<div class="ficon_img" onclick="upload_pic.show_uploadImg(this)"></div><div class="ficon_swtop" style="display: none"></div><div class="ficon_topic" style="display: none"></div>';
    commentHtml +='<div class="loginName"><a href="#" class="name">八云</a>|<a href="#" class="out">退出</a></div>';
    commentHtml +='<a class="SubmitBtn" onclick="comment_news.add(this,0,0,0)">发表评论</a><p class="num_S_txt">您还可以输入<span>0</span>字</p></div></div>';
    commentHtml +='<ul class="comment_tab">';
    commentHtml +='<li class="cur">最新评论</li>';
    commentHtml +='<li onclick="comment_news.hotCommentClick()">热门评论</li>';
    commentHtml +='<li onclick="comment_news.goodCommentClick()">精华评论</li>';
    if(commment_type == 4 && is_Original == 1){
        commentHtml +='<li onclick="comment_news.authorCommentClick()">只看作者</li>';
    }
    commentHtml +='</ul><div class="comment_con"><div id="top_status_4"></div><div id="top_status_2"></div>';
    commentHtml +='<div class="comment_con_tab con_select"><div id="commentAll"></div>';
    commentHtml +='<span class="loading">评论正在加载中，请稍后~</span><div class="page" id="page_new"></div></div>';
    commentHtml +='<div class="comment_con_tab"><div id="commentHot"></div>';
    commentHtml +='<span class="loading">评论正在加载中，请稍后~</span><div class="page" id="page_hot"></div></div>';
    commentHtml +='<div class="comment_con_tab"><div id="commentGood"></div>';
    commentHtml +='<span class="loading">评论正在加载中，请稍后~</span><div class="page" id="page_good"></div></div>';
    if(commment_type == 4 && is_Original == 1){
        commentHtml +='<div class="comment_con_tab"><div id="commentAuthor"></div>';
        commentHtml +='<span class="loading">评论正在加载中，请稍后~</span><div class="page" id="page_author"></div>';
    }
    commentHtml +='</div>';
$("#Comment").html(commentHtml);


//主评论html
function allComment_Html(json,divName,cur_page,index){
    if(!json){
        return false
    }
    

    var is_good = (json.is_goods==0||json.is_goods==undefined)?'加精':'取消加精';
    var icon_good = (json.is_goods==0||json.is_goods==undefined)?'':comment_news.icon_jing;
    var sex = (json.sex==1)?comment_news.icon_boy:comment_news.icon_girl;
    var author_uid = authoruid ==json.sender_uid;
    var is_sender = author_uid?comment_news.icon_author:'';
    var is_author = author_uid?' author':'';
    if(commment_type == 6){
        var is_author_icon = author_uid?comment_news.photo_author_1:'';
    }else{
        var is_author_icon = author_uid?comment_news.photo_author:'';
    }
    var is_author_bor = author_uid?'bor':'';
    var is_status = (json.top_status!=2)?'置顶':'取消置顶';
    if(json.top_status==4){
        is_status='';
    }
    var origin_comment_id = (json.origin_comment_id==0)?json.id:json.origin_comment_id;
    var floor_num = comment_news.getFloor(cur_page,comment_news.commentTotal);
    var status = '';
    if(json.top_status==2 && divName=='top_status_2'){

        status = '<span class="icon_zd"></span>';
    }
    if(json.top_status==4 && divName=='top_status_4'){

        status = '<span class="icon_gg"></span>';
    }
    var html ='<div class="comment_con_li autoHeight">';
        html +='<a name="'+comment_news.parentName+'_box_'+json.id+'"></a>';
        html +='<div class="photo '+is_author_bor+'"><a target="_blank" href="'+link_url+'/otherCenter/hisIndex?hisUid='+json.sender_uid+'"><img src="'+json.avatar_url+'" width="58" height="58" alt="" onerror="this.src=\'https://avatar.dmzj.com/default.png\'" width="58" height="58" /></a>'+is_author_icon+'</div>';
        html +='<div class="content_r autoHeight">'+icon_good;
        html +='<div class="info_bar">'+status;
        html +='<a target="_blank" href="'+link_url+'/otherCenter/hisIndex?hisUid='+json.sender_uid+'" target="_blank" class="userName '+is_author+'">'+json.nickname+'</a>'+sex+is_sender;

    if(divName=='top_status_4' || divName=='top_status_2'){
        html +='<span class="floor"></span></div>';
        html +='<div class="reply_content"></div>';
    }else{
        if(comment_news.click_type==1){
            comment_news.commentTotal+=1;
            html +='<span class="floor">['+comment_news.commentTotal+'楼 ]</span></div>';
        }else{
            html +='<span class="floor">['+parseInt(floor_num-index)+'楼 ]</span></div>';
        }
        html +='<div class="reply_content" id="'+comment_news.parentName+'_reply_'+json.id+'"></div>';
    }
    if(json.content!=undefined && json.content.length>350){
        html +='<p class="text"><span class="textCon hei">'+getImg(json.content)+'</span><a onclick="comment_news.openText(this)">展开全文>></a></p>';
    }else{
        html +='<p class="text"><span class="textCon">'+getImg(json.content)+'</span></p>';
    }
    if(json.upload_images!=undefined){
        html +=upload_pic.requestImgStr(json);
    }
    html +='<div class="btm_bar">';
    html +='<span class="time">'+new Date(parseInt(json.create_time) * 1000).Format("yyyy-MM-dd hh:mm:ss")+'</span>';
    if(divName=='top_status_4'){
        html +='';
    }else{
        html +='<a class="icon_hf" ' +
            'onclick="reply_html(this,'+json.sender_uid+','+json.id+','+origin_comment_id+')">回复</a>';
        html +='<a class="icon_ding"onclick="comment_news.agree(this,'+json.id+')">顶(<em>'+json.like_amount+'</em>)</a>';
        html +='<a class="handl good"  onclick="report_html('+json.id+','+json.sender_uid+')">举报</a>';
    }
    if(authoruid==comment_news.user_id && divName!='top_status_4' && commment_type == 4 && is_Original==1){
        if(json.is_goods==0){
            html +='<a target="_blank" class="handl good good_'+json.id+'" ' +
                'onclick="other_html(1,\''+URLdecode(json.nickname)+'\','+json.id+')">'+is_good+'</a>';
        }else{
            html +='<a target="_blank" class="handl good good_'+json.id+'" ' +
                'onclick="other_html(2,\''+URLdecode(json.nickname)+'\','+json.id+')">'+is_good+'</a>';
        }
        if(json.top_status==0){
            html +='<a target="_blank" class="handl top top_'+json.id+'" ' +
                'onclick="comment_news.is_top(3,\''+URLdecode(json.nickname)+'\','+json.id+')">'+is_status+'</a>';
        }else{
            html +='<a target="_blank" class="handl top top_'+json.id+'" ' +
                'onclick="other_html(4,\''+URLdecode(json.nickname)+'\','+json.id+')">'+is_status+'</a>';
        }
    }
    html +='</div></div></div>';
    if(comment_news.click_type == 1){
        $("#"+divName).prepend(html);
    }else{
        $("#"+divName).append(html);
    }
}

//子评论html
function child_html(json){

    var data = json;
    for(var j=0;j<data.masterComment.length;j++){
        var reply_html = '';
        var reply_data = data.masterComment[j];
        if(reply_data.is_passed==1){
            var icon_good = icon_good = (reply_data.is_goods==0||reply_data.is_goods==undefined)?'':comment_news.icon_jing;
        }else{
            var icon_good='';
        }

        var reply_sex = (data.masterComment[j].sex==1)?comment_news.icon_boy:comment_news.icon_girl;
        var reply_is_good = (reply_data.is_goods==0)?'加精':'取消加精';
        var reply_is_status = (reply_data.top_status!=2)?'置顶':'取消置顶';
        if(reply_data.top_status==4){
            reply_is_status='';
        }
        var origin_comment_id = (reply_data.origin_comment_id==0)?reply_data.id:reply_data.origin_comment_id;
        var is_passed =(reply_data.is_passed==2)?'此评论已被删除':getImg(reply_data.content);
        var id_pass_col = (reply_data.is_passed==2)?'del':'';
            reply_html += '<div class="comment_con_li autoHeight" id='+data.id+"_"+j+'">'+icon_good;
            reply_html +='<div class="info_bar"><a target="_blank" href="'+link_url+'/otherCenter/hisIndex?hisUid='+reply_data.sender_uid+'" class="userName">'+reply_data.nickname+'</a>';
            reply_html += reply_sex+'<span class="floor">'+parseInt(j+1)+'</span></div>';
            if(reply_data.content.length>350){
                reply_html +='<p class="text '+id_pass_col+'"><span class="textCon hei">'+is_passed+'</span><a onclick="comment_news.openText(this)">展开全文>></a></p>';
            }else{
                reply_html +='<p class="text '+id_pass_col+'"><span class="textCon">'+is_passed+'</span></p>';
            }

            if(reply_data.upload_images!=undefined){
                reply_html +=upload_pic.requestImgStr(data.masterComment[j]);
            }

            reply_html +='<div class="btm_bar">';
            reply_html +='<span class="time">'+new Date(parseInt(reply_data.create_time) * 1000).Format("yyyy-MM-dd hh:mm:ss")+'</span>';
        if(reply_data.is_passed==2){
            reply_html +=''
        }else{
            reply_html +='<a class="icon_hf" onclick="reply_html(this,'+reply_data.sender_uid+','+reply_data.id+','+origin_comment_id+')">回复</a>';
            reply_html +='<a class="icon_ding" onclick="comment_news.agree(this,'+reply_data.id+')">顶(<em>'+reply_data.like_amount+'</em>)</a>';
            reply_html +='<a class="handl h_1" onclick="report_html('+reply_data.id+','+reply_data.sender_uid+')">举报</a>';
            if(authoruid==comment_news.user_id && commment_type == 4 && is_Original==1){
                if(reply_data.is_goods==0){
                    reply_html +='<a target="_blank" class="handl h_1 good_'+reply_data.id+'" ' +
                        'onclick="other_html(1,\''+URLdecode(reply_data.nickname)+'\','+reply_data.id+')">'+reply_is_good+'</a>';
                }else{
                    reply_html +='<a target="_blank" class="handl h_1 h_1 good_'+reply_data.id+'" ' +
                        'onclick="other_html(2,\''+URLdecode(reply_data.nickname)+'\','+reply_data.id+')">'+reply_is_good+'</a>';
                }
                if(reply_data.top_status==0){
                    reply_html +='<a target="_blank" class="handl h_1 top_'+reply_data.id+'" ' +
                        'onclick="comment_news.is_top(3,\''+URLdecode(reply_data.nickname)+'\','+reply_data.id+')">'+reply_is_status+'</a>';
                }else{
                    reply_html +='<a target="_blank" class="handl h_1 top_'+reply_data.id+'" ' +
                        'onclick="other_html(4,\''+URLdecode(reply_data.nickname)+'\','+reply_data.id+')">'+reply_is_status+'</a>';
                }
            }
        }
        reply_html +='</div></div>';
        if(data.masterCommentNum>=4){
            if(j<=1){
                $("#"+comment_news.parentName+"_reply_"+data.id).show().append(reply_html)
            }
            if(j>=2 && j<data.masterCommentNum-2){
                if(j==2){
                    $("#"+comment_news.parentName+"_reply_"+data.id).append('<p class="mark" onclick="comment_news.showDiv('+data.id+')" id="'+comment_news.parentName+'_mark_'+data.id+'">显示隐藏次元</p>');
                    $("#"+comment_news.parentName+"_reply_"+data.id).append('<div id="'+comment_news.parentName+'_show_'+data.id+'" style="display:none"></div>')
                }
                $("#"+comment_news.parentName+"_show_"+data.id).append(reply_html);
            }
            if(j>=data.masterCommentNum-2){
                $("#"+comment_news.parentName+"_reply_"+data.id).append(reply_html);
            }
        }else{
            $("#"+comment_news.parentName+"_reply_"+data.id).show().append(reply_html);
        }
    }
    $(".reply_content>.comment_con_li:last").css("border-bottom",0);
}

//新子评论html
function child_html_n(json,comments,main_id){

    var data = json;
    for(var j=0;j<data.length;j++){
        var reply_html = '';
        var reply_index = data[j];
        var reply_data = comments[reply_index];
        if(!reply_data){
            reply_html += '<div class="comment_con_li autoHeight">' +
                //'<div class="info_bar">' +
                //'<span class="floor">'+parseInt(j+1)+'</span>' +
                //'</div>' +
                '<p class="text del">' +
                '<span class="textCon">此评论已被删除<span class="floor">'+parseInt(j+1)+'</span></span>' +
                '</p>' +
                //'<div class="btm_bar"></div>' +
                '</div>';
        }else{

            var icon_good = icon_good = (reply_data.is_goods==0||reply_data.is_goods==undefined)?'':comment_news.icon_jing;


            var reply_sex = (reply_data.sex==1)?comment_news.icon_boy:comment_news.icon_girl;
            var reply_is_good = (reply_data.is_goods==0)?'加精':'取消加精';
            var reply_is_status = (reply_data.top_status!=2)?'置顶':'取消置顶';
            if(reply_data.top_status==4){
                reply_is_status='';
            }
            var origin_comment_id = (reply_data.origin_comment_id==0)?reply_data.id:reply_data.origin_comment_id;
            var is_passed = getImg(reply_data.content);
            reply_html += '<div class="comment_con_li autoHeight" id='+reply_data.id+"_"+j+'">'+icon_good;
            reply_html +='<div class="info_bar"><a target="_blank" href="'+link_url+'/otherCenter/hisIndex?hisUid='+reply_data.sender_uid+'" class="userName">'+reply_data.nickname+'</a>';
            reply_html += reply_sex+'<span class="floor">'+parseInt(j+1)+'</span></div>';
            if(reply_data.content.length>350){
                reply_html +='<p class="text"><span class="textCon hei">'+is_passed+'</span><a onclick="comment_news.openText(this)">展开全文>></a></p>';
            }else{
                reply_html +='<p class="text"><span class="textCon">'+is_passed+'</span></p>';
            }

            if(reply_data.upload_images){
                var index = data[j];
                reply_html +=upload_pic.requestImgStr(comments[index]);
            }

            reply_html +='<div class="btm_bar">';
            reply_html +='<span class="time">'+new Date(parseInt(reply_data.create_time) * 1000).Format("yyyy-MM-dd hh:mm:ss")+'</span>';
            //if(reply_data.is_passed==2){
            //    reply_html +=''
            //}else{
            reply_html +='<a class="icon_hf" onclick="reply_html(this,'+reply_data.sender_uid+','+reply_data.id+','+origin_comment_id+')">回复</a>';
            reply_html +='<a class="icon_ding" onclick="comment_news.agree(this,'+reply_data.id+')">顶(<em>'+reply_data.like_amount+'</em>)</a>';
            reply_html +='<a class="handl h_1" onclick="report_html('+reply_data.id+','+reply_data.sender_uid+')">举报</a>';
            if(authoruid==comment_news.user_id && commment_type == 4 && is_Original==1){
                if(reply_data.is_goods==0){
                    reply_html +='<a target="_blank" class="handl h_1 good_'+reply_data.id+'" ' +
                        'onclick="other_html(1,\''+URLdecode(reply_data.nickname)+'\','+reply_data.id+')">'+reply_is_good+'</a>';
                }else{
                    reply_html +='<a target="_blank" class="handl h_1 h_1 good_'+reply_data.id+'" ' +
                        'onclick="other_html(2,\''+URLdecode(reply_data.nickname)+'\','+reply_data.id+')">'+reply_is_good+'</a>';
                }
                if(reply_data.top_status==0){
                    reply_html +='<a target="_blank" class="handl h_1 top_'+reply_data.id+'" ' +
                        'onclick="comment_news.is_top(3,\''+URLdecode(reply_data.nickname)+'\','+reply_data.id+')">'+reply_is_status+'</a>';
                }else{
                    reply_html +='<a target="_blank" class="handl h_1 top_'+reply_data.id+'" ' +
                        'onclick="other_html(4,\''+URLdecode(reply_data.nickname)+'\','+reply_data.id+')">'+reply_is_status+'</a>';
                }
            }
            //}
            reply_html +='</div></div>';

        }



        if(data.length>=4){
            if(j<=1){
                $("#"+comment_news.parentName+"_reply_"+main_id).show().append(reply_html)
            }
            if(j>=2 && j<data.length-2){
                if(j==2){
                    $("#"+comment_news.parentName+"_reply_"+main_id).append('<p class="mark" onclick="comment_news.showDiv('+main_id+')" id="'+comment_news.parentName+'_mark_'+main_id+'">显示隐藏次元</p>');
                    $("#"+comment_news.parentName+"_reply_"+main_id).append('<div id="'+comment_news.parentName+'_show_'+main_id+'" style="display:none"></div>')
                }
                $("#"+comment_news.parentName+"_show_"+main_id).append(reply_html);
            }
            if(j>=data.length -2){
                $("#"+comment_news.parentName+"_reply_"+main_id).append(reply_html);
            }
        }else{
            $("#"+comment_news.parentName+"_reply_"+main_id).show().append(reply_html);
        }
    }
    $(".reply_content>.comment_con_li:last").css("border-bottom",0);
}

//点击回复弹出html
function reply_html(obj,to_uid,to_comment_id,origin_comment_id){
    upload_pic.reset();
    var name = $(obj).parent().siblings(".info_bar").find(".userName").text();
    var html ='<div class="reply_box" id="reply_box_'+to_comment_id+'">';
        html +='<div class="textareaDiv">';
        html +='<textarea name="textfield" class="textare" onkeyup="text_s(this,'+to_uid+','+to_comment_id+','+origin_comment_id+')" placeholder="回复@'+name+'：">';
        html +='</textarea></div>';
        html +='<div class="func_area">';
        //表情
        html +='<div class="ficon_face">';
        html +='<a class="ficon_faceBtn"></a>';
        html +='<div class="faceBox">';
        html +='<div class="fBar">';
        html +='<ul><li class="cur" id="sort1">默认</li><li id="sort2">小幺鸡</li><li id="sort3">小胖鸟</li><li id="sort4">小黑猫</li></ul>';
        html +='<a class="close"></a></div><ul class="face"></ul></div></div>';
        html +='<div class="ficon_img" style="margin-top: 16px;" onclick="upload_pic.show_uploadImg(this)"></div>';
        html +='<a class="SubmitBtn" onclick="comment_news.add(this,'+to_uid+','+to_comment_id+','+origin_comment_id+')">发表</a>';
        html +='<a class="CancelBtn" onclick="comment_news.replyClose(reply_box_'+to_comment_id+')">取消</a>';
        html +='<p class="num_S_txt">您还可以输入<span>1000</span>字</p></div></div>';
    if($(obj).parent().next(".reply_box").length==0){
        $(obj).parent().after(html);
    }
}

//举报弹层html
function report_html(comment_id,report_uid){
    if(comment_news.is_login == false){
        alert("您必须登录才能举报");
        return false
    }
    var html = '<div class="clum autoHeight">';
        html += '<span class="n_span">举报类型：</span>';
        html += '<select class="select" id="report_select">';
        html += '<option value="0">请选择</option>';
        html += '<option value="1">诽谤谩骂</option>';
        html += '<option value="2">涉黄不良信息</option>';
        html += '<option value="3">涉暴</option>';
        html += '<option value="4">涉政</option>';
        html += '<option value="5">恶意攻击</option>';
        html += '</select></div><div class="clum autoHeight">';
        html += '<textarea name="textfield" id="message" maxlength="1000" class="reportTxt"></textarea></div>';
        html += '<a class="submitBtn" onclick="comment_news.report('+comment_id+','+report_uid+')">确定</a><a class="cancelBtn" onclick="closeWidow()">取消</a>';
    openWindow(html);
}

//加精 置顶弹层
function other_html(deal_type,user_name,comment_id){
    var html = '';
        if(deal_type==1){
            html +='<p class="textCen">确定对<span>'+user_name+'</span>的评论加精</p>';
        }
        if(deal_type==2){
            html +='<p class="textCen">确定对<span>'+user_name+'</span>的评论取消加精</p>';
        }
        if(deal_type==3){
            html +='<p class="textCen">确定对<span>'+user_name+'</span>的评论置顶</p>';
        }
        if(deal_type==4){
            html +='<p class="textCen">确定对<span>'+user_name+'</span>的评论取消置顶</p>';
        }
        html += '<a class="cancelBtn fl" onclick="closeWidow()">取消</a>';
        html += '<a class="submitBtn fr" onclick="comment_news.commentReport('+deal_type+','+comment_id+',\''+user_name+'\');commentReportHot('+deal_type+')">确定</a>';
    openWindow(html);
}

//弹层公共函数
function openWindow(html){
    var showBg = $('<div class="show">');
    var layerHtml = $('<div class="conmment_layer"><h2><a class="close" onclick="closeWidow()"></a> 提示信息</h2>');
    $("body").append(showBg).append(layerHtml);
    var _top = ($(window).height() - 200) / 2;
    var _left = ($(window).width() - 400) / 2;
    layerHtml.css({top:_top,left: _left});
    showBg.show();
    layerHtml.show().append(html);
}
function closeWidow(){
    $(".conmment_layer").remove();
    $(".show").remove();
}

//图片上传模块
var upload_pic ={
    obj_file : document.getElementById("fileupload"),
    reset:function(){
        upload_imgArr=[];
        is_show=1;
        $("#uploadForm").remove();
        $("#hiddenInput").val("");
    },
    //上传图片弹层
    show_uploadImg:function(obj){
        var html ='<form enctype="multipart/form-data" id="uploadForm"';
        html +='action="'+comment_Url+'/api/NewComment2/addImg?cb='+cb_url+'/conmment_cd.html"';
        html +=' method="post" target="upload_target">';
        html +='<div class="uploadImg_box"><p>最多添加3张图片</p>';
        html +='<div id="file-list"></div><div class="uploadImgBtn_b">';
        html +='<a href="javascript:;" class="uploadImgBtn">';
        html +='<input type="file" name="userfile[]" multiple="multiple" value="" id="fileupload" accept="image/*" onchange= "upload_pic.uploadImg(this)" /></a>'
        html +='<input type="submit" id="submitImg" style="display: none" /></div></div></form>';
        if(is_show==1){
            $(obj).parent(".func_area").prepend(html);
            is_show=2
        }else{
            upload_pic.reset();
        }
    },
    //限制图片大小
    fileSize:function(obj){
        var fileSize = 0;
        if (isIE && !obj.files) {

        } else {
            fileSize = obj.files[0].size;
        }
        var size = fileSize / 1024;
        if(size>1000){
            alert("附件不能大于1M");
        }else{
            $("#submitImg").click();
        }
    },
    //显示上传图片格式
    imgformat:function(){
        var f=document.getElementById("fileupload").value;
        if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f))
        {
            alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
            return false;
        }else{
            $("#submitImg").click();
        }
    },
    //选择图片
    uploadImg:function(obj){
        var li_len = 3-$(".file_li").size();
        if(!isIE && obj.files.length>li_len){
            if(li_len==3){
                alert("只能上传3张图片")
            }else{
                alert("还可以再上传"+parseInt(li_len)+"张图片")
            }
        }else{
            upload_pic.fileSize(obj);
            upload_pic.imgformat();
        }
    },
    //上传图片之后展示
    getIframeVal:function(json){
        var data_arry = decodeURI(json).split("&")[0];
        var data = data_arry.split(",");
        if(data==""){
            if (isIE) {
                alert(decodeURI(json).split("&")[1]);
            }
            return false
        }
        for(var i=0; i<data.length; i++){
            var file_img = data[i].split(".")[0];
            var file_img_suffix = data[i].split(".")[1];
            var html = '<div class="file_li" id="file-' + file_img +'"></div>';
            $(html).appendTo('#file-list');
            var str = '<div class="tableCell">';
                str +='<a class="delImg" onclick="upload_pic.delImg(\''+file_img+'\',\''+file_img_suffix+'\')"></a>';
                str +='<img src="'+Img_url+'temComment/'+ data[i] +'" /></div>';
            $("#file-" + file_img).append(str);
            upload_imgArr.push(data[i]);
            var imgStr = upload_imgArr.join(",");
            $("#hiddenInput").val(imgStr);
        }
        if(upload_imgArr.length==3){
            $(".uploadImgBtn").hide();
        }else{
            $(".uploadImgBtn").show();
        }
    },
    //删除图片
    delImg:function(imgId,suffix){
        $("#file-" + imgId).remove();
        if($(".file_li").length==3){
            $(".uploadImgBtn").hide();
        }else{
            $(".uploadImgBtn").show();
        }
        upload_imgArr.remove(imgId+"."+suffix);
        var imgStr = upload_imgArr.join(",");
        $("#hiddenInput").val(imgStr)
    },
    //上传图片之后展示
    requestImgStr:function(json){
        var upload_img =json.upload_images.split(",");
        var upload_file_name = json.obj_id%500;
        var upload_file_url =Img_url+'commentImg/'+upload_file_name+'/';
        var big_str = '';
        var small_str = '';
        for(var img_i =0 ;img_i<upload_img.length;img_i++ ){
            var file_img = upload_img[img_i].split(".")[0];
            var file_img_suffix = upload_img[img_i].split(".")[1];
            if(upload_img!=""){
                big_str +='<li><img src="'+upload_file_url+upload_img[img_i]+'"/></li>';
                small_str +='<li onclick="upload_pic.showBigImg(this)" id="small_'+file_img+'">';
                small_str +='<img id="file_'+file_img+'" src="'+upload_file_url+file_img+'_small.'+file_img_suffix+'"/></li>';
            }
        }
        var imgStr = '<ul class="upload_big_img">';
            imgStr += '<span class="leftCur" onclick="upload_pic.prevImg(this,'+upload_img.length+')"></span>';
            imgStr += '<span class="rightCur" onclick="upload_pic.nextImg(this,'+upload_img.length+')"></span>'+big_str+'</ul>';
            imgStr += '<ul class="uploadImg autoHeight" id="upload_'+json.id+'">'+small_str+'</ul>';

        return imgStr;

    },
    //点开大图
    showBigImg:function(obj){
        var small_ul = $(obj).parent("ul");
        var img_len = small_ul.find("li").length;
        var big_ul =  small_ul.siblings(".upload_big_img");
        if($(obj).index()==0){
            big_ul.find(".leftCur").hide();
        }else{
            big_ul.find(".leftCur").show();
        }
        if($(obj).index()==img_len-1){
            big_ul.find(".rightCur").hide();
        }else{
            big_ul.find(".rightCur").show();
        }
        if($(obj).parent("ul").find("li").length==1){
            big_ul.find(".rightCur").hide();
            big_ul.find(".leftCur").hide();
        }
        big_ul.find("li").hide();
        big_ul.find("li").eq($(obj).index()).show();
        big_ul.attr("index",$(obj).index());
    },
    //上一张
    prevImg:function(obj){
        var _parent = $(obj).parent("ul");
        var index = _parent.attr("index");
        var _li = $(obj).siblings("li");
        _li.eq(index).hide();
        _li.eq(index-1).show();
        _parent.attr("index",index-1);
        if(index==1){
            $(obj).hide();
            $(obj).siblings(".rightCur").show();
        }else{
            $(obj).show();
            $(obj).siblings(".rightCur").hide();
        }
        $(obj).siblings(".rightCur").show();
    },
    //下一张
    nextImg:function(obj,size){
        var _parent = $(obj).parent("ul");
        var index = _parent.attr("index");
        var _li = $(obj).siblings("li");
        _li.eq(parseInt(index)).hide();
        _li.eq(parseInt(index)+1).show();
        _parent.attr("index",parseInt(index)+1);
        if(index==size-2){
            $(obj).hide();
            $(obj).siblings(".leftCur").show();
        }else{
            $(obj).show();
            $(obj).siblings(".leftCur").hide();
        }
        $(obj).siblings(".leftCur").show();
    }
};

$(".upload_big_img li").live("click",function(){
    $(this).hide();
});

//评论展示
var comment_news ={
    parentName : 'new',
    user_photo:'',
    user_name : '',
    user_id : '',
    is_login : false,
    commentTotal : 0,
    click_type : 0,
    icon_girl : '<span class="icon_nv"></span>',
    icon_boy : '<span class="icon_nan"></span>',
    icon_jing : '<span class="icon_jing"></span>',
    icon_author :'<span class="icon_author" style="display: none"></span>',
    photo_author :'<span class="authorIcon"></span>',
    photo_author_1 :'<span class="authorIcon_1"></span>',
    currPage : 0,
    auth:function(myinfo){
        if(myinfo==''){
            return;
        }
        var photo = "https://avatar.dmzj.com/"+myinfo[3].substr(0,2)+"/"+myinfo[3].substr(2,2)+"/"+myinfo[3]+".png";
        comment_news.user_photo = photo;
        comment_news.user_name = decodeURI(myinfo[1]);
        comment_news.user_id = myinfo[0];
        comment_news.is_login = true;
    },
    get_json: function (url,data,callback,success_jsonpCallback) {
        $.ajax({
            type: 'get',
            url: url,
            cache: true,
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
    },
    //请求定位
    toCommentTop: function(){
        $('html, body').animate({
            scrollTop: $("#Comment").offset().top
        }, 100);
    },
    //楼层函数
    getFloor:function(curpage,allnum){
        var thisNmu = allnum - (curpage-1)*30;
        return thisNmu;
    },
    //获取评论总数
    comment_total:function(total_type,total_call,func){
        var url = comment_Url+'/api/NewComment2/total';
        var data ="type="+commment_type+"&obj_id="+obj_id+"&countType="+total_type+"&authorId="+authoruid;
        var callback = function(json){
            if(json.result==1000){
                comment_news.commentTotal =json.data;
                $(".comment_num em").html(json.data);
                total_call();
            }
        };
        comment_news.get_json(url,data,callback,func);
    },
    //置顶
    top_status:function(divName,status_type,cal){
        var url = comment_Url+'/api/NewComment2/getTopComment';
        var data = "type="+commment_type+"" +
                   "&obj_id="+obj_id+"" +
                   "&top_status="+status_type;
        var callback = function(json){
            if(json!=""){
                allComment_Html(json,divName);
            }
        };
        comment_news.get_json(url,data,callback,cal);
    },
    hide_gg:function(cur_page,index){
        if(cur_page==1 && comment_news.parentName == "new"){
            $("#top_status_2").show();
        }else{
            $("#top_status_2").hide();
        }
    },
    //获取所有评论
    getAllComment:function(cur_page){
        comment_news.click_type = 0;
        $("#commentAll").html("");
        var url = comment_Url_n+'/v1/'+commment_type+'/latest/'+obj_id;
        var data = "page_index="+cur_page;

        var callback = function(json){
            comment_news.commentTotal =json.total;
            $(".comment_num em").html(json.total);

            var commentIds = json.commentIds;
            var comments = json.comments;
            $(".loading").hide();
            if(commentIds.length == 0){
                $("#commentAll").html("暂无评论");
                return false;
            }
            for(var i=0; i<commentIds.length; i++){
                var dataArr = commentIds[i].split(',');
                var data0 = dataArr[0]
                allComment_Html(comments[data0],'commentAll',cur_page,i);
                if(dataArr.length>1){
                    var newData = dataArr.slice(1);
                    newData.reverse();
                    child_html_n(newData,comments,data0);
                }
            }
            publicClass.setPage(page_new,comment_news.commentTotal,cur_page,comment_news.getAllComment,30);
            comment_news.currPage = cur_page;
            comment_news.hide_gg(cur_page);
            comment_news.toCommentTop();
        };
        comment_news.get_json(url,data,callback,"comment_list_s");

    },
    hotCommentClick:function(){
        if($("#commentHot").html()==""){
            comment_news.hotComment(1,function(){
                comment_news.hotComment(1);
            },"s_1");
        }
    },
    //热门评论
    hotComment:function(cur_page){
        comment_news.click_type = 0;
        $("#commentHot").html("");
        var url = comment_Url+'/api/NewComment2/list';
        var data = "type="+commment_type+"" +
            "&obj_id="+obj_id+"" +
            "&hot=1"+
            "&page_index="+cur_page;
        var callback = function(json){
            $(".loading").hide();
            if(json == ""){
                $("#commentHot").html("暂无评论");
                return false;
            }
            for(var i=0; i<json.length; i++){
                var data =json[i];
                allComment_Html(data,'commentHot',cur_page,i);
                if(data.masterCommentNum>0){
                    child_html(data);
                }
            }
            publicClass.setPage(page_hot,comment_news.commentTotal,cur_page,comment_news.hotComment,30);
            comment_news.hide_gg(cur_page);
            comment_news.toCommentTop();
        };
        comment_news.get_json(url,data,callback,"hotComment_s");
    },
    goodCommentClick:function(){
        if($("#commentGood").html()==""){
            comment_news.comment_total(2,function(){
                comment_news.goodComment(1);
            },"s_2");
        }
    },
    //获取加精评论
    goodComment:function(cur_page){
        comment_news.click_type = 0;
        $("#commentGood").html("");
        var url = comment_Url+'/api/NewComment2/getGoodComment';
        var data = "type="+commment_type+
                   "&obj_id="+obj_id+
                   "&page_index="+cur_page;
        var callback = function(json){
            $(".loading").hide();
            if(json == ""){
                $("#commentGood").html("暂无评论");
                return false;
            }
            for(var i=0; i<json.length; i++){
                var data =json[i];
                allComment_Html(data,'commentGood',cur_page,i);
                if(data.masterCommentNum>0){
                    child_html(data);
                }
            }
            publicClass.setPage(page_good,comment_news.commentTotal,cur_page,comment_news.goodComment,30);
            comment_news.hide_gg(cur_page);
            comment_news.toCommentTop();
        };
        comment_news.get_json(url,data,callback,"goodComment_s");
    },
    authorCommentClick:function(){
        if($("#commentAuthor").html()==""){
            comment_news.comment_total(3,function(){
                comment_news.authorComment(1);
            },"s_3");
        }
    },
    //获取作者评论
    authorComment:function(cur_page){
        comment_news.click_type = 0;
        $("#commentAuthor").html("");
        var url = comment_Url+'/api/NewComment2/getAuthorComment';
        var data = "type="+commment_type+"" +
                   "&obj_id="+obj_id+""+
                   "&author_id="+authoruid+""+
                   "&page_index="+cur_page;
        var callback = function(json){
            $(".loading").hide();
            if(json == ""){
                $("#commentAuthor").html("暂无评论");
                return false;
            }
            for(var i=0; i<json.length; i++){
                var data =json[i];
                allComment_Html(data,'commentAuthor',cur_page,i);
                if(data.masterCommentNum>0){
                    child_html(data);
                }
            }
            publicClass.setPage(page_author,comment_news.commentTotal,cur_page,comment_news.authorComment,30);
            comment_news.hide_gg(cur_page);
            comment_news.toCommentTop();
        };
        comment_news.get_json(url,data,callback,"authorComment_s");
    },
    //发表评论
    add:function(obj,to_uid,to_comment_id,origin_comment_id){
        comment_news.click_type = 1;
        var centent =  encodeURIComponent($.trim($(obj).parent().siblings(".textareaDiv").find(".textare").val()));
        var clickFunc = $(obj).attr('onclick');//点击前onclick属性值
        var clickText = $(obj).html();
        if(!comment_news.is_login){
            alert('您必须登录后才能评论');
            return false;
        }
        if(centent==""){
            alert("评论不能为空");
            return false;
        }
        $(obj).attr('onclick','').addClass('SubBtn_load').html('发表中..');
        var _to_uid = (to_uid==0)?0:to_uid;
        var _to_comment_id = (to_comment_id==0)?0:to_comment_id;
        var _origin_comment_id = (to_uid==0)?0:origin_comment_id;
        var img_src= $("#hiddenInput").val();
        var url = comment_Url_n+'/v1/'+commment_type+'/add/web';
        var data = "obj_id="+obj_id +
                   "&sender_uid="+comment_news.user_id +
                   "&content="+centent+
                   "&to_uid=" +_to_uid +
                   "&to_comment_id=" +_to_comment_id+
                   "&sender_terminal=1"+
                   "&img="+img_src;
        var callback=function(json){
            $(obj).attr('onclick',clickFunc).removeClass('SubBtn_load').html(clickText);
            var code,result;
            if(!(typeof(json.code) == 'undefined' )){
                code = json.code
            }
            if(!(typeof(json.result) == 'undefined' )){
                result = json.result
            }


            if(code == 0 || result == 1000){
                var data = json.data;
                alert(json.msg);
                if(comment_news.currPage == 1 && comment_news.parentName == 'new'){
                    comment_news.getAllComment(1);
                }
                //allComment_Html(data,'commentAll');
                //if(data.masterCommentNum>0){
                //    child_html(data);
                //}
                $(obj).parent().siblings(".textareaDiv").find(".textare").val("");
                $(obj).siblings(".num_S_txt").html("您还可以输入<span>1000</span>个字");
                upload_pic.reset();
                //建立Cookie设置发表间隔
                // setCommentTime(comment_news.user_id,json.limitTime);

                addComicHot(obj);
                comment_news.replyClose($("#reply_box_"+to_comment_id));

            }else if(json.result==2001){
                $("body").append(telZc);
                boxzctel();
            }else{
                alert(json.msg);
            }
        };
        comment_news.get_json(url,data,callback,"add_sucess");

    },

    //点赞
    agree:function(obj,comment_id){
        var url = comment_Url_n+'/v1/'+commment_type+'/like/'+comment_id;
        var data = "comment_id="+comment_id;
                   "&type="+commment_type+"";
        var num = $(obj).children("em").text();
        var callback=function(json){
            if(json.code==0){
                $(obj).children("em").html(parseInt(num)+parseInt(1));
                $(obj).addClass("cur").attr("onclick","");
            }
        };
        comment_news.get_json(url,data,callback,"agree_s");
    },
    //举报
    report:function(comment_id,report_uid){
        var report_type = $('#report_select option:selected').val();
        if(report_type == 0){
            alert("请选择举报类型");
            return false;
        }
        var leave_message = $("#message").val();
        var url = comment_Url+'/api/NewComment2/commentReport';
        var data = "comment_id="+comment_id+"" +
                   "&type="+commment_type+"" +
                   "&uid="+comment_news.user_id+"" +
                   "&report_type="+report_type+"" +
                   "&be_report_uid="+report_uid+""+
                   "&leave_message="+leave_message+"";
        var callback =function(json){
            if(json.code==1000){
                $("#"+comment_news.parentName+"_"+comment_id).attr("r_type","1");
            }
            closeWidow();
            alert(json.msg);
        };
        comment_news.get_json(url,data,callback,"report_s");
    },
    //置顶/取消置顶/加精/取消加精
    commentReport:function(deal_type,comment_id,user_name){
        var url = comment_Url+'/api/NewComment2/authorDeal';
        var data = "comment_id="+comment_id+"" +
                   "&type="+commment_type+"" +
                   "&author_id="+authoruid+"" +
                   "&deal_type="+deal_type+"" +
                   "&obj_id="+obj_id+"";
        var callback =function(json){
            if(json.code==1000){
                closeWidow();
                if(deal_type==1){
                    $(".good_"+comment_id).html("取消加精");
                    $(".good_"+comment_id).attr('onclick','other_html(2,\''+user_name+'\','+comment_id+')');
                    $(".good_"+comment_id).parent().parent().append('<span class="icon_jing"></span>');
                }
                if(deal_type==2){
                    $(".good_"+comment_id).html("加精");
                    $(".good_"+comment_id).attr('onclick','other_html(1,\''+user_name+'\','+comment_id+')');
                    $(".good_"+comment_id).parent().parent().find('.icon_jing').remove();
                }
                if(deal_type==3){
                    $(".top_"+comment_id).attr('onclick','other_html(4,\''+user_name+'\','+comment_id+')');
                    $(".top_"+comment_id).html("取消置顶");
                    $(".top_"+json.oldTopId).html("置顶").attr('onclick','comment_news.is_top(3,\''+user_name+'\','+json.oldTopId+')');
                    $("#top_status_2").html("");
                    comment_news.top_status('top_status_2',2);
                    var html = '';
                        html +='<p class="textCen"> '+user_name+'的评论置顶成功</p>';
                        html += '<a class="submitBtn cl" onclick="closeWidow()")">确定</a>';
                    openWindow(html);
                }
                if(deal_type==4){
                    $(".top_"+comment_id).attr('onclick','other_html(3,\''+user_name+'\','+comment_id+')');
                    $(".top_"+comment_id).html("置顶");
                    $("#top_status_2").html("");
                    var html = '';
                        html +='<p class="textCen"> '+user_name+'的评论已取消置顶</p>';
                        html += '<a class="submitBtn cl" onclick="closeWidow()")">确定</a>';
                    openWindow(html);
                }

            }
        };
        comment_news.get_json(url,data,callback,"commentReport_s");
    },
    is_top:function(deal_type,user_name,comment_id){
        var url = comment_Url+'/api/NewComment2/ajaxSureIfTop';
        var data = "type="+commment_type+"&obj_id="+obj_id+"";
        var callback = function(json){
            if(json.result==200){
                if(deal_type==3){
                    $(".top_"+comment_id).attr('onclick','other_html(4,\''+user_name+'\','+comment_id+')');
                    other_html(deal_type,user_name,comment_id);
                }
                if(deal_type==4){
                    $(".top_"+comment_id).attr('onclick','other_html(3,\''+user_name+'\','+comment_id+')');
                }
            }else{
                var html = '';
                    html +='<p class="textCen">确定对替换当前置顶么？</p>';
                    html += '<a class="cancelBtn fl" onclick="closeWidow()">取消</a>';
                    html += '<a class="submitBtn fr" onclick="' +
                        'comment_news.commentReport('+deal_type+','+comment_id+',\''+user_name+'\');commentReportHot('+deal_type+')">确定</a>';
                openWindow(html);
            }
        };
        comment_news.get_json(url,data,callback,"is_top_s");
    },
    showDiv:function(show_id){
        $("#"+comment_news.parentName+"_show_"+show_id).show();
        $("#"+comment_news.parentName+"_mark_"+show_id).hide();
    },

    replyClose:function(obj){
        $(obj).remove();
        upload_pic.reset();
    },
    openText:function(obj){
        $(obj).siblings(".textCon").removeClass("hei");
        $(obj).html("收起全文>>").attr('onclick','comment_news.closeText(this)')
    },
    closeText:function(obj){
        $(obj).siblings(".textCon").addClass("hei");
        $(obj).html("展开全文>>").attr('onclick','comment_news.openText(this)')
    }
};

Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

var scrollType = 0;

//发表评论增加漫画人气
function addComicHot(obj){
    var centent =  $.trim($(obj).parent().siblings(".textareaDiv").find(".textare").val());
    if(!comment_news.is_login){
        return false;
    }
    if(centent==""){
        return false;
    }
    var url = comment_Url+'/api/hotHit/newComment';
    var data =	"&obj_id="+obj_id + "&content="+centent;
    var callback=function(json){};
    comment_news.get_json(url,data,callback,"addComicHot_s");
}


//置顶/取消置顶/加精/取消加精
function commentReportHot(deal_type){
    var url = comment_Url+'/api/hotHit/authorDeal';
    var data = "deal_type="+deal_type+"" +"&obj_id="+obj_id+"&type="+commment_type ;
    var callback =function(json){};
    comment_news.get_json(url,data,callback,"commentReportHot_s");
}

//设置发表时间间隔
// var setCommentTime = function(uid,limitTime){
//     var uid_limit = uid+'_limitTimeForC';
//     var expiresDate= new Date();
//     expiresDate.setTime(expiresDate.getTime() + (limitTime * 1000)); //?替换成秒数如果为60秒则为 60 *1000
//     $.cookie(uid_limit,"1000",{ expires: expiresDate ,path: '/',domain: 'dmzj.com' });
// }


//初始化
$(function(){
    if($.cookie('comment_cur')!=null && document.location.hash==""){

            var currpage = $.cookie('comment_cur').split(",")[1];
            var comment_id = $.cookie('comment_cur').split(",")[0];
            var locationUrl = window.location.href;
            comment_news.getAllComment(currpage);
            setTimeout(function(){
                document.location.href=locationUrl+"#"+comment_news.parentName+"_box_"+comment_id;
            },"s_0");
            comment_news.top_status('top_status_4',4,"cal_s4");
            comment_news.top_status('top_status_2',2,"cal_s2");
            $.cookie('comment_cur', null ,{path:"/",domain:'.dmzj.com'});

    }else{
        $(window).scroll(function () {
        	//下拉加载请求位置判定
        	var commentTop;
        	if( $('#Comment').length == 0 ){
        		commentTop = $(document).height()-300;
        	}else{
        		commentTop = $('#Comment').offset().top;
        	}
            if ($(document).scrollTop() + $(window).height() >=  commentTop){
                if(scrollType==0){
                        comment_news.getAllComment(1);
                    comment_news.top_status('top_status_4',4,"cal_s4");
                    comment_news.top_status('top_status_2',2,"cal_s2");
                    scrollType=1
                }
            }
        });
    }

    
    //评论类型切换
    $(".comment_tab li").click(function(){
        $(this).addClass("cur").siblings().removeClass("cur");
        var _index = $(this).index();
        $(".comment_con_tab").eq(_index).addClass("con_select").siblings().removeClass("con_select");
        if(_index == 0){
            comment_news.comment_total(0,function(){});
            comment_news.parentName = 'new';
        }
        if(_index == 1){
            comment_news.comment_total(1,function(){});
            comment_news.parentName = 'hot';
        }
        if(_index == 2){
            comment_news.comment_total(2,function(){});
            comment_news.parentName = 'good';
        }
        if(_index == 3){
            comment_news.comment_total(3,function(){});
            comment_news.parentName = 'author';
        }
        comment_news.hide_gg(comment_news.currPage);
    });

    //获取kookie
    if($.cookie("my")!=='' && $.cookie("my") !=null){
        var myinfo = $.cookie("my").split("|");
        if(myinfo[0] == '' || myinfo[1] == '' ){
            $.cookie('my',null, {expires: -1, path: '/', domain: '.dmzj.com', secure: true});
            alert("请重新登陆！");
            myinfo = '';
        }
        comment_news.auth(myinfo);
        $(".loginName").html('<a href="https://i.dmzj.com/" class="name">'+ URLdecode(decodeURI(myinfo[1]))+'</a>|<a href="#" onclick="userSubmit();" class="out">退出</a>')
    }else{
        $(".loginName").html('<a href="https://i.dmzj.com/login" target="_blank" class="name">登录</a>|<a href="https://user.dmzj.com/register?to='+window.location.href+'" class="out">注册</a>')
    }
    text_s($('.textare'),0,0,0);
});



function text_s(obj,to_uid,to_comment_id,origin_comment_id){
    var textare_len = $(obj).val().length;
    var text_len = 1000-textare_len;
    if(text_len<=1000){
        $(obj).parent().siblings(".func_area").children(".num_S_txt").find("span").html(text_len);
    }
    if(text_len<0){
        $(obj).parent().siblings(".func_area").children(".num_S_txt").html("您已超过<span>"+parseInt(text_len*-1)+"</span>字");
        $(obj).parent().siblings(".func_area").children(".SubmitBtn").addClass("unClick").attr("onclick","")
    }else{
        $(obj).parent().siblings(".func_area").children(".num_S_txt").html("您还可以输入<span>"+text_len+"</span>字");
        $(obj).parent().siblings(".func_area").children(".SubmitBtn").removeClass("unClick").attr("onclick","comment_news.add(this,"+to_uid+","+to_comment_id+","+origin_comment_id+")")
    }
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


var arrImg =
{"/微笑":"/faces/default/1.gif",
    "/呲牙":"/faces/default/2.gif",
    "/好吃":"/faces/default/3.gif",
    "/眨眼":"/faces/default/4.gif",
    "/害羞":"/faces/default/5.gif",
    "/委屈":"/faces/default/6.gif",
    "/得意":"/faces/default/7.gif",
    "/发怒":"/faces/default/8.gif",
    "/惊讶":"/faces/default/9.gif",
    "/错愕":"/faces/default/10.gif",
    "/度假":"/faces/default/11.gif",
    "/调皮":"/faces/default/12.gif",
    "/面壁":"/faces/default/13.gif",
    "/快乐":"/faces/default/14.gif",
    "/折磨":"/faces/default/15.gif",
    "/大哭":"/faces/default/16.gif",
    "/阴险":"/faces/default/17.gif",
    "/恶魔":"/faces/default/18.gif",
    "/天使":"/faces/default/19.gif",
    "/尴尬":"/faces/default/20.gif",
    "/可爱":"/faces/default/21.gif",
    "/闭嘴":"/faces/default/22.gif",
    "/白眼":"/faces/default/23.gif",
    "/流泪":"/faces/default/24.gif",
    "/幻想":"/faces/default/25.gif",
    "/糗大了":"/faces/default/26.gif",
    "/亲亲":"/faces/default/27.gif",
    "/空":"/faces/default/28.gif",
    "/吓":"/faces/default/29.gif",
    "/睡觉":"/faces/default/30.gif",
    "/晕":"/faces/default/31.gif",
    "/花痴":"/faces/default/32.gif",
    "/好心情":"/faces/default/33.gif",
    "/困":"/faces/default/34.gif",
    "/发呆":"/faces/default/35.gif",
    "/坏笑":"/faces/default/36.gif",
    "/大胡子":"/faces/default/37.gif",
    "/大笑":"/faces/default/38.gif",
    "/骷髅":"/faces/default/39.gif",
    "/外星人":"/faces/default/40.gif",
    "/土豆男":"/faces/default/41.gif",
    "/土豆女":"/faces/default/42.gif",
    "/难过":"/faces/default/43.gif",
    "/马里奥":"/faces/default/44.gif",
    "/马上回来":"/faces/default/45.gif",
    "/电话":"/faces/default/46.gif",
    "/时间":"/faces/default/47.gif",
    "/白纸":"/faces/default/48.gif",
    "/通讯录":"/faces/default/49.gif",
    "/文档":"/faces/default/50.gif",
    "/花":"/faces/default/51.gif",
    "/礼物":"/faces/default/52.gif",
    "/爱心":"/faces/default/53.gif",
    "/注意":"/faces/default/54.gif",
    "/信件":"/faces/default/55.gif",
    "/光盘":"/faces/default/56.gif",
    "/音乐":"/faces/default/57.gif",
    "/相机":"/faces/default/58.gif",
    "/禁止":"/faces/default/59.gif",
    "/屏蔽":"/faces/default/60.gif",
    "/a吐血":"/faces/a/1.gif",
    "/a坏笑":"/faces/a/2.gif",
    "/a恐惧":"/faces/a/3.gif",
    "/a贱笑":"/faces/a/4.gif",
    "/a哭泣":"/faces/a/5.gif",
    "/a救命":"/faces/a/6.gif",
    "/a戳菊花":"/faces/a/7.gif",
    "/a晕倒":"/faces/a/8.gif",
    "/a抽烟":"/faces/a/9.gif",
    "/a吃惊":"/faces/a/10.gif",
    "/a汗":"/faces/a/11.gif",
    "/a瀑布汗":"/faces/a/12.gif",
    "/a瞎眼":"/faces/a/13.gif",
    "/a难受":"/faces/a/14.gif",
    "/a激动":"/faces/a/15.gif",
    "/a豌豆射手":"/faces/a/16.gif",
    "/a僵尸":"/faces/a/17.gif",
    "/a见光死":"/faces/a/18.gif",
    "/a舔":"/faces/a/19.gif",
    "/a大笑":"/faces/a/20.gif",
    "/a抓狂":"/faces/a/21.gif",
    "/a灰心":"/faces/a/22.gif",
    "/a咖啡":"/faces/a/23.gif",
    "/a欢乐":"/faces/a/24.gif",
    "/a哭":"/faces/a/25.gif",
    "/a狂抓":"/faces/a/26.gif",
    "/a疯了":"/faces/a/27.gif",
    "/a冲":"/faces/a/28.gif",
    "/a雅蠛蝶":"/faces/a/29.gif",
    "/a吃西瓜":"/faces/a/30.gif",
    "/b巴掌":"/faces/b/1.gif",
    "/b奔跑":"/faces/b/2.gif",
    "/b扯":"/faces/b/3.gif",
    "/b出浴":"/faces/b/4.gif",
    "/b弹跳":"/faces/b/5.gif",
    "/b蹬腿":"/faces/b/6.gif",
    "/b飞吻":"/faces/b/7.gif",
    "/b好饱":"/faces/b/8.gif",
    "/b嘿哈":"/faces/b/9.gif",
    "/b哑铃":"/faces/b/10.gif",
    "/b啦啦啦":"/faces/b/11.gif",
    "/b练腰":"/faces/b/12.gif",
    "/b凌乱":"/faces/b/13.gif",
    "/b挠痒":"/faces/b/14.gif",
    "/b拍脸":"/faces/b/15.gif",
    "/b拍手":"/faces/b/16.gif",
    "/b跑":"/faces/b/17.gif",
    "/b皮":"/faces/b/18.gif",
    "/b飘":"/faces/b/19.gif",
    "/b揉眼":"/faces/b/20.gif",
    "/b撒娇":"/faces/b/21.gif",
    "/b踏步":"/faces/b/22.gif",
    "/b跳":"/faces/b/23.gif",
    "/b兴奋":"/faces/b/24.gif",
    "/b仰卧起坐":"/faces/b/25.gif",
    "/b转圈":"/faces/b/26.gif",
    "/c开心":"/faces/c/1.gif",
    "/c不爽":"/faces/c/2.gif",
    "/c鄙视":"/faces/c/3.gif",
    "/c甩尾":"/faces/c/4.gif",
    "/c打地鼠":"/faces/c/5.gif",
    "/c摆动":"/faces/c/6.gif",
    "/c大笑":"/faces/c/7.gif",
    "/c逗":"/faces/c/8.gif",
    "/c转圈":"/faces/c/9.gif",
    "/c挑逗":"/faces/c/10.gif",
    "/c打鼓":"/faces/c/11.gif",
    "/c游泳":"/faces/c/12.gif",
    "/c背对":"/faces/c/13.gif",
    "/c喝水":"/faces/c/14.gif",
    "/c汗":"/faces/c/15.gif",
    "/c卖萌":"/faces/c/16.gif",
    "/c蠕动":"/faces/c/17.gif",
    "/c铃铛":"/faces/c/18.gif",
    "/c伸懒腰":"/faces/c/19.gif",
    "/c生气":"/faces/c/20.gif",
    "/c睡觉":"/faces/c/21.gif",
    "/c舔爪子":"/faces/c/22.gif",
    "/c讨厌":"/faces/c/23.gif",
    "/c跳跃":"/faces/c/24.gif",
    "/c摇头":"/faces/c/25.gif",
    "/c呕吐":"/faces/c/26.gif",
    "/c爱":"/faces/c/27.gif",
    "/c魔术":"/faces/c/28.gif",
    "/c撕纸":"/faces/c/29.gif",
    "/c灯泡":"/faces/c/30.gif",
    "/c情书":"/faces/c/31.gif",
    "/c转身":"/faces/c/32.gif"
};

//表情解析
var key = new Array;
var KeyVal=new Array;
for(var item in arrImg){
    key.push(item);
    KeyVal.push('<img class="tweet_face" style="display: inline; " src="'+urlImg+'/static/images'+arrImg[item]+'">');
}

//js替换全部的函数
function str_replace(search, replace, subject, count){
    var i = 0, j = 0, temp = '', repl = '', sl = 0, fl = 0,
        f = [].concat(search),
        r = [].concat(replace),
        s = subject,
        ra = r instanceof Array, sa = s instanceof Array;
    s = [].concat(s);
    if(count){
        this.window[count] = 0;
    }

    for(i=0, sl=s.length; i < sl; i++){
        if(s[i] === ''){
            continue;
        }
        for(j=0, fl=f.length; j < fl; j++){
            temp = s[i]+'';
            repl = ra ? (r[j] !== undefined ? r[j] : '') : r[0];
            s[i] = (temp).split(f[j]).join(repl);
            if(count && s[i] !== temp){
                this.window[count] += (temp.length-s[i].length)/f[j].length;}
        }
    }
    return sa ? s : s[0];
}

function getImg(con){
    return str_replace(key, KeyVal, con);
}

getImg($("#commentInput").val());


$(function(){
    //插入表情
    var face1 = new Array("/微笑","/呲牙","/好吃","/眨眼","/害羞","/委屈","/得意","/发怒","/惊讶","/错愕","/度假","/调皮","/面壁","/快乐","/折磨","/大哭","/阴险","/恶魔","/天使","/尴尬","/可爱","/闭嘴","/白眼","/流泪","/幻想","/糗大了","/亲亲","/空","/吓","/睡觉","/晕","/花痴","/好心情","/困","/发呆","/坏笑","/大胡子","/大笑","/骷髅","/外星人","/土豆男","/土豆女","/难过","/马里奥","/马上回来","/电话","/时间","/白纸","/通讯录","/文档","/花","/礼物","/爱心","/注意","/信件","/光盘","/音乐","/相机","/禁止","/屏蔽");
    var face2 = new Array("/a吐血","/a坏笑","/a恐惧","/a贱笑","/a哭泣","/a救命","/a戳菊花","/a晕倒","/a抽烟","/a吃惊","/a汗","/a瀑布汗","/a瞎眼","/a难受","/a激动","/a豌豆射手","/a僵尸","/a见光死","/a舔","/a大笑","/a抓狂","/a灰心","/a咖啡","/a欢乐","/a哭","/a狂抓","/a疯了","/a冲","/a雅蠛蝶","/a吃西瓜");
    var face3 = new Array("/b巴掌","/b奔跑","/b扯","/b出浴","/b弹跳","/b蹬腿","/b飞吻","/b好饱","/b嘿哈","/b哑铃","/b啦啦啦","/b练腰","/b凌乱","/b挠痒","/b拍脸","/b拍手","/b跑","/b皮","/b飘","/b揉眼","/b撒娇","/b踏步","/b跳","/b兴奋","/b仰卧起坐","/b转圈");
    var face4 = new Array("/c开心","/c不爽","/c鄙视","/c甩尾","/c打地鼠","/c摆动","/c大笑","/c逗","/c转圈","/c挑逗","/c打鼓","/c游泳","/c背对","/c喝水","/c汗","/c卖萌","/c蠕动","/c铃铛","/c伸懒腰","/c生气","/c睡觉","/c舔爪子","/c讨厌","/c跳跃","/c摇头","/c呕吐","/c爱","/c魔术","/c撕纸","/c灯泡","/c情书","/c转身");
    function showfaces(faces,facepath){
        var facelis='';
        $(".face").empty();
        for (var i=1; i <= faces.length; i++){
            facelis ="<li class=\"fac"+i+"\" id=\"face"+i+"\"><img title=\""+faces[i-1]+"\" src=\""+urlImg+"/static/images/faces/"+facepath+i+".gif \"  width=\"19\" heigh=\"19\"  /><\/li>"
            $(".face").append(facelis);
            $(".fac"+i).bind("click", { id: i }, insertface);
        }
    }
    $(".ficon_faceBtn").live("click",function(){
        $(this).siblings("div.faceBox").css("display") == 'none' ? $(this).siblings("div.faceBox").css("display","inline"): $(this).siblings("div.faceBox").css("display","none");
        showfaces(face1,"default/");
    });

    function insertface(evt){
        var textObj = $(this).parent().parent().parent().parent().siblings(".textareaDiv").find(".textare")[0];
        if(textObj == undefined){
            textObj = $(this).parent().parent().parent().siblings("div.comment-box-in").find(".input")[0];
        }
        var textFeildValue = $("#face"+evt.data.id).children("img").attr("title");
        insertAtCaret(textObj,textFeildValue);
        $(".faceBox").css("display","none");
        $("#sort1").addClass("cur").siblings().removeClass("cur");
    }

    //循环绑定表情种类
    for (i = 1; i <= 4; i++) {
        $("#sort" + i).live("click", { id: i }, changesort);
    }

    function changesort(evt) {
        for (i = 1; i <= 4; i++) {
            $("#sort" + i).removeClass("cur");
        }
        var faces = "face"+evt.data.id;
        $("#sort"+evt.data.id).addClass("cur");
        switch(faces){
            case "face1":
                var facepath="default/";
                faces = face1;
                break;
            case "face2":
                var facepath="a/";
                faces = face2;
                break;
            case "face3":
                var facepath="b/";
                faces = face3;
                break;
            case "face4":
                var facepath="c/";
                faces = face4;
                break;
            default:
                var facepath="default/";
                faces = face1;
        }
        showfaces(faces,facepath);
    }

    //关闭表情窗口
    $(".close").live("click",function(){
        $(".faceBox").css("display","none");
        $("#sort1").addClass("cur").siblings().removeClass("cur");
    });

    //光标在插入表情后面
    function setCaret(textObj) {
        if (textObj.createTextRange) {
            textObj.caretPos = document.selection.createRange().duplicate();
        }
    }
    function insertAtCaret(textObj,textFeildValue) {
        if (document.all) {
            if (textObj.createTextRange && textObj.caretPos) {
                var caretPos = textObj.caretPos;
                caretPos.text = caretPos.text.charAt(caretPos.text.length - 1) == '   ' ? textFeildValue + '   ' : textFeildValue;
            } else {
                textObj.value = textFeildValue;
            }
        } else {
            if (textObj.setSelectionRange) {
                var rangeStart = textObj.selectionStart;
                var rangeEnd = textObj.selectionEnd;
                var tempStr1 = textObj.value.substring(0, rangeStart);
                var tempStr2 = textObj.value.substring(rangeEnd);
                textObj.value = tempStr1 + textFeildValue + tempStr2;
            } else {
                alert("This   version   of   Mozilla   based   browser   does   not   support   setSelectionRange");
            }
        }
    }
});
function boxzctel() {
    $(".account_close").click(function () {
        $("#box_zc,.Choose_way").hide();
    })
    $(".hint_setBtnStyle").click(function () {
        window.open("http://i.dmzj.com/setaccount");
    })
}
