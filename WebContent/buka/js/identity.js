var Identity = function(){};
Identity.prototype = {
    constructor : Identity,
    init : function(){
        this.topid = $('#top');
        this.tips = $('.applyTips');
        this.ap_form = $('.ap_form');
        this.ap_auth = $("#ap_auth");
        this.td_contact = $("#td_contact");
        this.apLoading = $('#apLoading');
    },
    verify : function(){
       if(this.ap_auth.hasClass('ap_type_list')){
           var authval = this.ap_auth.attr('data-value');
            if(!authval){
                this.tips.css({'visibility':'visible'});
                 this.tips.html("未选择申请类型");
                 return false;
            };
       };
       if(this.td_contact.hasClass('td_contact')){
           var contactval = this.td_contact.val();
            if(!contactval || contactval == '（电话/QQ/邮箱）'){
                this.tips.css({'visibility':'visible'});
                 this.tips.html("未填写联系方式");
                 return false;
            };
       };
       return true;
   },
   completeBtn : function(ele){
        if(!ele.data.that.verify()){
            return;
        };
        $(this).hide();
        var _this = $(this);
        ele.data.that.apLoading.show();
        var datas = {
                "uid" : ele.data.that.topid.attr('data-id'),
                "groupid" : ele.data.that.ap_auth.attr('data-value'),
                "contact" : ele.data.that.td_contact.val()
        };
        $.ajax({
            type:"post",
            url: baseurl + 'user/setIdentity',
            cache: false,
            data: datas,
            error: function(){
                ele.data.that.tips.html("申请失败，请重试。");
                ele.data.that.apLoading.hide();
                _this.show();
            },
            success: function(rs) {
                if (!rs || rs.ret != 0) {
                    ele.data.that.tips.css({'visibility':'visible'});
                    ele.data.that.tips.html("申请失败，请重试。");
                    ele.data.that.apLoading.hide();
                    _this.show();
                } else {
                    ele.data.that.tips.css({'visibility':'visible'});
                    ele.data.that.tips.html("<span style='color: green;'>申请成功</span>");
                    if(t){
                        clearTimeout(t);
                    }else{
                        var t = setTimeout(function(){
//                            ele.data.that.ap_form.html("<p>＼( ^▽^ )／ 少年，您提交申请成功，正在审核中，请耐心等待。</p>");
//                            ele.data.that.tips.css({'visibility':'hidden'});
//                            ele.data.that.tips.html("");
                             window.location.reload();
                        },500);
                    }
                }
            }
        });
    },
    cancelBtn : function(ele){
        $(this).hide();
        var _this = $(this);
        ele.data.that.apLoading.show();
        var data = {
            "type": ele.data.types
        }
        $.ajax({
            type:"post",
            url: baseurl + 'user/cancelIdentity',
            cache: false,
            data: data,
            error: function(){
                ele.data.that.tips.html("取消申请失败，请重试。");
                ele.data.that.apLoading.hide();
                _this.show();
            },
            success: function(rs) {
                if (!rs || rs.ret != 0) {
                    ele.data.that.tips.css({'visibility':'visible'});
                    ele.data.that.tips.html("取消申请失败，请重试。");
                    ele.data.that.apLoading.hide();
                    _this.show();
                } else {
                    ele.data.that.tips.css({'visibility':'visible'});
                    ele.data.that.tips.html("<span style='color: green;'>取消成功</span>");
                    if(t){
                        clearTimeout(t);
                    }else{
                        var t = setTimeout(function(){
                             window.location.reload();
                        },500);
                    }
                }
            }
        });
    }
};

